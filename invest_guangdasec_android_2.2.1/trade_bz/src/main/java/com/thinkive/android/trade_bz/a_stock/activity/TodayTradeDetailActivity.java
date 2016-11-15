package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.HistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.fragment.TodayTradeDetailsFragment;

/**
 * Created by Administrator on 2016/11/14.
 */
public class TodayTradeDetailActivity extends AbsNavBarActivity {
    private TodayTradeDetailsFragment mTodayTradeDetailsFragment = null;

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
    }

    @Override
    protected void initData() {
        Intent intent = this.getIntent();
        HistoryTradeBean bean = (HistoryTradeBean) intent.getParcelableExtra("bean");
        mTodayTradeDetailsFragment = new TodayTradeDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", bean);
        mTodayTradeDetailsFragment.setArguments(bundle);
    }
}
