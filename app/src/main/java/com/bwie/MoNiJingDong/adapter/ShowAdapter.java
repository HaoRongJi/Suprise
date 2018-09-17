package com.bwie.MoNiJingDong.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.ShowBean;
import com.bwie.MoNiJingDong.ui.activity.ShowActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ShowAdapter extends BaseQuickAdapter<ShowBean.DataBean,BaseViewHolder> {

    private SimpleDraweeView showIv;
    private Context mContext;

    public ShowAdapter(int layoutResId, @Nullable List<ShowBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShowBean.DataBean item) {
        helper.setText(R.id.show_tv,item.getTitle());


        showIv = helper.getView(R.id.show_iv);
        String[] split = item.getImages().split("\\|");
        showIv.setImageURI(split[0]);
    }
}
