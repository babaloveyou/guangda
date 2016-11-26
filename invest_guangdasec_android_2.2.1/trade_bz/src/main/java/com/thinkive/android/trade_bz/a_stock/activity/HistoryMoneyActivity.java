package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryMoneyFragment;

/**
 *当日资金流水的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/6/25
 */
public class HistoryMoneyActivity extends AbsNavBarActivity {


    private HistoryMoneyFragment mToadyMoneyFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mToadyMoneyFragment);
        setTitleText(R.string.money_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mToadyMoneyFragment = new HistoryMoneyFragment();
    }

}