package com.thinkive.android.trade_bz.a_new.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewOneKeyBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.NewStockInputNumDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.text.SimpleDateFormat;

/**
 *  一键申购适配器
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */

public class NewOneKeySubAdapter extends AbsBaseAdapter<NewOneKeyBean> {
    private Context mContext;
    private FontManager mFontManager;
    SimpleDateFormat df;

    public NewOneKeySubAdapter(Context context) {
        super(context, R.layout.item_new_one_key_sub);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final NewOneKeyBean bean) {
        TextView tvCode = holder.getComponentById(R.id.tv_total_stock_code);
        tvCode.setText(bean.getStock_code());

        TextView tvName = holder.getComponentById(R.id.tv_total_stock_name);
        tvName.setText(bean.getStock_name());

        TextView tvMax = holder.getComponentById(R.id.tv_one_key_sub_max);
        tvMax.setText(bean.getMaxamount());

        TextView tvPrice = holder.getComponentById(R.id.tv_one_key_sub_price);
        tvPrice.setText(bean.getPrice());

        TextView tvNum = holder.getComponentById(R.id.tv_one_key_sub_num);
        tvNum.setText(bean.getInput_num());

        CheckBox checkBox = holder.getComponentById(R.id.rb_one_key_sub);
        checkBox.setChecked(bean.isCheck());

        TextView tvInPut = holder.getComponentById(R.id.tv_click_input_num);
        tvInPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                NewStockInputNumDialog dialog = new NewStockInputNumDialog(mContext);
                dialog.setMaxNum(bean);
                dialog.show();
            }
        });

        mFontManager.setTextFont(tvCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvMax, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvNum, FontManager.FONT_DINPRO_MEDIUM);
    }
}
