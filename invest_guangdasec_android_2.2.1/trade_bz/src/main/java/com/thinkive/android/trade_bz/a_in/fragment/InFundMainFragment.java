package com.thinkive.android.trade_bz.a_in.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.activity.InFundPurchaseOrSubscriptionActivity;
import com.thinkive.android.trade_bz.a_in.activity.InFundRedemptionActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;


/**
 *  场内基金交易的主页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class InFundMainFragment extends AbsBaseFragment {
    private TextView tvInMonetaryFundSubscription;//场内货币基金认购
    private TextView tvInMonetaryFundPurchase;//场内货币基金申购
    private TextView tvInMonetaryFundRedemption;//场内货币基金赎回
    private TextView tvInFundSubscription;//基金认购
    private TextView tvInFundPurchase;//基金申购
    private TextView tvInFundRedemption;//基金赎回

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_in_fund_main, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        tvInMonetaryFundSubscription = (TextView) view.findViewById(R.id.tv_in_monetary_fund_subscription);
        tvInMonetaryFundPurchase = (TextView) view.findViewById(R.id.tv_in_monetary_fund_purchase);
        tvInMonetaryFundRedemption = (TextView) view.findViewById(R.id.tv_in_monetary_fund_redemption);
        tvInFundSubscription = (TextView) view.findViewById(R.id.tv_in_fund_subscription);
        tvInFundPurchase = (TextView) view.findViewById(R.id.tv_in_fund_purchase);
        tvInFundRedemption = (TextView) view.findViewById(R.id.tv_in_fund_redemption);
    }

    @Override
    protected void setListeners() {
        tvInMonetaryFundSubscription.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),InFundPurchaseOrSubscriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragmentFlag", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tvInMonetaryFundPurchase.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),InFundPurchaseOrSubscriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragmentFlag", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tvInMonetaryFundRedemption.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),InFundRedemptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragmentFlag", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tvInFundSubscription.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),InFundPurchaseOrSubscriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragmentFlag", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tvInFundPurchase.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),InFundPurchaseOrSubscriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragmentFlag", 3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tvInFundRedemption.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),InFundRedemptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragmentFlag", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }


}

