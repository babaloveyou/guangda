package com.thinkive.android.trade_bz.a_trans.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_trans.fragment.TransSureBuyOrSaleFragment;

/**
 * 转股交易 互报确认买入卖出
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/31
 */
public class TransSureBuyOrSaleActivityTrade extends AbsNavBarActivity {

    private TransSureBuyOrSaleFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getExtras();
        int buyOrSale = bundle.getInt("buyOrSale");
        if(buyOrSale == 0){
            setTitleText(R.string.trans_stock5);
            mFragment.setBuyOrSell(0);
        }else if(buyOrSale == 1){
            setTitleText(R.string.trans_stock6);
            mFragment.setBuyOrSell(1);
        }
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new TransSureBuyOrSaleFragment();
    }
}
