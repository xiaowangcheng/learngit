package com.example.com.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.com.xrecyclerview.adapter.MenuAdapter;
import com.example.com.xrecyclerview.view.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRecyclerView = (XRecyclerView)this.findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager( 3,
                StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        View header =   LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);

        initMenu(header);
        mRecyclerView.addHeaderView(header);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        listData.clear();
                        for(int i = 0; i < 25 ;i++){
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            mRecyclerView.stopLoadMore();
                            for(int i = 0; i < 25 ;i++){
                                listData.add("item" + (i + listData.size()) );
                            }
                            mAdapter.notifyDataSetChanged();
                            mRecyclerView.restoreFooter();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            mAdapter.notifyDataSetChanged();
                            mRecyclerView.noMoreLoading();
                        }
                    }, 1000);
                }
                times ++;
            }
        });

        listData = new  ArrayList<String>();
        for(int i = 0; i < 25 ;i++){
            listData.add("item" + (i + listData.size()) );
        }
        mAdapter = new MyAdapter(listData);

        mRecyclerView.setAdapter(mAdapter);
    }
    GridView gvMenus;  //格子
    private void initMenu(View view) {
        gvMenus = (GridView) view.findViewById(R.id.gv_menus);
        List<SparseArray> menuDatas = new ArrayList<SparseArray>();
        for (int i =0;i<8;i++){
            SparseArray sp = new SparseArray();
//            sp.put(1, StoreConstants.mList_community_icon.get(i));
//            sp.put(2, StoreConstants.mList_community_text.get(i));
            menuDatas.add(sp);
        }
        MenuAdapter mGvAdapter = new MenuAdapter(StaggeredGridActivity.this,menuDatas);
        gvMenus.setAdapter(mGvAdapter);
        setListViewHeightBasedOnChildren(gvMenus);

    }

    public   void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 4;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }


}
