package com.example.com.xrecyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.xrecyclerview.R;

import java.util.List;


/**
 * Created by wangcheng on 2017/8/25.
 */

public class MenuAdapter extends BaseAdapter {
    List<SparseArray> menus;
    private Context mcontext;

    public MenuAdapter(Context context, List<SparseArray> menus){
        this.menus = menus;
        this.mcontext =context;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int i) {
        return menus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_gv_room, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.item_img);
            holder.icon_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.bottom_divider = (TextView) convertView.findViewById(R.id.tv_bottom_divider);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageResource((Integer) menus.get(position).get(1));
        holder.icon_name.setText((Integer) menus.get(position).get(2));
        holder.icon_name.setTextColor(Color.BLACK);
        holder.bottom_divider.setVisibility(View.GONE);

        return convertView;
    }

    private class ViewHolder {
        ImageView icon;
        TextView icon_name;
        TextView bottom_divider;
    }
}
