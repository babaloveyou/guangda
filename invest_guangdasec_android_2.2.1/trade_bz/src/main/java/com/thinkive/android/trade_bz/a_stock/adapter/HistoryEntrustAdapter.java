package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.bean.HistoryEntrustBean;
import com.thinkive.android.trade_bz.views.ExpandableLayoutItem;

/**
 *  历史委托ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */

public class HistoryEntrustAdapter extends AbsBaseAdapter<HistoryEntrustBean> {

    private Context mSubContext;

    public HistoryEntrustAdapter(Context context) {
        super(context, R.layout.item_a_history_entrust);
        setIsReuseView(true);
        mSubContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final HistoryEntrustBean bean) {
        final ExpandableLayoutItem rowItem = (ExpandableLayoutItem) holder.getComponentById(R.id.row);
        FrameLayout headerLayout = rowItem.getHeaderLayout();
        FrameLayout contentLayout = rowItem.getContentLayout();
        TextView nameTv = (TextView)headerLayout.findViewById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());
        TextView codeTv = (TextView) headerLayout.findViewById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());
        TextView titleStatus = (TextView) headerLayout.findViewById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        titleStatus.setText(bean.getEntrust_type_name());
        String entrust_amount = bean.getEntrust_amount();//委托数量
        String business_amount = bean.getBusiness_amount();//成交数目
        TextView statusTv = (TextView) headerLayout.findViewById(R.id.tv_turn_status);
        statusTv.setText(bean.getEntrust_state_name());
        TextView timeTv = (TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getEntrust_time());
        TextView entrustInfoTv = (TextView)headerLayout.findViewById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getEntrust_price() +"/" + bean.getEntrust_amount() );
        TextView turnoverInfoTv = (TextView) headerLayout.findViewById(R.id.tv_trunover_info);
        turnoverInfoTv.setText(bean.getBusiness_price() + "/" + bean.getBusiness_amount() );
        contentLayout.findViewById(R.id.tv_item_expend_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mSubContext, MultiTradeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ViewPagerFragmentPos", 0);
                bundle.putString("stock_code", bean.getStock_code());
                intent.putExtras(bundle);
                mSubContext.startActivity(intent);
                rowItem.hide();
            }
        });
        contentLayout.findViewById(R.id.tv_item_expend_sale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mSubContext, MultiTradeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ViewPagerFragmentPos", 1);
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
