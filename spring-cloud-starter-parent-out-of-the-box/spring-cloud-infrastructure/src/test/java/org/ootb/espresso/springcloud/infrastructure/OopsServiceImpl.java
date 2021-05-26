package org.ootb.espresso.springcloud.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OopsServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(OopsServiceImpl.class);
    
    public void say(String msg) {
        logger.info("said {}", msg);
    }

}
