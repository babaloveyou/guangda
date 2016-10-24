package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterRevocationBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterRevocationServiceImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.RCollaterRevocationDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 *  融资融券--划转--撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */

public class RCollaterRevocationAdapter extends AbsBaseAdapter<RCollaterRevocationBean> {
    private Context mSubContext;
    private FontManager mFontManager;
    private RCollaterRevocationServiceImpl mServices;

    public RCollaterRevocationAdapter(Context context, RCollaterRevocationServiceImpl service) {
        super(context, R.layout.item_r_rollater_revocation);
        mSubContext = context;
        mServices = service;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RCollaterRevocationBean bean) {
        TextView entrustBs = (TextView) holder.getComponentById(R.id.tv_collater_revocation_bs);
        entrustBs.setText(bean.getEntrust_type_name());
        if(bean.getEntrust_bs().equals("76")){ //买入
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sBuyTextColor));
        }else if(bean.getEntrust_bs().equals("77")) { //卖出
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sSaleTextColor));
        }else{
            entrustBs.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
        }

        TextView name = (TextView) holder.getComponentById(R.id.tv_collater_revocation_name);
        name.setText(bean.getStock_name());

        TextView code = (TextView) holder.getComponentById(R.id.tv_collater_revocation_code);
        code.setText(bean.getStock_code());

        TextView time = (TextView) holder.getComponentById(R.id.tv_collater_revocation_time);
        time.setText(bean.getEntrust_date()+" "+bean.getEntrust_time());

        TextView price = (TextView) holder.getComponentById(R.id.tv_collater_revocation_price);
        price.setText(bean.getBusiness_balance());

        TextView num = (TextView) holder.getComponentById(R.id.tv_collater_revocation_count);
        num.setText(bean.getEntrust_amount());

        mFontManager.setTextFont(entrustBs, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(time, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(price, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(num, FontManager.FONT_DINPRO_MEDIUM);

        //点击列表项弹出对话框
        final LinearLayout lClick = (LinearLayout) holder.getComponentById(R.id.lin_lin_r);
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                RCollaterRevocationDialog dialog = new RCollaterRevocationDialog(mSubContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
