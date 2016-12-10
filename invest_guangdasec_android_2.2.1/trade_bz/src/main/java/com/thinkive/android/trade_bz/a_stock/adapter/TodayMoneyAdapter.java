package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.TodayMoneyBean;

import static com.thinkive.android.trade_bz.R.id.tv_time;

/**
 * Created by Administrator on 2016/11/29.
 */
public class TodayMoneyAdapter extends AbsBaseAdapter<TodayMoneyBean> {
    public TodayMoneyAdapter(Context context) {
        super(context, R.layout.item_a_today_money_water);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, TodayMoneyBean bean) {
        TextView titleTv = (TextView) holder.getComponentById(R.id.tv_title);
        TextView timeTv = (TextView) holder.getComponentById(tv_time);
        TextView stockCodeTv = (TextView) holder.getComponentById(R.id.tv_stockcode);
        TextView turnoverPriceTv = (TextView) holder.getComponentById(R.id.tv_turnover_price);
        TextView trunoverAmountTv = (TextView) holder.getComponentById(R.id.tv_turnover_amount);
        TextView happenPriceTv = (TextView) holder.getComponentById(R.id.tv_happen_price);
        titleTv.setText(bean.getBusiness_name());
        timeTv.setText(bean.getBusiness_date());
        stockCodeTv.setText(bean.getStock_code());
        turnoverPriceTv.setText(bean.getOccur_balance());
        trunoverAmountTv.setText(bean.getOccur_amount());
        happenPriceTv.setText(bean.getOrderprice());
    }
}
