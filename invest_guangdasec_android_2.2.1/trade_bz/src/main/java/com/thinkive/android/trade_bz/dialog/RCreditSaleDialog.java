package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bll.RCreditSaleServiceImpl;

/**
 * 融券卖出
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/20
 */
public class RCreditSaleDialog extends AbsTradeDialog {
    /**
     * 股票名称
     */
    private TextView stockNameTextView;
    /**
     * 股票代码
     */
    private TextView stockCodeTextView;
    /**
     * 买卖方向
     */
    private TextView buyOrSaleView;
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
    private RCreditSaleServiceImpl mService;

    public RCreditSaleDialog(Context context, RCreditSaleServiceImpl service) {
        super(context);
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
        buyOrSaleView = (TextView) view.findViewById(R.id.tv_pop_buy);
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
        buyOrSaleView.setText(R.string.trade_sell);
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
