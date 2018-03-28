package com.rainbow.laundry.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rainbow.laundry.adapter.DialogListAdapter;
import com.rainbow.laundry.application.MyApplication;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.R;

import java.util.List;

/**
 *  Created by wyf on 2018/1/10.
 */

public class ViewUtils {
    /**
     * 页面返回
     */
    public final static int NEWADD_CREORD = 101;//新增地址返回订单确认
    public final static int CGEADD_CREORD = 102;//选择地址返回订单确认
    public final static int ADD_ADDMSG = 105;//新增地址返回地址管理
    public final static int EDIT_ADDMSG = 106;//编辑地址返回地址管理
    public final static int ADDMSG_CGEADD = 107;//地址管理返回选择收货地址

    public final static int NORMAL_JUMP= 10;//普通页面跳转
    public final static int NORMAL_RETURN = 11;//普通页面跳转
    public final static int NORMAL_FINSH = 12;//普通页面跳转
    /**
     * 页面跳转
     */
    //(new_add_flag)
    public static int CREORD_NEWADDRESS = 103;//确认订单界面-->新增地址界面
    public static int ADDMSG_NEWADDRESS = 104;//地址管理界面-->新增地址界面


    public static  void showCameraDialog(final BottomView bottomView,
                                         Context context, List<String> listdata, DialogListAdapter.OnItemClickLister onItemClickLister){

        RecyclerView recyclerView = bottomView.getView().findViewById(R.id.dialog_list_rcv);
        DialogListAdapter dialogListAdapter = new DialogListAdapter(listdata,R.color.font_light,
                R.color.font_light,R.color.font_light,1,context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dialogListAdapter);
        dialogListAdapter.setOnItemClickLister(onItemClickLister);
        TextView btn_cancel = bottomView.getView().findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomView!=null) {
                    bottomView.dismissBottomView();
                }
            }
        });
        bottomView.setAnimation(R.style.BottomToTopAnim);
        bottomView.showBottomView(true);

    }

    public static final int getColor(Context context, @ColorRes int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static void exit(){
        MyUserInfo.setToken("");
        MyUserInfo.setBalance("");
        MyUserInfo.setHeaderUrl("");
        MyUserInfo.setPhone("");
        MyUserInfo.setUserId("");
        MyUserInfo.logoutqq(MyApplication.getContext());
    }
}
