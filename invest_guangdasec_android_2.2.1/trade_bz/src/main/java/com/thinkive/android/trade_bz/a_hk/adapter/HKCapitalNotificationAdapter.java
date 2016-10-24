package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalNotificationBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 19:45
 * 描述	    通知信息查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalNotificationAdapter extends AbsBaseAdapter<HKCapitalNotificationBean> {

    private final FontManager mFontManager;

    public HKCapitalNotificationAdapter(Context context) {
        super(context, R.layout.item_hk_notification);

        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(AbsBaseAdapter.ViewHolder holder, HKCapitalNotificationBean bean) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(bean.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(bean.getStock_code());

        TextView mTvNoteDate = holder.getComponentById(R.id.tv_note_date);
        mTvNoteDate.setText(bean.getNote_date());

        TextView mTvCirculationTypeName = holder.getComponentById(R.id.tv_circulation_type_name);
        mTvCirculationTypeName.setText(bean.getCirculation_type_name());

        TextView mTvAuthorityTypeName = holder.getComponentById(R.id.tv_authority_type_name);
        mTvAuthorityTypeName.setText(bean.getAuthority_type_name());

        TextView mTvMarketYear = holder.getComponentById(R.id.tv_market_year);
        mTvMarketYear.setText(bean.getMarket_year());

        TextView mTvAuthorityTimes = holder.getComponentById(R.id.tv_authority_times);
        mTvAuthorityTimes.setText(bean.getAuthority_times());

        TextView mTvCurrentPrice = holder.getComponentById(R.id.tv_current_price);
        mTvCurrentPrice.setText(bean.getCurrent_price());

        TextView mTvMoneyTypeName = holder.getComponentById(R.id.tv_money_type_name);
        mTvMoneyTypeName.setText(bean.getMoney_type_name());

        TextView mTvSno = holder.getComponentById(R.id.tv_sno);
        mTvSno.setText(bean.getSno());

        TextView mTvCurrentRate = holder.getComponentById(R.id.tv_current_rate);
        mTvCurrentRate.setText(bean.getCurrent_rate());


        // 给Textview设置字体
        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvNoteDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCirculationTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvAuthorityTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMarketYear, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvAuthorityTimes, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCurrentPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMoneyTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSno, FontManager.FONT_DINPRO_MEDIUM);
    }
}
