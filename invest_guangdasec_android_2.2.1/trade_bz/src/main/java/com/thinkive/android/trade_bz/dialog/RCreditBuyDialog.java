package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCreditBuyServiceImpl;

/**
 * 融资买入
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/20
 */
public class RCreditBuyDialog extends AbsTradeDialog {

    private boolean mShowWarning = false;
    private TextView mBuyOrSellTv;
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
    private RCreditBuyServiceImpl mService;
    private String mEntrustBs = "";
    private String entrustBsXjNum = "";
    private String limitOrMarketPriceFlag = "";

    public RCreditBuyDialog(Context context, RCreditBuyServiceImpl service,boolean showWarning) {
        super(context);
        mShowWarning = showWarning;
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
        mAccountTv = (TextView) view.findViewById(R.id.tv_account);
        mNameTv = (TextView) view.findViewById(R.id.tv_name);
        mCodeTv = (TextView) view.findViewById(R.id.tv_stock_code);
        mEntrustPriceTv = (TextView) view.findViewById(R.id.tv_entrust_price);
        mEntrustNumberTv = (TextView) view.findViewById(R.id.tv_entrust_number);
        mBuyOrSellTv = (TextView) view.findViewById(R.id.tv_buyorsell);
        mWarnTv1 = (TextView) view.findViewById(R.id.tv_warn1);
        mWarnTv2 = (TextView) view.findViewById(R.id.tv_warn2);
        setSubViewToParent(view);
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

    public void setDataToViews(RStockLinkBean stockLinkageBean, String entrustPrice, String entrustAmount) {
        if (mShowWarning) {
            showWarningText(0);
        } else {
            hideWarnText();
        }
        mBuyOrSellTv.setText("融资买入:    ");
        mAccountTv.setText(stockLinkageBean.getStock_account());
        mNameTv.setText(stockLinkageBean.getStock_name());
        mCodeTv.setText(stockLinkageBean.getStock_code());
        mEntrustPriceTv.setText(entrustPrice);
        mEntrustNumberTv.setText(entrustAmount);
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
