package com.manage.rain.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manage.rain.R;
import com.manage.rain.until.ViewUtils;

import java.util.List;

/**
 * Created by wyf on 2018/1/3.
 */

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.DialogListViewHolder> {
    protected Context mContext;
    protected List<String> mDatas;
    protected LayoutInflater mInflater;
    protected int itemTextColor,lastitemTextColor,titleTextColor,type;
    public interface OnItemClickLister {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLister mOnItemClickLister;

    public void setOnItemClickLister(OnItemClickLister lister) {
        this.mOnItemClickLister = lister;
    }

    public DialogListAdapter(List<String> mDatas, int itemTextColor, int lastitemTextColor, int titleTextColor, int type, Context mContext) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.itemTextColor=itemTextColor;
        this.lastitemTextColor=lastitemTextColor;
        this.titleTextColor=titleTextColor;
        this.type=type;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DialogListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= mInflater.inflate(R.layout.item_dialog_list,parent,false);
        DialogListViewHolder viewHolder= new DialogListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DialogListViewHolder holder, int position) {
        String txt=mDatas.get(position);
        if(1==type){
            if((0 != itemTextColor)){
                holder.item_dialog_tv.setTextColor(ViewUtils.getColor(mContext,itemTextColor));
            }
            if(0!=titleTextColor&&position==0){
                holder.item_dialog_tv.setTextColor(ViewUtils.getColor(mContext,titleTextColor));
            }
            if(0!=lastitemTextColor&&position==mDatas.size()-1){
                holder.item_dialog_tv.setTextColor(ViewUtils.getColor(mContext,lastitemTextColor));
            }
        }else {
            if((0 != itemTextColor)){
                holder.item_dialog_tv.setTextColor(ViewUtils.getColor(mContext,itemTextColor));
            }
            if(0!=lastitemTextColor&&position==mDatas.size()-1){
                holder.item_dialog_tv.setTextColor(ViewUtils.getColor(mContext,lastitemTextColor));
            }
        }
        holder.item_dialog_tv.setText(txt);
        setUpItemEvent(holder);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 添加数据到指定位置
     * @param pos 数据添加的位置
     * @param obj 数据
     */
    public  void addData(int pos,String obj){
        mDatas.add(obj);
        notifyItemInserted(pos);
    }

    /**
     * 删除指定位置数据
     * @param pos
     */
    public void deleteData(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    /**
     * 给item添加监听
     * @param holder
     */
    protected  void setUpItemEvent(final DialogListViewHolder holder){
        if(mOnItemClickLister!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition=holder.getLayoutPosition();
                    mOnItemClickLister.onItemClick(holder.itemView,layoutPosition);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition=holder.getLayoutPosition();
                    mOnItemClickLister.onItemLongClick(holder.itemView,layoutPosition);
                    return false;
                }
            });
        }
    }
    class DialogListViewHolder extends RecyclerView.ViewHolder{
        TextView item_dialog_tv;
        public DialogListViewHolder(View itemView) {
            super(itemView);
            item_dialog_tv=(TextView)itemView.findViewById(R.id.item_dialog_tv);
        }
    }
}
