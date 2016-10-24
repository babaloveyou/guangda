package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalCompanyMsgBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 港股通 公司行为信息查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class HKCapitalCompanyMsgAdapter extends AbsBaseAdapter<HKCapitalCompanyMsgBean> {

    private final FontManager mFontManager;

    public HKCapitalCompanyMsgAdapter(Context context) {
        super(context, R.layout.item_hk_company_msg);
        mFontManager = FontManager.getInstance(context);

    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalCompanyMsgBean bean) {
        TextView tvStockCode = (TextView) holder.getComponentById(R.id.tv_stock_code);
        TextView tvStockName = (TextView) holder.getComponentById(R.id.tv_stock_name);
        TextView tvInformType = (TextView) holder.getComponentById(R.id.tv_inform_type);
        TextView tvInformDay = (TextView) holder.getComponentById(R.id.tv_inform_day);
        TextView tvTradeDate = (TextView) holder.getComponentById(R.id.tv_trade_date);

        tvStockCode.setText(bean.getStock_code());
        tvStockName.setText(bean.getStock_name());
        tvInformType.setText(bean.getNotice_type());
        tvInformDay.setText(bean.getNotice_date());
        tvTradeDate.setText(bean.getTrade_date());

        mFontManager.setTextFont(tvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvInformType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvInformDay, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTradeDate, FontManager.FONT_DINPRO_MEDIUM);
    }
}
