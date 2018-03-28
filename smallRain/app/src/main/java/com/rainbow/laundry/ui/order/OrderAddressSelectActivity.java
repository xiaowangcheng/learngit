package com.rainbow.laundry.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.address.Address;
import com.rainbow.laundry.modle.good.Pro_good;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.ui.leftmanager.address.AddressManagerActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.util.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by wyf on 2018/1/7.
 */

public class OrderAddressSelectActivity extends BaseActivity implements View.OnClickListener {
    CommonRecyclerViewAdapter<HashMap<String, String>> commonRecyclerViewHolder;
    private String[] name = {"洗衣", "洗鞋", "洗家纺", "洗窗帘", "袋洗"};
    private String[] value = {"0", "1", "2", "3", "4"};
    private ArrayList<Pro_good> orderList = null;
    List<HashMap<String, String>> serverId = new ArrayList<HashMap<String, String>>();

    private TextView tv_name = null;
    private TextView tv_tel = null;
    private TextView tv_detial = null;
    private Button btn_next = null;
    private RecyclerView mRecyclerView = null;

    private RelativeLayout rl_to_address = null;
    private TextView tv_to_address = null;
    private RelativeLayout rl_address = null;
    private String addressid ="";
    private EditText et_remark =null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_address_select);
        setBack();
        setTitle("地址选择");
        orderList = (ArrayList<Pro_good>) getIntent().getSerializableExtra("orderList");
        initView();
        setTitleRecycler();
        doFindByAddressId();

    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_tel = (TextView) findViewById(R.id.tv_tel);
        tv_detial = (TextView) findViewById(R.id.tv_detial);
        btn_next = (Button) findViewById(R.id.btn_next);
        tv_detial.setText("您还未填写收货地址，请先添加。");
        rl_to_address = (RelativeLayout) findViewById(R.id.rl_to_address);
        tv_to_address = (TextView) findViewById(R.id.tv_to_address);
        rl_address = (RelativeLayout) findViewById(R.id.rl_address);
        et_remark = (EditText) findViewById(R.id.et_remark);
    }

    private void setView(Address.DataEntity address){
        if (address !=null &&!StringUtil.isBlank(address.getId()+"")) {
            tv_name.setText(address.getName());
            tv_tel.setText(address.getPhoneNumber());
            tv_detial.setText(address.getProvince()
                    + address.getCounty()
                    + address.getDetailed());

            rl_address.setVisibility(View.VISIBLE);
            tv_to_address.setVisibility(View.GONE);
            addressid = address.getId()+"";

        } else {
            rl_address.setVisibility(View.GONE);
            tv_to_address.setVisibility(View.VISIBLE);
        }


    }

    /**
     * 查询收货地址
     */
    private void doFindByAddressId() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("userid", MyUserInfo.getUserId(""));
        HttpUntilx.RequestInfo(Urlx.loadAddress, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            rl_to_address.setOnClickListener(OrderAddressSelectActivity.this);
                            btn_next.setOnClickListener(OrderAddressSelectActivity.this);
                            if (jsonObject.optString("status").equals("1")) {
                                try{
                                    Address address = JSON2Class.fromJson(jsonObject.toString(), Address.class);
                                    setView(address.getData().get(0));
                                }catch (Exception e){
                                    Common.showDoubleDialog(OrderAddressSelectActivity.this, "提示", "你还未添加收货地址，请添加地址！", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(OrderAddressSelectActivity.this, AddressManagerActivity.class);
                                            intent.putExtra("editMode",true);
                                            startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                                        }
                                    });
                                }
                            } else {
                                setView(null);
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


    private void setTitleRecycler( ) {

        for (int i = 0; i < name.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", name[i]);
            map.put("value", value[i]);
            map.put("isSelect", "0");
            serverId.add(map);
        }

        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        commonRecyclerViewHolder = new CommonRecyclerViewAdapter<HashMap<String, String>>(OrderAddressSelectActivity.this, serverId) {

            @Override
            public void convert(CommonRecyclerViewHolder h, HashMap<String, String> entity, final int position) {
                final TextView tv_item = h.getView(R.id.tv_item);
                tv_item.setText(entity.get("name"));
                if (!entity.get("isSelect").equals("0")) {
                    tv_item.setTextColor(getResources().getColor(R.color.white));
                    tv_item.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_blue));
                } else {
                    tv_item.setTextColor(getResources().getColor(R.color.gray));
                    tv_item.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_white_gray));
                }
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.order_address_select_item;
            }
        };
        commonRecyclerViewHolder.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (serverId.get(position).get("isSelect").equals("0")) {
                    serverId.get(position).put("isSelect", "1");
                } else {
                    serverId.get(position).put("isSelect", "0");
                }
                commonRecyclerViewHolder.notifyItemChanged(position);
            }
        });
        mRecyclerView.setAdapter(commonRecyclerViewHolder);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_to_address:
                intent = new Intent(OrderAddressSelectActivity.this, AddressManagerActivity.class);
                intent.putExtra("editMode",true);
                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                break;
            case R.id.btn_next:
                if(StringUtil.isBlank(addressid)){
                    ToastUtils.makeText(mActivity,"请选择收货地址！");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=0;i<serverId.size();i++){
                        if(serverId.get(i).get("isSelect").equals("1")){
                            stringBuilder.append(serverId.get(i).get("value")+",");
                        }
                    }
                    if (StringUtil.isBlank(stringBuilder.toString())){
                        ToastUtils.makeText(mActivity,"请选择您需要的服务！");
                    } else {
                        String serviceids = stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length()).toString();
                        addOrder(addressid,serviceids);
                    }
                }
                break;
        }
    }


    private void addOrder(String addressid,String serviceids) {
        StringBuilder comboIdAndCount = new StringBuilder();
        double sumAmt=0.00;
        for (Pro_good data : orderList) {
            comboIdAndCount.append(data.getId() + "_" + data.getOrder_count() + ",");
            sumAmt+=data.getPrice();
        }
        comboIdAndCount.delete(comboIdAndCount.length()-1,comboIdAndCount.length());
        HttpParams httpParams = new HttpParams();
        httpParams.put("comboIdAndCount", String.valueOf(comboIdAndCount));
        httpParams.put("addressid", addressid);
        httpParams.put("total", String.valueOf(sumAmt));
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("token", MyUserInfo.getToken());
        httpParams.put("serviceids",  serviceids);
        httpParams.put("remark",  et_remark.getText().toString().trim());
        Common.showLoading(mActivity);
        HttpUntilx.RequestInfo(Urlx.Place_order, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if (jsonObject.optString("status").toString().equals("1")) {
                                ToastUtils.makeText(mActivity,"下单成功！");
                                try {
                                    Thread.currentThread();
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Common.calcelLoading();
                                Intent  intent = new Intent(OrderAddressSelectActivity.this, OrderSuccessActivity.class);//OrderActivity
                                intent.putExtra("remark",et_remark.getText().toString().trim());
                                intent.putExtra("orderId",jsonObject.optString("data"));
                                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                            } else {
                                Common.calcelLoading();
                                ToastUtils.makeText(mActivity,"下单失败！");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ViewUtils.NORMAL_JUMP && resultCode == ViewUtils.NORMAL_RETURN) {
            Address.DataEntity address = (Address.DataEntity) data.getSerializableExtra("address");
            setView(address);
        }else if (requestCode == ViewUtils.NORMAL_JUMP && resultCode == ViewUtils.NORMAL_FINSH) {
            setResult(ViewUtils.NORMAL_FINSH);
            mActivity.finish();
        }
    }
}
