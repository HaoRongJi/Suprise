package com.bwie.MoNiJingDong.adapter;



import android.support.annotation.Nullable;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.ClassBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class LeftClassesAdapter extends BaseQuickAdapter<ClassBean.DataBean,BaseViewHolder> {
    public LeftClassesAdapter(int layoutResId, @Nullable List<ClassBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassBean.DataBean item) {
        helper.setText(R.id.classes_text,item.getName());
    }

}
