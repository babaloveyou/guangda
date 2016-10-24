package com.thinkive.android.trade_bz.a_level.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_level.bean.LFundTradeDataBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 * 分级基金 今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LFundTodayTradeAdapter extends AbsBaseAdapter<LFundTradeDataBean> {
    private Context mContext;
    private FontManager mFontManager;
    public LFundTodayTradeAdapter(Context context) {
        super(context, R.layout.item_level_today_trade);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, LFundTradeDataBean bean) {
        TextView tvStatus=(TextView) holder.getComponentById(R.id.tv_hfund_status);
        tvStatus.setText(bean.getReal_status_name());

        TextView tvData=(TextView) holder.getComponentById(R.id.tv_tfund_time);
        tvData.setText(bean.getBusiness_time());

        TextView tvName=(TextView) holder.getComponentById(R.id.tv_tfund_name);
        tvName.setText(bean.getStock_name());

        TextView tvCode=(TextView) holder.getComponentById(R.id.tv_tfund_code);
        tvCode.setText(bean.getStock_code());

        TextView tvProfit=(TextView) holder.getComponentById(R.id.tv_tfund_count);
        tvProfit.setText(bean.getBusiness_price());

        TextView tvBalance=(TextView) holder.getComponentById(R.id.tv_tfund_price);
        tvBalance.setText(bean.getBusiness_amount());

        mFontManager.setTextFont(tvStatus, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvData, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvProfit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBalance, FontManager.FONT_DINPRO_MEDIUM);
    }
}
