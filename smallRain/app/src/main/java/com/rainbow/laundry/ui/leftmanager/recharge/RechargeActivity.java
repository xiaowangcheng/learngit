package com.rainbow.laundry.ui.leftmanager.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.adapter.DialogListAdapter;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.Extralist;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.BottomView;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;


/**
 *  Created by wyf on 2018/1/11.
 */

public class RechargeActivity extends BaseActivity {
    CommonRecyclerViewAdapter<HashMap<String,String>> mAdapter=null;
    private String[] str_rece ={"充值200","充值300","充值400","充值500","充值600"};
    private String[] str_song ={"送100","送150","送200","送260","送300"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_phone);
        setBack();
        setTitle("充值");
        requestList();

    }

    private void requestList(){
        HttpParams httpParams = new HttpParams();
        httpParams.put("userid", MyUserInfo.getUserId(""));
        HttpUntilx.RequestInfo(Urlx.extralist,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            Extralist extralist = JSON2Class.fromJson(s, Extralist.class);
                            if(extralist.getStatus()==1){
                                List<Extralist.DataEntity.ExtraListEntity>  listEntities =  extralist.getData().getExtraList();
                                str_rece = new String[listEntities.size()];
                                for (int i=0;i<listEntities.size();i++){
                                    str_rece[i] = "充值"+String.valueOf(listEntities.get(i).getRecharge());
                                    str_song[i] = "送"+listEntities.get(i).getPresenter();
                                }
                                TextView tv_balance = (TextView) findViewById(R.id.tv_balance);
                                tv_balance.setText(extralist.getData().getBalance()+"");
                                setView();
                            } else {

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

    private void setView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        final List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

        for (int i =0; i<str_rece.length;i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("str_rece",str_rece[i]);
            map.put("str_song",str_song[i]);
            map.put("isselect","0");
            list.add(map);
        }
        mAdapter = new CommonRecyclerViewAdapter<HashMap<String,String>>(this,list) {
            @Override
            public void convert(CommonRecyclerViewHolder h, HashMap<String,String> map, final int position) {
                TextView tv_re = h.getView(R.id.tv_re);
                TextView tv_song = h.getView(R.id.tv_song);
                tv_re.setText(map.get("str_rece"));
                tv_song.setText(map.get("str_rece"));
                LinearLayout lin_rec = h.getView(R.id.lin_rec);
                if(map.get("isselect").equals("0")){
                    lin_rec.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_transparence_gray));
                    tv_re.setTextColor(getResources().getColor(R.color.text_color));
                    tv_song.setTextColor(getResources().getColor(R.color.color_FB2929));
                } else {
                    lin_rec.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_blue));

                    tv_re.setTextColor(getResources().getColor(R.color.white));
                    tv_song.setTextColor(getResources().getColor(R.color.white));
                }
                lin_rec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i =0; i<str_rece.length;i++){
                            list.get(i).put("isselect","0");
                        }
                        list.get(position).put("isselect","1");
                        mAdapter.notifyItemChanged(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.recharge_phone_item;
            }
        };

        recyclerView.setAdapter(mAdapter);

        TextView tv_look_recharge = (TextView) findViewById(R.id.tv_look_recharge);
        tv_look_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     Intent intent = new Intent(RechargeActivity.this,RechargeListActivity.class);
                     startActivity(intent);
            }
        });

        TextView btn_recharge = (TextView) findViewById(R.id.btn_recharge);
        btn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        TextView tv_shuoming = (TextView) findViewById(R.id.tv_shuoming);
        String str="继续操作表示您已同意<font color='#23ACF2'>《充值协议》</font>知晓充值本金和返现金额不可提现、转移、转赠。";
        String sds = "充值本金和返现金额不可提现、转移、转增。";
        //tv_shuoming.setText(Html.fromHtml(str));
        tv_shuoming.setText(sds);
    }


    private void showDialog(){
        List<String> list  = new ArrayList<String>();
        list.add("微信支付");
        list.add("支付宝支付");
        final BottomView bottomView  = new BottomView(RechargeActivity.this, R.style.BottomViewTheme_Dialog, R.layout.bottom_dialog);
        ViewUtils.showCameraDialog(bottomView,RechargeActivity.this, list, new DialogListAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0 :


                        bottomView.dismissBottomView();
                        break;
                    case 1 :


                        bottomView.dismissBottomView();
                        break;
                }
            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


}
