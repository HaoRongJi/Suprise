package com.bwie.MoNiJingDong.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.LeftClassesAdapter;
import com.bwie.MoNiJingDong.adapter.RightClassesAdapter;
import com.bwie.MoNiJingDong.constrat.ClassesConstract;
import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.bwie.MoNiJingDong.entity.ClassBean;
import com.bwie.MoNiJingDong.presenter.classes.ClassesPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hao.base.base.BaseFragment;
import com.hao.base.base.mvp.BaseMvpFragment;
import com.hao.base.base.mvp.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

import static org.greenrobot.eventbus.EventBus.TAG;

public class TwoFragment extends BaseMvpFragment<ClassesConstract.IClassesModel,ClassesConstract.ClassesPresenter> implements ClassesConstract.IClassesView {

    @BindView(R.id.left_recycler_view)
    RecyclerView leftRecyclerview;
    @BindView(R.id.right_recycler_view)
    RecyclerView rightRecyclerview;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartRefreshLayout;
    private LeftClassesAdapter leftClassesAdapter;
    private RightClassesAdapter rightClassesAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragmenttwo;
    }

    @Override
    public BasePresenter initPresenter() {
        return new ClassesPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.leftClasses();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                leftClassesAdapter.notifyDataSetChanged();
                rightClassesAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(500);
            }
        });
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
    public void onLeftSuccess(final ClassBean classBean) {
        leftRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        leftClassesAdapter = new LeftClassesAdapter(R.layout.left_classes_layout, classBean.getData());

        leftClassesAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        leftClassesAdapter.isFirstOnly(false);
        leftRecyclerview.setAdapter(leftClassesAdapter);
        presenter.rightClasses(classBean.getData().get(0).getCid());

        //设置分割线
        leftRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        leftClassesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.rightClasses(classBean.getData().get(position).getCid());
            }
        });

    }

    @Override
    public void onRightSuccess(ChildClassBean childClassBean) {
        rightRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        rightClassesAdapter = new RightClassesAdapter(R.layout.right_classes_layout, childClassBean.getData(),getContext());
        rightRecyclerview.setAdapter(rightClassesAdapter);
        rightClassesAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(String Msg) {

    }
}
