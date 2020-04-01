package noogel.xyz.algorithm;

import java.util.*;

public class Anagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram2("anagram", "nagaram"));
        groupAnagrams(new String[]{"eat", "ate", "aaa"});
    }

    public static boolean isAnagram(String s, String t) {
        if (null == s && null == t) {
            return true;
        } else if (null == s || null == t) {
            return false;
        } else if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> left = new HashMap<>();
        Map<Character, Integer> right = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            left.put(s.charAt(i), left.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int j = 0; j < t.length(); j++) {
            right.put(t.charAt(j), right.getOrDefault(t.charAt(j), 0) + 1);
        }
        for (Map.Entry<Character, Integer> c : left.entrySet()) {
            if (!c.getValue().equals(right.getOrDefault(c.getKey(), 0))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnagram2(String s, String t) {
        if (null == s && null == t) {
            return true;
        } else if (null == s || null == t) {
            return false;
        } else if (s.length() != t.length()) {
            return false;
        }
        char[] left = s.toCharArray();
        char[] right = t.toCharArray();
        Arrays.sort(left);
        Arrays.sort(right);
        for (int i = 0; i < left.length; i++) {
            if (left[i] != right[i]) {
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String sr : strs) {
            char[] chars = sr.toCharArray();
            Arrays.sort(chars);
            String sortedSr = new String(chars);
            if (!res.containsKey(sortedSr)) {
                res.put(sortedSr, new ArrayList<>());
            }
            List<String> mapVal = res.get(sortedSr);
            mapVal.add(sr);
        }
        List<List<String>> rep = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : res.entrySet()) {
            rep.add(entry.getValue());
        }
        return rep;
    }
}
