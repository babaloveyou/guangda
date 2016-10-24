package com.thinkive.android.trade_bz.a_trans.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 * 转股交易历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */

public class TransHistoryEntrustAdapter extends AbsBaseAdapter<TransSelectBean> {

    private Context mContext;
    private FontManager mFontManager;

    public TransHistoryEntrustAdapter(Context context) {
        super(context, R.layout.item_trans_history_entrust);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, TransSelectBean bean) {
        LinearLayout linOne= holder.getComponentById(R.id.lin_trans_line_one);
        LinearLayout linTwo= holder.getComponentById(R.id.lin_trans_line_two);

        TextView entrustBs= holder.getComponentById(R.id.tv_trans_history_entrust_bs);
        entrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
        }else if(bean.getEntrust_bs().equals("1")){ //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView date= holder.getComponentById(R.id.tv_trans_history_entrust_date);
        date.setText(bean.getEntrust_date()+ "  "+bean.getEntrust_time());

        TextView status = holder.getComponentById(R.id.tv_trans_history_entrust_status);
        status.setText(bean.getEntrust_state_name());

        TextView name= holder.getComponentById(R.id.tv_trans_history_entrust_name);
        name.setText(bean.getStock_name());

        TextView code= holder.getComponentById(R.id.tv_trans_history_entrust_code);
        code.setText(bean.getStock_code());

        TextView entrustPrice= holder.getComponentById(R.id.tv_trans_history_entrust_entrust_price);
        entrustPrice.setText(bean.getEntrust_price());

        TextView entrustNum= holder.getComponentById(R.id.tv_trans_history_entrust_entrust_num);
        entrustNum.setText(bean.getEntrust_amount());

        TextView tradePrice= holder.getComponentById(R.id.tv_trans_history_entrust_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeNum= holder.getComponentById(R.id.tv_trans_history_entrust_trade_num);
        tradeNum.setText(bean.getBusiness_amount());

        TextView tradeStatus= holder.getComponentById(R.id.tv_trans_history_entrust_trade_status);
        tradeStatus.setText(bean.getEntrust_state_name());

        TextView yueNum= holder.getComponentById(R.id.tv_trans_history_entrust_yue_num);
        yueNum.setText("????");

        TextView outXiNUm= holder.getComponentById(R.id.tv_trans_history_entrust_out_xi);
        outXiNUm.setText("????");

        TextView outAccount= holder.getComponentById(R.id.tv_trans_history_entrust_out_account);
        outAccount.setText("????");

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(date, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeStatus, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yueNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(outXiNUm, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(outAccount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(status, FontManager.FONT_DINPRO_MEDIUM);
    }
}
