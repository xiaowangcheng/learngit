package com.manage.rain.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.manage.rain.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by wyf on 2018/1/3.
 */

public class BaseActivity extends AppCompatActivity {
    public BaseActivity mActivity;
    protected SystemBarTintManager tintManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }else {
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorTextGrey));
            tintManager.setStatusBarTintEnabled(true);
        }
        mActivity = this;
    }



    /**
     * 标题
     *
     * @param title
     */
    public void setTitle(int title) {
        String str = getResources().getString(title);
        setTitle(str);
    }

    /**
     * 标题
     *
     * @param title
     */
    public void setTitle(String title) {
        TextView tv_left = (TextView) findViewById(R.id.tv_title);
        tv_left.setText(title);
    }

    public void setRight(int drawable,View.OnClickListener click){
        TextView tv_left = (TextView) findViewById(R.id.tv_right);
        tv_left.setBackgroundDrawable(getResources().getDrawable(drawable));
        LinearLayout lin_right = (LinearLayout) findViewById(R.id.lin_right);
        lin_right.setOnClickListener(click);
    }
    public void setRight(String str,View.OnClickListener click){
        TextView tv_left = (TextView) findViewById(R.id.tv_right);
        tv_left.setText(str);
        LinearLayout lin_right = (LinearLayout) findViewById(R.id.lin_right);
        lin_right.setOnClickListener(click);
    }

    /**
     * 返回
     */
    public void setBack() {
        LinearLayout lin_left = (LinearLayout) findViewById(R.id.lin_left);
        lin_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.this.finish();
            }
        });
    }


    public DividerDecoration getDivider( ){
        return  getDivider(R.dimen.default_divider_height,R.dimen.default_divider_padding);
    }
    public DividerDecoration getDivider(int hight,int padding){
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(hight)
                .setPadding(padding)
                .setColorResource(R.color.split)
                .build();
        return divider;
    }

}
