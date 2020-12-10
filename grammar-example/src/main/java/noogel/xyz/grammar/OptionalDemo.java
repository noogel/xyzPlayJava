package noogel.xyz.grammar;

import java.util.concurrent.ThreadLocalRandom;

public class OptionalDemo {
    public static void main(String[] args) {
        int i = 100;
        System.out.println(i++);
        System.out.println(++i);
        System.out.println(++i);
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int j=0;j< 100; j++
             ) {
            System.out.println(current.nextInt(0, 2));
        }
    }
}
