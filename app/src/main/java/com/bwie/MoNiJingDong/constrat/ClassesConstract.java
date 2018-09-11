package com.bwie.MoNiJingDong.constrat;

import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.bwie.MoNiJingDong.entity.ClassBean;
import com.bwie.MoNiJingDong.model.classes.ClassesModel;
import com.hao.base.base.mvp.BasePresenter;
import com.hao.base.base.mvp.IBaseModel;
import com.hao.base.base.mvp.IBaseView;

import io.reactivex.Observable;

public interface ClassesConstract {

    abstract class ClassesPresenter extends BasePresenter<IClassesModel,IClassesView> {

        @Override
        public IClassesModel getmModel() {
            return new ClassesModel();
        }

        public abstract void leftClasses();
        public abstract void rightClasses(int cid);

    }

    interface IClassesModel extends IBaseModel{

        Observable<ClassBean> leftClasses();
        Observable<ChildClassBean> rightClasses(int cid);

    }

    interface IClassesView extends IBaseView{

        void onLeftSuccess(ClassBean classBean);
        void onRightSuccess(ChildClassBean childClassBean);
        void onFailure(String Msg);

    }

}
