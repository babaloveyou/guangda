package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 融资融券——下单——合约展期——选择合约列表适配器
 * @author 王延龙
 * @company ThinkIve
 * @date 2016/4/19.
 */
public class RChooseContractExtensionAdapter extends AbsBaseAdapter<RChooseContractBean> {
    private Context mSubContext;
    private FontManager mFontManager;

    public RChooseContractExtensionAdapter(Context context) {
        super(context, R.layout.item_r_choose_contract_extension);
        this.mSubContext = context;
        this.mFontManager = FontManager.getInstance(mSubContext);
    }

    /**
     * 填充数据
     * @param holder ViewHolder实例对象
     * @param bean   数据bean实例对象
     */
    @Override
    protected void onFillComponentData(ViewHolder holder, RChooseContractBean bean) {
        /**
         * 证券代码
         */
        TextView tvCode = (TextView) holder.getComponentById(R.id.tv_r_stock_code);
        tvCode.setText(bean.getStock_code());

        /**
         * 证券名称
         */
        TextView tvName = (TextView) holder.getComponentById(R.id.tv_r_stock_name);
        tvName.setText(bean.getStock_name());

        /**
         * 证券编码
         */
        TextView tvCompactId = (TextView) holder.getComponentById(R.id.tv_r_contract_id);
        tvCompactId.setText(bean.getCompact_id());

        /**
         *合约到期日期
         */
        TextView tvBeginDate = (TextView) holder.getComponentById(R.id.tv_r_begin_date);
        tvBeginDate.setText(bean.getRet_end_date());

        /**
         * 是否被选中
         */
        CheckBox ckbChooseContract = (CheckBox)holder.getComponentById(R.id.ckb_contract_item_select);
        ckbChooseContract.setChecked(bean.isChecked());

        //设置字体
        mFontManager.setTextFont(tvCode, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCompactId, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBeginDate, mFontManager.FONT_DINPRO_MEDIUM);
    }
}
