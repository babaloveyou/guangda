package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.HistoryEntrustBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 *  历史委托ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */

public class HistoryEntrustAdapter extends AbsBaseAdapter<HistoryEntrustBean> {

    private Context mContext;
    private FontManager mFontManager;

    public HistoryEntrustAdapter(Context context) {
        super(context, R.layout.item_a_history_entrust);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HistoryEntrustBean bean) {
        TextView name = (TextView) holder.getComponentById(R.id.tv_a_history_entrust_name);
        name.setText(bean.getStock_name());

        TextView date = (TextView) holder.getComponentById(R.id.tv_a_history_entrust_date);
        date.setText(bean.getEntrust_date());

        TextView time = (TextView) holder.getComponentById(R.id.tv_a_history_entrust_time);
        time.setText(bean.getEntrust_time());

        TextView entrustPrice = (TextView) holder.getComponentById(R.id.tv_a_history_entrust_price);
        entrustPrice.setText(bean.getEntrust_price());

        TextView entrustNum = (TextView) holder.getComponentById(R.id.tv_a_history_entrust_num);
        entrustNum.setText(bean.getEntrust_amount());

        TextView tradePrice = (TextView) holder.getComponentById(R.id.tv_a_history_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeNum = (TextView) holder.getComponentById(R.id.tv_a_history_trade_num);
        tradeNum.setText(bean.getBusiness_amount());

        TextView status = (TextView) holder.getComponentById(R.id.tv_a_history_entrust_status);
        status.setText(bean.getEntrust_state_name());

        TextView entrustBs = (TextView) holder.getComponentById(R.id.tv_a_history_entrust_bs);
        entrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(date, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(time, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(status, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
    }
}
