package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 * 买入、卖出页面持仓列表适配器
 * 另一个是基本交易页面，资产选项卡中的
 * @author 黎丝军
 * @version 1.0
 * @corporation 思迪信息技术有限公司
 * @date 2015/5/26
 */
public class MyStoreListViewAdapterForBuysale extends AbsBaseAdapter<MyStoreStockBean> {

    /**
     * 字体设置管理器，用来给item中的textview设置字体
     */
    private FontManager mFontManager;

    /**
     * item_store_listview_in_buysale
     * @param context 外部调用环境
     */
    public MyStoreListViewAdapterForBuysale(Context context) {
        super(context, R.layout.item_a_hold_buy_or_sale);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, MyStoreStockBean bean) {
        TextView textViewSecurities = (TextView)holder.getComponentById(R.id.tv_securities);
        TextView textViewMarketValue = (TextView)holder.getComponentById(R.id.tv_market_value);
        TextView textViewStore = (TextView)holder.getComponentById(R.id.tv_store);
        TextView textViewUsable = (TextView)holder.getComponentById(R.id.tv_usable);
        TextView textViewCosting = (TextView)holder.getComponentById(R.id.tv_costing);
        TextView textViewNowPrice = (TextView)holder.getComponentById(R.id.tv_now_price);
        TextView textViewWinLoseNum = (TextView)holder.getComponentById(R.id.tv_win_lose_num);
        TextView textViewWinLoseRate = (TextView)holder.getComponentById(R.id.tv_win_lose_rate);

        String floatYk = bean.getFloat_yk();
        if(floatYk != null && !TextUtils.isEmpty(floatYk)){
            float num = Float.parseFloat(floatYk);
            if(num  > 0){
                textViewWinLoseNum.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
                textViewWinLoseRate.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
            }else if(num  < 0){
                textViewWinLoseNum.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                textViewWinLoseRate.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
            }else if(num  == 0){
                textViewWinLoseNum.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
                textViewWinLoseRate.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
            }
        }

        // 给Textview设置文字
        textViewSecurities.setText(bean.getStock_name());
        textViewMarketValue.setText(bean.getMarket_value());
        textViewStore.setText(bean.getCost_amount());
        textViewUsable.setText(bean.getEnable_amount());
        textViewCosting.setText(bean.getCost_price());
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
