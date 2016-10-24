package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionHoldStockBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 *
 * Description：期权持仓列表适配器 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/26 <br>
 */
public class OptionHoldStockAdapter extends AbsBaseAdapter<OptionHoldStockBean>{
    private Context mContext;
    public OptionHoldStockAdapter(Context context) {
        super(context, R.layout.item_option_hold_stock);
        mContext=context;
    }

    /**
     * ListView 的每一行数据的填充方法
     * @param holder ViewHolder实例对象
     * @param bean   数据bean实例对象
     */
    @Override
    protected void onFillComponentData(ViewHolder holder, OptionHoldStockBean bean) {
        TextView holdStock=holder.getComponentById(R.id.hold_stock);
        holdStock.setText(bean.getOption_name());
    }
}
