package com.bwie.MoNiJingDong.api;

        import com.bwie.MoNiJingDong.entity.OrderEntity;
        import com.bwie.MoNiJingDong.entity.PayEntity;

        import java.util.HashMap;

        import io.reactivex.Observable;
        import retrofit2.http.FieldMap;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.HeaderMap;
        import retrofit2.http.Multipart;
        import retrofit2.http.POST;
        import retrofit2.http.PartMap;
        import retrofit2.http.QueryMap;

public interface OrderApi {

    @POST("verify/buyMovieTicket")
    @FormUrlEncoded
    Observable<OrderEntity> getOrder(@HeaderMap HashMap<String, String> headers, @FieldMap HashMap<String, String> body);

    @POST("verify/pay")
    @FormUrlEncoded
    Observable<String> getPay(@HeaderMap HashMap<String, String> headers, @FieldMap HashMap<String, String> body);
}
