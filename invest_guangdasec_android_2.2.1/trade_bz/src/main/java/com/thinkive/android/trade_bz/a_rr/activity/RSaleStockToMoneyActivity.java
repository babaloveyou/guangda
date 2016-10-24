package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSaleStockToMoneyFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 融资融券-买券还款
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/23
 */

public class RSaleStockToMoneyActivity extends AbsNavBarActivity {
    private RSaleStockToMoneyFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        setTitleText(this.getResources().getString(R.string.r_order_one));
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);
    }

    @Override
    protected void initData() {
        mFragment = new RSaleStockToMoneyFragment();
    }

    /**
     * 点击右侧查询按钮
     */
    @Override
    protected void onLogouImEvent() {
        Intent intent = new Intent(this, RSelectPropertyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("checkId", R.id.rb_r_liability);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
