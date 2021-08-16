package noogel.xyz.aop;

import noogel.xyz.annotation.PerfMonitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // 越小越先执行
public class PerfAspect {

    @Pointcut("@annotation(noogel.xyz.annotation.PerfMonitor)")
    public void perfMethod() {
    }

    @Around("perfMethod()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        String className = joinPoint.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String extVal = methodSignature.getMethod().getAnnotation(PerfMonitor.class).value();
        long startTms = System.currentTimeMillis();
        System.out.println("PerfAspect.Around start.");
        try {
            obj = joinPoint.proceed();
        } finally {
            long endTms = System.currentTimeMillis();
            String msg = String.format("%s.%s() | %s | timeUsed: %s .", className, methodName, extVal, endTms - startTms);
            System.out.println(msg);
        }
        System.out.println("PerfAspect.Around end.");
        return obj;
    }
}
