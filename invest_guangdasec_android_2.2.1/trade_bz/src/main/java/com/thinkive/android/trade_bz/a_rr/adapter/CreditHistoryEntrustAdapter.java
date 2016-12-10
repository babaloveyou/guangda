package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryEntrustBean;
import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.views.ExpandableLayoutItem;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditHistoryEntrustAdapter extends AbsBaseAdapter<RSelectHistoryEntrustBean> {
    private Context mSubContext;
    public CreditHistoryEntrustAdapter(Context context) {
        super(context, R.layout.item_a_history_entrust);
        setIsReuseView(true);
        mSubContext = context;
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, final RSelectHistoryEntrustBean bean) {
        final ExpandableLayoutItem rowItem = (ExpandableLayoutItem) holder.getComponentById(R.id.row);
        FrameLayout headerLayout = rowItem.getHeaderLayout();
        FrameLayout contentLayout = rowItem.getContentLayout();
        TextView nameTv = (TextView) headerLayout.findViewById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv = (TextView)headerLayout.findViewById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());


        TextView titleStatus = (TextView)headerLayout.findViewById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        titleStatus.setText(bean.getEntrust_type_name());

        String entrust_amount = bean.getEntrust_amount();//委托数量
        String business_amount = bean.getBusiness_amount();//成交数目
        TextView statusTv = (TextView)headerLayout.findViewById(R.id.tv_turn_status);
        statusTv.setText(bean.getEntrust_state_name());
        TextView timeTv = (TextView) headerLayout.findViewById(R.id.tv_time);
        timeTv.setText(bean.getEntrust_time());

        TextView entrustInfoTv = (TextView) headerLayout.findViewById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getEntrust_price() +"/" + bean.getEntrust_amount() );

        TextView turnoverInfoTv = (TextView) headerLayout.findViewById(R.id.tv_trunover_info);
        turnoverInfoTv.setText(bean.getBusiness_price() + "/" + bean.getBusiness_amount() );
        TextView buy = (TextView) contentLayout.findViewById(R.id.tv_item_expend_buy);
        buy.setText("融买");
        buy  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mSubContext, MultiCreditTradeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 0);
                bundle.putString("stock_code", bean.getStock_code());
                intent.putExtras(bundle);
                mSubContext.startActivity(intent);
                rowItem.hide();
            }
        });
        TextView sale = (TextView) contentLayout.findViewById(R.id.tv_item_expend_sale);
        sale.setText("融卖");
        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mSubContext, MultiCreditTradeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 1);
                bundle.putString("stock_code", bean.getStock_code());
                intent.putExtras(bundle);
                mSubContext.startActivity(intent);
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

