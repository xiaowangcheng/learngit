package com.manage.rain.config;


import com.manage.rain.until.PreferenceUtils;

/**
 * Created by wyf on 2018/1/5.
 */

public class UserInfo {
    public static String getUserId(String defValue) {
        return PreferenceUtils.getString("userid", defValue);
    }

    public static void setUserId(String userId) {
        if (null == userId) {
            return;
        }
        PreferenceUtils.set("userid", userId);
    }

    public static String getToken() {
        return getToken("");
    }

    public static String getToken(String defValue) {
        return PreferenceUtils.getString("token", defValue);
    }

    public static void setToken(String token) {
        if (null == token) {
            return;
        }
        PreferenceUtils.set("token", token);
    }

    public static String getHeaderUrl(String defValue) {
        return PreferenceUtils.getString("headerUrl", defValue);
    }

    public static void setHeaderUrl(String headerUrl) {
        if (null == headerUrl) {
            return;
        }
        PreferenceUtils.set("headerUrl", headerUrl);
    }

    public static String getBalance() {
        return PreferenceUtils.getString("headerUrl", "");
    }

    public static void setBalance(String balance) {
        if (null == balance) {
            return;
        }
        PreferenceUtils.set("headerUrl", balance);
    }

    public static String getPhone() {
        return PreferenceUtils.getString("phone", "");
    }

    public static void setPhone(String phone) {
        if (null == phone) {
            return;
        }
        PreferenceUtils.set("phone", phone);
    }

}
