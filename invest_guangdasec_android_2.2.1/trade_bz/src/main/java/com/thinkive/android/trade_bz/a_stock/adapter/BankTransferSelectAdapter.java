package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.BankTransferBean;

import java.util.ArrayList;

/**
 *  银证转账（存管银行弹出pop的适配器）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/10
 */

public class BankTransferSelectAdapter extends AbsBaseAdapter<BankTransferBean> {

    private Context mContext;

    public BankTransferSelectAdapter(Context context) {
        super(context, R.layout.item_pop_for_list_fund);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, BankTransferBean bean) {
        TextView tvName=(TextView) holder.getComponentById(R.id.tv_pop_fund_code);
        tvName.setTextColor(mContext.getResources().getColor(R.color.trade_color_black));
        tvName.setText(bean.getBank_account());

        ((TextView) holder.getComponentById(R.id.tv_pop_fund_name)).setText(bean.getBank_name()+" "+formateMoneyType(bean.getMoney_type()));
        // item设置不同的颜色
        ArrayList<BankTransferBean> dataList = getListData();
        int position = dataList.indexOf(bean);
        if (position % 2 == 0) {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_list_item));
        } else {
            holder.getConvertView().setBackgroundColor(mContext.getResources().getColor(R.color.trade_color_white));
        }
    }

    /**
     * 处理货币类型
     * @param str
     * @return
     */
    private String formateMoneyType(String str) {
        if (str.contains("0")) {
            str = mContext.getResources().getString(R.string.bank_money_one);
        } else if (str.contains("1")) {
            str = mContext.getResources().getString(R.string.bank_money_two);
        } else if (str.contains("2")) {
            str = mContext.getResources().getString(R.string.bank_money_three);
        }
        return str;
    }
}
