package com.thinkive.android.trade_bz.a_out.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_out.fragment.FundRansomFragment;

/**
 *  基金赎回
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class FundRansomActivity extends AbsNavBarActivity {

    private FundRansomFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.fund_buy_three);
        setBackBtnVisibility(View.VISIBLE);
        addFragment(R.id.fl_container, mFragment);
        mFragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void initData() {
        mFragment = new FundRansomFragment();
    }
}
