package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterInFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterOutFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment;

/**
 *  融资融券--划转--担保品
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */
public class RCollaterTransActivity extends AbsNavBarActivity {

    private RadioButtonFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_transfer_title);
        setBackBtnVisibility(View.VISIBLE);

        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);
    }

    @Override
    protected void initData() {
        RCollaterInFragment fragment1 = new RCollaterInFragment();
        fragment1.setName(this.getResources().getString(R.string.r_layout_rb1));

        RCollaterOutFragment fragment2 = new RCollaterOutFragment();
        fragment2.setName(this.getResources().getString(R.string.r_layout_rb3));

        RCollaterRevocationFragment fragment3 = new RCollaterRevocationFragment();
        fragment3.setName(this.getResources().getString(R.string.r_layout_rb2));

        mFragment = new RadioButtonFragment(fragment1,fragment2,fragment3);
        mFragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void onLogouImEvent() {
        //点击右侧按钮所执行的操作
        startActivity(new Intent(this,RSelectCollateralSecurityActivity.class));
    }
}
