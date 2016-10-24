package com.thinkive.android.trade_bz.a_hk.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.fragment.HKBuySellFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 港股通买入或者卖出
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/6
 */
public class HKBuyOrSaleActivity extends AbsNavBarActivity {

    private HKBuySellFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mFragment.setArguments(bundle);
            int buyOrSale = bundle.getInt("buyOrSale",0);
            if(buyOrSale == 0){
                setTitleText(R.string.hk_company_action9);
            }else if(buyOrSale == 1){
                setTitleText(R.string.hk_company_action10);
            }
        }
    }

    @Override
    protected void initData() {
        mFragment = new HKBuySellFragment();
    }
}
