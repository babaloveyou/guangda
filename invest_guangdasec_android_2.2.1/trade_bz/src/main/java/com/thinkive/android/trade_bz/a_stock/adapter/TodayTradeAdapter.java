package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.HistoryTradeBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 *   今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/3
 */

public class TodayTradeAdapter extends AbsBaseAdapter<HistoryTradeBean> {
    private Context mContext;
    private FontManager mFontManager;
    public TodayTradeAdapter(Context context) {
        super(context,R.layout.item_a_today_trade);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HistoryTradeBean bean) {
        TextView name=(TextView) holder.getComponentById(R.id.tv_a_today_trade_name);
        name.setText(bean.getStock_name());

        TextView time=(TextView) holder.getComponentById(R.id.tv_a_today_trade_time);
        time.setText(bean.getBusiness_time());

        TextView tradePrice=(TextView) holder.getComponentById(R.id.tv_a_today_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeNum=(TextView) holder.getComponentById(R.id.tv_a_today_trade_num);
        tradeNum.setText(bean.getBusiness_amount());

        TextView tradeBalances=(TextView) holder.getComponentById(R.id.tv_a_today_trade_balances);
        tradeBalances.setText(bean.getBusiness_balance());

        TextView entrustBs=(TextView) holder.getComponentById(R.id.tv_a_today_trade_bs);
        entrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(time, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeBalances, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
    }
}
