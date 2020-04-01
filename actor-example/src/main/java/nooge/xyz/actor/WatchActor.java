package nooge.xyz.actor;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WatchActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public WatchActor(ActorRef ref){
        getContext().watch(ref);
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Terminated){
            System.out.println(String.format("%s has terminated, shutting down system",
                    ((Terminated) message).getActor().path()));
        }else{
            unhandled(message);
        }
    }
}
