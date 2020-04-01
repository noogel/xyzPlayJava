package noogel.xyz.grammar.nio.demo1;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public final class NioAsyncHttpClient implements AutoCloseable {

    private static final int PORT = 80;

    private AsynchronousChannelGroup httpChannelGroup;

    public static NioAsyncHttpClient create(final AsynchronousChannelGroup httpChannelGroup) {
        return new NioAsyncHttpClient(httpChannelGroup);
    }

    private NioAsyncHttpClient(final AsynchronousChannelGroup httpChannelGroup) {
        Objects.requireNonNull(httpChannelGroup);

        this.httpChannelGroup = httpChannelGroup;
    }

    public void get(final String url, final String headers, final Consumer<? super ByteBuffer> success, final Consumer<? super Exception> failure)
            throws URISyntaxException, IOException {
        Objects.requireNonNull(url);
        Objects.requireNonNull(headers);
        Objects.requireNonNull(success);
        Objects.requireNonNull(failure);

        process(url, Optional.<ByteBuffer>empty(), headers, success, failure);
    }

    public void post(final String url, String data, final String headers, final Consumer<? super ByteBuffer> success, final Consumer<? super Exception> failure)
            throws URISyntaxException, IOException {
        Objects.requireNonNull(data);
        Objects.requireNonNull(url);
        Objects.requireNonNull(headers);
        Objects.requireNonNull(success);
        Objects.requireNonNull(failure);

        process(url, Optional.of(ByteBuffer.wrap(data.getBytes())), headers, success, failure);
    }

    @Override
    public void close() throws Exception {
        this.httpChannelGroup.shutdown();
    }

    private void process(final String url, final Optional<ByteBuffer> data, final String headers, final Consumer<? super ByteBuffer> success,
                         final Consumer<? super Exception> failure) throws IOException, URISyntaxException {
        assert StringUtils.isNotEmpty(url) && !Objects.isNull(data) && StringUtils.isNotEmpty(headers) && !Objects.isNull(success) && !Objects.isNull(failure);

        final URI uri = new URI(url);
        final SocketAddress serverAddress = new InetSocketAddress(getHostName(uri), PORT);
        final RequestHandler handler = new RequestHandler(AsynchronousSocketChannel.open(this.httpChannelGroup), success, failure);

        doConnect(uri, handler, serverAddress, ByteBuffer.wrap(createRequestHeaders(headers, uri).getBytes()), data);
    }

    private void doConnect(final URI uri, final RequestHandler handler, final SocketAddress address, final ByteBuffer headers, final Optional<ByteBuffer> body) {
        assert !Objects.isNull(uri) && !Objects.isNull(handler) && !Objects.isNull(address) && !Objects.isNull(headers);

        handler.getChannel().connect(address, null, new CompletionHandler<Void, Void>() {

            @Override
            public void completed(final Void result, final Void attachment) {
                handler.headers(headers, body);
            }

            @Override
            public void failed(final Throwable exc, final Void attachment) {
                handler.getFailure().accept(new Exception(exc));
            }
        });
    }

    private String createRequestHeaders(final String headers, final URI uri) {
        assert StringUtils.isNotEmpty(headers) && !Objects.isNull(uri);

        return headers + "Host: " + getHostName(uri) + "\r\n\r\n";
    }

    private String getHostName(final URI uri) {
        assert !Objects.isNull(uri);

        return uri.getHost();
    }
}