package com.thinkive.android.trade_bz.a_level.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_level.bean.LFundEntrustDataBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 * 分级基金 今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LFundTodayEntrustAdapter extends AbsBaseAdapter<LFundEntrustDataBean> {
    private Context mContext;
    private FontManager mFontManager;
    public LFundTodayEntrustAdapter(Context context) {
        super(context, R.layout.item_level_today_entrust);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, LFundEntrustDataBean bean) {
        TextView tvEntrustBs=(TextView) holder.getComponentById(R.id.tv_hfund_bs);
        tvEntrustBs.setText(bean.getEntrust_name());

        TextView tvTime=(TextView) holder.getComponentById(R.id.tv_hfund_time);
        tvTime.setText(bean.getEntrust_time());

        TextView tvStatus=(TextView) holder.getComponentById(R.id.tv_hfund_status);
        tvStatus.setText(bean.getEntrust_state_name());

        TextView tvName=(TextView) holder.getComponentById(R.id.tv_hfund_name);
        tvName.setText(bean.getStock_name());

        TextView tvCode=(TextView) holder.getComponentById(R.id.tv_hfund_code);
        tvCode.setText(bean.getStock_code());

        TextView tvCount=(TextView) holder.getComponentById(R.id.tv_hfund_count);
        tvCount.setText(bean.getEntrust_amount());

        TextView tvPrice=(TextView) holder.getComponentById(R.id.tv_hfund_price);
        tvPrice.setText(bean.getBusiness_price());

        mFontManager.setTextFont(tvEntrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStatus, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCount, FontManager.FONT_DINPRO_MEDIUM);
    }
}
