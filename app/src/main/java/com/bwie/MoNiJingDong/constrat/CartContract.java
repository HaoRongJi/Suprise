package com.bwie.MoNiJingDong.constrat;

import com.bwie.MoNiJingDong.entity.CartsBean;
import com.bwie.MoNiJingDong.entity.ShowCartsBean;
import com.bwie.MoNiJingDong.model.cart.CartModel;
import com.bwie.MoNiJingDong.view.home.IHomeView;
import com.hao.base.base.mvp.BasePresenter;
import com.hao.base.base.mvp.IBaseModel;
import com.hao.base.base.mvp.IBaseView;

import io.reactivex.Observable;

public interface CartContract {

    abstract class CartPresenter extends BasePresenter<CartModel,ICartView> {

        @Override
        public CartModel getmModel() {
            return new CartModel();
        }

        public abstract void cartData(String uid);

    }

    interface ICartModel extends IBaseModel {

        Observable<ShowCartsBean> cartData(String uid);

    }

    interface ICartView extends IBaseView{

        void onSuccess(ShowCartsBean showCartsBean);
        void onFailure(String errorMsg);


    }

}
