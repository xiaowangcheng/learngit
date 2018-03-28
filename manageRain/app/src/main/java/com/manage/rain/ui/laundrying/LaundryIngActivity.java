package com.manage.rain.ui.laundrying;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.manage.rain.R;
import com.manage.rain.adapter.CommonRecyclerViewAdapter;
import com.manage.rain.adapter.CommonRecyclerViewHolder;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.Order;
import com.manage.rain.net.Netapi;
import com.manage.rain.ui.BasePageListActivity;
import com.manage.rain.ui.order.OrderDetailActivity;
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
 * 洗衣中
 * Created by wyf on 2018/1/19.
 */

public class LaundryIngActivity extends BasePageListActivity<Order.DataEntity.IndentListEntity> {

    CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity> mAdapter=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setBack();
        setTitle("洗衣中");
        setView();
    }


    private void setView(){
        mAdapter = new CommonRecyclerViewAdapter<Order.DataEntity.IndentListEntity>(mActivity,listDataT) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Order.DataEntity.IndentListEntity entity, int position) {

                if(!StringUtil.isBlank(entity.getServiceIds())){
                    h.setText(R.id.tv_name, Common.rainbow_name[Integer.parseInt(entity.getServiceIds())-1]);
                }
                h.setText(R.id.tv_time,  entity.getAddTime());
                h.setText(R.id.tv_address, entity.getEstateName()+" "+
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
                Intent intent = new Intent(LaundryIngActivity.this,OrderDetailActivity.class);
                intent.putExtra("indentid",listDataT.get(position).getId());
                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
            }
        });
        initView();
    }


    @Override
    protected void getMoreData() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(5);
        try {
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
        if(requestCode== ViewUtils.NORMAL_JUMP && resultCode == ViewUtils.NORMAL_FINSH){
            mRecyclerView.refresh();
        }
    }
}
