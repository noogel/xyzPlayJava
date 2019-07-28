package com.noogel.xyz.supporter;

import com.lmax.disruptor.WorkHandler;
import com.noogel.xyz.dto.PCData;

public class Consumer implements WorkHandler<PCData> {
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event: -- " + pcData.get() * pcData.get() + " --");
    }
}
