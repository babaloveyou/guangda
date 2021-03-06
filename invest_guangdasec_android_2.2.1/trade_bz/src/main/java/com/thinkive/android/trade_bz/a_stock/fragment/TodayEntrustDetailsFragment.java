package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * Created by Administrator on 2016/11/14.
 */
public class TodayEntrustDetailsFragment extends Fragment {

    private View mView;
    private ImageView mFlagIv;
    private TextView mNameAndCodeTv;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private TextView mTv7;
    private RevocationBean b;
    private TextView mPriceTv;
    private TextView mEntrustAmountTv;
    private TextView mBusinessAmountTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_today_entrust_details, container, false);
        findViews(mView);
        initData();
        initView();
        return mView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            b = (RevocationBean) bundle.getParcelable("bean");
        }
    }

    private void initView() {
        mFlagIv.setImageResource("0".equals(b.getEntrust_bs()) ? R.mipmap.flag_buy : R.mipmap.flag_sale);
        mNameAndCodeTv.setText(b.getStock_name() + " " + b.getStock_code());
        mPriceTv.setText(TradeUtils.formatDouble2(b.getEntrust_price()));
        mEntrustAmountTv.setText(b.getEntrust_amount());
        mBusinessAmountTv.setText(b.getBusiness_amount());
        mTv1.setText(b.getEntrust_date() +" "+ b.getEntrust_time());
        mTv2.setText(b.getBusiness_price());
        mTv3.setText(b.getBusiness_state_name());
        mTv4.setText(b.getFund_account());
        mTv5.setText("人民币");
        mTv6.setText(b.getStock_account());
        mTv7.setText(b.getExchange_type_name());
    }

    private void findViews(View v) {
        mFlagIv = (ImageView) v.findViewById(R.id.iv_flag);
        mNameAndCodeTv = (TextView) v.findViewById(R.id.tv_name_code);
        mPriceTv = (TextView) v.findViewById(R.id.tv_price);
        mEntrustAmountTv = (TextView) v.findViewById(R.id.tv_entrust_amount);
        mBusinessAmountTv = (TextView) v.findViewById(R.id.tv_business_amount);
        mTv1 = (TextView) v.findViewById(R.id.tv1);
        mTv2 = (TextView) v.findViewById(R.id.tv2);
        mTv3 = (TextView) v.findViewById(R.id.tv3);
        mTv4 = (TextView) v.findViewById(R.id.tv4);
        mTv5 = (TextView) v.findViewById(R.id.tv5);
        mTv6 = (TextView) v.findViewById(R.id.tv6);
        mTv7 = (TextView) v.findViewById(R.id.tv7);
    }
}
