package org.ootb.espresso.facilities.constant;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharsetConstants {

    private static final Logger LOG = LoggerFactory.getLogger(CharsetConstants.class);
    
    public static final Charset UTF_8;
    
    static {
        try {
            UTF_8 = Charset.forName("UTF-8");
        } catch (Exception e) {
            LOG.error("Err when lookup charset 'UTF-8'", e);
            throw e;
        }
    }
    
    
    public static void main(String[] args) {
        System.out.println(UTF_8.getClass());
    }
    
}
