package it.com.demo.search;

/**
 * 顺序查找
 */
public class SequentialSearch {

    public static void main(String[] args) {
        int[] array = {23, 53, 2, 4, 85, 7, 12, 6, 5, 9};
        int target = 5;
        int i = SequentialSearch.search(array, target);
        System.out.println(i == -1 ? "未找到" + target : "找到了, 位置是: " + i);
    }

    private static int search(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
