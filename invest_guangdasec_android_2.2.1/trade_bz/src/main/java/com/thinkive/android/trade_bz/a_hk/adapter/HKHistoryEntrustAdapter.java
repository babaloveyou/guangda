package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKHistoryEntrustBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 *  港股通 历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */

public class HKHistoryEntrustAdapter extends AbsBaseAdapter<HKHistoryEntrustBean> {

    private Context mContext;
    private FontManager mFontManager;

    public HKHistoryEntrustAdapter(Context context) {
        super(context, R.layout.item_hk_history_entrust);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKHistoryEntrustBean bean) {
        TextView tvData= holder.getComponentById(R.id.tv_history_data);
        tvData.setText(bean.getEntrust_date());

        TextView tvTime= holder.getComponentById(R.id.tv_history_time);
        tvTime.setText(bean.getEntrust_time());

        TextView tvName= holder.getComponentById(R.id.tv_history_securit_name);
        tvName.setText(bean.getStock_name());

        TextView tvState= holder.getComponentById(R.id.tv_history_entrust_status);
        tvState.setText(bean.getEntrust_state_name());

        TextView tvePrice= holder.getComponentById(R.id.tv_history_entrust_price);
        tvePrice.setText(bean.getEntrust_price());

        TextView tvecount= holder.getComponentById(R.id.tv_history_entrust_num);
        tvecount.setText(bean.getEntrust_amount());

        TextView tvtPrice= holder.getComponentById(R.id.tv_history_trade_price);
        tvtPrice.setText(bean.getEntrust_price());

        TextView tvtCount= holder.getComponentById(R.id.tv_history_trade_number);
        tvtCount.setText(bean.getBusiness_amount());

        TextView tvEntrustBs= holder.getComponentById(R.id.tv_history_entrust_bs);
        tvEntrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        mFontManager.setTextFont(tvData, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvState, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvecount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvtPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvtCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustBs, FontManager.FONT_DINPRO_MEDIUM);
    }
}
