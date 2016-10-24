package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionHoldStockBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 个股期权主页持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */

public class OptionHoldAdapter extends AbsBaseAdapter<OptionHoldStockBean> {

    private Context mContext;
    private FontManager mFontManager;

    public OptionHoldAdapter(Context context) {
        super(context, R.layout.item_option_hold);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }
    private TextView mTvOptionName;
    private TextView mTvOptionCode;
    private TextView mTvExchangeTypeName;
    private TextView mTvOptionTypeName;
    private TextView mTvOptholdType;
    private TextView mTvCostBalance;
    private TextView mTvLastPrice;
    private TextView mTvCurrentAmount;
    private TextView mTvEnableAmount;
    private TextView mTvIncomeBalance;
    private TextView mTvMarketValue;

    @Override
    protected void onFillComponentData(ViewHolder holder, OptionHoldStockBean bean) {
        mTvOptionName = (TextView) holder.getComponentById(R.id.tv_option_name);
        mTvOptionCode = (TextView)holder.getComponentById(R.id.tv_option_code);
        mTvExchangeTypeName = (TextView)holder.getComponentById(R.id.tv_exchange_type_name);
        mTvOptionTypeName = (TextView)holder.getComponentById(R.id.tv_option_type_name);
        mTvOptholdType = (TextView)holder.getComponentById(R.id.tv_opthold_type_name);
        mTvCostBalance = (TextView)holder.getComponentById(R.id.tv_cost_balance);
        mTvLastPrice = (TextView)holder.getComponentById(R.id.tv_last_price);
        mTvCurrentAmount = (TextView)holder.getComponentById(R.id.tv_current_amount);
        mTvEnableAmount = (TextView)holder.getComponentById(R.id.tv_enable_amount);
        mTvIncomeBalance = (TextView)holder.getComponentById(R.id.tv_income_balance);
        mTvMarketValue = (TextView)holder.getComponentById(R.id.tv_market_value);

        mTvOptionName.setText(bean.getOption_name());
        mTvOptionCode.setText(bean.getOption_code());
        mTvExchangeTypeName.setText(bean.getExchange_type_name());
        mTvOptionTypeName.setText(bean.getOption_type_name());
        mTvOptholdType.setText(bean.getOpthold_type_name());
        mTvCostBalance.setText(bean.getCost_balance());
        mTvLastPrice.setText(bean.getLast_price());
        mTvCurrentAmount.setText(bean.getCurrent_amount());
        mTvEnableAmount.setText(bean.getEnable_amount());
        mTvIncomeBalance.setText(bean.getIncome_balance());
        mTvMarketValue.setText(bean.getMarket_value());


        mFontManager.setTextFont(mTvOptionName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvOptionCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvExchangeTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvOptionTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvOptholdType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCostBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvLastPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCurrentAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvEnableAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvIncomeBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMarketValue, FontManager.FONT_DINPRO_MEDIUM);
    }
}
