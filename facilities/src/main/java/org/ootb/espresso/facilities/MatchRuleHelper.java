package org.ootb.espresso.facilities;

import com.google.common.base.Splitter;
import java.util.List;
import java.util.Objects;

public class MatchRuleHelper {
    
    private MatchRuleHelper() {
        throw new UnsupportedOperationException("can not be created");
    }

    private static final char COMMA_CHAR = ',';
    public static final String ALL_VALUE = "0";
    
    public static boolean isUserIdMod10SuffixMatch(long userId, String userIdSuffixCommaSeparatedStr) {
        return isUserIdModXSuffixMatch(userId, 10, userIdSuffixCommaSeparatedStr);
    }
    
    private static boolean isUserIdModXSuffixMatch(long userId, long mod, String userIdSuffixCommaSeparatedStr) {
        if (Objects.isNull(userIdSuffixCommaSeparatedStr)) {
            return false;
        }
        long remainder = userId % mod;
        List<String> userIdSuffixList = Splitter.on(COMMA_CHAR).trimResults().splitToList(userIdSuffixCommaSeparatedStr);
        for (String userIdSuffix : userIdSuffixList) {
            if (userIdSuffix.equals(String.valueOf(remainder))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContaining(int value, String commaSeparatedStr) {
        if (Objects.isNull(commaSeparatedStr)) {
            return false;
        }
        List<String> list = Splitter.on(COMMA_CHAR).trimResults().splitToList(commaSeparatedStr);
        for (String item : list) {
            if (item.equals(String.valueOf(value))) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isContainingCompatible(int value, String commaSeparatedStr) {
        if (isContaining(value, commaSeparatedStr)) {
            return true;
        }
        List<String> list = Splitter.on(COMMA_CHAR).trimResults().splitToList(commaSeparatedStr);
        return list.contains(ALL_VALUE);
    }

    public static boolean isPlatformMatch(int platformType, Integer platform) {
        return platformType == platform.intValue();
    }

    public static boolean isContaining(String value, String commaSeparatedStr) {
        if (Objects.isNull(commaSeparatedStr)) {
            return false;
        }
        List<String> list = Splitter.on(COMMA_CHAR).trimResults().splitToList(commaSeparatedStr);
        for (String item : list) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

}
