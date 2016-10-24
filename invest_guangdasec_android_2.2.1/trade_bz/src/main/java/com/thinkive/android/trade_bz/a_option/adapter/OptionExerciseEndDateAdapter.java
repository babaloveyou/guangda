package com.thinkive.android.trade_bz.a_option.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionExerciseEndDateBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;


/**
 *
 * Description：放行权日期列表的适配器 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/20 <br>
 */
public class OptionExerciseEndDateAdapter extends AbsBaseAdapter<OptionExerciseEndDateBean>{
    private Context mContext;

    public OptionExerciseEndDateAdapter(Context context) {
        super(context, R.layout.item_option_exercise_end_date);
        mContext=context;
    }

    /**
     *
     * Description：用于listview填充一行数据 <br>
     * Author：晏政清 <br>
     * Corporation：深圳市思迪信息技术股份有限公司 <br>
     * Date：2016/7/20 <br>
     */
    @Override
    protected void onFillComponentData(ViewHolder holder, OptionExerciseEndDateBean bean) {
        TextView exerciseEndDateView=holder.getComponentById(R.id.exercise_end_date);
        exerciseEndDateView.setText(bean.getExe_end_date());
    }
}
