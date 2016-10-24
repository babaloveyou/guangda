package com.thinkive.android.trade_bz.a_new.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.fragment.NewSubSelectFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 申购纪录查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewSubSelectActivity extends AbsNavBarActivity {

    private NewSubSelectFragment mFragment = null;

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
        setTitleText(R.string.new_stock1);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new NewSubSelectFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
