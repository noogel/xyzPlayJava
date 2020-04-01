package noogel.xyz.algorithm;

public class MinDepth {
    public static void main(String[] args) {

    }

    public int minDepth(TreeNode root) {
        return minDepth(root, 0);
    }

    public int minDepth(TreeNode cur, int level) {
        if (null == cur) return level;
        if (null == cur.left && null == cur.right) {
            return level + 1;
        } else if (null != cur.left && null == cur.right) {
            return minDepth(cur.left, level + 1);
        } else if (null == cur.left && null != cur.right) {
            return minDepth(cur.right, level + 1);
        } else {
            int left_level = minDepth(cur.left, level + 1);
            int right_level = minDepth(cur.right, level + 1);
            return left_level > right_level ? right_level : left_level;
        }
    }
}
