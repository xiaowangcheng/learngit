package com.rainbow.laundry.application;

import com.rainbow.laundry.ui.BaseNormalActivity;

import java.util.Stack;

/**
 * Created by wyc on 2018/1/2.
 */

public class ActivityManager {
    public static Stack<BaseNormalActivity> activityStack = new Stack<>();

    public static void addActivity(BaseNormalActivity activity) {
        activityStack.add(activity);
    }

    public static void removeActivity(BaseNormalActivity activity) {
        if (activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
            }
        }
    }

    public static void exit(BaseNormalActivity baseActivity) {
        for (BaseNormalActivity activity : activityStack) {
            if (!activity.isFinishing()&&!activity.equals(baseActivity)) {
                activity.finish();
            }
        }

    }
    public static void loginOut() {
        for (BaseNormalActivity activity : activityStack) {
            activity.finish();
        }

    }
}
