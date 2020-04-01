package noogel.xyz.concurrent.demo2;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadDownLoader {

    // 2、使用的子线程的个数
    private static int threadCount = 3;
    private static ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("start");
        try {
            String path = "http://down-ww3.newasp.net/pcdown/soft/soft1/ideaiuzxb.rar";//文件路径
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");

            int code = conn.getResponseCode();
            if (code == 200) {
                int length = conn.getContentLength();
                // 1、在客户端创建一个与服务端文件一样大小的文件
                RandomAccessFile file = new RandomAccessFile("temp.exe", "rw");
                file.setLength(length);
                // 3、每个子线程下载数据块 ,下载的起始位置和结束位置
                int blockSize = length / threadCount;

                LocalDateTime startTime = LocalDateTime.now();
                CompletableFuture[] futures = new CompletableFuture[threadCount];
                // threadId * blockSize ---- （threadId+1）* blockSize -1
                for (int threadId = 0; threadId < threadCount; threadId++) {
                    // 下载的起始位置和结束位置
                    int startIndex = threadId * blockSize;
                    int endIndex = 0;

                    if (threadId != (threadCount - 1)) {
                        endIndex = (threadId + 1) * blockSize - 1;
                    } else {
                        endIndex = length - 1;
                    }

                    // 开启子线程下载数据
                    CompletableFuture<Void> future = CompletableFuture.runAsync(new ThreadDownLoader(path, startIndex, endIndex, threadId), executorService);
                    futures[threadId] = future;
                }
                CompletableFuture.allOf(futures).join();
                LocalDateTime endTime = LocalDateTime.now();
                Duration between = Duration.between(startTime, endTime);
                System.out.println("文件下载完成, 耗时：" + between.toString());
            } else {
                System.out.println("not 200");
                // 抛出异常
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
