package com.thinkive.android.trade_bz.a_out.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_out.fragment.FundPledgeFragment;
import com.thinkive.android.trade_bz.a_out.fragment.FundPledgeSelectFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;

/**
 * 场外基金定投
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/26
 */
public class FundPledgeActivity extends AbsNavBarActivity {
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
        FundPledgeFragment fragment1 = new FundPledgeFragment();
        fragment1.setName(this.getResources().getString(R.string.fund_title6));

        FundPledgeSelectFragment fragment2 = new FundPledgeSelectFragment();
        fragment2.setName(this.getResources().getString(R.string.fund_title7));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);

    }
}
