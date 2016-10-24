package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 *  融券卖出--可容标的
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/19
 */

public class RBuyOrSaleObjectAdapter extends AbsBaseAdapter<RSelectCollaterSecurityBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RBuyOrSaleObjectAdapter(Context context) {
        super(context, R.layout.item_r_buy_or_sale_object);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectCollaterSecurityBean bean) {
        TextView tvName = (TextView) holder.getComponentById(R.id.tv_object_name);
        tvName.setText(bean.getStock_name());

        TextView tvCode = (TextView) holder.getComponentById(R.id.tv_object_code);
        tvCode.setText(bean.getStock_code());

        TextView tvMarket = (TextView) holder.getComponentById(R.id.tv_object_per);
        tvMarket.setText(bean.getBail_ratio());

        TextView tvProfit = (TextView) holder.getComponentById(R.id.tv_object_market);
        tvProfit.setText(bean.getExchange_type_name());


        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvMarket, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvProfit, FontManager.FONT_DINPRO_MEDIUM);
    }
}
