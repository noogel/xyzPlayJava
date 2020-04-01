package noogel.xyz.grammar;

public class AutoCLoseableDemo implements AutoCloseable {
    private int personCount;

    public AutoCLoseableDemo(int personCount) {
        this.personCount = personCount;
        System.out.println("init size:" + this.personCount);
    }

    @Override
    public void close() throws Exception {
        this.personCount = 0;
        System.out.println("clean.");
    }


    public static void main(String[] args) {
        try(AutoCLoseableDemo room = new AutoCLoseableDemo(4)) {
            System.out.println("process.");
        } catch (Exception ex) {
            System.out.println("ex:" + ex.getMessage());
        }
    }


}
