package it.com.demo.search;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9, 11};
        int key = 4;
        int position = recursionBinarySearch(arr, key, 0, arr.length - 1);

        if (position == -1) {
            System.out.println("查找的是" + key + ",序列中没有该数！");
        } else {
            System.out.println("查找的是" + key + ",找到位置为：" + position);
        }
    }

    // 递归实现
    public static int recursionBinarySearch(int[] array, int key, int low, int high) {

        if (key < array[low] || key > array[high] || low > high) {
            return -1;
        }
        // 初始中间位置
        int middle = low + (high - low) / 2;
        if (array[middle] > key) {
            // 比关键字大则关键字在左区域
            return recursionBinarySearch(array, key, low, middle - 1);
        } else if (array[middle] < key) {
            // 比关键字小则关键字在右区域
            return recursionBinarySearch(array, key, middle + 1, high);
        } else {
            return middle;
        }
    }

    // while实现
    public static int commonBinarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        int middle = 0;      // 定义middle

        if (key < array[low] || key > array[high] || low > high) {
            return -1;
        }

        while (low <= high) {
            middle = low + (high - low) / 2;
            if (array[middle] > key) {
                // 比关键字大则关键字在左区域
                high = middle - 1;
            } else if (array[middle] < key) {
                // 比关键字小则关键字在右区域
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1; // 最后仍然没有找到，则返回-1
    }
}
