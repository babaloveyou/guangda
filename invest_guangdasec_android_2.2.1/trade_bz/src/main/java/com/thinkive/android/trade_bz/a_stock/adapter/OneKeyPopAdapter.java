package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;

import java.util.ArrayList;

/**
 * 一键归集
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */

public class OneKeyPopAdapter extends AbsBaseAdapter<OneKeyBean> {
    private Context mContext;
    public OneKeyPopAdapter(Context context) {
        super(context,R.layout.item_pop_for_list_fund);
        mContext=context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, OneKeyBean bean) {
        TextView tvType=(TextView) holder.getComponentById(R.id.tv_pop_fund_left);
        if(bean.getFundseq().equals("0")){
            tvType.setText(this.getContext().getString(R.string.one_key_zhu2));
        }else if(bean.getFundseq().equals("1")){
            tvType.setText(this.getContext().getString(R.string.one_key_fu2));
        }

        TextView tvName=(TextView) holder.getComponentById(R.id.tv_pop_fund_code);
        tvName.setText(" "+bean.getBank_name()+"  "+bean.getFundid());
        tvName.setTextColor(mContext.getResources().getColor(R.color.trade_color_black));

        TextView tvNum=(TextView) holder.getComponentById(R.id.tv_pop_fund_name);
        tvNum.setText(bean.getMoney_type_name());

        // item设置不同的颜色
        ArrayList<OneKeyBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }
}
