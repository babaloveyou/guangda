package com.thinkive.android.trade_bz.a_out.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.fragment.FundOpenAccountFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 基金开户
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/9
 */
public class FundOpenAccountActivity extends AbsNavBarActivity {


    private FundOpenAccountFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.fund_buy6);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new FundOpenAccountFragment();
    }
}
