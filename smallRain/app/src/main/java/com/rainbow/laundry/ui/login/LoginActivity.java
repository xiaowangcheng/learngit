package com.rainbow.laundry.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.config.ConstantsRain;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.Login;
import com.rainbow.laundry.modle.good.Pro_good;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.ui.index.MainActivity;
import com.rainbow.laundry.ui.order.OrderAddressSelectActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.PreferenceUtils;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.util.ViewUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import rx.functions.Action1;


/**
 * Created by wyf on 2018/1/3.
 */

public class LoginActivity extends BaseActivity {

    private  boolean isRecember=true;
    private UserInfo userInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
    }

    private void initView(){
        mTencent = MyUserInfo.initQQ(this);
        //创建微信api并注册到微信
        ConstantsRain.wx_api = WXAPIFactory.createWXAPI(LoginActivity.this, ConstantsRain.APP_ID, true);
        ConstantsRain.wx_api.registerApp(ConstantsRain.APP_ID);

        scope = "all";
        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onComplete(Object value) {
                // TODO Auto-generated method stub

                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;

                    String msg = jo.getString("msg");

                    System.out.println("json=" + String.valueOf(jo));

                    System.out.println("msg="+msg);
                    if (!StringUtil.isBlank(jo.getString("openid"))) {
                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                        if(mTencent.getQQToken() == null){
                            System.out.println("qqtoken == null");
                        }
                        userInfo = new UserInfo(LoginActivity.this, mTencent.getQQToken());
                        userInfo.getUserInfo(userInfoListener);
                    } else{

                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };
        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onComplete(Object arg0) {
                // TODO Auto-generated method stub
                if(arg0 == null){
                    return;
                }
                try {
                    Common.calcelLoading();
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");

                    MyUserInfo.setUserId(mTencent.getQQToken().getOpenId() );
                    MyUserInfo.setBalance("0.00");
                    String figureurl =jo.getString("figureurl");
                    MyUserInfo.setHeaderUrl(figureurl);
                    String token = mTencent.getQQToken().getAccessToken();
                    MyUserInfo.setToken(token);
                    MyUserInfo.setPhone("");

                    Intent intent=null;
                    if(!StringUtil.isBlank(getIntent().getStringExtra("path"))){
                        if(getIntent().getStringExtra("path").equals("OrderAddressSelectActivity")){
                            intent = new Intent(LoginActivity.this, OrderAddressSelectActivity.class);
                            ArrayList<Pro_good> orderList =(ArrayList<Pro_good>) getIntent().getSerializableExtra("orderList");
                            intent.putExtra("orderList", (Serializable) orderList);
                            startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                            LoginActivity.this.finish();
                        }
                    } else {
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }


                } catch (Exception e) {
                    // TODO: handle exception
                }


            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };


        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);
        final EditText et_tel = (EditText) findViewById(R.id.et_tel);
        if(!StringUtil.isBlank(PreferenceUtils.getString("account",""))){
            et_tel.setText(PreferenceUtils.getString("account",""));
        }

        final EditText et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.hiddenKeyboard(LoginActivity.this);
                if(StringUtil.isBlank(et_tel.getText().toString())){
                    ToastUtils.makeText(LoginActivity.this,"请输入手机号！");
                } else if(StringUtil.isBlank(et_pwd.getText().toString())){
                    ToastUtils.makeText(LoginActivity.this,"请输入密码！");
                } else {
                    login(et_tel.getText().toString(),
                            et_pwd.getText().toString());
                }

                //login(et_tel.getText().toString(),et_pwd.getText().toString());
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        et_tel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (et_tel.getCompoundDrawables()[2] != null) {

                        boolean touchable = event.getX() > (et_tel.getWidth() - et_tel.getTotalPaddingRight())
                                && (event.getX() < ((et_tel.getWidth() - et_tel.getPaddingRight())));

                        if (touchable) {
                            //里面写上自己想做的事情，也就是DrawableRight的触发事件
                            if(isRecember){
                                et_tel.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.clients_icon_logins_zh),null,
                                        getResources().getDrawable(R.mipmap.clients_icon_logins_jz_click),null);
                            } else {
                                et_tel.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.clients_icon_logins_zh),null,
                                        getResources().getDrawable(R.mipmap.clients_icon_logins_jz_normal),null);
                            }
                            isRecember = !isRecember;
                            return  true;
                            //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        }
                    }
                }
                return false;
            }
        });
        TextView login_wecat = (TextView) findViewById(R.id.login_wecat);
        final TextView login_qq = (TextView) findViewById(R.id.login_qq);

        login_wecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建微信api并注册到微信

            }
        });
        login_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mTencent.isSessionValid()) {
                    mTencent.login(LoginActivity.this, scope, loginListener);
                }
            }
        });
        login_wecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                ConstantsRain.wx_api.sendReq(req);
            }
        });
    }

    private void fasfs(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        //api.sendReq(req);
    }



    private void login(final String phone,String pwd) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("phonenumber",phone);//"18720147511" "15958273487"  18720147511
        httpParams.put("password",pwd);//931201  "123456"  "123456"
        httpParams.put("openid","");
        httpParams.put("registrationId", JPushInterface.getRegistrationID(this));
        HttpUntilx.RequestInfo(Urlx.LOGIN,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getInt("status")==1){
                                Login login =  JSON2Class.fromJson(jsonObject.getString("data"), Login.class);
                                MyUserInfo.setUserId(login.getId());
                                MyUserInfo.setBalance(String.valueOf(login.getBalance()));
                                MyUserInfo.setHeaderUrl(login.getImgurl());
                                MyUserInfo.setToken(login.getToken());
                                MyUserInfo.setPhone(login.getPhoneNumber());

                                MyUserInfo.setIntegral(login.getIntegral());
                                if(isRecember){
                                    PreferenceUtils.set("account",phone);
                                }
                                Intent intent=null;
                                if(!StringUtil.isBlank(getIntent().getStringExtra("path"))){
                                    if(getIntent().getStringExtra("path").equals("OrderAddressSelectActivity")){
                                        intent = new Intent(LoginActivity.this, OrderAddressSelectActivity.class);
                                        ArrayList<Pro_good> orderList =(ArrayList<Pro_good>) getIntent().getSerializableExtra("orderList");
                                        intent.putExtra("orderList", (Serializable) orderList);
                                        startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                                        LoginActivity.this.finish();
                                    }
                                } else {
                                    LoginActivity.this.finish();
                                }
                                //ARouter.getInstance().build("/main/companyedit").withSerializable("companyBean", companyBean).navigation();
                            } else {
                                ToastUtils.makeText(mActivity,jsonObject.getString("msg"));
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


    private Tencent mTencent;
    private String scope;
    private IUiListener loginListener;
    private IUiListener userInfoListener; //获取用户信息监听器

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.RESULT_LOGIN) {
                Common.showLoading(mActivity);
                Tencent.handleResultData(data, loginListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }else if(resultCode==11){
            data.getStringExtra("");
        }
    }
}
