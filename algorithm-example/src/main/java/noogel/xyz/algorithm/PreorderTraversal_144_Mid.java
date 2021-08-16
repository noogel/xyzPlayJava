package noogel.xyz.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal_144_Mid {
    public static void main(String[] args) {

    }

    /**
     * 前序
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resp = new ArrayList<>();
        if (root == null) {
            return resp;
        }
        Stack<TreeNode> store = new Stack<>();
        store.push(root);
        while (!store.empty()) {
            TreeNode pop = store.pop();
            resp.add(pop.val);
            if (pop.right != null) {
                store.push(pop.right);
            }
            if (pop.left != null) {
                store.push(pop.left);
            }
        }
        return resp;
    }
}
