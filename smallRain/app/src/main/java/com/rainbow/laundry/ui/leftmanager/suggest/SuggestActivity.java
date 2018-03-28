package com.rainbow.laundry.ui.leftmanager.suggest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by wyf on 2018/1/7.
 */

public class SuggestActivity extends BaseActivity {
    CommonRecyclerViewAdapter<HashMap<String,String>> mAdapter=null;
    private String[] str_rece ={"洗衣质量","物流速度","付款流程","服务态度","产品易用","其他"};
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private String type ="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest);
        setBack();
        setTitle("建议反馈");
        initView();
    }

    private void initView(){

        final List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

        for (int i =0; i<str_rece.length;i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("name",str_rece[i]);
            map.put("isselect","0");
            list.add(map);
        }

        mAdapter = new CommonRecyclerViewAdapter<HashMap<String,String>>(this,list) {
            @Override
            public void convert(CommonRecyclerViewHolder h, HashMap<String,String> entity, final int position) {

                TextView textview = h.getView(R.id.textview);
                textview.setText(entity.get("name"));
                if(entity.get("isselect").equals("0")){
                    textview.setBackground(getResources().getDrawable(R.drawable.shape_bg_suggest_item_gray));
                    textview.setTextColor(getResources().getColor(R.color.color_666666));
                } else {
                    textview.setBackground(getResources().getDrawable(R.drawable.shape_bg_suggest_item_bule));
                    textview.setTextColor(getResources().getColor(R.color.white));
                }
                textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i=0;i<list.size();i++){
                            list.get(i).put("isselect","0");
                        }
                        list.get(position).put("isselect","1");
                        type = (position+1)+"";
                        mLRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.suggest_item;
            }
        };
        LRecyclerView recyclerView = (LRecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //recyclerView.addItemDecoration(getDivider());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        recyclerView.setAdapter(mLRecyclerViewAdapter);
        header = LayoutInflater.from(this).inflate(R.layout.suggest_top, (ViewGroup) findViewById(android.R.id.content), false);
        footer = LayoutInflater.from(this).inflate(R.layout.suggest_buttom, (ViewGroup) findViewById(android.R.id.content), false);

        mLRecyclerViewAdapter.addHeaderView(header);
        mLRecyclerViewAdapter.addFooterView(footer);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.refresh();
        setView();
    }
    private  View header =null;
    private  View footer =null;

    private void setView(){
        Button btn_ok =  footer.findViewById(R.id.btn_ok);
        final EditText et_suggest  = footer.findViewById(R.id.et_suggest);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringUtil.isBlank(type)){
                    ToastUtils.makeText(mActivity,"请选择反馈内容");
                } else if(StringUtil.isBlank(et_suggest.getText().toString())){
                    ToastUtils.makeText(mActivity,"请输入你的宝贵意见");
                }  else {
                    ridicule(et_suggest.getText().toString() );
                }
            }
        });
    }

    private void ridicule(String contont){
        HttpParams httpParams = new HttpParams();
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("type",type);
        httpParams.put("contont",contont);
        httpParams.put("indentId","0");
        HttpUntilx.RequestInfo(Urlx.ridicule,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                           //{"status":1,"msg":"反馈成功","data":null}
                            if(jsonObject.optInt("status")==1){
                                ToastUtils.makeText(mActivity,jsonObject.getString("msg"));
                                SuggestActivity.this.finish();
                            } else {
                                ToastUtils.makeText(mActivity,"反馈失败！");
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

}
