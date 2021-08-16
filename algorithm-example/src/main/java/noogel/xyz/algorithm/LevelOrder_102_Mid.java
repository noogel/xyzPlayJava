package noogel.xyz.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LevelOrder_102_Mid {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resp = new ArrayList<>();
        dfs(resp, root, 0);
        return resp;
    }
    public static void dfs(List<List<Integer>> resp, TreeNode root, int deep) {
        if (root == null) {
            return;
        }
        if (resp.size() == deep) {
            resp.add(new ArrayList<>());
        }
        resp.get(deep).add(root.val);
        dfs(resp, root.left, deep + 1);
        dfs(resp, root.right, deep + 1);
    }
}
