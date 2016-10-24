package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionRevocationBean;
import com.thinkive.android.trade_bz.a_option.bll.OptionRevocationServicesImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.OptionRevocationDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 个股期权撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */

public class OptionRevocationAdapter extends AbsBaseAdapter<OptionRevocationBean> {

    private Context mContext;
    private FontManager mFontManager;
    private OptionRevocationServicesImpl mServices;

    private LinearLayout linOptionRevocation;
    private TextView tvOptionName;
    private TextView tvOptionCode;
    private TextView tvOptionTypeName;
    private TextView tvOptholdTypeName;
    private TextView tvOptEntrustPrice;
    private TextView tvEntrustAmount;
    private TextView tvOptBusinessPrice;
    private TextView tvBusinessAmount;
    private TextView tvEntrustTime;
    private TextView tvEntrustStatusName;


    public OptionRevocationAdapter(Context context,OptionRevocationServicesImpl services) {
        super(context, R.layout.item_option_revocation);
        mContext = context;
        mServices = services;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final OptionRevocationBean bean) {
        linOptionRevocation = (LinearLayout) holder.getComponentById(R.id.lin_option_revocation);
        tvOptionName = (TextView) holder.getComponentById(R.id.tv_option_name);
        tvOptionCode = (TextView) holder.getComponentById(R.id.tv_option_code);
        tvOptionTypeName = (TextView) holder.getComponentById(R.id.tv_option_type_name);
        tvOptholdTypeName = (TextView) holder.getComponentById(R.id.tv_opthold_type_name);
        tvOptEntrustPrice = (TextView) holder.getComponentById(R.id.tv_opt_entrust_price);
        tvEntrustAmount = (TextView) holder.getComponentById(R.id.tv_entrust_amount);
        tvOptBusinessPrice = (TextView) holder.getComponentById(R.id.tv_opt_business_price);
        tvBusinessAmount = (TextView) holder.getComponentById(R.id.tv_business_amount);
        tvEntrustTime = (TextView) holder.getComponentById(R.id.tv_entrust_time);
        tvEntrustStatusName = (TextView) holder.getComponentById(R.id.tv_entrust_status_name);

        tvOptionName.setText(bean.getOption_name());
        tvOptionCode.setText(bean.getOption_code());
        //todo:缺字段
        tvOptionTypeName.setText("缺字段");
        tvOptholdTypeName.setText("缺字段");
        tvOptEntrustPrice.setText(bean.getOpt_entrust_price());
        tvEntrustAmount.setText(bean.getEntrust_amount());
        tvOptBusinessPrice.setText(bean.getOpt_business_price());
        tvBusinessAmount.setText(bean.getBusiness_amount());
        tvEntrustTime.setText(bean.getEntrust_time());
        tvEntrustStatusName.setText(bean.getEntrust_status_name());

        mFontManager.setTextFont(tvOptionName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptholdTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptEntrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptBusinessPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBusinessAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustStatusName, FontManager.FONT_DINPRO_MEDIUM);

        linOptionRevocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                OptionRevocationDialog dialog = new OptionRevocationDialog(mContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
