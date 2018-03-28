package com.rainbow.laundry.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rainbow.laundry.R;

/**
 * Created by wyf on 2018/1/2.
 */

public class BaseFragment extends Fragment {
    protected SystemBarTintManager tintManager;

    private View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ){
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else {
            tintManager = new SystemBarTintManager(getActivity());
            //tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.white));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    protected void setView(View view){
        this.view=view;
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
        TextView tv_left = (TextView) this.view.findViewById(R.id.tv_title);
        tv_left.setText(title);
    }

    public void setRight(int drawable,View.OnClickListener click){
        TextView tv_left = (TextView) this.view.findViewById(R.id.tv_right);
        tv_left.setBackgroundDrawable(getResources().getDrawable(drawable));
        LinearLayout lin_right = (LinearLayout) this.view.findViewById(R.id.lin_right);
        lin_right.setOnClickListener(click);
    }
    public void setRight(String str,View.OnClickListener click){
        TextView tv_left = (TextView) this.view.findViewById(R.id.tv_right);
        tv_left.setText(str);
        LinearLayout lin_right = (LinearLayout) this.view.findViewById(R.id.lin_right);
        lin_right.setOnClickListener(click);
    }

    /**
     * 返回
     */
    public void setBack() {
        LinearLayout lin_left = (LinearLayout) this.view.findViewById(R.id.lin_left);
        lin_left.setVisibility(View.VISIBLE);
        lin_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }
}
