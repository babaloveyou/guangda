package com.thinkive.android.trade_bz.a_in.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.fragment.InFundPurchaseOrSubscriptionFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 *
 * Description：场内货币基金认、内货币基金申购、LOF基金认购、LOF基金申购 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/9 <br>
 */
public class InFundPurchaseOrSubscriptionActivity extends AbsNavBarActivity {
    private InFundPurchaseOrSubscriptionFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment_trade,true);
        initData();
        initViews();
    }

    @Override
    protected void initData() {
        fragment = new InFundPurchaseOrSubscriptionFragment();
        fragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getExtras();
        int fragmentFlag = bundle.getInt("fragmentFlag");
        if(fragmentFlag == 0){
            setTitleText(R.string.in_monetary_fund_subscription_title);
        }else if(fragmentFlag ==1){
            setTitleText(R.string.in_monetary_fund_purchase_title);
        }else if(fragmentFlag == 2){
            setTitleText(R.string.in_fund_subscription_title);
        }else if(fragmentFlag == 3){
            setTitleText(R.string.in_fund_purchase_title);
        }
        setBackBtnVisibility(View.VISIBLE);
        addFragment(R.id.fl_container, fragment);
    }
}
