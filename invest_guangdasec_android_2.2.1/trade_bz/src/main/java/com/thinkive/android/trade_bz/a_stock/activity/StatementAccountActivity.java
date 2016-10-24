package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.StatementAccountFragment;

/**
 *  对账单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/6/24
 */
public class StatementAccountActivity extends AbsNavBarActivity {

    private StatementAccountFragment mStatementAccountFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mStatementAccountFragment);
        //左边按钮
        setTitleText(R.string.statement_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mStatementAccountFragment = new StatementAccountFragment();
    }

}
