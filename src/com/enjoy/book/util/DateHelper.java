package com.enjoy.book.util;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateHelper {
    //获取上传图片名
    public static String getImageName(){
        SimpleDateFormat  sdf= new SimpleDateFormat("yyyyMMddHHmmssS");
        return sdf.format(new Date());
    }
    //对date日期进行加减操作
    public static java.sql.Date getNewDate(java.sql.Date date, long amount){
        long mills = date.getTime();
        mills += amount*24*60*60*1000;
        java.sql.Date backDate = new java.sql.Date(mills);
        return backDate;
    }
    //计算两个时间差
    public static int getSpan(Date  date01,Date date02){
        long span = date01.getTime()-date02.getTime();
        int day = (int)(span/1000/60/60/24);
        return Math.abs(day);

    }
}
