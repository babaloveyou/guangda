package com.thinkive.android.trade_bz.a_trans.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.activity.TransLimitBuyOrSaleActivity;
import com.thinkive.android.trade_bz.a_trans.activity.TransMarketBuyOrSaleActivity;
import com.thinkive.android.trade_bz.a_trans.activity.TransRevocationActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransStockMainActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransSubBuyOrSaleActivity;
import com.thinkive.android.trade_bz.a_trans.activity.TransSureBuyOrSaleActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransTradeBuyOrSaleActivityTrade;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 转股交易 下单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/30
 */
public class TransStockBuyFragment extends AbsBaseFragment {
    private TransStockBuyController mController;
    private TransStockMainActivityTrade mActivity;
    private View mChildScrollView;
    private ScrollView mScrollView;
    /**
     * 成交确认买入
     */
    private TextView mTvTradeBuy;
    /**
     * 成交确认卖出
     */
    private TextView mTvTradeSale;
    /**
     * 互报确认买入
     */
    private TextView mTvSureBuy;
    /**
     * 互报确认卖出
     */
    private TextView mTvSureSale;
    /**
     * 限价买入
     */
    private TextView mTvTransLimitBuy;
    /**
     * 限价卖出
     */
    private TextView mTvTransLimitSale;
    /**
     * 定价申报买入
     */
    private TextView mTvTransSubBuy;
    /**
     * 定价申报卖出
     */
    private TextView mTvTransSubSale;
    /**
     * 做市买入
     */
    private TextView mTvTransMarketBuy;
    /**
     * 做市卖出
     */
    private TextView mTvTransMarketSale;
    /**
     * 撤单
     */
    private TextView mTvTransRevocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mChildScrollView =inflater.inflate(R.layout.fragment_trans_buy, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mScrollView=(ScrollView)view.findViewById(R.id.sll_layout);
        mScrollView.addView(mChildScrollView);
        mTvTradeBuy = (TextView) mChildScrollView.findViewById(R.id.tv_trans_trade_buy);
        mTvTradeSale = (TextView) mChildScrollView.findViewById(R.id.tv_trans_trade_sale);
        mTvSureBuy = (TextView) mChildScrollView.findViewById(R.id.tv_trans_sure_buy);
        mTvSureSale = (TextView) mChildScrollView.findViewById(R.id.tv_trans_sure_sale);
        mTvTransLimitBuy = (TextView) mChildScrollView.findViewById(R.id.tv_trans_buy);
        mTvTransLimitSale = (TextView) mChildScrollView.findViewById(R.id.tv_trans_sale);
        mTvTransSubBuy = (TextView) mChildScrollView.findViewById(R.id.tv_trans_sub_buy);
        mTvTransSubSale = (TextView) mChildScrollView.findViewById(R.id.tv_trans_sub_sale);
        mTvTransMarketBuy = (TextView) mChildScrollView.findViewById(R.id.tv_trans_market_buy);
        mTvTransMarketSale = (TextView) mChildScrollView.findViewById(R.id.tv_trans_market_sale);
        mTvTransRevocation = (TextView) mChildScrollView.findViewById(R.id.tv_trans_revocation);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTradeBuy, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTradeSale, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSureBuy, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSureSale, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransLimitBuy, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransLimitSale, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransSubBuy, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransSubSale, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransMarketBuy, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransMarketSale, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransRevocation, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TransStockMainActivityTrade) getActivity();
        mController = new TransStockBuyController(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 成交确认买入
     */
    public void onClickTradeBuy() {
        Intent intent = new Intent(mActivity, TransTradeBuyOrSaleActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 成交确认卖出
     */
    public void onClickTradeSale() {
        Intent intent = new Intent(mActivity, TransTradeBuyOrSaleActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 互报确认买入
     */
    public void onClickSureBuy() {
        Intent intent = new Intent(mActivity, TransSureBuyOrSaleActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 互报确认卖出
     */
    public void onClickSureSale() {
        Intent intent = new Intent(mActivity, TransSureBuyOrSaleActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 限价买入
     */
    public void onClickTransLimitBuy() {
        Intent intent = new Intent(mActivity, TransLimitBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 限价卖出
     */
    public void onClickTransLimitSale() {
        Intent intent = new Intent(mActivity, TransLimitBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 定价申报买入
     */
    public void onClickTransSubBuy() {
        Intent intent = new Intent(mActivity, TransSubBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 定价申报卖出
     */
    public void onClickTransSubSale() {
        Intent intent = new Intent(mActivity, TransSubBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 做市买入
     */
    public void onClickTransMarketBuy() {
        Intent intent = new Intent(mActivity, TransMarketBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 做市卖出
     */
    public void onClickTransMarketSale() {
        Intent intent = new Intent(mActivity, TransMarketBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 撤单
     */
    public void onClickTransRevocation() {
        startActivity(new Intent(mActivity, TransRevocationActivityTrade.class));
    }
}

class TransStockBuyController extends AbsBaseController implements View.OnClickListener {

    private TransStockBuyFragment mFragment;

    public TransStockBuyController(TransStockBuyFragment mFragment) {
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
        if (resId == R.id.tv_trans_trade_buy) {
            mFragment.onClickTradeBuy();
        } else if (resId == R.id.tv_trans_trade_sale) {
            mFragment.onClickTradeSale();
        } else if (resId == R.id.tv_trans_sure_buy) {
            mFragment.onClickSureBuy();
        } else if (resId == R.id.tv_trans_sure_sale) {
            mFragment.onClickSureSale();
        } else if (resId == R.id.tv_trans_buy) {
            mFragment.onClickTransLimitBuy();
        } else if (resId == R.id.tv_trans_sale) {
            mFragment.onClickTransLimitSale();
        } else if (resId == R.id.tv_trans_sub_buy) {
            mFragment.onClickTransSubBuy();
        } else if (resId == R.id.tv_trans_sub_sale) {
            mFragment.onClickTransSubSale();
        } else if (resId == R.id.tv_trans_market_buy) {
            mFragment.onClickTransMarketBuy();
        }else if (resId == R.id.tv_trans_market_sale) {
            mFragment.onClickTransMarketSale();
        } else if (resId == R.id.tv_trans_revocation) {
            mFragment.onClickTransRevocation();
        }
    }
}

