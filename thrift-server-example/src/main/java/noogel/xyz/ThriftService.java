package noogel.xyz;

import noogel.xyz.server.ShardHandler;
import noogel.xyz.thrift.SharedService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ThriftService {
    public static ShardHandler handler;
    public static SharedService.Processor processor;

    public static void main(String[] args) {
        try{
            handler = new ShardHandler();
            processor = new SharedService.Processor(handler);
            new Thread(()->{
                try {

                    TJSONProtocol.Factory factory = new TJSONProtocol.Factory();
                    TServerTransport serverTransport = new TServerSocket(1234);
                    TServer.Args processor = new TServer.Args(serverTransport).processor(ThriftService.processor);
                    processor.protocolFactory(factory);
                    TServer tServer = new TSimpleServer(processor);
                    tServer.serve();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
