package com.thinkive.android.trade_bz.a_new.adapter;

import android.content.Context;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewGiveUpRevocationBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 放弃认购申报撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewGiveUpRevocationAdapter extends AbsBaseAdapter<NewGiveUpRevocationBean> {

    private FontManager mFontManager;

    public NewGiveUpRevocationAdapter(Context context) {
        super(context, R.layout.item_new_give_up_revocation);
        mFontManager = FontManager.getInstance(context);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, NewGiveUpRevocationBean bean) {
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
