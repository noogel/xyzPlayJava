package noogel.xyz.service;

public interface ActionService {

    String say(String msg);
    String listen(String msg) throws InterruptedException;
    void throwExc();
}
