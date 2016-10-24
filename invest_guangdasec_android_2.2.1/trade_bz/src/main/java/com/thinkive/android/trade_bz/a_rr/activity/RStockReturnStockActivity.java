package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RStockReturnOrderStockFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 融资融券-现券还券(直接还券)
 * @author 张雪梅
 * @date 2016/7/20
 * @company Thinkive
 */
public class RStockReturnStockActivity extends AbsNavBarActivity {

    private RStockReturnOrderStockFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_order_four);
        setBackBtnVisibility(View.VISIBLE);
        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);
    }

    @Override
    protected void initData() {
        mFragment = new RStockReturnOrderStockFragment();

    }

    @Override
    protected void onLogouImEvent() {
        startActivity(new Intent(this, RSelectContractNotActivity.class));
    }
}
