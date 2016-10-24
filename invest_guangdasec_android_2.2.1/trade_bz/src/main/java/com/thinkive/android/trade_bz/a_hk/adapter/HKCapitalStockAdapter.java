package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKHoldStockBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 11:01
 * 描述	    资金股份查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalStockAdapter extends AbsBaseAdapter<HKHoldStockBean> {
    private FontManager mFontManager;

    public HKCapitalStockAdapter(Context context) {
        super(context, R.layout.item_hk_stock);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKHoldStockBean bean) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(bean.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(bean.getStock_code());

        TextView mTvCostPrice = holder.getComponentById(R.id.tv_cost_price);
        mTvCostPrice.setText(bean.getCost_price());

        TextView mTvLastPrice = holder.getComponentById(R.id.tv_last_price);
        mTvLastPrice.setText(bean.getLast_price());

        TextView mTvMarketValue = holder.getComponentById(R.id.tv_market_value);
        mTvMarketValue.setText(bean.getMarket_value());

        TextView mTvHoldAmount = holder.getComponentById(R.id.tv_hold_amount);
        mTvHoldAmount.setText(bean.getHold_amount());

        TextView mTvEnableAmount = holder.getComponentById(R.id.tv_enable_amount);
        mTvEnableAmount.setText(bean.getEnable_amount());

        TextView mTvFreezeAmount = holder.getComponentById(R.id.tv_freeze_amount);
        mTvFreezeAmount.setText(bean.getFreeze_amount());

        TextView mTvFloatYkPer = holder.getComponentById(R.id.tv_float_yk_per);
        mTvFloatYkPer.setText(bean.getFloat_yk_per());

        TextView mTvFloatYk = holder.getComponentById(R.id.tv_float_yk);
        mTvFloatYk.setText(bean.getFloat_yk());

        TextView mTvMoneyTypeName = holder.getComponentById(R.id.tv_money_type_name);
        mTvMoneyTypeName.setText(bean.getMoney_type_name());

        TextView mTvOnWayIn = holder.getComponentById(R.id.tv_on_way_in);
        mTvOnWayIn.setText(bean.getUncome_buy_amount());

        TextView mTvOnWayOut = holder.getComponentById(R.id.tv_on_way_out);
        mTvOnWayOut.setText(bean.getUncome_sell_amount());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCostPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvLastPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMarketValue, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHoldAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEnableAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFreezeAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFloatYkPer, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFloatYk, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMoneyTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvOnWayIn, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvOnWayOut, FontManager.FONT_DINPRO_MEDIUM);
    }
}
