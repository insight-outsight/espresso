package org.ootb.espresso.facilities;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
          System.out.println(sdf.format(sdf.parse("2017-13-01 99:01:05")));//正常输出 2018-01-05 03:01:05
          sdf.setLenient(false);
          System.out.println(sdf.parse("2017-12-01 9:01:05"));//正常输出 Fri Dec 01 09:01:05 CST 2017
          System.out.println(sdf.parse("2017-13-01 99:01:05"));//java.text.ParseException: Unparseable date: "2017-13-01 99:01:05"
      } catch (ParseException e) {
          e.printStackTrace();
      }
      SimpleDateFormat不是线程安全，FastDateFormat是线程安全，但不支持设置setLenient()模式，joda-time类库的
      org.joda.time.format.DateTimeFormat支持Lenient模式，用于校验字符串日期格式。
      
 * @author xuzhengchao
 *
 */
public class DateUtils {

    private static Logger LOG = LoggerFactory.getLogger(DateUtils.class);
    private static final String standardDateFormatStr = "yyyy-MM-dd";
    private static final FastDateFormat standardDateFormat = FastDateFormat.getInstance(standardDateFormatStr);
    private static final FastDateFormat compactStyleDateFormat = FastDateFormat.getInstance("yyyyMMdd");
    private static final String standardTimeFormatStr = "yyyy-MM-dd HH:mm:ss";
    private static final FastDateFormat standardTimeFormat = FastDateFormat.getInstance(standardTimeFormatStr);

    public static void main(String[] args) throws Exception {

//        System.out.println(getStandardDateFormat().format(new Date()));
//        System.out.println(formatToStandardDateString(System
//                .currentTimeMillis()));
//        System.out.println(formatToCompactStyleDateString(System
//                .currentTimeMillis()));
//        System.out.println(getStandardTimeFormat().format(new Date()));
//        System.out.println(formatToStandardTimeString(new Date()));
//        System.out.println(formatToStandardTimeString(System
//                .currentTimeMillis()));
//        
//        System.out.println(formatToStandardTimeString(parseStandardTimeString("2018-05-10 09:25:00")));
//        System.out.println(currentDateNumberFormat());
//        System.out.println(formatToStandardTimeString(parseDateNumberFormat(20191210)));
//        System.out.println(isTwoDaysContinuous(20180131,20180201));
//        System.out.println(formatToStandardTimeStringNow());
//        System.out.println(formatToStandardDateStringNow());
//        System.out.println(System.currentTimeMillis());
//        System.out.println(remainSecondsToday());
//        System.out.println(yesterday());
//        System.out.println(formatToStandardDateString(yesterday()));
//        System.out.println(tomorrow());
//        System.out.println(formatToStandardDateString(tomorrow()));
//      System.out.println(isStandardDateStr(null));
//      System.out.println(isStandardDateStr(""));
//      System.out.println(isStandardDateStr("2019-05-24"));
//      System.out.println(isStandardDateStr("2020-02-29"));
//      System.out.println(isStandardDateStr("2019-5-24"));
//      System.out.println(isStandardDateStr("2019-05-24 12"));
//      System.out.println(isStandardDateStr("2019-05-24 12-25-25"));
//      System.out.println(isStandardDateStr("2019-05-24 12:25:25"));
//      System.out.println(isStandardDateStr("2019-05-24 12:03:02"));
        long st = System.currentTimeMillis();
        System.out.println(isStandardTimeStr(null));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr(""));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-24"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2020-02-29"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-5-24"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-24 12"));
      System.out.println(System.currentTimeMillis()-st);//cost 63ms，因为
      //DateTimeFormatter.parseDateTime() Line 945         
      //throw new IllegalArgumentException(FormatUtils.createErrorMessage(text, newPos));
      //FormatUtils类首次使用被加载耗时较长？应该是的
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-24 12:89:50"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-24 12-26-25"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-24 12:25:25"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-24 12:75:25"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-32 12:25:25"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-15-24 12:03:02"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-24 101:03:02"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-5-24 22:03:02"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-05-4 11:03:02"));
      System.out.println(System.currentTimeMillis()-st);
      st = System.currentTimeMillis();
      System.out.println(isStandardTimeStr("2019-09-26 17:53:00"));
      System.out.println(System.currentTimeMillis()-st);
    }

    public static FastDateFormat getStandardDateFormat() {
        return standardDateFormat;
    }

    public static FastDateFormat getStandardTimeFormat() {
        return standardTimeFormat;
    }

    public static String formatToStandardDateString(long ts) {
        return standardDateFormat.format(ts);
    }

    public static String formatToCompactStyleDateString(long ts) {
        return compactStyleDateFormat.format(ts);
    }

    public static String formatToStandardTimeString(Date date) {
        return standardTimeFormat.format(date);
    }

    public static String formatToStandardTimeString(long ts) {
        return standardTimeFormat.format(ts);
    }
    
    public static String formatToStandardTimeStringNow() {
        return standardTimeFormat.format(System.currentTimeMillis());
    }
    
    public static String formatToStandardDateStringNow() {
        return standardDateFormat.format(System.currentTimeMillis());
    }
    
    public static String formatToStandardDateString(Date date) {
        return standardDateFormat.format(date);
    }
    
    public static Date parseStandardTimeString(String timeStr) throws ParseException {
        return standardTimeFormat.parse(timeStr);
    }
    
    public static int currentDateNumberFormat() throws NumberFormatException {
        String currentDateStr = formatToCompactStyleDateString(System.currentTimeMillis());
        return Integer.valueOf(currentDateStr);
    }
    
    public static Date parseDateNumberFormat(int dateNumber) throws Exception {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = LocalDate.parse(dateNumber+"", DateTimeFormatter.BASIC_ISO_DATE).atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
    
    public static boolean isTwoDaysContinuous(int firtDateNumber,int nextDateNumber) {
        LocalDate firtDateLocalDate = LocalDate.parse(firtDateNumber+"", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate nextDateLocalDate = LocalDate.parse(nextDateNumber+"", DateTimeFormatter.BASIC_ISO_DATE);
        return firtDateLocalDate.plusDays(1).equals(nextDateLocalDate);
    }
    
    public static long remainSecondsToday() throws Exception{
        long tomorrowZeroMilliSeconds = tomorrow().getTime();
        long nowMillSeconds = System.currentTimeMillis();
        long remainSecondsToday = tomorrowZeroMilliSeconds - nowMillSeconds;
        if(remainSecondsToday < 0 || remainSecondsToday/1000 > 86400){
            throw new RuntimeException("remainSecondsToday "+remainSecondsToday+" illegal");
        }
        return remainSecondsToday/1000;
    }
    
    public static Date tomorrow() throws Exception {
        LocalDate localDate = LocalDate.now();
        return standardDateFormat.parse(localDate.plusDays(1).toString());
    }
    
    public static Date yesterday() throws Exception {
        LocalDate localDate = LocalDate.now();
        return standardDateFormat.parse(localDate.plusDays(-1).toString());
    }
    
    public static boolean isStandardDateStr(String str) {
        if(StringUtils.isBlank(str)) {
            return false;
        }
        if(str.length() != standardDateFormatStr.length()) {
            return false;
        }
        org.joda.time.format.DateTimeFormatter format = DateTimeFormat.forPattern(standardDateFormatStr); 
        try {
            format.parseDateTime(str);
        } catch (Exception e) {
            LOG.error("parseDate Err",e);
            return false;
        }
        return true;
    }
    
    public static boolean isStandardTimeStr(String str) {
        if(StringUtils.isBlank(str)) {
            return false;
        }
        if(str.length() != standardTimeFormatStr.length()) {
            return false;
        }
        org.joda.time.format.DateTimeFormatter format = DateTimeFormat.forPattern(standardTimeFormatStr); 
        try {
//            format.parseDateTime(str);
//            DateTime d =
            DateTime.parse(str,format);
//            System.out.println(d.toLocalDateTime());
        } catch (Exception e) {
//            e.printStackTrace();
            LOG.error("parseTime Err",e);
            return false;
        }
        return true;
    }
    
}
