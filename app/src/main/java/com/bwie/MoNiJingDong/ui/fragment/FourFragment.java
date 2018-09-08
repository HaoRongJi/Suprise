package com.bwie.MoNiJingDong.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.CartAdapter;
import com.bwie.MoNiJingDong.constrat.CartContract;
import com.bwie.MoNiJingDong.entity.CartsBean;
import com.bwie.MoNiJingDong.entity.ShowCartsBean;
import com.bwie.MoNiJingDong.model.cart.CartModel;
import com.bwie.MoNiJingDong.presenter.cart.CartPresenter;
import com.hao.base.base.mvp.BaseMvpActivity;
import com.hao.base.base.mvp.BaseMvpFragment;
import com.hao.base.base.mvp.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class FourFragment extends BaseMvpFragment<CartModel,CartPresenter> implements CartContract.ICartView {

    @BindView(R.id.f_recyclerView)
    RecyclerView fRecyclerView;
    @BindView(R.id.f_checkbox)
    CheckBox fCheckbox;
    @BindView(R.id.qx_tv)
    TextView qxTv;
    @BindView(R.id.f_price)
    TextView fPrice;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private CartAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragmentfour;
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.cartData("17224");
    }



    @Override
    protected void initView() {
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
//设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        EventBus.getDefault().register(this);
    }



    @Override
    public BasePresenter initPresenter() {
        return new CartPresenter();
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
    public void onSuccess(ShowCartsBean showCartsBean) {
        Log.i("productentity",showCartsBean+"");

        final List<ShowCartsBean.DataBean> data = showCartsBean.getData();
        fRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CartAdapter(R.layout.cart_item, data);
        fRecyclerView.setAdapter(adapter);

        fCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fCheckbox.isChecked()){
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setIschekbox(true);
                        for (int i1 = 0; i1 < data.get(i).getList().size(); i1++) {
                            data.get(i).getList().get(i1).setIscheckbox(true);
                        }
                    }
                }else {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setIschekbox(false);
                        for (int i1 = 0; i1 < data.get(i).getList().size(); i1++) {
                            data.get(i).getList().get(i1).setIscheckbox(false);
                        }
                    }

                }
                /*  */
                totalPrice();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shub(String mas) {

        adapter.notifyDataSetChanged();
        totalPrice();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shua(String ma) {
        adapter.notifyDataSetChanged();

        StringBuilder stringBuilder = new StringBuilder();
        if (adapter != null) {
            for (int i = 0; i < adapter.getBean().size(); i++) {

                stringBuilder.append(adapter.getBean().get(i).getIschekbox());

                for (int i1 = 0; i1 < adapter.getBean().get(i).getList().size(); i1++) {

                    stringBuilder.append(adapter.getBean().get(i).getList().get(i1).isIscheckbox());

                }
            }
        }
        if (stringBuilder.toString().contains("false")) {
            fCheckbox.setChecked(false);
        } else {
            fCheckbox.setChecked(true);
        }

        totalPrice();


    }

    private void totalPrice() {
        double totalprice = 0 ;
        /*循环嵌套*/
        for (int i = 0; i <adapter.getBean().size(); i++) {
            for (int i1 = 0; i1 < adapter.getBean().get(i).getList().size(); i1++) {
                if (adapter.getBean().get(i).getList().get(i1).isIscheckbox()){
                    ShowCartsBean.DataBean.ListBean listBean = adapter.getBean().get(i).getList().get(i1);
                    totalprice+= listBean.getTotalNum()*listBean.getBargainPrice();
                }
            }
        }
        fPrice.setText("总价：¥"+totalprice);
    }

    @Override
    public void onFailure(String errorMsg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
