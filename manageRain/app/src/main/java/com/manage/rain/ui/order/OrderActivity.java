package com.manage.rain.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.manage.rain.R;
import com.manage.rain.adapter.CommonRecyclerViewAdapter;
import com.manage.rain.adapter.CommonRecyclerViewHolder;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.Order;
import com.manage.rain.net.Netapi;
import com.manage.rain.ui.BasePageListActivity;
import com.manage.rain.until.Common;
import com.manage.rain.until.JSON2Class;
import com.manage.rain.until.StringUtil;
import com.manage.rain.until.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 未处理订单
 *  Created by wyf on 2018/1/20.
 */

public class OrderActivity extends BasePageListActivity<Order.DataEntity.IndentListEntity> {

    CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity> mAdapter=null;
    private String type="0";
    private Button btn_update =null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setBack();
        type = getIntent().getStringExtra("type");
        btn_update = (Button) findViewById(R.id.btn_update);
        String title="";
        if(type.equals("0")){
            title="未处理订单";
        } else if(type.equals("1")){
            title="派送中";
        } else if(type.equals("2")){
            title="洗衣中";
        } else if(type.equals("3")){
            title="送回中";
        } else if(type.equals("4")){
            title="未支付订单";
        } else if(type.equals("5")){
            title="签收成功";
            btn_update.setVisibility(View.VISIBLE);
        } else if(type.equals("6")){
            title="瑕疵衣物";
        }
        setTitle(title);
        setView();
    }

    private void setView(){
        mAdapter = new CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity>(mActivity,listDataT) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Order.DataEntity.IndentListEntity entity, int position) {

                if(!StringUtil.isBlank(entity.getServiceIds())){
                    String[] ds = entity.getServiceIds().split(",");
                    String stringBuilder="";
                    for (int i=0;i<ds.length;i++){
                        if(i<2){
                            stringBuilder += Common.rainbow_name[Integer.parseInt(ds[i])]+"，";
                        }
                    }
                    stringBuilder = stringBuilder.substring(0,stringBuilder.length()-1);
                    h.setText(R.id.tv_name, stringBuilder);
                }
                h.setText(R.id.tv_time, entity.getAddTime());
                h.setText(R.id.tv_address, entity.getEstateName() +" "+
                        entity.getDetailed());
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
                if(type.equals("6")){
                    Intent intent = new Intent(OrderActivity.this,OrderDetaildefectsActivity.class);
                    intent.putExtra("type",type);
                    intent.putExtra("indentid",String.valueOf(listDataT.get(position).getId()));
                    startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                } else {
                    Intent intent = new Intent(OrderActivity.this,OrderDetailActivity.class);
                    intent.putExtra("type",type);
                    intent.putExtra("indentid",String.valueOf(listDataT.get(position).getId()));
                    startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                }

            }
        });
        initView();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    protected void getMoreData() {
        if(type.equals("4")){
            HttpParams httpParams = new HttpParams();
            httpParams.put("pageNumber",pageNumber);
            Netapi.requestParams(Urlx.ordersByPayStatus,httpParams).doOnSubscribe(new Action0() {
                @Override
                public void call() {

                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    Common.log(s);
                    Order nopay = JSON2Class.fromJson(s,Order.class);
                    if(nopay.getStatus()==1){
                        if(nopay.getData().getIndentList().size()>0){
                            listDataT.addAll(nopay.getData().getIndentList());
                            mRecyclerView.refreshComplete(10);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mRecyclerView.mRefreshHeader.refreshComplete();
                            mRecyclerView.setNoMore(true);
                        }
                    }else {
                        mRecyclerView.mRefreshHeader.refreshComplete();
                        mRecyclerView.setNoMore(true);
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        } else {
            JSONObject jsonObject = new JSONObject();
            try {
                JSONArray jsonArray = new JSONArray();
                if(type.equals("0")){
                    jsonArray.put(1);
                } else if(type.equals("1")){
                    jsonArray.put(2);
                } else if(type.equals("2")){
                    jsonArray.put(3);
                    jsonArray.put(4);
                    jsonArray.put(5);
                    jsonArray.put(6);
                } else if(type.equals("3")){
                    jsonArray.put(7);
                } else if(type.equals("5")){
                    jsonArray.put(8);
                    jsonArray.put(9);
                    jsonArray.put(10);
                } else if(type.equals("6")){
                    jsonArray.put(-2);
                }
                jsonObject.put("indentTypes",jsonArray);
                jsonObject.put("pageNumber",pageNumber);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Netapi.requestJson(Urlx.indentTypes,jsonObject).doOnSubscribe(new Action0() {
                @Override
                public void call() {

                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    Common.log(s);
                    Order nopay = JSON2Class.fromJson(s,Order.class);
                    if(nopay.getStatus()==1){
                        if(nopay.getData().getIndentList().size()>0){
                            listDataT.addAll(nopay.getData().getIndentList());
                            mRecyclerView.refreshComplete(10);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mRecyclerView.mRefreshHeader.refreshComplete();
                            mRecyclerView.setNoMore(true);
                        }
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }


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
        mLRecyclerViewAdapter.notifyDataSetChanged();
        getMoreData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ViewUtils.NORMAL_JUMP && resultCode == ViewUtils.NORMAL_FINSH){
            mRecyclerView.refresh();

        }
    }
}
