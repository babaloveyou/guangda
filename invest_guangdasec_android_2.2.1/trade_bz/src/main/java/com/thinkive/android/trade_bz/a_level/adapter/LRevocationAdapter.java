package com.thinkive.android.trade_bz.a_level.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_level.bean.LFundRevocationBean;
import com.thinkive.android.trade_bz.a_level.bll.LFundRevocationServicesImpl;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.LFundRevocationDialog;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 分级基金 撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LRevocationAdapter extends AbsBaseAdapter<LFundRevocationBean> {
    private Context mContext;
    private FontManager mFontManager;
    private LFundRevocationServicesImpl mServices;
    public LRevocationAdapter(Context context, LFundRevocationServicesImpl services) {
        super(context, R.layout.item_level_fund_revocation);
        mContext = context;
        mServices=services;
        mFontManager = FontManager.getInstance(mContext);
    }
    @Override
    protected void onFillComponentData(ViewHolder holder,final LFundRevocationBean bean) {
        TextView sercurityNum=(TextView) holder.getComponentById(R.id.tv_security_number);
        sercurityNum.setText(bean.getStock_code());

        TextView sercurityTime=(TextView) holder.getComponentById(R.id.tv_revocation_time);
        sercurityTime.setText(bean.getEntrust_time());

        TextView entrustValue=(TextView) holder.getComponentById(R.id.tv_entrust_value);
        entrustValue.setText(bean.getEntrust_price());

        TextView tradeValue=(TextView) holder.getComponentById(R.id.tv_trade_value);
        tradeValue.setText(bean.getBusiness_price());

        TextView entrustCount=(TextView) holder.getComponentById(R.id.tv_entrust_count);
        entrustCount.setText(bean.getEntrust_amount());

        TextView tradeCount=(TextView) holder.getComponentById(R.id.tv_trade_count);
        tradeCount.setText(bean.getBusiness_amount());

        TextView sercurityName=(TextView) holder.getComponentById(R.id.tv_security_name);
        sercurityName.setText(bean.getStock_name());

        mFontManager.setTextFont(sercurityNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(sercurityTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustValue, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeValue, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(sercurityName, FontManager.FONT_DINPRO_MEDIUM);

        LinearLayout revocationTv=(LinearLayout) holder.getComponentById(R.id.lin_level_revocation);
        revocationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                LFundRevocationDialog dialog = new LFundRevocationDialog(mContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
