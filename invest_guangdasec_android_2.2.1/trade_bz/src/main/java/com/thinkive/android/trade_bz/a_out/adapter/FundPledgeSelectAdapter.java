package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundPledgeSelectBean;
import com.thinkive.android.trade_bz.a_out.bll.FundPledgeSelectServiceImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 *   场外基金定投查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27
 */

public class FundPledgeSelectAdapter extends AbsBaseAdapter<FundPledgeSelectBean> {
    private Context mContext;
    private FontManager mFontManager;
    private FundPledgeSelectServiceImpl mServices;
    public FundPledgeSelectAdapter(Context context,FundPledgeSelectServiceImpl services) {
        super(context, R.layout.item_fund_pledge_select);
        mContext = context;
        mServices = services;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final FundPledgeSelectBean bean) {
        TextView fundName=(TextView)holder.getComponentById(R.id.tv_select_name);
        TextView fundCode=(TextView)holder.getComponentById(R.id.tv_select_code);
        TextView fundStatue=(TextView)holder.getComponentById(R.id.tv_select_status);
        TextView fundMoney=(TextView)holder.getComponentById(R.id.tv_direct_money);
        TextView fundTakeDate=(TextView)holder.getComponentById(R.id.tv_direct_data);
        TextView fundHaveTime=(TextView)holder.getComponentById(R.id.tv_direct_have);
        TextView fundCutDate=(TextView)holder.getComponentById(R.id.tv_direct_time);
        TextView fundRealTime=(TextView)holder.getComponentById(R.id.tv_direct_have_time);
        TextView fundClickCut=(TextView)holder.getComponentById(R.id.tv_fund_pledge_click);
        fundClickCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                mServices.requestCutPledge(bean);
            }
        });
        fundName.setText(bean.getFund_name());
        fundCode.setText(bean.getFund_code());
        fundStatue.setText(bean.getProtocol_status_name());
        fundMoney.setText(bean.getBalance());
        fundTakeDate.setText(bean.getInit_date());
        fundHaveTime.setText(bean.getStart_date());
        fundCutDate.setText(bean.getEnd_date());
        //TODO:缺字段
        fundRealTime.setText("");

        mFontManager.setTextFont(fundName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundStatue, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundMoney, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundTakeDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundHaveTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundCutDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundRealTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(fundClickCut, FontManager.FONT_DINPRO_MEDIUM);
    }
}
