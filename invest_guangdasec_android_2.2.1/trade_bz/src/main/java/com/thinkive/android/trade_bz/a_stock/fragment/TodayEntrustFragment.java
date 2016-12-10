package com.thinkive.android.trade_bz.a_stock.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
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
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.TodayEntrustDetailActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.TodayEntrustAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.TodayEntrustServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.controll.MyHoldStockViewController;
import com.thinkive.android.trade_bz.a_stock.controll.TodayEntrustController;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ScreenUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshScrollView;
import com.thinkive.android.trade_bz.views.listViewInScrollview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 今日委托
 * Announcements：
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/19
 */
public class TodayEntrustFragment extends AbsBaseFragment {
    /**
     * 该类的宿主Activity
     */
    private MultiTradeActivity mActivity;
    /**
     * 委托列表
     */
    private listViewInScrollview mListView;
    /**
     * 数据的LisView的适配器
     */
    private TodayEntrustAdapter mAdapter;
    /**
     * 该类的业务类
     */
    private TodayEntrustServicesImpl mServiceImpl = null;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;

    /**
     * 控制我的持仓
     */
    private TodayEntrustController mController;
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
        mScrollChild = inflater.inflate(R.layout.fragment_a_today_entrust, null);
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
        mListView.setAdapter(mAdapter, R.id.ll_today_entrust_list_item_view, R.id.ll_today_entrust_list_item_expand);
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
        mListView = (listViewInScrollview) mScrollChild.findViewById(R.id.lv_today_entrust);
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sll_my_stock);
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        mScrollView.addView(mScrollChild);
        //解决第一次进入显示ListView的焦点问题
        mListView.setFocusable(false);
        mLlLoading = (LinearLayout) mScrollChild.findViewById(R.id.ll_today_entrust_loading);
        mLiNoData = (LinearLayout) mScrollChild.findViewById(R.id.lin_not_data_set);
        mHsllPart = (HorizontalSlideLinearLayout) mScrollChild.findViewById(R.id.hsll_part);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_SCROLLVIEW_REFLASH, mPullToRefreshScrollView, mController);
        registerListener(MyHoldStockViewController.ON_ACTION_CLICK, mListView, mController);
        registerListener(MyHoldStockViewController.ON_SLIDE, mHsllPart, mController);
    }


    @Override
    protected void initData() {
        mActivity = (MultiTradeActivity) getActivity();
        mServiceImpl = new TodayEntrustServicesImpl(this);
        mController = new TodayEntrustController(this);
        mAdapter = new TodayEntrustAdapter(mActivity);
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
        mServiceImpl.requestTodayEntrust();
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
        RevocationBean bean = mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), TodayEntrustDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 给行情模块发送消息，让行情模块给本类返回股票搜索提示列表
     * 这里的模块通信异步返回结果。
     */
    private void sendMsgToHqForStockList(String curEditStockCode, final MyHoldStockFragment.IHqCallBackStock iHqCallBackStock) {
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
    public void onGetTodayEntrustData(ArrayList<RevocationBean> dataList) {
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
        mServiceImpl.requestTodayEntrust();
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
