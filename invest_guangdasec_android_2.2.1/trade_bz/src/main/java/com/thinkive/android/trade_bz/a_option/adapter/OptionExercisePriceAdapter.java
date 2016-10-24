package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionExercisePriceBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 *
 * Description：放行权价格列表的适配器 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/21 <br>
 */
public class OptionExercisePriceAdapter extends AbsBaseAdapter<OptionExercisePriceBean>{
    private Context mContext;

    public OptionExercisePriceAdapter(Context context) {
        super(context, R.layout.item_option_exercise_price);
        mContext=context;
    }

    /**
     *
     * Description：用于listview填充一行数据 <br>
     * Author：晏政清 <br>
     * Corporation：深圳市思迪信息技术股份有限公司 <br>
     * Date：2016/7/21 <br>
     */
    @Override
    protected void onFillComponentData(ViewHolder holder, OptionExercisePriceBean bean) {
        TextView exercisePrice=holder.getComponentById(R.id.exercise_price);
        exercisePrice.setText(bean.getExercise_price());
    }
}
