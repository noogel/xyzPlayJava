package noogel.xyz.algorithm.singleton;

public class StaticSingleton {
    private StaticSingleton(){
        System.out.println(this.getClass().getSimpleName() + " created.");
    }

    private static class StaticSingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return StaticSingletonHolder.instance;
    }
}
