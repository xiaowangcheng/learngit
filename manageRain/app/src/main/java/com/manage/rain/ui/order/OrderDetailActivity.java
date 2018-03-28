package com.manage.rain.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.lzy.okgo.model.HttpParams;
import com.manage.rain.R;
import com.manage.rain.adapter.CommonRecyclerViewAdapter;
import com.manage.rain.adapter.CommonRecyclerViewHolder;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.OrderDetial;
import com.manage.rain.net.Netapi;
import com.manage.rain.ui.BaseActivity;
import com.manage.rain.until.Common;
import com.manage.rain.until.JSON2Class;
import com.manage.rain.until.StringUtil;
import com.manage.rain.until.ToastUtils;
import com.manage.rain.until.ViewUtils;
import com.manage.rain.view.SimpleDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;


/**
 * 未处理订单
 *  Created by wyf on 2018/1/20.
 */

public class OrderDetailActivity extends BaseActivity {
    private static final int REQUEST_CODE = 200;
    private String type="0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_nopay_detail);
        type = getIntent().getStringExtra("type");
        setBack();
        setTitle("订单详情");
        requestDetial();
    }

    private void requestDetial(){
        HttpParams params = new HttpParams();
        params.put("indentid",getIntent().getStringExtra("indentid"));

        Netapi.requestParams(Urlx.backIndentEmployee,params).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Common.log(s);
                OrderDetial orderDetial =  JSON2Class.fromJson(s, OrderDetial.class);
                if(orderDetial.getStatus()==1){
                    OrderDetial.DataEntity.IndentEntity indentEntity = orderDetial.getData().getIndent();
                    List<OrderDetial.DataEntity.EmployeesEntity> entityList = orderDetial.getData().getEmployees();
                    setView(indentEntity,entityList);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
    private CommonRecyclerViewAdapter<OrderDetial.DataEntity.EmployeesEntity> mAdapter =null;
    private int selectIndex =0;
    private void setView(final OrderDetial.DataEntity.IndentEntity indentEntity,
                         final List<OrderDetial.DataEntity.EmployeesEntity> entityList ){
        TextView order_no = (TextView) findViewById(R.id.order_no);//订单编号
        TextView order_time = (TextView) findViewById(R.id.order_time);//取件时间
        TextView order_service = (TextView) findViewById(R.id.order_service);//服务项目
        TextView tv_amt = (TextView) findViewById(R.id.tv_amt);//服务项目

        TextView tv_name = (TextView) findViewById(R.id.tv_name);//收货姓名
        TextView tv_tel = (TextView) findViewById(R.id.tv_tel);//收货电话
        TextView tv_detial = (TextView) findViewById(R.id.tv_detial);//收货电话

        et_barCode = (EditText) findViewById(R.id.et_barCode);
        LinearLayout lin_kuaidiyuan = (LinearLayout) findViewById(R.id.lin_kuaidiyuan);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        if(false){
            // LayoutManager.setAutoMeasureEnabled(true);
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
            linearLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this,
                    getResources().getDrawable(R.drawable.line_divider),1));

            mAdapter = new CommonRecyclerViewAdapter<OrderDetial.DataEntity.EmployeesEntity>(mActivity,entityList) {
                @Override
                public void convert(CommonRecyclerViewHolder h, final OrderDetial.DataEntity.EmployeesEntity entity, final int position) {
                    ImageView iv_select = h.getView(R.id.iv_select);
                    RelativeLayout rl_kuaidi = h.getView(R.id.rl_kuaidi);
                    if(entity.getSelectIndxe()==1){
                        iv_select.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_sel));
                    } else {
                        iv_select.setBackground(getResources().getDrawable(R.drawable.icon_tjxdz_normal));
                    }
                    h.setText(R.id.tv_name,entity.getEmployeeName());
                    h.setText(R.id.tv_tel,entity.getEmployeePhone());
                    rl_kuaidi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (OrderDetial.DataEntity.EmployeesEntity e: entityList){
                                e.setSelectIndxe(0);
                            }
                            entity.setSelectIndxe(1);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
                @Override
                public int getLayoutViewId(int viewType) {
                    return R.layout.courier_item;
                }
            };

            recyclerView.setAdapter(mAdapter);
        } else {
            lin_kuaidiyuan.setVisibility(View.GONE);
        }
        order_no.setText(indentEntity.getOrderNumber());
        order_time.setText(indentEntity.getAddTime());
        tv_amt.setText(String.valueOf(indentEntity.getTotal()));
        order_service.setText(Common.toGetService(indentEntity.getServiceIds()));
        tv_name.setText(indentEntity.getConName());
        tv_tel.setText(indentEntity.getPhoneNumber());
        tv_detial.setText(indentEntity.getEstateName()+" "+
                indentEntity.getDetailed());

        Button btn_check = (Button) findViewById(R.id.btn_check);
        Button btn_zxing = (Button) findViewById(R.id.btn_zxing);
        LinearLayout lin_zing = (LinearLayout) findViewById(R.id.lin_zing);
        btn_check.setVisibility(View.VISIBLE);
        if(indentEntity.getStatus()==1){
            btn_check.setText("修改为取衣中");
        } else if(indentEntity.getStatus()==2){
            btn_check.setText("确认取到衣服");
            lin_zing.setVisibility(View.VISIBLE);
            btn_zxing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(OrderDetailActivity.this,CaptureActivity.class);
                    //startActivityForResult(intent,11);
                    callCapture(null);
                }
            });
        }

//        else if(indentEntity.getStatus()==3){
//            btn_check.setText("修改为送到工厂");
//            btn_check.setVisibility(View.GONE);
//        } else if(indentEntity.getStatus()==4){
//            btn_check.setText("修改为洗衣中");
//        } else if(indentEntity.getStatus()==5){
//            btn_check.setText("修改为回送中");
//        } else if(indentEntity.getStatus()==6){
//            btn_check.setText("确认送回");
//        }
        else if(indentEntity.getStatus()==7){
            btn_check.setText("确认送回");
        } else if(indentEntity.getStatus()==9){
            btn_check.setText("查看评价");
        } else{
            btn_check.setVisibility(View.GONE);
        }

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //entityList.get(selectIndex).getEmployeeId()
                updateState(indentEntity.getStatus());
            }
        });



//        btn_zxing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callCapture(null);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (null != data && requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    et_barCode.setText(data.getStringExtra(Intents.Scan.RESULT));
                    break;
                default:
                    break;
            }
        }else if(null != data && resultCode==12){

        }

    }


    private void callCapture(String characterSet) {

        Intent intent = new Intent();
        intent.setAction(Intents.Scan.ACTION);
        // intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
        intent.putExtra(Intents.Scan.CHARACTER_SET, characterSet);
        intent.putExtra(Intents.Scan.WIDTH, 800);
        intent.putExtra(Intents.Scan.HEIGHT, 600);
        // intent.putExtra(Intents.Scan.PROMPT_MESSAGE, "type your prompt message");
        intent.setClass(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    EditText et_barCode =null;
    private void updateState(int status){
        HttpParams params = new HttpParams();
        params.put("indentid",getIntent().getStringExtra("indentid"));
        if(status==2){
            if(StringUtil.isBlank(et_barCode.getText().toString())){
                ToastUtils.makeText(OrderDetailActivity.this,"条形码不能为空！");
                return;
            } else {
                params.put("barCode",et_barCode.getText().toString());
            }
        }
        params.put("upstatus",status+1);
//        if(type.equals("0")){
//            params.put("employeeId",selectIndex);
//        }
        Netapi.requestParams(Urlx.updateStatus,params).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Common.log(s);
                try {
                    JSONObject jsonObject
                            = new JSONObject(s);
                    int status = jsonObject.optInt("status");
                    if(status==1){
                        ToastUtils.makeText(mActivity,jsonObject.optString("msg"));
                        setResult(ViewUtils.NORMAL_FINSH);
                        OrderDetailActivity.this.finish();
                    } else {
                        ToastUtils.makeText(mActivity,"修改失败！");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }


}
