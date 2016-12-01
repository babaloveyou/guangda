package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.*;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bll.BuyOrSellServiceImpl;

/**
 * 普通交易委托确认提示框
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/29
 */
public class ATradeComfirmDialog extends AbsTradeDialog {

    private boolean mShowWarning = false;
    /**
     * 是委托买入还是委托卖出
     */
    private int mBuyOrSale;


    /**
     * 调用方的业务类
     */
    private BuyOrSellServiceImpl mService;
    /**
     * 设置委托标准
     */
    private String mEntrustBs = "";
    private CodeTableBean mStockBean;

    private TextView mBuyOrSellTv;
    private TextView mAccountTv;
    private TextView mNameTv;
    private TextView mCodeTv;
    private TextView mEntrustPriceTv;
    private TextView mEntrustNumberTv;

    private TextView mWarnTv1;
    private TextView mWarnTv2;

    public ATradeComfirmDialog(Context context, int buyOrSale, boolean showWarning, BuyOrSellServiceImpl service) {
        super(context);
        mBuyOrSale = buyOrSale;
        mService = service;
        mShowWarning = showWarning;
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
        mWarnTv1 = (TextView) view.findViewById(R.id.tv_warn1);
        mWarnTv2 = (TextView) view.findViewById(R.id.tv_warn2);
        mAccountTv = (TextView) view.findViewById(R.id.tv_account);
        mNameTv = (TextView) view.findViewById(R.id.tv_name);
        mCodeTv = (TextView) view.findViewById(R.id.tv_stock_code);
        mEntrustPriceTv = (TextView) view.findViewById(R.id.tv_entrust_price);
        mEntrustNumberTv = (TextView) view.findViewById(R.id.tv_entrust_number);
        mBuyOrSellTv = (TextView) view.findViewById(R.id.tv_buyorsell);
        setSubViewToParent(view);
    }

    /**
     * 设置数据到对话框的控件中
     *
     * @param EntrustPrice
     * @param EntrustAmount
     */
    public void setDataToViews(CodeTableBean bean,String stockAccount, String EntrustPrice, String EntrustAmount) {
        if (mBuyOrSale == 0) { // 如果单击的是买入
            mBuyOrSellTv.setText("买入数量:    ");
            if (mShowWarning) {
                showWarningText(0);
            } else {
                hideWarnText();
            }
        } else if (mBuyOrSale == 1) { // 如果单机的是卖出
            mBuyOrSellTv.setText("买入数量:    ");
            if (mShowWarning) {
                showWarningText(1);
            } else {
                hideWarnText();
            }
        }
        mStockBean = bean;
        mAccountTv.setText(stockAccount);
        mNameTv.setText(mStockBean.getName());
        mCodeTv.setText(mStockBean.getCode());
        mEntrustPriceTv.setText(EntrustPrice);
        mEntrustNumberTv.setText(EntrustAmount);
    }

    private void hideWarnText() {
        mWarnTv1.setVisibility(View.GONE);
        mWarnTv2.setVisibility(View.GONE);
    }

    private void showWarningText(int i) {
        if (i == 0) {
            mWarnTv1.setText("买入数量大于最大可买,交易可能不会成功");
            mWarnTv2.setText("确认买入该证券?");
        } else {
            mWarnTv1.setText("卖出数量大于最大可卖,交易可能不会成功");
            mWarnTv2.setText("确认卖出该证券?");
        }
        mWarnTv1.setVisibility(View.VISIBLE);
        mWarnTv2.setVisibility(View.VISIBLE);
    }

    /**
     * 设置委托类别
     */
    public void setEntrustBs(String entrustBs) {
        mEntrustBs = entrustBs;
        if (!mEntrustBs.equals("0") && !mEntrustBs.equals("1")) {
        }
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mService.requestBuyOrSell(mStockBean, mEntrustBs);
        dismiss();
    }
}
