package noogel.xyz.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge_56_Mid {
    public static void main(String[] args) {
        int[][] data = new int[][] {
                new int[]{2,6},
                new int[]{1,3},
                new int[]{8,10},
                new int[]{15,18},
        };
        int[][] merge = merge(data);
        System.out.println(merge);
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return new int[0][];
        }
        Arrays.sort(intervals, Comparator.comparing(item -> item[0]));
        List<List<Integer>> resp = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        int size =0;
        for (int i = 1; i < intervals.length; i++) {
            if (start == intervals[i][0]) {
                end = Math.max(end, intervals[i][1]);
            } else {
                if (intervals[i][0] <= end) {
                    end = Math.max(end, intervals[i][1]);
                } else {
                    intervals[size] = new int[] {start, end};
                    size++;
                    start = intervals[i][0];
                    end = intervals[i][1];
                }
            }
        }
        intervals[size] = new int[] {start, end};
        size++;
        int[][] data = new int[size][];
        for(int i=0; i< size; i++) {
            data[i] = intervals[i];
        }
        return data;
    }
}
