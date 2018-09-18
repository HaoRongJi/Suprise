package com.bwie.MoNiJingDong.constrat;

import com.bwie.MoNiJingDong.entity.OrderEntity;
import com.bwie.MoNiJingDong.entity.PayEntity;
import com.bwie.MoNiJingDong.model.order.OrderModel;
import com.hao.base.base.mvp.BasePresenter;
import com.hao.base.base.mvp.IBaseModel;
import com.hao.base.base.mvp.IBaseView;

import java.util.HashMap;

import io.reactivex.Observable;

public interface OrderContract {

    abstract class OrderPresenter extends BasePresenter<IOrderModel,IOrderView> {

        @Override
        public IOrderModel getmModel() {
            return new OrderModel();
        }

        public abstract void getOrder(HashMap<String,String> headers,HashMap<String,String> body);
        public abstract void getPay(HashMap<String,String> headers,HashMap<String,String> body);


    }

    interface IOrderModel extends IBaseModel{

        Observable<OrderEntity> getOrder(HashMap<String,String> headers,HashMap<String,String> body);
        Observable<String> getPay(HashMap<String,String> headers, HashMap<String,String> body);

    }

    interface IOrderView extends IBaseView{

        void onSuccess(OrderEntity orderEntity);
        void onFailure(String Msg);
        void onSuccessPay(String successPay);

    }

}
