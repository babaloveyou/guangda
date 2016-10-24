package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHistoryRiskFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 个股期权历史风险通知查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class OptionHistoryRiskActivity extends AbsNavBarActivity {

    private OptionHistoryRiskFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        //左边按钮
        setTitleText(R.string.option_main13);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new OptionHistoryRiskFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
