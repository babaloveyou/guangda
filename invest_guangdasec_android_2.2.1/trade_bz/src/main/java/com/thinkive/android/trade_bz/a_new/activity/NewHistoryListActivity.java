package com.thinkive.android.trade_bz.a_new.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.fragment.NewHistoryListFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 缴费历史纪录查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewHistoryListActivity extends AbsNavBarActivity {

    private NewHistoryListFragment mFragment = null;

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
        setTitleText(R.string.new_stock4);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new NewHistoryListFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
