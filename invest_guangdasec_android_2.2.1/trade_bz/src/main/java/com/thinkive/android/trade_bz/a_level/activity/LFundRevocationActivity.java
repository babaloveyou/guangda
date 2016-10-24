package com.thinkive.android.trade_bz.a_level.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_level.fragment.LFundRevocationFragment;

/**
 *  分级基金撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/27
 */
public class LFundRevocationActivity extends AbsNavBarActivity {

    private LFundRevocationFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.fund_trade_revocation_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new LFundRevocationFragment();
    }

}
