package com.rainbow.laundry.ui.index.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.order.Order1;
import com.rainbow.laundry.ui.BasePageListFragment;
import com.rainbow.laundry.ui.index.OrderCompletedActivity;
import com.rainbow.laundry.ui.order.OrderStateQueryActivity;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;

/**
 *  Created by wyf on 2018/1/9.
 */

public class OrderNoCompletedFragment extends BasePageListFragment<Order1.DataEntity.IndentListEntity> {
    CommonRecyclerViewAdapter<Order1.DataEntity.IndentListEntity> mAdapter=null;
    private String[] strings ={"洗衣","洗鞋","洗家纺","洗窗帘","袋洗"};
    View mContain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mContain == null) {
            mContain = inflater.inflate(R.layout.recycler_view, container, false);
            initView();
        }
        ViewGroup parent = (ViewGroup) mContain.getParent();
        if (parent != null) {
            parent.removeView(mContain);
        }
        return mContain;
    }

    private void initView(){
        mAdapter = new CommonRecyclerViewAdapter<Order1.DataEntity.IndentListEntity>(getActivity(),listDataT) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Order1.DataEntity.IndentListEntity entity, int position) {
                    if(!StringUtil.isBlank(entity.getServiceIds())){
                        String[] ds = entity.getServiceIds().split(",");
                        String stringBuilder="";
                        for (int i=0;i<ds.length;i++){
                            if(i<2){
                                if(Integer.parseInt(ds[i])<5){
                                    stringBuilder += strings[Integer.parseInt(ds[i])]+"，";
                                }
                            }
                        }
                        stringBuilder = stringBuilder.substring(0,stringBuilder.length()-1);
                        h.setText(R.id.tv_pro_name,String.valueOf(stringBuilder));

                        h.setText(R.id.tv_order_tiem, entity.getAddTime()+"下单");
                        h.setText(R.id.tv_expected_time, entity.getOutTime());
                        h.setText(R.id.tv_amt, "¥"+StringUtil.getZeroDouble(entity.getTotal()) );
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
                Intent intent = new Intent(getActivity(), OrderStateQueryActivity.class);//OrderActivity
                intent.putExtra("orderId",listDataT.get(position).getId()+"");
                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
            }
        });
        initProductLiner(mContain);
        setBack();
        setTitle("未完成");
        setRight("已完成", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),OrderCompletedActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void getMoreData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("paystatus","0");
        httpParams.put("pageNumber",""+pageNumber);
        HttpUntilx.RequestInfo(Urlx.indentByUser,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Order1 order =  JSON2Class.fromJson(s, Order1.class);
                            if(order.getStatus()==1){
                                if(order.getData().getIndentList().size()>0){
                                    listDataT.addAll(order.getData().getIndentList());
                                    mRecyclerView.refreshComplete(10);
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mRecyclerView.setNoMore(true);

                                }
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
