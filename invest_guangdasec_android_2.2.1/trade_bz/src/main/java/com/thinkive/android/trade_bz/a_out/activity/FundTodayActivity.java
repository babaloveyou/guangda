package com.thinkive.android.trade_bz.a_out.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_out.fragment.FundTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_out.fragment.FundTodayTradeFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;
/**
 *  基金交易--查询--今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class FundTodayActivity extends AbsNavBarActivity {
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
        FundTodayEntrustFragment fragment1 = new FundTodayEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.today_entrust));

        FundTodayTradeFragment fragment2 = new FundTodayTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.today_trade));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}
