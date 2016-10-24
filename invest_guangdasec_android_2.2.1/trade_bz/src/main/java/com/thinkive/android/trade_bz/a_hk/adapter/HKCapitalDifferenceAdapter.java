package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalDifferenceBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/2 16:40
 * 描述	       价差查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalDifferenceAdapter extends AbsBaseAdapter<HKCapitalDifferenceBean> {

    private final FontManager mFontManager;

    public HKCapitalDifferenceAdapter(Context context) {
        super(context, R.layout.item_hk_difference);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalDifferenceBean bean) {
        TextView mTvExchangeTypeName = holder.getComponentById(R.id.tv_exchange_type_name);
        mTvExchangeTypeName.setText(bean.getExchange_type_name());

        TextView mTvStockTypeName = holder.getComponentById(R.id.tv_stock_type_name);
        mTvStockTypeName.setText(bean.getStock_type_name());

        TextView mTvUpLimit = holder.getComponentById(R.id.tv_up_limit);
        mTvUpLimit.setText(bean.getUp_limit());

        TextView mTvDownLimit = holder.getComponentById(R.id.tv_down_limit);
        mTvDownLimit.setText(bean.getDown_limit());

        TextView mTvStepPrice = holder.getComponentById(R.id.tv_step_price);
        mTvStepPrice.setText(bean.getStep_price());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvExchangeTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvUpLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvDownLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStepPrice, FontManager.FONT_DINPRO_MEDIUM);

    }
}
