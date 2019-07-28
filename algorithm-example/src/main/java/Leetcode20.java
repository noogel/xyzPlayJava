import java.util.HashMap;
import java.util.Stack;

public class Leetcode20 {
    public static void main(String[] args) {

    }

    public static boolean isValid(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        hashMap.put('(', 0);
        hashMap.put(')', 1);
        hashMap.put('[', 2);
        hashMap.put(']', 3);
        hashMap.put('{', 4);
        hashMap.put('}', 5);
        Stack<Character> stack = new Stack<Character>();
        for(int idx=0; idx< s.length(); idx++){
            char sourceChar = s.charAt(idx);
            Integer sourceIdx = hashMap.get(sourceChar);
            if(sourceIdx % 2==0){
                stack.push(sourceChar);
            }else{
                if (stack.isEmpty()){
                    return false;
                }
                Character popChar = stack.pop();
                Integer popIdx = hashMap.get(popChar);
                if (sourceIdx - popIdx != 1){
                    return false;
                }
            }
        }
        if (stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
