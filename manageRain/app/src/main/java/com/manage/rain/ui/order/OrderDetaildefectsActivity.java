package com.manage.rain.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.lzy.okgo.model.HttpParams;
import com.manage.rain.R;
import com.manage.rain.adapter.DefectsAdapter;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.DefctsClothes;
import com.manage.rain.model.OrderDetial;
import com.manage.rain.net.Netapi;
import com.manage.rain.ui.BaseActivity;
import com.manage.rain.until.Common;
import com.manage.rain.until.JSON2Class;
import com.manage.rain.until.StringUtil;
import com.manage.rain.until.ToastUtils;
import com.manage.rain.until.ViewUtils;
import com.manage.rain.view.MyDividerGridItemDecoration;

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

public class OrderDetaildefectsActivity extends BaseActivity {
    private static final int REQUEST_CODE = 200;
    private String type="0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_nopay_defects_detail);
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

                    requestabnormalForIndent(indentEntity,entityList);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void requestabnormalForIndent(final OrderDetial.DataEntity.IndentEntity indentEntity,
                                          final List<OrderDetial.DataEntity.EmployeesEntity> entityList){
        HttpParams params = new HttpParams();
        params.put("indentid",getIntent().getStringExtra("indentid"));
        Netapi.requestParams(Urlx.abnormalForIndent,params).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Common.log(s);
                DefctsClothes orderDetial =  JSON2Class.fromJson(s, DefctsClothes.class);
                List<DefctsClothes.DataEntity> dataEntityList =orderDetial.getData();
                setView(indentEntity,entityList,dataEntityList);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
    private DefectsAdapter mAdapter=null;
    private void setView(final OrderDetial.DataEntity.IndentEntity indentEntity,
                         final List<OrderDetial.DataEntity.EmployeesEntity> entityList,
                         List<DefctsClothes.DataEntity> dataEntityList){

        TextView order_no = (TextView) findViewById(R.id.order_no);//订单编号
        TextView order_time = (TextView) findViewById(R.id.order_time);//取件时间
        TextView order_service = (TextView) findViewById(R.id.order_service);//服务项目
        TextView tv_amt = (TextView) findViewById(R.id.tv_amt);//服务项目

        TextView tv_name = (TextView) findViewById(R.id.tv_name);//收货姓名
        TextView tv_tel = (TextView) findViewById(R.id.tv_tel);//收货电话
        TextView tv_detial = (TextView) findViewById(R.id.tv_detial);//收货电话

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDividerGridItemDecoration(this));
        mAdapter  = new DefectsAdapter(this,dataEntityList);

        recyclerView.setAdapter(mAdapter);
        order_no.setText(indentEntity.getOrderNumber());
        order_time.setText(indentEntity.getAddTime());
        tv_amt.setText(String.valueOf(indentEntity.getTotal()));
        order_service.setText(Common.toGetService(indentEntity.getServiceIds()));
        tv_name.setText(indentEntity.getAddress().getName());
        tv_tel.setText(indentEntity.getAddress().getPhonenumber());
        tv_detial.setText(indentEntity.getAddress().getCity()+ " "+
                indentEntity.getAddress().getCounty()+" "+
                indentEntity.getAddress().getDetailed());
        RelativeLayout add_defact = (RelativeLayout) findViewById(R.id.add_defact);

        add_defact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetaildefectsActivity.this,AddDefactActivity.class);
                intent.putExtra("indentId",String.valueOf(indentEntity.getId()));
                startActivityForResult(intent,11);
            }
        });
        //mAdapter.notifyItemRangeChanged(0,dataEntityList.size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (null != data && requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    data.setClass(this, CaptureResultActivity.class);
                    startActivity(data);
                    break;
                default:
                    break;
            }
        }else if (resultCode==11){
            requestDetial();
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


    private void updateState(int status,String selectIndex){
        HttpParams params = new HttpParams();
        params.put("indentid",getIntent().getStringExtra("indentid"));
        if(status==2){
             EditText et_barCode = (EditText) findViewById(R.id.et_barCode);
            if(StringUtil.isBlank(et_barCode.getText().toString())){
                ToastUtils.makeText(OrderDetaildefectsActivity.this,"条形码不能为空！");
                return;
            } else {
                params.put("barCode",et_barCode.getText().toString());
            }
        }
        params.put("upstatus",status+1);
        if(type.equals("0")){
            params.put("employeeId",selectIndex);
        }
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
                        OrderDetaildefectsActivity.this.finish();
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
