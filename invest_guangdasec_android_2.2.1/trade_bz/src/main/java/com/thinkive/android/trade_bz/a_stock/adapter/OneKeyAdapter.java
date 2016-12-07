package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;

/**
 * 一键归集--归集数据信息
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */

public class OneKeyAdapter extends AbsBaseAdapter<OneKeyBean> {
    private Context mContext;
    public OneKeyAdapter(Context context) {
        super(context,R.layout.item_a_one_key);
        mContext = context;
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, OneKeyBean bean) {
        TextView ontKeyName =(TextView) holder.getComponentById(R.id.tv_one_key_name);
        ontKeyName.setText(bean.getBank_name());


        TextView oneKeyMoney =(TextView) holder.getComponentById(R.id.tv_one_key_money);
        oneKeyMoney.setText(bean.getCurrent_balance());

        TextView oneKeyCanUse =(TextView) holder.getComponentById(R.id.tv_one_key_can_use);
        oneKeyCanUse.setText(bean.getEnable_balance());

        TextView oneKeyType =(TextView) holder.getComponentById(R.id.tv_one_key_type);
        if(bean.getFundseq().equals("0")){
            oneKeyType.setText(this.getContext().getString(R.string.one_key_zhu));
        }else if(bean.getFundseq().equals("1")){
            oneKeyType.setText(this.getContext().getString(R.string.one_key_fu));
        }
    }
}
