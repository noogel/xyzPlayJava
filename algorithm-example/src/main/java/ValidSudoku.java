import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidSudoku {
    public static void main(String[] args) {
        System.out.println(0 / 3);
        System.out.println(1 / 3);
        System.out.println(2 / 3);
        System.out.println(3 / 3);
        System.out.println(4 / 3);
        System.out.println(5 / 3);
        System.out.println(String.format("%s%s", 0 / 3, 1 / 3));
    }

    public boolean isValidSudoku(char[][] board) {
        Map<String, List<Character>> group = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            List<Character> heng = new ArrayList<>();
            List<Character> shu = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {

                } else if (heng.contains(board[i][j])) {
                    return false;
                } else {
                    heng.add(board[i][j]);
                    if (j >= i && !patchGroup(group, i, j, board, false)) {
                        return false;
                    }
                }
                if (board[j][i] == '.') {

                } else if (shu.contains(board[j][i])) {
                    return false;
                } else {
                    shu.add(board[j][i]);
                    if (j >= i && !patchGroup(group, j, i, board, true)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean patchGroup(Map<String, List<Character>> group, int x, int y, char[][] board, boolean skipijEqual) {
        if (skipijEqual && x == y) {
            return true;
        }
        String key = String.format("%s%s", x / 3, y / 3);
        List<Character> val = group.getOrDefault(key, new ArrayList<>());
        if (val.contains(board[x][y])) {
            return false;
        }
        val.add(board[x][y]);
        group.put(key, val);
        return true;
    }
}
