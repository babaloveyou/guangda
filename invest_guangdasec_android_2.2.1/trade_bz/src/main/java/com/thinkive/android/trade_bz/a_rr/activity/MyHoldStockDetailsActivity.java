package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.HoldDetailsFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

/**
 * Created by Administrator on 2016/11/28.
 */
public class MyHoldStockDetailsActivity extends AbsNavBarActivity {
    private HoldDetailsFragment mFragmet = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragmet);
        setTitleText("持仓查询详情");
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        Intent intent = this.getIntent();
        MyStoreStockBean revocationBean = (MyStoreStockBean) intent.getParcelableExtra("bean");
        mFragmet = new HoldDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", revocationBean);
        mFragmet.setArguments(bundle);
    }
}
