package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionExerciseFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 个股期权 行权
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/17
 */
public class OptionExerciseActivity extends AbsNavBarActivity {

    private OptionExerciseFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.option_main9);
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new OptionExerciseFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
