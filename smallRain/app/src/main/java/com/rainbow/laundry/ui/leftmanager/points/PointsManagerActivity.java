package com.rainbow.laundry.ui.leftmanager.points;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.Integral;
import com.rainbow.laundry.ui.BasePageListActivity;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.util.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;

import static com.rainbow.laundry.R.id.tv_jifen;

/**
 * Created by wyf on 2018/1/9.
 */

public class PointsManagerActivity extends BasePageListActivity<Integral.DataEntity.IndentListEntity> {
    CommonRecyclerViewAdapter<Integral.DataEntity.IndentListEntity> mAdapter=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.points_manager);
        setBack();
        setTitle("积分管理");
        if(!StringUtil.isBlank(MyUserInfo.getToken())){
            findViewById(R.id.tv_jilu).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_integral).setVisibility(View.VISIBLE);
        } else {
            TextView tv_jifen = (TextView) findViewById(R.id.tv_jifen);
            tv_jifen.setText("登录查看积分");
        }
        setView();
    }

    private void  setView(){
        mAdapter = new CommonRecyclerViewAdapter<Integral.DataEntity.IndentListEntity>(this,listDataT) {
            @Override
            public void convert(CommonRecyclerViewHolder h, Integral.DataEntity.IndentListEntity entity, int position) {
                    h.setText(tv_jifen,String.valueOf(entity.getProductIntegral())+"积分");//积分
                    h.setText(R.id.tv_title,String.valueOf(entity.getProductTitle()));//
                ImageView image = h.getView(R.id.image);
                Glide.with(context)
                        .load(Urlx.Host+entity.getProductImg())
                        .asBitmap()//资源是一个图片是才算成功，其他的都算解析失败。
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(image);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.points_item;
            }
        };
        mLRecyclerViewAdapter =new LRecyclerViewAdapter(mAdapter);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(PointsManagerActivity.this, ProjectDetialActivity.class);//OrderActivity
                intent.putExtra("orderId",listDataT.get(position).getId());
                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
            }
        });
        initView();
    }

    @Override
    protected void getMoreData() {
        super.getMoreData();
        HttpParams httpParams = new HttpParams();
        httpParams.put("userId", MyUserInfo.getUserId(""));
        httpParams.put("pageNumber",1);
        HttpUntilx.RequestInfo(Urlx.mallList,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Integral integral =  JSON2Class.fromJson(s, Integral.class);
                            TextView tv_integral = (TextView) findViewById(R.id.tv_integral);
                            tv_integral.setText(integral.getData().getIntegral()+"");
                            if(integral.getStatus()==1  ){
                                if(integral.getData().getIndentList().size()>0){
                                    listDataT.addAll(integral.getData().getIndentList());
                                    mRecyclerView.refreshComplete(10);
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mRecyclerView.setNoMore(true);
                                }
                            }else {
                                ToastUtils.makeText(PointsManagerActivity.this,"查询失败");
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
