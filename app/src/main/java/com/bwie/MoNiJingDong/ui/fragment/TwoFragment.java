package com.bwie.MoNiJingDong.ui.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.LeftClassesAdapter;
import com.bwie.MoNiJingDong.adapter.RightClassesAdapter;
import com.bwie.MoNiJingDong.constrat.ClassesConstract;
import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.bwie.MoNiJingDong.entity.ClassBean;
import com.bwie.MoNiJingDong.presenter.classes.ClassesPresenter;
import com.bwie.MoNiJingDong.ui.activity.SearchActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hao.base.base.BaseFragment;
import com.hao.base.base.mvp.BaseMvpFragment;
import com.hao.base.base.mvp.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.bugly.crashreport.CrashReport;

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
    @BindView(R.id.procontent_et)
    EditText procontentEt;
    private LeftClassesAdapter leftClassesAdapter;
    private RightClassesAdapter rightClassesAdapter;
    private Transition explode;
    private Intent searchIntent;
    private int selectedPosition=0;
    private int sposition;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = sposition;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //getActivity().getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

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
                //CrashReport.testJavaCrash();
                leftClassesAdapter.notifyDataSetChanged();
                rightClassesAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(500);
            }
        });

        /*explode = TransitionInflater.from(getActivity()).inflateTransition(R.transition.slide);

        //退出时使用
        getActivity().getWindow().setExitTransition(explode);
        //第一次进入时使用
        getActivity().getWindow().setEnterTransition(explode);
        //再次进入时使用
        getActivity().getWindow().setReenterTransition(explode);*/

        procontentEt.setFocusable(false);

        procontentEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchIntent = new Intent(getActivity(), SearchActivity.class);
                startActivity(searchIntent/*,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle()*/);
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

                /*sposition=position;

                if (selectedPosition == position) {
                    //adapter.getViewByPosition(leftRecyclerview,position,R.id.classes_text).setBackgroundColor(Color.parseColor("#ffffff"));
                    view.setBackgroundColor(Color.parseColor("#ffffff"));

                    *//*holder.item_name.setTextColor(Color.parseColor("#000000"));
                    holder.item_name.setTextSize(18);*//*
                } else {
                    //adapter.getViewByPosition(leftRecyclerview,position,R.id.classes_text).setBackgroundColor(Color.TRANSPARENT);
                    view.setBackgroundColor(Color.TRANSPARENT);
                    *//*holder.item_name.setTextColor(Color.parseColor("#393939"));
                    holder.item_name.setTextSize(12);*//*
                }*/
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        leftClassesAdapter.notifyDataSetChanged();
        rightClassesAdapter.notifyDataSetChanged();
    }
}
