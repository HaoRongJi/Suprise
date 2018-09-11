package com.bwie.MoNiJingDong.presenter.classes;

import android.util.Log;

import com.bwie.MoNiJingDong.constrat.ClassesConstract;
import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.bwie.MoNiJingDong.entity.ClassBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClassesPresenter extends ClassesConstract.ClassesPresenter {
    @Override
    public void leftClasses() {
        mModel.leftClasses().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ClassBean>() {
                    @Override
                    public void accept(ClassBean classBean) throws Exception {
                        mView.onLeftSuccess(classBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("aaa","网络错误");
                    }
                });
    }

    @Override
    public void rightClasses(int cid) {
        mModel.rightClasses(cid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChildClassBean>() {
                    @Override
                    public void accept(ChildClassBean childClassBean) throws Exception {
                        mView.onRightSuccess(childClassBean);
                        Log.i("aaa",childClassBean.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("aaa","网络错误");
                    }
                });
    }
}
