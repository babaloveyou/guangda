package com.thinkive.android.trade_bz.a_rr.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.ICallBack;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.MyHoldStockDetailsActivity;
import com.thinkive.android.trade_bz.a_rr.bll.MyRRHoldStockServiceImpl;
import com.thinkive.android.trade_bz.a_rr.controll.MMyRRHoldStockViewController;
import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BasePagerAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.MyStoreListViewAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ScreenUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.ChildViewPager;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshScrollView;
import com.thinkive.android.trade_bz.views.listViewInScrollview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
public class MyRRHoldStockFragment extends AbsBaseFragment {
    /**
     * 该类的宿主Activity
     */
    private MultiCreditTradeActivity mActivity;
    /**
     * 持仓数据的ListView
     */
    private listViewInScrollview mListView;
    /**
     * 我的持仓数据的LisView的适配器
     */
    private MyStoreListViewAdapter mAdapter;
    /**
     * 该类的业务类
     */
    private MyRRHoldStockServiceImpl mServiceImpl = null;
    /**
     * 头部viewpager
     */
    private ChildViewPager mViewPager;
    /**
     * 装载头部fragment的集合类
     */
    private List<AbsBaseFragment> mFragmentList = null;
    /**
     * 头部viewpager的适配器
     */
    private BasePagerAdapter mPagerAdapter = null;
    /**
     * 上一个小圆点
     */
    private int preSelectPagePos = 0;
    /**
     * 头部pager fragment
     */
    private RRHolderPagerFragment mFragmentPagerOne;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;

    /**
     * 控制我的持仓
     */
    private MMyRRHoldStockViewController mController;
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
     * 上半部分的横向滑动监听布局，负责ViewPager部分的横滑监听
     */
    private HorizontalSlideLinearLayout mHsllPart1;
    /**
     * 下半部分的横向滑动监听布局，负责ViewPager以下部分的横滑监听
     */
    private HorizontalSlideLinearLayout mHsllPart2;
    /**
     * 开启定时器，每隔4秒刷新持仓
     */
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            mServiceImpl.requestMyHoldStock();
            handler.postDelayed(this, 4000);
        }
    };
    private int mLastHeaderPage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootview = inflater.inflate(R.layout.fragment_common_refresh_scrollview, null);
        mScrollChild = inflater.inflate(R.layout.fragment_a_rr_my_hold, null);
        findViews(mRootview);
        initData();
        setListeners();
        initViews();
        return mRootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        //        //刷新货币信息数据
        //        mFragmentPagerOne.getMoneyData();
        //        mFragmentPagerTwo.getMoneyData();
        //        mFragmentPagerThird.getMoneyData();
        // 设置宿主Activity的横滑监听不可用
        HorizontalSlideLinearLayout hsll_inActivity = mActivity.getHorizontalSlideLinearLayout();
        hsll_inActivity.setRightSlideable(false);
        hsll_inActivity.setLeftSlideable(false);
        mListView.setAdapter(mAdapter, R.id.ll_hold_list_item_view, R.id.ll_hold_list_item_expand);
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
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
        mListView = (listViewInScrollview) mScrollChild.findViewById(R.id.lv_my_hold_stock);
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sll_my_stock);
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        mScrollView.addView(mScrollChild);
        //解决第一次进入显示ListView的焦点问题
        mListView.setFocusable(false);
        mLlLoading = (LinearLayout) mScrollChild.findViewById(R.id.ll_myhold_list_loading);
        mViewPager = (ChildViewPager) mScrollChild.findViewById(R.id.vp_multi_trade);
        mLiNoData = (LinearLayout) mScrollChild.findViewById(R.id.lin_not_data_set);
        mHsllPart1 = (HorizontalSlideLinearLayout) mScrollChild.findViewById(R.id.hsll_part1);
        mHsllPart2 = (HorizontalSlideLinearLayout) mScrollChild.findViewById(R.id.hsll_part2);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_SCROLLVIEW_REFLASH, mPullToRefreshScrollView, mController);
        registerListener(MMyRRHoldStockViewController.ON_SLIDE, mHsllPart1, mController);
        registerListener(MMyRRHoldStockViewController.ON_SLIDE, mHsllPart2, mController);
        registerListener(MMyRRHoldStockViewController.ON_ACTION_CLICK, mListView, mController);
    }


    @Override
    protected void initData() {
        mActivity = (MultiCreditTradeActivity) getActivity();
        mServiceImpl = new MyRRHoldStockServiceImpl(this);
        mController = new MMyRRHoldStockViewController(this);
        mAdapter = new MyStoreListViewAdapter(mActivity,true);
        mFragmentList = new ArrayList<AbsBaseFragment>();
        mFragmentPagerOne = new RRHolderPagerFragment();
        mFragmentList.add(mFragmentPagerOne);
        mPagerAdapter = new BasePagerAdapter(getChildFragmentManager());

    }

    @Override
    protected void initViews() {
        mPagerAdapter.setFragmentsData(mFragmentList);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
        //设置禁止上拉加载更多
        mPullToRefreshScrollView.setPullLoadEnabled(false);
        //设置listview父布局
        mListView.setmParentScrollView(mScrollView);
        //        mViewPager.setmParentScrollView(mScrollView);
        //得到状态栏的高度（PX）
        int stateHeight = getStatusHeight(mActivity);
        //动态获取ListView高度 ，342为已知控件高度（dp）
        // 单位：dp，头部蓝色标题栏高度：44   基本交易的标签栏高度：37
        // 资产信息滑动页高度：220   持仓列表“头部”高度：40dp   其他（线条）：1
        float height = ScreenUtils.getScreenHeight(mActivity) - stateHeight -
                ScreenUtils.dpToPx(mActivity, (90 + 70 + 20 + 400 + 76) / 2);
        //设置ListView的高度（px）
        mListView.setMaxHeight((int) height);
        mListView.setDivider(null);
        mLlLoading.setMinimumHeight((int) height);
        mLiNoData.setMinimumHeight((int) height);
        mHsllPart1.initslideStandard(mActivity);
        mHsllPart2.initslideStandard(mActivity);
        if (preSelectPagePos == 0) {
            // 当前页是第一页时，ViewPager不需要右滑，设置横滑监听控件的右滑不可用
            mHsllPart1.setLeftSlideable(false);
            mHsllPart1.setRightSlideable(true);

        }
        //// TODO: 2016/11/7
        else if (preSelectPagePos == mPagerAdapter.getCount() - 1) {
            // 当前页是最后一页时，ViewPager不需要左滑，设置横滑监听控件的左滑不可用
            mHsllPart1.setLeftSlideable(true);
            mHsllPart1.setRightSlideable(false);
        } else {
            // 如果既不是第一页，也不是最后一页，
            // 那么Viewpager的左右滑动都需要，因此横滑控件的左右滑动都不能可用。
            mHsllPart1.setLeftSlideable(false);
            mHsllPart1.setRightSlideable(false);
        }
        mListView.setAdapter(mAdapter, R.id.ll_hold_list_item_view, R.id.ll_hold_list_item_expand);
        preSelectPagePos = 0;
        mServiceImpl.requestMyHoldStock();
        setTheme();
    }


    @Override
    protected void setTheme() {
    }

    public void onPart1LeftSlide() {
        mActivity.onLeftSlide();
    }

    public void onPart1RightSlide() {
        mActivity.onRightSlide();
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
    public void onClickHoldListviewExpandBuy(int position) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        // 获取被点击的项的股票代码
        String stockCode = mAdapter.getItem(position).getStock_code();
        // 通知activity做相应跳转和通知买卖Fragment操作
        mActivity.transferFragmentToBuySaleFromOthers(stockCode, 0);
    }

    /**
     * 持仓列表展开界面中的“卖出”按钮的点击事件
     * 点击此按钮时，进入卖出模块，并显示被点击的股票联动数据
     */
    public void onClickHoldListviewExpandSale(int position) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        // 获取被点击的项的股票代码
        String stockCode = mAdapter.getItem(position).getStock_code();
        String market = mAdapter.getItem(position).getMarket();
        // 通知activity做相应跳转和通知买卖Fragment操作
        mActivity.transferFragmentToBuySaleFromOthers(stockCode, 1);
    }

    //    /**
    //     * 持仓列表展开界面中的“行情”按钮的点击事件
    //     * 点击后，进入行情模块中的个股详情页面，但是返回后，还是本Fragment的界面
    //     */
    //    public void onClickHoldListviewExpandHq(int position) {
    //        if (TradeUtils.isFastClick()) {
    //            return;
    //        }
    //        // 获取被点击的是哪支股票，并获取其股票代码
    //        String stockCode = mAdapter.getItem(position).getStock_code();
    //        // 给行情发消息，通过股票代码查询这支股票的其他信息，
    //        // 行情的个股详情页面需要提供“股票名称”、“市场”、“股票代码”、“股票类型”四个参数。
    //        sendMsgToHqForStockList(stockCode, new MyHoldStockFragment.IHqCallBackStock() {
    //            @Override
    //            public void onGetStockMsg(ArrayList<CodeTableBean> dataList) {
    //                if (dataList != null && dataList.size() > 0) {
    //                    CodeTableBean codeTableBean = dataList.get(0);
    //                    TradeUtils.startPriceDetailStock(mActivity, codeTableBean);
    //                }
    //            }
    //        });
    //    }

    /**
     * 持仓列表展开界面中的“详情”按钮的点击事件
     * 点击后，进入个股详情页面
     */
    public void onClickHoldListviewDetails(int position) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        MyStoreStockBean bean = mAdapter.getItem(position);
        // 通知activity做相应跳转和通知买卖Fragment操作
        Intent intent = new Intent(getActivity(), MyHoldStockDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean",bean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 给行情模块发送消息，让行情模块给本类返回股票搜索提示列表
     * 这里的模块通信异步返回结果。
     */
    private void sendMsgToHqForStockList(String curEditStockCode, final IHqCallBackStock iHqCallBackStock) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("searchKey", curEditStockCode);
            jsonObject.put("num", "30");
            AppMessage msg = new AppMessage("self-stock", 60101, jsonObject);
            msg.setCallBack(new ICallBack() {
                @Override
                public void callback(Object o) {
                    ArrayList<CodeTableBean> dataList = new ArrayList<CodeTableBean>();
                    try {
                        if (o != null && !o.equals("")) {
                            String result = o.toString();
                            JSONObject resultJsonObject = new JSONObject(result);
                            JSONArray resultJsonArray = resultJsonObject.getJSONArray("results");
                            int resultJsonArrayLength = resultJsonArray.length();
                            CodeTableBean tempBean;
                            for (int i = 0; i < resultJsonArrayLength; i++) {
                                tempBean = JsonParseUtil.parseJsonToObject(resultJsonArray.getJSONObject(i), CodeTableBean.class);
                                dataList.add(tempBean);
                            }
                            iHqCallBackStock.onGetStockMsg(dataList);
                        } else {
                            ToastUtils.toast(CoreApplication.getInstance(), CoreApplication.getInstance().getResources().getString(R.string.toast_call_back_hq));
                        }
                    } catch (JSONException je) {
                        je.printStackTrace();
                    }
                }
            });
            MessageManager.getInstance(CoreApplication.getInstance()).sendMessage(msg);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    interface IHqCallBackStock {
        void onGetStockMsg(ArrayList<CodeTableBean> dataList);
    }


    /**
     * 获取持仓数据
     *
     * @param dataList
     */
    public void getStoreData(ArrayList<MyStoreStockBean> dataList) {
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
        mServiceImpl.requestMyHoldStock();
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

