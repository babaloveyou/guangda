package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.StatementAccountBean;

import static com.thinkive.android.trade_bz.R.id.tv_time;

/**
 * 今日资金流水ListView适配器
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */

public class HistoryMoneyAdapter extends AbsBaseAdapter<StatementAccountBean> {


    public HistoryMoneyAdapter(Context context) {
        super(context, R.layout.item_a_money_water);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, StatementAccountBean bean) {
        TextView titleTv = (TextView) holder.getComponentById(R.id.tv_title);
        TextView timeTv = (TextView) holder.getComponentById(tv_time);
        TextView noTv = (TextView) holder.getComponentById(R.id.tv_no);
        TextView stockCodeTv = (TextView) holder.getComponentById(R.id.tv_stockcode);
        TextView turnoverPriceTv = (TextView) holder.getComponentById(R.id.tv_turnover_price);
        TextView trunoverAmountTv = (TextView) holder.getComponentById(R.id.tv_turnover_amount);
        TextView happenPriceTv = (TextView) holder.getComponentById(R.id.tv_happen_price);
        TextView balanceTv = (TextView) holder.getComponentById(R.id.tv_balance);
        titleTv.setText(bean.getBusiness_name());
        timeTv.setText(bean.getBusiness_date());
        noTv.setText(bean.getLsh());
        if (!TextUtils.isEmpty(bean.getStock_code())) {
            stockCodeTv.setText(bean.getStock_code());
        }
        turnoverPriceTv.setText(bean.getBusiness_price());
        trunoverAmountTv.setText(bean.getOccur_amount());
        happenPriceTv.setText(bean.getOccur_balance());
        balanceTv.setText(bean.getPost_balance());
    }
}
