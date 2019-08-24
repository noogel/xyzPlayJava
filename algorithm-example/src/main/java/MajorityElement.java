import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        System.out.println(run(new int[]{3, 3, 4}));
    }

    public static int run(int[] nums) {
        Arrays.sort(nums);
        int max = nums.length / 2 + 1;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (!mp.containsKey(cur)) {
                mp.put(cur, 0);
            }
            int next = mp.get(cur) + 1;
            if (next == max) {
                return cur;
            }
            mp.put(cur, next);
        }
        return 0;
    }
}
