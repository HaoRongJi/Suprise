package com.bwie.MoNiJingDong.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpUtils {

    private OkHttpClient okHttpClient;

    private static OKHttpUtils okHttpUtils;

    public static OKHttpUtils getInstance() {
        if (okHttpUtils==null){

            synchronized (OKHttpUtils.class){

                if (okHttpUtils==null){

                    okHttpUtils=new OKHttpUtils();

                }

            }

        }
        return okHttpUtils;
    }

    private OKHttpUtils() {

        okHttpClient=new OkHttpClient.Builder()
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();

    }

    public void getData(String url, HashMap<String,String> hashMap, final RequestCallBack requestCallback){

        StringBuilder builder = new StringBuilder();
        String allUrl="";
        for (Map.Entry<String, String> stringStringEntry : hashMap.entrySet()) {
            builder.append("?").append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("&");
        }
        allUrl=url+builder.toString().substring(0,builder.length()-1);

        Request request=new Request.Builder()
                .url(allUrl)
                .get()
                .build();

        //异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback!=null){

                    requestCallback.onFailure(call, e);

                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallback!=null){

                    requestCallback.onResponse(call, response);

                }
            }
        });
    }


    public void postData(String url, HashMap<String,String> hashMap, final RequestCallBack requestCallback){

        FormBody.Builder formBody=new FormBody.Builder();

        if (hashMap!=null&&hashMap.size()>0){

            for (Map.Entry<String, String> stringStringEntry : hashMap.entrySet()) {
                formBody.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }

        }

        Request request=new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback!=null){

                    requestCallback.onFailure(call, e);

                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallback!=null){

                    requestCallback.onResponse(call, response);

                }
            }
        });




    }


}
