package it.com.demo.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/***
 * Id 生成器 (雪花算法)
 */
public class IdWorker2 {
    /**
     * 开始时间截
     */
    private final long twepoch = 1585712670235L;
    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 3L;
    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;
    /**
     * 支持的最大机器id, 结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 支持的最大数据标识id, 结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 8L;
    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     * 生成序列的掩码, 这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    // 具体实现不变

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(1, 1);
        int size = 100000;
        Set<String> set = new HashSet<>(size);
        for (int i = 1; i < size; i++) {
            String id = String.valueOf(idWorker.nextId());
            id = id.substring(id.length() - 8, id.length());
            if (set.contains(id)) {
                System.out.println("重复的id=" + id);
            } else {
                set.add(id);
            }
        }
        //统计
        Iterator iterator = set.iterator();
        if (iterator.hasNext()) {
            System.out.println("当前长度:" + String.valueOf(iterator.next()).length());
        }
        int count = 50;
        while (iterator.hasNext() && count > 0) {
            System.out.println(String.valueOf(iterator.next()));
            count--;
        }
    }
}
