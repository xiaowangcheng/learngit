package com.manage.rain.ui.nodeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.manage.rain.R;
import com.manage.rain.ui.BaseActivity;
import com.manage.rain.ui.nodeal.fragment.OrderNoPaySortTimeFragment;

import java.util.ArrayList;

/**
 *
 *  Created by wyf on 2018/1/20.
 */

public class OrderUntreatedActivity extends BaseActivity {
    PagerSlidingTabStrip pst;
    ViewPager viewPager;
    ArrayList<Fragment> fragments;
    //声明pst的标题
    String[] titles  = {"时间排序"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setBack();
        setTitle("派送中");
        initView();
    }

    private void initView(){
        pst= (PagerSlidingTabStrip) findViewById(R.id.pst);
        viewPager= (ViewPager) findViewById(R.id.pager);
        fragments = new ArrayList<>();
        OrderNoPaySortTimeFragment washShoesFragment = new OrderNoPaySortTimeFragment();
        //添加fragment到集合中时注意顺序
        fragments.add(washShoesFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyPagerAdapter(fragmentManager,titles,fragments));
        viewPager.setCurrentItem(0);
        //当ViewPager的onPagerChangeListener回调时，PagerSlidingTabStrip也一起随之变动
        //具体做法都已封装到了PagerSlidingTabStrip.setViewPager()方法里，使用时调用实例如下
        pst.setViewPager(viewPager);
        pst.setTextSize(30);
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
