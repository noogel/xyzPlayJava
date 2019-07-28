import singleton.Singleton;
import singleton.StaticSingleton;

public class MainSingleton {
    public static void main(String[] args) {
        System.out.println("1");
        StaticSingleton staticSingleton = StaticSingleton.getInstance();
        System.out.println("2");
        System.out.println(Singleton.STATE);
        System.out.println("3");
        Singleton instance = Singleton.getInstance();
        System.out.println("4");
    }
}
