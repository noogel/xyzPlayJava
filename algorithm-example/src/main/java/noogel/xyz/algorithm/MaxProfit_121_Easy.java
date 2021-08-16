package noogel.xyz.algorithm;

public class MaxProfit_121_Easy {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {7,1,5,3,6,4}));
    }

    public static int maxProfit(int[] prices) {
        int minP = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            minP = Math.min(price, minP);
            profit = Math.max(profit, price - minP);
        }
        return profit;
    }
}
