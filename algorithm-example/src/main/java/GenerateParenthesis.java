import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();
        List<String> stringList = obj.generateParenthesis(3);
        System.out.println(stringList);
    }

    public List<String> generateParenthesis(int n) {
        List<String> collect = new ArrayList<>();
        gen(collect, "", n, n);
        return collect;
    }

    public void gen(List<String> collect, String cur, int left, int right) {
        if (left == 0 && right == 0) {
            collect.add(cur);
            return;
        }
        if (left > 0) {
            gen(collect, cur + "(", left - 1, right);
        }
        if (right > 0 && right > left) {
            gen(collect, cur + ")", left, right - 1);
        }
    }
}
