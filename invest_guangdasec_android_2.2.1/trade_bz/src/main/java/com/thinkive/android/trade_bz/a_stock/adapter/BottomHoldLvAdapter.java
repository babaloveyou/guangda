package com.thinkive.android.trade_bz.a_stock.adapter;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bll.CreditBottomHolderServicesImpl;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */
public class BottomHoldLvAdapter extends BaseAdapter {
    private ArrayList<MyStoreStockBean> mDataList;
    private MultiTradeActivity mActivity;
    private CreditBottomHolderServicesImpl mServices;

    public BottomHoldLvAdapter(MultiTradeActivity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public MyStoreStockBean getItem(int position) {
        return mDataList != null ? mDataList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_lv_trade_bottom_hold, null);
            holder = new HoldViewHolder();
            holder.indicatorView = (View) convertView.findViewById(R.id.view_indicator);
            holder.winLoseRateTv = (TextView) convertView.findViewById(R.id.tv_win_lose_rate);
            holder.winLoseNumTv = (TextView) convertView.findViewById(R.id.tv_win_lose_num);
            holder.costPriceTv = (TextView) convertView.findViewById(R.id.tv_cost_price);
            holder.currentPriceTv = (TextView) convertView.findViewById(R.id.tv_current_price);
            holder.storeTv = (TextView) convertView.findViewById(R.id.tv_store);
            holder.unUsebleTv = (TextView) convertView.findViewById(R.id.tv_usable);
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv_name);
            holder.codeTv = (TextView) convertView.findViewById(R.id.tv_code);
            holder.marketPriceTv = (TextView) convertView.findViewById(R.id.tv_market_price);
            convertView.setTag(holder);
        } else {
            holder = (HoldViewHolder) convertView.getTag();
        }
        if (position == mDataList.size() - 1) {
            holder.indicatorView.setVisibility(View.INVISIBLE);
        } else {
            holder.indicatorView.setVisibility(View.VISIBLE);
        }
        MyStoreStockBean myStoreStockBean = mDataList.get(position);
        holder.nameTv.setText(myStoreStockBean.getStock_name());
        holder.codeTv.setText(myStoreStockBean.getStock_code());
        holder.marketPriceTv.setText("市值" + myStoreStockBean.getMarket_value() + "万");
        //盈亏百分比
        setWinLostRate(holder, myStoreStockBean);

        //盈亏数字
        setWinLoseNumText(convertView, holder, myStoreStockBean);
        //市价
        setCostPriceText(convertView, holder, myStoreStockBean);

        //现价
        setCurrentPrice(convertView, holder, myStoreStockBean);

        //持仓

        setStoreText(convertView, holder, myStoreStockBean);


        //可用
       setEnableText(convertView, holder, myStoreStockBean);
        return convertView;
    }

    private void setEnableText(View convertView, HoldViewHolder holder, MyStoreStockBean myStoreStockBean) {
        String enableText = "可用:" + myStoreStockBean.getEnable_amount();
        SpannableStringBuilder styleEnable = new SpannableStringBuilder(enableText);
        styleEnable.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleEnable.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.trade_text_color2)), 3, enableText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.unUsebleTv.setText(styleEnable);
    }

    private void setStoreText(View convertView, HoldViewHolder holder, MyStoreStockBean myStoreStockBean) {
        String storeText = "持仓:" + myStoreStockBean.getLast_price() ;
        SpannableStringBuilder styleStore = new SpannableStringBuilder(storeText);
        styleStore.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleStore.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.trade_text_color2)), 3, storeText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.storeTv.setText(styleStore);
    }

    private void setCurrentPrice(View convertView, HoldViewHolder holder, MyStoreStockBean myStoreStockBean) {
        String currentPriceText = "现价:" + myStoreStockBean.getLast_price();
        SpannableStringBuilder styleCurrentPrice = new SpannableStringBuilder(currentPriceText);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCurrentPrice.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.trade_text_color2)), 3, currentPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.currentPriceTv.setText(styleCurrentPrice);
    }

    private void setCostPriceText(View convertView, HoldViewHolder holder, MyStoreStockBean myStoreStockBean) {
        String costPriceText = "成本:" + myStoreStockBean.getCost_price() ;
        SpannableStringBuilder styleCostPrice = new SpannableStringBuilder(costPriceText);
        styleCostPrice.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleCostPrice.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.trade_text_color2)), 3, costPriceText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.costPriceTv.setText(styleCostPrice);
    }

    private void setWinLoseNumText(View convertView, HoldViewHolder holder, MyStoreStockBean myStoreStockBean) {
        holder.winLoseNumTv = (TextView) convertView.findViewById(R.id.tv_win_lose_num);
        holder.winLoseNumTv.setText(myStoreStockBean.getFloat_yk());
        holder.winLoseNumTv.setTextColor(mActivity.getResources().getColor(R.color.trade_text));
    }

    private void setWinLostRate(HoldViewHolder holder, MyStoreStockBean myStoreStockBean) {
        String winlostRate = myStoreStockBean.getFloat_yk_per();
        String winLostRateTvString = "盈亏" + winlostRate + "%";
        SpannableStringBuilder styleWinLostRate = new SpannableStringBuilder(winLostRateTvString);
        styleWinLostRate.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.text_reming)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styleWinLostRate.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.trade_text)), 2, winLostRateTvString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.winLoseRateTv.setText(styleWinLostRate);
    }

    public void setDataList(ArrayList<MyStoreStockBean> dataList) {
        if (mDataList == null) {

            mDataList = dataList;
        } else {
            mDataList.clear();
            mDataList.addAll(dataList);
        }
    }

    public ArrayList<MyStoreStockBean> getListData() {
        return mDataList;
    }

    class HoldViewHolder {
        View indicatorView;
        TextView winLoseRateTv;
        TextView winLoseNumTv;
        TextView costPriceTv;
        TextView currentPriceTv;
        TextView storeTv;
        TextView unUsebleTv;
        TextView nameTv;
        TextView codeTv;
        TextView marketPriceTv;
    }
}
