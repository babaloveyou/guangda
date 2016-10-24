package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundCompanyBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import java.util.ArrayList;

/**
 * 基金开户选择列表
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/9
 */

public class FundCompanyAdapter extends AbsBaseAdapter<FundCompanyBean> {

    private Context mContext;

    public FundCompanyAdapter(Context context) {
        super(context, R.layout.item_pop_for_list_fund);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, FundCompanyBean bean) {
        ((TextView) holder.getComponentById(R.id.tv_pop_fund_code)).setText(bean.getCompany_name());

        // item设置不同的颜色
        ArrayList<FundCompanyBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
