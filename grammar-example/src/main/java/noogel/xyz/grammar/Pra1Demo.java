package noogel.xyz.grammar;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author： xyz
 * @Date： 2020-12-06
 * @Description： 练习题
 */
public class Pra1Demo {

    /**
     * 随机获取数行
     *
     * @param reqNum
     * @param path
     * @return
     * @throws IOException
     */
    public static String[] randomLines(int reqNum, String path) throws IOException {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String[] resp = new String[reqNum];
        Path path1 = Paths.get(path);
        int lineNum = 0;
        ByteBuffer byteBuffer = null;
        try (FileChannel fc = FileChannel.open(path1)) {
            MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            byteBuffer = ByteBuffer.allocate(1024);
            while (map.hasRemaining()) {
                byte bt = map.get();
                // 10 = \n
                if (bt != 10) {
                    // 满了自动扩充内存
                    if (byteBuffer.capacity() == byteBuffer.position()) {
                        // todo
                    }
                    byteBuffer.put(bt);
                    continue;
                }
                byteBuffer.flip();
                // 小于行数，自动填充
                if (lineNum < reqNum) {
                    resp[lineNum] = new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit(), Charset.defaultCharset());
                } else {
                    //　大于行数随机抽取
                    int rand = random.nextInt(0, lineNum + 1);
                    if (rand < reqNum) {
                        while (rand < reqNum - 1) {
                            resp[rand] = resp[++rand];
                        }
                        resp[rand] = new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit(), Charset.defaultCharset());
                    }
                }
                byteBuffer.clear();
                lineNum++;
            }
            byteBuffer.clear();
            return resp;
        } finally {
            if (byteBuffer != null) {
                byteBuffer.clear();
            }
        }
    }

    /**
     * 随机生成大文件
     *
     * @param path
     * @throws IOException
     */
    public static void genFiles(String path) throws IOException {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        int lines = 1;
        try (BufferedWriter wf = new BufferedWriter(new FileWriter(path))) {
            while (lines < 1000000) {
                wf.write(lines + "_" + RandomStringUtils.randomAlphanumeric(current.nextInt(1, 1000)) + "\n");
                lines++;
            }
        }
    }

    public static void main(String[] args) {
        try {
//            genFiles("/home/xyz/IdeaProjects/xyzPlayJava/grammar-example/src/main/java/noogel/xyz/grammar/test.txt");
            String[] strings = randomLines(10, "/home/xyz/IdeaProjects/xyzPlayJava/grammar-example/src/main/java/noogel/xyz/grammar/test.txt");
            for (String str : strings) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}