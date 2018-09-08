package com.bwie.MoNiJingDong.presenter.home;

import android.util.Log;
import android.widget.Toast;

import com.bwie.MoNiJingDong.constrat.HomeConstract;
import com.bwie.MoNiJingDong.entity.HomeBean;


import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.greenrobot.eventbus.EventBus.TAG;

public class HomePresenter extends HomeConstract.ProductPresenter {


    @Override
    public void homeData() {



        mModel.homeData().subscribe(new Consumer<HomeBean>() {
            @Override
            public void accept(HomeBean homeBean) throws Exception {
                Log.i(TAG, "accept: " + homeBean.toString());
                if (homeBean!=null){
                    try{

                        mView.onSuccess(homeBean);

                    }catch (Exception e){

                        Log.i(TAG, "accept: "+e.toString());
                        e.printStackTrace();


                    }
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i("aaa","网络错误");
            }
        });
        /*mModel.homeData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        Log.i(TAG, "accept: " + homeBean.toString());

                        //EventBus.getDefault().post(homeBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
*/

    }

}
