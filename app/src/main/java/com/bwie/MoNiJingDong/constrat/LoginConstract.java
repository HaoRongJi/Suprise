package com.bwie.MoNiJingDong.constrat;

import com.bwie.MoNiJingDong.entity.UserLoginBean;
import com.bwie.MoNiJingDong.model.login.LoginModel;
import com.hao.base.base.mvp.BasePresenter;
import com.hao.base.base.mvp.IBaseModel;
import com.hao.base.base.mvp.IBaseView;

import io.reactivex.Observable;

public interface LoginConstract {

    abstract class  LoginPresenter extends BasePresenter<ILoginModel,ILoginView>{

        @Override
        public ILoginModel getmModel() {
            return new LoginModel();
        }

        public abstract void login(String phone,String password);

    }

    interface ILoginModel extends IBaseModel{

        Observable<UserLoginBean> login(String phone,String password);

    }

    interface ILoginView extends IBaseView{

        void onSuccess(UserLoginBean userLoginBean);
        void onFailure(String Msg);

    }

}
