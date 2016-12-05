package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditHistoryTradeAdapter extends AbsBaseAdapter<RSelectHistoryTradeBean> {
    private Context mContext;
    public CreditHistoryTradeAdapter(Context context) {
        super(context, R.layout.item_a_history_trade);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, RSelectHistoryTradeBean bean) {
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());


        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        //        String entrust_limit = bean.getEntrust_limit();
        titleStatus.setText("限价"  + ("0".equals(entrust_bs) ? "买入" : "卖出"));

        TextView timeTv = (TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getBusiness_time());

        TextView entrustInfoTv = (TextView) holder.getComponentById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getBusiness_price() + "元" );

        TextView turnoverInfoTv = (TextView) holder.getComponentById(R.id.tv_trunover_info);
        turnoverInfoTv.setText( bean.getBusiness_amount() + "股");
    }
}

