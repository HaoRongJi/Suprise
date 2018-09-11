package com.bwie.MoNiJingDong.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.ClassGridAdapter;
import com.bwie.MoNiJingDong.adapter.HomeAdapter;
import com.bwie.MoNiJingDong.adapter.MyViewPagerAdapter;
import com.bwie.MoNiJingDong.constrat.HomeConstract;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.bwie.MoNiJingDong.entity.ProductBean;
import com.bwie.MoNiJingDong.presenter.home.HomePresenter;
import com.bwie.MoNiJingDong.ui.activity.DetailsActivity;
import com.bwie.MoNiJingDong.utils.RetrofitUtils;
import com.bwie.MoNiJingDong.view.home.IHomeView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.base.base.BaseFragment;
import com.hao.base.base.mvp.BaseMvpFragment;
import com.hao.base.base.mvp.BasePresenter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OneFragment extends BaseMvpFragment<HomeConstract.ProductModel, HomeConstract.ProductPresenter> implements IHomeView, XBanner.XBannerAdapter {

    private SmartRefreshLayout smartLayout;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private XBanner banner_1;
    private ArrayList<String> strings;
    private ClassGridAdapter classGridAdapter;
    private int totalPage;
    private ArrayList<View> viewPagerList;
    private int mPageSize = 8;
    private ViewPager homeViewpager;
    private int currentPage;
    private MyViewPagerAdapter myViewPagerAdapter;
    private View classes_grid;
    private GridView classesView;
    private LayoutInflater inflater;
    private ImageView img_wennituijian;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragmentone;
    }

    @Override
    protected void initView() {
        super.initView();

        ImmersionBar.with(this).init();
//        EventBus.getDefault().register(this);
        smartLayout = mRootView.findViewById(R.id.smart_layout);
        recyclerView = mRootView.findViewById(R.id.recycler_view);
        /*smartLayout.setRefreshHeader(new MaterialHeader(getActivity()).setColorSchemeColors(R.color.colorAccent));
        smartLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));*/
        //设置 Footer 为 球脉冲
        smartLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        inflater=LayoutInflater.from(getActivity());
        smartLayout.autoRefresh(2000);
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.homeData();
                refreshlayout.finishRefresh(2000,true);
            }
        });
    }


    @Override
    protected void initData() {
        super.initData();
        presenter.homeData();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public BasePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void fail(String Msg) {

    }

    @Override
    public void onSuccess(final HomeBean homeBean) {

        //Toast.makeText(mActivity, homeBean.toString(), Toast.LENGTH_SHORT).show();

        EventBus.getDefault().postSticky(homeBean);

        strings = new ArrayList<>();

        for (int i = 0; i < homeBean.getData().getBanner().size(); i++) {
            String icon = homeBean.getData().getBanner().get(i).getIcon();
            strings.add(icon);
        }


        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        homeAdapter = new HomeAdapter(R.layout.product_item_layout, homeBean.getData().getTuijian().getList(),getActivity());
        recyclerView.setAdapter(homeAdapter);

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                String detailUrl = homeBean.getData().getTuijian().getList().get(position).getDetailUrl();
                EventBus.getDefault().postSticky(detailUrl);
                startActivity(intent);
            }
        });
        //轮播图
        View banner_layout = getLayoutInflater().inflate(R.layout.baner_layout, (ViewGroup) recyclerView.getParent(), false);
        banner_1 = banner_layout.findViewById(R.id.banner_1);
        homeAdapter.addHeaderView(banner_layout);

        banner_1.setData(strings);
        banner_1.setmAdapter(this);

        banner_1.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {

                Intent intent = new Intent(getContext(), DetailsActivity.class);
                String detailUrls = homeBean.getData().getBanner().get(position).getUrl();
                Toast.makeText(getContext(), detailUrls, Toast.LENGTH_SHORT).show();
                //Toast.makeText(mActivity, detailUrl, Toast.LENGTH_SHORT).show();
                //intent.putExtra("details",detailUrls);
                EventBus.getDefault().postSticky(detailUrls);
                startActivity(intent);
            }
        });


        classes(homeBean);

        img_wennituijian = (ImageView) getLayoutInflater().inflate(R.layout.weinituijian, (ViewGroup) recyclerView.getParent(), false);

        homeAdapter.addHeaderView(img_wennituijian);

    }

    private void classes(HomeBean homeBean) {



        View view_grid = View.inflate(getContext(), R.layout.view_grid, null);
        View.inflate(getContext(),R.layout.classes_layout,null);
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
        totalPage = (int) Math.ceil(homeBean.getData().getFenlei().size() * 1.0 / mPageSize);
        //Toast.makeText(getContext(), totalPage+"", Toast.LENGTH_SHORT).show();
        viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            /*GridView gridView = (GridView) inflater.inflate(R.layout.classes_layout,((TextHolder2) holder).home_viewPager,false);
            gridView.setAdapter(new MyGridViewAdapter(context,i,mPageSize,data.getFenlei()));*/
            classesView = (GridView) inflater.inflate(R.layout.classes_layout, homeViewpager, false);
            classes_grid = classesView.findViewById(R.id.home_viewpager_gridView);

            classGridAdapter = new ClassGridAdapter(getContext(), i,mPageSize, homeBean.getData().getFenlei());
            classesView.setAdapter(classGridAdapter);
            homeAdapter.addHeaderView(classes_grid);
            viewPagerList.add(classes_grid);
        }
    }

    @Override
    public void onFailure(String errorMsg) {

    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(this).load(strings.get(position)).into((ImageView) view);
    }
}
