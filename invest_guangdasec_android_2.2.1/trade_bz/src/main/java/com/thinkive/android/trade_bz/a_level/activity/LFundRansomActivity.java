package com.thinkive.android.trade_bz.a_level.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_level.fragment.LFundRansomFragment;

/**
 *  基金赎回
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class LFundRansomActivity extends AbsNavBarActivity {

    private LFundRansomFragment mFragmentLevel=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.level_title3);
        setBackBtnVisibility(View.VISIBLE);
        addFragment(R.id.fl_container, mFragmentLevel);
    }

    @Override
    protected void initData() {
        mFragmentLevel=new LFundRansomFragment();
    }

}
