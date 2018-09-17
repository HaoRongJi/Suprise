package com.bwie.MoNiJingDong.presenter.show;

import android.util.Log;

import com.bwie.MoNiJingDong.constrat.ShowConstract;
import com.bwie.MoNiJingDong.entity.ShowBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowPresenter extends ShowConstract.ShowPresenter {

    @Override
    public void showData(String keywords,int page) {
        mModel.showData(keywords,page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowBean>() {
                    @Override
                    public void accept(ShowBean showBean) throws Exception {
                        mView.onSuccess(showBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("abcd",throwable.getMessage());
                    }
                });
    }
}
