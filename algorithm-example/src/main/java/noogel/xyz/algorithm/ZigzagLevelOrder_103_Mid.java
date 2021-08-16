package noogel.xyz.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigzagLevelOrder_103_Mid {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resp = new ArrayList<>();
        if (root == null) {
            return resp;
        }
        int flag = 0;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.add(root);
        while (dq.size() > 0) {
            int size = dq.size();
            List<Integer> arr = new ArrayList<>();
            for(int i =0; i< size; i++) {
                if (flag % 2 == 0) {
                    TreeNode poll = dq.poll();
                    arr.add(poll.val);
                    if (poll.left != null) {
                        dq.add(poll.left);
                    }
                    if (poll.right != null) {
                        dq.add(poll.right);
                    }
                } else {
                    TreeNode poll = dq.pollLast();
                    arr.add(poll.val);
                    if (poll.right != null) {
                        dq.addFirst(poll.right);
                    }
                    if (poll.left != null) {
                        dq.addFirst(poll.left);
                    }
                }
            }
            resp.add(arr);
            flag++;
        }
        return resp;
    }
}
