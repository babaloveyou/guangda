package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionBuyOrSaleCloseFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 个股期权 买入平仓，卖出平仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/16
 */
public class OptionBuyOrSaleCloseActivity extends AbsNavBarActivity {

    private OptionBuyOrSaleCloseFragment mFragment = null;

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
                setTitleText(R.string.option_main5);
            }else if(buyOrSale == 1){
                setTitleText(R.string.option_main6);
            }else if(buyOrSale == 2){
                setTitleText(R.string.option_main8);
            }else if(buyOrSale == 3){
                //行权
                setTitleText(R.string.option_main9);
            }
        }
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new OptionBuyOrSaleCloseFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
