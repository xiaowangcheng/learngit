package com.manage.rain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manage.rain.R;
import com.manage.rain.model.GridInfo;

import java.util.List;

/**
 * Created by wyf on 2018/1/3.
 */

public class GridViewAdapter  extends BaseAdapter

    {

    private List<GridInfo> mList =null;

    private Context mContext;

    public GridViewAdapter(Context context,List<GridInfo> list) {

        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public GridInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChildHolderOne holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.girdview_item, parent, false);
            holder = new ChildHolderOne();
            holder.image = convertView.findViewById(R.id.img_shoukuan);
            holder.tvTitle = (TextView)convertView.findViewById(R.id.txt_shoukuan);

            convertView.setTag(holder);
        } else {
            holder = (ChildHolderOne) convertView.getTag();
        }
        final GridInfo gridInfo = mList.get(position);
        if(gridInfo.isSelect() == true){
            convertView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg_select));
        } else {
            convertView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg_no_select));
        }
        final String number = gridInfo.getName();
        holder.tvTitle.setText(number);
        holder.image.setImageResource(gridInfo.getId());
        return convertView;
    }

    class ChildHolderOne {
        ImageView image;
        TextView tvTitle;

    }
}
