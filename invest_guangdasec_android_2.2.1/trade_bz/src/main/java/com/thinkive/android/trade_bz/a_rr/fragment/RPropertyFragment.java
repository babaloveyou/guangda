package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSecuritiesMarginActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_rr.bll.RPopertServiceImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.BasePagerAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.MyStoreListViewAdapterForBuysale;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ScreenUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshScrollView;
import com.thinkive.android.trade_bz.views.listViewInScrollview;

import java.util.ArrayList;
import java.util.List;

/**
 * 融资融券--资产
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/29
 */
public class RPropertyFragment extends AbsBaseFragment {
    private RSecuritiesMarginActivity mActivity;
    private RPopertServiceImpl mServices;
    private RPropertyViewController mController;
    private MyStoreListViewAdapterForBuysale mAdapter;
    /**
     * 持仓数据的ListView
     */
    private listViewInScrollview mListView;
    /**
     * 头部viewpager
     */
    private ViewPager mViewPager;
    /**
     * 装载头部fragment的集合类
     */
    private List<AbsBaseFragment> mFragmentList = null;
    /**
     * 头部viewpager的适配器
     */
    private BasePagerAdapter mPagerAdapter = null;
    /**
     * 头部pager的2个fragment
     */
    private RHoldPagerFragment1 mFragmentPagerOne;
    private RHoldPagerFragment2 mFragmentPagerTwo;
    /**
     * 显示小圆点的容器
     */
    private ViewGroup mGroup;
    /**
     * 存放小圆点的集合类
     */
    private View[] mPointForViewPager;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLlLoading;
    /**
     * 父类布局ScrollView
     */
    private ScrollView mScrollView;
    /**
     * 自定义的ScrollView
     */
    private PullToRefreshScrollView mPullToRefreshScrollView;
    /**
     * 被加载的 ScrollView 子布局
     */
    private View mScrollChild;
    /**
     * 开启定时器，每隔4秒刷新持仓
     */
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run () {
            mServices.requestAllHoldList();
            handler.postDelayed(this,4000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_scrollview, null);
        mScrollChild = inflater.inflate(R.layout.fragment_a_my_hold, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void findViews(View view) {
        mListView = (listViewInScrollview) mScrollChild.findViewById(R.id.lv_my_hold_stock);
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sll_my_stock);
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        mScrollView.addView(mScrollChild);
        //解决第一次进入显示ListView的焦点问题
        mListView.setFocusable(false);
        mListView.setDivider(null);
        mLlLoading = (LinearLayout) mScrollChild.findViewById(R.id.ll_myhold_list_loading);
        mViewPager = (ViewPager) mScrollChild.findViewById(R.id.vp_multi_trade);
        mGroup = (ViewGroup) mScrollChild.findViewById(R.id.ll_points);
        mLiNoData = (LinearLayout) mScrollChild.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_SCROLLVIEW_REFLASH, mPullToRefreshScrollView, mController);
    }

    @Override
    protected void initData() {
        mActivity=(RSecuritiesMarginActivity)getActivity();
        mController=new RPropertyViewController(this);
        mServices=new RPopertServiceImpl(this);
        mAdapter=new MyStoreListViewAdapterForBuysale(mActivity);
        mFragmentList = new ArrayList<AbsBaseFragment>();
        mFragmentPagerOne = new RHoldPagerFragment1();
        mFragmentPagerTwo = new RHoldPagerFragment2();
        mFragmentList.add(mFragmentPagerOne);
        mFragmentList.add(mFragmentPagerTwo);
        mPagerAdapter = new BasePagerAdapter(getChildFragmentManager());
        mPointForViewPager = new View[mFragmentList.size()];
        for (int i = 0; i < mFragmentList.size(); i++) {
            //设置小圆点的相关参数,默认选中第一个
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(10, 0, 10, 0);
            TextView textView = new TextView(mActivity);
            textView.setLayoutParams(params);
            mPointForViewPager[i] = textView;
            if (i == 0) {
                mPointForViewPager[i].setBackgroundResource(R.drawable.radio_dark);
            } else {
                mPointForViewPager[i].setBackgroundResource(R.drawable.radio_light);
            }
            mGroup.addView(mPointForViewPager[i]);
        }
        //请求当前资产数据列表
        mServices.requestAllMoneyData();
    }

    @Override
    protected void initViews() {
        mPagerAdapter.setFragmentsData(mFragmentList);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
        mViewPager.addOnPageChangeListener(new MyListener());
        //设置禁止上拉加载更多
        mPullToRefreshScrollView.setPullLoadEnabled(false);
        //设置listview父布局
        mListView.setmParentScrollView(mScrollView);
        //得到状态栏的高度（PX）
        int stateHeight = ScreenUtils.getStatusHeight(mActivity);
        //动态获取ListView高度 ，342为已知控件高度（dp）
        // 单位：dp，头部蓝色标题栏高度：44   基本交易的标签栏高度：37
        // 资产信息滑动页高度：220   持仓列表“头部”高度：40dp   其他（线条）：1
        float height = ScreenUtils.getScreenHeight(mActivity) - stateHeight -
                ScreenUtils.dpToPx(mActivity, 342);
        //设置ListView的高度（px）
        mListView.setMaxHeight((int) height);
        mLlLoading.setMinimumHeight((int) height);
        setTheme();
        mListView.setAdapter(mAdapter);
        //请求当前持仓数据列表
        mServices.requestAllHoldList();
    }

    @Override
    protected void setTheme() { }

    /**
     * 得到资产账户信息
     */
    public void getMoneyAccountData(RSelectPropertBean bean) {
        if(bean == null){
            bean = new RSelectPropertBean();
        }
        mFragmentPagerOne.getMoneyAccountData(bean);
        mFragmentPagerTwo.getMoneyAccountData(bean);
        refreshComplete();
    }
    /**
     * 得到当前持仓的数据列表
     * @param dataList
     */
    public void getCurrentHoldData(ArrayList<MyStoreStockBean> dataList){
        if(dataList == null || dataList.size()==0){
            mLiNoData.setVisibility(View.VISIBLE);
            mLlLoading.setVisibility(View.GONE);
            mListView.setVisibility(View.GONE);
        }else{
            mLiNoData.setVisibility(View.GONE);
            mLlLoading.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mAdapter.notifyDataSetChanged();
        }
    }
    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mPullToRefreshScrollView.onPullDownRefreshComplete();
        mPullToRefreshScrollView.onPullUpRefreshComplete();
        mPullToRefreshScrollView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }
    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestAllMoneyData();
    }

    // 头部ViewPager的监听实现类
    class MyListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int status) { }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) { }
        //当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
            // 更新三个小圆点的选中状态
            for (int i = 0; i < mPointForViewPager.length; i++) {
                if (position == i) {
                    mPointForViewPager[position].setBackgroundResource(R.drawable.radio_dark);
                }
                if (position != i) {
                    mPointForViewPager[i].setBackgroundResource(R.drawable.radio_light);
                }
            }
        }
    }
}

class RPropertyViewController extends AbsBaseController implements PullToRefreshBase.OnRefreshListener<ScrollView> {

    private RPropertyFragment mFragment;

    public RPropertyViewController(RPropertyFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_SCROLLVIEW_REFLASH:
                ((PullToRefreshScrollView)view).setOnRefreshListener(this);
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
    }
}
