package snow_flake;

/**
 * 分布式自增ID雪花算法 snowflake
 */
public class SnowFlake {

    /**
     * 起始时间戳
     */
    private final static long START_STAMP = 1314855724000L;

    /**
     * 每个部分占用的位数
     */
    private final static long DATACENTER_BIT = 5;
    private final static long MACHINE_BIT = 5;
    private final static long SEQUENCE_BIT = 12;

    /**
     * 每个部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;
    private long machineId;
    private long sequence = 0L;
    private long lastStamp = -1L; //上一次时间戳

    /**
     * 构造方法
     *
     * @param datacenterId
     * @param machineId
     */
    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId 不能大于最大值或者小于0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId 不能大于最大值或者小于0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 生产下一个ID
     */
    public synchronized long nextId() {
        long currentStamp = getNewStamp();
        if (currentStamp < lastStamp) {
            throw new RuntimeException("量子领域时间倒流，拒绝生产ID");
        }

        if (currentStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //如果同一毫秒的序列号已经达到最大
            if (sequence == 0) {
                currentStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0;
        }

        lastStamp = currentStamp;

        return (currentStamp - START_STAMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT   //数据中心部分
                | machineId << MACHINE_LEFT         //机器识别部分
                | sequence;                         //序列号部分
    }

    /**
     * 得到比上一次时间戳 大的当前时间戳
     */
    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    /**
     * 得到当前时间戳
     *
     * @return 当前系统时间戳
     */
    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(2, 3);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            System.out.println(snowFlake.nextId());
        }

        System.out.println(System.currentTimeMillis() - start);
    }
}
