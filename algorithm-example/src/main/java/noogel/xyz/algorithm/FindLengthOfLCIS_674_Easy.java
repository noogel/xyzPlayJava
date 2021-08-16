package noogel.xyz.algorithm;

public class FindLengthOfLCIS_674_Easy {
    public static void main(String[] args) {
        System.out.println(findLengthOfLCIS(new int[] {1,3,5,4,7}));
    }

    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;
        int l = 0;
        int r = l + 1;
        int max = r - l;
        while (r< nums.length) {
            if (nums[r - 1] < nums[r]) {
                r++;
            } else {
                l = r;
                r++;
            }
            max = Math.max(max, r - l);
        }
        return max;
    }
}
