package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionBuyOrSaleOpenFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;


/**
 * 个股期权 买入开仓，卖出开仓,备兑券开仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/20
 */
public class OptionBuyOrSaleOpenActivity extends AbsNavBarActivity {

    private OptionBuyOrSaleOpenFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int buyOrSale = bundle.getInt("buyOrSale");
            if(buyOrSale == 0){
                setTitleText(R.string.option_main3);
            }else if(buyOrSale == 1){
                setTitleText(R.string.option_main4);
            }else if(buyOrSale == 2){
                setTitleText(R.string.option_main7);
            }
        }
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new OptionBuyOrSaleOpenFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
