package noogel.xyz.algorithm;

public class QuickSort {

    /**
     * https://www.cnblogs.com/nicaicai/p/12689403.html
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{39, 28, 55, 87, 66, 3, 17, 39};
        quickSort2(nums, 0, nums.length - 1);
        System.out.println(1);
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int low = left;
            int high = right;
            int tmp = nums[left];
            while (low < high) {
                while (tmp <= nums[high] && low < high) {
                    high--;
                }
                nums[low] = nums[high];
                while (tmp > nums[low] && low < high) {
                    low++;
                }
                nums[high] = nums[low];
            }
            nums[low] = tmp;
            int pivot = low;
            quickSort(nums, left, pivot - 1);
            quickSort(nums, pivot + 1, right);
        }
    }

    public static void quickSort2(int[] nums, int left, int right) {
        if (left < right) {
            int low = left;
            int high = right;
            int tmp = nums[left];
            while (low < high) {
                while (low< high && tmp <= nums[high]) {
                    high--;
                }
                nums[low] = nums[high];
                while (low< high && tmp > nums[low]) {
                    low++;
                }
                nums[high] = nums[low];
            }
            nums[low] = tmp;
            quickSort(nums, left, low-1);
            quickSort(nums, low+1, right);
        }
    }
}
