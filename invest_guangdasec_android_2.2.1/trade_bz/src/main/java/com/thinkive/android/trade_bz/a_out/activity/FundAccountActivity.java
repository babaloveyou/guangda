package com.thinkive.android.trade_bz.a_out.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_out.fragment.FundAccountFragment;

/**
 *  基金交易--查询-基金账户查询
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class FundAccountActivity extends AbsNavBarActivity {


    private FundAccountFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.fund_trade_account_title);
        setBackBtnVisibility(View.VISIBLE);
        setLogoutBtnVisibility(View.VISIBLE);
        setLogoutBtnText(R.string.fund_account9);
    }

    @Override
    protected void initData() {
        mFragment = new FundAccountFragment();
    }
    /**
     * 点击开户
     */
    @Override
    protected void onLogouBtEvent() {
        startActivity(new Intent(this,FundOpenAccountActivity.class));
    }
}
