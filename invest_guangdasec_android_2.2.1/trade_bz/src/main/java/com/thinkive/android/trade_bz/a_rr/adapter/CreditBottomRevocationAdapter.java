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
 * Created by Administrator on 2016/10/27.
 */
public class CreditBottomRevocationAdapter extends AbsBaseAdapter<RRevocationBean> {

    private Context mSubContext;
    private RRevocationServiceImpl mService;

    public CreditBottomRevocationAdapter(Context context, RRevocationServiceImpl mServices) {
        super(context, R.layout.item_bottom_revocation);
        mSubContext = context;
       this.mService = mServices;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RRevocationBean bean) {
        TextView nameTv=(TextView) holder.getComponentById(R.id.tv_name);
        nameTv.setText(bean.getStock_name());

        TextView codeTv=(TextView) holder.getComponentById(R.id.tv_code);
        codeTv.setText(bean.getStock_code());

        TextView titleStatus = (TextView) holder.getComponentById(R.id.tv_title_status);
        String entrust_bs = bean.getEntrust_bs();
        titleStatus.setText((true?"限价":"市价")+("0".equals(entrust_bs)?"买入":"卖出"));


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
                if (TradeUtils.isFastClick()) {
                    return;
                }
                RRevocationConfirmDialog dialog = new RRevocationConfirmDialog(mSubContext, mService);
                dialog.setDataBean(bean);
                dialog.setDataToViews();
                dialog.show();
            }
        });
    }
}
