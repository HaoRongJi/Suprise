package com.bwie.MoNiJingDong.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.SetUserEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MySetUser2Adapter extends BaseQuickAdapter<SetUserEntity,BaseViewHolder> {


    private int size;
    private TextView view;

    public MySetUser2Adapter(int layoutResId, @Nullable List<SetUserEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SetUserEntity item) {
        size = item.getList().size();
        for (int i = 0; i <= size; i++) {

            helper.setText(R.id.text1,item.getList().get(i));

            if (i==size){

                view = helper.getView(R.id.text1);
                view.setBackgroundResource(R.drawable.shape);

            }

        }
    }
}
