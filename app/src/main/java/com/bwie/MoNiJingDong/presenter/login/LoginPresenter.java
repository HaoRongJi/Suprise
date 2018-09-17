package com.bwie.MoNiJingDong.presenter.login;

import android.util.Log;

import com.bwie.MoNiJingDong.constrat.LoginConstract;
import com.bwie.MoNiJingDong.entity.UserLoginBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

public class LoginPresenter extends LoginConstract.LoginPresenter {
    @Override
    public void login(String phone, String password) {
        mModel.login(phone,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserLoginBean>() {
                    @Override
                    public void accept(UserLoginBean userLoginBean) throws Exception {
                        mView.onSuccess(userLoginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: 登录网络错误");
                    }
                });
    }
}
