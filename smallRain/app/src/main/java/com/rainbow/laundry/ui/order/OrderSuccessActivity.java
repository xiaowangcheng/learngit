package com.rainbow.laundry.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.rainbow.laundry.R;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.util.ViewUtils;


/**
 * Created by wyf on 2018/1/7.
 */

public class OrderSuccessActivity  extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_success);
        setBack();
        setTitle("下单成功");
        initView();
    }

    private void initView(){
        Button btn_chakan = (Button) findViewById(R.id.btn_chakan);
        Button btn_back = (Button) findViewById(R.id.btn_back);

        btn_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSuccessActivity.this, OrderStateQueryActivity.class);//OrderActivity
                intent.putExtra("orderId",getIntent().getStringExtra("orderId"));
                startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderSuccessActivity.this.finish();
            }
        });

    }
}
