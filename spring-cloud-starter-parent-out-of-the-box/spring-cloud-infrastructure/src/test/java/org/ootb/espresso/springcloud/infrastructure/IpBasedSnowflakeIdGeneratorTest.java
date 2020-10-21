package org.ootb.espresso.springcloud.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class IpBasedSnowflakeIdGeneratorTest {

    private final Set<Long> ids = new HashSet<>();

    private IpBasedSnowflakeIdGenerator generator;

    @BeforeEach
    public void setUp() throws Exception {
        generator = new IpBasedSnowflakeIdGenerator();
    }

    @Test
    void noDuplicateIds() {
        // this implementation can only generate up to 64 unique IDs per millis
        int count = 64;

        for (int i = 0; i < count; i++) {
            ids.add(generator.nextId());
            System.out.println(generator.nextId());
        }

        assertThat(ids).hasSize(count);
    }

    @Test
    void idWithPrefix() {
        String prefix = "h1h2h3";

        String id = generator.nextId(prefix);

        assertThat(id).startsWith(prefix);
    }
    
}
