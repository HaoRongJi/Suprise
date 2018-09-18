package com.bwie.MoNiJingDong.presenter.order;

import com.bwie.MoNiJingDong.constrat.OrderContract;
import com.bwie.MoNiJingDong.entity.OrderEntity;
import com.bwie.MoNiJingDong.entity.PayEntity;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter extends OrderContract.OrderPresenter {
    @Override
    public void getOrder(HashMap<String, String> headers, HashMap<String, String> body) {
        mModel.getOrder(headers, body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderEntity>() {
                    @Override
                    public void accept(OrderEntity orderEntity) throws Exception {
                        mView.onSuccess(orderEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onFailure(throwable.toString());
                    }
                });
    }

    @Override
    public void getPay(HashMap<String, String> headers, HashMap<String, String> body) {
        mModel.getPay(headers, body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String payEntity) throws Exception {
                        mView.onSuccessPay(payEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onFailure(throwable.toString());
                    }
                });
    }
}
