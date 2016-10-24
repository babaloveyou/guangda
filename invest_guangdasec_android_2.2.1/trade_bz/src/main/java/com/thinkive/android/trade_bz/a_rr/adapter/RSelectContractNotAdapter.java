package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;


/**
 * 融资融券--查询--合约查询（303035）
 *    未偿还合约
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectContractNotAdapter extends AbsBaseAdapter<RSelectContractBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RSelectContractNotAdapter(Context context) {
        super(context, R.layout.item_r_select_contract_not);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectContractBean bean) {
        TextView name = (TextView) holder.getComponentById(R.id.tv_r_not_name);
        name.setText(bean.getStock_name());

        TextView code = (TextView) holder.getComponentById(R.id.tv_r_not_code);
        code.setText(bean.getStock_code());

        TextView date = (TextView) holder.getComponentById(R.id.tv_r_not_date);
        date.setText(bean.getopen_date());

        TextView entrustBs = (TextView) holder.getComponentById(R.id.tv_r_not_bs);
        entrustBs.setText(bean.getType_name());

        TextView status = (TextView) holder.getComponentById(R.id.tv_r_not_status);
        status.setText(bean.getStatus_name());

        TextView tradeNum = (TextView) holder.getComponentById(R.id.tv_r_not_trade_num);
        tradeNum.setText(bean.getbusiness_amount());

        TextView tradeBalances = (TextView) holder.getComponentById(R.id.tv_r_not_trade_balances);
        tradeBalances.setText(bean.getbusiness_money());

        TextView rMoney = (TextView) holder.getComponentById(R.id.tv_r_not_r_money);
        rMoney.setText(bean.getbusiness_price());

        TextView compactYk = (TextView) holder.getComponentById(R.id.tv_r_not_yk);
        compactYk.setText(bean.getcontractProfit());

        TextView notMoney = (TextView) holder.getComponentById(R.id.tv_r_not_money);
        notMoney.setText(bean.getReal_compact_balance());

        TextView notBen = (TextView) holder.getComponentById(R.id.tv_r_not_cash);
        notBen.setText(bean.getfundremain());

        TextView yesLi = (TextView) holder.getComponentById(R.id.tv_r_not_yes_li);
        yesLi.setText(bean.getRepaid_interest());

        TextView notLi = (TextView) holder.getComponentById(R.id.tv_r_not_not_li);
        notLi.setText(bean.getReal_compact_interest());

        TextView yesFa = (TextView) holder.getComponentById(R.id.tv_r_not_yes_fa);
        yesFa.setText(bean.getpunifee_repay());

        TextView notFa = (TextView) holder.getComponentById(R.id.tv_r_not_not_fa);
        notFa.setText(bean.getpunidebts());

        TextView cutDate = (TextView) holder.getComponentById(R.id.tv_r_not_cut_date);
        cutDate.setText(bean.getend_date());

        TextView compactId = (TextView) holder.getComponentById(R.id.tv_r_not_id);
        compactId.setText(bean.getsno());

        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(date, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(status, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeBalances, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(rMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(compactYk, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(notMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(notBen, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yesLi, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(notLi, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yesFa, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(notFa, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(cutDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(compactId, FontManager.FONT_DINPRO_MEDIUM);
    }
}
