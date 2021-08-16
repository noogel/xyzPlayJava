package noogel.xyz.algorithm;

public class SortedMergeLcci_Easy {
    public static void main(String[] args) {
        merge(new int[] {1,2,3,0,0,0}, 3, new int[] {2,5,6}, 3);
        System.out.println(1);
    }

    public static void merge(int[] A, int m, int[] B, int n) {
        int al = 0;
        int ar = m;
        for(int i =0; i< n; i++) {
            while (A[al] < B[i] && al < ar) {
                al++;
            }
            // 从 al 后移 一位
            for(int aEnd = ar; aEnd > al; aEnd--) {
                A[aEnd] = A[aEnd-1];
            }
            A[al] = B[i];
            al++;
            ar++;
        }
    }
}
