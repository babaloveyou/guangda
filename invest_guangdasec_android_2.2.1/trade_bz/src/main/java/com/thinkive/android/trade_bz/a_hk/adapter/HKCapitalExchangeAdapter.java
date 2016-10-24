package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalExchangeBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 17:25
 * 描述	        汇率查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalExchangeAdapter extends AbsBaseAdapter<HKCapitalExchangeBean> {

    private final FontManager mFontManager;

    public HKCapitalExchangeAdapter(Context context) {
        super(context, R.layout.item_hk_exchange);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalExchangeBean bean) {
        TextView mTvMoneyTypeName = holder.getComponentById(R.id.tv_money_type_name);
        mTvMoneyTypeName.setText(bean.getMoney_type_name());

        TextView mTvExchangeTypeName = holder.getComponentById(R.id.tv_exchange_type_name);
        mTvExchangeTypeName.setText(bean.getExchange_type_name());

        TextView mTvBuyrate = holder.getComponentById(R.id.tv_buyrate);
        mTvBuyrate.setText(bean.getBuyrate());

        TextView mTvSalerate = holder.getComponentById(R.id.tv_salerate);
        mTvSalerate.setText(bean.getSalerate());

        TextView mTvMidrate = holder.getComponentById(R.id.tv_midrate);
        mTvMidrate.setText(bean.getMidrate());

        TextView mTvSettrate = holder.getComponentById(R.id.tv_settrate);
        mTvSettrate.setText(bean.getSettrate());

        TextView mTvDaybuyriserate = holder.getComponentById(R.id.tv_daybuyriserate);
        mTvDaybuyriserate.setText(bean.getDaybuyriserate());

        TextView mTvDaysaleriserate = holder.getComponentById(R.id.tv_daysaleriserate);
        mTvDaysaleriserate.setText(bean.getDaysaleriserate());

        TextView mTvSysdate = holder.getComponentById(R.id.tv_sysdate);
        mTvSysdate.setText(bean.getSysdate());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvMoneyTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvExchangeTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBuyrate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSalerate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMidrate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSettrate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvDaybuyriserate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvDaysaleriserate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSysdate, FontManager.FONT_DINPRO_MEDIUM);

    }
}
