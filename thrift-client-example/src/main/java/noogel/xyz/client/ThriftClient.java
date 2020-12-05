package noogel.xyz.client;

import noogel.xyz.thrift.SharedService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Objects;

public class ThriftClient {
    public static void main(String[] args) {
        TTransport transport = null;
        try {
            transport = new TSocket("localhost", 1234);
            transport.open();
            TProtocol protocol = new TJSONProtocol(transport);
            SharedService.Client client = new SharedService.Client(protocol);
            doSomething(client);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (Objects.nonNull(transport)) {
                transport.close();
            }
        }
    }

    public static void doSomething(SharedService.Client client) throws TException {
        System.out.println(client.test("a"));
        System.out.println(client.test("b"));
    }
}
