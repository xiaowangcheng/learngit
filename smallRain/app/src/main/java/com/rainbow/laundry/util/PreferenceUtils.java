package com.rainbow.laundry.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.rainbow.laundry.application.MyApplication;

/**
 * Created by wyf on 2018/1/2.
 */

public class PreferenceUtils {
    public static final String PREF_NAME = "preference";
    public static SharedPreferences preference = MyApplication.getContext()
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

    public static void set(String paramString1, String paramString2) {
        preference.edit().putString(paramString1, paramString2).apply();
    }
    public static String getString(String paramString1, String paramString2) {
        return preference.getString(paramString1, paramString2);
    }

    public static boolean getBoolean(String paramString, boolean paramBoolean) {
        return preference.getBoolean(paramString, paramBoolean);
    }
    public static void setBoolean(String paramString, boolean paramBoolean) {
        preference.edit().putBoolean(paramString,paramBoolean);
    }

    public static void set(String paramString, boolean paramBoolean) {
        preference.edit().putBoolean(paramString, paramBoolean).apply();
    }
}
