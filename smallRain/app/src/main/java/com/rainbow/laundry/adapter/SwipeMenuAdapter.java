package com.rainbow.laundry.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainbow.laundry.modle.message.MessageModel;
import com.rainbow.laundry.util.DateUtil;
import com.rainbow.laundry.R;

/**
 * Created by wyf on 2018/1/5.
 */

public class SwipeMenuAdapter extends ListBaseAdapter<MessageModel> {

    private Activity mcontext;
    public SwipeMenuAdapter(Activity context) {
        super(context);
        this.mcontext =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.message_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        View contentView = holder.getView(R.id.swipe_content);
        Button btnDelete = holder.getView(R.id.btnDelete);
        TextView tv_state = holder.getView(R.id.tv_state);//订单状态
        TextView tv_detial = holder.getView(R.id.tv_detial);//订单状态
        TextView tv_time = holder.getView(R.id.tv_time);
        tv_state.setText(mDataList.get(position).getMessageTitle());
        tv_detial.setText(mDataList.get(position).getMessageContent());
        tv_time.setText(DateUtil.stampToDate(mDataList.get(position).getAddTime()));
        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
       //((SwipeMenuView)holder.itemView).setIos(false).setLeftSwipe(position % 2 == 0 ? true : false);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(position);
                }
            }
        });

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( mcontext, OrderStateQueryActivity.class);//OrderActivity
//                intent.putExtra("orderId",mDataList.get(position).getIndentId());
//                mcontext.startActivityForResult(intent, ViewUtils.NORMAL_JUMP);
                Log.d("TAG", "onClick() called with: v = [" + v + "]");
            }
        });

//        //置顶：
//        btnTop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null!=mOnSwipeListener){
//                    mOnSwipeListener.onTop(position);
//                }
//
//            }
//        });
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

        void onTop(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }
}
