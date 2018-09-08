package com.bwie.MoNiJingDong.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeBean.DataBean.TuijianBean.ListBeanX,BaseViewHolder> {

    public HomeAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.TuijianBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.TuijianBean.ListBeanX item) {
        helper.setText(R.id.product_item_tv,item.getTitle());
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.product_item_iv);
        String[] split = item.getImages().split("\\|");


        simpleDraweeView.setImageURI(split[0]);
    }
}
