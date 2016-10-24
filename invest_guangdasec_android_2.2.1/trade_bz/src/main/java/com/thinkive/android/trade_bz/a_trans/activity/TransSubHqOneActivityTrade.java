package com.thinkive.android.trade_bz.a_trans.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_trans.fragment.TransHqOneFragment;

/**
 * 转股交易 定价申报行情查询
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/30
 */
public class TransSubHqOneActivityTrade extends AbsNavBarActivity {

    private TransHqOneFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.trans_stock31);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new TransHqOneFragment();
    }
}
