package com.bwie.MoNiJingDong.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.MoNiJingDong.R;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisiterActivity extends BaseMvpActivity {


    @BindView(R.id.return_img)
    ImageView returnImg;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.duanxinyzm)
    TextView duanxinyzm;
    @BindView(R.id.lianxikefu)
    TextView lianxikefu;
    @BindView(R.id.regisit_btn)
    Button regisitBtn;
    private String phoneNum;
    private String password;

    @Override
    public boolean getIsFullScreen() {
        return false;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).init();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_regisiter;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.return_img)
    public void onReturnImgClicked() {

        finish();

    }

    @OnClick(R.id.duanxinyzm)
    public void onDuanxinyzmClicked() {
    }

    @OnClick(R.id.lianxikefu)
    public void onLianxikefuClicked() {
    }

    @OnClick(R.id.regisit_btn)
    public void onViewClicked() {

        phoneNum = phone.getText().toString();
        password = pwd.getText().toString();

        TelephonyManager telephonyManager = (TelephonyManager)this.getSystemService( Context.TELEPHONY_SERVICE);

        //获取本机MIEI号码（仅手机存在）
        String deviceId = telephonyManager.getDeviceId();
        //获取设备序列号
        String sn =  telephonyManager.getSimSerialNumber();
        //获取本机电话号码
        String phonenumber = telephonyManager.getLine1Number();
        //获取本机型号
        String phonetype = android.os.Build.MODEL;

        Log.d("TAG", "获取本机MIEI号码（仅手机存在）"+deviceId+"获取设备序列号"+sn+"获取本机电话号码"+phonenumber+"获取本机型号"+phonetype);



    }


}
