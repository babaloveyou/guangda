package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKNoTradeBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/1 16:40
 * 描述	     未交明细查询Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalNoTradeAdapter extends AbsBaseAdapter<HKNoTradeBean> {


    private final FontManager mFontManager;

    public HKCapitalNoTradeAdapter(Context context) {
        super(context, R.layout.item_hk_no_trade);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKNoTradeBean resultsEntity) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(resultsEntity.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(resultsEntity.getStock_code());

        TextView mTvHkNotPaidDetaPrice = holder.getComponentById(R.id.tv_hk_not_paid_deta_price);
        mTvHkNotPaidDetaPrice.setText(resultsEntity.getEntrust_amount());

        TextView mTvHkTransactionPrice = holder.getComponentById(R.id.tv_hk_transaction_price);
        mTvHkTransactionPrice.setText(resultsEntity.getBusiness_amount());

        TextView mTvHkNotPaidDetaEntrust = holder.getComponentById(R.id.tv_hk_not_paid_deta_entrust);
        mTvHkNotPaidDetaEntrust.setText(resultsEntity.getEntrust_amount());

        TextView mTvHkNotPaidDetailNumbler = holder.getComponentById(R.id.tv_hk_not_paid_detail_numbler);
        mTvHkNotPaidDetailNumbler.setText(resultsEntity.getBusiness_amount());

        TextView mTvTvHkNotPaidDetailMoney = holder.getComponentById(R.id.tv_tv_hk_not_paid_detail_money);
        mTvTvHkNotPaidDetailMoney.setText(resultsEntity.getBusiness_balance());

        TextView mTvHkLiquidationMoney = holder.getComponentById(R.id.tv_hk_liquidation_money);
        mTvHkLiquidationMoney.setText(resultsEntity.getCleared_balance());

        TextView mTvHkStampDuty = holder.getComponentById(R.id.tv_hk_stamp_duty);
        mTvHkStampDuty.setText(resultsEntity.getFee_yhs());

        TextView mTvHkCommission = holder.getComponentById(R.id.tv_hk_commission);
        mTvHkCommission.setText(resultsEntity.getFee_sxf());

        TextView mTvHkTransactionNumber = holder.getComponentById(R.id.tv_hk_transaction_number);
        mTvHkTransactionNumber.setText(resultsEntity.getBusiness_id());

        TextView mTvHkSettlementRate = holder.getComponentById(R.id.tv_hk_settlement_rate);
        mTvHkSettlementRate.setText(resultsEntity.getSettrate());

        TextView mTvHkCurrency = holder.getComponentById(R.id.tv_hk_currency);
        mTvHkCurrency.setText(resultsEntity.getMoney_type_name());

        TextView mTvHkBusinessName = holder.getComponentById(R.id.tv_hk_business_name);
        mTvHkBusinessName.setText(resultsEntity.getState_busitype_name());

        TextView mTvHkCommissionData = holder.getComponentById(R.id.tv_hk_commission_data);
        mTvHkCommissionData.setText(resultsEntity.getEntrust_date());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkNotPaidDetaPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkTransactionPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkNotPaidDetaEntrust, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkNotPaidDetailNumbler, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvTvHkNotPaidDetailMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkLiquidationMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkStampDuty, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkCommission, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkTransactionNumber, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkSettlementRate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkCurrency, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkBusinessName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvHkCommissionData, FontManager.FONT_DINPRO_MEDIUM);


    }
}
