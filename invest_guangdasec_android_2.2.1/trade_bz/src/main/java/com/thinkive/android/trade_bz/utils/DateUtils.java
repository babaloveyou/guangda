package com.thinkive.android.trade_bz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：时间帮助类
 *
 * @author xuhao
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015-02-21
 */
public class DateUtils {
    private static SimpleDateFormat format = new SimpleDateFormat();

    public static String getDay(Date date){
        format.applyPattern("dd");
        return format.format(date);
    }

    public static String getMouth(Date date){
        format.applyPattern("MM");
        return format.format(date);
    }

    public static String getYear(Date date){
        format.applyPattern("yyyy");
        return format.format(date);
    }

    public static String getDay(){
        return getDay(new Date());
    }

    public static String getMouth(){
        return getMouth(new Date());
    }

    public static String getYear(){
        return getYear(new Date());
    }

    public static String getDateString(Date date, String pattern){
        format.applyPattern(pattern);
        return format.format(date);
    }

    public static String getDateString(String pattern){
        return getDateString(new Date(),pattern);
    }

    public static Date toDate(long l){
        return new Date(l);
    }
}
