package com.bwie.MoNiJingDong.model.cart;

import com.bwie.MoNiJingDong.constrat.CartContract;
import com.bwie.MoNiJingDong.entity.CartsBean;
import com.bwie.MoNiJingDong.entity.ShowCartsBean;
import com.bwie.MoNiJingDong.utils.RetrofitUtils;
import com.hao.base.base.mvp.IBaseModel;

import java.util.HashMap;

import io.reactivex.Observable;

public class CartModel implements CartContract.ICartModel {


    @Override
    public Observable<ShowCartsBean> cartData(String uid) {
        return RetrofitUtils.getInstance().getServer().showCarts(Integer.parseInt(uid));
    }
}
