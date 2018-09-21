package com.bwie.MoNiJingDong.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.utils.TakePictureManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseMvpActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.settx)
    SimpleDraweeView settx;
    @BindView(R.id.touxiang)
    LinearLayout touxiang;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.phone)
    LinearLayout phone;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.username)
    LinearLayout username;
    @BindView(R.id.xingbie)
    LinearLayout xingbie;
    @BindView(R.id.birthday)
    LinearLayout birthday;
    @BindView(R.id.sex_tv)
    TextView sexTv;
    @BindView(R.id.birthday_tv)
    TextView birthdayTv;
    private SharedPreferences isFirst;
    private String headPic;
    private String nickName;
    private String sessionId;
    private int sex;
    private int userId;
    private String birthday1;
    private AlertDialog.Builder builder;
    private String path;
    private TakePictureManager takePictureManager;

    @Override
    public boolean getIsFullScreen() {
        return false;
    }

    @Override
    protected void initData() {
        super.initData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        isFirst = getSharedPreferences("isLogin", MODE_PRIVATE);
        userId = isFirst.getInt("userId", 0);
        sex = isFirst.getInt("sex", 0);
        headPic = isFirst.getString("headPic", "");
        sessionId = isFirst.getString("phone", "");
        nickName = isFirst.getString("nickName", "");
        birthday1 = isFirst.getString("birthday", "");

        settx.setImageURI(headPic);
        name.setText(nickName);
        tel.setText(sessionId);
        birthdayTv.setText(birthday1);
        if (sex==1){

            sexTv.setText("男");

        }else{

            sexTv.setText("女");

        }


    }


    @Override
    protected void initView() {
        ImmersionBar.with(this).init();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_person;
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

    @OnClick(R.id.touxiang)
    public void onTouxiangClicked() {





        builder = new AlertDialog.Builder(PersonActivity.this);

        builder.setTitle("在以下方式中选择：")
                .setItems(
                        new String[] { "相机", "相册" },
                        new android.content.DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub

                                switch (which) {
                                    case 0:
                                        picFromCam();
                                        break;
                                    case 1:
                                        picFromPic();
                                        break;

                                    default:
                                        break;
                                }

                            }

                        });

        builder.create().show();

    }



    private void picFromPic() {

        takePictureManager = new TakePictureManager(this);
        takePictureManager.setTailor(1, 1, 350, 350);
        takePictureManager.startTakeWayByAlbum();
        takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
            @Override
            public void successful(boolean isTailor, File outFile, Uri filePath) {
                Picasso.with(PersonActivity.this).load(outFile).error(R.mipmap.ic_launcher).into(settx);
            }

            @Override
            public void failed(int errorCode, List<String> deniedPermissions) {

            }

        });

    }

    private void picFromCam() {

        takePictureManager = new TakePictureManager(this);
        //开启裁剪 比例 1:3 宽高 350 350  (默认不裁剪)
        takePictureManager.setTailor(1, 3, 350, 350);
        //拍照方式
        takePictureManager.startTakeWayByCarema();
        //回调
        takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
            //成功拿到图片,isTailor 是否裁剪？ ,outFile 拿到的文件 ,filePath拿到的URl
            @Override
            public void successful(boolean isTailor, File outFile, Uri filePath) {
                Picasso.with(PersonActivity.this).load(outFile).error(R.mipmap.ic_launcher).into(settx);
            }

            //失败回调
            @Override
            public void failed(int errorCode, List<String> deniedPermissions) {
                Log.e("==w",deniedPermissions.toString());
            }
        });

    }





    @OnClick(R.id.phone)
    public void onPhoneClicked() {
    }

    @OnClick(R.id.username)
    public void onUsernameClicked() {
    }

    @OnClick(R.id.xingbie)
    public void onXingbieClicked() {
    }

    @OnClick(R.id.birthday)
    public void onBirthdayClicked() {
    }

    //把本地的onActivityResult()方法回调绑定到对象
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePictureManager.attachToActivityForResult(requestCode, resultCode, data);
    }

    //onRequestPermissionsResult()方法权限回调绑定到对象
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePictureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
