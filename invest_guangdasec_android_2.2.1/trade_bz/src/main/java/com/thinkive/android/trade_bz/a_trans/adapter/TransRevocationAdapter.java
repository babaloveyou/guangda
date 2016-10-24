package com.thinkive.android.trade_bz.a_trans.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.a_trans.bll.TransRevocationServicesImpl;
import com.thinkive.android.trade_bz.dialog.TransRevocationDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 转股交易撤单列表
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/5
 */

public class TransRevocationAdapter extends AbsBaseAdapter<TransSelectBean> {
    private Context mSubContext;
    private TransRevocationServicesImpl mServices;
    private FontManager mFontManager;

    public TransRevocationAdapter(Context context, TransRevocationServicesImpl mServices) {
        super(context, R.layout.item_trans_revocation);
        mSubContext = context;
        this.mServices = mServices;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final TransSelectBean bean) {
        final LinearLayout lClick= holder.getComponentById(R.id.lin_lin_revocation);
        LinearLayout linOne= holder.getComponentById(R.id.lin_trans_line_one);
        LinearLayout linTwo= holder.getComponentById(R.id.lin_trans_line_two);

        TextView entrustBs= holder.getComponentById(R.id.tv_trans_revocation_bs);
        entrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
        }else if(bean.getEntrust_bs().equals("1")){ //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView date= holder.getComponentById(R.id.tv_trans_revocation_date);
        date.setText(bean.getEntrust_date()+ "  "+bean.getEntrust_time());

        TextView name= holder.getComponentById(R.id.tv_trans_revocation_name);
        name.setText(bean.getStock_name());

        TextView code= holder.getComponentById(R.id.tv_trans_revocation_code);
        code.setText(bean.getStock_code());

        TextView entrustPrice= holder.getComponentById(R.id.tv_trans_revocation_entrust_price);
        entrustPrice.setText(bean.getEntrust_price());

        TextView entrustNum= holder.getComponentById(R.id.tv_trans_revocation_entrust_num);
        entrustNum.setText(bean.getEntrust_amount());

        TextView tradePrice= holder.getComponentById(R.id.tv_trans_revocation_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeNum= holder.getComponentById(R.id.tv_trans_revocation_trade_num);
        tradeNum.setText(bean.getBusiness_amount());

        TextView tradeStatus= holder.getComponentById(R.id.tv_trans_revocation_trade_status);
        tradeStatus.setText(bean.getEntrust_state_name());

        TextView yueNum= holder.getComponentById(R.id.tv_trans_revocation_yue_num);
        yueNum.setText("????");

        TextView outXiNUm= holder.getComponentById(R.id.tv_trans_revocation_out_xi);
        outXiNUm.setText("????");

        TextView outAccount= holder.getComponentById(R.id.tv_trans_revocation_out_account);
        outAccount.setText("????");

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(date, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeStatus, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yueNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(outXiNUm, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(outAccount, FontManager.FONT_DINPRO_MEDIUM);

        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                TransRevocationDialog dialog = new TransRevocationDialog(mSubContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
