package com.rainbow.laundry.ui.leftmanager.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.address.Address;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.ViewUtils;
import com.rainbow.laundry.view.MyDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by wyf on 2018/1/9.
 */

public class AddressManagerActivity extends BaseActivity {
    private CommonRecyclerViewAdapter<Address.DataEntity> mAdapter = null;
    private List<Address.DataEntity> addressList =null;
    private boolean isEditMode;//true:地址管理模式  flase:选择收货地址模式
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_manager);
        setBack();
        setTitle("地址管理");
        isEditMode = getIntent().getBooleanExtra("",true);
        doFindByAddressId();

    }

    private void doFindByAddressId() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("userid", MyUserInfo.getUserId(""));
        Common.log("url地址：" + Urlx.myEstate);
        HttpUntilx.RequestInfo(Urlx.myEstate, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                        Common.log("报文：" + s);
                        Address address = JSON2Class.fromJson(s, Address.class);
                        if(address.getStatus()==1){
                            addressList = address.getData();
                            setView();
                        } else {
                            ToastUtils.makeText(mActivity,address.getMsg()+"");
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void setView() {
        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressManagerActivity.this, AddAdressActivity.class);
                intent.putExtra("editMode", false);
                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);

            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL, 15));
        mAdapter = new CommonRecyclerViewAdapter<Address.DataEntity>(AddressManagerActivity.this, addressList) {
            @Override
            public void convert(CommonRecyclerViewHolder h, final Address.DataEntity entity, final int position) {
                h.setText(R.id.tv_name, entity.getName() + "    " + entity.getPhoneNumber());
                h.setText(R.id.tv_address, entity.getEstateName()+"  " + entity.getDetailed());

                TextView tv_defult = h.getView(R.id.tv_defult);
                if (entity.getIsetw() == 1) {
                    tv_defult.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.icom_dx_correct), null, null, null);
                } else {
                    tv_defult.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.icom_dx_error), null, null, null);

                }
                TextView tv_edit = h.getView(R.id.tv_edit);
                TextView tv_delete = h.getView(R.id.tv_delete);
                tv_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AddressManagerActivity.this, AddAdressActivity.class);
                        intent.putExtra("editMode", true);
                        intent.putExtra("address", (Serializable) addressList.get(position));
                        startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                    }
                });
                tv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Common.showDoubleDialog(mActivity, "温馨提示", "您确认要删除？", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dodeleteAddress(position);
                                Common.calcelDialog();
                            }
                        });

                    }
                });
                tv_defult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (entity.getIsetw() == 1) {
                            ToastUtils.makeText(mActivity,"已是默认地址！");
                        } else {
                            setAddressDefult(addressList.get(position).getId()+"",position);
                        }
                    }
                });

            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.address_manager_item;
            }
        };
        mAdapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent();
                intent.putExtra("address",  addressList.get(position));
                setResult(ViewUtils.NORMAL_RETURN,intent);
                AddressManagerActivity.this.finish();
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void dodeleteAddress( final int position) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("addressid", addressList.get(position).getId());
        httpParams.put("userid", MyUserInfo.getUserId(""));
        httpParams.put("token", MyUserInfo.getToken());
        HttpUntilx.RequestInfo(Urlx.dodeleteAddress, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            Common.log("报文：" + s);
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optString("status").equals("1")) {
                                ToastUtils.makeText(mActivity, "删除成功！");
                                addressList.remove(position);
                                mAdapter.notifyItemRemoved(position);
                                mAdapter.notifyDataSetChanged();
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

    private void setAddressDefult(String addressid,final int position){
        HttpParams httpParams = new HttpParams();
        httpParams.put("addressId", addressid);
        httpParams.put("userId", MyUserInfo.getUserId(""));
        HttpUntilx.RequestInfo(Urlx.updateAddressDefult, httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        try {
                            Common.log("报文：" + s);
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optString("status").equals("1")) {
                                ToastUtils.makeText(mActivity,"设置成功！");
                                for (Address.DataEntity entity :addressList){
                                    entity.setIsetw(0);
                                }
                                addressList.get(position).setIsetw(1);
                                mAdapter.notifyDataSetChanged();
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
        if (requestCode == ViewUtils.NORMAL_JUMP && resultCode == ViewUtils.ADD_ADDMSG) {
            doFindByAddressId();
        }
    }
}
