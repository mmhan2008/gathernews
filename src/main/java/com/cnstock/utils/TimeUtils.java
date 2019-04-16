package com.cnstock.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Created by Administrator on 2019/1/23.
 */
public class TimeUtils {
    public static String getTime(Date now){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(now);
    }

    public static String getDate(Date now){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(now);
    }

    public static Date timestampToDate(Date currentTime){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        return formatter.parse(dateString, pos);

    }

    public static String timeFormat(String time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat1.format(date);
    }

    public static String endTimeFormat(String time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        Date resultDate = null;
        try {
            date = simpleDateFormat.parse(time);
            int dayMis=1000*60*60*24;
            long dateTime = date.getTime();
            long lastTime = dateTime+(dayMis-1);
            resultDate=new Date(lastTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat1.format(resultDate);
    }


    public static void main(String args[]) {
        String str = "2019-04-03";
        System.out.println(endTimeFormat(str));
    }
}
