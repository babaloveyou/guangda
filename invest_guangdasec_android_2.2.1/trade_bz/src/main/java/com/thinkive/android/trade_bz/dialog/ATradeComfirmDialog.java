package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.*;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bll.BuyOrSellServiceImpl;

/**
 * 普通交易委托确认提示框
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/29
 */
public class ATradeComfirmDialog extends AbsTradeDialog {

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
    private BuyOrSellServiceImpl mService;
    /**
     * 设置委托标准
     */
    private String mEntrustBs = "";
    private CodeTableBean mStockBean;

    public ATradeComfirmDialog(Context context, int buyOrSale, BuyOrSellServiceImpl service) {
        super(context);
        mBuyOrSale = buyOrSale;
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
     * @param EntrustPrice
     * @param EntrustAmount
     */
    public void setDataToViews(CodeTableBean bean, String EntrustPrice, String EntrustAmount) {
        if (mBuyOrSale == 0) { // 如果单击的是买入
            buyOrSaleView.setText(R.string.trade_buying);
        } else if (mBuyOrSale == 1) { // 如果单机的是卖出
            buyOrSaleView.setText(R.string.trade_sell);
        }
        mStockBean = bean;
        stockNameTextView.setText(mStockBean.getName());
        stockCodeTextView.setText(mStockBean.getCode());
        entrustPriceTextView.setText(EntrustPrice);
        entrustNumTextView.setText(EntrustAmount);
    }

    /**
     * 设置委托类别
     */
    public void setEntrustBs(String entrustBs){
        mEntrustBs = entrustBs;
        if(!mEntrustBs.equals("0") && !mEntrustBs.equals("1")){
            entrustPriceTextView.setText(mContext.getResources().getString(R.string.trade_buy_rb1));
        }
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mService.requestBuyOrSell(mStockBean,mEntrustBs);
        dismiss();
    }
}
