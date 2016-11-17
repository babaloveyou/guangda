package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 *  融资融券--下单--撤单（303017）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */
public class RRevocationActivity extends AbsNavBarActivity {


    private RRevocationFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_revocation_title);
        //左边按钮
        setBackBtnVisibility(View.VISIBLE);
        //右边按钮
    }

    @Override
    protected void initData() {
        mFragment = new RRevocationFragment();
    }

    @Override
    protected void onLogouBtEvent() {
        //点击右侧按钮所执行的操作
        mFragment.clickTitleRightText();
    }
}
