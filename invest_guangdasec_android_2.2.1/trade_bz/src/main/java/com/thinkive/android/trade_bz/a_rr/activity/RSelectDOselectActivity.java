package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectDOselectFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 融资融券--查询--交割单（303027）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectDOselectActivity extends AbsNavBarActivity {
    private RSelectDOselectFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_do_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new RSelectDOselectFragment();
    }
}


