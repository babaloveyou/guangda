package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectHoldStockFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 *   融资融券--查询--股份持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/17
 */
public class RSelectHoldStockActivity extends AbsNavBarActivity {
    private RSelectHoldStockFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_hold_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new RSelectHoldStockFragment();
    }

}


