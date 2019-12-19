package com.investment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 *
 * @author Sun
 */
public class DateUtil {
    /**
     * 日期装字符串
     *
     * @return
     */
    public static String getStringDate(String type) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static Date getDate(String type) throws ParseException {
        Date date = new Date();
        //HH表示24小时制
        SimpleDateFormat dFormat = new SimpleDateFormat(type);
        String formatDate = dFormat.format(date);
        Date parse = dFormat.parse(formatDate);
        return parse;
    }
}
