package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.BankTransferSelectBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.views.MarqueeTextView;

/**
 *  银证转账--流水--转账查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/10
 */

public class TransferSelectAdapter extends AbsBaseAdapter<BankTransferSelectBean> {

    private Context mContext;
    private FontManager mFontManager;

    public TransferSelectAdapter(Context context) {
        super(context, R.layout.item_a_bank_select);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, BankTransferSelectBean bean) {
        TextView tvDirection=(TextView) holder.getComponentById(R.id.tv_bank_direction);
        TextView tvAccount=(TextView) holder.getComponentById(R.id.tv_bank_account);
        TextView tvCount=(TextView) holder.getComponentById(R.id.tv_bank_count);
        TextView tvTime=(TextView) holder.getComponentById(R.id.tv_bank_time);
        MarqueeTextView tvStatus=(MarqueeTextView) holder.getComponentById(R.id.tv_bank_status);

        tvDirection.setText(bean.getTransfer_direction_name());
        tvAccount.setText(mContext.getResources().getString(R.string.bracket_left)+bean.getFund_account()+mContext.getResources().getString(R.string.bracket_right));
        tvTime.setText(bean.getBusiness_date() +" "+ bean.getBusiness_time());
        tvStatus.setText(bean.getRemark());

        if(bean.getTransfer_direction().equals("0")){  //转入
            tvCount.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
            tvCount.setText("+"+bean.getTranamt());
        }else if(bean.getTransfer_direction().equals("1")){ // 转出
            tvCount.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
            tvCount.setText(bean.getTranamt());
        }else{
            tvCount.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
            tvCount.setText(bean.getTranamt());
        }
        mFontManager.setTextFont(tvDirection, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvAccount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCount, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(tvTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStatus, FontManager.FONT_DINPRO_MEDIUM);
    }
}
