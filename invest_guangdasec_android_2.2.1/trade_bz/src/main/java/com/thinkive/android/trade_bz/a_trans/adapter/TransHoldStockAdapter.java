package com.thinkive.android.trade_bz.a_trans.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransHoldStockBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;


/**
 * 转股交易 持仓适配器
 * @author 张雪梅
 * @corporation thinkive
 * @date 2016/5/24
 */
public class TransHoldStockAdapter extends AbsBaseAdapter<TransHoldStockBean> {

    private FontManager mFontManager;

    public TransHoldStockAdapter(Context context) {
        super(context, R.layout.item_trans_hold_stock);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, TransHoldStockBean bean) {
        TextView textViewSecurities = holder.getComponentById(R.id.tv_securities);
        TextView textViewMarketValue = holder.getComponentById(R.id.tv_market_value);
        TextView textViewStore = holder.getComponentById(R.id.tv_store);
        TextView textViewUsable = holder.getComponentById(R.id.tv_usable);
        TextView textViewCosting = holder.getComponentById(R.id.tv_costing);
        TextView textViewNowPrice = holder.getComponentById(R.id.tv_now_price);
        TextView textViewWinLoseNum = holder.getComponentById(R.id.tv_win_lose_num);
        TextView textViewWinLoseRate = holder.getComponentById(R.id.tv_win_lose_rate);

        String floatYk = bean.getFloat_yk();
        if(floatYk != null && !TextUtils.isEmpty(floatYk)) {
            float num = Float.parseFloat(floatYk);
            if (num > 0) {
                textViewWinLoseNum.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
                textViewWinLoseRate.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
            } else if (num < 0) {
                textViewWinLoseNum.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                textViewWinLoseRate.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
            } else if (num == 0) {
                textViewWinLoseNum.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
                textViewWinLoseRate.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
            }
        }

        // 给Textview设置文字
        textViewSecurities.setText(bean.getStock_name());
        textViewMarketValue.setText(bean.getMarket_value());
        textViewStore.setText(bean.getCost_amount());
        textViewUsable.setText(bean.getEnable_amount());
        textViewCosting.setText(bean.getCost_balance());
        textViewNowPrice.setText(bean.getLast_price());
        textViewWinLoseNum.setText(bean.getFloat_yk());
        textViewWinLoseRate.setText(bean.getFloat_yk_per());

        // 给Textview设置字体
        mFontManager.setTextFont(textViewMarketValue, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(textViewStore, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(textViewUsable, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(textViewCosting, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(textViewNowPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(textViewWinLoseNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(textViewWinLoseRate, FontManager.FONT_DINPRO_MEDIUM);
    }
}
