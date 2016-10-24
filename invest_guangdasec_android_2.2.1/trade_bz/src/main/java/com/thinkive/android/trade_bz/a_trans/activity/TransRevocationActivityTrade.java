package com.thinkive.android.trade_bz.a_trans.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_trans.fragment.TransRevocationFragment;

/**
 * 转股交易撤单查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/5
 */
public class TransRevocationActivityTrade extends AbsNavBarActivity {

    private TransRevocationFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.trans_stock21);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new TransRevocationFragment();
    }
}
