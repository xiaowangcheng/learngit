package com.manage.rain.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.manage.rain.R;
import com.manage.rain.config.Urlx;
import com.manage.rain.model.DefctsClothes;
import com.manage.rain.until.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangcheng on 2018/2/9.
 */

public class DefectsAdapter extends RecyclerView.Adapter<DefectsAdapter.HoldView>{

    private static final int ITEM_VIEW_TYPE_ITEM_left = 0;
    private static final int ITEM_VIEW_TYPE_ITEM_right = 1;
    private int mHeaderCount = 1;

    private Context context;
    private LayoutInflater inflater;
    private List<DefctsClothes.DataEntity> mData ;
    private List<Integer> list = new ArrayList<>();


    public  DefectsAdapter(Context ct,List<DefctsClothes.DataEntity> list){
        this.context = ct;
        this.mData =list;
        this.inflater = LayoutInflater.from(ct);


    }



    @Override
    public HoldView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.defects_item,parent,
                false);
        HoldView holdView = new HoldView(view,viewType);
        return holdView;
    }

    @Override
    public void onBindViewHolder(final HoldView holder, int position) {
        //http://www.honglonglong.com/?pic=.jpg
        final int dss =  holder.getAdapterPosition();
        DefctsClothes.DataEntity s = mData.get(dss);
        int dss1  =mData.get(dss).getHeight();
        //存在记录的高度时先Layout再异步加载图片
        if (mData.get(dss).getHeight() > 0) {
            ViewGroup.LayoutParams layoutParams = holder.item_img.getLayoutParams();
            layoutParams.height = mData.get(dss).getHeight();
        }
        holder.item_name.setText(mData.get(dss).getAbnormalIntro());
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        final int screenWidth = Common.getWindowheight((Activity) context)/2;

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width =screenWidth;
        params.height=screenWidth/2;
        //holder.lin_image.setLayoutParams(params);
        Bitmap tag = (Bitmap) holder.item_img.getTag();
        if(tag !=null){
            holder.item_img.setImageBitmap(tag);
        }
        Glide.with(context)
                .load(Urlx.Host+mData.get(position).getAbnormalImg())
                .asBitmap()//资源是一个图片是才算成功，其他的都算解析失败。
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>(screenWidth, screenWidth) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                            holder.item_img.setTag(resource);
                            holder.item_img.setImageBitmap(resource);
                        }
                    }
                });


    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_VIEW_TYPE_ITEM_left;
        } else {
            if(position%2 ==0){
                return ITEM_VIEW_TYPE_ITEM_left;
            } else {
                return ITEM_VIEW_TYPE_ITEM_right;
            }
        }
    }



    class   HoldView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView item_img;
        private TextView item_name;

        public HoldView(View view,int type){
            super(view);
            item_img = (ImageView)view.findViewById(R.id.item_img);
            item_name = (TextView)view.findViewById(R.id.item_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
