package com.manage.rain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.model.HttpParams;
import com.manage.rain.R;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.Login;
import com.manage.rain.net.Netapi;
import com.manage.rain.until.Common;
import com.manage.rain.until.JSON2Class;
import com.manage.rain.until.StringUtil;
import com.manage.rain.until.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by wyf on 2018/1/20.
 */

public class LoginActivity extends  BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        final EditText et_tel = (EditText) findViewById(R.id.et_tel);
        final EditText et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StringUtil.isBlank(et_tel.getText().toString())){
                    ToastUtils.makeText(LoginActivity.this,"请输入账号/用户名/手机号");
                } else  if(StringUtil.isBlank(et_pwd.getText().toString())){
                    ToastUtils.makeText(LoginActivity.this,"请输入密码");
                } else {
                    HttpParams params = new HttpParams();
                    params.put("phonenumber", et_tel.getText().toString());//15252525252
                    params.put("password", et_pwd.getText().toString());//123456
                    Common.showLoading(mActivity);
                    Netapi.requestParams(Urlx.LOGIN,params).doOnSubscribe(new Action0() {
                        @Override
                        public void call() {

                        }
                    }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            Common.calcelLoading();
                            Login login = JSON2Class.fromJson(s,Login.class);
                            if(login.getStatus()==1){
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                ToastUtils.makeText(LoginActivity.this,login.getMsg()+"");
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            Common.calcelLoading();
                        }
                    });
                }

            }
        });
    }
}
