package com.thinkive.android.trade_bz.a_hk.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalLiabiitiesBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/2 15:02
 * 描述	     组合费查询的Adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalCombinedAdapter extends AbsBaseAdapter<HKCapitalLiabiitiesBean> {

    private final FontManager mFontManager;

    public HKCapitalCombinedAdapter(Context contex) {
        super(contex, R.layout.item_hk_combine);
        mFontManager = FontManager.getInstance(contex);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, HKCapitalLiabiitiesBean bean) {
        TextView mTvSno = holder.getComponentById(R.id.tv_sno);
        mTvSno.setText(bean.getSno());

        TextView mTvJsdate = holder.getComponentById(R.id.tv_jsdate);
        mTvJsdate.setText(bean.getJsdate());

        TextView mTvDebtsid = holder.getComponentById(R.id.tv_debtsid);
        mTvDebtsid.setText(bean.getDebtsid());

        TextView mTvQsdate = holder.getComponentById(R.id.tv_qsdate);
        mTvQsdate.setText(bean.getQsdate());

        TextView mTvSettrate = holder.getComponentById(R.id.tv_settrate);
        mTvSettrate.setText(bean.getSettrate());

        TextView mTvMoneyTypeName = holder.getComponentById(R.id.tv_money_type_name);
        mTvMoneyTypeName.setText(bean.getMoney_type_name());

        TextView mTvCombinfeeRmb = holder.getComponentById(R.id.tv_combinfee_rmb);
        mTvCombinfeeRmb.setText(bean.getCombinfee_rmb());

        TextView mTvLastmktvalue = holder.getComponentById(R.id.tv_lastmktvalue);
        mTvLastmktvalue.setText(bean.getLastmktvalue());

        TextView mCombinfeeHk = holder.getComponentById(R.id.combinfee_hk);
        mCombinfeeHk.setText(bean.getCombinfee_hk());
        // 给Textview设置字体
        mFontManager.setTextFont(mTvSno, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvJsdate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvDebtsid, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvQsdate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvSettrate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvMoneyTypeName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvCombinfeeRmb, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvLastmktvalue, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mCombinfeeHk, FontManager.FONT_DINPRO_MEDIUM);

    }
}
