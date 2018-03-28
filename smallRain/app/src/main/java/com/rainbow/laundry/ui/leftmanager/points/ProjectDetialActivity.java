package com.rainbow.laundry.ui.leftmanager.points;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.R;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.modle.IntegralDetail;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;

/**
 * Created by wangcheng on 2018/2/18.
 */

public class ProjectDetialActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detial);
        setTitle("商品详情");
        setBack();
        requestProductDetial();

    }

    private void requestProductDetial(){
        HttpParams httpParams = new HttpParams();
        httpParams.put("productId", getIntent().getIntExtra("orderId",0));
        httpParams.put("userId",MyUserInfo.getUserId(""));
        HttpUntilx.RequestInfo(Urlx.productDetail,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            IntegralDetail integralDetail =  JSON2Class.fromJson(s, IntegralDetail.class);
                            if(integralDetail.getStatus()==1){
                                ImageView image = (ImageView) findViewById(R.id.image);
                                Glide.with(ProjectDetialActivity.this)
                                        .load(Urlx.Host+integralDetail.getData().getProject().getProductImg())
                                        .asBitmap()//资源是一个图片是才算成功，其他的都算解析失败。
                                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                        .into(image);
                                TextView tv_detial = (TextView) findViewById(R.id.tv_detial);
                                TextView tv_jifen = (TextView) findViewById(R.id.tv_jifen);
                                TextView tv_amt = (TextView) findViewById(R.id.tv_amt);
                                tv_jifen.setText(integralDetail.getData().getProject().getProductIntegral()+"积分");
                                tv_amt.setText("¥"+String.valueOf(integralDetail.getData().getProject().getProductPrice()));
                                tv_amt.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
                                String[] sdd = integralDetail.getData().getProject().getProductIntroduce().split(" ");
                                String as = "";
                                for (int i=1;i<sdd.length+1;i++){
                                    as+=(i+"、"+sdd[i-1]) +"\n";
                                }
                                tv_detial.setText(as);
                                Button btn_submit = (Button) findViewById(R.id.btn_submit);
                                if(Double.parseDouble(MyUserInfo.getIntegral())<integralDetail.getData().getProject().getProductIntegral()){
                                    btn_submit.setEnabled(false);
                                    btn_submit.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_gray));
                                } else {
                                    btn_submit.setEnabled(true);
                                    btn_submit.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_blue));
                                }
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
}
