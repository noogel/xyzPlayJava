package noogel.xyz.grammar;


import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicApiDemo {
    private static final ExecutorService ES = Executors.newWorkStealingPool();
    private static final AtomicLong AL = new AtomicLong();
    private static final LongAdder LA = new LongAdder();

    public static void main(String[] args) {
        statAl();
        statAl();
        statAl();
        statAl();
        statAl();
        statLa();
        statLa();
        statLa();
        statLa();
        statLa();
    }

    private static void statAl() {
        StopWatch st = new StopWatch();
        List<CompletableFuture<Long>> als = new ArrayList<>();
        for(long i=0; i< 1_000_000L; i++) {
            als.add(CompletableFuture.supplyAsync(AL::incrementAndGet, ES));
        }
        st.start();
        CompletableFuture.allOf(als.toArray(new CompletableFuture[0]));
        st.stop();
        System.out.println("print al:" + st.getTime());
    }

    private static void statLa() {
        StopWatch st = new StopWatch();
        List<CompletableFuture<Long>> als = new ArrayList<>();
        for(long i=0; i< 1_000_000L; i++) {
            als.add(CompletableFuture.supplyAsync(()-> {LA.increment(); return LA.sum();}, ES));
        }
        st.start();
        CompletableFuture.allOf(als.toArray(new CompletableFuture[0]));
        st.stop();
        System.out.println("print la:" + st.getTime());
    }
}
