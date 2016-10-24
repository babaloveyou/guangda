package com.thinkive.android.trade_bz.a_level.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_level.fragment.LFundHistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_level.fragment.LFundHistoryTradeFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 *  分级基金历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 15/11/10
 */
public class LFundHistoryActivity extends AbsNavBarActivity {
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
        LFundHistoryEntrustFragment fragment1 = new LFundHistoryEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.history_entrust_title));

        LFundHistoryTradeFragment fragment2 = new LFundHistoryTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.history_trade_title));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
