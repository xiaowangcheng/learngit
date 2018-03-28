package com.rainbow.laundry.ui.leftmanager.message;

import android.os.Bundle;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.adapter.SwipeMenuAdapter;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.message.MessageModel;
import com.rainbow.laundry.ui.BasePageListActivity;
import com.rainbow.laundry.R;
import com.rainbow.laundry.modle.message.MessageList;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;

import java.util.List;

import rx.functions.Action1;

/**
 *  Created by wyf on 2018/1/9.
 */

public class MessageActivity extends BasePageListActivity<MessageModel> {

    private SwipeMenuAdapter mDataAdapter = null;

    private boolean isRefresh = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        setBack();
        setTitle("我的消息");
        setView();
    }


    private void setView(){
        mDataAdapter = new SwipeMenuAdapter(this);
        mDataAdapter.setDataList(listDataT);
//        mDataAdapter.setOnDelListener(new SwipeMenuAdapter.onSwipeListener() {
//            @Override
//            public void onDel(int pos) {
//                mDataAdapter.getDataList().remove(pos);
//                mDataAdapter.notifyItemRemoved(pos);//推荐用这个
//                if(pos != (mDataAdapter.getDataList().size())){ // 如果移除的是最后一个，忽略 注意：这里的mDataAdapter.getDataList()不需要-1，因为上面已经-1了
//                    mDataAdapter.notifyItemRangeChanged(pos, mDataAdapter.getDataList().size() - pos);
//                }
//                //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
//            }
//
//            @Override
//            public void onTop(int pos) {//置顶功能有bug，后续解决
//            }
//        });
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        initView(R.dimen.default_divider_height,R.dimen.default_divider_padding0);

    }


    private void addItems(List<MessageModel> list) {
        mDataAdapter.addAll(list);
    }

    @Override
    protected void getMoreData() {
        super.getMoreData();
        if(isRefresh){
            listDataT.clear();
            mDataAdapter.clear();
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("userId", MyUserInfo.getUserId(""));
        httpParams.put("pageNumber", pageNumber);
        httpParams.put("pageCount", pagesize);
        HttpUntilx.RequestInfo(Urlx.queryAllMessage, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        MessageList messageList = JSON2Class.fromJson(s,MessageList.class);
                        if(messageList.getStatus()==1){
                            if(messageList.getData().getMsgList().size()>0){
                                listDataT.addAll(messageList.getData().getMsgList());
                                addItems(listDataT);
                                mRecyclerView.refreshComplete(10);
                                mDataAdapter.notifyDataSetChanged();
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

    @Override
    protected void onUIRefresh() {
        super.onUIRefresh();
        mDataAdapter.clear();
        mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
        isRefresh = true;
        getMoreData();
    }
}
