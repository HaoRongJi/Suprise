package com.bwie.MoNiJingDong.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.ClassesEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MutilAdapter extends BaseMultiItemQuickAdapter<ClassesEntity,BaseViewHolder> {

    private int totalPage;
    private int mPageSize = 8;
    private ArrayList<View> viewPagerList;


    public MutilAdapter(List<ClassesEntity> data) {
        super(data);
        addItemType(ClassesEntity.ClASSES_TYPE, R.layout.product_item_layout);
        addItemType(ClassesEntity.SHOWLIST_TYPT, R.layout.classes_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassesEntity item) {
        switch (helper.getItemViewType()){

            case ClassesEntity.ClASSES_TYPE:

                break;

            case ClassesEntity.SHOWLIST_TYPT:

                /*totalPage = (int) Math.ceil(homeBean.getData().getFenlei().size() * 1.0 / mPageSize);
                viewPagerList = new ArrayList<>();
                for(int i=0;i<totalPage;i++){
                    //每个页面都是inflate出一个新实例
                    View classes = getLayoutInflater().inflate(R.layout.classes_layout, (ViewGroup) recyclerView.getParent(), false);
                    classes.findViewById(R.id.)
                    classes.
                            GridView gridView = (GridView) inflater.inflate(R.layout.home_viewpager_grid,((TextHolder2) holder).home_viewPager,false);
                    gridView.setAdapter(new MyGridViewAdapter(context,i,mPageSize,data.getFenlei()));
                    viewPagerList.add(gridView);
                }
                ((TextHolder2) holder).home_viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));

                ((TextHolder2) holder).home_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        currentPage = position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });*/

                break;

            default:

                break;

        }
    }
}
