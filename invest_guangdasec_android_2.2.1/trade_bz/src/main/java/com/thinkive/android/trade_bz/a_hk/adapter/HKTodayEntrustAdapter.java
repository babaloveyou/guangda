package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKTodayEntrustBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 * 港股通 今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */

public class HKTodayEntrustAdapter extends AbsBaseAdapter<HKTodayEntrustBean> {
    private Context mContext;
    private FontManager mFontManager;
    public HKTodayEntrustAdapter(Context context) {
        super(context, R.layout.item_hk_today_entrust);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }
    @Override
    protected void onFillComponentData(AbsBaseAdapter.ViewHolder holder, HKTodayEntrustBean bean) {
        TextView entrustBs = holder.getComponentById(R.id.tv_select_hk_bs);
        entrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView entrustTime = holder.getComponentById(R.id.tv_select_time);
        entrustTime.setText(bean.getEntrust_time());

        TextView entrustPrice = holder.getComponentById(R.id.tv_select_entrust_price);
        entrustPrice.setText(bean.getEntrust_price());

        TextView entrustCount = holder.getComponentById(R.id.tv_select_entrust_number);
        entrustCount.setText(bean.getEntrust_amount());

        TextView tradePrice = holder.getComponentById(R.id.tv_select_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeCount = holder.getComponentById(R.id.tv_select_trade_number);
        tradeCount.setText(bean.getBusiness_amount());

        TextView securitName = holder.getComponentById(R.id.tv_select_securit_name);
        securitName.setText(bean.getStock_name());

        TextView status = holder.getComponentById(R.id.tv_select_trade_status);
        status.setText(bean.getEntrust_state_name());

        mFontManager.setTextFont(entrustTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(securitName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(status, FontManager.FONT_DINPRO_MEDIUM);
    }
}
