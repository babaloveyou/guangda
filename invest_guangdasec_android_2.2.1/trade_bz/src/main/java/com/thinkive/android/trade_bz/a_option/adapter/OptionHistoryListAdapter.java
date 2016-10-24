package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionHistoryListBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 个股期权历史对账单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */

public class OptionHistoryListAdapter extends AbsBaseAdapter<OptionHistoryListBean> {

    private Context mContext;
    private FontManager mFontManager;
    private TextView tvOptionName;
    private TextView tvOptionCode;
    private TextView tvEntrustStateName;
    private TextView tvOptionTypeName;
    private TextView tvOptholdTypeName;
    private TextView tvOptEntrustPrice;
    private TextView tvEntrustAmount;
    private TextView tvOptBusinessPrice;
    private TextView tvBusinessAmount;
    private TextView tvEntrustTime;

    public OptionHistoryListAdapter(Context context) {
        super(context, R.layout.item_option_history_list);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, OptionHistoryListBean bean) {
        tvOptionName = (TextView)  holder.getComponentById(R.id.tv_option_name);
        tvOptionCode = (TextView)  holder.getComponentById(R.id.tv_option_code);
        tvEntrustStateName = (TextView)  holder.getComponentById(R.id.tv_entrust_state_name);
        tvOptionTypeName = (TextView)  holder.getComponentById(R.id.tv_option_type_name);
        tvOptholdTypeName = (TextView)  holder.getComponentById(R.id.tv_opthold_type_name);
        tvOptEntrustPrice = (TextView)  holder.getComponentById(R.id.tv_opt_entrust_price);
        tvEntrustAmount = (TextView)  holder.getComponentById(R.id.tv_entrust_amount);
        tvOptBusinessPrice = (TextView)  holder.getComponentById(R.id.tv_opt_business_price);
        tvBusinessAmount = (TextView)  holder.getComponentById(R.id.tv_business_amount);
        tvEntrustTime = (TextView)  holder.getComponentById(R.id.tv_entrust_time);

//        tvOptionName.setText(bean.getOption_name());
//        tvOptionCode.setText(bean.getOption_code());
//        tvEntrustStateName.setText(bean.getEntrust_state_name());
//        //todo：缺字段
//        tvOptionTypeName.setText("缺字段");
//        tvOptholdTypeName.setText("缺字段");
//        tvOptEntrustPrice.setText(bean.getOpt_entrust_price());
//        tvEntrustAmount.setText(bean.getEntrust_amount());
//        tvOptBusinessPrice.setText(bean.getOpt_business_price());
//        tvBusinessAmount.setText(bean.getBusiness_amount());
//        tvEntrustTime.setText(bean.getEntrust_time());

        mFontManager.setTextFont(tvOptionName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustStateName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptholdTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptEntrustPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptBusinessPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBusinessAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustTime, FontManager.FONT_DINPRO_MEDIUM);
    }
}
