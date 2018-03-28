package com.rainbow.laundry.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangcheng on 2018/2/4.
 */

public class DateUtil1 {

    public static String stampToDate( ){
        Long timestamp = Long.valueOf(Long.parseLong("1517836585000"));
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(timestamp.longValue()));
        return date;
    }

    public static void main(String[] args) {

        String dat  =stampToDate();

        System.out.println(dat);  //运行输出:1470278082
    }
}
