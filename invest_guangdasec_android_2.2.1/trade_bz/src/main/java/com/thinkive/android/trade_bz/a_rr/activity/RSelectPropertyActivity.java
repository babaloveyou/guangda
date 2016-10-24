package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectPropertFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 *   融资融券--查询--资产负责
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/17
 */
public class RSelectPropertyActivity extends AbsNavBarActivity {
    private RSelectPropertFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_select_money);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new RSelectPropertFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}


