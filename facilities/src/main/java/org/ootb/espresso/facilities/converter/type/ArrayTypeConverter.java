package org.ootb.espresso.facilities.converter.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

public class ArrayTypeConverter {

    public static void main(String[] args) {
//        String[] testStrArrays = {"12","3235325","12A","-7"};
        String[] testStrArrays = {"12","3235325","-7"};
        Long[] result = toLongArrayWithClassicAndRedundantStyle(testStrArrays,true,null); 
        System.out.println("size="+result.length);
        for(Long l :result) {
            System.out.println(l);
        }
        System.out.println("--------------");
        long[] result2 = toLongArrayWithStreamStyle(testStrArrays); 
        System.out.println("size="+result2.length);
        for(long l :result2) {
            System.out.println(l);
        }
        System.out.println("--------------");
        Long[] result3 = toLongObjectArrayWithStreamStyle("998,222"); 
        System.out.println("size="+result3.length);
        for(long l :result3) {
            System.out.println(l);
        }
        System.out.println("--------------");
        long[] result4 = toLongArrayWithStreamPatternStyle("1,75,-2242342,3"); 
        System.out.println("size="+result4.length);
        for(long l :result4) {
            System.out.println(l);
        }
        ArrayList<Long> newArrayList = Lists.newArrayList(result3);
        System.out.println("----------e----");
        System.out.println("size="+newArrayList.size());
        for(Long l2 :newArrayList) {
            System.out.println(l2);
        }
    }
    
    /**
     * 
     * @param strArrays
     * @param ignoreParseException 是否忽略转型异常
     * @param defaultValue 当忽略转型异常时，出现转换异常时的默认值
     * @return
     */
    public static Long[] toLongArrayWithClassicAndRedundantStyle(String[] strArrays, 
            boolean ignoreParseException,Long defaultValue) {

        Long[] result = new Long[strArrays.length]; 
        for (int i = 0; i < result.length; i++) { 
            try {
                result[i] = Long.parseLong(strArrays[i]);
            } catch (NumberFormatException e) {
                if(ignoreParseException) {
                    result[i] = defaultValue;
                }
                else {
                    throw e;
                }
            } 
        } 
        return result;
        
    }
    
    public static long[] toLongArrayWithStreamStyle(String[] arr) { 
        return Stream.of(arr).mapToLong(Long::parseLong).toArray(); 
    }
    
    public static long[] toLongArrayWithStreamStyle(String commaSeparatedArrStr) { 
        return Arrays.stream(commaSeparatedArrStr.split(",")).mapToLong(Long::parseLong).toArray(); 
    }
    
    public static List<Long> toLongListWithStreamStyle(String commaSeparatedArrStr) { 
        return Arrays.stream(commaSeparatedArrStr.split(",")).map(item -> Long.parseLong(item)).collect(Collectors.toList());
    }
    
    public static Long[] toLongObjectArrayWithStreamStyle(String commaSeparatedArrStr) { 
        List<Long> longList = toLongListWithStreamStyle(commaSeparatedArrStr);
        Long[] longArray = new Long[longList.size()];
        return longList.toArray(longArray);
    }
    
    private static Pattern pattern = Pattern.compile(",");
    
    public static long[] toLongArrayWithStreamPatternStyle(String commaSeparatedArrStr) { 
        return pattern.splitAsStream(commaSeparatedArrStr).mapToLong(Long::parseLong).toArray(); 
    }
    
}
