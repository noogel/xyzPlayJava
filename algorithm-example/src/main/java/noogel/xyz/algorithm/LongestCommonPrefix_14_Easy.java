package noogel.xyz.algorithm;

public class LongestCommonPrefix_14_Easy {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {
                "abc", "abccc", "abddd"
        }));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {
            return "";
        }
        int flag =  0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (strs[0].length() <= flag) {
                return sb.toString();
            }
            char point = strs[0].charAt(flag);
            for(int i =1; i< strs.length; i++) {
                if (strs[i].length() > flag && strs[i].charAt(flag) == point) {
                    continue;
                } else {
                    return sb.toString();
                }
            }
            sb.append(point);
            flag++;
        }
    }
}
