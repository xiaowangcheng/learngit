package com.manage.rain.ui.back.framment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.manage.rain.R;
import com.manage.rain.adapter.CommonRecyclerViewAdapter;
import com.manage.rain.adapter.CommonRecyclerViewHolder;
import com.manage.rain.ui.BasePageListFragment;

import java.util.HashMap;

/**
 * Created by wyf on 2018/1/19.
 */

public class OrderBackSortTimeFragment extends BasePageListFragment<HashMap<String,String>> {
    View mContain;
    CommonRecyclerViewAdapter<HashMap<String,String>> mAdapter=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mContain == null) {
            mContain =inflater.inflate(R.layout.order_back_sort,container,false);
            initView();
        }
        ViewGroup parent = (ViewGroup) mContain.getParent();
        if (parent != null) {
            parent.removeView(mContain);
        }
        return mContain;
    }
    private void initView(){
        mAdapter = new CommonRecyclerViewAdapter<HashMap<String,String>>(getActivity(),listDataT) {
            @Override
            public void convert(CommonRecyclerViewHolder h, HashMap<String, String> entity, int position) {

            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.order_item;
            }
        };

        mLRecyclerViewAdapter =new LRecyclerViewAdapter(mAdapter);

        initProductLiner(mContain);
        setBack();
        setTitle("未完成");


    }

    @Override
    protected void getMoreData() {
        super.getMoreData();


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
