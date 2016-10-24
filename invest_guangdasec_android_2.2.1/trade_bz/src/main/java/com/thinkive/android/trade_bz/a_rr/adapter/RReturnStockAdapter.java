package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 融资融券-选择合约
 * 注：针对还券的样式
 * @author 张雪梅
 * @date 2016/8/23
 */
public class RReturnStockAdapter extends AbsBaseAdapter<RChooseContractBean> {

    private Context mSubContext;
    private FontManager mFontManager;

    public RReturnStockAdapter(Context context) {
        super(context, R.layout.item_r_choose_contract_return_stock);
        this.mSubContext = context;
        this.mFontManager = FontManager.getInstance(mSubContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final RChooseContractBean bean) {
        LinearLayout layOut = (LinearLayout) holder.getComponentById(R.id.lin_choose_contract);
        /**
         * 是否被选中
         */
        CheckBox ckbChooseContract = (CheckBox)holder.getComponentById(R.id.ckb_contract_item_select);
        boolean canCheck = bean.isCan_checked();
        if(canCheck){
            ckbChooseContract.setChecked(bean.isChecked());
            layOut.setBackgroundResource(R.drawable.selector_press_list_item);
        }else{
            ckbChooseContract.setChecked(false);
            layOut.setBackgroundResource(R.color.trade_text_color0);
        }
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
         * 合约开仓金额
         */
        TextView tvBusinessbalance = (TextView) holder.getComponentById(R.id.tv_r_business_balance);
        tvBusinessbalance.setText(bean.getBegin_compact_balance());

        /**
         * 合约未还金额
         */
        TextView tvBalance = (TextView) holder.getComponentById(R.id.tv_r_real_compact_balance);
        tvBalance.setText(bean.getReal_compact_balance());

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
