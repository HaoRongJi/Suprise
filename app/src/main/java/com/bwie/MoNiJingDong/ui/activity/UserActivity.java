package com.bwie.MoNiJingDong.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.MySetUser2Adapter;
import com.bwie.MoNiJingDong.adapter.MySetUserAdapter;
import com.bwie.MoNiJingDong.entity.SetUserEntity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends BaseMvpActivity {


    @BindView(R.id.mylogintopback)
    ImageView mylogintopback;
    @BindView(R.id.img_tou)
    SimpleDraweeView imgTou;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_user)
    TextView textUser;
    @BindView(R.id.set_user_recy)
    RecyclerView setUserRecy;
    @BindView(R.id.sex_img)
    ImageView imageView;
    private SharedPreferences isFirst;
    private int userId;
    private int sex;
    private String headPic;
    private String nickName;
    private String sessionId;
    private ArrayList<String> mList;
    @BindView(R.id.setziliao)
    LinearLayout setziliao;
    private SetUserEntity setUserEntity;

    @Override
    public boolean getIsFullScreen() {
        return false;
    }

    @Override
    protected void initView() {

        ImmersionBar.with(this).init();

        setUserRecy.setLayoutManager(new LinearLayoutManager(this));
        //创建集合,自定义数据
        mList = new ArrayList<>();
        mList.add("会员俱乐部");
        mList.add("PLUSS会员");
        mList.add("小白信用");
        mList.add("地址管理");
        mList.add("增票资质");
        mList.add("实名认证");
        mList.add("账户安全");
        mList.add("支付设置");
        mList.add("关联账号");
        mList.add("设置");
        mList.add("我的定制");
        mList.add("退出登录");

        setUserEntity = new SetUserEntity(mList);

        //设置分割线
        setUserRecy.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //设置适配器
       setUserRecy.setAdapter(new MySetUserAdapter(this, mList));

        //setUserRecy.setAdapter(new MySetUser2Adapter(R.layout.my_setuser_adapter,setUserEntity.list));

        setziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UserActivity.this, PersonActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        mylogintopback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        isFirst = getSharedPreferences("isLogin", MODE_PRIVATE);
        userId = isFirst.getInt("userId", 0);
        sex = isFirst.getInt("sex", 0);
        headPic = isFirst.getString("headPic", "");
        sessionId = isFirst.getString("sessionId", "");
        nickName = isFirst.getString("nickName", "");

        imgTou.setImageURI(headPic);
        textName.setText(nickName);
        textUser.setText(sessionId);
        if (sex==1){
            imageView.setImageResource(R.drawable.man);
        }else if (sex==2){
            imageView.setImageResource(R.drawable.women);
        }





    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_user;
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
}
