package com.bwie.MoNiJingDong.ui.fragment;

import com.bwie.MoNiJingDong.R;
import com.hao.base.base.mvp.BaseMvpFragment;
import com.hao.base.base.mvp.BasePresenter;

public class RightFragment extends BaseMvpFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.right_fragment_layout;
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
}
