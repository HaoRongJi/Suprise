package com.bwie.MoNiJingDong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;

import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeAdapter extends BaseQuickAdapter<HomeBean.DataBean.TuijianBean.ListBeanX,BaseViewHolder> {

    private ViewPager homeViewpager;
    private ArrayList<View> viewPagerList;
    private MyViewPagerAdapter myViewPagerAdapter;
    private int currentPage;
    private int totalPage;
    private int mPageSize = 8;
    public HomeBean homeBean1;
    private GridView classes_grid;
    private Context mContext;
    private ClassGridAdapter classGridAdapter;
    private View classesView;
    private View view_grid;


    public HomeAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.TuijianBean.ListBeanX> data,Context context) {
        super(layoutResId, data);
        EventBus.getDefault().register(this);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.TuijianBean.ListBeanX item) {

        helper.setText(R.id.product_item_tv,item.getTitle());
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.product_item_iv);
        String[] split = item.getImages().split("\\|");


        simpleDraweeView.setImageURI(split[0]);
        //classes();
    }

    private void classes() {
        view_grid = View.inflate(mContext, R.layout.view_grid, null);
        View.inflate(mContext, R.layout.classes_layout, null);
        homeViewpager=view_grid.findViewById(R.id.home_viewpager);
        //homeViewpager = helper.getView(R.id.home_viewpager);
        homeViewpager = view_grid.findViewById(R.id.home_viewpager);
        myViewPagerAdapter = new MyViewPagerAdapter(viewPagerList);

        homeViewpager.setAdapter(myViewPagerAdapter);
        homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //LayoutInflater inflater = LayoutInflater.from(getActivity());
        //总的页数，取整（这里有三种类型：Math.ceil(3.5)=4:向上取整，只要有小数都+1  Math.floor(3.5)=3：向下取整  Math.round(3.5)=4:四舍五入）
        totalPage = (int) Math.ceil(homeBean1.getData().getFenlei().size() * 1.0 / mPageSize);
        //Toast.makeText(getContext(), totalPage+"", Toast.LENGTH_SHORT).show();
        viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            //GridView gridView = (GridView) inflater.inflate(R.layout.classes_layout,((TextHolder2) holder).home_viewPager,false);
            //gridView.setAdapter(new MyGridViewAdapter(context,i,mPageSize,data.getFenlei()));*/
            classesView=View.inflate(mContext, R.layout.classes_layout, homeViewpager);
            //View classesView = getLayoutInflater().inflate(R.layout.classes_layout, homeViewpager, false);
            classes_grid = classesView.findViewById(R.id.home_viewpager_gridView);
            //classes_grid = helper.getView(R.id.home_viewpager_gridView);
            classGridAdapter = new ClassGridAdapter(mContext, i,mPageSize, homeBean1.getData().getFenlei());
            classes_grid.setAdapter(classGridAdapter);
            this.addHeaderView(classes_grid);
            viewPagerList.add(classes_grid);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(HomeBean homeBean){

        homeBean1=homeBean;

    }


}
