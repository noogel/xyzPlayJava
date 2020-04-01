package noogel.xyz.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LevelOrder {
    public static void main(String[] args) {
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resp = new ArrayList<>();
        levelOrder(root, 0, resp);
        return resp;
    }
    public void levelOrder(TreeNode cur, int cur_level, List<List<Integer>> resp) {
        if(null == cur){
            return;
        }
        if(cur_level == resp.size()){
            resp.add(new ArrayList<>());
        }
        resp.get(cur_level).add(cur.val);
        levelOrder(cur.left, cur_level+1, resp);
        levelOrder(cur.right, cur_level+1, resp);
    }
}
