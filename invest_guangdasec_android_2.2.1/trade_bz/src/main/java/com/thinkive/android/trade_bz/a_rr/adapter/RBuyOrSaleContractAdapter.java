package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 *  融券卖出--融券合约
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/19
 */

public class RBuyOrSaleContractAdapter extends AbsBaseAdapter<RChooseContractBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RBuyOrSaleContractAdapter(Context context) {
        super(context, R.layout.item_r_buy_or_sale_contract);
        mSubContext = context;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RChooseContractBean bean) {
        TextView tvCode = (TextView) holder.getComponentById(R.id.tv_collater_code);
        tvCode.setText(bean.getStock_code());

        TextView tvName = (TextView) holder.getComponentById(R.id.tv_collater_name);
        tvName.setText(bean.getStock_name());

        TextView tvCompact = (TextView) holder.getComponentById(R.id.tv_collater_num);
        tvCompact.setText(bean.getCompact_id());

        TextView openHold = (TextView) holder.getComponentById(R.id.tv_collater_hold);
        openHold.setText(bean.getBegin_compact_balance()); //开仓金额

        TextView tvNotReturn = (TextView) holder.getComponentById(R.id.tv_collater_not);
        tvNotReturn.setText(bean.getReal_compact_balance()); // 未还金额

        TextView tvOpenDate = (TextView) holder.getComponentById(R.id.tv_collater_date);
        tvOpenDate.setText(bean.getOpen_date());

        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCompact, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(openHold, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvNotReturn, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOpenDate, FontManager.FONT_DINPRO_MEDIUM);

    }
}
