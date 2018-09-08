package com.bwie.MoNiJingDong.presenter.cart;

import android.util.Log;

import com.bwie.MoNiJingDong.constrat.CartContract;
import com.bwie.MoNiJingDong.entity.CartsBean;
import com.bwie.MoNiJingDong.entity.ShowCartsBean;
import com.hao.base.base.mvp.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.greenrobot.eventbus.EventBus.TAG;

public class CartPresenter extends CartContract.CartPresenter {


    @Override
    public void cartData(String uid) {
        mModel.cartData(uid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowCartsBean>() {
                    @Override
                    public void accept(ShowCartsBean showCartsBean) throws Exception {
                        mView.onSuccess(showCartsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "accept: "+throwable);
                    }
                });
    }
}
