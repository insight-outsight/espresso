package org.ootb.espresso.facilities;

import org.slf4j.Logger;

import java.util.List;


public class LogUtils {


    public static void printListDebug(Logger logger,String title,List<String> list) {
        if(logger == null || list==null){
            return;
        }
        logger.debug(">>>--------------------{}--------------------------<<<",title);
        for(int i=0;i<list.size();i++){
            logger.debug("{}.{}",i,list.get(i));
        }
        logger.debug("<<<--------------------{}-------------------------->>>",title);
    }

}
