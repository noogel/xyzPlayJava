package noogel.xyz.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolveNQueens {
    public static void main(String[] args) {
        SolveNQueens queens = new SolveNQueens();
        System.out.println(queens.solveNQueens(5));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> resp = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> pie = new HashSet<>();
        Set<Integer> na = new HashSet<>();
        dfs(n, 0, new ArrayList<>(), resp, cols, pie, na);
        return resp;
    }

    public void dfs(int max, int row, List<String> curState, List<List<String>> resp,
                    Set<Integer> cols, Set<Integer> pie, Set<Integer> na) {
        // 终结条件
        if (row >= max) {
            if (curState.size() == max) {
                resp.add(curState);
            }
            return;
        }
        // 循环列
        for (int col = 0; col < max; col++) {
            if (cols.contains(col) || pie.contains(row + col) || na.contains(row - col)) {
                continue;
            }
            cols.add(col);
            pie.add(row + col);
            na.add(row - col);
            curState.add(trans(col, max));
            int size = curState.size();
            List<String> newState = (max - row == 1) ? new ArrayList<String>() {{
                addAll(curState);
            }} : curState;
            // 递归层
            dfs(max, row + 1, newState, resp, cols, pie, na);
            cols.remove(col);
            pie.remove(row + col);
            na.remove(row - col);
            curState.remove(size - 1);
        }
    }

    public String trans(int point, int max) {
        char[] chars = new char[max];
        for (int i = 0; i < max; i++) {
            chars[i] = i == point ? 'Q' : '.';
        }
        return String.valueOf(chars);
    }
}
