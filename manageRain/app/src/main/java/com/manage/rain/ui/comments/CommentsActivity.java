package com.manage.rain.ui.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.manage.rain.R;
import com.manage.rain.adapter.CommonRecyclerViewAdapter;
import com.manage.rain.adapter.CommonRecyclerViewHolder;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.commons.Comments;
import com.manage.rain.net.Netapi;
import com.manage.rain.ui.BasePageListActivity;
import com.manage.rain.until.Common;
import com.manage.rain.until.DateUtil;
import com.manage.rain.until.JSON2Class;
import com.manage.rain.until.StringUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 用户评论
 * Created by wyf on 2018/1/18.
 */

public class CommentsActivity extends BasePageListActivity<Comments.DataEntity.CommitListEntity> {

    CommonRecyclerViewAdapter<Comments.DataEntity.CommitListEntity> mAdapter=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentspingjun);
        setBack();
        setTitle("用户评论");
        setView();
    }

    private void setView(){
        mAdapter = new CommonRecyclerViewAdapter<Comments.DataEntity.CommitListEntity>(CommentsActivity.this,listDataT) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Comments.DataEntity.CommitListEntity entity, int position) {
                if(!StringUtil.isBlank(entity.getIndentId())){
                    h.setText(R.id.tv_name, Common.rainbow_name[Integer.parseInt(entity.getIndentId())-1]);
                }
                h.setText(R.id.tv_time, DateUtil.stampToDate(entity.getCommitTime()));
                h.setText(R.id.tv_address, entity.getCommitContent());
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.comments_item;
            }
        };
        mLRecyclerViewAdapter =new LRecyclerViewAdapter(mAdapter);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        initView();
    }

    @Override
    protected void getMoreData() {
        HttpParams params = new HttpParams();
        params.put("pageNumber",pageNumber);
        Netapi.requestParams(Urlx.listCommit,params).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Common.log(s);
                Comments comments = JSON2Class.fromJson(s,Comments.class);
                if(comments.getStatus()==1){
                    if(comments.getData().getCommitList().size()>0){
                        listDataT.addAll(comments.getData().getCommitList());
                        mRecyclerView.refreshComplete(10);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mRecyclerView.setNoMore(true);
                    }
                } else {

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                Common.calcelLoading();
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
}
