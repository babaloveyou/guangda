package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_trans.bll.TransSubBuyOrSaleServiceImpl;

/**
 * 转股交易 定价申报买入卖出
 * @author 张雪梅
 * @corporation thinkive
 * @date 2016/5/26
 */
public class TransSubBuyOrSaleDialog extends AbsTradeDialog {
    /**
     * 是委托买入还是委托卖出
     */
    private int mBuyOrSale;
    /**
     * 股票名称
     */
    private TextView stockNameTextView;
    /**
     * 股票代码
     */
    private TextView stockCodeTextView;
    /**
     * 委托价格
     */
    private TextView entrustPriceTextView;
    /**
     * 委托数量
     */
    private TextView entrustNumTextView;
    /**
     * 调用方的业务类
     */
    private TransSubBuyOrSaleServiceImpl mService;

    public TransSubBuyOrSaleDialog(Context context, int mBuyOrSell,TransSubBuyOrSaleServiceImpl service) {
        super(context);
        this.mBuyOrSale = mBuyOrSell;
        mService = service;
        initDialogLayout();
        setLayout();
    }

    /**
     * 初始化对话框布局
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.dialog_entrust_buy);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_trade_comfirm, null);
        // 显示股票名称
        stockNameTextView = (TextView)view.findViewById(R.id.tv_pop_name);
        // 显示股票代码
        stockCodeTextView = (TextView)view.findViewById(R.id.tv_pop_code);
        //买卖方向
        // 显示委托价格
        entrustPriceTextView = (TextView)view.findViewById(R.id.tv_pop_price);
        // 显示委托数量
        entrustNumTextView = (TextView)view.findViewById(R.id.tv_pop_entrust_number);
        setSubViewToParent(view);
    }

    /**
     * 设置数据到对话框的控件中
     * @param stockName
     * @param stockCode
     * @param EntrustPrice
     * @param EntrustAmount
     */
    public void setDataToViews(String stockName, String stockCode, String EntrustPrice, String EntrustAmount) {
        stockNameTextView.setText(stockName);
        stockCodeTextView.setText(stockCode);
        entrustPriceTextView.setText(EntrustPrice);
        entrustNumTextView.setText(EntrustAmount);
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mService.requestBuyOrSell();
        dismiss();
    }
}
