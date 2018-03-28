package com.rainbow.laundry.ui.leftmanager.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.order.Order;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.view.MyDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.functions.Action1;

/**
 *  Created by wyf on 2018/1/11.
 */

public class RechargeListActivity extends BaseActivity {
    CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity> mAdapter =null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_list);
        setBack();
        setTitle("消费明细");

        oadByUserId();
    }

    private void oadByUserId(){
        HttpParams httpParams = new HttpParams();
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("paystatus", "1");
        HttpUntilx.RequestInfo(Urlx.indentByUser,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            Order order =  JSON2Class.fromJson(s, Order.class);
                            if(order.getStatus()==1  ){
                                List<Order.DataEntity.IndentListEntity> listEntities =  order.getData().getIndentList();
                                initView(listEntities);
                            }else {
                                ToastUtils.makeText(RechargeListActivity.this,"查询失败");
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

    private String[] name = {"洗衣", "洗鞋", "洗家纺", "洗窗帘", "一袋洗"};
    private void initView(final List<Order.DataEntity.IndentListEntity> listEntities){
        RecyclerView mRecyclerView = (RecyclerView)  findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity>(this,listEntities) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Order.DataEntity.IndentListEntity entity, int position) {
                    h.setText(R.id.tv_amt,"-"+entity.getPayMoney());
                h.setText(R.id.tv_time,"下单时间："+entity.getAddTime());
                String[] str=null;
                if(entity.getServiceids().contains(",")){
                    str = entity.getServiceids().split(",");
                } else {
                    str = new String[1];
                    str[0] = entity.getServiceids();
                }
                StringBuilder  str_project  =new StringBuilder();
                for (int i=0;i<str.length;i++){
                    str_project.append(name[Integer.parseInt(str[i])]+", ");
                }
                str_project.delete(str_project.length()-2,str_project.length());
                h.setText(R.id.tv_name,String.valueOf(str_project));

            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.recharge_item;
            }
        };
        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(RechargeListActivity.this,RechargeDetialActivity.class);
                intent.putExtra("listEntities",listEntities.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
