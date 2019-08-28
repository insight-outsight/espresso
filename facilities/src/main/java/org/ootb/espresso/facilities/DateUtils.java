package org.ootb.espresso.facilities;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtils {

    private static final FastDateFormat standardDateFormat = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat compactStyleDateFormat = FastDateFormat.getInstance("yyyyMMdd");
    private static final FastDateFormat standardTimeFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {

        System.out.println(getStandardDateFormat().format(new Date()));
        System.out.println(formatToStandardDateString(System
                .currentTimeMillis()));
        System.out.println(formatToCompactStyleDateString(System
                .currentTimeMillis()));
        System.out.println(getStandardTimeFormat().format(new Date()));
        System.out.println(formatToStandardTimeString(new Date()));
        System.out.println(formatToStandardTimeString(System
                .currentTimeMillis()));
        
        System.out.println(formatToStandardTimeString(parseStandardTimeString("2018-05-10 09:25:00")));
        System.out.println(currentDateNumberFormat());
        System.out.println(formatToStandardTimeString(parseDateNumberFormat(20191210)));
        System.out.println(isTwoDaysContinuous(20180131,20180201));
        System.out.println(formatToStandardTimeStringNow());
        System.out.println(formatToStandardDateStringNow());
        System.out.println(System.currentTimeMillis());
        System.out.println(remainSecondsToday());
        System.out.println(yesterday());
        System.out.println(formatToStandardDateString(yesterday()));
        System.out.println(tomorrow());
        System.out.println(formatToStandardDateString(tomorrow()));

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
    
}
