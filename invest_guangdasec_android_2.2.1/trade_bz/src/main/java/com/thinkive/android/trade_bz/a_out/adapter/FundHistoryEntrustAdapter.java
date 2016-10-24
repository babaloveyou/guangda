package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundHistoryEntrustBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 *  基金交易--查询--历史委托ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundHistoryEntrustAdapter extends AbsBaseAdapter<FundHistoryEntrustBean> {

    private Context mContext;
    private FontManager mFontManager;

    public FundHistoryEntrustAdapter(Context context) {
        super(context, R.layout.item_fund_history_entrust);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, FundHistoryEntrustBean bean) {
        TextView entrustBs=(TextView) holder.getComponentById(R.id.tv_fund_select_bs);
        entrustBs.setText(bean.getBusiness_name());
        //申购或者认购
        if(bean.getBusiness_flag().equals("17") || bean.getBusiness_flag().equals("20")){
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getBusiness_flag().equals("21")){ //赎回
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView name=(TextView) holder.getComponentById(R.id.tv_fund_select_name);
        name.setText(bean.getFund_name());

        TextView code=(TextView) holder.getComponentById(R.id.tv_fund_select_code);
        code.setText(bean.getFund_code());

        TextView status=(TextView) holder.getComponentById(R.id.tv_fund_select_status);
        status.setText(bean.getEntrust_status_mame());

        TextView profit=(TextView) holder.getComponentById(R.id.tv_fund_select_profit);
        profit.setText(bean.getNav());

        TextView balances=(TextView) holder.getComponentById(R.id.tv_fund_select_balance);
        balances.setText(bean.getBalance());

        TextView time=(TextView) holder.getComponentById(R.id.tv_fund_select_time);
        time.setText(bean.getEntrust_date()+" "+bean.getEntrust_time());

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(status, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(profit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(balances, FontManager.FONT_DINPRO_MEDIUM);
    }
}
