package com.rainbow.laundry.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by wyf on 2018/1/2.
 */

public class ToastUtils {
    private static Toast mToast = null;

    public static void makeText(Context context, CharSequence msg) {

        if (null == msg) {
            return;
        }

        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    public static void makeText(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                if (mToast == null) {
                    mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(message);
                    if (message.length() < 20)
                        mToast.setDuration(Toast.LENGTH_SHORT);
                    else
                        mToast.setDuration(Toast.LENGTH_LONG);
                }
                mToast.show();
            }
        });
    }

    public static void makeText(final Activity activity, final int messageRes) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                CharSequence message = activity.getResources().getString(messageRes);
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                if (mToast == null) {
                    mToast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(message);
                    if (message.length() < 20)
                        mToast.setDuration(Toast.LENGTH_SHORT);
                    else
                        mToast.setDuration(Toast.LENGTH_LONG);
                }
                mToast.show();
            }
        });
    }
}
