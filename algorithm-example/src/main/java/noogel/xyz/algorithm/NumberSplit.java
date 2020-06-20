package noogel.xyz.algorithm;

public class NumberSplit {
    public int integerBreak(int n) {
        int area = 0;
        for (int i = 2; i<= n; i++) {
            int min = n / i; // 整除
            int maxCount = n % i;  // 多少个 min + 1
            int minCount = i - maxCount; // 多少个 min

            int minMux = minCount == 0 ? 1: min;
            int maxMux = maxCount == 0 ? 1: (min + 1);
            while (minCount > 1) {
                minMux *= min;
                minCount--;
            }
            while (maxCount > 1) {
                maxMux *= (min + 1);
                maxCount--;
            }
            int newArea = minMux * maxMux;
            if (newArea <= area) {
                break;
            }else {
                area = newArea;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        NumberSplit numberSplit = new NumberSplit();
        System.out.println(numberSplit.integerBreak(2));
        System.out.println(numberSplit.integerBreak(3));
        System.out.println(numberSplit.integerBreak(4));
        System.out.println(numberSplit.integerBreak(5));
        System.out.println(numberSplit.integerBreak(6));
        System.out.println(numberSplit.integerBreak(7));
        System.out.println(numberSplit.integerBreak(8));
        System.out.println(numberSplit.integerBreak(9));
        System.out.println(numberSplit.integerBreak(10));
    }
}
