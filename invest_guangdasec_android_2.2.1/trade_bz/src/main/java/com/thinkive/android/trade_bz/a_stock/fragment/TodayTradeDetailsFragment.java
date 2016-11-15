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
import com.thinkive.android.trade_bz.a_stock.bean.HistoryTradeBean;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * Created by Administrator on 2016/11/14.
 */
public class TodayTradeDetailsFragment extends Fragment {

    private View mView;
    private ImageView mFlagIv;
    private TextView mNameAndCodeTv;
    private TextView mTimeTv;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private TextView mTv7;
    private TextView mTv8;
    private TextView mTv9;
    private TextView mTv10;
    private TextView mTv11;
    private HistoryTradeBean b;
    private TextView mNumTv;
    private TextView mPriceTv;
    private TextView mTotalPriceTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_today_trade_details, container, false);
        findViews(mView);
        initData();
        initView();
        return mView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            b = (HistoryTradeBean) bundle.getParcelable("bean");
        }
    }

    private void initView() {
        mFlagIv.setImageResource("0".equals(b.getEntrust_bs()) ? R.mipmap.flag_buy : R.mipmap.flag_sale);
        mNameAndCodeTv.setText(b.getStock_name() + " " + b.getStock_code());
        mTimeTv.setText(b.getBusiness_date() + "" + b.getBusiness_time());
        mNumTv.setText(b.getBusiness_amount());
        mPriceTv.setText(TradeUtils.formatDouble2(b.getBusiness_price()));
        mTotalPriceTv.setText(Double.parseDouble(b.getBusiness_price()) * Double.parseDouble(b.getBusiness_amount()) + "");
        //        mTv1.setText(b.getEn);
        mTv2.setText(b.getBusiness_date() + "" + b.getBusiness_time());
        mTv3.setText(b.getBusiness_price());
        mTv4.setText("0".equals(b.getEntrust_bs()) ? "买入" : "卖出");
        mTv5.setText(TradeLoginManager.sNormalUserInfo.getFund_account());
        //                mTv6.setText(b.getStock_account());币种
        mTv7.setText(b.getStock_account());
        mTv8.setText(b.getEntrust_no());
    }
    private void findViews(View v) {
        mFlagIv = (ImageView) v.findViewById(R.id.iv_flag);
        mNameAndCodeTv = (TextView) v.findViewById(R.id.tv_name_code);
        mTimeTv = (TextView) v.findViewById(R.id.tv_time);
        mNumTv = (TextView) v.findViewById(R.id.tv_num);
        mPriceTv = (TextView) v.findViewById(R.id.tv_price);
        mTotalPriceTv = (TextView) v.findViewById(R.id.tv_total_price);
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
