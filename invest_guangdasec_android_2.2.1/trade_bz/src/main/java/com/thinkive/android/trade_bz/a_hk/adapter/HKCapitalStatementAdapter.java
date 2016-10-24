package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalStatementBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 17:32
 * 描述	    沪港通对帐单查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalStatementAdapter extends AbsBaseAdapter<HKCapitalStatementBean> {

    private final FontManager mFontManager;

    public HKCapitalStatementAdapter(Context context) {
        super(context, R.layout.item_hk_statement);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalStatementBean bean) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(bean.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(bean.getStock_code());

        TextView mTvUddelegerePriser = holder.getComponentById(R.id.tv_uddelegere_priser);
        mTvUddelegerePriser.setText(bean.getUddelegere_priser());

        TextView mTvBusinessPrice = holder.getComponentById(R.id.tv_business_price);
        mTvBusinessPrice.setText(bean.getBusiness_price());

        TextView mTvUddelegereNumbler = holder.getComponentById(R.id.tv_uddelegere_numbler);
        mTvUddelegereNumbler.setText(bean.getEntrust_amount());

        TextView mTvBusinessAmount = holder.getComponentById(R.id.tv_business_amount);
        mTvBusinessAmount.setText(bean.getBusiness_amount());

        TextView mTvBusinessBalance = holder.getComponentById(R.id.tv_business_balance);
        mTvBusinessBalance.setText(bean.getBusiness_balance());

        TextView mTvEntrustBalance = holder.getComponentById(R.id.tv_entrust_balance);
        mTvEntrustBalance.setText(bean.getEntrust_balance());

        TextView mTvSurplusBalance = holder.getComponentById(R.id.tv_surplus_balance);
        mTvSurplusBalance.setText(bean.getSurplus_balance());

        TextView mTvMoneyTypeName1 = holder.getComponentById(R.id.tv_money_type_name1);
        mTvMoneyTypeName1.setText(bean.getMoney_type_name());

        TextView mTvEntrustNo = holder.getComponentById(R.id.tv_entrust_no);
        mTvEntrustNo.setText(bean.getEntrust_no());

        TextView mTvSettrate = holder.getComponentById(R.id.tv_settrate);
        mTvSettrate.setText(bean.getSettrate());

        TextView mTvBusinessTypeName = holder.getComponentById(R.id.tv_business_type_name);
        mTvBusinessTypeName.setText(bean.getBusiness_type_name());

        TextView mTvCleardate = holder.getComponentById(R.id.tv_cleardate);
        mTvCleardate.setText(bean.getCleardate());

        TextView mTvEntrustDate = holder.getComponentById(R.id.tv_entrust_date);
        mTvEntrustDate.setText(bean.getEntrust_date());

        TextView mTvBizdate = holder.getComponentById(R.id.tv_bizdate);
        mTvBizdate.setText(bean.getBizdate());

        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvUddelegerePriser, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvUddelegereNumbler, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEntrustBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSurplusBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMoneyTypeName1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEntrustNo, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSettrate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCleardate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEntrustDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBizdate, FontManager.FONT_DINPRO_MEDIUM);

    }
}
