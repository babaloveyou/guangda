package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HoldDetailsFragment extends Fragment {

    private View mView;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private TextView mTv7;
    private TextView mTv8;
    private MyStoreStockBean b;
    private TextView mWinLoseTv;
    private TextView mWinLoseRatioTv;
    private TextView mNewMarketTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hold_details, container, false);
        findViews(mView);
        initData();
        initView();
        return mView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            b = (MyStoreStockBean) bundle.getParcelable("bean");
        }
    }

    private void initView() {
        mWinLoseTv.setText(b.getFloat_yk());
        mWinLoseRatioTv.setText(b.getFloat_yk_per());
        mNewMarketTv.setText(b.getMarket_value());
        mTv1.setText(b.getStock_name());
        mTv2.setText(b.getStock_code());
        mTv3.setText(b.getCost_amount());
        mTv4.setText(b.getEnable_amount());
        mTv5.setText(b.getCost_price());
        mTv6.setText(b.getLast_price());
        mTv7.setText(b.getStock_account());
        mTv8.setText(b.getExchange_type_name());
    }

    private void findViews(View v) {
        mWinLoseTv = (TextView) v.findViewById(R.id.tv_win_lose);
        mWinLoseRatioTv = (TextView) v.findViewById(R.id.tv_ratio_win_lose);
        mNewMarketTv = (TextView) v.findViewById(R.id.tv_new_market);


        mTv1 = (TextView) v.findViewById(R.id.tv1);
        mTv2 = (TextView) v.findViewById(R.id.tv2);
        mTv3 = (TextView) v.findViewById(R.id.tv3);
        mTv4 = (TextView) v.findViewById(R.id.tv4);
        mTv5 = (TextView) v.findViewById(R.id.tv5);
        mTv6 = (TextView) v.findViewById(R.id.tv6);
        mTv7 = (TextView) v.findViewById(R.id.tv7);
        mTv8 = (TextView) v.findViewById(R.id.tv8);
    }
}

