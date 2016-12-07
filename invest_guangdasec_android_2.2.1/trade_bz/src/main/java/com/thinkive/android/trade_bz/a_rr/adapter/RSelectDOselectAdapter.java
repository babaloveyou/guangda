package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectDOSelectBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 * 融资融券--查询--交割单（303027）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectDOselectAdapter extends AbsBaseAdapter<RSelectDOSelectBean> {
    private Context mSubContext;

    public RSelectDOselectAdapter(Context context) {
        super(context, R.layout.item_r_select_do);
        mSubContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectDOSelectBean bean) {
        TextView tv1 = (TextView) holder.getComponentById(R.id.tv1);
        TextView tv2 = (TextView) holder.getComponentById(R.id.tv2);
        TextView tv3 = (TextView) holder.getComponentById(R.id.tv3);
        TextView tv4 = (TextView) holder.getComponentById(R.id.tv4);
        TextView tv5 = (TextView) holder.getComponentById(R.id.tv5);
        TextView tv6 = (TextView) holder.getComponentById(R.id.tv6);
        TextView tv7 = (TextView) holder.getComponentById(R.id.tv7);
        TextView tv8 = (TextView) holder.getComponentById(R.id.tv8);
        TextView tv9 = (TextView) holder.getComponentById(R.id.tv9);
        TextView tv10 = (TextView) holder.getComponentById(R.id.tv10);
        TextView tv11 = (TextView) holder.getComponentById(R.id.tv11);
        TextView tv12 = (TextView) holder.getComponentById(R.id.tv12);
        TextView tv13 = (TextView) holder.getComponentById(R.id.tv13);
        TextView tv14 = (TextView) holder.getComponentById(R.id.tv14);

        tv1.setText(bean.getBusiness_name());
        tv2.setText(bean.getInit_date());
        tv3.setText(bean.getOccur_amount());
        tv4.setText(bean.getBusiness_price());
        tv5.setText(bean.getBusiness_balance());
        tv6.setText(bean.getFundeffect());
        tv7.setText(bean.getFee_sxf());
        tv8.setText(bean.getFee_qsf());
        tv9.setText(bean.getFee_jygf());
        tv10.setText(bean.getFare1());
        tv11.setText(bean.getFare2());
        tv12.setText(bean.getBusiness_no());
        tv13.setText(bean.getPost_amount());
        tv14.setText(bean.getPost_balance());

    }
}
