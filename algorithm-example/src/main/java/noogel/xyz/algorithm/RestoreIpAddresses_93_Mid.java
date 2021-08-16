package noogel.xyz.algorithm;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses_93_Mid {
    public static void main(String[] args) {
        for (String item :
                restoreIpAddresses("101023")) {
            System.out.println(item);
        }
    }

    public static List<String> restoreIpAddresses(String s) {
        int sLen = s.length();
        List<String> resp = new ArrayList<>();
        if (sLen < 1 || sLen > 12) {
            return resp;
        }
        subProcess(resp, "", s.substring(0, 1), 3, s.substring(1));
        if (sLen > 1) {
            subProcess(resp, "", s.substring(0, 2), 3, s.substring(2));
        }
        if (sLen > 2) {
            subProcess(resp, "", s.substring(0, 3), 3, s.substring(3));
        }
        return resp;
    }

    public static void subProcess(List<String> resp, String pref, String cur, int leftGroup, String leftStr) {
        int leftLen = leftStr.length();
        int curLen = cur.length();
        if (leftLen > leftGroup * 3) {
            return;
        }
        if (leftLen == 0 && leftGroup > 0) {
            return;
        }
        if (curLen > 1 && cur.charAt(0) == '0') {
            return;
        }
        if (curLen > 2 && Integer.parseInt(cur) > 255) {
            return;
        }
        if (leftLen == 0 && leftGroup == 0) {
            resp.add(pref + "." + cur);
        }
        if (leftLen > 0 && leftGroup > 0) {
            String newPref = pref + (pref.length() > 0 ? "." : "") + cur;
            subProcess(resp, newPref, leftStr.substring(0, 1), leftGroup -1, leftStr.substring(1));
            if (leftLen > 1) {
                subProcess(resp, newPref, leftStr.substring(0, 2), leftGroup -1, leftStr.substring(2));
            }
            if (leftLen > 2) {
                subProcess(resp, newPref, leftStr.substring(0, 3), leftGroup -1, leftStr.substring(3));
            }
        }
    }


}
