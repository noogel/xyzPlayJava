package noogel.xyz.concurrent.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        final ExecutorService executor = Executors.newSingleThreadExecutor();  // 这个只能开启一个线程
        executor.submit(new Runnable() {
            public void run() {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Hello " + threadName);
                    executor.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    System.out.println("tasks interrupted");
                } finally {
                    if (!executor.isTerminated()) {
                        System.out.println("cancel non-finished tasks.");
                    }
                    executor.shutdown();
                    System.out.println("shutdown finished!");
                }
            }
        });


        // Callable 返回一个值
        Callable<Integer> task = new Callable<Integer>() {
            public Integer call() throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    return 123;
                } catch (InterruptedException e) {
                    throw new IllegalStateException("task interrupted", e);
                }
            }
        };

        ExecutorService executor2 = Executors.newFixedThreadPool(1);  // 线程池，可以开启多个线程。
        Future<Integer> future = executor2.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = future.get(3, TimeUnit.SECONDS);  // 超时设置，在执行这一行开始才会去实际执行 task。

        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);


        ExecutorService executor3 = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        // Executors支持通过invokeAll()一次批量提交多个callable。这个方法结果一个callable的集合，然后返回一个future的列表。
        executor3.invokeAll(callables)
                .stream()
                .map(future2 -> {
                    try {
                        return future2.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }
}
