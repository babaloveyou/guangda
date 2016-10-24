package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalMarketDayBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 19:35
 * 描述	     港股通交易日历查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalMarketDayAdapter extends AbsBaseAdapter<HKCapitalMarketDayBean> {

    private FontManager mFontManager;

    public HKCapitalMarketDayAdapter(Context context) {
        super(context, R.layout.item_hk_marketday);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalMarketDayBean bean) {
        TextView mTvExchangeTypeName = holder.getComponentById(R.id.tv_exchange_type_name);
        mTvExchangeTypeName.setText(bean.getExchange_type_name());

        TextView mTvMoneyTypeName = holder.getComponentById(R.id.tv_money_type_name);
        mTvMoneyTypeName.setText(bean.getMoney_type_name());

        TextView mTvBeginTime = holder.getComponentById(R.id.tv_begin_time);
        mTvBeginTime.setText(bean.getBegin_time());

        TextView mTvBusinessflag = holder.getComponentById(R.id.tv_businessflag);
        mTvBusinessflag.setText(bean.getBusinessflag());

        TextView mTvCommitflag = holder.getComponentById(R.id.tv_commitflag);
        mTvCommitflag.setText(bean.getCommitflag());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvExchangeTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMoneyTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBeginTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessflag, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCommitflag, FontManager.FONT_DINPRO_MEDIUM);

    }
}
