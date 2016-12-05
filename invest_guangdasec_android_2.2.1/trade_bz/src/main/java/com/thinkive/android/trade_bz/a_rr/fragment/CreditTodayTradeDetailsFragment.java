package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayTradeBean;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayTradeDetailsFragment extends Fragment {

    private View mView;
    private ImageView mFlagIv;
    private TextView mNameAndCodeTv;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private TextView mTv7;
    private TextView mTv8;
    private RSelectTodayTradeBean b;
    private TextView mNumTv;
    private TextView mPriceTv;
    private TextView mTotalPriceTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_credit_today_trade_details, container, false);
        findViews(mView);
        initData();
        initView();
        return mView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            b = (RSelectTodayTradeBean) bundle.getParcelable("bean");
        }
    }

    private void initView() {
        mFlagIv.setImageResource("0".equals(b.getEntrust_bs()) ? R.mipmap.flag_buy : R.mipmap.flag_sale);
        mNameAndCodeTv.setText(b.getStock_name() + " " + b.getStock_code());
        mNumTv.setText(b.getBusiness_amount());
        mPriceTv.setText(TradeUtils.formatDouble2(b.getBusiness_price()));
        mTotalPriceTv.setText(Double.parseDouble(b.getBusiness_price()) * Double.parseDouble(b.getBusiness_amount()) + "");
        mTv2.setText(b.getBusiness_date() + "" + b.getBusiness_time());
        mTv3.setText(b.getEntrust_type_name());
        mTv4.setText(b.getStock_account());
        mTv5.setText("人民币");
        mTv6.setText(b.getStock_account());
        mTv7.setText(b.getEntrust_no());
        mTv8.setText(b.getExchange_type_name());
    }
    private void findViews(View v) {
        mFlagIv = (ImageView) v.findViewById(R.id.iv_flag);
        mNameAndCodeTv = (TextView) v.findViewById(R.id.tv_name_code);
        mNumTv = (TextView) v.findViewById(R.id.tv_num);
        mPriceTv = (TextView) v.findViewById(R.id.tv_price);
        mTotalPriceTv = (TextView) v.findViewById(R.id.tv_total_price);
        mTv2 = (TextView) v.findViewById(R.id.tv2);
        mTv3 = (TextView) v.findViewById(R.id.tv3);
        mTv4 = (TextView) v.findViewById(R.id.tv4);
        mTv5 = (TextView) v.findViewById(R.id.tv5);
        mTv6 = (TextView) v.findViewById(R.id.tv6);
        mTv7 = (TextView) v.findViewById(R.id.tv7);
        mTv8 = (TextView) v.findViewById(R.id.tv8);
    }
}
