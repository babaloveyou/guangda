package com.thinkive.android.trade_bz.a_hk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.activity.HKCapitalStockActivity;
import com.thinkive.android.trade_bz.a_hk.activity.HKHistoryActivity;
import com.thinkive.android.trade_bz.a_hk.activity.HKMultiTradeActivity;
import com.thinkive.android.trade_bz.a_hk.activity.HKTodayActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

/**
 * 港股通 查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/19
 */

public class HKSelectFragment extends AbsBaseFragment {
    /**
     * 撤单Fragment的宿主
     */
    protected HKMultiTradeActivity mActivity;
    /**
     * 该类的控制器
     */
    private HKSelectViewController mController;
    /**
     * 今日委托
     */
    private TextView mTvTodayEntrust;
    /**
     * 今日成交
     */
    private TextView mTvTodayTrade;
    /**
     * 历史委托
     */
    private TextView mTvHistortradeyEntrust;
    /**
     * 历史成交
     */
    private TextView mTvHistortradeyTrade;
    /**
     * 本类视图根布局
     */
    private View mRootView;
    /**
     * 滚动布局
     */
    private ScrollView mScrollView;
    /**
     * 资金股份查询
     */
    private TextView mTvSelectCapitalStock;
    /**
     * 未交易查询
     */
    private TextView mTvSelectNoTrade;
    /**
     * 标的证券查询
     */
    private TextView mTvSelectSecurities;
    /**
     * 资金明细查询
     */
    private TextView mTvSelectFund;
    /**
     * 组合费查询
     */
    private TextView mTvSelectCombined;
    /**
     * 负债查询
     */
    private TextView mTvSelectLiabilities;
    /**
     * 差价查询
     */
    private TextView mTvSelectPriceDifference;
    /**
     * 汇率查询
     */
    private TextView mTvSelectExchange;
    /**
     * 额度查询
     */
    private TextView mTvSelectLimit;
    /**
     * 投票信息查询
     */
    private TextView mTvSelectVotingMsg;
    /**
     * 投票委托查询
     */
    private TextView mTvSelectVotingTrust;
    /**
     * 公司行为信息查询
     */
    private TextView mTvSelectCorporateBehaviorMsg;
    /**
     * 公司行为委托查询
     */
    private TextView mTvSelectCorporateBehaviorTrust;
    /**
     * 对账单查询
     */
    private TextView mTvSelectStatementAccount;
    /**
     * 交割单查询
     */
    private TextView mTvSelectDeliveryOrder;
    /**
     * 通知信息查询
     */
    private TextView mTvSelectNotification;
    /**
     * 港股通交易日历查询
     */
    private TextView mTvSelectMarketDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mRootView = inflater.inflate(R.layout.fragment_hk_select_layout, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mScrollView = (ScrollView) view.findViewById(R.id.sll_layout);
        mScrollView.addView(mRootView);
        mTvTodayEntrust = (TextView) mRootView.findViewById(R.id.tv_select_today_entrust);
        mTvTodayTrade = (TextView) mRootView.findViewById(R.id.tv_select_today_trade);
        mTvHistortradeyEntrust = (TextView) mRootView.findViewById(R.id.tv_select_histortradey_entrust);
        mTvHistortradeyTrade = (TextView) mRootView.findViewById(R.id.tv_select_history_trade);
        mTvSelectCapitalStock = (TextView) mRootView.findViewById(R.id.tv_select_capital_stock);
        mTvSelectNoTrade = (TextView) mRootView.findViewById(R.id.tv_select_no_trade);
        mTvSelectSecurities = (TextView) mRootView.findViewById(R.id.tv_select_securities);
        mTvSelectFund = (TextView) mRootView.findViewById(R.id.tv_select_fund);
        mTvSelectCombined = (TextView) mRootView.findViewById(R.id.tv_select_combined);
        mTvSelectLiabilities = (TextView) mRootView.findViewById(R.id.tv_select_liabilities);
        mTvSelectPriceDifference = (TextView) mRootView.findViewById(R.id.tv_select_price_difference);
        mTvSelectExchange = (TextView) mRootView.findViewById(R.id.tv_select_exchange);
        mTvSelectLimit = (TextView) mRootView.findViewById(R.id.tv_select_limit);
        mTvSelectVotingTrust = (TextView) mRootView.findViewById(R.id.tv_select_voting_trust);
        mTvSelectCorporateBehaviorTrust = (TextView) mRootView.findViewById(R.id.tv_select_corporate_behavior_trust);
        mTvSelectVotingMsg = (TextView) mRootView.findViewById(R.id.tv_select_voting_msg);
        mTvSelectCorporateBehaviorMsg = (TextView) mRootView.findViewById(R.id.tv_select_corporate_behavior_msg);
        mTvSelectStatementAccount = (TextView) mRootView.findViewById(R.id.tv_select_statement_account);
        mTvSelectDeliveryOrder = (TextView) mRootView.findViewById(R.id.tv_select_delivery_order);
        mTvSelectNotification = (TextView) mRootView.findViewById(R.id.tv_select_notification);
        mTvSelectMarketDay = (TextView) mRootView.findViewById(R.id.tv_select_market_day);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistortradeyEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistortradeyTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectCapitalStock, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectNoTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectSecurities, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectFund, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectCombined, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectLiabilities, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectPriceDifference, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectExchange, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectLimit, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectVotingTrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectCorporateBehaviorTrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectVotingMsg, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectCorporateBehaviorMsg, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectStatementAccount, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectDeliveryOrder, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectNotification, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectMarketDay, mController);

    }

    @Override
    protected void initData() {
        mActivity = (HKMultiTradeActivity) getActivity();
        mController = new HKSelectViewController(this);
    }

    @Override
    protected void initViews() {
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 点击今日委托所执行的操作
     */
    public void TodayEntrust() {
        Intent intent = new Intent(mActivity, HKTodayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 点击今日交易所执行的操作
     */
    public void TodayTrade() {
        Intent intent = new Intent(mActivity, HKTodayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击历史委托所执行的操作
     */
    public void HistoryEntrust() {
        Intent intent = new Intent(mActivity, HKHistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击历史成交所执行的操作
     */
    public void HistoryTrade() {
        Intent intent = new Intent(mActivity, HKHistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 跳转港股查询
     *
     * @param
     */
    public void setFragmentData(String title, String hk) {
        Intent intent = new Intent(mActivity, HKCapitalStockActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("hkfragment", hk);
        startActivity(intent);
    }

}

class HKSelectViewController extends AbsBaseController implements View.OnClickListener {

    private HKSelectFragment mFragment;

    public HKSelectViewController(HKSelectFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_select_today_entrust) { // 今日委托
            mFragment.TodayEntrust();
        } else if (resId == R.id.tv_select_today_trade) { //今日交易
            mFragment.TodayTrade();
        } else if (resId == R.id.tv_select_histortradey_entrust) {//历史委托
            mFragment.HistoryEntrust();
        } else if (resId == R.id.tv_select_history_trade) {//历史交易
            mFragment.HistoryTrade();
        } else if (resId == R.id.tv_select_capital_stock) {//资金股份查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select0), "hk_0");
        } else if (resId == R.id.tv_select_no_trade) { //未交明细查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select1), "hk_1");
        } else if (resId == R.id.tv_select_securities) { //标的证券查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select2), "hk_2");
        } else if (resId == R.id.tv_select_fund) { //资金明细查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select3), "hk_3");
        } else if (resId == R.id.tv_select_combined) { //组合费查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select4), "hk_4");
        } else if (resId == R.id.tv_select_liabilities) { //负债查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select5), "hk_5");
        } else if (resId == R.id.tv_select_price_difference) { //差价查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select6), "hk_6");
        } else if (resId == R.id.tv_select_exchange) { //汇率查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select7), "hk_7");
        } else if (resId == R.id.tv_select_limit) { //额度查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select8), "hk_8");
        } else if (resId == R.id.tv_select_voting_trust) { //投票委托查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select9), "hk_9");
        } else if (resId == R.id.tv_select_corporate_behavior_trust) { //公司行为委托查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select10), "hk_10");
        } else if (resId == R.id.tv_select_statement_account) { //对账单查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select11), "hk_11");
        } else if (resId == R.id.tv_select_delivery_order) { //交割单查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select12), "hk_12");
        } else if (resId == R.id.tv_select_notification) { //通知信息查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select13), "hk_13");
        } else if (resId == R.id.tv_select_market_day) { //港股通交易日历查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select14), "hk_14");
        } else if (resId == R.id.tv_select_voting_msg) { //投票信息查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select21), "hk_15");
        } else if (resId == R.id.tv_select_corporate_behavior_msg) { //公司行为信息查询
            mFragment.setFragmentData(getContext().getResources().getString(R.string.hk_select22), "hk_16");
        }
    }
}
