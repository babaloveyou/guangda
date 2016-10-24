package com.thinkive.android.trade_bz.a_out.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.activity.FundTradeMainActivity;
import com.thinkive.android.trade_bz.a_out.adapter.FundHoldCountAdapter;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.a_out.bll.FundHoldStockServicesImpl;
import com.thinkive.android.trade_bz.a_stock.activity.TransferBanktActivity;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ScreenUtils;
import com.thinkive.android.trade_bz.utils.StringUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshScrollView;
import com.thinkive.android.trade_bz.views.listViewInScrollview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 基金交易之持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/6
 */
public class FundHoldeCountFragment extends AbsBaseFragment {
    /**
     * 该类的宿主Activity
     */
    private FundTradeMainActivity mFundTradeActivity = null;
    /**
     * 业务类
     */
    private FundHoldStockServicesImpl mServices;
    /**
     * 适配器
     */
    private FundHoldCountAdapter mAdapter;
    /**
     * 控制类
     */
    private FundHoldCountViewController mController;
    /**
     * 持仓数据的ListView
     */
    private listViewInScrollview mListView;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLlLoading;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 被加载的 ScrollView 子布局
     */
    private View mScrollChild;
    /**
     * 自定义的ScrollView
     */
    private PullToRefreshScrollView mPullToRefreshScrollView;
    /**
     * 父类布局ScrollView
     */
    private ScrollView mScrollView;
    /**
     * 总资产
     */
    private TextView mTvAllMoney;
    private TextView mTvAllMoneya;
    /**
     * 可用
     */
    private TextView mTvCanUse;
    private TextView mTvCanUsea;
    /**
     * 可取
     */
    private TextView mTvCanGet;
    private TextView mTvCanGeta;
    /**
     * 基金市值
     */
    private TextView mTvFundMarketValue;
    private TextView mTvFundMarketValuea;
    /**
     * 持有基金
     */
    private TextView mTvHoldFund;
    /**
     * 银行转账按钮
     */
    private TextView mTvBlankMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_scrollview, null);
        mScrollChild = inflater.inflate(R.layout.fragment_fund_hold_stock, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(),"查询中"+new Date());
        mServices.requestHoldFundData();
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sll_my_stock);
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        mScrollView.addView(mScrollChild);
        mListView = (listViewInScrollview) mScrollChild.findViewById(R.id.lv_fund_hold_stock);
        mListView.setFocusable(false);
        mListView.setDivider(null);
        mLlLoading = (LinearLayout) mScrollChild.findViewById(R.id.ll_fselect_trade_loading);
        mLiNoData = (LinearLayout) mScrollChild.findViewById(R.id.lin_not_data_set);
        mTvBlankMoney = (TextView) view.findViewById(R.id.tv_holdpager_bank);

        mTvAllMoney = (TextView) view.findViewById(R.id.tv_myhold_money);
        mTvFundMarketValue = (TextView) view.findViewById(R.id.tv_myhold_port);
        mTvHoldFund = (TextView) view.findViewById(R.id.tv_myhold_value);
        mTvCanUse = (TextView) view.findViewById(R.id.tv_myhold_canuse);
        mTvCanGet = (TextView) view.findViewById(R.id.tv_myhold_canget);
        mTvAllMoneya = (TextView) view.findViewById(R.id.tv_myhold_moneya);
        mTvFundMarketValuea = (TextView) view.findViewById(R.id.tv_myhold_porta);
        mTvCanUsea = (TextView) view.findViewById(R.id.tv_myhold_canusea);
        mTvCanGeta = (TextView) view.findViewById(R.id.tv_myhold_cangeta);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mTvBlankMoney, mController);
        registerListener(AbsBaseController.ON_SCROLLVIEW_REFLASH, mPullToRefreshScrollView, mController);
    }


    @Override
    protected void initData() {
        mFundTradeActivity = (FundTradeMainActivity) getActivity();
        mServices = new FundHoldStockServicesImpl(this);
        mAdapter = new FundHoldCountAdapter(mFundTradeActivity);
        mController = new FundHoldCountViewController(this);
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mPullToRefreshScrollView.setPullLoadEnabled(false);
        mListView.setmParentScrollView(mScrollView);
        int stateHeight = ScreenUtils.getStatusHeight(mFundTradeActivity);
        //tab栏44dp  ,title栏44dp ，持仓模块200dp
        float height = ScreenUtils.getScreenHeight(mFundTradeActivity) - stateHeight -
                ScreenUtils.dpToPx(mFundTradeActivity, 288);
        //设置ListView的高度（px）
        mListView.setMaxHeight((int) height);
        mListView.setDivider(null);
        mLlLoading.setMinimumHeight((int) height);
        mLiNoData.setMinimumHeight((int) height);
        //持仓数据请求
        mServices.requestHoldStockList();
        setTheme();
    }

    @Override
    protected void setTheme() {}

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
        mServices.requestHoldStockList();
    }

    /**
     * 获取持仓数据列表
     *
     * @param dataList
     */
    public void onGetFundHoldStockData(ArrayList<FundHoldBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLiNoData.setVisibility(View.VISIBLE);
            mLlLoading.setVisibility(View.GONE);
            mListView.setVisibility(View.GONE);
        } else {
            mTvHoldFund.setText(dataList.size()+"");
            mLiNoData.setVisibility(View.GONE);
            mLlLoading.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
            String fundMarketValue = calculateAllProfit(dataList);
            mTvFundMarketValue.setText(StringUtils.subStringBefor(fundMarketValue));
            mTvFundMarketValuea.setText(StringUtils.subStringAfter(fundMarketValue));
        }
        refreshComplete();
    }

    /**
     * 基金市值累加
     * @return
     */
    public String calculateAllProfit(ArrayList<FundHoldBean> dataList) {
        double sum = 0;
        if (dataList != null) {
            for (FundHoldBean bean : dataList) {
                try {
                    double floatYk = Double.parseDouble(bean.getMarket_value());
                    sum += floatYk;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(sum);
    }

    /**
     * 获得当前持仓信息
     * @param bean
     */
    public void getFundHoldMessage(MoneySelectBean bean) {
        Log.d(getClass().getSimpleName(),"查询结束"+new Date());
        if(bean == null)
            return;
        mTvAllMoney.setText(StringUtils.subStringBefor(bean.getAssert_val()));
        mTvAllMoneya.setText(StringUtils.subStringAfter(bean.getAssert_val()));

        mTvCanUse.setText(StringUtils.subStringBefor(bean.getEnable_balance()));

        mTvCanGet.setText(StringUtils.subStringBefor(bean.getFetch_balance()));
        mTvCanGeta.setText(StringUtils.subStringAfter(bean.getFetch_balance()));

    }

    /**
     * 点击银证转账
     */
    public void clickBtnClick() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("userType", TradeLoginManager.LOGIN_TYPE_NORMAL);
        Intent intent = new Intent(mFundTradeActivity, TransferBanktActivity.class);
        intent.putExtras(bundle);
        mFundTradeActivity.startActivity(intent);
    }
}

class FundHoldCountViewController extends AbsBaseController implements View.OnClickListener
        , PullToRefreshListView.OnRefreshListener{

    private FundHoldeCountFragment mFragment;

    public FundHoldCountViewController(FundHoldeCountFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_SCROLLVIEW_REFLASH:
                ((PullToRefreshScrollView) view).setOnRefreshListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_holdpager_bank) { // 银证转账
            mFragment.clickBtnClick();
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

