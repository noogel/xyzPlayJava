package noogel.xyz.algorithm;

import java.util.HashMap;

public class LengthOfLongestSubstring_3_Mid {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("abba"));
        System.out.println(lengthOfLongestSubstring(""));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int max = 0;
        int left = 0;
        HashMap<Character, Integer> container = new HashMap<>();
        for(int point = 0; point < s.length(); point++) {
            if (container.containsKey(s.charAt(point))) {
                left = Math.max(left, container.get(s.charAt(point)) + 1);
            }
            container.put(s.charAt(point), point);
            max = Math.max(max, point - left +1);
        }
        return max;
    }
}
