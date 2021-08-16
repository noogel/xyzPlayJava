package noogel.xyz.algorithm;

public class FindSqrt {
    public static void main(String[] args) {
        System.out.println(fn(4.1f, 0.001f));
    }

    public static float fn(float n, float e) {
        int len = String.valueOf(e).length() - 2;
        if (n<0 || e<0) {
            return 0;
        }
        float min = 0;
        float max = n;
        float x = 0;
        while (min < max) {
            float mid = Float.parseFloat(String.format("%." + len + "f", (max + min) / 2));
            if (mid * mid < n - e) {
                min = mid;
            } else if (mid * mid > n+e) {
                max = mid;
            } else {
                return mid;
            }
        }
        return x;
    }
}
