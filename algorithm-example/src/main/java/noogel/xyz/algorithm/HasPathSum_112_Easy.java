package noogel.xyz.algorithm;

public class HasPathSum_112_Easy {
    boolean flag = false;
    public static void main(String[] args) {
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);
        tn1.left = tn2;
        tn1.right = tn3;
        HasPathSum_112_Easy tt = new HasPathSum_112_Easy();
        tt.hasPathSum(tn1, 0, 5);
        System.out.println(tt.flag);
    }

    public void hasPathSum(TreeNode root, int curSum, int targetSum) {
        if (flag) {
            return;
        }
        if (root == null) {
            return;
        }
        if (root.left != null) {
            hasPathSum(root.left, curSum + root.val, targetSum);
        }
        if (root.right != null) {
            hasPathSum(root.right, curSum + root.val, targetSum);
        }
        if (root.left == null && root.right == null && curSum + root.val == targetSum) {
            flag = true;
        }
    }
}
