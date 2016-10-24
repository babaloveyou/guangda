package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RContractExpandFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 融资融券——下单——合约展期
 * @author 王延龙
 * @date 2016/4/18.
 * @company Thinkive
 */
public class RContractExpandActivity extends AbsNavBarActivity {
    private RContractExpandFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initData() {
        mFragment = new RContractExpandFragment();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_order_contract);

        setBackBtnVisibility(View.VISIBLE);//左边按钮可见
        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);

    }

    @Override
    protected void onLogouImEvent() {
        startActivity(new Intent(this,RSelectContractNotActivity.class));
    }
}
