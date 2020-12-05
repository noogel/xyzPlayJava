package noogel.xyz.server;

import noogel.xyz.thrift.SharedService;
import noogel.xyz.thrift.SharedStruct;
import org.apache.thrift.TException;

public class ShardHandler implements SharedService.Iface {
    @Override
    public SharedStruct test(String key) throws TException {
        SharedStruct sharedStruct = new SharedStruct();
        sharedStruct.setKey(key);
        sharedStruct.setValue("123");
        return sharedStruct;
    }
}
