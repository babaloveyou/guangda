package com.thinkive.android.trade_bz.a_in.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.fragment.InFundRedemptionFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 *
 * Description：货币基金赎回、LOF基金赎回 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/10 <br>
 */
public class InFundRedemptionActivity extends AbsNavBarActivity {
    private InFundRedemptionFragment fundRedemptionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment_trade,true);
        initData();
        initViews();
    }

    @Override
    protected void initData() {
        fundRedemptionFragment = new InFundRedemptionFragment();
        fundRedemptionFragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getExtras();
        int fragmentFlag = bundle.getInt("fragmentFlag");
        if(fragmentFlag == 0){
            setTitleText(R.string.in_monetary_fund_redemption_title);
        }else if(fragmentFlag ==1){
            setTitleText(R.string.in_fund_redemption_title);
        }
        setBackBtnVisibility(View.VISIBLE);
        addFragment(R.id.fl_container, fundRedemptionFragment);
    }
}
