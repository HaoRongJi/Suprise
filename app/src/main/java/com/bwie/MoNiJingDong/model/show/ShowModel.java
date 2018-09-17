package com.bwie.MoNiJingDong.model.show;

import com.bwie.MoNiJingDong.constrat.ShowConstract;
import com.bwie.MoNiJingDong.entity.ShowBean;
import com.bwie.MoNiJingDong.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.Observable;

public class ShowModel implements ShowConstract.IShowModel {


    @Override
    public Observable<ShowBean> showData(String keywords, int page) {
        return RetrofitUtils.getInstance().getServer().getShowData(keywords, page);
    }
}
