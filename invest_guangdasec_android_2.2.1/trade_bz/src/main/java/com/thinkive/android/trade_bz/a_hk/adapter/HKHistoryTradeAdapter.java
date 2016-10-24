package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKHistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 * 港股通 历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */

public class HKHistoryTradeAdapter extends AbsBaseAdapter<HKHistoryTradeBean> {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 字体设置
     */
    private FontManager mFontManager;
    public HKHistoryTradeAdapter(Context context) {
        super(context, R.layout.item_hk_history_trade);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKHistoryTradeBean bean) {
        TextView tvData= holder.getComponentById(R.id.tv_history_trade_data);
        tvData.setText(bean.getBusiness_date());

        TextView tvTime= holder.getComponentById(R.id.tv_history_trade_time);
        tvTime.setText(bean.getBusiness_time());

        TextView tvName= holder.getComponentById(R.id.tv_history_trade_name);
        tvName.setText(bean.getStock_name());

        TextView tvMoney= holder.getComponentById(R.id.tv_history_trade_money);
        tvMoney.setText(bean.getBusiness_balance());

        TextView tvPrice= holder.getComponentById(R.id.tv_history_trade_pric);
        tvPrice.setText(bean.getBusiness_price());

        TextView tvCount= holder.getComponentById(R.id.tv_history_trade_count);
        tvCount.setText(bean.getBusiness_amount());

        TextView tvEntrustBs= holder.getComponentById(R.id.tv_history_trade_bs);
        tvEntrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        mFontManager.setTextFont(tvData, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustBs, FontManager.FONT_DINPRO_MEDIUM);
    }
}
