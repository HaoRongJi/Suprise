package com.bwie.MoNiJingDong.ui.activity;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.app.MyApplication;
import com.bwie.MoNiJingDong.entity.FailureEventBus;
import com.bwie.MoNiJingDong.entity.SuccessEventBus;
import com.bwie.MoNiJingDong.ui.fragment.FiveFragment;
import com.bwie.MoNiJingDong.ui.fragment.FourFragment;
import com.bwie.MoNiJingDong.ui.fragment.OneFragment;
import com.bwie.MoNiJingDong.ui.fragment.ThreeFragment;
import com.bwie.MoNiJingDong.ui.fragment.TwoFragment;
import com.bwie.MoNiJingDong.utils.ACache;
import com.bwie.MoNiJingDong.utils.NetState;
import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class  MainActivity extends AppCompatActivity {




    private BottomTabBar mBottomTabBar;
    protected Context mContext;
    protected ACache mACache;
    protected boolean mCheckNetWork = true; //默认检查网络状态
    View mTipView;
    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        NetState receiver = new NetState();
        IntentFilter filter = new IntentFilter();

        initTipView();
        mContext = this;
        this.mACache = ACache.get(mContext);
        //MyApplication.addActivity(this);
        EventBus.getDefault().register(this);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(receiver, filter);
        receiver.onReceive(this, null);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initData() {

        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels=metrics.widthPixels;
        int heightPixels=metrics.heightPixels;

        if (widthPixels==1080&&heightPixels==1920){

            mBottomTabBar.setImgSize(60,60);

        }else if(widthPixels==480&&heightPixels==800){

            mBottomTabBar.setImgSize(32,32);

        }else{

            mBottomTabBar.setImgSize(60,60);

        }

        mBottomTabBar.init(getSupportFragmentManager())
                //.setFontSize(0)
                .addTabItem("首页", R.drawable.home, OneFragment.class)
                .addTabItem("分类", R.drawable.fenlei, TwoFragment.class)
                .addTabItem("发现", R.drawable.find, ThreeFragment.class)
                .addTabItem("购物车", R.drawable.cart, FourFragment.class)
                .addTabItem("我的", R.drawable.mine, FiveFragment.class);






    }

    private void initView() {

        ImmersionBar.with(this).init();

        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Success(SuccessEventBus successEventBus) {

        if (successEventBus.getSuccessNum() == 1) {

            Toast.makeText(this, "当前连接网络为4G网络，请注意流量使用", Toast.LENGTH_SHORT).show();
            if (mTipView != null && mTipView.getParent() != null) {
                mWindowManager.removeView(mTipView);
            }

        }else if (successEventBus.getSuccessNum() == 2){

            Toast.makeText(this, "已连接WIFI网络", Toast.LENGTH_SHORT).show();
            if (mTipView != null && mTipView.getParent() != null) {
                mWindowManager.removeView(mTipView);
            }

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Failure(FailureEventBus failureEventBus) {

        if (failureEventBus.getFailureNum() == 0) {

            if (mTipView.getParent() == null) {
                mWindowManager.addView(mTipView, mLayoutParams);
            }

        }

    }


    private void initTipView() {
        LayoutInflater inflater = getLayoutInflater();
        mTipView = inflater.inflate(R.layout.layout_network_tip, null); //提示View布局
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }
}
