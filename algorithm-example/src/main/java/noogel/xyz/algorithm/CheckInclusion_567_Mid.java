package noogel.xyz.algorithm;

import java.util.Arrays;

public class CheckInclusion_567_Mid {
    public static void main(String[] args) {
        System.out.println(checkInclusion("abc", "abcaaaaa"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] s1Fq = new int[26];
        int[] winFq = new int[26];
        for (int i = 0; i < n; i++) {
            ++s1Fq[s1.charAt(i) - 'a'];
            ++winFq[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(s1Fq, winFq)) {
            return true;
        }
        for (int i = n; i < m; i++) {
            ++winFq[s2.charAt(i) - 'a'];
            --winFq[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(s1Fq, winFq)) {
                return true;
            }
        }
        return false;
    }
}
