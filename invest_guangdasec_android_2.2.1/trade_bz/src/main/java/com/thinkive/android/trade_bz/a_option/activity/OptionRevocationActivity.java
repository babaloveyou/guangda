package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 个股期权 撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */
public class OptionRevocationActivity extends AbsNavBarActivity {

    private OptionRevocationFragment mFragment = null;

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
        setTitleText(R.string.option_main12);
        setBackBtnVisibility(View.VISIBLE);
//        setLogoutBtnVisibility(View.VISIBLE);
//        setLogoutBtnText(R.string.option_main23);
    }

    @Override
    protected void initData() {
        mFragment = new OptionRevocationFragment();
        mFragment.setArguments(getIntent().getExtras());
    }

    /**
     * 点击全部撤单
     */
    @Override
    protected void onLogouBtEvent() {
        mFragment.onClickAllRevocation();
    }
}
