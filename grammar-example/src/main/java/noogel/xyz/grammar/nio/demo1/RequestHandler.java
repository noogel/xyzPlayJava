package noogel.xyz.grammar.nio.demo1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

final class RequestHandler {

    private final AsynchronousSocketChannel channel;
    private final Consumer<? super ByteBuffer> success;
    private final Consumer<? super Exception> failure;

    RequestHandler(final AsynchronousSocketChannel channel, final Consumer<? super ByteBuffer> success, final Consumer<? super Exception> failure) {
        assert !Objects.isNull(channel) && !Objects.isNull(success) && !Objects.isNull(failure);

        this.channel = channel;
        this.success = success;
        this.failure = failure;
    }

    AsynchronousSocketChannel getChannel() {
        return this.channel;
    }

    Consumer<? super ByteBuffer> getSuccess() {
        return this.success;
    }

    Consumer<? super Exception> getFailure() {
        return this.failure;
    }

    void closeChannel() {
        try {
            this.channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void headers(final ByteBuffer headers, final Optional<ByteBuffer> body) {
        assert !Objects.isNull(headers);

        this.channel.write(headers, this, new CompletionHandler<Integer, RequestHandler>() {

            @Override
            public void completed(final Integer result, final RequestHandler handler) {
                if (headers.hasRemaining()) {
                    RequestHandler.this.channel.write(headers, handler, this);
                } else if (body.isPresent()) {
                    RequestHandler.this.body(body.get(), handler);
                } else {
                    RequestHandler.this.response();
                }
            }

            @Override
            public void failed(final Throwable exc, final RequestHandler handler) {
                handler.getFailure().accept(new Exception(exc));
                RequestHandler.this.closeChannel();
            }
        });
    }

    void body(final ByteBuffer body, final RequestHandler handler) {
        assert !Objects.isNull(body) && !Objects.isNull(handler);

        this.channel.write(body, handler, new CompletionHandler<Integer, RequestHandler>() {

            @Override
            public void completed(final Integer result, final RequestHandler handler) {
                if (body.hasRemaining()) {
                    RequestHandler.this.channel.write(body, handler, this);
                } else {
                    RequestHandler.this.response();
                }
            }

            @Override
            public void failed(final Throwable exc, final RequestHandler handler) {
                handler.getFailure().accept(new Exception(exc));
                RequestHandler.this.closeChannel();
            }
        });
    }

    void response() {

        final ByteBuffer buffer = ByteBuffer.allocate(2048);
        this.channel.read(buffer, this, new CompletionHandler<Integer, RequestHandler>() {

            @Override
            public void completed(final Integer result, final RequestHandler handler) {
                if (result > 0) {
                    handler.getSuccess().accept(buffer);
                    buffer.clear();

                    RequestHandler.this.channel.read(buffer, handler, this);
                } else if (result < 0) {
                    RequestHandler.this.closeChannel();
                } else {
                    RequestHandler.this.channel.read(buffer, handler, this);
                }
            }

            @Override
            public void failed(final Throwable exc, final RequestHandler handler) {
                handler.getFailure().accept(new Exception(exc));
                RequestHandler.this.closeChannel();
            }
        });
    }
}