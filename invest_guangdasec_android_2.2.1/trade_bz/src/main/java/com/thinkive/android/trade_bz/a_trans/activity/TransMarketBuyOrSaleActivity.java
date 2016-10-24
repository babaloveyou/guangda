package com.thinkive.android.trade_bz.a_trans.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_trans.fragment.TransMarketBuyOrSaleFragment;

/**
 * 转股交易 做市买入/做市卖出
 * @author 张雪梅
 * @corporation thinkive
 * @date 2016/7/28
 */
public class TransMarketBuyOrSaleActivity extends AbsNavBarActivity {

    private TransMarketBuyOrSaleFragment mFragment = null;

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
            setTitleText(R.string.trans_stock8);
            mFragment.setBuyOrSell(0);
        }else if(buyOrSale == 1){
            setTitleText(R.string.trans_stock9);
            mFragment.setBuyOrSell(1);
        }
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new TransMarketBuyOrSaleFragment();
    }
}
