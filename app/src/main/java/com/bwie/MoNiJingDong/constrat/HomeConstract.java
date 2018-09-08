package com.bwie.MoNiJingDong.constrat;

import com.bwie.MoNiJingDong.entity.HomeBean;
import com.bwie.MoNiJingDong.entity.ProductBean;
import com.bwie.MoNiJingDong.model.home.HomeModel;
import com.bwie.MoNiJingDong.view.home.IHomeView;
import com.hao.base.base.mvp.BasePresenter;
import com.hao.base.base.mvp.IBaseModel;
import com.hao.base.base.mvp.IBaseView;

import io.reactivex.Observable;


public interface HomeConstract {

    abstract class ProductPresenter extends BasePresenter<ProductModel,IHomeView>{

        @Override
        public ProductModel getmModel() {
            return new HomeModel();
        }

        public abstract void homeData();

    }

    interface ProductModel extends IBaseModel{

        Observable<HomeBean> homeData();

    }





}
