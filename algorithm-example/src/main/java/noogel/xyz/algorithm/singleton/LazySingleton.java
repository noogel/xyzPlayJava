package noogel.xyz.algorithm.singleton;

public class LazySingleton {
    private LazySingleton(){
        System.out.println(this.getClass().getSimpleName() + " created.");
    }
    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
