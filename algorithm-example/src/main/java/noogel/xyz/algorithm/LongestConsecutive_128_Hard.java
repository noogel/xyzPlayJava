package noogel.xyz.algorithm;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive_128_Hard {
    public static void main(String[] args) {

    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> sets = new HashSet<>();
        for(int num: nums) {
            sets.add(num);
        }
        int gMax = 0;
        for(int num: sets) {
            if (!sets.contains(num-1)) {
                int offset = 1;
                int curStat = 1;
                while (sets.contains(num + offset)) {
                    offset++;
                    curStat++;
                }
                gMax = Math.max(gMax, curStat);
            }
        }
        return gMax;
    }
}
