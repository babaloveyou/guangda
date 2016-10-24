package com.thinkive.android.trade_bz.a_trans.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.activity.TransSelectTicketActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransStockHistoryActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransStockMainActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransStockTodayActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransSubHqOneActivityTrade;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 转股交易 查询
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/30
 */
public class TransStockSelectFragment  extends AbsBaseFragment {
    private TransStockMainActivityTrade mActivity;
    private TransSelectController mController;
    /**
     * 今日委托
     */
    private TextView mTvTransToday;
    /**
     * 历史委托
     */
    private TextView mTvTransHistory;
    /**
     * 定价申报行情
     */
    private TextView mTvTransPriceHq;
    /**
     * 挂牌股票查询
     */
    private TextView mTvMarketTicket;
    /**
     * 当日成交
     */
    private TextView mTvTodayTrade;
    /**
     * 历史成交
     */
    private TextView mTvHistoryTrade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trans_select, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvTransToday =(TextView)view.findViewById(R.id.tv_trans_today);
        mTvTransHistory =(TextView)view.findViewById(R.id.tv_trans_history);
        mTvTransPriceHq =(TextView)view.findViewById(R.id.tv_trans_price_hq);
        mTvMarketTicket =(TextView)view.findViewById(R.id.tv_trans_market_ticket);
        mTvTodayTrade =(TextView)view.findViewById(R.id.tv_trans_today2);
        mTvHistoryTrade =(TextView)view.findViewById(R.id.tv_trans_history2);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransToday, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransPriceHq, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransHistory, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvMarketTicket, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryTrade, mController);
    }

    @Override
    protected void initData() {
        mActivity=(TransStockMainActivityTrade)getActivity();
        mController=new TransSelectController(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 今日委托
     */
    public void onClickTodayEntrust(){
        Intent intent=new Intent(mActivity,TransStockTodayActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 历史委托
     */
    public void onClickHistoryEntrust(){
        Intent intent=new Intent(mActivity,TransStockHistoryActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 今日成交
     */
    public void onClickTodayTrade(){
        Intent intent=new Intent(mActivity,TransStockTodayActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 历史成交
     */
    public void onClickHistoryTrade(){
        Intent intent=new Intent(mActivity,TransStockHistoryActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 定价申报行情
     */
    public void onClickDirPriceHq(){
        startActivity(new Intent(mActivity, TransSubHqOneActivityTrade.class));
    }
    /**
     * 挂牌股票查询
     */
    public void onClickMarketTicket(){
        startActivity(new Intent(mActivity, TransSelectTicketActivityTrade.class));
    }
}


class TransSelectController extends AbsBaseController implements View.OnClickListener {

    private TransStockSelectFragment mFragment;

    public TransSelectController(TransStockSelectFragment mFragment) {
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
        if (TradeUtils.isFastClick()) {
            return;
        }
        if (resId == R.id.tv_trans_today) {
            mFragment.onClickTodayEntrust();
        } else if (resId == R.id.tv_trans_history) {
            mFragment.onClickHistoryEntrust();
        } else if (resId == R.id.tv_trans_price_hq) {
            mFragment.onClickDirPriceHq();
        } else if (resId == R.id.tv_trans_market_ticket) {
            mFragment.onClickMarketTicket();
        } else if (resId == R.id.tv_trans_today2) {
            mFragment.onClickTodayTrade();
        } else if (resId == R.id.tv_trans_history2) {
            mFragment.onClickHistoryTrade();
        }
    }
}

