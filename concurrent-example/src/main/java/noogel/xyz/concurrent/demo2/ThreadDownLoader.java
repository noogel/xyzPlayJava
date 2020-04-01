package noogel.xyz.concurrent.demo2;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThreadDownLoader extends Thread {
    private String path;
    private int startIndex;
    private int endIndex;
    private int threadId;

    public ThreadDownLoader(String path, int startIndex, int endIndex, int threadId) {
        this.path = path;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;

    }

    @Override
    public void run() {
        downLoad(path, startIndex, endIndex, threadId);
    }

    public void downLoad(String path, int startIndex, int endIndex, int threadId) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            // 设置子线程请求数据的范围
            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
            int code = conn.getResponseCode();
            if (code == 206) {// 请求部分数据
                System.out.println("线程" + threadId + "开始下载...............");
                InputStream is = conn.getInputStream();
                RandomAccessFile file = new RandomAccessFile("temp.rar", "rw");
                // 指定从哪个位置开始写数据
                file.seek(startIndex);

                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    file.write(buffer, 0, len);
                }

                file.close();
                System.out.println("线程" + threadId + "下载完成...............");
            }
        } catch (Exception e) {
            System.out.println("线程" + threadId + "下载失败...............");
            e.printStackTrace();
        }

    }
}
