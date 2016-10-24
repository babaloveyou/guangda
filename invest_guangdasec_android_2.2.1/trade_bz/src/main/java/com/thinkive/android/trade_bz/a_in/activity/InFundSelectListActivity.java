package com.thinkive.android.trade_bz.a_in.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.fragment.InFundSelectListFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 场内基金，选择列表类（单选）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/21
 */
public class InFundSelectListActivity extends AbsNavBarActivity {

    private InFundSelectListFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.option_buy1);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new InFundSelectListFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}

