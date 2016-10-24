package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_option.fragment.OptionTodayTradeFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 * 当日成交、当日委托所在的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */
public class OptionTodayActivity extends AbsNavBarActivity {
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
        OptionTodayEntrustFragment fragment1 = new OptionTodayEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.option_main15));

        OptionTodayTradeFragment fragment2 = new OptionTodayTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.option_main16));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
