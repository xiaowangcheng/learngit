package com.rainbow.laundry.ui.price.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.R;
import com.rainbow.laundry.adapter.CommonRecyclerViewAdapter;
import com.rainbow.laundry.adapter.CommonRecyclerViewHolder;
import com.rainbow.laundry.modle.good.Pro_GoodsList;
import com.rainbow.laundry.modle.good.Pro_good;
import com.rainbow.laundry.ui.price.PriceListActivity;
import com.rainbow.laundry.util.HttpUntilx;
import com.rainbow.laundry.util.JSON2Class;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.view.MyDividerItemDecoration;
import com.rainbow.laundry.view.NumImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Action1;

/**
 * 洗家访
 * Created by wyf on 2018/1/6.
 */

public class WashTextileFragment extends BaseFragment {



    CommonRecyclerViewAdapter<HashMap<String,String>> commonRecyclerViewHolder;
    CommonRecyclerViewAdapter<Pro_good> commonRecyclerViewHolder1;
    private LinearLayout.LayoutParams params;
    private int index =0;
    private List<Pro_good> list =new ArrayList<Pro_good>();
    private  View view=null;
    private RecyclerView mRecyclerView1=null;
    private boolean[] isrequest={false,false};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null){
            view=inflater.inflate(R.layout.fragment_wash_textile, null);
            initView();
        }else {
            //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    private  void initView(){
        mRecyclerView1 = view.findViewById(R.id.recyclerview1);

        //设置布局管理器
        mRecyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        //设置分隔线
        //mRecyclerView1.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mRecyclerView1.addItemDecoration(new MyDividerItemDecoration(getActivity() , DividerItemDecoration.VERTICAL,5));
        mRecyclerView1.addItemDecoration(new MyDividerItemDecoration(getActivity() , DividerItemDecoration.HORIZONTAL,5));

        setTitleRecycler();
    }

    private void setTitleRecycler(){
        RadioGroup radioGroup =view.findViewById(R.id.rg);
        final RadioButton radioButton1= view.findViewById(R.id.rb1);
        final RadioButton radioButton2= view.findViewById(R.id.rb2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                if(R.id.rb1==radioGroup.getCheckedRadioButtonId()){
                    if(!isrequest[0]){
                        requestServer("3","31");
                    } else {
                        list.clear();
                        list.addAll(mapArrayList.get("31"));
                        setProRecycler();
                    }
                    isrequest[0]=true;
                    radioButton1.setTextColor(getResources().getColor(R.color.white));
                    radioButton1.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_blue));
                    radioButton2.setTextColor(getResources().getColor(R.color.gray));
                    radioButton2.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_white_gray));
                } else {
                    if(!isrequest[1]){
                        requestServer("3","32");
                    } else {
                        list.clear();
                        list.addAll(mapArrayList.get("32"));
                        setProRecycler();
                    }
                    isrequest[1]=true;
                    radioButton1.setTextColor(getResources().getColor(R.color.gray));
                    radioButton1.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_white_gray));
                    radioButton2.setTextColor(getResources().getColor(R.color.white));
                    radioButton2.setBackground(getResources().getDrawable(R.drawable.layout_btn_round_bg_blue));
                }
            }
        });

        requestServer("3","31");
        isrequest[0]=true;
    }

    private  void requestServer(String sort,final String classes){
        HttpParams httpParams = new HttpParams();
        httpParams.put("sort",sort);
        httpParams.put("classes",classes);
        Common.showLoading(getActivity());
        HttpUntilx.RequestInfo(Urlx.SERVER,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Common.calcelLoading();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Pro_GoodsList clotheAttribute =
                                    JSON2Class.fromJson(s, Pro_GoodsList.class);
                            if(clotheAttribute.getStatus()==1){
                                list.clear();
                                list.addAll(clotheAttribute.getData());
                                mapArrayList.put(classes,clotheAttribute.getData());
                                setProRecycler();
                            }else {
                                ToastUtils.makeText(getActivity(),clotheAttribute.getMsg().toString());
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


    private  void setProRecycler(){
          commonRecyclerViewHolder1 = new CommonRecyclerViewAdapter<Pro_good>(getActivity(),list){

            @Override
            public void convert(CommonRecyclerViewHolder h, Pro_good entity, final int position) {
                final  TextView  tv_pro_name = h.getView(R.id.tv_pro_name);
                h.setText(R.id.tv_pro_name,entity.getComboname());
                h.setText(R.id.tv_price, StringUtil.formatMoney(String.valueOf(entity.getPrice())));

                final NumImageView image   = h.getView(R.id.image);
                if(list.get(position).getOrder_count()>0){
                    image.setNum(list.get(position).getOrder_count());
                }
                Glide.with(context)
                        .load(Urlx.Host+ entity.getImgurl())
                        .asBitmap()//资源是一个图片是才算成功，其他的都算解析失败。
                        .placeholder(R.drawable.img2)
                        .error(R.drawable.img2)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                                image.setImageBitmap(resource);
                            }
                        });
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.pro_item;
            }
        };
        commonRecyclerViewHolder1.setOnRecyclerViewItemClickListener(
                new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        int coun = list.get(position).getOrder_count() +1;
                        list.get(position).setOrder_count(coun);
                        PriceListActivity priceListActivity = (PriceListActivity) getActivity();
                        priceListActivity.getOrder(list.get(position));
                        commonRecyclerViewHolder1.notifyItemChanged(position);
                    }
                });
        mRecyclerView1.setAdapter(commonRecyclerViewHolder1);
        commonRecyclerViewHolder1.notifyDataSetChanged();
    }
}
