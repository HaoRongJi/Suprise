package com.bwie.MoNiJingDong.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.ui.fragment.FiveFragment;
import com.bwie.MoNiJingDong.ui.fragment.FourFragment;
import com.bwie.MoNiJingDong.ui.fragment.OneFragment;
import com.bwie.MoNiJingDong.ui.fragment.ThreeFragment;
import com.bwie.MoNiJingDong.ui.fragment.TwoFragment;
import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;

public class  MainActivity extends AppCompatActivity {




    private BottomTabBar mBottomTabBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
