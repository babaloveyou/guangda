package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.RevocationServicesImpl;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.dialog.ARevocationConfirmDialog;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 撤单ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/16
 */

public class RevocationAdapter extends AbsBaseAdapter<RevocationBean> {
    private Context mSubContext;
    private RevocationServicesImpl mRevocationServices;
    private FontManager mFontManager;

    public RevocationAdapter(Context context, RevocationServicesImpl mServices) {
        super(context, R.layout.item_a_revocation);
        mSubContext = context;
        mRevocationServices = mServices;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RevocationBean bean) {
        TextView entrustBs=(TextView) holder.getComponentById(R.id.tv_a_revocation_bs);
        entrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }
        TextView name=(TextView) holder.getComponentById(R.id.tv_a_revocation_name);
        name.setText(bean.getStock_name());

        TextView code=(TextView) holder.getComponentById(R.id.tv_a_revocation_code);
        code.setText(bean.getStock_code());

        TextView time=(TextView) holder.getComponentById(R.id.tv_a_revocation_time);
        time.setText(bean.getEntrust_date() + " " + bean.getEntrust_time());

        TextView entrustPrice=(TextView) holder.getComponentById(R.id.tv_a_revocation_entrust_price);
        entrustPrice.setText(bean.getEntrust_price());

        TextView entrustNum=(TextView) holder.getComponentById(R.id.tv_a_revocation_entrust_num);
        entrustNum.setText(bean.getEntrust_amount());

        TextView tradePrice=(TextView) holder.getComponentById(R.id.tv_a_revocation_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeNum=(TextView) holder.getComponentById(R.id.tv_a_revocation_trade_num);
        tradeNum.setText(bean.getBusiness_amount());

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(time, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);

        final LinearLayout lClick=(LinearLayout) holder.getComponentById(R.id.lin_a_revocation);
        //点击列表项弹出对话框
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                ARevocationConfirmDialog dialog = new ARevocationConfirmDialog(mSubContext, mRevocationServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
