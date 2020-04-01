package noogel.xyz.jvm.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 堆内存溢出
 */
public class JvmDemo1 {

    public static void main(String[] args) {
        Random random = new Random();
        List<Long> list = new ArrayList<>();
        while (true) {
            list.add(random.nextLong());
        }
    }
}
