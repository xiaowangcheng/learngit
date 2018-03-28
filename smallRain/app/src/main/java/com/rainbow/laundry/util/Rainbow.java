package com.rainbow.laundry.util;

/**
 * Created by wyf on 2018/1/2.
 */

public class Rainbow {
    public static String[] rainbow_name = {"洗衣", "洗鞋", "洗家纺", "洗窗帘", "一袋洗"};


    public static  String toGetService(String serviceids){
        String[] str=null;
        if(serviceids.contains(",")){
            str = serviceids.split(",");
        } else {
            str = new String[1];
            str[0] = serviceids;
        }
        StringBuilder  str_project  =new StringBuilder();
        for (int i=0;i<str.length;i++){
            str_project.append(rainbow_name[Integer.parseInt(str[i])]+", ");
        }
        str_project.delete(str_project.length()-2,str_project.length());
        return str_project.toString();
    }
}
