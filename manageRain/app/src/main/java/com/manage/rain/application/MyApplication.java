package com.manage.rain.application;

import android.app.Application;

/**
 * Created by wyf on 2018/1/6.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication =null;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

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
