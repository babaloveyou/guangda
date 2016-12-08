package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.HistoryTradeBean;

/**
 * 历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */

public class HistoryTradeAdapter extends AbsBaseAdapter<HistoryTradeBean> {
    private Context mContext;
    public HistoryTradeAdapter(Context context) {
        super(context,R.layout.item_a_history_trade);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HistoryTradeBean bean) {
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());


        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        //        String entrust_limit = bean.getEntrust_limit();
        titleStatus.setText(bean.getEntrust_type_name());

        TextView timeTv = (TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getBusiness_time());

        TextView entrustInfoTv = (TextView) holder.getComponentById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getBusiness_price() + "元" );

        TextView turnoverInfoTv = (TextView) holder.getComponentById(R.id.tv_trunover_info);
        turnoverInfoTv.setText( bean.getBusiness_amount() + "股");
    }
}
