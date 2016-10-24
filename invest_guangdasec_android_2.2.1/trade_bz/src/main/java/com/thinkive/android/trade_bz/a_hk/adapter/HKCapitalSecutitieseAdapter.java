package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKSecutitieseBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 10:15
 * 描述	      标的证券查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalSecutitieseAdapter extends AbsBaseAdapter<HKSecutitieseBean> {

    private final FontManager mFontManager;

    public HKCapitalSecutitieseAdapter(Context context) {
        super(context, R.layout.item_hk_secutitiese);

        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(AbsBaseAdapter.ViewHolder holder, HKSecutitieseBean bean) {
        TextView mTvStockName = holder.getComponentById(R.id.tv_stock_name);
        mTvStockName.setText(bean.getStock_name());

        TextView mTvStockCode = holder.getComponentById(R.id.tv_stock_code);
        mTvStockCode.setText(bean.getStock_code());

        TextView mTvZstrdstatusName = holder.getComponentById(R.id.tv_zstrdstatusName);
        mTvZstrdstatusName.setText(bean.getZstrdstatusName());

        TextView mTvLgtrdstatusName = holder.getComponentById(R.id.tv_lgtrdstatusName);
        mTvLgtrdstatusName.setText(bean.getLgtrdstatusName());

        TextView mTvUpdateDate = holder.getComponentById(R.id.tv_update_date);
        mTvUpdateDate.setText(bean.getUpdate_date());

        // 给Textview设置字体
        mFontManager.setTextFont(mTvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvZstrdstatusName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvLgtrdstatusName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvUpdateDate, FontManager.FONT_DINPRO_MEDIUM);


    }
}
