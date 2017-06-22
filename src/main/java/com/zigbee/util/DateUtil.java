package com.zigbee.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/13.
 */
public class DateUtil {
    public static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";

    public static Date stringToDate(String dateStr,String formatStr){
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormat.parseDateTime(dateStr);
        return dateTime.toDate();
    }
    public static Date stringToDate(String dateStr){
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormat.parseDateTime(dateStr);
        return dateTime.toDate();
    }
    public static String dateToString(Date date,String formatStr){
        if (date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString();
    }
    public static String dateToString(Date date){
        if (date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

}
