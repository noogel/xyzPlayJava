import com.noogel.xyz.dto.TreeNode;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class ValidBSTDemo {


    public static void main(String[] args) {
        TreeNode root =new TreeNode(1);
        TreeNode left = new TreeNode(1);
        root.left = left;
        System.out.println(isValidBST1(root));
    }

    public static boolean isValidBST1(TreeNode root) {
        return forEachNode(root, new ArrayList<>());
    }

    public static boolean forEachNode(TreeNode node, List<Integer> val) {
        if (null == node) {
            return true;
        }
        if (!forEachNode(node.left, val)){
            return false;
        }
        if (!validOrAdd(val, node)){
            return false;
        }
        if (!forEachNode(node.right, val)){
            return false;
        }
        return true;
    }

    public static boolean validOrAdd(List<Integer> val, TreeNode node){
        if(val.size() > 0 && val.get(val.size() - 1) >= node.val){
            return false;
        }else{
            return val.add(node.val);
        }
    }

    public static boolean isValidBST(TreeNode root) {
        return subValidBSTLeft(root, null, null);
    }

    public static boolean subValidBSTLeft(TreeNode node, Integer min, Integer max) {
        if (null == node){
            return true;
        }
        if (null == min && null == max){
        }else if (null == min && null !=max && node.val < max){

        }else if (null != min && null == max && min < node.val){

        }else if (null != min && null != max && min < node.val && node.val < max){

        }else {
            return false;
        }
        // left
        if (!subValidBSTLeft(node.left, min, node.val)){
            return false;
        }
        // right
        if (!subValidBSTLeft(node.right, node.val, max)){
            return false;
        }
        return true;
    }
}
