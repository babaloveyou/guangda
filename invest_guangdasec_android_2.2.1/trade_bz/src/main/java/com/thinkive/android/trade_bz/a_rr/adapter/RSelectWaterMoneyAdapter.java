package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryWaterMoneyBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 融资融券--查询--历史资金流水（303043）
 * 历史资金流失（对账单）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectWaterMoneyAdapter extends AbsBaseAdapter<RSelectHistoryWaterMoneyBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RSelectWaterMoneyAdapter(Context context) {
        super(context, R.layout.item_r_select_water_money);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectHistoryWaterMoneyBean bean) {
        TextView tvBs = (TextView) holder.getComponentById(R.id.tv_r_water_bs);
        tvBs.setText(bean.getBusiness_name());

        TextView tvData = (TextView) holder.getComponentById(R.id.tv_r_water_date);
        tvData.setText(bean.getInit_date()+" "+bean.getInit_time());

        TextView entrustNo = (TextView) holder.getComponentById(R.id.tv_r_water_entrust_no);
        entrustNo.setText(bean.getEntrust_no());

        TextView tvBalances = (TextView) holder.getComponentById(R.id.tv_r_water_balance);
        tvBalances.setText(bean.getOccur_balance());

        TextView tvNum = (TextView) holder.getComponentById(R.id.tv_r_water_num);
        tvNum.setText(bean.getOccur_amount());

        TextView tvPrice = (TextView) holder.getComponentById(R.id.tv_r_business_price);
        tvPrice.setText(bean.getBusiness_price());

        TextView postMoney = (TextView) holder.getComponentById(R.id.tv_r_water_post_money);
        postMoney.setText(bean.getPost_balance());

        TextView tvMoneyType = (TextView) holder.getComponentById(R.id.tv_r_money_type_name);
        tvMoneyType.setText(bean.getMoney_type_name());

        mFontManager.setTextFont(tvBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvData, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNo, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(postMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBalances, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvMoneyType, FontManager.FONT_DINPRO_MEDIUM);
    }
}
