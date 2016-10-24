package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;

import java.util.ArrayList;

/**
 * 股票搜索列表的试图适配器
 * 列表项布局：item_pop_search_stocks.xml.xml
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/22
 */
public class SearchStocksAdapter extends AbsBaseAdapter<CodeTableBean> {
    private Context mContext;

    public SearchStocksAdapter(Context context) {
        super(context, R.layout.item_pop_search_stocks);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, CodeTableBean bean) {
        ((TextView) holder.getComponentById(R.id.tv_item_search_stock_code)).setText(bean.getCode());
        ((TextView) holder.getComponentById(R.id.tv_item_search_stock_name)).setText(bean.getName());
        // 下面的代码是为了实现奇数行和偶数行颜色不同。
        ArrayList<CodeTableBean> dataList = getListData();
        int position = dataList.indexOf(bean);
    }
}
