package noogel.xyz.algorithm;

public class MaxDepth {
    public static void main(String[] args) {

    }
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }
    public int maxDepth(TreeNode cur, int level) {
        if(null == cur) return level;
        int left_level = maxDepth(cur.left, level + 1);
        int right_level = maxDepth(cur.right, level + 1);
        return left_level > right_level ? left_level: right_level;
    }
}
