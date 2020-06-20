package noogel.xyz.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LogAspect {

//    @Pointcut("this(noogel.xyz.service.ActionServiceImpl)")
//    public void matchAction() {}
//
//    @Before("matchAction()")
//    public void before() {
//        System.out.println("matchAction 前置通知......");
//    }
//
//    @After("matchAction()")
//    public void after() {
//        System.out.println("matchAction 后置通知......");
//    }

    @Pointcut("execution(public * noogel.xyz.service.ActionServiceImpl.say(*))")
    public void matchSay() {}


    @Before("matchSay()")
    public void beforeSay() {
        System.out.println("matchSay 前置通知......");
    }

    @After("matchSay()")
    public void afterSay() {
        System.out.println("matchSay 后置通知......");
    }

}
