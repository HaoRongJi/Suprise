package com.bwie.MoNiJingDong.model.order;

import com.bwie.MoNiJingDong.api.Api;
import com.bwie.MoNiJingDong.api.OrderApi;
import com.bwie.MoNiJingDong.constrat.OrderContract;
import com.bwie.MoNiJingDong.entity.OrderEntity;
import com.bwie.MoNiJingDong.entity.PayEntity;
import com.bwie.MoNiJingDong.utils.LoggingInterceptor;
import com.bwie.MoNiJingDong.utils.ToStringConverter;
import com.hao.base.net.RetrofitUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OrderModel implements OrderContract.IOrderModel {

    @Override
    public Observable<OrderEntity> getOrder(HashMap<String, String> headers, HashMap<String, String> body) {
        return RetrofitUtils.getInstanse().createApi(Api.BASE_ORDER_URL,OrderApi.class).getOrder(headers, body);
    }

    @Override
    public Observable<String> getPay(HashMap<String, String> headers, HashMap<String, String> body) {
        //return RetrofitUtils.getInstanse().createApi(Api.BASE_ORDER_URL,OrderApi.class).getPay(headers, body);
        return  new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_ORDER_URL)
                .build()
                .create(OrderApi.class)
                .getPay(headers, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


}
