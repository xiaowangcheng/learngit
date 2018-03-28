package com.rainbow.laundry.ui.price.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
import com.rainbow.laundry.view.NumImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.functions.Action1;

/**
 * //洗窗帘
 * Created by wyf on 2018/1/6.
 */

public class WashCurtainsFragment extends Fragment {
    CommonRecyclerViewAdapter<Pro_good> commonRecyclerViewHolder;
    private   View view =null;
    private  RecyclerView mRecyclerView=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_wash_curtain, null);
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

    private void initView(){
        mRecyclerView = view.findViewById(R.id.recyclerview);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        requestServer();
    }

    private void setView(final List<Pro_good> list){

        commonRecyclerViewHolder = new CommonRecyclerViewAdapter<Pro_good>(getActivity(),list){

            @Override
            public void convert(CommonRecyclerViewHolder h, Pro_good entity, final int position) {
                NumImageView imageView = h.getView(R.id.image);
                h.setText(R.id.tv_price,"¥"+ StringUtil.formatMoney(String.valueOf(entity.getPrice()))+"／袋");
                h.setText(R.id.tv_mi,entity.getSpecify());
                h.setText(R.id.tv_shuxin1,entity.getRemark());
                h.setText(R.id.tv_shuxin2,entity.getSecondary());
                LinearLayout lin_view = h.getView(R.id.lin_view);
                if(position==list.size()-1){
                    lin_view.setVisibility(View.GONE);
                } else {
                    lin_view.setVisibility(View.VISIBLE);
                }
                if(list.get(position).getOrder_count()>0){
                    imageView.setNum(list.get(position).getOrder_count());
                }
                Glide.with(context)
                        .load(Urlx.Host+ entity.getImgurl())
                        .asBitmap()//资源是一个图片是才算成功，其他的都算解析失败。
                        .placeholder(R.drawable.img2)
                        .error(R.drawable.img2)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageView);
            }

            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.washcurtains_item;
            }
        };
        commonRecyclerViewHolder.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                int coun = list.get(position).getOrder_count() +1;
                list.get(position).setOrder_count(coun);
                PriceListActivity priceListActivity = (PriceListActivity) getActivity();
                priceListActivity.getOrder(list.get(position));
                commonRecyclerViewHolder.notifyItemChanged(position);
            }
        });
        mRecyclerView.setAdapter(commonRecyclerViewHolder);

        TextView tv_shuoming = view.findViewById(R.id.tv_shuoming);
        String str="<font color='#333333'>点击查看</font><font color='#23ACF2'>《彩虹清洗说明及不能提供清洗服务衣物列表》</font>";
        tv_shuoming.setTextSize(13);
        tv_shuoming.setText(Html.fromHtml(str));
    }

    private  void requestServer(){
        HttpParams httpParams = new HttpParams();
        httpParams.put("sort","4");
        httpParams.put("classes","41");
        Common.showLoading(getActivity());
        HttpUntilx.RequestInfo(Urlx.SERVER,httpParams)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Common.calcelLoading();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Pro_GoodsList washCurtain =
                                    JSON2Class.fromJson(s, Pro_GoodsList.class);
                            if(washCurtain.getStatus()==1){
                                List<Pro_good> lis = washCurtain.getData();
                                setView(lis);
                            }else {
                                ToastUtils.makeText(getActivity(),washCurtain.getMsg().toString());
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
