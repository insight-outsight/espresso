package org.ootb.espresso.springcloud.infrastructure;

public interface UniqueIdGenerator {
    long nextId();

    String nextId(String prefix);
}
