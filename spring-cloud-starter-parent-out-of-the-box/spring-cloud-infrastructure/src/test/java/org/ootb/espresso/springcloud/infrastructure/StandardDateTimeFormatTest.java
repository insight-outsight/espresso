package org.ootb.espresso.springcloud.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import org.slf4j.MDC;

public class StandardDateTimeFormatTest {

    @Test
    public void testStandardDateTimeFormat() {
        MDC.clear();
        MDC.put("auserId", "tosi");


        new StandardDateTimeFormat(new SimpleDateFormat(), "yyyy-MM-dd HH:mm:ss");
    }

}
