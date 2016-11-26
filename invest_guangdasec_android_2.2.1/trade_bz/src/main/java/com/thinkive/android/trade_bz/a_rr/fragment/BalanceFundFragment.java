package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.BalanceDetailActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsNavbarFragment;

/**
 * Created by Administrator on 2016/11/26.
 */
public class BalanceFundFragment extends AbsNavbarFragment{
    /**
     * 宿主
     */
    private BalanceDetailActivity mActivity;
    private RSelectPropertBean mBean;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private TextView mTv7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_balance_fund, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }
    @Override
    protected void findViews(View view) {
        mTv1 = (TextView) view.findViewById(R.id.tv1);
        mTv2 = (TextView) view.findViewById(R.id.tv2);
        mTv3 = (TextView) view.findViewById(R.id.tv3);
        mTv4 = (TextView) view.findViewById(R.id.tv4);
        mTv5 = (TextView) view.findViewById(R.id.tv5);
        mTv6 = (TextView) view.findViewById(R.id.tv6);
        mTv7 = (TextView) view.findViewById(R.id.tv7);
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {
        mActivity = (BalanceDetailActivity) getActivity();
        Bundle arguments = getArguments();
        mBean = (RSelectPropertBean) arguments.getParcelable("bean");
    }

    @Override
    protected void initViews() {
        mTv1.setText(mBean.getAssert_val());
        mTv2.setText(mBean.getNet_asset());
        mTv3.setText(mBean.getPer_assurescale_value());
        mTv4.setText(mBean.getMarket_value());
        mTv5.setText(mBean.getEnable_bail_balance());
        mTv6.setText(mBean.getCurrent_balance());
        mTv7.setText(mBean.getEnable_balance());
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

}
