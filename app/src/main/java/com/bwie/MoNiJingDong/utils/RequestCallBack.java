package com.bwie.MoNiJingDong.utils;

import okhttp3.Call;
import okhttp3.Response;

public interface RequestCallBack {

    void onResponse(Call call, Response response);
    void onFailure(Call call,Exception e);

}
