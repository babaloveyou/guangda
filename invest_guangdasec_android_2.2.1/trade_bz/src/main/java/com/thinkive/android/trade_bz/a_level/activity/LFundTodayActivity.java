package com.thinkive.android.trade_bz.a_level.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_level.fragment.LFundTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_level.fragment.LFundTodayTradeFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 *  分级基金今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/27
 */
public class LFundTodayActivity extends AbsNavBarActivity {
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
        LFundTodayEntrustFragment fragment1 = new LFundTodayEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.today_entrust));

        LFundTodayTradeFragment fragment2 = new LFundTodayTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.today_trade));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}