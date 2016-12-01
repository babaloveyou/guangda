package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayEntrustBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayEntrustAdapter extends AbsBaseAdapter<RSelectTodayEntrustBean> {

    private Context mSubContext;

    public CreditTodayEntrustAdapter(Context context) {
        super(context, R.layout.item_bottom_today_entrust);
        setIsReuseView(true);
        mSubContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, RSelectTodayEntrustBean bean) {
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());


        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        titleStatus.setText("限价" + ("0".equals(entrust_bs) ? "买入" : "卖出"));

        String entrust_amount = bean.getEntrust_amount();//委托数量
        String business_amount = bean.getBusiness_amount();//成交数目
        TextView statusTv = (TextView) holder.getComponentById(R.id.tv_turn_status);
        statusTv.setText(bean.getEntrust_state_name());
        TextView timeTv = (TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getEntrust_time());

        TextView entrustInfoTv = (TextView) holder.getComponentById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getEntrust_price() + "/" + bean.getEntrust_amount());

        TextView turnoverInfoTv = (TextView) holder.getComponentById(R.id.tv_trunover_info);
        turnoverInfoTv.setText(bean.getBusiness_price() + "/" + bean.getBusiness_amount());

    }
}

