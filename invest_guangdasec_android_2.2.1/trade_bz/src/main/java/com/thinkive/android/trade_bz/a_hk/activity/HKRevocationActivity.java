package com.thinkive.android.trade_bz.a_hk.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.fragment.HKRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 港股撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/6
 */
public class HKRevocationActivity extends AbsNavBarActivity {

    private HKRevocationFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.hk_company_action11);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new HKRevocationFragment();
    }

}
