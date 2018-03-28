package com.rainbow.laundry.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;

/**
 * Created by wyf on 2018/1/3.
 */

public class RegisterActivity  extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setBack();
        setTitle("注册");
        final EditText et_tel = (EditText) findViewById(R.id.et_tel);
        final EditText et_sms = (EditText) findViewById(R.id.et_sms);
        final EditText et_pwd = (EditText) findViewById(R.id.et_pwd);

        Button btn_submit = (Button) findViewById(R.id.btn_submit);

        TextView btn_sms = (TextView) findViewById(R.id.btn_sms);
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StringUtil.isBlank(et_tel.getText().toString())){
                    ToastUtils.makeText(RegisterActivity.this,"请输入手机号！");
                } else {
                    sendSMS(et_tel.getText().toString());
                }

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.hiddenKeyboard(RegisterActivity.this);
                String tel = et_tel.getText().toString();
                String sms = et_sms.getText().toString();
                String pwd = et_pwd.getText().toString();
                if(StringUtil.isBlank(tel)){
                    ToastUtils.makeText(RegisterActivity.this,"请输入手机号！");
                } else if(StringUtil.isBlank(sms)){
                    ToastUtils.makeText(RegisterActivity.this,"请输入短信验证码");
                } else if(StringUtil.isBlank(pwd)){
                    ToastUtils.makeText(RegisterActivity.this,"请输入密码");
                } else {
                    register(tel,sms,pwd);
                }
            }
        });
    }

    private void register(String tel, String sms, String pwd) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("phonenumber",tel);
        httpParams.put("phonetoken",sms);
        httpParams.put("password",pwd);
        httpParams.put("openid","");
        HttpUntilx.RequestInfo(Urlx.REGISTER,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getInt("status")==1){
                                Intent intent = new Intent(RegisterActivity.this, RegisterSuccessActivity.class);
                                startActivity(intent);
                                RegisterActivity.this.finish();
                            }
                            ToastUtils.makeText(RegisterActivity.this,"注册成功");
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
     * 发送短信
     * @param tel
     */
    private void  sendSMS(String tel){
        HttpParams httpParams = new HttpParams();
        httpParams.put("phonenumber",tel);
        HttpUntilx.RequestInfo(Urlx.SEND,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getInt("status")==0){
                                ToastUtils.makeText(RegisterActivity.this,jsonObject.getString("msg"));
                                RegisterActivity.this.finish();
                            }else {
                                ToastUtils.makeText(RegisterActivity.this,jsonObject.getString("msg"));
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
