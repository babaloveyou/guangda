package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectObjectCapitalFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectObjectTicketFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 *   融资融券--查询--标的证券
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/20
 */
public class RSelectObjectSecurityActivity extends AbsNavBarActivity {
    private RadioButtonFragment2 mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        //隐藏Activity的标题栏
        setTitleBarVisible(View.GONE);
    }
    @Override
    protected void initData() {
        RSelectObjectCapitalFragment fragment1 = new RSelectObjectCapitalFragment();
        fragment1.setName(this.getResources().getString(R.string.r_object_bar1));

        RSelectObjectTicketFragment fragment2 = new RSelectObjectTicketFragment();
        fragment2.setName(this.getResources().getString(R.string.r_object_bar2));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }
}