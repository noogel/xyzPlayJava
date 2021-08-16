package noogel.xyz.algorithm;

import java.util.Stack;

public class ReverseWords_151_Mid {
    public static void main(String[] args) {
        System.out.println(reverseWords("  a good   example  "));
    }

    public static String reverseWords(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        int flag = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (stack.empty()) {
                    continue;
                }
                if (flag > 0) {
                    sb.append(' ');
                }
                while (!stack.empty()) {
                    sb.append(stack.pop());
                }
                ++flag;
            } else {
                stack.push(s.charAt(i));
            }
        }
        if (!stack.empty()) {
            if (flag > 0) {
                sb.append(' ');
            }
            do {
                sb.append(stack.pop());
            } while (!stack.empty());
        }
        return sb.toString();
    }
}
