package com.bwie.MoNiJingDong.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    //间距大小
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //outRect.left=space;
        //outRect.right=space;
        //outRect.bottom=space;
        //如果是第一列设置右方的值
        if(parent.getChildAdapterPosition(view)==0){
            outRect.right=space;
        }
    }
}
