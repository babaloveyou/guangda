package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.a_rr.bll.RRevocationServiceImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.RRevocationConfirmDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 *  融资融券--下单--撤单（303017）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */

public class RRevocationAdapter extends AbsBaseAdapter<RRevocationBean> {
    private Context mSubContext;
    private FontManager mFontManager;
    private RRevocationServiceImpl mServices;

    public RRevocationAdapter(Context context, RRevocationServiceImpl services) {
        super(context, R.layout.item_r_revocation);
        mSubContext = context;
        mServices = services;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RRevocationBean bean) {
        TextView entrustBs=(TextView) holder.getComponentById(R.id.tv_r_revocation_bs);
        entrustBs.setText(bean.getEntrust_type_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }
        TextView name=(TextView) holder.getComponentById(R.id.tv_r_revocation_name);
        name.setText(bean.getStock_name());

        TextView code=(TextView) holder.getComponentById(R.id.tv_r_revocation_code);
        code.setText(bean.getStock_code());

        TextView time=(TextView) holder.getComponentById(R.id.tv_r_revocation_time);
        time.setText(bean.getEntrust_date() + " " + bean.getEntrust_time());

        TextView entrustPrice=(TextView) holder.getComponentById(R.id.tv_r_revocation_entrust_price);
        entrustPrice.setText(bean.getEntrust_price());

        TextView entrustNum=(TextView) holder.getComponentById(R.id.tv_r_revocation_entrust_num);
        entrustNum.setText(bean.getEntrust_amount());

        TextView tradePrice=(TextView) holder.getComponentById(R.id.tv_r_revocation_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeNum=(TextView) holder.getComponentById(R.id.tv_r_revocation_trade_num);
        tradeNum.setText(bean.getBusiness_amount());

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(time, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);

        //点击列表项弹出对话框
        final LinearLayout lClick=(LinearLayout) holder.getComponentById(R.id.lin_r_revocation);
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                RRevocationConfirmDialog dialog = new RRevocationConfirmDialog(mSubContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
