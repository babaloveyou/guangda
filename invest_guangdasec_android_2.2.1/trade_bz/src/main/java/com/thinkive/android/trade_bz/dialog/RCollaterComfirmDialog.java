package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterBuyOrSaleServiceImpl;

/**
 * 担保品买入和卖出
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/20
 */
public class RCollaterComfirmDialog extends AbsTradeDialog {
    /**
     * 是委托买入还是委托卖出
     */
    private int mBuyOrSale;
    private TextView mBuyOrSellTv;
    private boolean mShowWarning = false;
    private TextView mAccountTv;
    private TextView mNameTv;
    private TextView mCodeTv;
    private TextView mEntrustPriceTv;
    private TextView mEntrustNumberTv;

    private TextView mWarnTv1;
    private TextView mWarnTv2;
    /**
     * 调用方的业务类
     */
    private RCollaterBuyOrSaleServiceImpl mService;
    private String mEntrustBs = "";
    private String entrustBsXjNum = "";
    private String limitOrMarketPriceFlag = "";

    public RCollaterComfirmDialog(Context context, int buyOrSale,boolean showWarning, RCollaterBuyOrSaleServiceImpl service) {
        super(context);
        mShowWarning = showWarning;
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

    public void setDataToViews(RStockLinkBean bean, String EntrustPrice, String EntrustAmount) {
        if (mBuyOrSale == 0) { // 如果单击的是买入
            mBuyOrSellTv.setText("买入数量:    ");
            if (mShowWarning) {
                showWarningText(0);
            } else {
                hideWarnText();
            }
        } else if (mBuyOrSale == 1) { // 如果单机的是卖出
            mBuyOrSellTv.setText("卖出数量:    ");
            if (mShowWarning) {
                showWarningText(1);
            } else {
                hideWarnText();
            }
        }
        String stock_account = bean.getStock_account();
        if (stock_account != null && stock_account.contains(",")) {
            stock_account = stock_account.split(",")[0];
        }
        mAccountTv.setText(stock_account);
        mNameTv.setText(bean.getStock_name());
        mCodeTv.setText(bean.getStock_name());
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
    public void setEntrustBs(String entrustBs,String limitOrMarketPriceFlag,String entrustBsXjNum){
        this.mEntrustBs = entrustBs;
        this.entrustBsXjNum = entrustBsXjNum;
        this.limitOrMarketPriceFlag = limitOrMarketPriceFlag;
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mService.requestBuyOrSell(mEntrustBs,limitOrMarketPriceFlag,entrustBsXjNum);
        dismiss();
    }
}
