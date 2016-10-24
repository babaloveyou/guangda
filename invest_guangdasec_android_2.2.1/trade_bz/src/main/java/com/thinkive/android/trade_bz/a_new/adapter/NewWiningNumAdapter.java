package com.thinkive.android.trade_bz.a_new.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewNumberWinningBean;
import com.thinkive.android.trade_bz.a_new.bll.NewWinningNumServicesImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.NewStockWiningDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 新股中签istView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */

public class NewWiningNumAdapter extends AbsBaseAdapter<NewNumberWinningBean> {
    private Context mContext;
    private NewWinningNumServicesImpl mServices;
    private FontManager mFontManager;
    public NewWiningNumAdapter(Context context, NewWinningNumServicesImpl services) {
        super(context, R.layout.item_new_winning);
        mContext = context;
        mServices = services;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final NewNumberWinningBean bean) {

        TextView tvWinData = holder.getComponentById(R.id.tv_winning_data);
        TextView tvWinName = holder.getComponentById(R.id.tv_winning_securit_name);
        TextView tvWinCode = holder.getComponentById(R.id.tv_winning_securit_number);
        TextView tvWinNum = holder.getComponentById(R.id.tv_winning_number);
        TextView tvWinCount = holder.getComponentById(R.id.tv_winning_count);
        TextView tvWinPrice = holder.getComponentById(R.id.tv_winning_price);
        TextView tvWinTcount = holder.getComponentById(R.id.tv_winning_tcount);
        TextView tvWinMoney = holder.getComponentById(R.id.tv_winning_money);
        TextView tvcash = holder.getComponentById(R.id.tv_winning_cash);
        TextView tvClick = holder.getComponentById(R.id.tv_click_set);

        tvWinData.setText(bean.getBizdate());
        tvWinName.setText(bean.getStock_name());
        tvWinCode.setText(bean.getStock_code());
        tvWinNum.setText(bean.getStock_account());
        tvWinCount.setText(bean.getMatchprice());
        tvWinPrice.setText(bean.getMatchqty());
        tvWinTcount.setText(bean.getClearsno());
        tvWinMoney.setText(bean.getGiveupqty());
        tvcash.setText(bean.getJkamt());

        mFontManager.setTextFont(tvWinData, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvWinName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvWinCode, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(tvWinNum, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvWinCount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvWinPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvWinTcount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvWinMoney, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(tvcash, FontManager.FONT_DINPRO_MEDIUM);

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                NewStockWiningDialog dialog = new NewStockWiningDialog(mContext,mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
