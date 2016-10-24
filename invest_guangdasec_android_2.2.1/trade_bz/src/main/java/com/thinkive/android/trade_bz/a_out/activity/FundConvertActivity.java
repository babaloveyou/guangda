package com.thinkive.android.trade_bz.a_out.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_out.fragment.FundConvertFragment;

/**
 *  场外基金转换
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/26
 */
public class FundConvertActivity extends AbsNavBarActivity {

    private FundConvertFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.fund_buy_four);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new FundConvertFragment();
    }

}
