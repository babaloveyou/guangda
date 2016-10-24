package com.thinkive.android.trade_bz.a_trans.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.TransferBanktActivity;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.adapter.TransHoldStockAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransHoldStockBean;
import com.thinkive.android.trade_bz.a_trans.bll.TransStockHoldServicesImpl;
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

/**
 * 转股交易 我的持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/30
 */
public class TransStockHoldFragment extends AbsBaseFragment {
    /**
     * 该类的宿主Activity
     */
    private TKFragmentActivity mActivity = null;
    /**
     * 持仓数据的ListView
     */
    private listViewInScrollview mListView;
    /**
     * 银行转账按钮
     */
    private TextView mBtClickBlank;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLlLoading;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 业务类
     */
    private TransStockHoldServicesImpl mServices;
    /**
     * 适配器
     */
    private TransHoldStockAdapter mAdapter;
    /**
     * 控制类
     */
    private TransStockController mController;
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
     * 总盈亏
     */
    private TextView mTvAllPorfit;
    private TextView mTvAllPorfita;
    /**
     * 总市值
     */
    private TextView mTvTodayProfit;
    private TextView mTvTodayProfita;
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
     * 开启定时器，每隔4秒刷新持仓
     */
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run () {
            mServices.getHoldList();
            handler.postDelayed(this,4000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_scrollview, null);
        mScrollChild = inflater.inflate(R.layout.fragment_trans_stock_hold, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sll_my_stock);
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        mScrollView.addView(mScrollChild);
        mListView = (listViewInScrollview) mScrollChild.findViewById(R.id.lv_fund_hold_stock);
        mListView.setFocusable(false);
        mBtClickBlank = (TextView) mScrollChild.findViewById(R.id.tv_trans_bank_click);
        mLlLoading = (LinearLayout) mScrollChild.findViewById(R.id.ll_fselect_trade_loading);
        mLiNoData = (LinearLayout) mScrollChild.findViewById(R.id.lin_not_data_set);
        mTvAllMoney = (TextView) view.findViewById(R.id.tv_myhold_money);
        mTvAllPorfit = (TextView) view.findViewById(R.id.tv_myhold_port);
        mTvTodayProfit = (TextView) view.findViewById(R.id.tv_myhold_value);
        mTvCanUse = (TextView) view.findViewById(R.id.tv_myhold_canuse);
        mTvCanGet = (TextView) view.findViewById(R.id.tv_myhold_canget);
        mTvAllMoneya = (TextView) view.findViewById(R.id.tv_myhold_moneya);
        mTvAllPorfita = (TextView) view.findViewById(R.id.tv_myhold_porta);
        mTvTodayProfita = (TextView) view.findViewById(R.id.tv_myhold_valuea);
        mTvCanUsea = (TextView) view.findViewById(R.id.tv_myhold_canusea);
        mTvCanGeta = (TextView) view.findViewById(R.id.tv_myhold_cangeta);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mBtClickBlank, mController);
        registerListener(AbsBaseController.ON_SCROLLVIEW_REFLASH, mPullToRefreshScrollView, mController);
    }


    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mServices = new TransStockHoldServicesImpl(this);
        mAdapter = new TransHoldStockAdapter(mActivity);
        mController = new TransStockController(this);
        mServices.requestHoldFundData();
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mPullToRefreshScrollView.setPullLoadEnabled(false);
        mListView.setmParentScrollView(mScrollView);
        int stateHeight = ScreenUtils.getStatusHeight(mActivity);
        //tab栏44dp  ,title栏44dp ，持仓模块220dp ,持仓数据栏40dp,分隔线1dp
        float height = ScreenUtils.getScreenHeight(mActivity) - stateHeight -
                ScreenUtils.dpToPx(mActivity, 349);
        //设置ListView的高度（px）
        mListView.setMaxHeight((int) height);
        mListView.setDivider(null);
        mLlLoading.setMinimumHeight((int) height);
        mLiNoData.setMinimumHeight((int) height);
        mListView.setAdapter(mAdapter);
        //持仓数据请求
        mServices.getHoldList();
        setTheme();
    }

    @Override
    protected void setTheme() {
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
        mServices.requestHoldFundData();
    }
    /**
     * 获取持仓数据列表
     * @param dataList
     */
    public void getFundHoldStockData(ArrayList<TransHoldStockBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLiNoData.setVisibility(View.VISIBLE);
            mLlLoading.setVisibility(View.GONE);
            mListView.setVisibility(View.GONE);
        } else {
            mLiNoData.setVisibility(View.GONE);
            mLlLoading.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mAdapter.notifyDataSetChanged();
//            calculateAllProfit(dataList);
        }
    }

    /**
     * 盈亏累加（总盈亏）
     * @return
     */
    public void calculateAllProfit(ArrayList<TransHoldStockBean> dataList){
        DecimalFormat df = new DecimalFormat("##0.00");
        double sum = 0;
        if(dataList != null){
            for(TransHoldStockBean bean:dataList){
                try {
                    double floatYk = Double.parseDouble(bean.getFloat_yk());
                    sum +=floatYk;
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }
        String profit = df.format(sum);
        mTvAllPorfit.setText(StringUtils.subStringBefor(profit));
        mTvAllPorfita.setText(StringUtils.subStringAfter(profit));
    }

    /**
     * 获得当前持仓信息
     * @param bean
     */
    public void getFundHoldMessage(MoneySelectBean bean) {
        mTvAllMoney.setText(StringUtils.subStringBefor(bean.getAssert_val()));
        mTvAllMoneya.setText(StringUtils.subStringAfter(bean.getAssert_val()));

        mTvTodayProfit.setText(StringUtils.subStringBefor(bean.getDaily_income_balance()));
        mTvTodayProfita.setText(StringUtils.subStringAfter(bean.getDaily_income_balance()));

        mTvAllPorfit.setText(StringUtils.subStringBefor(bean.getTotal_income_balance()));
        mTvAllPorfita.setText(StringUtils.subStringAfter(bean.getTotal_income_balance()));

        mTvCanUse.setText(StringUtils.subStringBefor(bean.getEnable_balance()));
        mTvCanUsea.setText(StringUtils.subStringAfter(bean.getEnable_balance()));

        mTvCanGet.setText(StringUtils.subStringBefor(bean.getFetch_balance()));
        mTvCanGeta.setText(StringUtils.subStringAfter(bean.getFetch_balance()));

        refreshComplete();
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
        Intent intent = new Intent(mActivity, TransferBanktActivity.class);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
}

class TransStockController extends AbsBaseController implements
        View.OnClickListener, PullToRefreshListView.OnRefreshListener {

    private TransStockHoldFragment mFragment;

    public TransStockController(TransStockHoldFragment fragment) {
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
        if (resId == R.id.tv_trans_bank_click) { // 银证转账
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

