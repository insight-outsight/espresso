package org.ootb.espresso.springcloud.infrastructure;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * IP based snowflake is able to generate up to 64 unique IDs per millis. Calling {@link IpBasedSnowflakeIdGenerator#nextId()}
 * more than 64 times per millis will generate duplicate IDs.
 */
public class IpBasedSnowflakeIdGenerator implements UniqueIdGenerator {

    private final byte[] address;
    private AtomicInteger counter;

    public IpBasedSnowflakeIdGenerator() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        address = localHost.getAddress();
        counter = new AtomicInteger();
    }

    @Override
    public long nextId() {
        return (System.currentTimeMillis() << 22)
                | ((address[2] & 0xFF) << 14)
                | ((address[3] & 0xFF) << 6)
                | (counter.getAndIncrement() & 0x03F);
    }

    @Override
    public String nextId(String prefix) {
        return prefix + nextId();
    }
}
