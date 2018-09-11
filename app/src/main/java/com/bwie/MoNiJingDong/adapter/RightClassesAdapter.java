package com.bwie.MoNiJingDong.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.ChildClassBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RightClassesAdapter extends BaseQuickAdapter<ChildClassBean.DataBean,BaseViewHolder> {

    private RecyclerView classItemRecyclerview;
    private Context mContext;
    private ClassItemAdapter classItemAdapter;

    public RightClassesAdapter(int layoutResId, @Nullable List<ChildClassBean.DataBean> data,Context context) {
        super(layoutResId, data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ChildClassBean.DataBean item) {

        helper.setText(R.id.classes_proName,item.getName());
        classItemRecyclerview = helper.getView(R.id.class_item_recyclerview);
        classItemRecyclerview.setLayoutManager(new GridLayoutManager(mContext, 3));
        classItemAdapter = new ClassItemAdapter(R.layout.right_classes_item_layout,item.getList());
        classItemRecyclerview.setAdapter(classItemAdapter);
        classItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mContext, "您点击了"+item.getList().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
