package com.dev.devblog.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtil {


    //LocalDateTime 을 String 으로 변환
    public static String getNowDate(String dateFormat){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat));
    }

    //String 을 LocalDateTime으로 변환
    public static LocalDateTime setStringToDate(String dateStr){
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyyMMdd HHmmss"));

    }


}