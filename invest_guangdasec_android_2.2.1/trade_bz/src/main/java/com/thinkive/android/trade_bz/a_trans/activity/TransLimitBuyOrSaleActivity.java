package com.thinkive.android.trade_bz.a_trans.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_trans.fragment.TransLimitBuyOrSaleFragment;

/**
 * 转股交易 委托买入或卖出
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/30
 */
public class TransLimitBuyOrSaleActivity extends AbsNavBarActivity {

    private TransLimitBuyOrSaleFragment mFragment = null;

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
            setTitleText(R.string.trans_stock);
            mFragment.setBuyOrSell(0);
        }else if(buyOrSale == 1){
            setTitleText(R.string.trans_stock0);
            mFragment.setBuyOrSell(1);
        }
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new TransLimitBuyOrSaleFragment();
    }

}
