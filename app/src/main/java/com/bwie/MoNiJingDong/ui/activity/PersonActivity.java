package com.bwie.MoNiJingDong.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.MoNiJingDong.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;

import java.io.File;

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

        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("选择图片：")
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
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, 1);
    }

    private void picFromCam() {

        path="";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));

        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            crop(uri);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {

            Bitmap bitmap = data.getParcelableExtra("data");
            settx.setImageBitmap(bitmap);
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {

            Uri uri = Uri.fromFile(new File(path));

            settx.setImageURI(uri);

            crop(uri);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {

            Bitmap bitmap = data.getParcelableExtra("data");
            settx.setImageBitmap(bitmap);

        }
    }

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);

        intent.putExtra("noFaceDetection", false);

        intent.putExtra("return-data", true);

        startActivityForResult(intent, 2);
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
}
