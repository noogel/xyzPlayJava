package noogel.xyz.algorithm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程题，有序打印
 */
public class SortedPrint {

    private volatile AtomicInteger flag = new AtomicInteger(3);

    public SortedPrint() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag.set(2);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (flag.get() != 2) {
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag.set(1);
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (flag.get() != 1) {
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
