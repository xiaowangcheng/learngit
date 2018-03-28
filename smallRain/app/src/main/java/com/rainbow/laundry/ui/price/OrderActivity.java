package com.rainbow.laundry.ui.price;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2018/1/6.
 */

public class OrderActivity extends BaseActivity {
    CommonRecyclerViewAdapter<String> commonRecyclerViewHolder;
    private int index =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setBack();
        setTitle("下单");
        setTitleRecycler();
    }
    private void setTitleRecycler( ){
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final List<String> lis = new ArrayList<String>();
        lis.add("洗衣");
        lis.add("洗鞋");
        lis.add("洗家纺");
        lis.add("洗窗帘");
        lis.add("袋洗");
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        commonRecyclerViewHolder = new CommonRecyclerViewAdapter<String>(OrderActivity.this,lis){

            @Override
            public void convert(CommonRecyclerViewHolder h, String entity, final int position) {
                final TextView tv_item =h.getView(R.id.tv_item);
                tv_item.setText(lis.get(position));
                if(position == index){
                    tv_item.setTextColor(getResources().getColor(R.color.gray));
                    tv_item.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_blue));
                } else {
                    tv_item.setTextColor(getResources().getColor(R.color.gray));
                    tv_item.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_white_gray));
                }

                tv_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        index = position;
                        notifyDataSetChanged();
                    }
                });
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.item;
            }
        };

        mRecyclerView.setAdapter(commonRecyclerViewHolder);
    }

}
