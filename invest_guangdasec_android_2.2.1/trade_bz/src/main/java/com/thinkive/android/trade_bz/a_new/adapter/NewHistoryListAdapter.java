package com.thinkive.android.trade_bz.a_new.adapter;

import android.content.Context;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewHistoryListBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 缴费历史记录查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewHistoryListAdapter extends AbsBaseAdapter<NewHistoryListBean> {

    private FontManager mFontManager;

    public NewHistoryListAdapter(Context context) {
        super(context, R.layout.item_new_history_list);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, NewHistoryListBean bean) {
//        TextView tvDisData= holder.getComponentById(R.id.tv_distribute_data);
//        tvDisData.setText(bean.getBizdate());
//
//        TextView tvDisName= holder.getComponentById(R.id.tv_distribute_securit_name);
//        tvDisName.setText(bean.getStock_name());
//
//        TextView tvDisNum= holder.getComponentById(R.id.tv_distribute_securit_number);
//        tvDisNum.setText(bean.getStock_code());
//
//        TextView tvDisSelect= holder.getComponentById(R.id.tv_select_distribute_data);
//        tvDisSelect.setText(bean.getMateno());
//
//        TextView tvDisCount= holder.getComponentById(R.id.tv_distribute_count);
//        tvDisCount.setText(bean.getStock_account());
//
//        TextView tvDisBegin= holder.getComponentById(R.id.tv_distribute_begin);
//        tvDisBegin.setText(bean.getMatchqty());
//
//        mFontManager.setTextFont(tvDisData, FontManager.FONT_DINPRO_MEDIUM);
//        mFontManager.setTextFont(tvDisName, FontManager.FONT_DINPRO_MEDIUM);
//        mFontManager.setTextFont(tvDisNum, FontManager.FONT_DINPRO_MEDIUM);
//        mFontManager.setTextFont(tvDisSelect, FontManager.FONT_DINPRO_MEDIUM);
//        mFontManager.setTextFont(tvDisCount, FontManager.FONT_DINPRO_MEDIUM);
//        mFontManager.setTextFont(tvDisBegin, FontManager.FONT_DINPRO_MEDIUM);
    }
}
