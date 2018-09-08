package com.bwie.MoNiJingDong.api;

import com.bwie.MoNiJingDong.entity.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface ProductApi {

    @POST("home/getHome")
    Observable<HomeBean> homeData();


}
