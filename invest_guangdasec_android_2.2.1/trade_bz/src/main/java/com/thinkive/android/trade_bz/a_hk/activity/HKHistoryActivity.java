package com.thinkive.android.trade_bz.a_hk.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.fragment.HKHistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKHistoryTradeFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 *  港股通 历史成交、历史委托所在的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/11/2
 */
public class HKHistoryActivity extends AbsNavBarActivity {
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
        HKHistoryEntrustFragment fragment1 = new HKHistoryEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.hk_select19));

        HKHistoryTradeFragment fragment2 = new HKHistoryTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.hk_select20));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
