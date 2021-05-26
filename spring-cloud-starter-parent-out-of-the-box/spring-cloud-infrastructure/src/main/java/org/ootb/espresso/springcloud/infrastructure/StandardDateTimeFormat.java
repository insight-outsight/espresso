package org.ootb.espresso.springcloud.infrastructure;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StandardDateTimeFormat extends DateFormat {

    /**
     * 
     */
    private static final long serialVersionUID = -399717153036522048L;

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String pattern;
    private final DateFormat dateFormat;
    // ?? SimpleDateFormat is not thread-safe
    private final SimpleDateFormat dateTimeFormatter;

    StandardDateTimeFormat(DateFormat dateFormat, String pattern) {
        this.dateFormat = dateFormat;
        this.dateTimeFormatter = new SimpleDateFormat(pattern);
        this.pattern = pattern;
        logger.info("enabled dateFormatter pattern '{}' for serialization/deserialization", pattern);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return this.dateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        try {
            return this.dateFormat.parse(source, pos);
        } catch (Exception e) {
            return dateTimeFormatter.parse(source, pos);
        }
    }

    @Override
    public Date parse(String source) throws ParseException {
        try {
            return this.dateFormat.parse(source);
        } catch (Exception e) {
            return dateTimeFormatter.parse(source);
        }
    }

    @Override
    public Object clone() {
        return new StandardDateTimeFormat((DateFormat) dateFormat.clone(), pattern);
    }
}
