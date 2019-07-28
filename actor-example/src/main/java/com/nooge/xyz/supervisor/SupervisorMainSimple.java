package com.nooge.xyz.supervisor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.TimeUnit;

public class SupervisorMainSimple {

    public static void customStrategy(ActorSystem system) throws InterruptedException {
        // 创建一个监管 actor
        ActorRef a = system.actorOf(Props.create(Supervisor.class), "Supervisor");
        // 对于监管 actor创建一个子 actor
        a.tell(Props.create(RestartActor.class), ActorRef.noSender());

        ActorSelection sel = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");

        for (int i = 0; i < 100; i++){
            // 不断的对子 actor 发送消息，导致其发生异常
            sel.tell(RestartActor.Msg.RESTART, ActorRef.noSender());
        }
        system.terminate();
    }

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("lifecycle", ConfigFactory.load("lifecycle.conf"));
        customStrategy(system);
    }
}
