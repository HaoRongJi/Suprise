package com.bwie.MoNiJingDong.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.bwie.MoNiJingDong.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hao.base.base.BaseActivity;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity implements Animation.AnimationListener{


    @BindView(R.id.first_img)
    SimpleDraweeView firstImg;

    @Override
    protected void initData() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(this);
        firstImg.startAnimation(alphaAnimation);
    }

    @Override
    public boolean getIsFullScreen() {
        return true;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
