package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalFundBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/2 14:16
 * 描述	     资金明细查询的Adpter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalFundAdapter extends AbsBaseAdapter<HKCapitalFundBean> {

    private final FontManager mFontManager;

    public HKCapitalFundAdapter(Context context) {
        super(context, R.layout.item_hk_fund);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalFundBean bean) {
        TextView mTvAssertVal = holder.getComponentById(R.id.tv_assert_val);
        mTvAssertVal.setText(bean.getAssert_val());

        TextView mTvEnableBalance = holder.getComponentById(R.id.tv_enable_balance);
        mTvEnableBalance.setText(bean.getEnable_balance());

        TextView mTvFundTranin = holder.getComponentById(R.id.tv_fund_tranin);
        mTvFundTranin.setText(bean.getFund_tranin());

        TextView mTvFundTranout = holder.getComponentById(R.id.tv_fund_tranout);
        mTvFundTranout.setText(bean.getFund_tranout());

        TextView mTvFundRealin = holder.getComponentById(R.id.tv_fund_realin);
        mTvFundRealin.setText(bean.getFund_realin());

        TextView mTvFundRealout = holder.getComponentById(R.id.tv_fund_realout);
        mTvFundRealout.setText(bean.getFund_realout());

        TextView mTvFundnightout = holder.getComponentById(R.id.tv_fundnightout);
        mTvFundnightout.setText(bean.getFundnightout());

        TextView mTvFundRealOwn = holder.getComponentById(R.id.tv_fund_real_own);
        mTvFundRealOwn.setText(bean.getFund_real_own());

        TextView mTvFundCode = holder.getComponentById(R.id.tv_fund_code);
        mTvFundCode.setText(bean.getFund_code());

        TextView mTvFunduncomein = holder.getComponentById(R.id.tv_funduncomein);
        mTvFunduncomein.setText(bean.getFunduncomein());

        TextView mTvFunduncomeout = holder.getComponentById(R.id.tv_funduncomeout);
        mTvFunduncomeout.setText(bean.getFunduncomeout());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvAssertVal, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEnableBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFundTranin, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFundTranout, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFundRealin, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFundRealout, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFundnightout, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFundRealOwn, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFundCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFunduncomein, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvFunduncomeout, FontManager.FONT_DINPRO_MEDIUM);
    }
}
