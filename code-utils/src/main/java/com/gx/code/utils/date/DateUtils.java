package com.gx.code.utils.date;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author miya
 */
public class DateUtils {
    public static void main(String args[]) throws Exception {
        System.out.println(
                DateUtils.getLastDayOfMonth(
                        DateUtils.transform("2020-02-27 00:00:00", null)));
    }

    public static Date getLastDayOfMonth(Date date) {
        if (null == date) {
            return null;
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);

        Date lastDay = ca.getTime();
        return lastDay;
    }

    public static void transform(long seconds, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }

        Date d = new Date(seconds);
        DateFormat format = new SimpleDateFormat(pattern);
        System.out.println(format.format(d));
    }

    public static String transform(Date date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }

        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date transform(String str, String pattern) throws ParseException {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }

        DateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(str);
        return date;
    }
}


