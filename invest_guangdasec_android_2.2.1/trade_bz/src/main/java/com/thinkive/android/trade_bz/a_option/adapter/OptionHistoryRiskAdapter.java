package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionHistoryRiskBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 个股期权历史风险通知查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class OptionHistoryRiskAdapter extends AbsBaseAdapter<OptionHistoryRiskBean> {

    private Context mContext;
    private FontManager mFontManager;

    public OptionHistoryRiskAdapter(Context context) {
        super(context, R.layout.item_option_history_risk);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, OptionHistoryRiskBean bean) {
        TextView tvNoticeSn = (TextView) holder.getComponentById(R.id.tv_notice_sn);
        tvNoticeSn.setText(bean.getNotice_sn());

        TextView tvChannel = (TextView) holder.getComponentById(R.id.tv_channel);
        tvChannel.setText(bean.getChannel());

        TextView tvSendType = (TextView) holder.getComponentById(R.id.tv_send_type);
        tvSendType.setText(bean.getSend_type());

        TextView tvRiskId = (TextView) holder.getComponentById(R.id.tv_risk_id);
        tvRiskId.setText(bean.getRisk_id());

        TextView tvCreateDate = (TextView) holder.getComponentById(R.id.tv_create_date);
        tvCreateDate.setText(bean.getCreate_date());

        TextView tvCreateTime = (TextView) holder.getComponentById(R.id.tv_create_time);
        tvCreateTime.setText(bean.getCreate_time());

        TextView tvSendDate = (TextView) holder.getComponentById(R.id.tv_send_date);
        tvSendDate.setText(bean.getSend_date());

        TextView tvSendTime = (TextView) holder.getComponentById(R.id.tv_send_time);
        tvSendTime.setText(bean.getSend_time());

        TextView tvContent = (TextView) holder.getComponentById(R.id.tv_content);
        tvContent.setText(bean.getContent());

        mFontManager.setTextFont(tvNoticeSn, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvChannel, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvSendType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvRiskId, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCreateDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCreateTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvSendDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvSendTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvContent, FontManager.FONT_DINPRO_MEDIUM);
    }
}
