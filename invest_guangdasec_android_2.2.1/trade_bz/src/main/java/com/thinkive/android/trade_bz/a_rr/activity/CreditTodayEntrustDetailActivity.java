package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayEntrustBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTodayEntrustDetailsFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayEntrustDetailActivity extends AbsNavBarActivity {
    private CreditTodayEntrustDetailsFragment mTodayEntrustDetailsFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mTodayEntrustDetailsFragment);
        setTitleText(R.string.today_entrust_details);
//        setTitleDrawableLedt();
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        Intent intent = this.getIntent();
        RSelectTodayEntrustBean revocationBean = (RSelectTodayEntrustBean) intent.getParcelableExtra("bean");
        mTodayEntrustDetailsFragment = new CreditTodayEntrustDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", revocationBean);
        mTodayEntrustDetailsFragment.setArguments(bundle);
    }
}
