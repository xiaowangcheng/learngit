package com.rainbow.laundry.ui;

import android.support.v7.app.AppCompatActivity;

import com.rainbow.laundry.application.ActivityManager;

/**
 * Created by wyf on 2018/1/2.
 */

public class BaseNormalActivity extends AppCompatActivity {


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_MODERATE) {
            ActivityManager.exit(this);
        }
    }
}
