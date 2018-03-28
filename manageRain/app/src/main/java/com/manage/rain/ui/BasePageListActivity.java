package com.manage.rain.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.manage.rain.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2018/1/3.
 */

public class BasePageListActivity<T> extends BaseActivity {

    public List<T> listDataT = new ArrayList<T>();
    public  LRecyclerView mRecyclerView =null;
    public LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    protected int pageNumber = 1;
    protected int pagesize =10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public void initView(){
        initView(R.dimen.default_divider_height,R.dimen.default_divider_padding0);
    }

    public void initView(int hight,int padding){
        mRecyclerView = (LRecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(getDivider(hight,padding));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //mRecyclerView.setLoadMoreEnabled(true);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNumber =1;
                onUIRefresh();
            }
        });
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
        mRecyclerView.refresh();
    }




    protected  void getMoreData(){};

    protected  void onUIRefresh(){}
}
