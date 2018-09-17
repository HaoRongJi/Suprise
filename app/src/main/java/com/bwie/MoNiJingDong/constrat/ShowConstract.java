package com.bwie.MoNiJingDong.constrat;

import com.bwie.MoNiJingDong.entity.ShowBean;
import com.bwie.MoNiJingDong.model.show.ShowModel;
import com.hao.base.base.mvp.BasePresenter;
import com.hao.base.base.mvp.IBaseModel;
import com.hao.base.base.mvp.IBaseView;

import java.util.HashMap;

import io.reactivex.Observable;

public interface ShowConstract {

    abstract class ShowPresenter extends BasePresenter<IShowModel,IShowView> {

        @Override
        public IShowModel getmModel() {
            return new ShowModel();
        }

        public abstract void showData(String keywords,int page);

    }

    interface IShowModel extends IBaseModel {

        Observable<ShowBean> showData(String keywords,int page);

    }

    interface IShowView extends IBaseView{

        void onSuccess(ShowBean showBean);
        void onFailure(String Msg);

    }

}
