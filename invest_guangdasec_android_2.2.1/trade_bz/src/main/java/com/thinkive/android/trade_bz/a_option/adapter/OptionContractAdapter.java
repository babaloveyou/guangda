package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

import java.util.ArrayList;

/**
 * 个股期权合约查询适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/16
 */

public class OptionContractAdapter extends AbsBaseAdapter<OptionContractBean> {

    private Context mContext;
    private FontManager mFontManager;
    private TextView tvOptionName;
    private TextView tvOptionType;
    private TextView tvOptionBs;

    public OptionContractAdapter(Context context) {
        super(context, R.layout.item_option_contract);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, OptionContractBean bean) {
        tvOptionName = (TextView)  holder.getComponentById(R.id.tv_option_contract_name);
        tvOptionType = (TextView)  holder.getComponentById(R.id.tv_option_contract_type);
        tvOptionBs = (TextView)  holder.getComponentById(R.id.tv_option_contract_bs);

        tvOptionName.setText(bean.getOption_name());
        tvOptionType.setText(bean.getOption_type_name());
        tvOptionBs.setText(bean.getExchange_type_name());

        mFontManager.setTextFont(tvOptionName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOptionBs, FontManager.FONT_DINPRO_MEDIUM);

        // item设置不同的颜色
        ArrayList<OptionContractBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
