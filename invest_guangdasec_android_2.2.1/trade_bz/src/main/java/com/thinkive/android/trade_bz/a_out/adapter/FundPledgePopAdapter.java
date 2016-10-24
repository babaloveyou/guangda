package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundReturnMoneyDateBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import java.util.ArrayList;

/**
 *  基金定投的还款日期的 popupWindown
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/5
 */

public class FundPledgePopAdapter extends AbsBaseAdapter<FundReturnMoneyDateBean> {
   private Context mContext;
    public FundPledgePopAdapter(Context context) {
    super(context, R.layout.item_pop_for_list_fund);
        mContext=context;
}

    @Override
    protected void onFillComponentData(ViewHolder holder, FundReturnMoneyDateBean bean) {
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_code)).setText(bean.getPay_day());

        // item设置不同的颜色
        ArrayList<FundReturnMoneyDateBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
