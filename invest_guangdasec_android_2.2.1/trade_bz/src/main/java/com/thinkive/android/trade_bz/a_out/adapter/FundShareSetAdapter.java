package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_out.fragment.FundShareSetFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 *  场外基金交易--分红设置
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/28
 */

public class FundShareSetAdapter extends AbsBaseAdapter<FundHoldBean> {

    private Context mContext;
    private FundShareSetFragment mFragment;
    private FontManager mFontManager;

    public FundShareSetAdapter(Context context, FundShareSetFragment fragment) {
        super(context, R.layout.item_fund_share_set);
        mContext = context;
        mFragment=fragment;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final FundHoldBean bean) {
        TextView tvShareName=(TextView) holder.getComponentById(R.id.tv_share_set_name);
        TextView tvShareCode=(TextView) holder.getComponentById(R.id.tv_share_set_code);
        TextView tvShareProfit=(TextView) holder.getComponentById(R.id.tv_share_set_profit);
        TextView tvShareMarket=(TextView) holder.getComponentById(R.id.tv_share_set_market);
        TextView tvShareHold=(TextView) holder.getComponentById(R.id.tv_share_set_hold);
        TextView tvShareStatusName=(TextView) holder.getComponentById(R.id.tv_share_set_status);
        RadioButton rbShareHold=(RadioButton) holder.getComponentById(R.id.rb_share_set_hold);
        RadioButton rbShareCash=(RadioButton) holder.getComponentById(R.id.rb_share_set_cash);
        RadioGroup rgShare=(RadioGroup)holder.getComponentById(R.id.rg_radio_group_share);

        rgShare.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                if (checkedId == R.id.rb_share_set_hold) {
                    mFragment.onSetShareSet("0", bean.getFund_code(),bean.getFund_company());
                } else if (checkedId == R.id.rb_share_set_cash) {
                    mFragment.onSetShareSet("1", bean.getFund_code(),bean.getFund_company());
                }
            }
        });

        //份额分红
        if(bean.getDividendmethod().equals("0")){
            rbShareHold.setChecked(true);
            //现金分红
        }else if(bean.getDividendmethod().equals("1")){
            rbShareCash.setChecked(true);
        }

        tvShareName.setText(bean.getFund_name());
        tvShareCode.setText(bean.getFund_code());
        tvShareProfit.setText(bean.getNav());
        tvShareMarket.setText(bean.getMarket_value());
        tvShareHold.setText(bean.getEnable_shares());
        tvShareStatusName.setText(bean.getFund_status_name());

        mFontManager.setTextFont(tvShareName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvShareCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvShareProfit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvShareMarket, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvShareHold, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvShareStatusName, FontManager.FONT_DINPRO_MEDIUM);
    }
}
