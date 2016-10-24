package com.thinkive.android.trade_bz.a_level.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_level.fragment.LFundSubscriptionFragment;

/**
 *  基金认购
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class LFundSubscriptionActivity extends AbsNavBarActivity {
    private LFundSubscriptionFragment mFragment =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.level_title2);
        setBackBtnVisibility(View.VISIBLE);
        addFragment(R.id.fl_container, mFragment);
    }

    @Override
    protected void initData() {
        mFragment =new LFundSubscriptionFragment();
    }

}
