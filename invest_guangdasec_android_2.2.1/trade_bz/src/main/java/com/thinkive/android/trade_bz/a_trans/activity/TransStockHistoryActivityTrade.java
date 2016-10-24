package com.thinkive.android.trade_bz.a_trans.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;
import com.thinkive.android.trade_bz.a_trans.fragment.TransHistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_trans.fragment.TransHistoryTradeFragment;

/**
 * 转股交易历史成交、历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/31
 */
public class TransStockHistoryActivityTrade extends AbsNavBarActivity {
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
        TransHistoryEntrustFragment fragment1 = new TransHistoryEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.trans_select2));

        TransHistoryTradeFragment fragment2 = new TransHistoryTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.trans_select3));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
