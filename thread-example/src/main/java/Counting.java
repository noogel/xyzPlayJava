public class Counting {

    public static void main(String[] args) throws InterruptedException {
        class Counter {
            private int count = 0;

            public synchronized void incr() {
                ++count;
            }

            public int getCount() {
                return count;
            }

        }
        final Counter counter = new Counter();
        class CountingThread extends Thread {
            @Override
            public void run() {
                for (int x = 0; x < 1000000; x++)
                    counter.incr();
            }
        }

        CountingThread t1 = new CountingThread();
        CountingThread t2 = new CountingThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());
    }
}
