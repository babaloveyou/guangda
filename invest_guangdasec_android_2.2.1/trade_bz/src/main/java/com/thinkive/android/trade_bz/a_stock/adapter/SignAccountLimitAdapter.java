package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;

import java.util.ArrayList;

/**
 *  退市板块协议签署
 *  获得客户证券账户权限查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/3
 */
public class SignAccountLimitAdapter extends AbsBaseAdapter<SignStockAccountLimitBean> {

    private Context mContext;

    public SignAccountLimitAdapter(Context context) {
        super(context, R.layout.item_pop_for_list_fund);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, SignStockAccountLimitBean bean) {
        TextView tvName=(TextView) holder.getComponentById(R.id.tv_pop_fund_code);
        tvName.setText(bean.getBusiness_type_name());

//        ((TextView) holder.getComponentById(R.id.tv_pop_fund_name)).setText(bean.getExchange_type_name());
        // item设置不同的颜色
        ArrayList<SignStockAccountLimitBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
