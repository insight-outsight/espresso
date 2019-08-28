package org.ootb.espresso.facilities;


public class ExceptionUtils {

    public static String getStackTrace(final Throwable throwable,int stackTraceLine) {
        
        String[] sf = org.apache.commons.lang3.exception.ExceptionUtils.getStackFrames(throwable);
        StringBuilder sb = new StringBuilder();
        int maxExceptionDepth = sf.length>stackTraceLine?stackTraceLine:sf.length;
        for(int j=0;j<maxExceptionDepth;j++){
            sb.append(sf[j]).append("\n");
        }
        return sb.toString();
    }

    public static String getStackTrace(final Throwable throwable) {
        return  org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(throwable);
    }
    
}
