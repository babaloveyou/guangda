package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import java.util.ArrayList;

/**
 * 基金转托弹出的 pop适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/6
 */

public class FundHoldForPopAdapter extends AbsBaseAdapter<FundHoldBean> {
   private Context mContext;
    public FundHoldForPopAdapter(Context context) {
    super(context, R.layout.item_pop_for_list_fund);
        mContext=context;
}

    @Override
    protected void onFillComponentData(ViewHolder holder, FundHoldBean bean) {
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_code)).setText(bean.getFund_code());
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_name)).setText(bean.getFund_name());
        // item设置不同的颜色
        ArrayList<FundHoldBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
