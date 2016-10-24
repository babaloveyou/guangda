package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectHistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectHistoryTradeFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 * 融资融券--查询 --历史成交、历史委托所在的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/18
 */
public class RSelectHistoryActivity extends AbsNavBarActivity {
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
        RSelectHistoryEntrustFragment fragment1 = new RSelectHistoryEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.r_select_history_entrust));

        RSelectHistoryTradeFragment fragment2 = new RSelectHistoryTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.r_select_history_trade));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
