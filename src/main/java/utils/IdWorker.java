package utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 分布式id生成工具
 * @author wangbowang
 * @date 2021/2/8 15:56
 */
public class IdWorker {

    /**
     * 开始时间截 (从2015-01-01起)
     */
    private static final long START_TIME = 1420041600000L;
    /**
     * 机器ID所占位数
     */
    private static final long ID_BITS = 5L;
    /**
     * 数据中心ID所占位数
     */
    private static final long DATA_CENTER_ID_BITS = 5L;
    /**
     * 机器ID最大值31 (此移位算法可很快计算出n位二进制数所能表示的最大十进制数)
     */
    private static final long MAX_WORKER_ID = ~(-1L << ID_BITS);
    /**
     * 数据中心ID最大值31
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    /**
     * Sequence所占位数
     */
    private static final long SEQUENCE_BITS = 12L;
    /**
     * 机器ID偏移量12
     */
    private static final long ID_SHIFT_BITS = SEQUENCE_BITS;
    /**
     * 数据中心ID偏移量12+5=17
     */
    private static final long DATA_CENTER_ID_SHIFT_BITS = SEQUENCE_BITS + ID_BITS;
    /**
     * 时间戳的偏移量12+5+5=22
     */
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = SEQUENCE_BITS + ID_BITS + DATA_CENTER_ID_BITS;
    /**
     * Sequence掩码4095
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    /**
     * 上一毫秒数
     */
    private static long lastTimestamp = -1L;
    /**
     * 毫秒内Sequence(0~4095)
     */
    private static long sequence = 0L;
    /**
     * 机器ID(0-31)
     */
    private long workerId;
    /**
     * 数据中心ID(0-31)
     */
    private long dataCenterId;

    public IdWorker() {
        this.dataCenterId = getDatacenterId(MAX_DATA_CENTER_ID);
        this.workerId = getWorkerId(dataCenterId,MAX_WORKER_ID);
    }


    /**
     * 生成ID（线程安全）
     *
     * @return id
     */
    public synchronized Long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常！！！
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException(
                    String.format(
                            "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内sequence生成
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 溢出处理
            if (sequence == 0) {
                // 阻塞到下一毫秒,获得新时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else { // 时间戳改变，毫秒内sequence重置
            sequence = 0L;
        }
        // 上次生成ID时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算组成64位ID
        return ((timestamp - START_TIME) << TIMESTAMP_LEFT_SHIFT_BITS)
                | (dataCenterId << DATA_CENTER_ID_SHIFT_BITS)
                | (workerId << ID_SHIFT_BITS)
                | sequence;
    }

    /**
     * 阻塞到下一毫秒,获得新时间戳
     *
     * @param lastTimestamp 上次生成ID时间截
     * @return 当前时间戳
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取 workerId
     * @param datacenterId 数据标识id
     * @param maxWorkerId 最大机器id
     * @return
     */
    protected static long getWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            // get jvmPid
            mpid.append(name.split("@")[0]);
        }
        //MAC + PID 的 hashcode 获取16个低位
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * 数据标识id部分
     * @param maxDatacenterId 支持的最大数据标识
     * @return 数据标识id
     */
    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (Exception e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }

}