package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectDOSelectBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 * 融资融券--查询--交割单（303027）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectDOselectAdapter extends AbsBaseAdapter<RSelectDOSelectBean> {
    private Context mSubContext;

    public RSelectDOselectAdapter(Context context) {
        super(context, R.layout.item_r_select_do);
        mSubContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectDOSelectBean bean) {
        TextView entrustBs = (TextView) holder.getComponentById(R.id.tv_r_do_bs);
        TextView date = (TextView) holder.getComponentById(R.id.tv_r_do_date);
        TextView tradeNum = (TextView) holder.getComponentById(R.id.tv_r_do_trade_num);
        TextView tradePrice = (TextView) holder.getComponentById(R.id.tv_r_do_trade_price);
        TextView tradeBalance = (TextView) holder.getComponentById(R.id.tv_r_do_trade_balances);
        TextView qingMoney = (TextView) holder.getComponentById(R.id.tv_r_do_qing_money);
        TextView fareShou = (TextView) holder.getComponentById(R.id.tv_r_do_fare_shou);
        TextView fareQing = (TextView) holder.getComponentById(R.id.tv_r_do_fare_qing);
        TextView fareTrade = (TextView) holder.getComponentById(R.id.tv_r_do_fare_trade);
        TextView fareYinHua = (TextView) holder.getComponentById(R.id.tv_r_do_fare_yin);
        TextView fareGuoHu = (TextView) holder.getComponentById(R.id.tv_r_do_fare_guo_hu);
        TextView compactId = (TextView) holder.getComponentById(R.id.tv_r_do_compact_id);
        TextView tradeId = (TextView) holder.getComponentById(R.id.tv_r_do_trade_id);
        TextView stockYu = (TextView) holder.getComponentById(R.id.tv_r_do_stock_yu);
        TextView moneyYu = (TextView) holder.getComponentById(R.id.tv_r_do_money_yu);
        TextView stockAccount = (TextView) holder.getComponentById(R.id.tv_r_do_stock_account);
        TextView market = (TextView) holder.getComponentById(R.id.tv_r_do_market);

        entrustBs.setText(bean.getBusiness_name());
        date.setText(bean.getInit_date());
        tradeNum.setText(bean.getOccur_amount());
        tradePrice.setText(bean.getBusiness_price());
        tradeBalance.setText(bean.getBusiness_balance());
        qingMoney.setText(bean.getFundeffect());
        fareShou.setText(bean.getFee_sxf());
        fareQing.setText(bean.getFee_qsf());
        fareTrade.setText(bean.getFee_jygf());
        fareYinHua.setText(bean.getFare1());
        fareGuoHu.setText(bean.getFare2());
        compactId.setText(bean.getReport_no());
        tradeId.setText(bean.getBusiness_no());
        stockYu.setText(bean.getPost_amount());
        moneyYu.setText(bean.getPost_balance());
        stockAccount.setText(bean.getStock_account());
        market.setText(bean.getExchange_type_name());
//        remark.setText(bean.getRemark());

    }
}
