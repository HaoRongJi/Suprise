package com.bwie.MoNiJingDong.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.procontent_et)
    EditText searchEt;
    @BindView(R.id.finish_img)
    ImageView finishImg;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.con_fl1)
    AutoFlowLayout conFl1;
    @BindView(R.id.con_fl2)
    AutoFlowLayout conFl2;

    private ArrayList<String> content;
    private ArrayList<String> content2;
    private TextView tjText;
    private String s1;

    @Override
    protected void initData() {
        content2 = new ArrayList<>();
        content2.add("姬存希");
        content2.add("金利来 商品");
        content2.add("奢睿");
        auto2();
        content = new ArrayList<>();
        searchTv.setOnClickListener(this);
        finishImg.setOnClickListener(this);
    }

    private void auto2() {
        conFl2.setAdapter(new FlowAdapter(content2) {
            @Override
            public View getView(int i) {
                View view = View.inflate(SearchActivity.this, R.layout.text_layout, null);
                tjText = view.findViewById(R.id.tj_text);
                tjText.setText(content2.get(i));
                tjText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = tjText.getText().toString();
                        Toast.makeText(SearchActivity.this, s + "", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SearchActivity.this,ShowActivity.class);
                        intent.putExtra("s1",s);
                        startActivity(intent);
                    }
                });
                return view;
            }
        });
    }

    private void auto() {
        conFl1.setAdapter(new FlowAdapter(content) {
            @Override
            public View getView(int i) {
                View view = View.inflate(SearchActivity.this, R.layout.text_layout, null);
                tjText = view.findViewById(R.id.tj_text);
                tjText.setText(content.get(i));
                tjText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = tjText.getText().toString();
                        //String s1 = searchEt.getText().toString();
                        Toast.makeText(SearchActivity.this, s + "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SearchActivity.this, ShowActivity.class);
                        intent.putExtra("s1",s);
                        startActivity(intent);
                    }
                });
                return view;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.search_tv:

                s1 = searchEt.getText().toString();
                if (s1 != null && !s1.equals("")) {

                    content.add(s1);
                    auto();
                    content.clear();
                    Intent intent=new Intent(SearchActivity.this,ShowActivity.class);
                    intent.putExtra("s1",s1);
                    startActivity(intent);
                    finish();


                } else {

                    Toast.makeText(this, "请在输入框输入您想搜索的商品", Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.finish_img:

                finish();

                break;

            default:

                break;


        }
    }

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
        return R.layout.activity_search;
    }
}
