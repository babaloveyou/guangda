package com.thinkive.android.trade_bz.a_trans.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_trans.fragment.TransSelectTicketFragment;

/**
 * 转股交易 挂牌股票查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/29
 */
public class TransSelectTicketActivityTrade extends AbsNavBarActivity {

    private TransSelectTicketFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.trans_stock34);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new TransSelectTicketFragment();
    }
}
