package noogel.xyz.grammar.nio.demo1;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class Demo1 {

    public void get() throws Exception {
        doGet(() -> "https://httpbin.org/get", () -> String.format("%s %s %s %s", "GET", "get", "application/json", String.valueOf(0)));
    }

    private void doGet(final Supplier<? extends String> url, final Supplier<? extends String> headers) throws Exception {

        final WritableByteChannel target = Channels.newChannel(System.out);
        final AtomicBoolean pass = new AtomicBoolean(true);
        final CountDownLatch latch = new CountDownLatch(1);
        AsynchronousChannelGroup tenThreadGroup = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());

        try (NioAsyncHttpClient client = NioAsyncHttpClient.create(tenThreadGroup)) {
            client.get(url.get(), headers.get(), (buffer) -> {
                try {
                    buffer.flip();

                    while (buffer.hasRemaining()) {
                        target.write(buffer);
                    }
                } catch (IOException e) {
                    pass.set(false);
                } finally {
                    latch.countDown();
                }
            }, (exc) -> {
                exc.printStackTrace();
                pass.set(false);
                latch.countDown();
            });
        }

        latch.await();
    }
}
