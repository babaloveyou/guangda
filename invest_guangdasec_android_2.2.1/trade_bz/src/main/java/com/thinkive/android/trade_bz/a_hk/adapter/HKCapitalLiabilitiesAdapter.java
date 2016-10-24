package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalLiabilitiesBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/2 16:35
 * 描述	    负债查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalLiabilitiesAdapter extends AbsBaseAdapter<HKCapitalLiabilitiesBean> {

    private final FontManager mFontManager;

    public HKCapitalLiabilitiesAdapter(Context context) {
        super(context, R.layout.item_hk_liabilities);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalLiabilitiesBean bean) {
        TextView mTvUnpayamt = holder.getComponentById(R.id.tv_unpayamt);
        mTvUnpayamt.setText(bean.getUnpayamt());

        TextView mTvSumpaidamt = holder.getComponentById(R.id.tv_sumpaidamt);
        mTvSumpaidamt.setText(bean.getSumpaidamt());

        TextView mTvSumdebtamt = holder.getComponentById(R.id.tv_sumdebtamt);
        mTvSumdebtamt.setText(bean.getSumdebtamt());

        TextView mTvMoneyTypeName = holder.getComponentById(R.id.tv_money_type_name);
        mTvMoneyTypeName.setText(bean.getMoney_type_name());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvUnpayamt, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSumpaidamt, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSumdebtamt, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMoneyTypeName, FontManager.FONT_DINPRO_MEDIUM);

    }
}
