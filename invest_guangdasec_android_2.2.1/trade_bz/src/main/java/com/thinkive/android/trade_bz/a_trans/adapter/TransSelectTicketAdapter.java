package com.thinkive.android.trade_bz.a_trans.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectTicketBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;


/**
 * 转股交易 挂牌股票查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/29
 */
public class TransSelectTicketAdapter extends AbsBaseAdapter<TransSelectTicketBean> {

    private Context mContext;
    private FontManager mFontManager;

    public TransSelectTicketAdapter(Context context) {
        super(context, R.layout.item_trans_select_ticket);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, TransSelectTicketBean bean) {
        TextView tvTransStockName = (TextView) holder.getComponentById(R.id.tv_trans_stock_name);
        tvTransStockName.setText(bean.getStock_name());

        TextView tvTransStockCode = (TextView) holder.getComponentById(R.id.tv_trans_stock_code);
        tvTransStockCode.setText(bean.getStock_code());

        TextView tvTransUpLimit = (TextView) holder.getComponentById(R.id.tv_trans_up_limit);
        tvTransUpLimit.setText(bean.getUp_limit());

        TextView tvTransDownLimit = (TextView) holder.getComponentById(R.id.tv_trans_down_limit);
        tvTransDownLimit.setText(bean.getDown_limit());

        TextView tvTransTradeMarket = (TextView) holder.getComponentById(R.id.tv_trans_trade_market);
        tvTransTradeMarket.setText(bean.getExchange_type_name());

        TextView tvTransStockStatus = (TextView) holder.getComponentById(R.id.tv_trans_stock_status);
        tvTransStockStatus.setText(bean.getStock_type_name());

        mFontManager.setTextFont(tvTransStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTransStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTransUpLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTransDownLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTransTradeMarket, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTransStockStatus, FontManager.FONT_DINPRO_MEDIUM);
    }
}
