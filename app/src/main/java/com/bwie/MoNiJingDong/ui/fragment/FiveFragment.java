package com.bwie.MoNiJingDong.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.HomeAdapter;
import com.bwie.MoNiJingDong.constrat.LoginConstract;
import com.bwie.MoNiJingDong.entity.CartEventBus;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.bwie.MoNiJingDong.entity.SuccessEntity;
import com.bwie.MoNiJingDong.entity.UserEventBus;
import com.bwie.MoNiJingDong.entity.UserLoginBean;
import com.bwie.MoNiJingDong.model.login.LoginModel;
import com.bwie.MoNiJingDong.presenter.login.LoginPresenter;
import com.bwie.MoNiJingDong.ui.activity.LoginActivity;
import com.bwie.MoNiJingDong.ui.activity.UserActivity;
import com.bwie.MoNiJingDong.utils.SharedPreferencesUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hao.base.base.mvp.BaseMvpFragment;
import com.hao.base.base.mvp.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static org.greenrobot.eventbus.EventBus.TAG;

public class FiveFragment extends BaseMvpFragment<LoginModel, LoginPresenter> implements LoginConstract.ILoginView {


    @BindView(R.id.head_img)
    SimpleDraweeView headImg;
    @BindView(R.id.log_reg_tv)
    TextView logRegTv;
    @BindView(R.id.message_img)
    ImageView messageImg;
    @BindView(R.id.set_img)
    ImageView setImg;
    @BindView(R.id.recycler_view5)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private AlertDialog.Builder builder;
    private SharedPreferences sp;
    private boolean isFirst;
    private String sessionId;
    private int userId;
    private String birthday;
    private String headPic;
    private int id;
    private String lastLoginTime;
    private String nickName;
    private String phone;
    private int sex;
    private String status;
    private SharedPreferences.Editor edit;
    private boolean isLogin;
    private boolean isLogin1;
    private boolean isLogin2;
    private boolean isLogin3;
    private boolean isLogin4;
    private SharedPreferences loginText;
    private String phone1;
    private String encryptPwd;
    private HomeBean homeBean;
    private HomeBean.DataBean.TuijianBean tuijian;
    private HomeAdapter homeAdapter;

    @Override
    protected void initData() {
        super.initData();

        EventBus.getDefault().register(this);


        sp = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);


        isFirst = sp.getBoolean("isLogin", false);


        if (isFirst) {

            loginText = getActivity().getSharedPreferences("LoginText", Context.MODE_PRIVATE);
            phone1 = loginText.getString("phone", "");
            encryptPwd = loginText.getString("encryptPwd", "");
            presenter.login(phone1, encryptPwd);

            headPic = sp.getString("headPic", "");
            nickName = sp.getString("nickName", "");
            headImg.setImageURI(headPic);
            logRegTv.setText(nickName);
            logRegTv.setTextSize(18);

        }

        setImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = sp.getBoolean("isLogin", false);

                if (!isLogin) {


                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();

                } else {

                    startActivity(new Intent(getActivity(), UserActivity.class));
                    //Toast.makeText(getActivity(),"已经登录，无需再次登录",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragmentfive;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.head_img)
    public void onHeadImgClicked() {

        isLogin1 = sp.getBoolean("isLogin", false);

        if (isLogin1) {

            startActivity(new Intent(getActivity(), UserActivity.class));


        } else {

            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();

        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void tuijian(SuccessEntity successEntity) {

        homeBean = successEntity.getHomeBean();
        tuijian = homeBean.getData().getTuijian();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        homeAdapter = new HomeAdapter(R.layout.product_item_layout,tuijian.getList(), getActivity());
        recyclerView.setAdapter(homeAdapter);

    }


    @OnClick(R.id.log_reg_tv)
    public void onLogRegTvClicked() {

        isLogin = sp.getBoolean("isLogin", false);

        if (!isLogin) {


            startActivity(new Intent(getContext(), LoginActivity.class));

        } else {

            startActivity(new Intent(getActivity(), UserActivity.class));
            //Toast.makeText(getActivity(),"已经登录，无需再次登录",Toast.LENGTH_SHORT).show();

        }


    }

    @OnClick(R.id.message_img)
    public void onMessageImgClicked() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exitLogin(UserEventBus userEventBus) {
        isLogin3 = sp.getBoolean("isLogin", true);
        Toast.makeText(getActivity(), isLogin3 + "", Toast.LENGTH_SHORT).show();
        if (isLogin3) {

            headImg.setImageURI("");
            logRegTv.setText("登录/注册");
            logRegTv.setTextSize(15);
            isLogin4 = sp.getBoolean("isLogin", false);
            EventBus.getDefault().post(new CartEventBus(isLogin4));

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginSuccess(UserLoginBean userLoginBean) {

        /*sessionId = sp.getString("sessionId", "");
        userId = sp.getInt("userId", 0);

        id = sp.getInt("id", 0);
        lastLoginTime = sp.getString("lastLoginTime", "");

        phone = sp.getString("phone", "");
        status = sp.getString("status", "");*/
        Log.i(TAG, "loginSuccess: " + userLoginBean.toString());
        SharedPreferencesUtils.setParam(getActivity(), "sessionId", userLoginBean.getResult().getSessionId());
        SharedPreferencesUtils.setParam(getActivity(), "userId", userLoginBean.getResult().getUserId());
        sp.edit().putBoolean("isLogin", true).commit();
        headPic = sp.getString("headPic", "");
        nickName = sp.getString("nickName", "");

        headImg.setImageURI(headPic);
        logRegTv.setText(nickName);
        logRegTv.setTextSize(18);

        isLogin2 = sp.getBoolean("isLogin", true);

        EventBus.getDefault().post(new CartEventBus(isLogin2));

    }


    @Override
    public void onSuccess(UserLoginBean userLoginBean) {
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

        Log.i("sessionId", "onSuccess: " + sp.getString("sessionId", "000"));

        headPic = sp.getString("headPic", "");
        nickName = sp.getString("nickName", "");
        headImg.setImageURI(headPic);
        logRegTv.setText(nickName);
        logRegTv.setTextSize(18);
    }

    @Override
    public void onFailure(String Msg) {

    }
}
