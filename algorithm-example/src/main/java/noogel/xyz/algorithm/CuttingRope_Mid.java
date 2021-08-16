package noogel.xyz.algorithm;

public class CuttingRope_Mid {
    public static void main(String[] args) {
        System.out.println(cuttingRope(2));
        System.out.println(cuttingRope(10));
    }

    public static int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3;
        int b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a -1) * 4;
        return (int) Math.pow(3, a) * 2;
    }
}
