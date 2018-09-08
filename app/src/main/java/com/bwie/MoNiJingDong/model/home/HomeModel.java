package com.bwie.MoNiJingDong.model.home;

import com.bwie.MoNiJingDong.api.Api;
import com.bwie.MoNiJingDong.api.HomeApi;
import com.bwie.MoNiJingDong.api.ProductApi;
import com.bwie.MoNiJingDong.constrat.HomeConstract;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.bwie.MoNiJingDong.entity.ProductBean;
import com.bwie.MoNiJingDong.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeConstract.ProductModel {


    @Override
    public Observable<HomeBean> homeData() {
        return RetrofitUtils.getInstance().getServer().showData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //return com.hao.base.net.RetrofitUtils.getInstanse().createApi(Api.BASE_RELEASE_URL,ProductApi.class).homeData();
    }
}
