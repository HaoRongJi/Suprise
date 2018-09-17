package com.bwie.MoNiJingDong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.ShowAdapter;
import com.bwie.MoNiJingDong.constrat.ShowConstract;
import com.bwie.MoNiJingDong.entity.ShowBean;
import com.bwie.MoNiJingDong.presenter.show.ShowPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends BaseMvpActivity<ShowConstract.IShowModel, ShowConstract.ShowPresenter> implements ShowConstract.IShowView {

    @BindView(R.id.finish_img)
    ImageView finishImg;
    @BindView(R.id.procontent_et)
    EditText procontentEt;
    @BindView(R.id.classes_img)
    ImageView classesImg;
    @BindView(R.id.search_recyclerview)
    RecyclerView searchRecyclerview;
    @BindView(R.id.smart_layout_5)
    SmartRefreshLayout smartRefreshLayout;
    private int page = 1;
    private String s1;
    private int i = 1;


    private ShowAdapter showAdapter;
    private Intent intent;

    @Override
    public boolean getIsFullScreen() {
        return false;
    }

    @Override
    protected void initData() {
        super.initData();
        intent = getIntent();
        s1 = intent.getStringExtra("s1");

        presenter.showData(s1, page);

    }



    @Override
    protected void initView() {
        ImmersionBar.with(this).init();
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                presenter.showData(s1,page);
                showAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                presenter.showData(s1,page);
                showAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore(1000);
            }
        });

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    public BasePresenter initPresenter() {
        return new ShowPresenter();
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
    public void onSuccess(final ShowBean showBean) {
        //Toast.makeText(this, showBean.getData().toString(), Toast.LENGTH_SHORT).show();
        Log.i("aaa",showBean.getMsg());
        searchRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        showAdapter = new ShowAdapter(R.layout.show_item_layout, showBean.getData(),this);
        searchRecyclerview.setAdapter(showAdapter);

        showAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ShowActivity.this, DetailsActivity.class);
                String detailUrl = showBean.getData().get(position).getDetailUrl();
                EventBus.getDefault().postSticky(detailUrl);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFailure(String Msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.finish_img)
    public void onFinishImgClicked() {
        finish();
    }

    @OnClick(R.id.classes_img)
    public void onClassesImgClicked() {
        if (i % 2 == 1) {
            searchRecyclerview.setLayoutManager(new GridLayoutManager(ShowActivity.this, 2));
            i++;
        } else {
            searchRecyclerview.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
            i++;
        }
    }

}
