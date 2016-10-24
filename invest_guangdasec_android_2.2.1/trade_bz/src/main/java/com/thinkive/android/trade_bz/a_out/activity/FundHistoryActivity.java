package com.thinkive.android.trade_bz.a_out.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_out.fragment.FundHistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_out.fragment.FundHistoryTradeFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;
/**
 *   基金交易--查询--历史委托、历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class FundHistoryActivity extends AbsNavBarActivity {
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
        FundHistoryEntrustFragment fragment1 = new FundHistoryEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.history_entrust_title));

        FundHistoryTradeFragment fragment2 = new FundHistoryTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.history_trade_title));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
