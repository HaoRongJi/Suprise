package com.bwie.MoNiJingDong.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.constrat.OrderContract;
import com.bwie.MoNiJingDong.entity.OrderEntity;
import com.bwie.MoNiJingDong.presenter.order.OrderPresenter;
import com.bwie.MoNiJingDong.utils.MD5Utils;
import com.bwie.MoNiJingDong.utils.MyALipayUtils;
import com.bwie.MoNiJingDong.utils.PayResult;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends BaseMvpActivity<OrderContract.IOrderModel, OrderContract.OrderPresenter> implements OrderContract.IOrderView {

    @BindView(R.id.pay1_btn)
    LinearLayout pay1Btn;
    @BindView(R.id.dt_back_img)
    ImageView dtBackImg;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.pay2_btn)
    LinearLayout pay2Btn;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.pay3_btn)
    LinearLayout pay3Btn;
    private String md5;
    private HashMap<String, String> headers;
    private SharedPreferences isLogin;
    private int userId;
    private String sessionId;
    private HashMap<String, String> body;
    private HashMap<String, String> payHeaders;
    private HashMap<String, String> payBody;
    private MyALipayUtils myALipayUtils;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 10) {

                PayResult result = new PayResult((Map<String, String>) msg.obj);

                MyALipayUtils.ALiPayBuilder builder = new MyALipayUtils.ALiPayBuilder();
                builder.build().toALiPay(PayActivity.this, result.getResultStatus());

            }
        }

        ;
    };

    @Override
    public boolean getIsFullScreen() {
        return true;
    }

    @Override
    protected void initView() {

        pay1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payBody.put("payType", 2 + "");
                presenter.getPay(payHeaders, payBody);
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();

        headers = new HashMap<>();
        isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
        userId = isLogin.getInt("userId", 0);
        sessionId = isLogin.getString("sessionId", "");
        headers.put("userId", userId + "");
        headers.put("sessionId", sessionId);
        body = new HashMap<>();
        body.put("scheduleId", "1");
        body.put("amount", "2");
        Log.i("sessioniiiiiiiii", "userId: " + userId + "   sessionId: " + sessionId);
        md5 = MD5Utils.calc(userId + "1" + "2" + "movie");
        Log.d("Md5123   ", "initData: " + md5 + "      " + userId);
        body.put("sign", md5);
        presenter.getOrder(headers, body);


    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_pay;

    }

    @Override
    public BasePresenter initPresenter() {
        return new OrderPresenter();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void fail(String Msg) {

    }

    @Override
    public void onSuccess(OrderEntity orderEntity) {
        Toast.makeText(this, orderEntity.getOrderId(), Toast.LENGTH_SHORT).show();
        payHeaders = new HashMap<>();
        userId = isLogin.getInt("userId", 0);
        sessionId = isLogin.getString("sessionId", "");
        payHeaders.put("userId", userId + "");
        payHeaders.put("sessionId", sessionId);
        payBody = new HashMap<>();

        payBody.put("orderId", orderEntity.getOrderId());


    }

    @Override
    public void onFailure(String Msg) {

        //Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        Log.d("bbbbbbbbbb", Msg);
    }

    @Override
    public void onSuccessPay(String successPay) {

        /*Toast.makeText(this, successPay, Toast.LENGTH_SHORT).show();*/
        final String orderInfo = successPay;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = 10;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dt_back_img, R.id.pay2_btn, R.id.pay3_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dt_back_img:

                finish();
                break;
            case R.id.pay2_btn:
                Toast.makeText(this, "微信功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pay3_btn:
                Toast.makeText(this, "银行卡功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
