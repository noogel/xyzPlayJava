package noogel.xyz.algorithm;

public class MaxPathSum_124_Hard {
    int gMaxGain = Integer.MIN_VALUE;

    public static void main(String[] args) {

    }

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return gMaxGain;
    }

    public int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int gain = root.val;
        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);
        gMaxGain = Math.max(gMaxGain, gain + leftGain + rightGain);
        return Math.max(leftGain, rightGain) + gain;
    }
}
