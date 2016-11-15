package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.fragment.TodayEntrustDetailsFragment;

/**
 * Created by Administrator on 2016/11/14.
 */
public class TodayEntrustDetailActivity extends AbsNavBarActivity {
    private TodayEntrustDetailsFragment mTodayEntrustDetailsFragment = null;

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
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        Intent intent = this.getIntent();
        RevocationBean revocationBean = (RevocationBean) intent.getParcelableExtra("bean");
        mTodayEntrustDetailsFragment = new TodayEntrustDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", revocationBean);
        mTodayEntrustDetailsFragment.setArguments(bundle);
    }
}
