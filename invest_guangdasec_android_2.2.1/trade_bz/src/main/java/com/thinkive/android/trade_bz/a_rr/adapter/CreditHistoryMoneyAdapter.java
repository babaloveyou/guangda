package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryWaterMoneyBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import static com.thinkive.android.trade_bz.R.id.tv_time;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditHistoryMoneyAdapter extends AbsBaseAdapter<RSelectHistoryWaterMoneyBean> {


    public CreditHistoryMoneyAdapter(Context context) {
        super(context, R.layout.item_a_money_water);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, RSelectHistoryWaterMoneyBean bean) {
        TextView titleTv = (TextView) holder.getComponentById(R.id.tv_title);
        TextView timeTv = (TextView) holder.getComponentById(tv_time);
        TextView noTv = (TextView) holder.getComponentById(R.id.tv_no);
        TextView stockCodeTv = (TextView) holder.getComponentById(R.id.tv_stockcode);
        TextView turnoverPriceTv = (TextView) holder.getComponentById(R.id.tv_turnover_price);
        TextView trunoverAmountTv = (TextView) holder.getComponentById(R.id.tv_turnover_amount);
        TextView happenPriceTv = (TextView) holder.getComponentById(R.id.tv_happen_price);
        TextView balanceTv = (TextView) holder.getComponentById(R.id.tv_balance);
        titleTv.setText(bean.getBusiness_name());
        timeTv.setText(bean.getInit_date());
        noTv.setText(bean.getEntrust_no());
        stockCodeTv.setText(bean.getInit_date());
        turnoverPriceTv.setText(bean.getBusiness_price());
        trunoverAmountTv.setText(bean.getOccur_amount());
        happenPriceTv.setText(bean.getOccur_balance());
        balanceTv.setText(bean.getPost_balance());
    }
}

