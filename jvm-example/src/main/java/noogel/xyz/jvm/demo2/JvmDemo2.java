package noogel.xyz.jvm.demo2;

/**
 * VM Args: -Xss256k
 * 栈越界异常
 */
public class JvmDemo2 {

    private static int stackLength = 1;

    public void stackLeak() {
        stackLength ++;
        stackLeak();
    }

    public int getStackLength() {
        return stackLength;
    }

    public static void main(String[] args) {
        JvmDemo2 demo2 = new JvmDemo2();
        try{
            demo2.stackLeak();
        } catch (Throwable ex) {
            System.out.println("stackLength:" + demo2.getStackLength());
            throw ex;
        }
    }
}
