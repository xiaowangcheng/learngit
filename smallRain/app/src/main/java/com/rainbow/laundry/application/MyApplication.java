package com.rainbow.laundry.application;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wyf on 2018/1/2.
 */

public class MyApplication  extends Application {
    private static MyApplication myApplication =null;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    public static  MyApplication getContext(){
        if(myApplication ==null){
            myApplication =new MyApplication();
        }
        return myApplication;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
