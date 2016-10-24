package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundRevocationBean;
import com.thinkive.android.trade_bz.a_out.bll.FundRevocationServicesImpl;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.FundRevocationConfirmDialog;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 基金交易--查询--基金撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundRevocationAdapter extends AbsBaseAdapter<FundRevocationBean> {
    private Context mSubContext;
    private FontManager mFontManager;
    private FundRevocationServicesImpl mServices;

    public FundRevocationAdapter(Context context, FundRevocationServicesImpl services) {
        super(context, R.layout.item_fund_revocation);
        mSubContext=context;
        mFontManager = FontManager.getInstance(mSubContext);
        mServices = services;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final FundRevocationBean bean) {
        TextView entrustBs = (TextView) holder.getComponentById(R.id.tv_fund_revocation_bs);
        entrustBs.setText(bean.getBusiness_name());
        String businessFlag = bean.getBusiness_flag();//标志：0买1卖
        if("0".equals(businessFlag)){
            entrustBs.setTextColor(mSubContext.getResources().getColor(R.color.trade_up_red));
        }else if("1".equals(businessFlag)){
            entrustBs.setTextColor(mSubContext.getResources().getColor(R.color.trade_sale));
        }else{
            entrustBs.setTextColor(mSubContext.getResources().getColor(R.color.trade_text_color0));
        }
        TextView name = (TextView) holder.getComponentById(R.id.tv_fund_revocation_name);
        name.setText(bean.getFund_name());

        TextView code = (TextView) holder.getComponentById(R.id.tv_fund_revocation_code);
        code.setText(bean.getFund_code());

        TextView time = (TextView) holder.getComponentById(R.id.tv_fund_revocation_time);
        time.setText(bean.getEntrust_date() +" "+bean.getEntrust_time());

        TextView profit = (TextView) holder.getComponentById(R.id.tv_fund_revocation_profit);
        profit.setText(bean.getNav());
        //委托金额/份额设置
        TextView balancesTitle = holder.getComponentById(R.id.tv_fund_revocation_balances_title);
        TextView balances = (TextView) holder.getComponentById(R.id.tv_fund_revocation_balances);
        if("21".equals(businessFlag)){
            //赎回，不显示委托金额，应该显示为委托份额
            balancesTitle.setText(R.string.fund_select3);
            balances.setText(bean.getShares());
        }else{
            balancesTitle.setText(R.string.fund_select1);
            balances.setText(bean.getBalance());
        }
        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(time, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(profit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(balances, FontManager.FONT_DINPRO_MEDIUM);

        final LinearLayout lClick=(LinearLayout) holder.getComponentById(R.id.lin_lin);
        //点击列表项弹出对话框
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                FundRevocationConfirmDialog dialog = new FundRevocationConfirmDialog(mSubContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
