package com.thinkive.android.trade_bz.a_out.fragment;

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
import com.thinkive.android.trade_bz.a_out.activity.FundAccountActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundConvertActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundPledgeActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundRansomActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundRevocationActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundRiskActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundScribeActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundShareSetActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundSubscriptionActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundTradeMainActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundTransferEntrustActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 场外基金下单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/23
 */
public class FundBuyFragment extends AbsBaseFragment {
    /**
     * 控制类
     */
    private FundBuyController mController;
    /**
     * 环境
     */
    private FundTradeMainActivity mActivity;
    /**
     * 申购
     */
    private TextView mTvFundSubtion;
    /**
     * 认购
     */
    private TextView mTvFundSub;
    /**
     * 赎回
     */
    private TextView mTvFundRansom;
    /**
     * 转换
     */
    private TextView mTvFundConvert;
    /**
     * 转托
     */
    private TextView mTvFundEntrust;
    /**
     * 分红设置
     */
    private TextView mTvFundSet;
    /**
     * 基金账户
     */
    private TextView mTvFundAccount;
    /**
     * 风险测评
     */
    private TextView mTvFundRisk;
    /**
     * 基金定投
     */
    private TextView mTvFundDirection;
    /**
     * 基金撤单
     */
    private TextView mTvFundRemove;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_buy, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvFundSubtion = (TextView) view.findViewById(R.id.tv_out_fund_subtion);
        mTvFundSub = (TextView) view.findViewById(R.id.tv_out_fund_sub);
        mTvFundRansom = (TextView) view.findViewById(R.id.tv_out_fund_ransom);
        mTvFundConvert = (TextView) view.findViewById(R.id.tv_out_fund_convert);
        mTvFundEntrust = (TextView) view.findViewById(R.id.tv_out_fund_entrust);
        mTvFundSet = (TextView) view.findViewById(R.id.tv_out_fund_set);
        mTvFundAccount = (TextView) view.findViewById(R.id.tv_out_fund_account);
        mTvFundRisk = (TextView) view.findViewById(R.id.tv_out_fund_risk);
        mTvFundDirection = (TextView) view.findViewById(R.id.tv_out_fund_direction);
        mTvFundRemove = (TextView) view.findViewById(R.id.tv_out_fund_remove);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundSubtion, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundSub, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundRansom, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundConvert, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundSet, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundAccount, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundRisk, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundDirection, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundRemove, mController);
    }

    @Override
    protected void initData() {
        mController = new FundBuyController(this);
        mActivity = (FundTradeMainActivity) getActivity();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 申购
     */
    public void onClickSubscribe() {
        Intent intent = new Intent(mActivity, FundScribeActivity.class);
        startActivity(intent);
    }

    /**
     * 认购
     */
    public void onClickSub() {
        Intent intent = new Intent(mActivity, FundSubscriptionActivity.class);
        startActivity(intent);
    }

    /**
     * 赎回
     */
    public void onClickRansom() {
        Intent intent = new Intent(mActivity, FundRansomActivity.class);
        startActivity(intent);
    }

    /**
     * 转换
     */
    public void onClickConvert() {
        Intent intent = new Intent(mActivity, FundConvertActivity.class);
        startActivity(intent);
    }

    /**
     * 转托
     */
    public void onClickEntrust() {
        Intent intent = new Intent(mActivity, FundTransferEntrustActivity.class);
        startActivity(intent);
    }

    /**
     * 分红
     */
    public void onClickSet() {
        Intent intent = new Intent(mActivity, FundShareSetActivity.class);
        startActivity(intent);
    }

    /**
     * 账户
     */
    public void onClickAccount() {
        Intent intent = new Intent(mActivity, FundAccountActivity.class);
        startActivity(intent);
    }

    /**
     * 风险测评
     */
    public void onClickRisk() {
        Intent intent = new Intent(mActivity, FundRiskActivity.class);
        startActivity(intent);
    }

    /**
     * 基金定投
     */
    public void onClickDirection() {
        Intent intent = new Intent(mActivity, FundPledgeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 基金撤单
     */
    public void onClickRevocation() {
        Intent intent = new Intent(mActivity, FundRevocationActivity.class);
        startActivity(intent);
    }


}

class FundBuyController extends AbsBaseController implements View.OnClickListener {

    private FundBuyFragment mFragment;

    public FundBuyController(FundBuyFragment mFragment) {
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
        if (resId == R.id.tv_out_fund_subtion) {
            mFragment.onClickSubscribe();
        } else if (resId == R.id.tv_out_fund_sub) {
            mFragment.onClickSub();
        } else if (resId == R.id.tv_out_fund_ransom) {
            mFragment.onClickRansom();
        } else if (resId == R.id.tv_out_fund_convert) {
            mFragment.onClickConvert();
        } else if (resId == R.id.tv_out_fund_entrust) {
            mFragment.onClickEntrust();
        } else if (resId == R.id.tv_out_fund_set) {
            mFragment.onClickSet();
        } else if (resId == R.id.tv_out_fund_account) {
            mFragment.onClickAccount();
        } else if (resId == R.id.tv_out_fund_risk) {
            mFragment.onClickRisk();
        } else if (resId == R.id.tv_out_fund_direction) {
            mFragment.onClickDirection();
        } else if (resId == R.id.tv_out_fund_remove) {
            mFragment.onClickRevocation();
        }
    }
}
