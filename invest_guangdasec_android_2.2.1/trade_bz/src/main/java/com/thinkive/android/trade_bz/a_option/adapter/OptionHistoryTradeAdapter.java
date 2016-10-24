package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionHistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 个股期权历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */

public class OptionHistoryTradeAdapter extends AbsBaseAdapter<OptionHistoryTradeBean> {

    private Context mContext;
    private FontManager mFontManager;
    private TextView tvOptionName;
    private TextView tvOptionCode;
    private TextView tvEntrustStateName;
    private TextView tvOptionTypeName;
    private TextView tvOptholdTypeName;
    private TextView tvOptEntrustPrice;
    private TextView tvEntrustAmount;
    private TextView tvOptBusinessPrice;
    private TextView tvBusinessAmount;
    private TextView tvEntrustTime;

    public OptionHistoryTradeAdapter(Context context) {
        super(context, R.layout.item_option_history_trade);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }


    @Override
    protected void onFillComponentData(ViewHolder holder, OptionHistoryTradeBean bean) {
        tvOptionName = (TextView)  holder.getComponentById(R.id.tv_option_name);
        tvOptionCode = (TextView)  holder.getComponentById(R.id.tv_option_code);
        tvEntrustStateName = (TextView)  holder.getComponentById(R.id.tv_entrust_state_name);
        tvOptionTypeName = (TextView)  holder.getComponentById(R.id.tv_option_type_name);
        tvOptholdTypeName = (TextView)  holder.getComponentById(R.id.tv_opthold_type_name);
        tvOptEntrustPrice = (TextView)  holder.getComponentById(R.id.tv_opt_entrust_price);
        tvEntrustAmount = (TextView)  holder.getComponentById(R.id.tv_entrust_amount);
        tvOptBusinessPrice = (TextView)  holder.getComponentById(R.id.tv_opt_business_price);
        tvBusinessAmount = (TextView)  holder.getComponentById(R.id.tv_business_amount);
        tvEntrustTime = (TextView)  holder.getComponentById(R.id.tv_entrust_time);

        tvOptionName.setText(bean.getOption_name());
        tvOptionCode.setText(bean.getOption_code());
        tvEntrustStateName.setText(bean.getBusiness_status());
        //todo：缺字段
        tvOptionTypeName.setText("缺字段");
        tvOptholdTypeName.setText("缺字段");
        tvOptEntrustPrice.setText(bean.getOccur_balance());
        tvEntrustAmount.setText(bean.getOccur_amount());
        tvOptBusinessPrice.setText(bean.getOpt_business_price());
        tvBusinessAmount.setText("晕了");
        tvEntrustTime.setText(bean.getBusiness_time());

        mFontManager.setTextFont(tvOptionName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustStateName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptholdTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptEntrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptBusinessPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBusinessAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustTime, FontManager.FONT_DINPRO_MEDIUM);
    }
}
