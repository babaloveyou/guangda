package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 *  基金交易--持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundHoldCountAdapter extends AbsBaseAdapter<FundHoldBean> {

    private Context mContext;
    private FontManager mFontManager;

    public FundHoldCountAdapter(Context context) {
        super(context, R.layout.item_fund_hold_count);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final FundHoldBean bean) {
        TextView tvName=(TextView) holder.getComponentById(R.id.tv_outf_name);
        tvName.setText(bean.getFund_name());

        TextView tvCode=(TextView) holder.getComponentById(R.id.tv_outf_code);
        tvCode.setText(bean.getFund_code());

        TextView tvProfit=(TextView) holder.getComponentById(R.id.tv_outf_profit);
        tvProfit.setText(bean.getNav());

        TextView tvShare=(TextView) holder.getComponentById(R.id.tv_outf_share);
        tvShare.setText(bean.getDividendmethod_name());

        TextView tvValue=(TextView) holder.getComponentById(R.id.tv_outf_value);
        tvValue.setText(bean.getMarket_value());

        TextView tvCount=(TextView) holder.getComponentById(R.id.tv_outf_count);
        tvCount.setText(bean.getEnable_shares());

        TextView tvStatus=(TextView) holder.getComponentById(R.id.tv_outf_status);
        tvStatus.setText(bean.getFund_status_name());

        mFontManager.setTextFont(tvProfit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvShare, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvValue, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStatus, FontManager.FONT_DINPRO_MEDIUM);
    }
}
