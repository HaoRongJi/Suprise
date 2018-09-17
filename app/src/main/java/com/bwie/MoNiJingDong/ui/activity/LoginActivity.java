package com.bwie.MoNiJingDong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.constrat.LoginConstract;
import com.bwie.MoNiJingDong.entity.UserLoginBean;
import com.bwie.MoNiJingDong.model.login.LoginModel;
import com.bwie.MoNiJingDong.presenter.login.LoginPresenter;
import com.bwie.MoNiJingDong.utils.EncryptUtil;
import com.bwie.MoNiJingDong.utils.PhoneFormatCheckUtils;
import com.bwie.MoNiJingDong.utils.SharedPreferencesUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginModel, LoginPresenter> implements LoginConstract.ILoginView {

    @BindView(R.id.mobile_et)
    EditText mobileEt;
    @BindView(R.id.pwd_et)
    EditText pwdEt;
    @BindView(R.id.eye_btn)
    ImageView eyeBtn;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.duanxinyzm)
    TextView duanxinyzm;
    @BindView(R.id.xinyonghuzc)
    TextView xinyonghuzc;
    @BindView(R.id.finish_img)
    ImageView finishImg;
    private int isShow = 1;
    private String phone;
    private String pwd;
    private String encryptPwd;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private SharedPreferences loginText;
    private SharedPreferences.Editor edit1;

    /**
     * 设置是否全屏方法，封装在base基类
     *
     * @return
     */
    @Override
    public boolean getIsFullScreen() {
        return false;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).init();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter();
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


    @OnClick(R.id.eye_btn)
    public void onEyeBtnClicked() {


        if (isShow % 2 == 0) {

            isShow++;
            pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());

        } else {

            isShow++;
            pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }


    }

    @OnClick(R.id.login_btn)
    public void onLoginBtnClicked() {

        phone = mobileEt.getText().toString();

        pwd = pwdEt.getText().toString();

        if (PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {

            if (pwd.length() >= 6 && pwd != null && !pwd.equals("")) {

                encryptPwd = EncryptUtil.encrypt(pwd);

                presenter.login(phone, encryptPwd);

            } else {


                Toast.makeText(this, "密码不能为空且长度必须大于6位", Toast.LENGTH_SHORT).show();

            }

        } else {

            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();

        }


    }

    @OnClick(R.id.duanxinyzm)
    public void onDuanxinyzmClicked() {
    }

    @OnClick(R.id.xinyonghuzc)
    public void onXinyonghuzcClicked() {

        startActivity(new Intent(LoginActivity.this,RegisiterActivity.class));

    }


    @OnClick(R.id.finish_img)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(UserLoginBean userLoginBean) {
        if (userLoginBean.getStatus().equals("0000")) {

            Toast.makeText(this, userLoginBean.getMessage(), Toast.LENGTH_SHORT).show();

            sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
            loginText = getSharedPreferences("LoginText", MODE_PRIVATE);
            edit1 = loginText.edit();
            edit1.putString("phone",phone);
            edit1.putString("encryptPwd",encryptPwd);
            edit1.commit();

            edit = sp.edit();
            edit.putString("sessionId", userLoginBean.getResult().getSessionId());
            edit.putInt("userId", userLoginBean.getResult().getUserId());

            edit.putString("nickName", userLoginBean.getResult().getUserInfo().getNickName());
            edit.putString("headPic", userLoginBean.getResult().getUserInfo().getHeadPic());
            edit.putString("phone", userLoginBean.getResult().getUserInfo().getPhone());
            edit.putInt("sex", userLoginBean.getResult().getUserInfo().getSex());
            edit.putInt("id", userLoginBean.getResult().getUserInfo().getId());
            edit.putString("lastLoginTime", userLoginBean.getResult().getUserInfo().getLastLoginTime() + "");
            edit.commit();

            EventBus.getDefault().post(userLoginBean);
            finish();

        } else {

            Toast.makeText(this, userLoginBean.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(String Msg) {

    }
}
