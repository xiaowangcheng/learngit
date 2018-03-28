package com.rainbow.laundry.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.rainbow.laundry.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2018/1/2.
 */

public class BasePageListFragment<T> extends BaseFragment {
    protected LRecyclerView mRecyclerView;
    public LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    protected List<T> listDataT;
    protected int pageNumber = 1;
    protected int pagesize =10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listDataT = new ArrayList<T>();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected  void initProductLiner(View view){
        setView(view);


        mRecyclerView = (LRecyclerView)view.findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.split)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);


        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNumber =1;
                onUIRefresh();
            }
        });

        //是否禁用自动加载更多功能,false为禁用, 默认开启自动加载更多功能
        mRecyclerView.setLoadMoreEnabled(true);

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                pageNumber =pageNumber+1;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        getMoreData();
                    }
                }, 1000);


            }
        });

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }


            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);

        mRecyclerView.refresh();
        //onUIRefresh();
    }



    protected  void getMoreData(){};

    protected  void onUIRefresh(){}
}
