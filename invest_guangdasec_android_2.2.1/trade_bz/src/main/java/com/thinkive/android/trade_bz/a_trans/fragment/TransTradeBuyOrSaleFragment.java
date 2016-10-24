package com.thinkive.android.trade_bz.a_trans.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.ListenerController;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.controll.BuyOrSellViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.activity.TransTradeBuyOrSaleActivityTrade;
import com.thinkive.android.trade_bz.a_trans.adapter.TransTradeBuyOrSaleAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransStockLinkBean;
import com.thinkive.android.trade_bz.a_trans.bean.TransSubHqBean;
import com.thinkive.android.trade_bz.a_trans.bll.TransTradeBuyOrSaleServiceImpl;
import com.thinkive.android.trade_bz.a_trans.controll.TransTradeBuyOrSellViewController;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 转股交易 成交确认买入、成交确认卖出
 * @author 张雪梅
 * @corporation thinkive
 * @date 2015/12/31
 */
public class TransTradeBuyOrSaleFragment extends AbsBaseFragment {
    private TransTradeBuyOrSaleActivityTrade mActivity = null;
    private TransTradeBuyOrSaleServiceImpl mService = null;
    private TransTradeBuyOrSellViewController mController = null;
    private TransTradeBuyOrSaleAdapter mAdapter;
    private KeyboardManager mStockCodeEdKeyboardManager;
    private Resources mResources;
    /**
     * 输入“证券代码”
     */
    private EditText mEdStockCode = null;
    /**
     * 股票名称
     */
    private TextView mTvStockName = null;
    /**
     * 价格加
     */
    private TextView mTvPriceAdd = null;
    /**
     * 价格减
     */
    private TextView mTvPriceCut = null;
    /**
     * 数量加
     */
    private TextView mTvNumAdd = null;
    /**
     * 数量减
     */
    private TextView mTvNumCut = null;
    /**
     * 最大可用
     */
    private TextView mTvMaxCanUse = null;
    /**
     * 最大可用描述
     */
    private TextView mTvMaxStr = null;
    /**
     * 股票购买的价格输入框
     */
    private EditText mEdStockPrice = null;
    /**
     * 委托交易数量输入框
     */
    private EditText mEdEntrustAmount = null;
    /**
     * 约定号
     */
    private EditText mEdYueNum = null;
    /**
     * 交易按钮，买入，或者卖出
     */
    private Button mBtnBuyOrSell = null;
    /**
     * 储存我的持仓数据的LisView
     */
    private ListView mLvMyStore = null;
    /**
     * 下方列表的头部
     */
    private LinearLayout mLinListHead;
    /**
     * 买入或者卖出，买入：0，卖出：1
     * 请在本类构造方法执行完后就给这个变量赋值！
     */
    private int mBuyOrSell;
    /**
     * 上一次联动的委托价格
     */
    private String mLastEntrustPrice = "";
    /**
     * 从行情获取的证券实体类
     */
    private CodeTableBean mCodeTableBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trans_trade_buy_or_sale, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onPause() {
        super.onPause();
        TradeUtils.hideSystemKeyBoard(mActivity);
        //清除数据，避免影响下次操作
        Bundle bundle = getArguments();
        if(bundle != null){
            bundle.remove("stock_code");
            bundle.remove("yu_num");
        }
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mStockCodeEdKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mEdStockCode = (EditText) view.findViewById(R.id.edt_trans_trade_code);
        mTvStockName = (TextView) view.findViewById(R.id.tv_trans_trade_name);
        mEdStockPrice = (EditText) view.findViewById(R.id.edt_trans_trade_price);
        mEdEntrustAmount = (EditText) view.findViewById(R.id.edt_trans_trade_num);
        mEdYueNum = (EditText)view.findViewById(R.id.edt_trans_trade_yue_num);
        mTvPriceCut = (TextView) view.findViewById(R.id.tv_trans_price_cut);
        mTvPriceAdd = (TextView) view.findViewById(R.id.tv_trans_price_add);
        mTvNumCut = (TextView) view.findViewById(R.id.tv_trans_num_cut);
        mTvNumAdd = (TextView) view.findViewById(R.id.tv_trans_num_add);
        mTvMaxCanUse = (TextView) view.findViewById(R.id.tv_trans_trade_max);
        mTvMaxStr = (TextView)view.findViewById(R.id.tv_trans_max_text);
        mBtnBuyOrSell = (Button) view.findViewById(R.id.btn_trans_trade_commit);
        mLvMyStore = (ListView) view.findViewById(R.id.lv_show_stock);
        mLvMyStore.setDivider(null);
        mLinListHead = (LinearLayout) view.findViewById(R.id.lin_trans_trade_head);
    }

    @Override
    protected void initData() {
        mActivity = (TransTradeBuyOrSaleActivityTrade) getActivity();
        mService = new TransTradeBuyOrSaleServiceImpl(this);
        mResources = CoreApplication.getInstance().getResources();
        mController = new TransTradeBuyOrSellViewController(this);
        mAdapter = new TransTradeBuyOrSaleAdapter(mActivity);
        mStockCodeEdKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdStockCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_CLICK, mTvPriceCut, mController);
        registerListener(ListenerController.ON_CLICK, mTvPriceAdd, mController);
        registerListener(ListenerController.ON_CLICK, mTvNumCut, mController);
        registerListener(ListenerController.ON_CLICK, mTvNumAdd, mController);
        registerListener(ListenerController.ON_CLICK, mBtnBuyOrSell, mController);
        registerListener(ListenerController.ON_ITEM_CLICK, mLvMyStore, mController);
        registerListener(BuyOrSellViewController.ON_FOCUS_CHANGE, mEdStockPrice, mController);
        //委托价格输入监听
        mEdStockPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                onPriceChangeLink();
            }
        });
        //股票代码输入监听
        mEdStockCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 6) {
                    mService.startLinkage(s.toString());
                } else {
                    clearDataInViewsExpectStockCodeEd();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    @Override
    protected void initViews() {
        if (mBuyOrSell == 0) { // 如果是买入
            mService.setEntrust_bs("1g");
            mTvMaxStr.setText(mResources.getString(R.string.trans_buy_or_sale9));
            mBtnBuyOrSell.setText(mResources.getString(R.string.trans_buy_or_sale15));

            mBtnBuyOrSell.setBackgroundResource(R.drawable.buy_all_corner_shi);
            mTvPriceCut.setBackgroundResource(R.drawable.buy_all_corner_shi);
            mTvPriceAdd.setBackgroundResource(R.drawable.buy_all_corner_shi);
            mTvNumCut.setBackgroundResource(R.drawable.buy_all_corner_shi);
            mTvNumAdd.setBackgroundResource(R.drawable.buy_all_corner_shi);

        } else if (mBuyOrSell == 1) { // 如果是卖出
            mService.setEntrust_bs("1h");
            mTvMaxStr.setText(mResources.getString(R.string.trans_buy_or_sale10));
            mBtnBuyOrSell.setText(mResources.getString(R.string.trans_buy_or_sale16));

            mBtnBuyOrSell.setBackgroundResource(R.drawable.sale_all_corner_shi);
            mTvPriceCut.setBackgroundResource(R.drawable.sale_all_corner_shi);
            mTvPriceAdd.setBackgroundResource(R.drawable.sale_all_corner_shi);
            mTvNumCut.setBackgroundResource(R.drawable.sale_all_corner_shi);
            mTvNumAdd.setBackgroundResource(R.drawable.sale_all_corner_shi);

            //有可能是TransHqThreeFragment跳转至此
            Bundle bundle = getArguments();
            if(bundle != null){
                String stockCode = bundle.getString("stock_code");
                String yuNum = bundle.getString("yu_num");
                if(!TextUtils.isEmpty(stockCode)){
                    mEdStockCode.setText(stockCode);
                }
            }
        }
        setTheme();
    }

    @Override
    protected void setTheme() {}

    /**
     * 持仓列表项单击事件监听
     * @param position
     */
    public void onStoreListViewItemClick(int position) {
        ArrayList<TransSubHqBean> dataList = mAdapter.getListData();
        TransSubHqBean bean = dataList.get(position);
        mEdYueNum.setText(bean.getConfernum());
        mEdStockPrice.setText(bean.getOrderprice());
    }

    /**
     * 交易价格+，按钮单击事件
     */
    public void onClickPriceAdd() {
        double curTradePrice = getDoubleEntrustPrice();
        if (curTradePrice != 0) {
            mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice + 0.01));
        }
    }

    /**
     * 交易价格-，按钮单击事件
     */
    public void onClickPriceCut() {
        double curTradePrice = getDoubleEntrustPrice();
        if (curTradePrice != 0) {
            mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice - 0.01));
        }
    }

    /**
     * 交易数量+，按钮单击事件
     */
    public void onClickNumAdd() {
        double curTradeNum = getDoubleEntrustNum();
        if (curTradeNum != 0) {
            mEdEntrustAmount.setText(TradeUtils.formatDouble2(curTradeNum + 100));
        }
    }

    /**
     * 交易数量-，按钮单击事件
     */
    public void onClickNumCut() {
        double curTradeNum = getDoubleEntrustNum();
        if (curTradeNum != 0 && curTradeNum > 100) {
            mEdEntrustAmount.setText(TradeUtils.formatDouble2(curTradeNum - 100));
        }
    }
    /**
     * 买入或者卖出按钮单击事件
     * 弹出确认操作对话框对话框，设置对话框布局，然后监听对话框中按钮的单击事件
     */
    public void onClickTrade() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String code = getEntrustCode();
        String entrustPrice = getEntrustPrice();
        String entrustAmount = getEntrustAmount();
        String yueNum = getYueNum();
        //没有正确输入股票代码
        if(TextUtils.isEmpty(code) || code.length() != 6){
            ToastUtils.toast(mActivity, mResources.getString(R.string.trans_hold11));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        // 没有获取到证券信息
        if (mCodeTableBean == null) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trans_hold12));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        // 没输入委托数量时
        if (entrustAmount == null || entrustAmount.equals("")) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trans_hold10));
            TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
            return;
            // 没输入委托价格时
        } else if (entrustPrice == null || entrustPrice.equals("")) {
            ToastUtils.toast(mActivity,mResources.getString(R.string.trans_hold9));
            mStockCodeEdKeyboardManager.dismiss();
            TradeUtils.showKeyBoard(mActivity, mEdStockPrice, true);
            return;
            // 没填写约定号时
        } else if(yueNum == null || yueNum.equals("")){
            ToastUtils.toast(mActivity,mResources.getString(R.string.trans_stock10));
            mStockCodeEdKeyboardManager.dismiss();
            TradeUtils.showKeyBoard(mActivity, mEdYueNum, true);
            return;
        }
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
        mService.requestBuyOrSell();
    }

    /**
     * 当委托价格发生改变时，联动最大可用量
     * 只有在买入时，证券有效时
     */
    public void onPriceChangeLink(){
        String curPrice = getEntrustPrice();
        if(mBuyOrSell == 0 && mCodeTableBean != null &&
                !TextUtils.isEmpty(curPrice) && !curPrice.equals(mLastEntrustPrice)){
            mService.requestLinkageData(mCodeTableBean.getCode(),curPrice);
            mLastEntrustPrice = curPrice;
        }
    }
    /**
     * 监听价格输入框失去焦点事件，失去焦点时请求联动接口更新数据
     */
    public void onPriceEdtFocusChange(boolean hasFocus) {
        if (!hasFocus) {
            onPriceChangeLink();
        }
    }
    /**
     * 从服务器返回了委托交易（买入或卖出）成功时执行
     */
    public void onSuccessEntrustTrade(String resultStr) {
        MessageOkCancelDialog dialog = new MessageOkCancelDialog(mActivity, null);
        dialog.setMsgText(resultStr);
        dialog.setCancelBtnVisiable(false);
        dialog.show();
    }

    /**
     * 联动成功，获取到最大买用量
     * @param data
     * 装联动数据的实体类
     */
    public void onGetStockLinkAgeData(TransStockLinkBean data) {
        if(data != null) {
            mTvMaxCanUse.setText(data.getStock_max_amount());
            if (!mEdStockPrice.hasFocus()) {
                mEdEntrustAmount.requestFocus();
                mStockCodeEdKeyboardManager.dismiss();
                TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, true);
            }
        }
    }

    /**
     * 获取行情数据时
     * @param bean
     * 装行情数据的实体类
     */
    public void onGetHqData(CodeTableBean bean) {
        mCodeTableBean = bean;
        mTvStockName.setText(bean.getName());
        mEdStockPrice.setText(TradeUtils.formatDouble2(bean.getNow()));
    }

    /**
     * 请求行情接口发现股票停牌时
     */
    public void onGetSuspendStock(String stockName) {
        clearDataInViewsExpectStockCodeEd();
        mTvStockName.setText(stockName);
    }

    /**
     * 清空界面上的所有数字
     */
    public void clearDataInViews() {
        clearDataInViewsExpectStockCodeEd();
        // 清除股票代码输入框上的数据
        mEdStockCode.setText("");
    }

    /**
     * 找不到股票，股票代码输入错误
     */
    public void clearDataInViewsExpectStockCodeEd() {
        mCodeTableBean = null;
        mTvStockName.setText("");
        mTvMaxCanUse.setText(mResources.getString(R.string.no_text_set));
        mEdStockPrice.setText("");
        mEdEntrustAmount.setText("");
        mEdYueNum.setText("");
        mLvMyStore.setVisibility(View.INVISIBLE);
        mLinListHead.setVisibility(View.INVISIBLE);
    }
    /**
     * 获取传过来的持仓数据
     * @param dataList
     * 持仓列表数据表
     */
    public void getStoreData(ArrayList<TransSubHqBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLvMyStore.setVisibility(View.INVISIBLE);
            mLinListHead.setVisibility(View.INVISIBLE);
        } else {
            mLvMyStore.setVisibility(View.VISIBLE);
            mLinListHead.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mLvMyStore.setAdapter(mAdapter);
        }
    }

    /**
     * 区分买入卖出的标志
     * @param buyOrSell
     */
    public void setBuyOrSell(int buyOrSell) {
        mBuyOrSell = buyOrSell;
    }
    /**
     *获取委托价格，返回double类型
     */
    private double getDoubleEntrustPrice() {
        double result = 0;
        String priceEditTextContent = mEdStockPrice.getText().toString().trim();
        if (!priceEditTextContent.equals("")) {
            try {
                result = Double.parseDouble(priceEditTextContent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取委托数量，返回double类型
     */
    private double getDoubleEntrustNum() {
        double result = 0;
        String priceEditTextContent = mEdEntrustAmount.getText().toString().trim();
        if (!priceEditTextContent.equals("")) {
            try {
                result = Double.parseDouble(priceEditTextContent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取当前委托交易（买入或者卖出）的价格
     */
    public String getEntrustPrice() {
        return mEdStockPrice.getText().toString().trim();
    }

    /**
     * 获取当前交易数量
     */
    public String getEntrustAmount() {
        return mEdEntrustAmount.getText().toString().trim();
    }

    /**
     * 获取当前股票代码
     * @return
     */
    public String getEntrustCode() {
        return mEdStockCode.getText().toString().trim();
    }

    /**
     * 获取当前约定号
     * @return
     */
    public String getYueNum() {
        return mEdYueNum.getText().toString().trim();
    }
}
