package com.rainbow.laundry.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.Order_detail;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.BottomView;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.Rainbow;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.util.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;


/**
 * Created by wyf on 2018/1/7.
 */

public class OrderStateQueryActivity extends BaseActivity implements View.OnClickListener{
    private CommonRecyclerViewAdapter<String> commonRecyclerViewHolder;
    private BottomView bottomView=null;

    private String[] name = {"洗衣", "洗鞋", "洗家纺", "洗窗帘", "一袋洗"};
    private String[] value = {"1", "2", "3", "4", "5"};
    private int state  =0;

    private  String indentid ="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_state_query);
        setBack();
        setTitle("取件中");
        indentid = getIntent().getStringExtra("orderId");
        indentDetail();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        indentid = intent.getStringExtra("orderId");
        indentDetail();
    }

    private void indentDetail(){
        HttpParams httpParams = new HttpParams();
        httpParams.put("indentid",  indentid);
        httpParams.put("userId", MyUserInfo.getUserId(""));
        Common.showLoading(mActivity);
        HttpUntilx.RequestInfo(Urlx.indentDetail, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Common.calcelLoading();
                        Order_detail order_detail = JSON2Class.fromJson(s, Order_detail.class);
                        if (order_detail.getStatus()==1) {
                            if(order_detail.getData().getIndent().getStatus()==1
                                    || order_detail.getData().getIndent().getStatus()==2){
                                state =0;
                            } else if(order_detail.getData().getIndent().getStatus()==3
                                     ||order_detail.getData().getIndent().getStatus()==4){
                                state =1;
                            } else if(order_detail.getData().getIndent().getStatus()==5){
                                state =2;
                            } else if(order_detail.getData().getIndent().getStatus()==6 ||
                                    order_detail.getData().getIndent().getStatus()==7){
                                state =3;
                            } else if(order_detail.getData().getIndent().getStatus()==8 ||
                                    order_detail.getData().getIndent().getStatus()==8){
                                state =4;
                            }
                            setTitleRecycler();
                            setView(order_detail);
                        } else {
                            ToastUtils.makeText(mActivity,"下单失败！");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void setView(Order_detail order_detail){
        //order_detail.getData().getIndent().getTotal()
        TextView tv_Serial = (TextView) findViewById(R.id.tv_Serial);//订单编号
        TextView tv_PlaceOrder_time = (TextView) findViewById(R.id.tv_PlaceOrder_time);//订单时间
        TextView tv_project = (TextView) findViewById(R.id.tv_project);//服务项目
        tv_Serial.setText(order_detail.getData().getIndent().getOrderNumber());
        tv_PlaceOrder_time.setText(order_detail.getData().getIndent().getAddTime());
        tv_project.setText(Rainbow.toGetService(order_detail.getData().getIndent().getServiceIds()));
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("地址信息");
        findViewById(R.id.image_arrow).setVisibility(View.GONE);
        Button btn_buttom1 = (Button) findViewById(R.id.btn_buttom1);
        Button btn_buttom2 = (Button) findViewById(R.id.btn_buttom2);
        btn_buttom1.setVisibility(View.GONE);
        btn_buttom2.setVisibility(View.GONE);
        if(state==0){
            findViewById(R.id.lin_beizhu).setVisibility(View.VISIBLE);
            btn_buttom2.setVisibility(View.VISIBLE);
            btn_buttom2.setText("取消订单");
        } else if(state==1){
            findViewById(R.id.lin_beizhu).setVisibility(View.VISIBLE);
            btn_buttom2.setVisibility(View.VISIBLE);
            btn_buttom2.setText("取消订单");
        } else if(state==2){
            findViewById(R.id.lin_beizhu).setVisibility(View.VISIBLE);
            btn_buttom1.setVisibility(View.VISIBLE);
            btn_buttom2.setVisibility(View.VISIBLE);
            btn_buttom1.setText("投诉");
            btn_buttom2.setText("待签收");
        } else if(state==3){
            btn_buttom1.setVisibility(View.VISIBLE);
            btn_buttom2.setVisibility(View.VISIBLE);
            btn_buttom1.setText("投诉");
            btn_buttom2.setText("待签收");
        } else if(state==4){
            btn_buttom1.setVisibility(View.VISIBLE);
            btn_buttom2.setVisibility(View.VISIBLE);
            btn_buttom1.setText("投诉");
            btn_buttom2.setText("评价");
        }

        TextView tv_remark = (TextView) findViewById(R.id.tv_remark);//备注

        tv_PlaceOrder_time.setText(order_detail.getData().getIndent().getAddTime());
        String[] str=null;
        String employeeId =order_detail.getData().getIndent().getServiceIds();
        if(employeeId.contains(",")){
          str = order_detail.getData().getIndent().getServiceIds().split(",");
        } else {
            str = new String[1];
            str[0] = order_detail.getData().getIndent().getServiceIds();
        }

        StringBuilder  str_project  =new StringBuilder();
        for (int i=0;i<str.length;i++){
            str_project.append(name[Integer.parseInt(str[i])]+", ");
        }
        str_project.delete(str_project.length()-2,str_project.length());
        tv_project.setText(str_project);
        tv_remark.setText(order_detail.getData().getIndent().getRemark());

        TextView tv_name = (TextView) findViewById(R.id.tv_name);//联系人姓名
        TextView tv_tel = (TextView) findViewById(R.id.tv_tel);//联系人电话
        TextView tv_detial = (TextView) findViewById(R.id.tv_detial);//地址
        tv_name.setText(order_detail.getData().getIndent().getAddress().getName());
        tv_tel.setText(order_detail.getData().getIndent().getAddress().getPhoneNumber());

        tv_detial.setText(order_detail.getData().getIndent().getAddress().getCity()
                +order_detail.getData().getIndent().getAddress().getCounty()
        +order_detail.getData().getIndent().getAddress().getDetailed());


        btn_buttom1.setOnClickListener(this);
        btn_buttom2.setOnClickListener(this);
    }




    private void showDialog(){
        bottomView  = new BottomView(OrderStateQueryActivity.this, R.style.BottomViewTheme_Dialog, R.layout.dialog_order_pickup);
        TextView btn_cancel =  bottomView.getView().findViewById(R.id.btn_cancel);
        LinearLayout tv_cause_need = bottomView.getView().findViewById(R.id.tv_cause_need);//不需要了
        LinearLayout tv_cause_time = bottomView.getView().findViewById(R.id.tv_cause_time);//需要更换上门时间
        LinearLayout tv_cause_e = bottomView.getView().findViewById(R.id.tv_cause_e);//小e拒绝取件或态度恶劣
        LinearLayout tv_cause_price = bottomView.getView().findViewById(R.id.tv_cause_price);//价格因素
        LinearLayout tv_cause_other = bottomView.getView().findViewById(R.id.tv_cause_other);//其他原因

        tv_cause_need.setOnClickListener(this);
        tv_cause_time.setOnClickListener(this);
        tv_cause_e.setOnClickListener(this);
        tv_cause_price.setOnClickListener(this);
        tv_cause_other.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        bottomView.setAnimation(R.style.BottomToTopAnim);
        bottomView.showBottomView(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cause_need://不需要了
                cancelOrder("1");
                break;
            case R.id.tv_cause_time://需要更换上门时间
                cancelOrder("2");
                break;
            case R.id.tv_cause_e://小e拒绝取件或态度恶劣
                cancelOrder("3");
                break;
            case R.id.tv_cause_price://价格因素
                cancelOrder("4");
                break;
            case R.id.tv_cause_other://其他原因
                cancelOrder("5");
                break;
            case R.id.btn_cancel://取消
                bottomView.dismissBottomView();
                break;
            case R.id.btn_buttom1:
                if(state==0){
                } else if(state==1){
                } else if(state==2){
                } else if(state==3){
                } else if(state==4){
                }
                break;

            case R.id.btn_buttom2:
                if(state==0){
                    showDialog();
                } else if(state==1){

                } else if(state==2){

                } else if(state==3){
                } else if(state==4){
                }

                break;
        }
    }


    private void cancelOrder(String cancelId){
        HttpParams httpParams = new HttpParams();
        httpParams.put("indentid",  indentid);
        httpParams.put("cancelId",  cancelId);
        httpParams.put("upstatus",  -1);
        Common.showLoading(mActivity);
        HttpUntilx.RequestInfo(Urlx.indentDetail, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Common.calcelLoading();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if(jsonObject.optInt("status")==1) {
                                ToastUtils.makeText(mActivity,"取消成功");
                                setResult(ViewUtils.NORMAL_FINSH);
                                mActivity.finish();
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


    private void setTitleRecycler() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final List<String> lis = new ArrayList<String>();
        lis.add("取件中");
        lis.add("送往加工店");
        lis.add("清洗中");
        lis.add("送回中");
        lis.add("已签收");
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        commonRecyclerViewHolder = new CommonRecyclerViewAdapter<String>(OrderStateQueryActivity.this, lis) {

            @Override
            public void convert(CommonRecyclerViewHolder h, String entity, final int position) {
                final View lineLeft = h.getView(R.id.lineLeft);
                final View lineRight = h.getView(R.id.lineRight);
                h.setText(R.id.tv_name, lis.get(position));
                TextView iamge = h.getView(R.id.image);
                if (position == 0) {
                    lineLeft.setVisibility(View.INVISIBLE);
                } else if (position == lis.size()) {
                    lineRight.setVisibility(View.INVISIBLE);
                }
                if (state == 0) {
                    if (position == 0) {
                        lineLeft.setVisibility(View.INVISIBLE);
                    } else if (position == lis.size() - 1) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setVisibility(View.INVISIBLE);
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    } else {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setBackground(getResources().getDrawable(R.color.gray));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    }
                } else if (state == 1) {
                    if (position == 0) {
                        lineRight.setVisibility(View.VISIBLE);
                        lineRight.setBackground(getResources().getDrawable(R.color.btn_bule));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position == 1) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.btn_bule));
                        lineRight.setBackground(getResources().getDrawable(R.color.gray));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position == lis.size() - 1) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setVisibility(View.INVISIBLE);
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    } else {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setBackground(getResources().getDrawable(R.color.gray));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    }
                } else if (state == 2) {
                    if (position == 0) {
                        lineRight.setBackground(getResources().getDrawable(R.color.btn_bule));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position > 0 && position < 2) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.btn_bule));
                        lineRight.setBackground(getResources().getDrawable(R.color.btn_bule));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position == 2) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.btn_bule));
                        lineRight.setBackground(getResources().getDrawable(R.color.gray));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position == lis.size() - 1) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setVisibility(View.INVISIBLE);
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    } else {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setBackground(getResources().getDrawable(R.color.gray));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    }
                } else if (state == 3) {
                    if (position == 0) {
                        lineRight.setBackground(getResources().getDrawable(R.color.btn_bule));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position > 0 && position < 3) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.btn_bule));
                        lineRight.setBackground(getResources().getDrawable(R.color.btn_bule));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position == 3) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.btn_bule));
                        lineRight.setBackground(getResources().getDrawable(R.color.gray));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position == lis.size() - 1) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setVisibility(View.INVISIBLE);
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    } else {
                        lineLeft.setBackground(getResources().getDrawable(R.color.gray));
                        lineRight.setBackground(getResources().getDrawable(R.color.gray));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    }
                } else if (state == 4) {
                    if (position == 0) {
                        lineRight.setBackground(getResources().getDrawable(R.color.btn_bule));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position > 0 && position < 4) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.btn_bule));
                        lineRight.setBackground(getResources().getDrawable(R.color.btn_bule));
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else if (position == 4) {
                        lineLeft.setBackground(getResources().getDrawable(R.color.btn_bule));
                        lineRight.setVisibility(View.INVISIBLE);
                        iamge.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    }
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.progress_item;
            }
        };

        mRecyclerView.setAdapter(commonRecyclerViewHolder);
    }

}
