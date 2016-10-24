package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalDeliveryBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 20:03
 * 描述	    交割单查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalDeliveryAdapter extends AbsBaseAdapter<HKCapitalDeliveryBean> {

    private final FontManager mFontManager;

    public HKCapitalDeliveryAdapter(Context context) {
        super(context, R.layout.item_hk_delivery);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalDeliveryBean bean) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(bean.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(bean.getStock_code());

        TextView mTvEntrustPrice = holder.getComponentById(R.id.tv_entrust_price);
        mTvEntrustPrice.setText(bean.getEntrust_price());

        TextView mTvBusinessPrice = holder.getComponentById(R.id.tv_business_price);
        mTvBusinessPrice.setText(bean.getBusiness_price());

        TextView mTvEntrustAmount = holder.getComponentById(R.id.tv_entrust_amount);
        mTvEntrustAmount.setText(bean.getEntrust_amount());

        TextView mTvBusinessAmount = holder.getComponentById(R.id.tv_business_amount);
        mTvBusinessAmount.setText(bean.getBusiness_amount());

        TextView mTvBusinessBalance = holder.getComponentById(R.id.tv_business_balance);
        mTvBusinessBalance.setText(bean.getBusiness_balance());

        TextView mTvSetBalance = holder.getComponentById(R.id.tv_set_balance);
        mTvSetBalance.setText(bean.getSet_balance());

        TextView mTvSurplusBalance = holder.getComponentById(R.id.tv_surplus_balance);
        mTvSurplusBalance.setText(bean.getSurplus_balance());

        TextView mTvSurplusAmount = holder.getComponentById(R.id.tv_surplus_amount);
        mTvSurplusAmount.setText(bean.getSurplus_amount());

        TextView mTvFeeYhs = holder.getComponentById(R.id.tv_fee_yhs);
        mTvFeeYhs.setText(bean.getFee_yhs());

        TextView mTvFeeSxf = holder.getComponentById(R.id.tv_fee_sxf);
        mTvFeeSxf.setText(bean.getFee_sxf());

        TextView mTvSettrate = holder.getComponentById(R.id.tv_settrate);
        mTvSettrate.setText(bean.getSettrate());

        TextView mTvBusinessId = holder.getComponentById(R.id.tv_business_id);
        mTvBusinessId.setText(bean.getBusiness_id());

        TextView mTvMoneyTypeName = holder.getComponentById(R.id.tv_money_type_name);
        mTvMoneyTypeName.setText(bean.getMoney_type_name());

        TextView mTvBusinessTime = holder.getComponentById(R.id.tv_business_time);
        mTvBusinessTime.setText(bean.getBusiness_time());

        TextView mTvBizdate = holder.getComponentById(R.id.tv_bizdate);
        mTvBizdate.setText(bean.getBizdate());

        TextView mTvBusinessType = holder.getComponentById(R.id.tv_business_type);
        mTvBusinessType.setText(bean.getBusiness_name());

        TextView mTvFeeZgf = holder.getComponentById(R.id.tv_fee_zgf);
        mTvFeeZgf.setText(bean.getFee_zgf());

        TextView mTvFeeJygf = holder.getComponentById(R.id.tv_fee_jygf);
        mTvFeeJygf.setText(bean.getFee_jygf());

        TextView mTvFeefront = holder.getComponentById(R.id.tv_feefront);
        mTvFeefront.setText(bean.getFeefront());

        TextView mTvFeeQsf = holder.getComponentById(R.id.tv_fee_qsf);
        mTvFeeQsf.setText(bean.getFee_qsf());

        TextView mTvFeeJsf = holder.getComponentById(R.id.tv_fee_jsf);
        mTvFeeJsf.setText(bean.getFee_jsf());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEntrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSetBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSurplusBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSurplusAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFeeYhs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFeeSxf, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSettrate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessId, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMoneyTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBizdate, FontManager.FONT_DINPRO_MEDIUM);
    }
}
