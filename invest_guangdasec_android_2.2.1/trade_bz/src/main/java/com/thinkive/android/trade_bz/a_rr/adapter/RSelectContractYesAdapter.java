package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;


/**
 * 融资融券--查询--合约查询（303035）
 *    已了结合约
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectContractYesAdapter extends AbsBaseAdapter<RSelectContractBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RSelectContractYesAdapter(Context context) {
        super(context, R.layout.item_r_select_contract_yes);
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

        TextView yesLi = (TextView) holder.getComponentById(R.id.tv_r_not_yes_li);
        yesLi.setText(bean.getRepaid_interest());

        TextView yesFa = (TextView) holder.getComponentById(R.id.tv_r_not_yes_fa);
        yesFa.setText(bean.getpunifee_repay());

        TextView cutDate = (TextView) holder.getComponentById(R.id.contract_not_cut_date);
        cutDate.setText(bean.getDate_clear());

        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(date, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(status, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeBalances, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(rMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yesLi, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yesFa, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(cutDate, FontManager.FONT_DINPRO_MEDIUM);
    }
}
