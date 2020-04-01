package noogel.xyz.concurrent.demo1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    private static final int count = 4;
    private static final Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static final List<Integer> container = new ArrayList<>();
    private static final ExecutorService excutor = Executors.newFixedThreadPool(count);
    private static final ExecutorService producerExcutor = Executors.newSingleThreadExecutor();

    public static void safeAdd(int i) {
        synchronized (container) {
            container.add(i);
        }
    }

    public static int safeSize() {
        synchronized (container) {
            return container.size();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i< count; i++) {
            excutor.submit(new Runnable() {
                @Override
                public void run() {
                    String threadName = "-- "  + LocalDateTime.now().toString() + " " + Thread.currentThread().getName() + " -- ";
                    while (true) {
                        if (queue.isEmpty()) {
                            System.out.println(threadName + "sleep."  + " container size:" + safeSize());
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                System.out.println(threadName + "interrupted.");
                                Thread.currentThread().interrupt();
                            }
                            continue;
                        }
                        Integer poll = queue.poll();
                        safeAdd(poll);
//                        System.out.println(threadName + poll + " container size:" + safeSize());
                    }
                }
            });
        }
        producerExcutor.submit(new Runnable() {
            @Override
            public void run() {
                String threadName = "-- "  + LocalDateTime.now().toString() + " " + Thread.currentThread().getName() + " -- ";
                for(int i=0; i< 1000000; i++) {
                    queue.add(i);
//                    System.out.println(threadName + "add:" + i);
                }
            }
        });
    }
}
