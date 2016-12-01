package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayTradeBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTodayTradeDetailsFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayTradeDetailActivity  extends AbsNavBarActivity {
    private CreditTodayTradeDetailsFragment mTodayTradeDetailsFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mTodayTradeDetailsFragment);
        setTitleText(R.string.today_trade_details);
        setBackBtnVisibility(View.VISIBLE);
//        setTitleDrawableLedt();
    }

    @Override
    protected void initData() {
        Intent intent = this.getIntent();
        RSelectTodayTradeBean bean = (RSelectTodayTradeBean) intent.getParcelableExtra("bean");
        mTodayTradeDetailsFragment = new CreditTodayTradeDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        mTodayTradeDetailsFragment.setArguments(bundle);
    }
}
