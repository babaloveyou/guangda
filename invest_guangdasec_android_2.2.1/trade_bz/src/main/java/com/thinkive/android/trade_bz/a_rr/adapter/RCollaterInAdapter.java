package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterInServiceImpl;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterInFragment;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.dialog.RCollaterInDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;

/**
 *  融资融券--划转--担保品转入
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */

public class RCollaterInAdapter extends AbsBaseAdapter<MyStoreStockBean> {
    private Context mSubContext;
    private FontManager mFontManager;
    private RCollaterInServiceImpl mServices;
    private RCollaterInFragment mFragment;

    public RCollaterInAdapter(Context context, RCollaterInServiceImpl services, RCollaterInFragment fragment) {
        super(context, R.layout.item_r_rollater_in);
        mSubContext = context;
        mServices = services;
        mFragment = fragment;
        mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final MyStoreStockBean bean) {
        TextView name = (TextView) holder.getComponentById(R.id.tv_rollater_in_name);
        name.setText(bean.getStock_name());

        TextView code = (TextView) holder.getComponentById(R.id.tv_rollater_in_code);
        code.setText(bean.getStock_code());

        TextView nowPrice = (TextView) holder.getComponentById(R.id.tv_rollater_in_now_price);
        nowPrice.setText(bean.getLast_price());

        TextView yk = (TextView) holder.getComponentById(R.id.tv_rollater_in_yk);
        yk.setText(bean.getFloat_yk());

        TextView ykPer = (TextView) holder.getComponentById(R.id.tv_rollater_in_yk_per);
        ykPer.setText(bean.getFloat_yk_per());

        TextView chenBenJia = (TextView) holder.getComponentById(R.id.tv_rollater_in_ben);
        chenBenJia.setText(bean.getCost_price());

        TextView hold = (TextView) holder.getComponentById(R.id.tv_rollater_in_hold);
        hold.setText(bean.getEnable_amount());

        TextView zheSuanLv = (TextView) holder.getComponentById(R.id.tv_rollater_in_per_price);
        zheSuanLv.setText(bean.getPledge_rate());

        TextView market = (TextView) holder.getComponentById(R.id.tv_rollater_in_market);
        market.setText(bean.getMarket_value());

        mFontManager.setTextFont(name, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(code, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(nowPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(yk, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(ykPer, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(zheSuanLv, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(market, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(chenBenJia, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(hold, FontManager.FONT_DINPRO_MEDIUM);

        String floatYk = bean.getFloat_yk();
        if(floatYk != null && !TextUtils.isEmpty(floatYk)){
            float num = Float.parseFloat(floatYk);
            if(num  > 0){
                nowPrice.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
                yk.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
                ykPer.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
            }else if(num  < 0){
                nowPrice.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                yk.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                ykPer.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
            }else if(num  == 0){
                nowPrice.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
                yk.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
                ykPer.setTextColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
            }
        }

        //点击列表项弹出对话框
        final LinearLayout lClick = (LinearLayout) holder.getComponentById(R.id.lin_lin_r);
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.requestLink(bean);
            }
        });
    }
    /**
     * 得到最大可转后，弹框框
     * @param bean
     */
    public void onGetLinkData(RCollaterLinkBean bean){
        RCollaterInDialog dialog = new RCollaterInDialog(mSubContext, mServices);
        dialog.setDataBean(bean);
        dialog.show();
    }
}
