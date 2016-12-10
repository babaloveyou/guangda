package com.thinkive.android.trade_bz.a_rr.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.CreditTodayTradeDetailActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.CreditTodayTradeAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayTradeBean;
import com.thinkive.android.trade_bz.a_rr.bll.CreditTodayTradeServicesImpl;
import com.thinkive.android.trade_bz.a_rr.controll.CreditTodayTradeController;
import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.controll.TodayTradeController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ScreenUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshScrollView;
import com.thinkive.android.trade_bz.views.listViewInScrollview;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayTradeFragment extends AbsBaseFragment {
    /**
     * 该类的宿主Activity
     */
    private MultiCreditTradeActivity mActivity;
    /**
     * 今日成交的ListView
     */
    private listViewInScrollview mListView;
    /**
     * 今日成交数据的LisView的适配器
     */
    private CreditTodayTradeAdapter mAdapter;
    /**
     * 该类的业务类
     */
    private CreditTodayTradeServicesImpl mServiceImpl = null;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;

    /**
     * 控制今日成交
     */
    private CreditTodayTradeController mController;
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
     * 界面的根布局
     */
    private View mRootview;
    /**
     * 下半部分的横向滑动监听布局，负责ViewPager以下部分的横滑监听
     */
    private HorizontalSlideLinearLayout mHsllPart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootview = inflater.inflate(R.layout.fragment_common_refresh_scrollview, null);
        mScrollChild = inflater.inflate(R.layout.fragment_a_today_trade, null);
        findViews(mRootview);
        initData();
        setListeners();
        initViews();
        return mRootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 设置宿主Activity的横滑监听不可用
        HorizontalSlideLinearLayout hsll_inActivity = mActivity.getHorizontalSlideLinearLayout();
        hsll_inActivity.setRightSlideable(false);
        hsll_inActivity.setLeftSlideable(false);
        mListView.setAdapter(mAdapter, R.id.ll_list_item_view, R.id.ll_list_item_expand);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HorizontalSlideLinearLayout hsll_inActivity = mActivity.getHorizontalSlideLinearLayout();
        hsll_inActivity.setRightSlideable(true);
        hsll_inActivity.setLeftSlideable(true);
    }

    @Override
    protected void findViews(View view) {
        mListView = (listViewInScrollview) mScrollChild.findViewById(R.id.data_list_view);
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sll_my_stock);
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        mScrollView.addView(mScrollChild);
        //解决第一次进入显示ListView的焦点问题
        mListView.setFocusable(false);
        mLlLoading = (LinearLayout) mScrollChild.findViewById(R.id.ll_loading);
        mLiNoData = (LinearLayout) mScrollChild.findViewById(R.id.lin_not_data_set);
        mHsllPart = (HorizontalSlideLinearLayout) mScrollChild.findViewById(R.id.hsll_part);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_SCROLLVIEW_REFLASH, mPullToRefreshScrollView, mController);
        registerListener(TodayTradeController.ON_ACTION_CLICK, mListView, mController);
        registerListener(TodayTradeController.ON_SLIDE, mHsllPart, mController);
    }


    @Override
    protected void initData() {
        mActivity = (MultiCreditTradeActivity) getActivity();
        mServiceImpl = new CreditTodayTradeServicesImpl(this);
        mController = new CreditTodayTradeController(this);
        mAdapter = new CreditTodayTradeAdapter(mActivity);
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mPullToRefreshScrollView.setPullLoadEnabled(false);
        //设置listview父布局
        mListView.setmParentScrollView(mScrollView);
        //        mViewPager.setmParentScrollView(mScrollView);
        //得到状态栏的高度（PX）
        int stateHeight = getStatusHeight(mActivity);
        // 单位：dp，头部蓝色标题栏高度：44   基本交易的标签栏高度：37
        // 资产信息滑动页高度：220   持仓列表“头部”高度：40dp   其他（线条）：1
        float height = ScreenUtils.getScreenHeight(mActivity) - stateHeight -
                ScreenUtils.dpToPx(mActivity, 190);
        //设置ListView的高度（px）
        mListView.setMaxHeight((int) height);
        mListView.setDivider(null);
        mLlLoading.setMinimumHeight((int) height);
        mHsllPart.initslideStandard(mActivity);
        mListView.setAdapter(mAdapter, R.id.ll_today_entrust_list_item_view, R.id.ll_today_entrust_list_item_expand);

        setTheme();
    }

    @Override
    public void onStart() {
        super.onStart();
        mServiceImpl.requestTodayTrade();
    }

    @Override
    protected void setTheme() {
    }


    public void onPart2LeftSlide() {
        mActivity.onLeftSlide();
    }

    public void onPart2RightSlide() {
        mActivity.onRightSlide();
    }

    /**
     * 持仓列表展开界面中的“买入”按钮的点击事件
     * 点击此按钮时，进入买入模块，并显示被点击的股票联动数据
     */
    public void onClickExpandBuy(int position) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        // 获取被点击的项的股票代码
        String stockCode = mAdapter.getItem(position).getStock_code();
        //        String market = mAdapter.getItem(position).getMarket();
        // 通知activity做相应跳转和通知买卖Fragment操作
        mActivity.transferFragmentToBuySaleFromOthers(stockCode, 0);
    }

    /**
     * 持仓列表展开界面中的“卖出”按钮的点击事件
     * 点击此按钮时，进入卖出模块，并显示被点击的股票联动数据
     */
    public void onClickExpandSale(int position) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        // 获取被点击的项的股票代码
        String stockCode = mAdapter.getItem(position).getStock_code();
        //        String market = mAdapter.getItem(position).getMarket();
        // 通知activity做相应跳转和通知买卖Fragment操作
        mActivity.transferFragmentToBuySaleFromOthers(stockCode, 1);
    }

    /**
     * 持仓列表展开界面中的“行情”按钮的点击事件
     * 点击后，进入行情模块中的个股详情页面，但是返回后，还是本Fragment的界面
     */
    public void onClickExpandDetails(int position) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        RSelectTodayTradeBean bean = mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), CreditTodayTradeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    /**
     * 获取持仓数据
     *
     * @param dataList
     */
    public void onGetTodayTradeData(ArrayList<RSelectTodayTradeBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLiNoData.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            mLlLoading.setVisibility(View.GONE);
        } else {
            mLiNoData.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mLlLoading.setVisibility(View.GONE);
            mAdapter.setListData(dataList);
            mAdapter.notifyDataSetChanged();
        }
        refreshComplete();
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
        mServiceImpl.requestTodayTrade();
    }

    /**
     * 获得顶部状态栏的高度
     *
     * @param activity
     * @return
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

}

