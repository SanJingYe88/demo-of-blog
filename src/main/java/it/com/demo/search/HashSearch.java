package it.com.demo.search;

import java.util.Arrays;

/**
 * 哈希查找
 */
public class HashSearch {
    public static void main(String[] args) {

        int[] array = {6, 9, 12, 18, 23, 27, 30, 35, 49, 58, 72, 99};  //等待 hash 的元素
        int[] hashArray = new int[array.length];    // 元素 hash 后存放在这里

        for (int i = 0; i < array.length; i++) {
            //依次 hash
            int index = insertHash(hashArray, array[i]);
            System.out.println("元素 " + array[i] + " hash 后的位置是 : " + index);
        }

        System.out.println("------ Hash 后的结果为 ------");
        System.out.println(Arrays.toString(hashArray));

        System.out.println("------ Hash 查找 ------");
        int target = 31;
        int i = search3(hashArray, target);
        System.out.println(i == -1 ? "未找到" + target : "找到了, 位置是: " + i);
    }

    /**
     * 对元素进行哈希
     *
     * @param hashArray 存放最终 hash 的结果
     * @param target    等待 hash 的元素
     * @return 元素在 hashArray 中的位置
     */
    public static int insertHash(int[] hashArray, int target) {
        int index = target % hashArray.length;

        if (hashArray[index] == 0) {        //该位置没存放元素
            hashArray[index] = target;
        } else {     //该位置有存放元素,进行冲突解决
            index = conflict(hashArray, target, index);   //采用开放地址法
        }
        return index;
    }

    /**
     * 哈希冲突解决 - 开放地址法
     *
     * @param hashArray 存放最终 hash 的结果
     * @param target    等待 hash 的元素
     * @param index     发生冲突时的 target 元素在 hashArray 的下标
     * @return 元素在 hashArray 中的位置
     */
    public static int conflict(int[] hashArray, int target, int index) {
        while (true) {
            index++;        //向后移动
            if (index >= hashArray.length - 1) {     //到达 hashArray 末尾时, 从头开始
                index = 0;
            }
            if (hashArray[index] == 0) {
                hashArray[index] = target;
                break;
            }
        }
        return index;
    }

    // 哈希查找
    public static int search3(int[] hashArray, int target) {
        int index = target % hashArray.length;
        int hashIndex = index;
        if (hashArray[index] == target) {
            return index;
        }
        while (true) {
            index++;
            if (index > hashArray.length - 1) {
                break;
            }
            if (hashArray[index] == target) {
                return index;
            }
        }
        index = 0;
        while (true) {
            if (index >= hashIndex) {
                return -1;
            }
            if (hashArray[index] == target) {
                return index;
            }
            index++;
        }
    }
}
