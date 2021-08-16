package noogel.xyz.algorithm;

public class FindDuplicated {
    public static void main(String[] args) {
        findDuplicated(new int[]{1, 2, 3, 4, 5, 6, 3, 1});
    }

    public static void findDuplicated(int[] a) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int item : a) {
            min = Math.min(min, item);
            max = Math.max(max, item);
        }

        int length = max / 8 - min / 8 + 1;
        byte[] container = new byte[length];
        for (int item : a) {
            int virSize = item - min;
            int idx = virSize / 8;
            int po = virSize % 8;
            if ((container[idx] & 1 << po) > 0) {
                System.out.println(item);
            } else {
                container[idx] |= 1 << po;
            }
        }
    }
}
