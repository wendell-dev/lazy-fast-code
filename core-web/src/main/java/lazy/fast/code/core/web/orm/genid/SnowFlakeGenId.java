package lazy.fast.code.core.web.orm.genid;

import lazy.fast.code.core.web.exception.SystemException;
import tk.mybatis.mapper.genid.GenId;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 基于snowflake算法改造实现GenId
 *
 * @author wendell
 */
public class SnowFlakeGenId implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        return String.valueOf(worker.nextId());
    }

    /**
     * 根据具体机器环境提供
     */
    private final long workerId;
    /**
     * 滤波器,使时间变小,生成的总位数变小,一旦确定不能变动
     */
    private static final long TWEPOCH = 1361753741828L;
    private long sequence = 0L;
    private static final long WORKER_ID_BIS = 10L;
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BIS);
    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_LEF_SHIFT = SEQUENCE_BITS + WORKER_ID_BIS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private long lastTimestamp = -1L;

    /**
     * 根据主机id获取机器码
     */
    private static SnowFlakeGenId worker = new SnowFlakeGenId();

    /**
     * 不主动实例化此类, 本应设置private，但是因为GenId限制不能设置作用域
     * 
     * @see tk.mybatis.mapper.genid.GenIdUtil#genId ==> CACHE.put(genClass, genClass.newInstance());
     */
    public SnowFlakeGenId() {
        this.workerId = getAddress() % (SnowFlakeGenId.MAX_WORKER_ID + 1);
    }

    private synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & SnowFlakeGenId.SEQUENCE_MASK;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            throw new SystemException(String.format(
                "Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        }

        this.lastTimestamp = timestamp;
        return (timestamp - TWEPOCH << TIMESTAMP_LEF_SHIFT) | (this.workerId << SnowFlakeGenId.WORKER_ID_SHIFT)
            | (this.sequence);
    }

    private long tilNextMillis(final long lastTimestamp1) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp1) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private static long getAddress() {
        String currentIpAddress;
        try {
            currentIpAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return 2L;
        }
        String[] str = currentIpAddress.split("\\.");
        StringBuilder hardware = new StringBuilder();
        for (String s : str) {
            hardware.append(s);
        }
        return Long.parseLong(hardware.toString());
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
