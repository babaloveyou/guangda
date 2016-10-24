package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 * 融资融券--查询--历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectHistoryTradeAdapter extends AbsBaseAdapter<RSelectHistoryTradeBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RSelectHistoryTradeAdapter(Context context) {
        super(context, R.layout.item_r_select_history_trade);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectHistoryTradeBean bean) {
        TextView tvEntrustBs=(TextView) holder.getComponentById(R.id.tv_r_entrust_bs);
        tvEntrustBs.setText(bean.getEntrust_type_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView tvData=(TextView) holder.getComponentById(R.id.tv_r_trade_time);
        tvData.setText(bean.getBusiness_date()+" "+bean.getBusiness_time());

        TextView tvName=(TextView) holder.getComponentById(R.id.tv_r_trade_name);
        tvName.setText(bean.getStock_name());

        TextView tvCode=(TextView) holder.getComponentById(R.id.tv_r_trade_code);
        tvCode.setText(bean.getStock_code());

        TextView tvPrice=(TextView) holder.getComponentById(R.id.tv_r_trade_price);
        tvPrice.setText(bean.getBusiness_price());

        TextView tvNum=(TextView) holder.getComponentById(R.id.tv_r_trade_num);
        tvNum.setText(bean.getBusiness_amount());

        TextView tvBalance=(TextView) holder.getComponentById(R.id.tv_r_trade_balances);
        tvBalance.setText(bean.getBusiness_balance());

        mFontManager.setTextFont(tvEntrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvData, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBalance, FontManager.FONT_DINPRO_MEDIUM);
    }
}
