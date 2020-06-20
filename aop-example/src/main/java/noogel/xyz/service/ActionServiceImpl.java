package noogel.xyz.service;

import noogel.xyz.annotation.PerfMonitor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ActionServiceImpl implements ActionService {
    @PerfMonitor("say")
    @Override
    public String say(String msg) {
        String content = String.format("program say:%s", msg);
        System.out.println(content);
        return content;
    }

    @Override
    public String listen(String msg) throws InterruptedException {
        String content = String.format("program listen:%s", msg);
        System.out.println(content);
        Thread.sleep(Math.abs((new Random()).nextLong() % 100));
        return content;
    }

    @PerfMonitor("throwExc")
    @Override
    public void throwExc() {
        throw new RuntimeException("test");
    }
}
