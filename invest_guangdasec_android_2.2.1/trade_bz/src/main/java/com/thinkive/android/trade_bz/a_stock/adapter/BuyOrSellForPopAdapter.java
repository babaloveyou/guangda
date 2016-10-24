package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.MarketEntrustWay;

import java.util.ArrayList;

/**
 * 弹出的 pop适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/6
 */

public class BuyOrSellForPopAdapter extends AbsBaseAdapter<MarketEntrustWay> {
   private Context mContext;
    public BuyOrSellForPopAdapter(Context context) {
    super(context, R.layout.item_pop_for_list_fund);
        mContext=context;
}

    @Override
    protected void onFillComponentData(ViewHolder holder, MarketEntrustWay bean) {
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_code)).setText(bean.getEntrust_way_name());
        // item设置不同的颜色
        ArrayList<MarketEntrustWay> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
