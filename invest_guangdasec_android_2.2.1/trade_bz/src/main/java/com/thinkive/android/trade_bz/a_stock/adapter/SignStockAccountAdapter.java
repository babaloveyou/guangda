package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.SignAgreementBean;

import java.util.ArrayList;

/**
 *  退市板块协议签署
 *  选择股东账号
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */
public class SignStockAccountAdapter extends AbsBaseAdapter<SignAgreementBean> {

    private Context mContext;

    public SignStockAccountAdapter(Context context) {
        super(context, R.layout.item_pop_for_list_fund);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, SignAgreementBean bean) {
        TextView tvName=(TextView) holder.getComponentById(R.id.tv_pop_fund_code);
        tvName.setText(bean.getStock_account());

        ((TextView) holder.getComponentById(R.id.tv_pop_fund_name)).setText(bean.getExchange_type_name());
        // item设置不同的颜色
        ArrayList<SignAgreementBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
