package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 *  融资融券--查询--标的证券--融资标的（303005）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/20
 */

public class RSelectObjectCapitalAdapter extends AbsBaseAdapter<RSelectCollaterSecurityBean> {
    private Context mContext;
    private FontManager mFontManager;

    public RSelectObjectCapitalAdapter(Context context) {
        super(context, R.layout.item_r_select_object_capital);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectCollaterSecurityBean bean) {
        TextView tvCode = (TextView) holder.getComponentById(R.id.tv_collater_code);
        tvCode.setText(bean.getStock_code());

        TextView tvName = (TextView) holder.getComponentById(R.id.tv_collater_name);
        tvName.setText(bean.getStock_name());

        TextView tvMarket = (TextView) holder.getComponentById(R.id.tv_collater_market);
        tvMarket.setText(bean.getExchange_type_name());
//        if(bean.getExchange_type().equals("0")){
//            tvMarket.setText(mContext.getResources().getString(R.string.r_exchange_type));
//        }else{
//            tvMarket.setText(bean.getExchange_type());
//        }

        TextView tvProfit = (TextView) holder.getComponentById(R.id.tv_collater_profit);
        tvProfit.setText(bean.getBail_ratio());

        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvMarket, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvProfit, FontManager.FONT_DINPRO_MEDIUM);
    }
}
