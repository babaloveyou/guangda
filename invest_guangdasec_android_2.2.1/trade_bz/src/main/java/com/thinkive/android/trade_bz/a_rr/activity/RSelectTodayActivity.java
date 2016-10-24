package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectTodayTradeFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 * 融资融券--查询--当日委托.当日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectTodayActivity extends AbsNavBarActivity {
    private RadioButtonFragment2 mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        //隐藏Activity的标题栏
        setTitleBarVisible(View.GONE);
    }
    @Override
    protected void initData() {
        RSelectTodayEntrustFragment fragment1 = new RSelectTodayEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.r_select_today_entrust));

        RSelectTodayTradeFragment fragment2 = new RSelectTodayTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.r_select_today_trade));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}

