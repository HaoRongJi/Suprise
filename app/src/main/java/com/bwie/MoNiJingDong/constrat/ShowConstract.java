package com.bwie.MoNiJingDong.constrat;

import com.bwie.MoNiJingDong.entity.ShowBean;
import com.hao.base.base.mvp.BasePresenter;
import com.hao.base.base.mvp.IBaseModel;
import com.hao.base.base.mvp.IBaseView;

import io.reactivex.Observable;

public interface ShowConsrract {

    abstract class ShowPresenter extends BasePresenter<IShowModel,IShowView> {

        @Override
        public IShowModel getmModel() {
            return null;
        }

        public abstract void showData();

    }

    interface IShowModel extends IBaseModel {

        Observable<ShowBean> showData();

    }

    interface IShowView extends IBaseView{

        void onSuccess(ShowBean showBean);
        void onFailure(String Msg);

    }

}
