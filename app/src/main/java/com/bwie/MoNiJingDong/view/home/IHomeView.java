package com.bwie.MoNiJingDong.view.home;

import com.bwie.MoNiJingDong.entity.HomeBean;
import com.bwie.MoNiJingDong.entity.ProductBean;
import com.hao.base.base.mvp.IBaseView;

public interface IHomeView extends IBaseView {

    void onSuccess(HomeBean homeBean);
    void onFailure(String errorMsg);

}
