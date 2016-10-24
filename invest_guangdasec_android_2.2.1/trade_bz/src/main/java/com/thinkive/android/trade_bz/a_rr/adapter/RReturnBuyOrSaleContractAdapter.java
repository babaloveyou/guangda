package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 买券还券 未偿还合约
 * @author 张雪梅
 * @date 2016/8/22
 * @company Thinkive
 */
public class RReturnBuyOrSaleContractAdapter extends AbsBaseAdapter<RChooseContractBean> {

    private Context mSubContext;
    private FontManager mFontManager;

    public RReturnBuyOrSaleContractAdapter(Context context) {
        super(context, R.layout.item_r_not_contract);
        this.mSubContext = context;
        this.mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RChooseContractBean bean) {
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
         * 合约开仓数量
         */
        TextView tvBusinessbalance = (TextView) holder.getComponentById(R.id.tv_r_business_balance);
        tvBusinessbalance.setText(bean.getBusiness_amount());

        /**
         * 合约未还数量
         */
        TextView tvBalance = (TextView) holder.getComponentById(R.id.tv_r_real_compact_balance);
        tvBalance.setText(bean.getReal_compact_amount());

        /**
         * 合约开始日期
         */
        TextView tvBeginDate = (TextView) holder.getComponentById(R.id.tv_r_begin_date);
        tvBeginDate.setText(bean.getOpen_date());


        //设置字体
        mFontManager.setTextFont(tvCode, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCompactId, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBusinessbalance, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBalance, mFontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBeginDate, mFontManager.FONT_DINPRO_MEDIUM);
    }
}
