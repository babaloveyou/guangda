package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalLimitBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 14:00
 * 描述	    额度查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalLimitAdapter extends AbsBaseAdapter<HKCapitalLimitBean> {

    private final FontManager mFontManager;
    private Context mContext;

    public HKCapitalLimitAdapter(Context context) {
        super(context, R.layout.item_hk_limit);
        mFontManager = FontManager.getInstance(context);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalLimitBean bean) {
        TextView mTvExchangeTypeName = holder.getComponentById(R.id.tv_exchange_type_name);
        mTvExchangeTypeName.setText(bean.getExchange_type_name());

        TextView mTvTotalQuota = holder.getComponentById(R.id.tv_total_quota);
        mTvTotalQuota.setText(bean.getTotal_quota());

        TextView mTvSurplusQuota = holder.getComponentById(R.id.tv_surplus_quota);
        mTvSurplusQuota.setText(bean.getSurplus_quota());

        TextView mTvProductStatus = holder.getComponentById(R.id.tv_product_status);
        if ("1".equals(bean.getProduct_status())) {
            mTvProductStatus.setText(mContext.getResources().getString(R.string.hk_quota_item6));
        } else if ("2".equals(bean.getProduct_status())) {
            mTvProductStatus.setText(mContext.getResources().getString(R.string.hk_quota_item7));
        }

        TextView mTvMkttrdstatusName = holder.getComponentById(R.id.tv_mkttrdstatus_name);
        mTvMkttrdstatusName.setText(bean.getMkttrdstatus_name());

        TextView mTvUpddate = holder.getComponentById(R.id.tv_upddate);
        mTvUpddate.setText(bean.getUpddate());


        // 给Textview设置字体
        mFontManager.setTextFont(mTvExchangeTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvTotalQuota, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSurplusQuota, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvProductStatus, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMkttrdstatusName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvUpddate, FontManager.FONT_DINPRO_MEDIUM);

    }
}
