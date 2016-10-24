package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.StatementAccountBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 *  对账单ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/24
 */
public class StatementAccountAdapter extends AbsBaseAdapter<StatementAccountBean> {

    private Context mContext;
    private FontManager mFontManager;

    public StatementAccountAdapter(Context context) {
        super(context, R.layout.item_a_statement_account);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, StatementAccountBean bean) {
        TextView entrustBs = (TextView) holder.getComponentById(R.id.tv_a_statement_bs);
        TextView entrustDate = (TextView) holder.getComponentById(R.id.tv_a_statement_date);
        TextView businessPrice = (TextView) holder.getComponentById(R.id.tv_business_price);
        TextView tradeNum = (TextView) holder.getComponentById(R.id.tv_matchqty);
        TextView tradeBalance = (TextView) holder.getComponentById(R.id.tv_matchamt);
        TextView occurBalance = (TextView) holder.getComponentById(R.id.tv_occur_balance);
        TextView stkbal = (TextView) holder.getComponentById(R.id.tv_stkbal);
        TextView fundbal = (TextView) holder.getComponentById(R.id.tv_fundbal);
        TextView moneyType = (TextView) holder.getComponentById(R.id.tv_money_type_name);
        TextView fee_ghf = (TextView) holder.getComponentById(R.id.tv_fee_ghf);
        TextView fee_sxf = (TextView) holder.getComponentById(R.id.tv_fee_sxf);

        entrustBs.setText(bean.getEntrust_name());//买卖标志
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }
        entrustDate.setText(bean.getBusiness_date());//日期
        businessPrice.setText(bean.getBusiness_price());//成交价格
        tradeNum.setText(bean.getMatchqty());//成交数量
        tradeBalance.setText(bean.getMatchamt());//成交金额
        occurBalance.setText(bean.getOccur_balance());// 发生金额
        stkbal.setText(bean.getStkbal());//股份本次余额
        fundbal.setText(bean.getFundbal());// 资金本次余额
        moneyType.setText(bean.getMoney_type_name());// 货币类型
        fee_ghf.setText(bean.getFee_ghf());//过户费
        fee_sxf.setText(bean.getFee_sxf());// 佣金

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(businessPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(occurBalance, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(stkbal, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundbal, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(moneyType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fee_ghf, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fee_sxf, FontManager.FONT_DINPRO_MEDIUM);
    }
}
