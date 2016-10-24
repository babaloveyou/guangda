package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalCorporateBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/7 14:05
 * 描述	   公司行为委托查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalCorporateAdapter extends AbsBaseAdapter<HKCapitalCorporateBean> {

    private final FontManager mFontManager;
    private TextView mTvReportid;

    public HKCapitalCorporateAdapter(Context context) {
        super(context, R.layout.item_hk_corporate);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalCorporateBean bean) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(bean.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(bean.getStock_code());

        TextView mTvApplyTime = holder.getComponentById(R.id.tv_apply_time);
        mTvApplyTime.setText(bean.getApply_time());

        TextView mTvCorpbehaviorCode = holder.getComponentById(R.id.tv_corpbehavior_code);
        mTvCorpbehaviorCode.setText(bean.getCorpbehavior_code());

        TextView mTvReportType = holder.getComponentById(R.id.tv_report_type);
        mTvReportType.setText(bean.getReport_type_name());

        TextView mTvEntrustAmount = holder.getComponentById(R.id.tv_entrust_amount);
        mTvEntrustAmount.setText(bean.getEntrust_amount());

        TextView mTvTrustStatusFj = holder.getComponentById(R.id.tv_trust_status_fj);
        mTvTrustStatusFj.setText(bean.getTrust_status_fj());

        TextView mTvBbusinessType = holder.getComponentById(R.id.tv_bbusiness_type);
        mTvBbusinessType.setText(bean.getBusiness_type_name());

        TextView mTvBusinessId = holder.getComponentById(R.id.tv_business_id);
        mTvBusinessId.setText(bean.getBusiness_id());

        mTvReportid = holder.getComponentById(R.id.tv_report_id);
        mTvReportid.setText(bean.getReport_id());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvApplyTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCorpbehaviorCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvReportType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvTrustStatusFj, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBbusinessType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvBusinessId, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvReportid, FontManager.FONT_DINPRO_MEDIUM);
    }
}
