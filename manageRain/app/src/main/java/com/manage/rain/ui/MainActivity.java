package com.manage.rain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.manage.rain.R;
import com.manage.rain.adapter.GridViewAdapter;
import com.manage.rain.model.GridInfo;
import com.manage.rain.ui.comments.CommentsActivity;
import com.manage.rain.ui.order.OrderActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2018/1/3.
 */

public class MainActivity extends BaseActivity {
    //定义图标数组
    private Integer[] imageRes1 = {
            R.mipmap.icon_ddgl_wcldd,
            R.mipmap.icon_ddgl_psz,
            R.mipmap.icon_ddgl_xyz,
            R.mipmap.icon_ddgl_shz,
            R.mipmap.icon_ddgl_wzfdd,
            R.mipmap.icon_ddgl_qscg,
            R.mipmap.icon_ddgl_yhpj};
    private String[] arrStr ={
            "未处理订单","派送中","洗衣中",
            "送回中","未支付订单","签收成功","瑕疵衣物","用户评价"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("订单管理");
        setBack();

        GridView gridview = (GridView)findViewById(R.id.gridview1);
        int length = imageRes1.length;
        //生成动态数组，并且转入数据
        final List<GridInfo> lstImageItem = new ArrayList<GridInfo>();
        for (int i = 0; i < length; i++) {
            GridInfo gridInfo = new GridInfo();
            gridInfo.setId(imageRes1[i]);//添加图像资源的ID
            gridInfo.setName(arrStr[i]);//按序号做ItemText
            lstImageItem.add(gridInfo);
        }
        final GridViewAdapter gridViewAdapter = new GridViewAdapter(MainActivity.this,lstImageItem);
        gridview.setAdapter(gridViewAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for(int i=0;i<lstImageItem.size();i++){
                    if (position == i) {//当前选中的Item改变背景颜色
                        lstImageItem.get(i).setSelect(true);
                    } else {
                        lstImageItem.get(i).setSelect(false);
                    }
                }
                gridViewAdapter.notifyDataSetChanged();
                if(position==0){
                    //未处理订单
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("type","0");
                    startActivity(intent);
                } else if(position==1){
                    //派送中
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("type","1");
                    startActivity(intent);
                } else if(position==2){
                    //洗衣中
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("type","2");
                    startActivity(intent);
                } else if(position==3){
                    //送回中
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("type","3");
                    startActivity(intent);
                } else if(position==4){
                    //未支付订单
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("type","4");
                    startActivity(intent);
                }  else if(position==5){
                    //签收成功
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("type","5");
                    startActivity(intent);
                }   else if(position==6){
                    //瑕疵衣物
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("type","6");
                    startActivity(intent);
                }   else if(position==7){
                    Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                    startActivity(intent);
                }   else {
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}
