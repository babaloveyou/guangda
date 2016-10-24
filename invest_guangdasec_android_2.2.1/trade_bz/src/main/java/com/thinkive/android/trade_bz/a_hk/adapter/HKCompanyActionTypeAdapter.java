package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCompanyActionTypeBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import java.util.ArrayList;

/**
 *  公司行为(类型弹出框适配器)
 * @author 张雪梅
 * @company Thinkive
 * @date 16/5/23
 */

public class HKCompanyActionTypeAdapter extends AbsBaseAdapter<HKCompanyActionTypeBean> {
    private Context mContext;
    public HKCompanyActionTypeAdapter(Context context) {
        super(context, R.layout.item_pop_search_stocks);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCompanyActionTypeBean bean) {
        TextView tvData= holder.getComponentById(R.id.tv_item_search_stock_code);
        tvData.setText(bean.getEntrust_way_name());
        // 下面的代码是为了实现奇数行和偶数行颜色不同。
        ArrayList<HKCompanyActionTypeBean> dataList = getListData();
        int position = dataList.indexOf(bean);
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
    }
}
