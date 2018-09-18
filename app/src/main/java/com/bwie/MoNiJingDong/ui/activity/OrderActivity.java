package com.bwie.MoNiJingDong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.bwie.MoNiJingDong.R;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseMvpActivity {

    @BindView(R.id.toShop_btn)
    Button toShopBtn;

    @Override
    public boolean getIsFullScreen() {
        return true;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_order;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.toShop_btn)
    public void onViewClicked() {

        startActivity(new Intent(OrderActivity.this,PayActivity.class));

    }
}
