package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;

/**
 * Created by Administrator on 2016/12/7.
 */
public class OnkeyAccountListQueryAdapter extends AbsBaseAdapter<OneKeyBean> {
    private Context mContext;

    public OnkeyAccountListQueryAdapter(Context context) {
        super(context, R.layout.item_pop_onkey_accounts);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, OneKeyBean bean) {
        TextView tvContent = (TextView) holder.getComponentById(R.id.tv_item);
        tvContent.setText(mContext.getResources().getString(R.string.one_key_fu2)
                + bean.getFundid() + bean.getBank_name());
    }
}


