package com.bwie.MoNiJingDong.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwie.MoNiJingDong.R;
import com.hao.base.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class ThreeFragment extends BaseFragment {

    @BindView(R.id.baidu_webview)
    WebView webView;
    @BindView(R.id.smart_layout_3)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragmentthree;
    }

    @Override
    protected void initView() {
        super.initView();
        webView.loadUrl("https://www.baidu.com/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    protected void initData() {
        super.initData();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                webView.reload();
                refreshlayout.finishRefresh(200);
            }
        });
    }
}
