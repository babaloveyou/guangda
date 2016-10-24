package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.SelectListFragment;

/**
 * 普通交易选择列表类（单选）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/31
 */
public class SelectListActivity extends AbsNavBarActivity {

    private SelectListFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.fund_input_hint2);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new SelectListFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}

