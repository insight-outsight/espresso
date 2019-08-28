package org.ootb.espresso.facilities;

import org.apache.commons.lang3.StringUtils;
import org.ootb.espresso.facilities.constant.CharsetConstants;

public class StringUtilsExt {
    
    public static String toUTF8String(byte[] bytes){
        return (bytes != null) ? StringUtils.toEncodedString(bytes, CharsetConstants.UTF_8) : null;
    }
    
}
