package com.rainbow.laundry.ui.price.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.okgo.model.HttpParams;
import com.rainbow.laundry.config.Urlx;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.ToastUtils;
import com.rainbow.laundry.R;
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
 * Created by wyf on 2018/1/11.
 */

public class WashBagFragment extends Fragment {
    private int number =0;
    View view =null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_wash_bag, null);
            requestServer();
        }else {
            //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    private  void requestServer(){
        HttpParams httpParams = new HttpParams();
        httpParams.put("sort","5");
        httpParams.put("classes","51");
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
                            if(washCurtain.getStatus()==1 && washCurtain.getData().size()>0){
                                  List<Pro_good> dataEntity = washCurtain.getData();
                                setView(dataEntity.get(0));
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

    private void setView(final Pro_good dataEntity){
        final NumImageView imageView = view.findViewById(R.id.image);
        TextView tv_mi = view.findViewById(R.id.tv_mi);//米
        TextView tv_price = view.findViewById(R.id.tv_price);//价格
        tv_mi.setText(dataEntity.getSpecify());
        tv_price.setText("¥"+ StringUtil.formatMoney(String.valueOf(dataEntity.getPrice()))+"/袋");

        TextView tv_shuoming = view.findViewById(R.id.tv_shuoming);
        String str="<font color='#333333'>点击查看</font><font color='#23ACF2'>《彩虹清洗说明及不能提供清洗服务衣物列表》</font>";
        tv_shuoming.setTextSize(13);
        tv_shuoming.setText(Html.fromHtml(str));
        Glide.with(getActivity())
                .load(Urlx.Host+ dataEntity.getImgurl())
                .asBitmap()//资源是一个图片是才算成功，其他的都算解析失败。
                .placeholder(R.drawable.img2)
                .error(R.drawable.img2)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
        if(number>0){
            imageView.setNum(number);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number+=1;
                imageView.setNum(number);
                PriceListActivity priceListActivity = (PriceListActivity) getActivity();
                priceListActivity.getOrder(dataEntity);
            }
        });
    }
}
