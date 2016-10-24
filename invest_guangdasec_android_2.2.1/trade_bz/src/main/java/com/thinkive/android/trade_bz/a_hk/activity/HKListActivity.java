package com.thinkive.android.trade_bz.a_hk.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.fragment.HKSelectListFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 港股选择列表（单选）
 * 可以动态传入使用类型
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/27
 */
public class HKListActivity extends AbsNavBarActivity {

    private HKSelectListFragment mFragment = null;

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
        mFragment = new HKSelectListFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}

