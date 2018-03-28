package com.rainbow.laundry.ui.price;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.rainbow.laundry.R;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.modle.good.Pro_good;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.ui.login.LoginActivity;
import com.rainbow.laundry.ui.order.OrderAddressSelectActivity;
import com.rainbow.laundry.ui.price.fragment.WashBagFragment;
import com.rainbow.laundry.ui.price.fragment.WashClotheFragment;
import com.rainbow.laundry.ui.price.fragment.WashCurtainsFragment;
import com.rainbow.laundry.ui.price.fragment.WashShoesFragment;
import com.rainbow.laundry.ui.price.fragment.WashTextileFragment;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.view.NumImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyf on 2018/1/6.
 */

public class PriceListActivity extends BaseActivity {

    PagerSlidingTabStrip pst;
    ViewPager viewPager;
    ArrayList<Fragment> fragments;
    NumImageView image=null;
    //声明pst的标题
    String[] titles  = {"洗衣","洗鞋","洗家纺","洗窗帘","袋洗"};
    TextView tv_price=null;
    private double sum_price=0;
    private List<Pro_good> orderList = new ArrayList<Pro_good>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_list);
        setBack();
        setTitle("价目表");
        pst= (PagerSlidingTabStrip) findViewById(R.id.pst);

        viewPager= (ViewPager) findViewById(R.id.pager);

        fragments = new ArrayList<>();
        WashClotheFragment washClotheFragment = new WashClotheFragment();//洗衣
        WashShoesFragment washShoesFragment = new WashShoesFragment();//洗鞋
        WashTextileFragment washTextileFragment = new WashTextileFragment();//洗家纺
        WashCurtainsFragment washCurtainsFragment = new WashCurtainsFragment();//洗窗帘
        WashBagFragment washBagFragment = new WashBagFragment();//袋洗

        //添加fragment到集合中时注意顺序
        fragments.add(washClotheFragment);
        fragments.add(washShoesFragment);
        fragments.add(washTextileFragment);
        fragments.add(washCurtainsFragment);
        fragments.add(washBagFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyPagerAdapter(fragmentManager,titles,fragments));
        viewPager.setCurrentItem(getIntent().getIntExtra("index",0));
        //当ViewPager的onPagerChangeListener回调时，PagerSlidingTabStrip也一起随之变动
        //具体做法都已封装到了PagerSlidingTabStrip.setViewPager()方法里，使用时调用实例如下
        pst.setViewPager(viewPager);
        pst.setTextSize(30);


        tv_price = (TextView) findViewById(R.id.tv_price);
        image = (NumImageView) findViewById(R.id.image);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Button btn_order = (Button) findViewById(R.id.btn_order);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StringUtil.isBlank(MyUserInfo.getToken())){
                    Intent intent = new Intent(mActivity,LoginActivity.class);//OrderActivity
                    intent.putExtra("path", "OrderAddressSelectActivity");
                    intent.putExtra("orderList", (Serializable) orderList);
                    startActivity(intent);
                } else {
                    if(orderList.size()==0){
                        ToastUtils.makeText(mActivity,"请还未选择要清洗的物件！");
                    }else {
                        Intent intent = new Intent(mActivity,OrderAddressSelectActivity.class);//OrderActivity
                        intent.putExtra("orderList", (Serializable) orderList);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void getOrder(Pro_good order){
        orderList.add(order);
        image.setNum(orderList.size());
        sum_price+=order.getPrice();
        tv_price.setText(sum_price + "");
    }

    /**
     * 自定义适配器
     */
    class MyPagerAdapter extends FragmentPagerAdapter {
        private String[] titles;
        ArrayList<Fragment> fragments;
        //根据需求定义构造方法，方便外部调用
        public MyPagerAdapter(FragmentManager fm, String[] titles, ArrayList<Fragment> fragments) {
            super(fm);
            this.titles = titles;
            this.fragments = fragments;
        }
        //设置每页的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
        //设置每一页对应的fragment
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        //fragment的数量
        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
