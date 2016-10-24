package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionEntrustPriceWayBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 *
 * Description：授权价格选择列表适配器 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/26 <br>
 */
public class OptionEntrustPriceWayAdapter extends AbsBaseAdapter<OptionEntrustPriceWayBean>{
    private Context mContext;
    public OptionEntrustPriceWayAdapter(Context context) {
        super(context, R.layout.item_option_entrust_price_way);
        mContext=context;
    }

    /**
     * ListView 的每一行数据的填充方法
     * @param holder ViewHolder实例对象
     * @param bean   数据bean实例对象
     */
    @Override
    protected void onFillComponentData(ViewHolder holder, OptionEntrustPriceWayBean bean) {
        TextView entrustPriceWay = holder.getComponentById(R.id.entrust_price_way);
        entrustPriceWay.setText(bean.getEntrust_way_name());
    }
}
