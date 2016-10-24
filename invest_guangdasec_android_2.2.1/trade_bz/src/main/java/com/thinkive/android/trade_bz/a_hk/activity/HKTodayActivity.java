package com.thinkive.android.trade_bz.a_hk.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.fragment.HKTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKTodayTradeFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 *   港股通 今日成交、今日委托所在的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/11/2
 */
public class HKTodayActivity extends AbsNavBarActivity {
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
        HKTodayEntrustFragment fragment1 = new HKTodayEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.hk_select17));

        HKTodayTradeFragment fragment2 = new HKTodayTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.hk_select18));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
