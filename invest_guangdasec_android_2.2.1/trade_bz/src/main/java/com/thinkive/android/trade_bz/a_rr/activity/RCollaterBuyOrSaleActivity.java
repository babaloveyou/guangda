package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterBuyOrSellFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 融资融券担保品买入卖出
 * @author 张雪梅
 * @version 1.0
 * @corporation thinkive
 * @date 2016/1/20
 */
public class RCollaterBuyOrSaleActivity extends AbsNavBarActivity {

    private RCollaterBuyOrSellFragment mFragment = null;

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
            setTitleText(R.string.r_order_d_buy);
        }else if(buyOrSale == 1){
            setTitleText(R.string.r_order_d_sell);
        }
        addFragment(R.id.fl_container, mFragment);
        mFragment.setBuyOrSell(buyOrSale);
        setBackBtnVisibility(View.VISIBLE);
        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);
    }

    @Override
    protected void initData() {
        mFragment = new RCollaterBuyOrSellFragment();
    }

    @Override
    protected void onLogouImEvent() {
        //点击右侧按钮所执行的操作
        startActivity(new Intent(this,RSelectCollateralSecurityActivity.class));
    }
}
