package com.rainbow.laundry.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.R;


/**
 *  Created by wyf on 2018/1/11.
 */

public class RegisterSuccessActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);
        setBack();
        setTitle("注册");
        Button btn_register_ok = (Button) findViewById(R.id.btn_register_ok);
        btn_register_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterSuccessActivity.this.finish();
            }
        });

    }
}
