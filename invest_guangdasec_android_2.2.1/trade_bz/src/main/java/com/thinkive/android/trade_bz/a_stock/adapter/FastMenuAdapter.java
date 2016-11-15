package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.TradeFastItemBean;


/**
 * 快捷菜单GridView适配器
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */

public class FastMenuAdapter extends AbsBaseAdapter<TradeFastItemBean> {


    public FastMenuAdapter(Context context) {
        super(context, R.layout.item_fast_menu_trade);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, TradeFastItemBean bean) {
        TextView tvMenuItem = holder.getComponentById(R.id.tv_menu_item);
        ImageView imgview = holder.getComponentById(R.id.iv_fast_menu_icon);
        imgview.setImageResource(bean.getImageRes());
        tvMenuItem.setText(bean.getItemName());
    }
}
