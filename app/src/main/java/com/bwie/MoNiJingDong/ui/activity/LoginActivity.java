package com.bwie.MoNiJingDong.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.MoNiJingDong.R;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity {

    @BindView(R.id.mobile_et)
    EditText mobileEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.eye_btn)
    ImageView eyeBtn;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.duanxinyzm)
    TextView duanxinyzm;
    @BindView(R.id.xinyonghuzc)
    TextView xinyonghuzc;
    @BindView(R.id.finish_img)
    ImageView finishImg;

    /**
     * 设置是否全屏方法，封装在base基类
     *
     * @return
     */
    @Override
    public boolean getIsFullScreen() {
        return true;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void fail(String Msg) {

    }


    @OnClick(R.id.eye_btn)
    public void onEyeBtnClicked() {
    }

    @OnClick(R.id.login_btn)
    public void onLoginBtnClicked() {
    }

    @OnClick(R.id.duanxinyzm)
    public void onDuanxinyzmClicked() {
    }

    @OnClick(R.id.xinyonghuzc)
    public void onXinyonghuzcClicked() {
    }


    @OnClick(R.id.finish_img)
    public void onViewClicked() {
        finish();
    }
}
