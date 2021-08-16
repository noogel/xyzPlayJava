package noogel.xyz.algorithm;

public class Multiply_43_Mid {
    public static void main(String[] args) {
        System.out.println(multiply("151234342342342345", "15"));
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n1 = num1.length();
        int n2 = num2.length();
        int[] container = new int[n1 + n2];
        for(int i = n1 -1; i >= 0; i--) {
            int n1v = num1.charAt(i) - '0';
            for(int j = n2 - 1; j >= 0; j--) {
                int n2v = num2.charAt(j) - '0';
                container[i+1+j+1-1] += n1v * n2v;
                container[i+1+j+1-2] += container[i+1+j+1-1] / 10;
                container[i+1+j+1-1] = container[i+1+j+1-1] % 10;
            }
        }
        int index = container[0] == 0 ? 1: 0;
        StringBuffer sb = new StringBuffer();
        for(int k =index; k<n1+n2; k++){
            sb.append(container[k]);
        }
        return sb.toString();
    }
}
