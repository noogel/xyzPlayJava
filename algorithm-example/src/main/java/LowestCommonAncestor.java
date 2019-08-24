import com.noogel.xyz.dto.TreeNode;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root =new TreeNode(2);
        TreeNode left = new TreeNode(1);
        root.left = left;

        LowestCommonAncestor aaa = new LowestCommonAncestor();
        TreeNode treeNode = aaa.lowestCommonAncestorBST(root, root, left);
        System.out.println(treeNode);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || p == root || q == root) return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l == null) return r;
        else if (r == null) return l;
        else return root;
    }

    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val){
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        if (null == root) return root;
        else if (p.val <= root.val && root.val <= q.val){
            return root;
        }else if (root.val > q.val){
            return lowestCommonAncestorBST(root.left, p, q);
        }else if (p.val > root.val){
            return lowestCommonAncestorBST(root.right, p, q);
        }else{
            return root;
        }
    }

}
