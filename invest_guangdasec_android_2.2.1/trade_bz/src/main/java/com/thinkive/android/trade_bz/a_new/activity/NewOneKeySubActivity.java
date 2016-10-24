package com.thinkive.android.trade_bz.a_new.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.fragment.NewOneKeySubFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 一键申购
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/21
 */
public class NewOneKeySubActivity extends AbsNavBarActivity {

    private NewOneKeySubFragment mFragment = null;

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
        setTitleText(R.string.new_stock0);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new NewOneKeySubFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
