package noogel.xyz.algorithm;

public class Trap_42_Hard {

    public static void main(String[] args) {
        int trap = trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(trap);
    }

    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = Math.max(height[j], rightMax[j + 1]);
        }
        int ans = 0;
        for (int k = 0; k < n; k++) {
            ans += Math.min(leftMax[k], rightMax[k]) - height[k];
        }
        return ans;
    }
}
