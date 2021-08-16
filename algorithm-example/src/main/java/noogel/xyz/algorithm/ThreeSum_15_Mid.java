package noogel.xyz.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15_Mid {
    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{1, -1, -1, 0});
        System.out.println(1);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resp = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return resp;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            int l = i + 1;
            int r = nums.length - 1;
            if (i > 0 && nums[i-1] == nums[i]) {
                continue;
            }
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    resp.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                } else if (sum > 0) {
                    r--;
                } else{
                    l++;
                }
            }
        }
        return resp;
    }
}
