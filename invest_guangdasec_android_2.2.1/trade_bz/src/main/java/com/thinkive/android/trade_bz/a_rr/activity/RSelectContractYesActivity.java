package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectContractYesFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 融资融券--查询--合约查询（303035）
 *   已了结合约
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */
public class RSelectContractYesActivity extends AbsNavBarActivity {


    private RSelectContractYesFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_contract_yes_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new RSelectContractYesFragment();
    }

}
