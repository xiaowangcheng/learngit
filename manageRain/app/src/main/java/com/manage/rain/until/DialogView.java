package com.manage.rain.until;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * Created by wyf on 2018/1/15.
 */

public class DialogView extends Dialog {
    private static int default_width = 160; //默认宽度
    private static int default_height = 120;//默认高度
    private boolean isShowing;
    private final static int SHOW_DIALOG_DELAY = 0xff94;

    private DelayHandler mDelayHandler;

    public DialogView(Context context,  int layout, int style) {

        this(context, default_width, default_height, layout, style);
        isShowing = false;
    }

    public DialogView(Context context, int width, int height, int layout, int style) {
        super(context, style);

        setContentView(layout);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        float density = getDensity(context);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;

        window.setAttributes(params);

    }

    private float getDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }

    public void showDelay() {
        showDelay(1500);
    }

    public void showDelay(long delay) {
        if (mDelayHandler == null) {
            mDelayHandler = new DelayHandler(DialogView.this);
        }
        mDelayHandler.sendEmptyMessageDelayed(SHOW_DIALOG_DELAY, delay);
        isShowing = true;
    }

    @Override
    public void cancel() {
        super.cancel();
        removeMessage();
        isShowing = false;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        removeMessage();
        isShowing = false;
    }

    @Override
    public boolean isShowing() {
        return isShowing || super.isShowing();
    }

    private void removeMessage() {
        if (mDelayHandler != null && isShowing) {
            mDelayHandler.removeMessages(SHOW_DIALOG_DELAY);
        }
    }

    private static class DelayHandler extends Handler {
        WeakReference<DialogView> dialogWeakReference;

        DelayHandler(DialogView dialogView) {
            dialogWeakReference = new WeakReference<>(dialogView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DialogView temp = dialogWeakReference.get();
            if (temp == null) {
                return;
            }
            switch (msg.what) {
                case SHOW_DIALOG_DELAY:
                    temp.show();
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }
}
