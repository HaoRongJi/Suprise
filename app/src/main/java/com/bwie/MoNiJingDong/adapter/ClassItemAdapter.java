package com.bwie.MoNiJingDong.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ClassItemAdapter extends BaseQuickAdapter<ChildClassBean.DataBean.ListBean,BaseViewHolder> {

    private SimpleDraweeView classImg;

    public ClassItemAdapter(int layoutResId, @Nullable List<ChildClassBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChildClassBean.DataBean.ListBean item) {
        helper.setText(R.id.classes_item_proName,item.getName());
        classImg = helper.getView(R.id.classes_imgUrl);
        classImg.setImageURI(item.getIcon());
    }
}
