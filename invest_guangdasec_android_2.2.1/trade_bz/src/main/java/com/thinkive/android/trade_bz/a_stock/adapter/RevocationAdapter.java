package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.RevocationServicesImpl;
import com.thinkive.android.trade_bz.dialog.ARevocationConfirmDialog;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 撤单ListView适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/16
 */

public class RevocationAdapter extends AbsBaseAdapter<RevocationBean> {

    private Context mSubContext;
    private RevocationServicesImpl mRevocationServices;

    public RevocationAdapter(Context context, RevocationServicesImpl mServices) {
        super(context, R.layout.item_a_revocation);
        mSubContext = context;
        mRevocationServices = mServices;
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, final RevocationBean bean) {
        TextView nameTv=(TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv=(TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());

        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        String entrust_limit = bean.getEntrust_limit();
        titleStatus.setText(bean.getEntrust_type_name());


        TextView timeTv=(TextView) holder.getComponentById(R.id.tv_time);
        timeTv.setText(bean.getEntrust_time()+"");

        TextView entrustInfoTv=(TextView) holder.getComponentById(R.id.tv_entrust_info);
        entrustInfoTv.setText(bean.getEntrust_price()+"/"+bean.getEntrust_amount());

        TextView turnoverInfoTv=(TextView) holder.getComponentById(R.id.tv_trunover_info);
        turnoverInfoTv.setText(bean.getBusiness_price()+"/"+bean.getBusiness_amount());
        final LinearLayout lClick=(LinearLayout) holder.getComponentById(R.id.lin_bottom_revocation);
        //点击列表项弹出对话框
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick2()) {
                    return;
                }
                ARevocationConfirmDialog dialog = new ARevocationConfirmDialog(mSubContext, mRevocationServices);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
