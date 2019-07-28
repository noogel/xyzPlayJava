package com.noogel.xyz.supporter;

import com.lmax.disruptor.EventFactory;
import com.noogel.xyz.dto.PCData;

public class PCDataFactory implements EventFactory<PCData> {
    public PCData newInstance(){
        return new PCData();
    }
}
