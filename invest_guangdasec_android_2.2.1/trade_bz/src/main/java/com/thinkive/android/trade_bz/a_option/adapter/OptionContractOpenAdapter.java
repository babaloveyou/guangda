package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractOpenBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;


/**
 *
 * Description：备兑解锁 合约选择列表 适配器 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/30 <br>
 */
public class OptionContractOpenAdapter extends AbsBaseAdapter<OptionContractOpenBean>{
    private Context mContext;
    public OptionContractOpenAdapter(Context context) {
        super(context, R.layout.item_option_contract_open);
        mContext=context;
    }

    /**
     * ListView 的每一行数据的填充方法
     * @param holder ViewHolder实例对象
     * @param bean   数据bean实例对象
     */
    @Override
    protected void onFillComponentData(ViewHolder holder, OptionContractOpenBean bean) {
        TextView stockCode = holder.getComponentById(R.id.hold_stock);
        stockCode.setText(bean.getStock_code());
    }
}
