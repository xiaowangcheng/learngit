package com.rainbow.laundry.ui.index.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.rainbow.laundry.ui.index.MainActivity;
import com.rainbow.laundry.ui.leftmanager.points.PointsManagerActivity;
import com.rainbow.laundry.ui.leftmanager.suggest.SuggestActivity;
import com.rainbow.laundry.ui.price.PriceListActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wyf on 2017/1/10.
 */

public class MainFragment extends Fragment {

    //定义图标数组
    private int[] imageRes = {R.mipmap.icon_home_ydx, R.mipmap.icon_home_xy,
            R.mipmap.icon_home_xx, R.mipmap.icon_home_xjf, R.mipmap.icon_home_clqx};

    //定义图标下方的名称数组
    private String[] name = { "洗衣", "洗鞋", "洗家纺", "窗帘清洗","一袋洗"};

    //定义图标数组
    private int[] imageRes1 = {R.mipmap.pic_home_fwjs, R.mipmap.pic_home_jfhl, R.mipmap.pic_home_kfdh, R.mipmap.pic_home_yhpj};

    //定义图标下方的名称数组
    private String[] name1 = { "服务介绍","客服电话", "用户评价", "积分有利"
    };
    //定义图标下方的名称数组
    private String[] name2 = {"优惠、优速、优质", "为您排忧解惑", "在乎每一位客户", "0 元好物在这里"
    };
    private ImageView image;
    View mContain;
    private RollPagerView mRollViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mContain == null) {
            mContain = inflater.inflate(R.layout.fragment_main, container, false);

            initView(mContain,savedInstanceState);
            setGridView(mContain);
            setGridView1(mContain);
        }
        ViewGroup parent = (ViewGroup) mContain.getParent();
        if (parent != null) {
            parent.removeView(mContain);
        }
        return mContain;
    }

    private class TestLoopAdapter extends LoopPagerAdapter
    {
        private int[] imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,};  // 本地图片
        private int count = imgs.length; // banner上图片的数量

        public TestLoopAdapter(RollPagerView viewPager)
        {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position)
        {
            final int picNo = position + 1;
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity(), "点击了第" + picNo + "张图片", Toast.LENGTH_SHORT).show();
                }

            });

            return view;
        }

        @Override
        public int getRealCount()
        {
            return count;
        }

    }

    private void initView(View view, Bundle savedInstanceState){
        image = view.findViewById(R.id.image);

        mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        mRollViewPager.setAnimationDurtion(500);//设置切换时间
        mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager)); //设置适配器
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.WHITE, Color.GRAY));// 设置圆点指示器颜色

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ds = (MainActivity) getActivity();
                ds.openSlder();
            }
        });
        mRollViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    private void  setGridView(View view){
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        int length = imageRes.length;

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageRes[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
                lstImageItem,//数据来源
                R.layout.home_gridview_item1,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.img_shoukuan, R.id.txt_shoukuan});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),name[position],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(),PriceListActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);

            }
        });
    }

    private void  setGridView1(View view){
        GridView gridview = (GridView) view.findViewById(R.id.gridview1);
        int length = imageRes1.length;
        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageRes1[i]);//添加图像资源的ID
            map.put("title0", name1[i]);//按序号做ItemText
            map.put("title1", name2[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
                lstImageItem,//数据来源
                R.layout.home_gridview_item2,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "title0", "title1"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.image, R.id.tv_title0, R.id.tv_title1});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 if(position==0){

                 } else if (position ==1){
                     final String tel ="400-818-6868";
                     Common.showDoubleDialog(getActivity(), "拨打客服电话", tel, new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             call(tel);
                             Common.calcelDialog();
                         }
                     });
                 } else if (position ==2){
                    Intent intent = new Intent(getActivity(), SuggestActivity.class);
                     startActivity(intent);
                 } else if (position ==3){
                     Intent intent = new Intent(getActivity(), PointsManagerActivity.class);
                     startActivity(intent);
                 }
            }
        });
    }

    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onClickSlider(View view){
        Toast.makeText(getActivity(),"11",Toast.LENGTH_LONG).show();
    }
}
