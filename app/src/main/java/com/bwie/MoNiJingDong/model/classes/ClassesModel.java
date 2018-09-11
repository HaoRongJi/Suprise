package com.bwie.MoNiJingDong.model.classes;

import com.bwie.MoNiJingDong.constrat.ClassesConstract;
import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.bwie.MoNiJingDong.entity.ClassBean;
import com.bwie.MoNiJingDong.utils.RetrofitUtils;

import io.reactivex.Observable;

public class ClassesModel implements ClassesConstract.IClassesModel {
    @Override
    public Observable<ClassBean> leftClasses() {
        return RetrofitUtils.getInstance().getServer().getClassBean();
    }

    @Override
    public Observable<ChildClassBean> rightClasses(int cid) {
        return RetrofitUtils.getInstance().getServer().getChildClassBean(cid);
    }


}
