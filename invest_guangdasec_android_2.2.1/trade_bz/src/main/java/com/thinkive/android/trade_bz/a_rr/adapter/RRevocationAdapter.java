package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.a_rr.bll.RRevocationServiceImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.RRevocationConfirmDialog;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 *  融资融券--下单--撤单（303017）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */

public class RRevocationAdapter extends AbsBaseAdapter<RRevocationBean> {
    private Context mSubContext;
    private RRevocationServiceImpl mServices;

    public RRevocationAdapter(Context context, RRevocationServiceImpl services) {
        super(context, R.layout.item_r_revocation);
        mSubContext = context;
        mServices = services;
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, final RRevocationBean bean) {
        TextView nameTv=(TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv=(TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());

        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        titleStatus.setText(bean.getEntrust_type_name());


        TextView timeTv=(TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getEntrust_time()+"");

        TextView entrustInfoTv=(TextView) holder.getComponentById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getEntrust_price()+"/"+bean.getEntrust_amount());

        TextView turnoverInfoTv=(TextView) holder.getComponentById(R.id.tv_trunover_info);
        turnoverInfoTv.setText(bean.getBusiness_price()+"/"+bean.getBusiness_amount());

        //点击列表项弹出对话框
        final LinearLayout llClick=(LinearLayout) holder.getComponentById(R.id.lin_bottom_revocation);
        llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                RRevocationConfirmDialog dialog = new RRevocationConfirmDialog(mSubContext, mServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
