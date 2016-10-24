package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.activity.NewStockMainActivity;
import com.thinkive.android.trade_bz.a_stock.activity.HistoryEntrustOrTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.StatementAccountActivity;
import com.thinkive.android.trade_bz.a_stock.activity.TodayEntrustOrTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.TodayMoneyActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 查询Fragment
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/19
 */
public class SelectFragment extends AbsBaseFragment {
    /**
     * 撤单Fragment的宿主
     */
    private MultiTradeActivity mMultiTradeActivity;
    /**
     * 该类的控制器
     */
    private SelsectViewController mController;
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
     * 对账单
     */
    private TextView mTvMoneyAccount;
    /**
     * 当日资金流水
     */
    private TextView mTvTodayMoney;
    /**
     * 配号
     */
    private TextView mTvNumber;
    /**
     * 中签
     */
    private TextView mTvNumberwinning;
    /**
     * 本类视图根布局
     */
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_a_select, null);
            findViews(mRootView);
            initData();
            setListeners();
            initViews();
        }
        return mRootView;
    }

    @Override
    protected void findViews(View view) {
        mTvTodayEntrust = (TextView) view.findViewById(R.id.tv_select_today_entrust);
        mTvTodayTrade = (TextView) view.findViewById(R.id.tv_select_today_trade);
        mTvHistortradeyEntrust = (TextView) view.findViewById(R.id.tv_select_histortradey_entrust);
        mTvHistortradeyTrade = (TextView) view.findViewById(R.id.tv_select_history_trade);
        mTvMoneyAccount = (TextView) view.findViewById(R.id.tv_select_statement_account);
        mTvTodayMoney = (TextView) view.findViewById(R.id.tv_select_today_money);
        mTvNumber = (TextView) view.findViewById(R.id.tv_select_distribute_number);
        mTvNumberwinning = (TextView) view.findViewById(R.id.tv_select_numberwinning);

    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistortradeyEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistortradeyTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvMoneyAccount, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayMoney, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvNumber, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvNumberwinning, mController);
    }

    @Override
    protected void initData() {
        mMultiTradeActivity = (MultiTradeActivity) getActivity();
        mController = new SelsectViewController(this);
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
        Intent intent = new Intent(mMultiTradeActivity, TodayEntrustOrTradeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 点击今日交易所执行的操作
     */
    public void TodayTrade() {
        Intent intent = new Intent(mMultiTradeActivity, TodayEntrustOrTradeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 点击历史委托所执行的操作
     */
    public void HistoryEntrust() {
        Intent intent = new Intent(mMultiTradeActivity, HistoryEntrustOrTradeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 点击历史成交所执行的操作
     */
    public void HistoryTrade() {
        Intent intent = new Intent(mMultiTradeActivity, HistoryEntrustOrTradeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击对账单所执行的操作
     */
    public void MoneyAccount() {
        startActivity(new Intent(mMultiTradeActivity, StatementAccountActivity.class));
    }


    /**
     * 点击今日资金流水所执行的操作
     */
    public void TodayMoney() {
        startActivity(new Intent(mMultiTradeActivity, TodayMoneyActivity.class));
    }

    /**
     * 点击配号所执行的操作
     */
    public void PeiNumber() {
        Intent intent = new Intent(mMultiTradeActivity, NewStockMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        bundle.putString("userType", TradeLoginManager.LOGIN_TYPE_NORMAL);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击中签所执行的操作
     */
    public void Numberwinning() {
        Intent intent = new Intent(mMultiTradeActivity, NewStockMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 2);
        bundle.putString("userType", TradeLoginManager.LOGIN_TYPE_NORMAL);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

class SelsectViewController extends AbsBaseController implements View.OnClickListener {

    private SelectFragment mSelectFragment;

    public SelsectViewController(SelectFragment mFragment) {
        mSelectFragment = mFragment;
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
        if (TradeUtils.isFastClick()) {
            return;
        }
        if (resId == R.id.tv_select_today_entrust) { // 今日委托
            mSelectFragment.TodayEntrust();
        } else if (resId == R.id.tv_select_today_trade) { //今日交易
            mSelectFragment.TodayTrade();
        } else if (resId == R.id.tv_select_histortradey_entrust) {//历史委托
            mSelectFragment.HistoryEntrust();
        } else if (resId == R.id.tv_select_history_trade) {//历史交易
            mSelectFragment.HistoryTrade();
        } else if (resId == R.id.tv_select_statement_account) {//对账单
            mSelectFragment.MoneyAccount();
        } else if (resId == R.id.tv_select_today_money) {//今日资金流水
            mSelectFragment.TodayMoney();
        } else if (resId == R.id.tv_select_distribute_number) {//配号
            mSelectFragment.PeiNumber();
        } else if (resId == R.id.tv_select_numberwinning) {//中签
            mSelectFragment.Numberwinning();
        }
    }
}
