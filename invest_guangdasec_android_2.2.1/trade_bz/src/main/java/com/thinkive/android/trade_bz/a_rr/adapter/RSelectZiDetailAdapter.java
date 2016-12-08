package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 * Created by Administrator on 2016/12/2.
 */
public class RSelectZiDetailAdapter extends AbsBaseAdapter<RSelectContractBean> {
    private Context mSubContext;

    public RSelectZiDetailAdapter(Context context) {
        super(context, R.layout.item_r_select_zi_detail);
        mSubContext = context;
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectContractBean b) {
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

        tv1.setText(getCurrentPosition()+1+"");
        tv2.setText(b.getCompact_id());
        tv3.setText(b.getopen_date());
        tv4.setText(b.getRet_end_date());
        tv5.setText(b.getStock_code());
        tv6.setText(b.getStock_name());
        tv7.setText(b.getBusiness_balance());
        tv8.setText(b.getRepaid_balance());
        tv9.setText(b.getCompact_interest());
        tv10.setText(b.getRepaid_interest());
        tv11.setText(b.getpunidebts());
        tv12.setText(b.getpunifee_repay());
        tv13.setText(b.getReal_compact_balance());
        tv14.setText(b.getCompact_status());
    }
}

