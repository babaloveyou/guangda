package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import java.util.ArrayList;

/**
 * 融资融券--查询--担保品证券查询（303002）
 *  融资融券--查询--标的证券--融资标的（303005）
 *  融资融券--查询--标的证券--融券标的（303006）
 * 弹出Popupwindow 数据列表的适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectPopListViewAdapter extends AbsBaseAdapter<RSelectCollaterSecurityBean> {
   private Context mContext;
    public RSelectPopListViewAdapter(Context context) {
    super(context, R.layout.item_pop_for_list_fund);
        mContext=context;
}

    @Override
    protected void onFillComponentData(ViewHolder holder, RSelectCollaterSecurityBean bean) {
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_code)).setText(bean.getStock_code());
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_name)).setText(bean.getStock_name());
        // item设置不同的颜色
        ArrayList<RSelectCollaterSecurityBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
