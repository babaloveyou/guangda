package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.ToadyMoneyFragment;

/**
 *当日资金流水的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/6/25
 */
public class TodayMoneyActivity extends AbsNavBarActivity {


    private ToadyMoneyFragment mToadyMoneyFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mToadyMoneyFragment);
        setTitleText(R.string.today_money_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mToadyMoneyFragment = new ToadyMoneyFragment();
    }

}
