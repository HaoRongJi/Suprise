package com.bwie.MoNiJingDong.api;

import com.bwie.MoNiJingDong.entity.UserLoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface LoginApi {

    @POST("login")
    @FormUrlEncoded
    Observable<UserLoginBean> login(@Field("phone") String phone, @Field("pwd") String pwd);

}
