package com.rainbow.laundry.ui.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.order.Order;
import com.rainbow.laundry.ui.BasePageListActivity;
import com.rainbow.laundry.ui.order.OrderStateQueryActivity;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.util.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by wyf on 2017/1/10.
 */

public class OrderCompletedActivity extends BasePageListActivity<Order.DataEntity.IndentListEntity> {
    CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity> mAdapter=null;
    private String[] strings ={"","洗衣","洗鞋","洗家纺","窗帘清洗","一袋洗"};
    List<HashMap<String,String>> list = new  ArrayList<HashMap<String,String> >();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        setBack();
        setTitle("已完成");
        setRight("未完成", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderCompletedActivity.this.finish();
            }
        });
        setView();
    }


    private void setView(){
        mAdapter = new CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity>(mActivity,listDataT) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Order.DataEntity.IndentListEntity entity, int position) {
                if(!StringUtil.isBlank(entity.getServiceids())){
                    String[] ds = entity.getServiceids().split(",");
                    String stringBuilder="";
                    for (int i=0;i<ds.length;i++){
                        stringBuilder += strings[Integer.parseInt(ds[i])]+"， ";
                    }
                    stringBuilder = stringBuilder.substring(0,stringBuilder.length()-2);
                    h.setText(R.id.tv_pro_name,String.valueOf(stringBuilder));
                    h.setText(R.id.tv_order_tiem, entity.getAddTime());
                    h.setText(R.id.tv_expected_time, entity.getOutTime());
                    h.setText(R.id.tv_amt, "¥"+ StringUtil.getZeroDouble(entity.getTotal()) );
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.order_item;
            }
        };
        mLRecyclerViewAdapter =new LRecyclerViewAdapter(mAdapter);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(OrderCompletedActivity.this, OrderStateQueryActivity.class);//OrderActivity
                intent.putExtra("orderId",listDataT.get(position).getId());
                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
            }
        });
        initView();

    }


    @Override
    protected void getMoreData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("paystatus","0");
        httpParams.put("pageNumber",pageNumber);
        HttpUntilx.RequestInfo(Urlx.indentByUser,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Order order =  JSON2Class.fromJson(s, Order.class);
                            if(order.getStatus()==1  ){
                                if(order.getData().getIndentList().size()>0){
                                    listDataT.addAll(order.getData().getIndentList());
                                    mRecyclerView.refreshComplete(10);
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mRecyclerView.setNoMore(true);
                                }
                            }else {
                                ToastUtils.makeText(OrderCompletedActivity.this,"查询失败");
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

    /**
     * 已经没有更多数据
     */
    private  void noMoreData(){
        mRecyclerView.setNoMore(true);
    }

    @Override
    protected void onUIRefresh() {
        listDataT.clear();
        mAdapter.notifyDataSetChanged();
        getMoreData();
    }
}
