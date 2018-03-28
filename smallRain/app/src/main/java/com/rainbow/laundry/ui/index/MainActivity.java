package com.rainbow.laundry.ui.index;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rainbow.laundry.R;
import com.rainbow.laundry.config.MyUserInfo;
import com.rainbow.laundry.receicer.LocalBroadcastManager;
import com.rainbow.laundry.receicer.MessageReceiver;
import com.rainbow.laundry.ui.BaseActivity;
import com.rainbow.laundry.ui.index.fragment.MainFragment;
import com.rainbow.laundry.ui.index.fragment.OrderNoCompletedFragment;
import com.rainbow.laundry.ui.leftmanager.address.AddressManagerActivity;
import com.rainbow.laundry.ui.leftmanager.coupon.CouponActivity;
import com.rainbow.laundry.ui.leftmanager.message.MessageActivity;
import com.rainbow.laundry.ui.leftmanager.points.PointsManagerActivity;
import com.rainbow.laundry.ui.leftmanager.recharge.RechargeActivity;
import com.rainbow.laundry.ui.leftmanager.suggest.SuggestActivity;
import com.rainbow.laundry.ui.login.LoginActivity;
import com.rainbow.laundry.ui.order.OrderStateQueryActivity;
import com.rainbow.laundry.util.Common;
import com.rainbow.laundry.util.GlideCircleTransform;
import com.rainbow.laundry.util.StringUtil;
import com.rainbow.laundry.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTextMessage;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private boolean isFisrt=true;

    private DrawerLayout container = null;  //主内容窗格
    private LinearLayout contentleft = null;    //侧滑内容菜单
    private ActionBarDrawerToggle drawerToggle = null;  //侧滑状态监听管理器
    private TextView movieBtn, tvBtn;
    private List<TextView> btnList = new ArrayList<TextView>();
    public static boolean isForeground = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (DrawerLayout)findViewById(R.id.Main_DrawerLayout);
        contentleft = (LinearLayout)findViewById(R.id.Main_LeftLayout);

//      //使主按键能够关联侧滑菜单
//      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//      getSupportActionBar().setHomeButtonEnabled(true);
        gestureDetector=new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //判断是否是右滑
                float offsetX=e2.getX()-e1.getX();
                float offsetY=e2.getY()-e1.getY();
                if ((offsetX > 0 && offsetX > Math.abs(offsetY)) || (velocityX > 0 && velocityX > Math.abs(velocityY))) {
                    if(e1.getY()<495 &&  isFisrt){
                        return false;
                    } else {
                        setView();
                    }

                    return true;//返回true表示我们在dispatchTouchEvent中，就不把事件传递到子控件中了
                }
                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }
        });
        findById();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.replace(R.id.content, new MainFragment());
        ft.commit();
        contentleft.setOnClickListener(null);



        registerMessageReceiver();
        Bundle bundle = getIntent().getBundleExtra("EXTRA_BUNDLE");
        if(bundle!=null){
            String orderId = bundle.getString("orderId");
            Intent intent = new Intent(MainActivity.this, OrderStateQueryActivity.class);
            intent.putExtra("orderId",orderId);
            startActivityForResult(intent,11);
        }

        isForeground = true;
        JPushInterface.onResume(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setView();
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMessageReceiver!=null){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        }


    }

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "indentid";


    protected void onPause() {
        super.onPause();
        isForeground = false;
        JPushInterface.onPause(this);
    }

    TextView tv_tel,tv_exit;
    ImageView image_head;
    private void setView(){
        if(!StringUtil.isBlank(MyUserInfo.getToken())){
            tv_tel.setText(MyUserInfo.getPhone());
            Glide.with(this).load(MyUserInfo.getHeaderUrl(""))
                    .transform(new GlideCircleTransform(this))
                    .placeholder(R.mipmap.default_user_circle)
                    .error(R.mipmap.default_user_circle).into(image_head);

            findViewById(R.id.lin_exit).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_exit_line).setVisibility(View.VISIBLE);
        } else {
            tv_tel.setText("您还未登录，立即登录");
            Glide.with(this).load(Common.resourceIdToUri(MainActivity.this,R.mipmap.pic_leftbar_head))
                    .transform(new GlideCircleTransform(this))
                    .placeholder(R.mipmap.default_user_circle)
                    .error(R.mipmap.default_user_circle).into(image_head);
            findViewById(R.id.lin_exit).setVisibility(View.GONE);
            findViewById(R.id.tv_exit_line).setVisibility(View.GONE);
            tv_tel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity,LoginActivity.class);//OrderActivity

                    startActivity(intent);
                }
            });
        }
    }

    private void findById() {
        movieBtn = (TextView) this.findViewById(R.id.movie_btn);
        tvBtn = (TextView) this.findViewById(R.id.tv_btn);

        movieBtn.setOnClickListener(this);
        tvBtn.setOnClickListener(this);

        btnList.add(movieBtn);
        btnList.add(tvBtn);
        TextView tv_recharge = (TextView) findViewById(R.id.tv_recharge);//充值
        LinearLayout lin_coupon = (LinearLayout) findViewById(R.id.lin_coupon);//优惠券
        LinearLayout lin_point_manager = (LinearLayout) findViewById(R.id.lin_point_manager);//积分管理
        LinearLayout lin_address = (LinearLayout) findViewById(R.id.lin_address);//地址管理

        LinearLayout lin_message = (LinearLayout) findViewById(R.id.lin_message);//我的消息
        LinearLayout lin_tel = (LinearLayout) findViewById(R.id.lin_tel);//客服电话
        LinearLayout  lin_suggest = (LinearLayout) findViewById(R.id.lin_suggest);//意见反馈


        image_head = (ImageView) findViewById(R.id.image_head);
        tv_tel = (TextView) findViewById(R.id.tv_tel);
        tv_exit = (TextView) findViewById(R.id.tv_exit);


        tv_recharge.setOnClickListener(this);

        lin_coupon.setOnClickListener(this);
        lin_point_manager.setOnClickListener(this);
        lin_address.setOnClickListener(this);
        lin_message.setOnClickListener(this);
        lin_tel.setOnClickListener(this);
        lin_suggest.setOnClickListener(this);
        tv_exit.setOnClickListener(this);
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isOpen = container.isDrawerVisible(contentleft);    //判断侧滑菜单是否可见

        return super.onPrepareOptionsMenu(menu);
    }
    private GestureDetector gestureDetector;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(gestureDetector.onTouchEvent(event)){
            //打开侧边栏
            container.openDrawer(GravityCompat.START);
            return false;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //官方建议用if(drawerToggle.onOptionsItemSelected(item))的方法获取主按键的事件
        if(drawerToggle.onOptionsItemSelected(item)) {
            invalidateOptionsMenu();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Intent intent = null;
        switch (v.getId()) {
            case R.id.movie_btn:
                movieBtn.setTextColor(getResources().getColor(R.color.btn_bule));
                tvBtn.setTextColor(getResources().getColor(R.color.gray));
                movieBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.mipmap.icon_tab_main_select),null,null);
                tvBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.mipmap.icon_tab_order_no_select),null,null);
                MainFragment mainFragment = new MainFragment();
                ft.replace(R.id.content, mainFragment);
                isFisrt = true;
                break;
            case R.id.tv_btn:
                if(!StringUtil.isBlank(MyUserInfo.getToken())){
                    movieBtn.setTextColor(getResources().getColor(R.color.gray));
                    tvBtn.setTextColor(getResources().getColor(R.color.btn_bule));
                    movieBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.mipmap.icon_tab_main_not_select),null,null);
                    tvBtn.setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(R.mipmap.icon_tab_order_select),null,null);
                    ft.replace(R.id.content, new OrderNoCompletedFragment());
                    isFisrt = false;
                } else {
                    intent = new Intent(mActivity,LoginActivity.class);//OrderActivity
                    startActivity(intent);
                }

                break;

            case R.id.tv_recharge://充值
                if(!StringUtil.isBlank(MyUserInfo.getToken())){
                    intent = new Intent(MainActivity.this, RechargeActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(mActivity,LoginActivity.class);//OrderActivity
                    startActivity(intent);
                }

                break;
            case R.id.lin_coupon:// 优惠券
                if(!StringUtil.isBlank(MyUserInfo.getToken())){
                    intent = new Intent(MainActivity.this, CouponActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(mActivity,LoginActivity.class);//OrderActivity
                    startActivity(intent);
                }

                break;
            case  R.id.lin_point_manager://积分管理
                if(!StringUtil.isBlank(MyUserInfo.getToken())){
                    intent = new Intent(MainActivity.this, PointsManagerActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(mActivity,LoginActivity.class);//OrderActivity
                    startActivity(intent);
                }

                break;
            case R.id.lin_address://地址管理
                if(!StringUtil.isBlank(MyUserInfo.getToken())){
                    intent = new Intent(MainActivity.this, AddressManagerActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(mActivity,LoginActivity.class);//OrderActivity
                    startActivity(intent);
                }

                break;
            case R.id.lin_message://我的消息
                if(!StringUtil.isBlank(MyUserInfo.getToken())){
                    intent = new Intent(MainActivity.this, MessageActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(mActivity,LoginActivity.class);//OrderActivity
                    startActivity(intent);
                }

                break;
            case R.id.lin_tel://客服电话
                final String tel ="400-818-6868";
                Common.showDoubleDialog(mActivity, "拨打客服电话", tel, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        call(tel);
                        Common.calcelDialog();
                    }
                });
                break;
            case R.id.lin_suggest://意见反馈
                intent = new Intent(MainActivity.this, SuggestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_exit://退出
                Common.showDoubleDialog(mActivity, "温馨提示", "是否确定要退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ViewUtils.exit();
                        setView();
                        container.closeDrawer(GravityCompat.START);
                        Common.calcelDialog();
                    }
                });
                break;
            default:
                break;
        }
        ft.commit();
    }

    public void openSlder(){
        boolean isOpen = container.isDrawerVisible(contentleft);
        if(!isOpen){
            container.openDrawer(GravityCompat.START);
        }
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                ){
            Common.showDoubleDialog(mActivity, "温馨提示", "是否确定要退出", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LocalBroadcastManager.getInstance(MainActivity.this).
                            unregisterReceiver(mMessageReceiver);
                    ViewUtils.exit();
                    Common.calcelDialog();
                    MainActivity.this.finish();

                }
            });
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
