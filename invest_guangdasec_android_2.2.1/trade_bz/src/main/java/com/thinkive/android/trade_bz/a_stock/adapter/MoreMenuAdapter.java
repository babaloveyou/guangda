package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.TradeFastItemBean;

/**
 * Created by Administrator on 2016/10/10.
 */
public class MoreMenuAdapter extends AbsBaseAdapter<TradeFastItemBean> {

    public MoreMenuAdapter(Context context) {
        super(context, R.layout.item_more_menu);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, TradeFastItemBean bean) {
        TextView tvMenuItem = holder.getComponentById(R.id.tv_more_menu_item);
        tvMenuItem.setText(bean.getItemName());
    }
}
