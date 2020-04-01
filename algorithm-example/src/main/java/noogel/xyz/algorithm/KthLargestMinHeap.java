package noogel.xyz.algorithm;

import java.util.PriorityQueue;

public class KthLargestMinHeap {
    private PriorityQueue<Integer> minHeap;
    private int kSize;

    public KthLargestMinHeap(int k, int[] nums) {
        kSize = k;
        minHeap = new PriorityQueue<Integer>(kSize);
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (minHeap.size() < kSize) {
            minHeap.offer(val);
        } else if (minHeap.peek() < val) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }
}
