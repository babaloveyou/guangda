package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.TodayEntrustServicesImpl;

/**
 * Created by Administrator on 2016/10/19.
 */
public class BottomTodayEntrustAdapter extends AbsBaseAdapter<RevocationBean> {

    private Context mSubContext;
    private TodayEntrustServicesImpl mService;

    public BottomTodayEntrustAdapter(Context context, TodayEntrustServicesImpl services) {
        super(context, R.layout.item_bottom_today_entrust);
        mSubContext = context;
        mService = services;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, RevocationBean bean) {
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());


        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        String entrust_limit = bean.getEntrust_limit();
        titleStatus.setText(("0".equals(entrust_limit) ? "限价" : "市价") + ("0".equals(entrust_bs) ? "买入" : "卖出"));

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
