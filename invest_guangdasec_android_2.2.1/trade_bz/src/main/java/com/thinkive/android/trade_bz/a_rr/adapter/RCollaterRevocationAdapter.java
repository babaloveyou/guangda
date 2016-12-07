package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterRevocationBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterRevocationServiceImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.RCollaterRevocationDialog;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 融资融券--划转--撤单
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */

public class RCollaterRevocationAdapter extends AbsBaseAdapter<RCollaterRevocationBean> {
    private Context mSubContext;
    private RCollaterRevocationServiceImpl mServices;

    public RCollaterRevocationAdapter(Context context, RCollaterRevocationServiceImpl service) {
        super(context, R.layout.item_r_rollater_revocation);
        mSubContext = context;
        mServices = service;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RCollaterRevocationBean bean) {
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());

        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        titleStatus.setText(bean.getEntrust_type_name());
        TextView timeTv = (TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getEntrust_time() + "");

        TextView entrustInfoTv = (TextView) holder.getComponentById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getEntrust_price() + "/" + bean.getEntrust_amount());

        TextView turnoverInfoTv = (TextView) holder.getComponentById(R.id.tv_trunover_info);
        turnoverInfoTv.setText(bean.getEntrust_no());
        final LinearLayout lClick = (LinearLayout) holder.getComponentById(R.id.lin_bottom_revocation);
        //点击列表项弹出对话框
        final LinearLayout llParent = (LinearLayout) holder.getComponentById(R.id.lin_lin_r);
        llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                RCollaterRevocationDialog dialog = new RCollaterRevocationDialog(mSubContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
