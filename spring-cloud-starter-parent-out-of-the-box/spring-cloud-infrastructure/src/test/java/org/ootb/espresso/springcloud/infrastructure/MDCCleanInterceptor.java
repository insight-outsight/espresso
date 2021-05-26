package org.ootb.espresso.springcloud.infrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class MDCCleanInterceptor extends HandlerInterceptorAdapter {
    
    private Logger logger = LoggerFactory.getLogger(MDCCleanInterceptor.class);
    
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
        MDC.clear();
        logger.info("preHandle :" +request.getContextPath());

    }
}