package com.thinkive.android.trade_bz.a_level.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_level.activity.LFundDevideActivity;
import com.thinkive.android.trade_bz.a_level.activity.LFundMergerActivity;
import com.thinkive.android.trade_bz.a_level.activity.LFundRansomActivity;
import com.thinkive.android.trade_bz.a_level.activity.LFundRevocationActivity;
import com.thinkive.android.trade_bz.a_level.activity.LFundScribeActivity;
import com.thinkive.android.trade_bz.a_level.activity.LFundSubscriptionActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 分级基金下单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/23
 */
public class LFundBuyFragment extends AbsBaseFragment {
    private LFundBuyController mController;
    private AppCompatActivity mActivity;
    /**
     * 申购
     */
    private TextView mTvLevelSub;
    /**
     * 认购
     */
    private TextView mTvLevelSubtion;
    /**
     * 赎回
     */
    private TextView mTvLevelRansom;
    /**
     * 撤单
     */
    private TextView mTvLevelRevocation;
    /**
     * 基金分拆
     */
    private TextView mTvLevelDivide;
    /**
     * 基金合并
     */
    private TextView mTvLevelTogether;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level_fund_buy, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvLevelSub = (TextView) view.findViewById(R.id.tv_level_buy_sub);
        mTvLevelSubtion = (TextView) view.findViewById(R.id.tv_level_buy_subtion);
        mTvLevelRansom = (TextView) view.findViewById(R.id.tv_level_buy_ransom);
        mTvLevelRevocation = (TextView) view.findViewById(R.id.tv_level_buy_revocation);
        mTvLevelDivide = (TextView) view.findViewById(R.id.tv_level_buy_divide);
        mTvLevelTogether = (TextView) view.findViewById(R.id.tv_level_buy_together);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvLevelSub, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvLevelSubtion, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvLevelRansom, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvLevelRevocation, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvLevelDivide, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvLevelTogether, mController);
    }

    @Override
    protected void initData() {
        mActivity = (AppCompatActivity) getActivity();
        mController = new LFundBuyController(this);
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
    public void onClickLevelSub() {
        Intent intent = new Intent(mActivity, LFundScribeActivity.class);
        startActivity(intent);
    }

    /**
     * 认购
     */
    public void onClickLevelSubtion() {
        Intent intent = new Intent(mActivity, LFundSubscriptionActivity.class);
        startActivity(intent);
    }

    /**
     * 赎回
     */
    public void onClickLevelRansom() {
        Intent intent = new Intent(mActivity, LFundRansomActivity.class);
        startActivity(intent);
    }

    /**
     * 撤单
     */
    public void onClickLevelRevocation() {
        Intent intent = new Intent(mActivity, LFundRevocationActivity.class);
        startActivity(intent);
    }
    /**
     * 基金拆分
     */
    public void onClickLevelDevide() {
        Intent intent = new Intent(mActivity, LFundDevideActivity.class);
        startActivity(intent);
    }

    /**
     * 基金合并
     */
    public void onClickLevelTogether() {
        Intent intent = new Intent(mActivity, LFundMergerActivity.class);
        startActivity(intent);
    }
}

class LFundBuyController extends AbsBaseController implements View.OnClickListener {

    private LFundBuyFragment mFragment;

    public LFundBuyController(LFundBuyFragment mFragment) {
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
        if (resId == R.id.tv_level_buy_sub) {
            mFragment.onClickLevelSub();
        } else if (resId == R.id.tv_level_buy_subtion) {
            mFragment.onClickLevelSubtion();
        } else if (resId == R.id.tv_level_buy_ransom) {
            mFragment.onClickLevelRansom();
        } else if (resId == R.id.tv_level_buy_revocation) {
            mFragment.onClickLevelRevocation();
        } else if (resId == R.id.tv_level_buy_divide) {
            mFragment.onClickLevelDevide();
        } else if (resId == R.id.tv_level_buy_together) {
            mFragment.onClickLevelTogether();
        }
    }
}

