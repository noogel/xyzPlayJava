package noogel.xyz.algorithm;

public class LowestCommonAncestor_236_Mid {
    private TreeNode ans;
    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dsf(root, p, q);
        return this.ans;
    }

    public boolean dsf(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dsf(root.left, p, q);
        boolean rson = dsf(root.right, p, q);

        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            this.ans = root;
        }
        return lson || rson || root.val == p.val || root.val == q.val;
    }
}
