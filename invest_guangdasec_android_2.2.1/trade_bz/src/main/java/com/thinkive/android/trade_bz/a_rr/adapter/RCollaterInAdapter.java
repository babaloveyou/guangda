package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterInServiceImpl;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterInFragment;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.RCollaterInBean;
import com.thinkive.android.trade_bz.dialog.RCollaterInDialog;

/**
 * 融资融券--划转--担保品转入
 * Announcements：
 *
 * @author 柳广剑
 * @company Thinkive
 * @date 16/11/15
 */

public class RCollaterInAdapter extends AbsBaseAdapter<RCollaterInBean> {
    private RCollaterInFragment mFragment;
    private Context mContext;
    private RCollaterInServiceImpl mServices;

    public RCollaterInAdapter(Context context, RCollaterInServiceImpl services, RCollaterInFragment fragment) {
        super(context, R.layout.item_r_rollater_in);
        mFragment = fragment;
        mContext = mFragment.getActivity();
        mServices = services;
    }

    @Override
    protected void onFillComponentData(AbsBaseAdapter.ViewHolder holder, final RCollaterInBean bean) {
        View indicatorView = (View) holder.getComponentById(R.id.view_indicator);
        TextView winLoseRateTv = (TextView) holder.getComponentById(R.id.tv_win_lose_rate);
        TextView winLoseNumTv = (TextView) holder.getComponentById(R.id.tv_win_lose_num);
        TextView costPriceTv = (TextView) holder.getComponentById(R.id.tv_cost_price);
        TextView currentPriceTv = (TextView) holder.getComponentById(R.id.tv_current_price);
        TextView marketPriceTv = (TextView) holder.getComponentById(R.id.tv_market_price);
        TextView usebleTv = (TextView) holder.getComponentById(R.id.tv_usable);
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_code);
        nameTv.setText(bean.getStock_name());
        codeTv.setText(bean.getStock_code());
        //盈亏百分比
        setWinLostRate(winLoseRateTv, bean);

        //盈亏数字
        setWinLoseNumText(winLoseNumTv, bean);
        //市价
        setCostPriceText(costPriceTv, bean);

        //现价
        setCurrentPrice(currentPriceTv, bean);

        //可用
        setEnableText(usebleTv, bean);

        //市值
        setMarketPrice(marketPriceTv, bean);
        //点击列表项弹出对话框
        final LinearLayout lClick = (LinearLayout) holder.getComponentById(R.id.lin_lin_r);
        lClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.requestLink(bean);
            }
        });

    }

    private void setMarketPrice(TextView view, RCollaterInBean bean) {
        String enableText = "市值:" + bean.getMarket_value();
        SpannableStringBuilder styleEnable = new SpannableStringBuilder(enableText);
        styleEnable.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleEnable.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, enableText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleEnable);
    }

    private void setEnableText(TextView view, RCollaterInBean myStoreStockBean) {
        String enableText = "可用:" + myStoreStockBean.getEnable_amount();
        SpannableStringBuilder styleEnable = new SpannableStringBuilder(enableText);
        styleEnable.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleEnable.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, enableText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleEnable);
    }


    private void setCurrentPrice(TextView view, RCollaterInBean myStoreStockBean) {
        String currentPriceText = "现价:" + myStoreStockBean.getLast_price();
        SpannableStringBuilder styleCurrentPrice = new SpannableStringBuilder(currentPriceText);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, currentPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleCurrentPrice);
    }

    private void setCostPriceText(TextView view, RCollaterInBean myStoreStockBean) {
        String costPriceText = "成本:" + myStoreStockBean.getCost_price();
        SpannableStringBuilder styleCostPrice = new SpannableStringBuilder(costPriceText);
        styleCostPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCostPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, costPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleCostPrice);
    }

    private void setWinLoseNumText(TextView view, RCollaterInBean myStoreStockBean) {
        view.setText(myStoreStockBean.getFloat_yk());
        view.setTextColor(view.getText().toString().startsWith("-") ? mContext.getResources().getColor(R.color.trade_down_green) : mContext.getResources().getColor(R.color.trade_text));
    }

    private void setWinLostRate(TextView view, RCollaterInBean myStoreStockBean) {
        String winlostRate = myStoreStockBean.getFloat_yk_per();
        if (!TextUtils.isEmpty(winlostRate) && !winlostRate.startsWith("-")) {
            winlostRate = "+" + winlostRate;
        }
        String winLostRateTvString = "盈亏" + winlostRate + "%";
        SpannableStringBuilder styleWinLostRate = new SpannableStringBuilder(winLostRateTvString);
        if (winlostRate.startsWith(mContext.getResources().getString(R.string.common_emp_text))) {
            styleWinLostRate.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            styleWinLostRate.setSpan(new ForegroundColorSpan(winlostRate.startsWith("-") ? mContext.getResources().getColor(R.color.trade_down_green) : mContext.getResources().getColor(R.color.trade_text)), 2, winLostRateTvString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        view.setText(styleWinLostRate);
    }

    /**
     * 得到最大可转后，弹框框
     *
     * @param bean
     * @param rCollaterInBean
     */
    public void onGetLinkData(RCollaterLinkBean bean, RCollaterInBean rCollaterInBean) {
        RCollaterInDialog dialog = new RCollaterInDialog(mContext, mServices);
        dialog.setDataBean(bean, rCollaterInBean);
        dialog.show();
    }
}
