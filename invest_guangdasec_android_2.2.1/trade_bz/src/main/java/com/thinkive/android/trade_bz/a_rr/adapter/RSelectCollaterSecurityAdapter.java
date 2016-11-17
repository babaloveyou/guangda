package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 融资融券--查询--担保品证券查询（303002）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectCollaterSecurityAdapter extends AbsBaseAdapter<RSelectCollaterSecurityBean> {
    private Context mContext;

    public RSelectCollaterSecurityAdapter(Context context) {
        super(context, R.layout.item_r_select_collater_security);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectCollaterSecurityBean bean) {
        TextView tvCode = (TextView) holder.getComponentById(R.id.tv_collater_code);
        tvCode.setText(bean.getStock_code());

        TextView tvName = (TextView) holder.getComponentById(R.id.tv_collater_name);
        tvName.setText(bean.getStock_name());

        TextView tvMarket = (TextView) holder.getComponentById(R.id.tv_collater_market);
//        0：深A，1：深B，2：沪A，3：沪B，4：三板，9：特转A，A：特转B，F1：郑州交易所，F2：大连交易所，F3：上海交易所，F4：金融交易所，G：港股
        switch (bean.getExchange_type()) {
            case "0":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type1));
                break;
            case "1":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type2));
                break;
            case "2":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type3));
                break;
            case "3":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type4));
                break;
            case "4":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type5));
                break;
            case "9":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type6));
                break;
            case "A":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type7));
                break;
            case "F1":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type8));
                break;
            case "F2":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type9));
                break;
            case "F3":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type10));
                break;
            case "F4":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type11));
                break;
            case "G":
                tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type12));
                break;

        }
        TextView tvProfit = (TextView) holder.getComponentById(R.id.tv_collater_profit);
        tvProfit.setText(TradeUtils.formatDouble2(Double.parseDouble(bean.getAssure_ratio()) * 100) + "%");

    }
}
