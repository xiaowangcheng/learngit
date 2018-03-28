package com.rainbow.laundry.ui.leftmanager.recharge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.modle.order.Order;
import com.rainbow.laundry.ui.BaseActivity;

import java.util.HashMap;

/**
 *  Created by wyf on 2018/1/11.
 */

public class RechargeDetialActivity extends BaseActivity {
    CommonRecyclerViewAdapter<HashMap<String, String>> mAdapter =null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_detial);
        setBack();
        setTitle("消费明细");
        Order.DataEntity.IndentListEntity indentListEntity =
                (Order.DataEntity.IndentListEntity) getIntent().getSerializableExtra("listEntities");
        initView(indentListEntity);
    }

    private String[] name = {"洗衣", "洗鞋", "洗家纺", "洗窗帘", "一袋洗"};
    private void initView(Order.DataEntity.IndentListEntity indentListEntity){
        TextView tv_amt = (TextView) findViewById(R.id.tv_amt);
        TextView tv_payways = (TextView) findViewById(R.id.tv_payways);
        TextView tv_servers = (TextView) findViewById(R.id.tv_servers);
        TextView tv_time = (TextView) findViewById(R.id.tv_time);
        TextView tv_no = (TextView) findViewById(R.id.tv_no);
        tv_amt.setText("-"+indentListEntity.getPayMoney());
        tv_payways.setText(indentListEntity.getPayWay());

        String[] str=null;
        if(indentListEntity.getServiceids().contains(",")){
            str = indentListEntity.getServiceids().split(",");
        } else {
            str = new String[1];
            str[0] = indentListEntity.getServiceids();
        }
        StringBuilder  str_project  =new StringBuilder();
        for (int i=0;i<str.length;i++){
            str_project.append(name[Integer.parseInt(str[i])]+", ");
        }
        str_project.delete(str_project.length()-2,str_project.length());
        tv_servers.setText(str_project);
        tv_time.setText(indentListEntity.getAddTime());
        tv_no.setText(indentListEntity.getOrderNumber());
    }



}
