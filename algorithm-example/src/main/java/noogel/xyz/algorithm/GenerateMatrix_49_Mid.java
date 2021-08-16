package noogel.xyz.algorithm;

public class GenerateMatrix_49_Mid {
    public static void main(String[] args) {
        int[][] ints = generateMatrix(3);
        System.out.println(ints);
    }

    public static int[][] generateMatrix(int n) {
        int start = 1;
        int end = n * n;
        int[][] store = new int[n][n];
        int l = 0;
        int r = n -1;
        int t = 0;
        int b = n - 1;
        while (start <= end) {
            for(int i = l; i<=r; i++) {
                store[t][i] = start;
                start++;
            }
            t++;
            for(int i = t; i<=b; i++) {
                store[i][r] = start;
                start++;
            }
            r--;
            for(int i = r; i>=l; i--) {
                store[b][i] = start;
                start++;
            }
            b--;
            for(int i = b; i>=t; i--) {
                store[i][l] = start;
                start++;
            }
            l++;
        }
        return store;
    }
}
