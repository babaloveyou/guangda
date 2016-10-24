package com.thinkive.android.trade_bz.a_new.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewStockBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 *  新股申购ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */

public class NewSubscribeAdapter extends AbsBaseAdapter<NewStockBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public NewSubscribeAdapter(Context context) {
        super(context, R.layout.item_new_subscribe);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final NewStockBean bean) {
        TextView tvName = holder.getComponentById(R.id.tv_subscribe_name);
        tvName.setText(bean.getStock_name());

        TextView tvCode = holder.getComponentById(R.id.tv_subscribe_code);
        tvCode.setText(bean.getStock_code());

        TextView tvPrice = holder.getComponentById(R.id.tv_subscribe_price);
        tvPrice.setText(bean.getIssue_price());

        TextView tvMarket = holder.getComponentById(R.id.tv_subscribe_unit);
        tvMarket.setText(bean.getApplyunitonline());

        TextView tvProfit = holder.getComponentById(R.id.tv_subscribe_uplimit);
        tvProfit.setText(bean.getApplymax_online());

        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvMarket, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvProfit, FontManager.FONT_DINPRO_MEDIUM);
    }
}
