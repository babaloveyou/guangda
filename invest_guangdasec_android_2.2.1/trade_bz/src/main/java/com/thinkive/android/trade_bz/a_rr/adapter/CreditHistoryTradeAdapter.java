package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.views.ExpandableLayoutItem;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditHistoryTradeAdapter extends AbsBaseAdapter<RSelectHistoryTradeBean> {
    private Context mContext;
    public CreditHistoryTradeAdapter(Context context) {
        super(context, R.layout.item_a_history_trade);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectHistoryTradeBean bean) {
        final ExpandableLayoutItem rowItem = (ExpandableLayoutItem) holder.getComponentById(R.id.row);
        FrameLayout headerLayout = rowItem.getHeaderLayout();
        FrameLayout contentLayout = rowItem.getContentLayout();
        TextView nameTv = (TextView) headerLayout.findViewById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv = (TextView) headerLayout.findViewById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());


        TextView titleStatus = (TextView) headerLayout.findViewById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        //        String entrust_limit = bean.getEntrust_limit();
        titleStatus.setText(bean.getEntrust_type_name());

        TextView timeTv = (TextView)headerLayout.findViewById(R.id.tv_time);
        timeTv.setText(bean.getBusiness_time());

        TextView entrustInfoTv = (TextView) headerLayout.findViewById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getBusiness_price() + "元" );

        TextView turnoverInfoTv = (TextView) headerLayout.findViewById(R.id.tv_trunover_info);
        turnoverInfoTv.setText( bean.getBusiness_amount() + "股");
        TextView buy = (TextView) contentLayout.findViewById(R.id.tv_item_expend_buy);
        buy.setText("融买");
        buy  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MultiCreditTradeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 0);
                bundle.putString("stock_code", bean.getStock_code());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                rowItem.hide();
            }
        });
        TextView sale = (TextView) contentLayout.findViewById(R.id.tv_item_expend_sale);
        sale.setText("融卖");
        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MultiCreditTradeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 1);
                bundle.putString("stock_code", bean.getStock_code());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                rowItem.hide();
            }
        });
        contentLayout.findViewById(R.id.tv_item_expend_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowItem.hide();
            }
        });
    }
}

