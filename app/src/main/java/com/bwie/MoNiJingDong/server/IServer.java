package com.bwie.MoNiJingDong.server;

import com.bwie.MoNiJingDong.entity.CartsBean;
import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.bwie.MoNiJingDong.entity.ClassBean;
import com.bwie.MoNiJingDong.entity.DeleteCarts;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.bwie.MoNiJingDong.entity.LoginBean;
import com.bwie.MoNiJingDong.entity.ProductBean;
import com.bwie.MoNiJingDong.entity.ProductListBean;
import com.bwie.MoNiJingDong.entity.RegBean;
import com.bwie.MoNiJingDong.entity.ShowBean;
import com.bwie.MoNiJingDong.entity.ShowCartsBean;
import com.bwie.MoNiJingDong.entity.UpdateCarts;
import com.bwie.MoNiJingDong.entity.XiangQingBean;


import io.reactivex.Observable;
import okhttp3.FormBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IServer {
    //登陆
    @POST("user/login")
    Observable<LoginBean> login(@Query("mobile") String mobile, @Query("password") String password);
    //注册
    @POST("user/reg")
    Observable<RegBean> regist(@Query("mobile") String mobile, @Query("password") String password);
    //展示
    @GET("home/getHome")
    Observable<HomeBean> showData();
    @POST("product/getCatagory")
    Observable<ClassBean> getClassBean();
    @POST("product/getProductCatagory")
    Observable<ChildClassBean> getChildClassBean(@Query("cid") int cid);
    @POST("product/getProductDetail")
    Observable<XiangQingBean> getXqBean(@Query("pid") int pid);

    @GET("product/searchProducts")
    Observable<ShowBean> getShowData(@Query("keywords") String key,@Query("page") int page);
    //添加购物车
    @POST("product/addCart")
    Observable<CartsBean> getCarts(@Query("pid") int pid, @Query("uid") int uid);
    @POST("product/getCarts")
    Observable<ShowCartsBean> showCarts(@Query("uid") int uid);
    //删除
    @POST("product/deleteCart")
    Observable<DeleteCarts> delte(@Query("uid") int uid, @Query("pid") int pid);
    //更新购物车
    @POST("product/updateCarts")
    Observable<UpdateCarts> update(@Query("uid") int uid, @Query("sellerid") int sellerid, @Query("pid") int pid, @Query("selected") int selected, @Query("num") int num);

}
