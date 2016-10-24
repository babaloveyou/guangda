package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.TodayMoneyBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 * 今日资金流水ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */

public class TodayMoneyAdapter extends AbsBaseAdapter<TodayMoneyBean> {

    private FontManager mFontManager;

    public TodayMoneyAdapter(Context context) {
        super(context,R.layout.item_a_money_water);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, TodayMoneyBean bean) {
        TextView entrustBs = (TextView) holder.getComponentById(R.id.tv_a_water_bs);
        TextView entrustTime = (TextView) holder.getComponentById(R.id.tv_a_water_date);
        TextView entrustNo = (TextView) holder.getComponentById(R.id.tv_a_water_entrust_no);
        TextView occurMoney = (TextView) holder.getComponentById(R.id.tv_a_water_occur_money);
        TextView houMoney = (TextView) holder.getComponentById(R.id.tv_a_water_hou_money);
        TextView tradePrice = (TextView) holder.getComponentById(R.id.tv_a_water_trade_price);
        TextView tradeNum = (TextView) holder.getComponentById(R.id.tv_a_water_trade_num);
        TextView entrustPrice = (TextView) holder.getComponentById(R.id.tv_a_water_entrust_price);
        TextView entrustNum = (TextView) holder.getComponentById(R.id.tv_a_water_entrust_num);
        TextView moneyType = (TextView) holder.getComponentById(R.id.tv_a_water_money_type);
        TextView remark = (TextView) holder.getComponentById(R.id.tv_a_water_remark);

        entrustBs.setText(bean.getEntrust_bs_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }
        entrustTime.setText(bean.getBusiness_date());
        entrustNo.setText(bean.getEntrust_no());
        houMoney.setText(bean.getEnable_balance());
        tradePrice.setText(bean.getBusiness_price());
        tradeNum.setText(bean.getOrderqty());
        occurMoney.setText(bean.getOccur_balance());
        //todo:缺字段
        entrustPrice.setText("????");
        entrustNum.setText(bean.getOccur_amount());
        moneyType.setText(bean.getMoney_type_name());
        remark.setText(bean.getRemark());

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNo, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(houMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(occurMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(moneyType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(remark, FontManager.FONT_DINPRO_MEDIUM);
    }
}
