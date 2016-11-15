package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bll.CreditBottomHolderServicesImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CreditBottomHoldLvAdapter extends AbsBaseAdapter<MyStoreStockBean> {
    private Context mSubContext;
    private CreditBottomHolderServicesImpl mService;

    public CreditBottomHoldLvAdapter(Context context, CreditBottomHolderServicesImpl services) {
        super(context, R.layout.item_lv_trade_bottom_hold);
        mSubContext = context;
        mService = services;
    }

    @Override
    protected void onFillComponentData(AbsBaseAdapter.ViewHolder holder, MyStoreStockBean bean) {
        //        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        //        nameTv.setText(bean.getStock_name());

        View indicatorView = (View) holder.getComponentById(R.id.view_indicator);
        TextView winLoseRateTv = (TextView) holder.getComponentById(R.id.tv_win_lose_rate);
        TextView winLoseNumTv = (TextView) holder.getComponentById(R.id.tv_win_lose_num);
        TextView costPriceTv = (TextView) holder.getComponentById(R.id.tv_cost_price);
        TextView currentPriceTv = (TextView) holder.getComponentById(R.id.tv_current_price);
        TextView storeTv = (TextView) holder.getComponentById(R.id.tv_store);
        TextView usebleTv = (TextView) holder.getComponentById(R.id.tv_usable);
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_name);
        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_code);
        TextView marketPriceTv = (TextView) holder.getComponentById(R.id.tv_market_price);


        nameTv.setText(bean.getStock_name());
        codeTv.setText(bean.getStock_code());
        marketPriceTv.setText("市值" + bean.getMarket_value() + "万");
        //盈亏百分比
        setWinLostRate(winLoseRateTv, bean);

        //盈亏数字
        setWinLoseNumText(winLoseNumTv, bean);
        //市价
        setCostPriceText(costPriceTv, bean);

        //现价
        setCurrentPrice(currentPriceTv, bean);

        //持仓

        setStoreText(storeTv, bean);


        //可用
        setEnableText(usebleTv, bean);
    }

    private void setEnableText(TextView view, MyStoreStockBean myStoreStockBean) {
        String enableText = "可用:" + myStoreStockBean.getEnable_amount();
        SpannableStringBuilder styleEnable = new SpannableStringBuilder(enableText);
        styleEnable.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleEnable.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, enableText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleEnable);
    }

    private void setStoreText(TextView view, MyStoreStockBean myStoreStockBean) {
        String storeText = "持仓:" + myStoreStockBean.getLast_price();
        SpannableStringBuilder styleStore = new SpannableStringBuilder(storeText);
        styleStore.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleStore.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, storeText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleStore);
    }

    private void setCurrentPrice(TextView view, MyStoreStockBean myStoreStockBean) {
        String currentPriceText = "现价:" + myStoreStockBean.getLast_price();
        SpannableStringBuilder styleCurrentPrice = new SpannableStringBuilder(currentPriceText);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, currentPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleCurrentPrice);
    }

    private void setCostPriceText(TextView view, MyStoreStockBean myStoreStockBean) {
        String costPriceText = "成本:" + myStoreStockBean.getCost_price();
        SpannableStringBuilder styleCostPrice = new SpannableStringBuilder(costPriceText);
        styleCostPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCostPrice.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.trade_text_color2)), 3, costPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleCostPrice);
    }

    private void setWinLoseNumText(TextView view, MyStoreStockBean myStoreStockBean) {
        view.setText(myStoreStockBean.getFloat_yk());
      view.setTextColor(view.getText().toString().startsWith("-") ?mSubContext.getResources().getColor(R.color.trade_down_green) : mSubContext.getResources().getColor(R.color.trade_text));
    }

    private void setWinLostRate(TextView view, MyStoreStockBean myStoreStockBean) {
        String winlostRate = myStoreStockBean.getFloat_yk_per();
        if (!TextUtils.isEmpty(winlostRate)&&!winlostRate.startsWith("-")) {
            winlostRate = "+" + winlostRate;
        }
        String winLostRateTvString = "盈亏" + winlostRate + "%";
        SpannableStringBuilder styleWinLostRate = new SpannableStringBuilder(winLostRateTvString);
        if (winlostRate.startsWith(mSubContext.getResources().getString(R.string.common_emp_text))) {
            styleWinLostRate.setSpan(new ForegroundColorSpan(mSubContext.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            styleWinLostRate.setSpan(new ForegroundColorSpan(winlostRate.startsWith("-") ? mSubContext.getResources().getColor(R.color.trade_down_green) : mSubContext.getResources().getColor(R.color.trade_text)), 2, winLostRateTvString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
      view.setText(styleWinLostRate);
    }
}
