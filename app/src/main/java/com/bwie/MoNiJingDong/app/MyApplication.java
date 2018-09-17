package com.bwie.MoNiJingDong.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyApplication extends Application {


        @Override
        public void onCreate()
        {
            super.onCreate();

            Fresco.initialize(this);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggerInterceptor("TAG"))
                    .addNetworkInterceptor(new Interceptor() {//自定义拦截器
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request  = chain.request();
                            //配置统一的头
                            Request  newRequest     = request.newBuilder().addHeader("head", "xiayu").build();
                            return  chain.proceed(newRequest);
                        }
                    })
                    .addNetworkInterceptor( new StethoInterceptor())//增加Stetho拦截器,用于调试
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    //其他配置
                    .build();
            //使用自定义OkHttpClient
            OkHttpUtils.initClient(okHttpClient);
            CrashReport.initCrashReport(getApplicationContext(), "898d0045c7", true);

        }


}
