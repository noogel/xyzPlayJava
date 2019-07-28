package singleton;

public class Singleton {
    public static String STATE = "PAID";
    private Singleton(){
        System.out.println(this.getClass().getSimpleName() + " created.");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
