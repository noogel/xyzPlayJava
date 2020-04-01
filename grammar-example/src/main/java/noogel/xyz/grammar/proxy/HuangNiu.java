package noogel.xyz.grammar.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HuangNiu implements InvocationHandler {

    private People people;

    public Object getInstance(People people) {
        this.people = people;
        Class<? extends People> clazz = people.getClass();
        System.out.println("source clazz:" + clazz);
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("HuangNiu proxy start.");
        Object invoke = method.invoke(people);
        System.out.println("HuangNiu proxy end.");
        return invoke;
    }

    public static void main(String[] args) {
        People people = (People) new HuangNiu().getInstance(new PeopleImpl("Jack"));
        people.speak();
        System.out.println("proxy instance:" + people.getClass());
    }
}
