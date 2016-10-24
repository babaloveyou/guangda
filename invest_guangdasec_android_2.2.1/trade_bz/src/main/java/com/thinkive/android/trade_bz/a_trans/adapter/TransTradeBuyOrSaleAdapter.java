package com.thinkive.android.trade_bz.a_trans.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransSubHqBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 转股交易 成交确认买入、成交确认卖出,持仓适配器
 * @author 张雪梅
 * @corporation thinkive
 * @date 2016/5/24
 */
public class TransTradeBuyOrSaleAdapter extends AbsBaseAdapter<TransSubHqBean> {

    private FontManager mFontManager;

    public TransTradeBuyOrSaleAdapter(Context context) {
        super(context, R.layout.item_trans_trade_buy_or_sale);
        mFontManager = FontManager.getInstance(context);
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, TransSubHqBean bean) {
        TextView name = holder.getComponentById(R.id.tv_trans_trade_name);
        TextView code = holder.getComponentById(R.id.tv_trans_trade_code);
        TextView entrustBs = holder.getComponentById(R.id.tv_trans_trade_bs);
        TextView count = holder.getComponentById(R.id.tv_trans_trade_count);
        TextView price = holder.getComponentById(R.id.tv_trans_trade_price);
        TextView yueNum = holder.getComponentById(R.id.tv_trans_trade_yue_num);
        TextView xiNum = holder.getComponentById(R.id.tv_trans_trade_xi_num);

        // 给Textview设置文字
        name.setText(bean.getStock_name());
        code.setText(bean.getStock_code());
        entrustBs.setText(bean.getBsflag_name());
        count.setText(bean.getOrderqty());
        price.setText(bean.getOrderprice());
        yueNum.setText(bean.getConfernum());
        xiNum.setText(bean.getSeat());

        // 给Textview设置字体
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(count, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(price, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yueNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(xiNum, FontManager.FONT_DINPRO_MEDIUM);
    }
}
