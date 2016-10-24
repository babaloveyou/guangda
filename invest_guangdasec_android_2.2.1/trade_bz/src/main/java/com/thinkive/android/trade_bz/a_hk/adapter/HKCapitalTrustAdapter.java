package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalTrustBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/7 13:28
 * 描述	      投票委托查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalTrustAdapter extends AbsBaseAdapter<HKCapitalTrustBean> {

    private final FontManager mFontManager;

    public HKCapitalTrustAdapter(Context context) {
        super(context, R.layout.item_hk_trust);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalTrustBean bean) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(bean.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(bean.getStock_code());

        TextView mTvReportType = holder.getComponentById(R.id.tv_report_type);
        mTvReportType.setText("gone");

        TextView mTvApplyTime = holder.getComponentById(R.id.tv_apply_time);
        mTvApplyTime.setText(bean.getApply_time());

        TextView mTvPlacardId = holder.getComponentById(R.id.tv_placard_id);
        mTvPlacardId.setText(bean.getPlacard_id());

        TextView mTvMotionId = holder.getComponentById(R.id.tv_motion_id);
        mTvMotionId.setText(bean.getMotion_id());

        TextView mTvApproveAmount = holder.getComponentById(R.id.tv_approve_amount);
        mTvApproveAmount.setText(bean.getApprove_amount());

        TextView mTvOpposeAmount = holder.getComponentById(R.id.tv_oppose_amount);
        mTvOpposeAmount.setText(bean.getOppose_amount());

        TextView mTvWaiveAmount = holder.getComponentById(R.id.tv_waive_amount);
        mTvWaiveAmount.setText(bean.getWaive_amount());

        TextView mTvTrustStatusFj = holder.getComponentById(R.id.tv_trust_status_fj);
        mTvTrustStatusFj.setText(bean.getTrust_status_fj());

        TextView mTvBusinessId = holder.getComponentById(R.id.tv_business_id);
        mTvBusinessId.setText(bean.getBusiness_id());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvReportType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvApplyTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvPlacardId, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMotionId, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvApproveAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvOpposeAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvWaiveAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvTrustStatusFj, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessId, FontManager.FONT_DINPRO_MEDIUM);

    }
}
