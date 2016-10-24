package com.thinkive.android.trade_bz.a_new.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewStockBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import java.util.ArrayList;

/**
 * 股票搜索列表的试图适配器
 * 列表项布局：item_a_search_popxml
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/22
 */
public class NewStockInPopAdapter extends AbsBaseAdapter<NewStockBean> {

    private Context mContext;

    public NewStockInPopAdapter(Context context) {
        super(context, R.layout.item_pop_for_list_fund);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, NewStockBean bean) {
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_code)).setText(bean.getStock_code());
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_name)).setText(bean.getStock_name());
        // 下面的代码是为了实现奇数行和偶数行颜色不同。
        ArrayList<NewStockBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
