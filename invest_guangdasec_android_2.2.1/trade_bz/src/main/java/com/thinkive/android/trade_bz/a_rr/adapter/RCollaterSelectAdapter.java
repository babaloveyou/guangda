package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterSelectBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 *  融资融券--划转--担保品划转查询
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */

public class RCollaterSelectAdapter extends AbsBaseAdapter<RCollaterSelectBean> {
    private Context mSubContext;
    private FontManager mFontManager;
    private TextView tvEntrustName;
    private TextView tvStockName;
    private TextView tvStockCode;
    private TextView tvEntrustStateName;
    private TextView tvEntrustPrice;
    private TextView tvBusinessPrice;
    private TextView tvEntrustAmount;
    private TextView tvBusinessAmount;
    private TextView tvEntrustDate;

    public RCollaterSelectAdapter(Context context) {
        super(context, R.layout.item_r_rollater_select);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }
    
    @Override
    protected void onFillComponentData(ViewHolder holder, final RCollaterSelectBean bean) {
        tvEntrustName = (TextView) holder.getComponentById(R.id.tv_entrust_name);
        tvEntrustName.setText(bean.getEntrust_type_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            tvEntrustName.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            tvEntrustName.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            tvEntrustName.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        tvStockName = (TextView) holder.getComponentById(R.id.tv_stock_name);
        tvStockName.setText(bean.getStock_name());

        tvStockCode = (TextView) holder.getComponentById(R.id.tv_stock_code);
        tvStockCode.setText(bean.getStock_code());

        tvEntrustStateName = (TextView) holder.getComponentById(R.id.tv_entrust_state_name);
        tvEntrustStateName.setText(bean.getEntrust_state_name());

        tvEntrustPrice = (TextView) holder.getComponentById(R.id.tv_entrust_price);
        tvEntrustPrice.setText(bean.getEntrust_price());

        tvBusinessPrice = (TextView) holder.getComponentById(R.id.tv_business_price);
        tvBusinessPrice.setText(bean.getBusiness_price());

        tvEntrustAmount = (TextView) holder.getComponentById(R.id.tv_entrust_amount);
        tvEntrustAmount.setText(bean.getEntrust_amount());

        tvBusinessAmount = (TextView) holder.getComponentById(R.id.tv_business_amount);
        tvBusinessAmount.setText(bean.getBusiness_amount());

        tvEntrustDate = (TextView) holder.getComponentById(R.id.tv_entrust_date);
        tvEntrustDate.setText(bean.getEntrust_date() + " " +bean.getEntrust_time());

        mFontManager.setTextFont(tvEntrustName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustStateName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBusinessPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBusinessAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustDate, FontManager.FONT_DINPRO_MEDIUM);
    }
}
