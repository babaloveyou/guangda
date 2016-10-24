package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundAccountBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

/**
 *  基金交易--查询--基金账户查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundAccountAdapter extends AbsBaseAdapter<FundAccountBean> {

    private Context mContext;
    private FontManager mFontManager;

    public FundAccountAdapter(Context context) {
        super(context, R.layout.item_fund_account);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, FundAccountBean bean) {
        TextView tvFundName=(TextView) holder.getComponentById(R.id.tv_fund_name_account);
        tvFundName.setText(bean.getCompany_name());

        TextView tvData=(TextView) holder.getComponentById(R.id.tv_open_date);
        tvData.setText(bean.getOpen_date());

        TextView tvAccount=(TextView) holder.getComponentById(R.id.tv_fselect_one);
        tvAccount.setText(bean.getFund_account());

        TextView tvCompanyCode=(TextView) holder.getComponentById(R.id.tv_fselect_three);
        tvCompanyCode.setText(bean.getFund_company());

        TextView tvName=(TextView) holder.getComponentById(R.id.tv_fselect_two);
        tvName.setText(bean.getHolder_name());

        TextView tvStatus=(TextView) holder.getComponentById(R.id.tv_fselect_four);
        tvStatus.setText(forMateStatus(bean.getHolder_status()));

        mFontManager.setTextFont(tvFundName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvData, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvAccount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvCompanyCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvName, FontManager.FONT_DINPRO_MEDIUM);
    }
    private String forMateStatus(String str){
        if(str.equals("1")){
            str=mContext.getResources().getString(R.string.fund_account5);
        }else if(str.equals("2")){
            str=mContext.getResources().getString(R.string.fund_account6);
        }else if(str.equals("3")){
            str=mContext.getResources().getString(R.string.fund_account7);
        }else if(str.equals("4")){
            str=mContext.getResources().getString(R.string.fund_account8);
        }else{
            str=mContext.getResources().getString(R.string.fund_account4);
        }
        return str;
    }
}
