public class MySqrt {
    public static void main(String[] args) {
        MySqrt mySqrt = new MySqrt();
        System.out.println(mySqrt.mySqrt(2147395599));

    }

    // 边界问题
    // 1. 0\1边界
    //  类型长度越界
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        return mySqrt(x, 0, x);
    }

    public int mySqrt(long x, long left, long right) {
        long cur = (right - left) / 2 + left;
        long cur2 = cur * cur;
        if (cur2 == x) {
            return (int) cur;
        } else if (right - left == 1) {
            return (int) left;
        }

        if (cur2 < x) {
            left = cur;
        } else if (cur2 > x) {
            right = cur;
        } else {
            return (int) cur;
        }
        return mySqrt(x, left, right);
    }
}
