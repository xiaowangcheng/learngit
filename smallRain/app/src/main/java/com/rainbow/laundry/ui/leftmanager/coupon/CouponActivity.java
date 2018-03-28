package com.rainbow.laundry.ui.leftmanager.coupon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.coupon.Coupon;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.view.MyDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 *  Created by wyf on 2018/1/9.
 */

public class CouponActivity  extends BaseActivity {
    CommonRecyclerViewAdapter<Coupon.DataEntity.KuponListEntity> mAdapter=null;
    private List<Coupon.DataEntity.KuponListEntity> list = new ArrayList<Coupon.DataEntity.KuponListEntity>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon);
        setBack();
        setTitle("优惠券");
        //initView();
        getdate();
    }

    private void getdate(){
        HttpParams httpParams = new HttpParams();
        httpParams.put("userId", MyUserInfo.getUserId(""));
        Common.showLoading(this);
        HttpUntilx.RequestInfo(Urlx.loadAllCoupon,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Common.calcelLoading();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Coupon order =  JSON2Class.fromJson(s, Coupon.class);
                            if(order.getStatus()==1){
                                list.clear();
                                list.addAll(order.getData().getKuponList());
                                initView();
                            }else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void initView(){
        RecyclerView mRecyclerView = (RecyclerView)  findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL,10));

        mAdapter = new CommonRecyclerViewAdapter<Coupon.DataEntity.KuponListEntity>(this,list) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Coupon.DataEntity.KuponListEntity entity, int position) {
                DecimalFormat df = new DecimalFormat("#");

                h.setText(R.id.tv_amt,df.format(entity.getKupon().getAgio()));

                h.setText(R.id.tv_man,df.format(entity.getKupon().getFordrn()));
                h.setText(R.id.tv_time,"有效期至"+entity.getKupon().getEndTime());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.coupon_item;
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }
}
