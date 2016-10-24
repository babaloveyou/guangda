package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HkRevocationBean;
import com.thinkive.android.trade_bz.a_hk.bll.HKRevocationServicesImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.HKRevocationConfirmDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 港股通 撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */

public class HKRevocationAdapter extends AbsBaseAdapter<HkRevocationBean> {
    private Context mSubContext;
    private HKRevocationServicesImpl mRevocationServices;
    private FontManager mFontManager;

    public HKRevocationAdapter(Context context, HKRevocationServicesImpl mServices) {
        super(context, R.layout.item_hk_revocation_listview);
        mSubContext = context;
        mRevocationServices = mServices;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final HkRevocationBean bean) {
        TextView entrustBs= holder.getComponentById(R.id.tv_hk_revocation_bs);
        entrustBs.setText(bean.getEntrust_name());
        if(bean.getEntrust_bs().equals("0")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("1")){ //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView name= holder.getComponentById(R.id.tv_hk_revocation_name);
        name.setText(bean.getStock_name());

        TextView code= holder.getComponentById(R.id.tv_hk_revocation_code);
        code.setText(bean.getStock_code());

        TextView date= holder.getComponentById(R.id.tv_hk_revocation_date);
        date.setText(bean.getEntrust_date()+" "+bean.getEntrust_time());

        TextView entrustPrice= holder.getComponentById(R.id.tv_hk_revocation_entrust_price);
        entrustPrice.setText(bean.getEntrust_price());

        TextView entrustNum= holder.getComponentById(R.id.tv_hk_revocation_entrust_num);
        entrustNum.setText(bean.getEntrust_amount());

        TextView tradePrice= holder.getComponentById(R.id.tv_hk_revocation_trade_price);
        tradePrice.setText(bean.getBusiness_price());

        TextView tradeNum= holder.getComponentById(R.id.tv_hk_revocation_trade_num);
        tradeNum.setText(bean.getBusiness_amount());

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(date, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(entrustNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradePrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tradeNum, FontManager.FONT_DINPRO_MEDIUM);

        final LinearLayout lClick= holder.getComponentById(R.id.lin_lin_revocation);
       //点击列表项弹出对话框
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                HKRevocationConfirmDialog dialog = new HKRevocationConfirmDialog(mSubContext, mRevocationServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
