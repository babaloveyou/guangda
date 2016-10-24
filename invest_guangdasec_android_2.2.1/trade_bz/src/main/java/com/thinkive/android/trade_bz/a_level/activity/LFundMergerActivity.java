package com.thinkive.android.trade_bz.a_level.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_level.fragment.LFundMergerFragment;

/**
 *  分级基金合并
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/27
 */
public class LFundMergerActivity extends AbsNavBarActivity {

    private LFundMergerFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.level_title5);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new LFundMergerFragment();
    }

}
