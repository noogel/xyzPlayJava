package com.noogel.xyz.supporter;

import com.lmax.disruptor.RingBuffer;
import com.noogel.xyz.dto.PCData;

import java.nio.ByteBuffer;

public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        try {
            PCData pcData = ringBuffer.get(sequence);
            pcData.set(bb.getLong(0));
        }
        finally {
            ringBuffer.publish(sequence);
        }
    }
}
