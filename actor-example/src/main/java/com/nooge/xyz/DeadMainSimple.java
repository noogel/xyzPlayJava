package com.nooge.xyz;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class DeadMainSimple {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create(
                "deadwatch", ConfigFactory.load("samplehello.conf")
        );
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        system.actorOf(Props.create(WatchActor.class, worker), "watcher");
        worker.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
        worker.tell(MyWorker.Msg.DONE, ActorRef.noSender());
        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
