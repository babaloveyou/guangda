package com.thinkive.android.trade_bz.a_in.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.bean.InFundSercuritiesPositionsQueryBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 *
 * Description：赎回，选择场内基金的列表适配器 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/12 <br>
 */

public class InFundSercuritiesPositionsAdapter extends AbsBaseAdapter<InFundSercuritiesPositionsQueryBean>{
    private Context mContext;

    public InFundSercuritiesPositionsAdapter(Context context) {
        super(context, R.layout.item_in_fund_sercurities_positions);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, InFundSercuritiesPositionsQueryBean bean) {
        TextView tvInFundStockName = holder.getComponentById(R.id.tv_in_fund_stock_name);
        TextView tvInFundStockCode = holder.getComponentById(R.id.tv_in_fund_stock_code);
        tvInFundStockName.setText(bean.getStock_name());
        tvInFundStockCode.setText(bean.getStock_code());
    }
}
