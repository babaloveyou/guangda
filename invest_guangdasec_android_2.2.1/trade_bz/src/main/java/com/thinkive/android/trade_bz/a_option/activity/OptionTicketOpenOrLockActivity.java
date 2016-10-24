package com.thinkive.android.trade_bz.a_option.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionTicketOpenOrLockFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 个股期权 备兑券解锁 备兑券锁定
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/17
 */
public class OptionTicketOpenOrLockActivity extends AbsNavBarActivity {

    private OptionTicketOpenOrLockFragment mFragment = null;

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
            if(buyOrSale == 0){ //锁定
                setTitleText(R.string.option_main10);
            }else if(buyOrSale == 1){ // 解锁
                setTitleText(R.string.option_main11);
            }
        }
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new OptionTicketOpenOrLockFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
