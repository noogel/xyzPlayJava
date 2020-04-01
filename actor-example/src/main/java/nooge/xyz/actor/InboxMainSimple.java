package nooge.xyz.actor;

import akka.actor.*;
import com.typesafe.config.ConfigFactory;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class InboxMainSimple {

    public static void main(String[] args) throws TimeoutException {
        ActorSystem system = ActorSystem.create("inboxdemo", ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        final Inbox inbox = Inbox.create(system);
        inbox.watch(worker);
        inbox.send(worker, MyWorker.Msg.WORKING);
        inbox.send(worker, MyWorker.Msg.DONE);
        inbox.send(worker, MyWorker.Msg.CLOSE);

        while (true){
            Object msg = inbox.receive(Duration.ofSeconds(1));
            if (msg == MyWorker.Msg.CLOSE) {
                System.out.println("inbox: My worker is Closing");
            } else if (msg instanceof Terminated) {
                System.out.println("inbox: My worker is deal");
                system.terminate();
                break;
            } else {
                System.out.println(msg);
            }
        }
    }
}
