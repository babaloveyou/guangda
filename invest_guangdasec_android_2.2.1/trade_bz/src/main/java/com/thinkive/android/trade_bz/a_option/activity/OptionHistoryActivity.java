package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHistoryTradeFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 * 历史成交、历史委托所在的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */
public class OptionHistoryActivity extends AbsNavBarActivity {
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
        OptionHistoryEntrustFragment fragment1 = new OptionHistoryEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.option_main17));

        OptionHistoryTradeFragment fragment2 = new OptionHistoryTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.option_main18));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
