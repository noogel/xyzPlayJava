package noogel.xyz.algorithm;

public class MaxProfit_122_Easy {
    public static void main(String[] args) {

    }

    public static int maxProfit(int[] prices) {
        int stat = 0;
        for(int i=0; i< prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                stat += diff;
            }
        }
        return stat;
    }
}
