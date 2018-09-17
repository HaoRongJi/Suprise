package com.bwie.MoNiJingDong.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ScrollHomeAdapter extends BaseQuickAdapter<HomeBean.DataBean.MiaoshaBean.ListBean,BaseViewHolder> {

    private SimpleDraweeView simpleDraweeView;

    public ScrollHomeAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.MiaoshaBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.MiaoshaBean.ListBean item) {

        helper.setText(R.id.tv,"￥  "+item.getPrice());
        simpleDraweeView = helper.getView(R.id.sdv);
        String[] split = item.getImages().split("\\|");
        simpleDraweeView.setImageURI(split[0]);

    }
}
