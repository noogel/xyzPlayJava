package noogel.xyz.algorithm;


public class CanJump_55_Mid {

    public static void main(String[] args) {

        System.out.println(canJump(new int[]{}));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int rightMost = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
