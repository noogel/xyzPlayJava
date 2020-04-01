package noogel.xyz.grammar.proxy;

public class PeopleImpl implements People {

    private final String name;

    PeopleImpl(String name) {
        this.name = name;
    }

    public void speak() {
        System.out.println(name + " say: 'Hello world!'");
    }
}
