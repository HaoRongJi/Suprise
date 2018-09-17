package com.bwie.MoNiJingDong.model.login;

import com.bwie.MoNiJingDong.api.Api;
import com.bwie.MoNiJingDong.api.LoginApi;
import com.bwie.MoNiJingDong.constrat.LoginConstract;
import com.bwie.MoNiJingDong.entity.UserLoginBean;
import com.hao.base.net.RetrofitUtils;

import io.reactivex.Observable;

public class LoginModel implements LoginConstract.ILoginModel {
    @Override
    public Observable<UserLoginBean> login(String phone, String password) {
        return RetrofitUtils.getInstanse().createApi(Api.BASE_DEBUG_URL,LoginApi.class).login(phone,password);
    }
}
