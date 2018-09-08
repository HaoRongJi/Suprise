package com.bwie.MoNiJingDong.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.ui.activity.LoginActivity;
import com.hao.base.base.mvp.BaseMvpFragment;
import com.hao.base.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FiveFragment extends BaseMvpFragment {


    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.log_reg_tv)
    TextView logRegTv;
    @BindView(R.id.message_img)
    ImageView messageImg;

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragmentfive;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.head_img)
    public void onHeadImgClicked() {



    }

    @OnClick(R.id.log_reg_tv)
    public void onLogRegTvClicked() {

        startActivity(new Intent(getContext(),LoginActivity.class));

    }

    @OnClick(R.id.message_img)
    public void onMessageImgClicked() {

    }
}
