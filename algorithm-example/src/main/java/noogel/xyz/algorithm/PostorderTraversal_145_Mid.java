package noogel.xyz.algorithm;

import java.util.*;

public class PostorderTraversal_145_Mid {
    public static void main(String[] args) {

        TreeNode tn = new TreeNode(3);
        TreeNode tn2 = new TreeNode(2);
        tn2.left = tn;
        TreeNode tn3 = new TreeNode(1);
        tn3.right = tn2;

        List<Integer> integers = postorderTraversal(tn);
        System.out.println(1);
    }

    /**
     * 后序
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resp = new ArrayList<>();
        if (root == null) {
            return resp;
        }
        Stack<TreeNode> store = new Stack<>();
        TreeNode prev = null;
        while (root != null || !store.isEmpty()) {
            while (root != null) {
                store.push(root);
                root = root.left;
            }
            root = store.pop();

            if (root.right == null || root.right == prev) {
                resp.add(root.val);
                prev = root;
                root = null;
            } else {
                store.push(root);
                root = root.right;
            }
        }
        return resp;
    }
}
