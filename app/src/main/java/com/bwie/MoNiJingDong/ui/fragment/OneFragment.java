package com.bwie.MoNiJingDong.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.MoNiJingDong.R;
import com.bwie.MoNiJingDong.adapter.ClassGridAdapter;
import com.bwie.MoNiJingDong.adapter.HomeAdapter;
import com.bwie.MoNiJingDong.adapter.MyViewPagerAdapter;
import com.bwie.MoNiJingDong.adapter.ScrollHomeAdapter;
import com.bwie.MoNiJingDong.constrat.HomeConstract;
import com.bwie.MoNiJingDong.entity.HomeBean;
import com.bwie.MoNiJingDong.entity.ProductBean;
import com.bwie.MoNiJingDong.entity.SuccessEntity;
import com.bwie.MoNiJingDong.presenter.home.HomePresenter;
import com.bwie.MoNiJingDong.ui.activity.DetailsActivity;
import com.bwie.MoNiJingDong.ui.activity.SearchActivity;
import com.bwie.MoNiJingDong.utils.RetrofitUtils;
import com.bwie.MoNiJingDong.view.home.IHomeView;
import com.bwie.MoNiJingDong.widget.SpaceItemDecoration;
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
import com.sunfusheng.marqueeview.MarqueeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private int currentPage;
    private MyViewPagerAdapter myViewPagerAdapter;
    private GridView classesView;
    private LayoutInflater inflater;
    private View marqueeLayout;
    private MarqueeView marqueeView;
    private View procontentEt;
    private Intent searchIntent;
    private View miaoshaLayout;
    private RecyclerView mHome_miaosha_recy;
    private TextView miaosha_time;
    private TextView miaosha_shi;
    private TextView miaosha_minter;
    private TextView miaosha_second;
    private List<HomeBean.DataBean.MiaoshaBean.ListBean> mList;



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setTime();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    private View scrollLayout;
    private ScrollHomeAdapter scrollHomeAdapter;
    private View inflate;
    private ViewPager view_grid;


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
        smartLayout = mRootView.findViewById(R.id.smart_layout);
        recyclerView = mRootView.findViewById(R.id.recycler_view);
        procontentEt = mRootView.findViewById(R.id.procontent_et);
        smartLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        inflater=LayoutInflater.from(getActivity());
        smartLayout.autoRefresh(500);
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.homeData();
                refreshlayout.finishRefresh(500,true);
            }
        });

        procontentEt.setFocusable(false);

        procontentEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchIntent = new Intent(getActivity(), SearchActivity.class);
                startActivity(searchIntent/*,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle()*/);
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

        EventBus.getDefault().post(new SuccessEntity(homeBean));

        strings = new ArrayList<>();

        for (int i = 0; i < homeBean.getData().getBanner().size(); i++) {
            String icon = homeBean.getData().getBanner().get(i).getIcon();
            strings.add(icon);
        }

        //为你推荐
        showRecyclerView(homeBean);
        //xbanner轮播图
        banner(homeBean);
        //分类九宫格实现
        classes(homeBean);
        //快报
        newsFlash();

        seckill(homeBean);


        inflate = getLayoutInflater().inflate(R.layout.weinituijian, (ViewGroup) recyclerView.getParent(), false);

        homeAdapter.addHeaderView(inflate);

    }

    private void seckill(final HomeBean homeBean) {
        miaoshaLayout = getLayoutInflater().inflate(R.layout.time_miaosha_layout, (ViewGroup) recyclerView.getParent(), false);

        miaosha_time = miaoshaLayout.findViewById(R.id.tv_miaosha_time);
        miaosha_shi = miaoshaLayout.findViewById(R.id.tv_miaosha_shi);
        miaosha_minter = miaoshaLayout.findViewById(R.id.tv_miaosha_minter);
        miaosha_second = miaoshaLayout.findViewById(R.id.tv_miaosha_second);

        mList = homeBean.getData().getMiaosha().getList();
        int size=mList.size();

        handler.sendEmptyMessage(0);

        homeAdapter.addHeaderView(miaoshaLayout);

        scrollLayout = getLayoutInflater().inflate(R.layout.scroll_layout, (ViewGroup) recyclerView.getParent(), false);
        mHome_miaosha_recy=scrollLayout.findViewById(R.id.home_seckill);


        mHome_miaosha_recy.setLayoutManager(new GridLayoutManager(getActivity(),size));
        scrollHomeAdapter = new ScrollHomeAdapter(R.layout.scroll_item_layout, homeBean.getData().getMiaosha().getList());
        mHome_miaosha_recy.setAdapter(scrollHomeAdapter);
        scrollHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                String detailUrls=homeBean.getData().getMiaosha().getList().get(position).getDetailUrl();
                EventBus.getDefault().postSticky(detailUrls);
                startActivity(intent);
            }
        });

        homeAdapter.addHeaderView(scrollLayout);
    }

    private void newsFlash() {
        marqueeLayout = getLayoutInflater().inflate(R.layout.marquee_layout, (ViewGroup) recyclerView.getParent(), false);

        List<String> info = new ArrayList<>();
        info.add("大促销下单拆福袋，亿万新年红包随便拿");
        info.add("家电五折团，抢十亿无门槛现金红包");
        info.add("星球大战剃须刀首发送200元代金券");

        marqueeView = marqueeLayout.findViewById(R.id.marqueeView);

        marqueeView.startWithList(info);
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);

        homeAdapter.addHeaderView(marqueeLayout);
    }

    private void showRecyclerView(final HomeBean homeBean) {



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
    }

    private void banner(final HomeBean homeBean) {
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
    }

    //秒杀倒计时
    public void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //df.setTimeZone(TimeZone.getTimeZone("GMT+08:00")); // 不会受系统时区设置的影响,否则时间可能不准确
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        Log.i("hour","事件"+format);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.i("hour",hour+"");
        if (hour % 2 == 0) {
            miaosha_time.setText( hour+ "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else if(hour+8==24){
            hour=-9;
            miaosha_time.setText( hour+9+ "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");

        }else {
            miaosha_time.setText(hour + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            miaosha_shi.setText("0" + hours + "");
            if (minute >= 10) {
                miaosha_minter.setText(minute + "");
            } else {
                miaosha_minter.setText("0" + minute + "");
            }
            if (second >= 10) {
                miaosha_second.setText(second + "");
            } else {
                miaosha_second.setText("0" + second + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void classes(HomeBean homeBean) {

        view_grid = (ViewPager) getLayoutInflater().inflate(R.layout.view_grid, (ViewGroup) recyclerView.getParent(), false);

        totalPage = (int) Math.ceil(homeBean.getData().getFenlei().size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            classesView = (GridView) inflater.inflate(R.layout.classes_layout, (ViewGroup) view_grid.getParent(), false);

            classGridAdapter = new ClassGridAdapter(getContext(), i,mPageSize, homeBean.getData().getFenlei());

            classesView.setAdapter(classGridAdapter);
            viewPagerList.add(classesView);
        }

        myViewPagerAdapter = new MyViewPagerAdapter(viewPagerList);

        view_grid.setAdapter(myViewPagerAdapter);
        view_grid.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        homeAdapter.addHeaderView(view_grid);


    }

    @Override
    public void onFailure(String errorMsg) {

    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(this).load(strings.get(position)).into((ImageView) view);
    }
}
