package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

import java.util.ArrayList;

/**
 * 持仓证券选择适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/20
 */

public class RSelectPopHoldStockAdapter extends AbsBaseAdapter<MyStoreStockBean> {
   private Context mContext;
    public RSelectPopHoldStockAdapter(Context context) {
    super(context, R.layout.item_pop_for_list_fund);
        mContext=context;
}

    @Override
    protected void onFillComponentData(ViewHolder holder, MyStoreStockBean bean) {
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_code)).setText(bean.getStock_code());
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_name)).setText(bean.getStock_name());
        // item设置不同的颜色
        ArrayList<MyStoreStockBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
