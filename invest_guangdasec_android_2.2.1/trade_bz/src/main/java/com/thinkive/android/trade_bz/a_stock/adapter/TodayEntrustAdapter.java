package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;

/**
 * 今日委托的适配器
 * （与撤单共用实体类）
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/16
 */

public class TodayEntrustAdapter extends AbsBaseAdapter<RevocationBean> {

    private Context mSubContext;

    public TodayEntrustAdapter(Context context) {
        super(context, R.layout.item_bottom_today_entrust);
        setIsReuseView(true);
        mSubContext = context;
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
        if (entrust_amount == business_amount) {
            statusTv.setText("全部成交");
        } else {
            statusTv.setText("部分成交");
        }
        TextView timeTv = (TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getEntrust_time());

        TextView entrustInfoTv = (TextView) holder.getComponentById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getEntrust_price() + "元/" + bean.getEntrust_amount() + "股");

        TextView turnoverInfoTv = (TextView) holder.getComponentById(R.id.tv_trunover_info);
        turnoverInfoTv.setText(bean.getBusiness_price() + "元/" + bean.getBusiness_amount() + "股");

    }
}
