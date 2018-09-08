package com.bwie.MoNiJingDong.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.bwie.MoNiJingDong.R;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseMvpActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.dt_back_img)
    ImageView dtBackImg;
    @BindView(R.id.inCart)
    Button inCart;
    @BindView(R.id.addCart)
    Button addCart;


    @Override
    protected void initData() {

    }

    @Override
    public boolean getIsFullScreen() {
        return true;
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web_view);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_details;
    }

    @Subscribe(sticky = true)
    public void showDetails(String details) {

        webView.loadUrl(details);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    @OnClick(R.id.dt_back_img)
    public void onDtBackImgClicked() {
        finish();
    }

    @OnClick(R.id.inCart)
    public void onInCartClicked() {
    }

    @OnClick(R.id.addCart)
    public void onAddCartClicked() {
    }
}
