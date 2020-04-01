package nooge.xyz.actor.supervisor;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.Function;

import java.time.Duration;

public class Supervisor extends UntypedActor {

    /*
    * 容错：不是抓住系统所有的错误并恢复，而是将错误（崩溃）孤立出来，不会导致整个系统崩溃（隔离故障组件），备份组件可以替换崩溃组件（冗余）（可恢复性）
    * 容错方式：Restart, Resume, Stop, Escalate
    * let it crash原则
    * 崩溃原因：网络，第三方服务，硬件故障
    * Akka容错：分离业务逻辑（receive）和容错逻辑(supervisorStrategy)
    * 父actor自动成为子actor的supervisor
    * supervisor不fix子actor，而是简单的呈现如何恢复的一个判断==>
    * List(Restart, //重启并替换原actor,mailbox消息可继续发送，
    * //但是接收会暂停至替换完成，重启默认重启所有子actor
    * Resume, //同一个actor不重启，忽略崩溃，继续处理下一个消息
    * Stop, //terminated 不再处理任何消息，剩余消息会进入死信信箱
    * Escalate//交给上层处理
    * )
    * Akka提供两种恢复策略：
    * OneForOneStrategy: 只针对单独actor
    * AllForOneStrategy: 对所有节点，一个节点重启，其它节点全部重启
    *
    * */
    private static SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.ofMinutes(1),
            new Function<Throwable, SupervisorStrategy.Directive>() {
                @Override
                public SupervisorStrategy.Directive apply(Throwable param) throws Exception {
                    if (param instanceof ArithmeticException) {
                        System.out.println("meet ArithmeticException, just resume");
                        return SupervisorStrategy.resume();
                    } else if (param instanceof NullPointerException) {
                        System.out.println("meet NullPointerException, restart");
                        return SupervisorStrategy.restart();
                    } else if (param instanceof IllegalArgumentException) {
                        return SupervisorStrategy.stop();
                    } else {
                        // 交给上层处理
                        return SupervisorStrategy.escalate();
                    }
                }
            }
    );

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Props){
            getContext().actorOf((Props) message, "restartActor");
        } else {
            unhandled(message);
        }
    }
}
