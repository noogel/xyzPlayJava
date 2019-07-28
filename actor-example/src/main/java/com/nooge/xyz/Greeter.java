package com.nooge.xyz;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public static enum Msg{
        GREET, DONE;
    }
    @Override
    public void onReceive(Object message) throws Throwable {
        if(message == Msg.GREET){
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(message);
        }
    }
}
