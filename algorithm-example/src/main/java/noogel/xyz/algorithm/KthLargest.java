package noogel.xyz.algorithm;

public class KthLargest {
    private int[] kthL;

    public KthLargest(int k, int[] nums) {
        kthL = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                // 填充
                kthL[i] = nums[i];
            } else {
                // 找最小
                int minIdx = getMiniIdx();
                int minNUm = kthL[minIdx];
                if (nums[i] > minNUm) {
                    kthL[minIdx] = nums[i];
                }
            }
        }
        if (k > nums.length) {
            for (int j = nums.length; j < k; j++) {
                kthL[j] = -1<<31;
            }
        }
    }

    public int getMiniIdx(){
        // 找最小
        int minIdx = 0;
        int minNUm = kthL[0];
        for (int j = 1; j < kthL.length; j++) {
            if (kthL[j] < minNUm) {
                minIdx = j;
                minNUm = kthL[j];
            }
        }
        return minIdx;
    }

    public int add(int val) {
        // 找最小
        int minIdx = getMiniIdx();
        int minNUm = kthL[minIdx];
        if (val > minNUm) {
            kthL[minIdx] = val;
        }
        return kthL[getMiniIdx()];
    }

    public static void main(String[] args) {
        System.out.println(-1<<31);
        System.out.println(Integer.MIN_VALUE);
    }
}
