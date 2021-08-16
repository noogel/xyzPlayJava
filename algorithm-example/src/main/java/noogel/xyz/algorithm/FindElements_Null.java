package noogel.xyz.algorithm;

public class FindElements_Null {
    public static void main(String[] args) {
        System.out.println(findElements(new int[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 1}));
    }

    public static int findElements(int[] nums) {
        int repeat = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] < nums[right]) {
                left++;
            } else if (nums[left] > nums[right]) {
                right--;
            } else {
                left++;
                right--;
                repeat++;
            }
        }
        return nums.length - repeat;
    }
}
