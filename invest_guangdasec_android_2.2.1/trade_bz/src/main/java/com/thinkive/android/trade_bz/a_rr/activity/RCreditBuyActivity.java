package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RCreditBuyFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 *  融资融券--融资买入
 * @author 张雪梅
 * @company Thinkive
 * @date 16/1/20
 */
public class RCreditBuyActivity extends AbsNavBarActivity {

    private RCreditBuyFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.r_order_buy);
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);
    }

    @Override
    protected void initData() {
        mFragment = new RCreditBuyFragment();
    }
    @Override
    protected void onLogouImEvent() {
        //点击右侧按钮所执行的操作
        mFragment.onClickTitleRight();
    }
}
