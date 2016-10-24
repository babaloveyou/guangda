package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayEntrustBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 * 融资融券--查询--当日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectTodayEntrustAdapter extends AbsBaseAdapter<RSelectTodayEntrustBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RSelectTodayEntrustAdapter(Context context) {
        super(context, R.layout.item_r_select_today_entrust);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectTodayEntrustBean bean) {
        TextView tvEntrustBs=(TextView) holder.getComponentById(R.id.tv_r_bs);
        tvEntrustBs.setText(bean.getEntrust_type_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            tvEntrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView tvStatus=(TextView) holder.getComponentById(R.id.tv_r_bsstatus);
        tvStatus.setText(bean.getEntrust_state_name());

        TextView tvName=(TextView) holder.getComponentById(R.id.tv_r_bsname);
        tvName.setText(bean.getStock_name());

        TextView tvCode=(TextView) holder.getComponentById(R.id.tv_r_bscode);
        tvCode.setText(bean.getStock_code());

        TextView tvPrice=(TextView) holder.getComponentById(R.id.tv_r_bsprice);
        tvPrice.setText(bean.getEntrust_price());

        TextView tvCount=(TextView) holder.getComponentById(R.id.tv_r_bsnum);
        tvCount.setText(bean.getEntrust_amount());

        TextView tvTradePrice=(TextView) holder.getComponentById(R.id.tv_r_trade_price);
        tvTradePrice.setText(bean.getBusiness_price());

        TextView tvTradeCount=(TextView) holder.getComponentById(R.id.tv_r_trade_num);
        tvTradeCount.setText(bean.getBusiness_amount());

        TextView tvTime=(TextView) holder.getComponentById(R.id.tv_r_bstime);
        tvTime.setText(bean.getEntrust_time());

        mFontManager.setTextFont(tvEntrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStatus, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTradeCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvTime, FontManager.FONT_DINPRO_MEDIUM);
    }
}
