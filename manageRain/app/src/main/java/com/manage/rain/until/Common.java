package com.manage.rain.until;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.manage.rain.R;


/**
 * Created by wyf on 2018/1/15.
 */

public class Common {

    public static String[] rainbow_name = {"洗衣","洗鞋","洗家纺","洗窗帘","袋洗"};
    public static String[] rainbow_value = {"0", "1", "2", "3", "4"};


    public static DialogView dialog =null;
    public static void log(String i){
        Log.i("rain",i);
    }
    private static AlertDialog.Builder normalDialog=null;
    public static Dialog builderDialog;

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
    public  static int getWindowheight(Activity content){
        WindowManager wm = (WindowManager) content.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return  height;
    }
    /**
     * 通用加载提示框
     *
     * @param context
     * @return
     */
    public static void showLoading( Activity context) {
        showLoading(context,"请稍后.....");
    }

    /**
     * 通用加载提示框
     *
     * @param context
     * @param title   提示语句
     * @return
     */
    public static void showLoading( Activity context, String title) {
        if(dialog==null){
            dialog = new DialogView(context, R.layout.load_dialog, R.style.Theme_dialog);//Dialog使用默认大小
            TextView textView = (TextView) dialog.findViewById(R.id.message);
            if (TextUtils.isEmpty(title)) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(title);
            }
            dialog.show();
        }
    }


    /**
     * 通用加载提示框
     *
     * @param context
     * @param title   提示语句
     * @return
     */
    public static void showDoubleDialog( Activity context, String title,String content,View.OnClickListener ok_click) {
        showDoubleDialog(context,title,content, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcelDialog();
            }
        },ok_click);
    }



    public  static void showNormalDialog(Activity activity,
                                         String title, String content, DialogInterface.OnClickListener click){

        normalDialog =
                new AlertDialog.Builder(activity);
        normalDialog.setTitle(title);
        normalDialog.setMessage(content);
        normalDialog.setPositiveButton("确定",
                click);
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        // 显示
        normalDialog.show();
    }

    public static void showNormalDialog(Activity activity,String content,DialogInterface.OnClickListener click){
        showNormalDialog(activity,"温馨提示",content,click);
    }

    public  static void showDoubleDialog(Activity activity,
                                         String title,
                                         String content,
                                         View.OnClickListener calcel_click,View.OnClickListener ok_click){

        normalDialog =
                new AlertDialog.Builder(activity, R.style.BottomViewTheme_Dialog);
        builderDialog = normalDialog.show();
        builderDialog.getWindow().setContentView(R.layout.dialog_double);
        WindowManager.LayoutParams lp = builderDialog.getWindow()
                .getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int fdfd = dm.widthPixels;
        lp.width =  fdfd *4 / 5; // 设置宽度
        builderDialog.getWindow().setAttributes(lp);

        TextView tv_title = builderDialog.findViewById(R.id.tv_title);
        TextView tv_content = builderDialog.findViewById(R.id.tv_content);
        TextView btn_cancel = builderDialog.findViewById(R.id.btn_cancel);
        TextView btn_ok = builderDialog.findViewById(R.id.btn_ok);
        tv_title.setText(title);
        tv_content.setText(content);
        btn_cancel.setOnClickListener(calcel_click);
        btn_ok.setOnClickListener(ok_click);
    }




    /**
     * 取消加载提示框
     */
    public  static void calcelLoading(){
        if(dialog != null){
            dialog.cancel();
            dialog = null;
        }
    }

    public static  void calcelDialog(){
        if(builderDialog !=null){
            builderDialog.cancel();
            builderDialog= null;
        }
    }

    public static void hideKeyBoard(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
    }

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
