package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

/**
 * 我的持仓列表适配器
 * 作用于基础交易页面的，资产选项卡中，
 * 另一个持仓列表Adapter是{@link MyStoreListViewAdapterForBuysale}
 *
 * @author 王志鸿
 * @version 1.0
 * @corporation 思迪信息技术有限公司
 * @date 2015/5/26
 */
public class MyStoreListViewAdapter extends AbsBaseAdapter<MyStoreStockBean> {

    private Context mContext;
    private String empString ;

    /**
     * item_store_listview_in_buysale
     *
     * @param context 外部调用环境
     */
    public MyStoreListViewAdapter(Context context) {
        super(context, R.layout.item_a_hold_stock_expand);
        mContext = context;
        setIsReuseView(true);
        empString= mContext.getResources().getString(R.string.common_emp_text);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, MyStoreStockBean bean) {
        TextView winLoseRateTv = (TextView) holder.getComponentById(R.id.tv_win_lose_rate);
        TextView winLoseNumTv = (TextView) holder.getComponentById(R.id.tv_win_lose_num);
        TextView costPriceTv = (TextView) holder.getComponentById(R.id.tv_cost_price);
        TextView currentPriceTv = (TextView) holder.getComponentById(R.id.tv_current_price);
        TextView storeTv = (TextView) holder.getComponentById(R.id.tv_store);
        TextView unUsebleTv = (TextView) holder.getComponentById(R.id.tv_usable);
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
        setEnableText(unUsebleTv, bean);
    }


    private void setEnableText(TextView view, MyStoreStockBean myStoreStockBean) {
        String enableText = "可用:" + myStoreStockBean.getEnable_amount();
        SpannableStringBuilder styleEnable = new SpannableStringBuilder(enableText);
        styleEnable.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleEnable.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.trade_text_color2)), 3, enableText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleEnable);
    }

    private void setStoreText(TextView view, MyStoreStockBean myStoreStockBean) {
        String storeText = "持仓:" + myStoreStockBean.getLast_price();
        SpannableStringBuilder styleStore = new SpannableStringBuilder(storeText);
        styleStore.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleStore.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.trade_text_color2)), 3, storeText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleStore);
    }

    private void setCurrentPrice(TextView view, MyStoreStockBean myStoreStockBean) {
        String currentPriceText = "现价:" + myStoreStockBean.getLast_price();
        SpannableStringBuilder styleCurrentPrice = new SpannableStringBuilder(currentPriceText);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.trade_text_color2)), 3, currentPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleCurrentPrice);
    }

    private void setCostPriceText(TextView view, MyStoreStockBean myStoreStockBean) {
        String costPriceText = "成本:" + myStoreStockBean.getCost_price();
        SpannableStringBuilder styleCostPrice = new SpannableStringBuilder(costPriceText);
        styleCostPrice.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCostPrice.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.trade_text_color2)), 3, costPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleCostPrice);
    }

    private void setWinLoseNumText(TextView view, MyStoreStockBean myStoreStockBean) {
        view.setText(myStoreStockBean.getFloat_yk());
        int sColor = -1;
        if (view.getText().toString().startsWith(empString)) {
            sColor = mContext.getResources().getColor(R.color.text_reming);
        } else {
            sColor = view.getText().toString().startsWith("-") ? mContext.getResources().getColor(R.color.trade_down_green) : mContext.getResources().getColor(R.color.trade_text);
        }
        view.setTextColor(sColor);
    }

    private void setWinLostRate(TextView view, MyStoreStockBean myStoreStockBean) {
        String winlostRate = myStoreStockBean.getFloat_yk_per();
        int sColor = -1;
        if (winlostRate.startsWith(empString)) {
            sColor = mContext.getResources().getColor(R.color.text_reming);
        } else {
            sColor = winlostRate.startsWith("-") ? mContext.getResources().getColor(R.color.trade_down_green) : mContext.getResources().getColor(R.color.trade_text);
        }
        String winLostRateTvString = "盈亏" + winlostRate + "%";
        SpannableStringBuilder styleWinLostRate = new SpannableStringBuilder(winLostRateTvString);
        styleWinLostRate.setSpan(new ForegroundColorSpan(sColor), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(styleWinLostRate);
    }

}
