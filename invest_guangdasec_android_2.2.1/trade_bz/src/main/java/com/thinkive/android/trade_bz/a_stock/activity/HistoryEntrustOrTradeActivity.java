package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryTradeFragment;
/**
 * 历史成交、历史委托所在的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/3
 */
public class HistoryEntrustOrTradeActivity extends AbsNavBarActivity {
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
        HistoryEntrustFragment fragment1 = new HistoryEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.history_entrust_title));

        HistoryTradeFragment fragment2 = new HistoryTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.history_trade_title));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
