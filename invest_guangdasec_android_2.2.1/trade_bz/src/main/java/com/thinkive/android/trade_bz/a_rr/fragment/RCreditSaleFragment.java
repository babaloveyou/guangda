package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.ListenerController;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RCreditSaleActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectObjectSecurityActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.RBuyOrSaleContractAdapter;
import com.thinkive.android.trade_bz.a_rr.adapter.RBuyOrSaleObjectAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.bean.RStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCreditSaleServiceImpl;
import com.thinkive.android.trade_bz.a_rr.controll.RCreditSaleViewController;
import com.thinkive.android.trade_bz.a_stock.adapter.SearchStocksAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.controll.BuyOrSellViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.dialog.RCreditSaleDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.others.tools.StockFuzzyQueryManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 融券卖出
 * @author 张雪梅
 * @version 1.0
 * @corporation thinkive
 * @date 2016/1/20
 */
public class RCreditSaleFragment extends AbsBaseFragment {
    private RCreditSaleActivity mActivity = null;
    private RCreditSaleServiceImpl mService = null;
    private RCreditSaleViewController mController = null;
    private KeyboardManager mStockCodeEdKeyboardManager;
    private KeyboardManager mEntrustNumEDKeyboardManager;
    private StockFuzzyQueryManager mStockFuzzyQueryManager;
    private FontManager mFontManager;
    private Resources mResources;
    /**
     * 合约适配器
     */
    private RBuyOrSaleContractAdapter mContractAdapter;
    /**
     * 融券标的适配器
     */
    private RBuyOrSaleObjectAdapter mObjectAdapter;
    /**
     * 输入“证券代码”
     */
    private EditText mEdStockCode = null;
    /**
     * 输入“股票名称”
     */
    private TextView mTvStockName = null;
    /**
     * 证券的单位
     */
    private TextView mTvStockUnit = null;
    /**
     * 显示涨停价
     */
    private TextView mTvUpLimit = null;
    /**
     * 显示跌停价
     */
    private TextView mTvDownLimit = null;
    /**
     * 购买价 -
     */
    private TextView mTvSubtract = null;
    /**
     * 购买价 +
     */
    private TextView mTvAdd = null;
    /**
     * 股票购买的价格输入框
     */
    private EditText mEdStockPrice = null;
    /**
     * 委托交易数量输入框
     */
    private EditText mEdEntrustAmount = null;
    /**
     * 交易按钮，买入，或者卖出
     */
    private Button mBtnBuyOrSell = null;
    /**
     * fragment页面根布局
     */
    private View mRootView;
    // 五档买卖盘控件定义开始：买一到买五，卖一到卖五。价格和数量
    private TextView mPriceBuy5;
    private TextView mPriceBuy4;
    private TextView mPriceBuy3;
    private TextView mPriceBuy2;
    private TextView mPriceBuy1;
    private TextView mPriceSale5;
    private TextView mPriceSale4;
    private TextView mPriceSale3;
    private TextView mPriceSale2;
    private TextView mPriceSale1;
    private TextView mAmountBuy5;
    private TextView mAmountBuy4;
    private TextView mAmountBuy3;
    private TextView mAmountBuy2;
    private TextView mAmountBuy1;
    private TextView mAmountSale1;
    private TextView mAmountSale2;
    private TextView mAmountSale3;
    private TextView mAmountSale4;
    private TextView mAmountSale5;
    /**
     * 五档盘行的集合，里面的每个LinearLayout代表五档盘的一行
     */
    private ArrayList<LinearLayout> mBuySaleDiskLlList;
    // ***********************合约总布局*******************************
    private LinearLayout mLinContractLayout;
    private ListView mLvContract = null;
    private LinearLayout mLinContractNoData;
    private LinearLayout mLinContractLoading;
    // ***********************可容标的总布局*******************************
    private LinearLayout mLinTicketLayOut;
    private ListView mLvTicket= null;
    private LinearLayout mLinTicketNoData;
    private LinearLayout mLinTicketLoading;
    /**
     * 目前用来弹出模糊查询PopupWindow
     */
    private LinearLayout mLlStockCodeName;
    /**
     * 当前的股票联动数据bean
     */
    private RStockLinkBean mStockLinkageBean;
    /**
     * 上一次输入股票代码的长度
     */
    private int mLastInputStockCodeLength = 0;
    /**
     * 上一次的委托价格
     */
    private String mLastEntrustPrice = "";
    /**
     * 现价
     */
    private String mNowPrice = "";
    /**
     * 管理下方列表的切换
     */
    private RadioGroup mRgBuyOrSellList;
    /**
     * 融券合约
     */
    private RadioButton mRbContract;
    /**
     * 可容标的
     */
    private RadioButton mRbObject;
    /**
     * 设置到买卖按钮上的文字
     */
    private String mBtnBuyOrSaleText = "";
    /**
     * 从行情获取的证券信息类
     */
    private CodeTableBean mCodeTableBean    = null;
    /**
     * 开启定时器，每隔3秒刷新五档盘
     */
    private Handler handler  = new Handler();
    private Runnable runnable  = new Runnable() {
        public void run() {
            if (mCodeTableBean != null) {
                mService.requestStockWuDangPan(mCodeTableBean.getCode(),
                        mCodeTableBean.getMarket(), mCodeTableBean.getExchange_type(), false);
            }
            handler.postDelayed(this, 2000);
        }
    };

    public RCreditSaleFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_r_credit_sale, null);
            findViews(mRootView);
            initData();
            setListeners();
            initViews();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 初始化股票模糊查询popupwindow的宽度和单击事件
        mStockFuzzyQueryManager.setPopupwindowWidth(mLlStockCodeName.getWidth());
        mStockFuzzyQueryManager.setPopupWindowReserveWidthReferView(mLlStockCodeName);
        mStockFuzzyQueryManager.initListViewPopupwindow(mController);
        // 开启自动刷新模式
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //关闭定时刷新
        handler.removeCallbacks(runnable);
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mEdStockCode = (EditText) view.findViewById(R.id.edt_stock_code);
        mTvStockName = (TextView) view.findViewById(R.id.tv_stock_name);
        mTvStockUnit = (TextView) view.findViewById(R.id.tv_stock_unit);
        mTvUpLimit = (TextView) view.findViewById(R.id.tv_up_limit);
        mTvDownLimit = (TextView) view.findViewById(R.id.tv_down_limit);
        mTvSubtract = (TextView) view.findViewById(R.id.ibt_subtract);
        mTvAdd = (TextView) view.findViewById(R.id.ibt_add);
        mEdStockPrice = (EditText) view.findViewById(R.id.edt_stock_price);
        mEdEntrustAmount = (EditText) view.findViewById(R.id.ed_entrust_num);
        mBtnBuyOrSell = (Button) view.findViewById(R.id.btn_buy_or_sell);

        mLvContract = (ListView) view.findViewById(R.id.lv_show_contract);
        mLvContract.setDivider(null);
        mLinContractLayout = (LinearLayout) view.findViewById(R.id.lin_r_head_contract);
        mLinContractLoading = (LinearLayout) view.findViewById(R.id.lin_loading_contract);
        mLinContractNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_contract);

        mLvTicket = (ListView) view.findViewById(R.id.lv_show_object);
        mLvTicket.setDivider(null);
        mLinTicketLayOut = (LinearLayout) view.findViewById(R.id.lin_r_head_object);
        mLinTicketLoading = (LinearLayout) view.findViewById(R.id.lin_loading_object);
        mLinTicketNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_object);

        mLlStockCodeName = (LinearLayout) mRootView.findViewById(R.id.ll_code_name);
        mRgBuyOrSellList = (RadioGroup) view.findViewById(R.id.rg_r_buy_or_sale);
        mRbContract = (RadioButton) view.findViewById(R.id.rb_contract);
        mRbObject = (RadioButton) view.findViewById(R.id.rb_object);

        // 五档买卖盘中的LinearLayout
        LinearLayout tempLl = (LinearLayout) view.findViewById(R.id.ll_buy1);
        mBuySaleDiskLlList = new ArrayList<LinearLayout>();
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy2);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy3);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy4);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy5);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale1);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale2);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale3);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale4);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale5);
        mBuySaleDiskLlList.add(tempLl);

        // 五档买卖盘中LinearLayout中的TextView
        mPriceBuy5 = (TextView) mRootView.findViewById(R.id.tv_buy5_price);
        mPriceBuy4 = (TextView) mRootView.findViewById(R.id.tv_buy4_price);
        mPriceBuy3 = (TextView) mRootView.findViewById(R.id.tv_buy3_price);
        mPriceBuy2 = (TextView) mRootView.findViewById(R.id.tv_buy2_price);
        mPriceBuy1 = (TextView) mRootView.findViewById(R.id.tv_buy1_price);
        mPriceSale1 = (TextView) mRootView.findViewById(R.id.tv_sale1_price);
        mPriceSale2 = (TextView) mRootView.findViewById(R.id.tv_sale2_price);
        mPriceSale3 = (TextView) mRootView.findViewById(R.id.tv_sale3_price);
        mPriceSale4 = (TextView) mRootView.findViewById(R.id.tv_sale4_price);
        mPriceSale5 = (TextView) mRootView.findViewById(R.id.tv_sale5_price);
        mAmountBuy5 = (TextView) mRootView.findViewById(R.id.tv_buy5_amount);
        mAmountBuy4 = (TextView) mRootView.findViewById(R.id.tv_buy4_amount);
        mAmountBuy3 = (TextView) mRootView.findViewById(R.id.tv_buy3_amount);
        mAmountBuy2 = (TextView) mRootView.findViewById(R.id.tv_buy2_amount);
        mAmountBuy1 = (TextView) mRootView.findViewById(R.id.tv_buy1_amount);
        mAmountSale1 = (TextView) mRootView.findViewById(R.id.tv_sale1_amount);
        mAmountSale2 = (TextView) mRootView.findViewById(R.id.tv_sale2_amount);
        mAmountSale3 = (TextView) mRootView.findViewById(R.id.tv_sale3_amount);
        mAmountSale4 = (TextView) mRootView.findViewById(R.id.tv_sale4_amount);
        mAmountSale5 = (TextView) mRootView.findViewById(R.id.tv_sale5_amount);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_CLICK, mTvSubtract, mController);
        registerListener(ListenerController.ON_CLICK, mTvAdd, mController);
        registerListener(ListenerController.ON_CLICK, mBtnBuyOrSell, mController);
        registerListener(ListenerController.ON_CLICK, mTvStockName, mController);
        registerListener(ListenerController.ON_ITEM_CLICK, mLvContract, mController);
        registerListener(ListenerController.ON_ITEM_CLICK, mLvTicket, mController);
        registerListener(BuyOrSellViewController.ON_TEXT_CHANGE, mEdStockCode, mController);
        registerListener(ListenerController.ON_CLICK, mTvUpLimit, mController);
        registerListener(ListenerController.ON_CLICK, mTvDownLimit, mController);
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRgBuyOrSellList, mController);
        registerListener(BuyOrSellViewController.ON_FOCUS_CHANGE, mEdStockPrice, mController);

        for (LinearLayout ll : mBuySaleDiskLlList) {
            registerListener(ListenerController.ON_CLICK, ll, mController);
        }
        //价格框监听
        mEdStockPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(mService.mCount == 2){
                    TradeUtils.onLimitAfterPoint(mEdStockPrice, s, 5, mService.mCount);
                }else{
                    TradeUtils.onLimitAfterPoint(mEdStockPrice, s, 5, 3);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                onListenerEdtNum();
                requestLinkOnPriceChange();
            }
        });
        //数量输入框监听
        mEdEntrustAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                onListenerEdtNum();
            }
        });
    }

    @Override
    protected void initData() {
        mActivity = (RCreditSaleActivity) getActivity();
        mResources = CoreApplication.getInstance().getResources();
        mContractAdapter = new RBuyOrSaleContractAdapter(mActivity);
        mObjectAdapter = new RBuyOrSaleObjectAdapter(mActivity);
        mController = new RCreditSaleViewController(this);
        mFontManager = FontManager.getInstance(mActivity);
        mStockFuzzyQueryManager = StockFuzzyQueryManager.getInstance(mActivity);
        mService = new RCreditSaleServiceImpl(this);

        mBtnBuyOrSaleText = mResources.getString(R.string.hk_sell);
        mRgBuyOrSellList.setVisibility(View.VISIBLE);
        mLinContractLayout.setVisibility(View.VISIBLE);
        mEdStockPrice.setHint(mResources.getString(R.string.trade_hint_input_sale_price));
        mBtnBuyOrSell.setText(mBtnBuyOrSaleText);
        mLastEntrustPrice = "0";
        mService.getContract();
    }

    @Override
    protected void initViews() {
        mService.getObjectTicket();
        mStockCodeEdKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdStockCode, KeyboardManager.KEYBOARD_TYPE_STOCK, new TradeTools.OnFocusChangeWithKeyboard() {
            @Override
            public void onFocusChange(boolean hasFocus) {
                if (hasFocus) {
                    showOptionalList();
                }
            }
        }, new TradeTools.OnClickWithKeyboard() {
            @Override
            public void onClick() {
                showOptionalList();
            }
        });
        mEntrustNumEDKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdEntrustAmount, KeyboardManager.KEYBOARD_TYPE_MERCHANDISE);
        setTheme();
        // 设置字体
        mFontManager.setTextFont(mTvUpLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvDownLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mEdStockPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mEdEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mBtnBuyOrSell, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale1, FontManager.FONT_DINPRO_MEDIUM);
    }

    @Override
    protected void setTheme() {}

    /**
     * 当股票代码输入框{@link #mEdStockCode}中的输入内容改变的时候
     */
    public void onSearchTextChange() {
        // 当前股票代码输入框输入的内容
        String curEditContent = getEntrustCode();
        // 当前输入内容的长度
        int curInputLength = curEditContent.length();
        setLinkageState(false);
        // 没有输入完成时
        if (curInputLength > 1 && curInputLength < 6) {
            clearDataInViewsExpectStockCodeEd();
            //判断输入的是否是数字
            if (TradeUtils.isNumeric(curEditContent)) {
                //如果是数字（输入3位及以上开始模糊查询）
                if (curInputLength > 2 && curInputLength < 6) {
                    mStockFuzzyQueryManager.execQuery(curEditContent, "1", mEdStockCode);
                }
            } else { //如果是字母（输入2位及以上开始模糊查询）
                mStockFuzzyQueryManager.execQuery(curEditContent, "1", mEdStockCode);
            }
        }else if (curInputLength == 6) {
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            mStockCodeEdKeyboardManager.dismiss();
            // 主要校验是否是用户输入的代码
            if (mLastInputStockCodeLength == 5 || mLastInputStockCodeLength == 3) {
                onStockLengthMax(curEditContent,mEdStockCode);
            }
        } else {
            clearDataInViewsExpectStockCodeEd();
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
        }
        mLastInputStockCodeLength = curInputLength;
    }

    /**
     * 当用户输入满6位时
     * @param curEditContent
     * @param mEdStockCode
     */
    private void onStockLengthMax(String curEditContent,final View mEdStockCode){
        mStockFuzzyQueryManager.execQuery(curEditContent, "1",new StockFuzzyQueryManager.IHqCallBackStock(){
            @Override
            public void onGetStockMsg(ArrayList<CodeTableBean> dataList) {
                mStockFuzzyQueryManager.dismissQueryPopupWindow();
                if(dataList != null && dataList.size() > 0){
                    if(dataList.size() == 1){
                        CodeTableBean bean = dataList.get(0);
                        mService.request20000ForHqData(bean.getCode(),bean.getMarket());
                    }else{
                        SearchStocksAdapter adapter = mStockFuzzyQueryManager.getSearchStocksAdapter();
                        adapter.setListData(dataList);
                        adapter.notifyDataSetChanged();
                        mStockFuzzyQueryManager.showQueryPopupWindow(mEdStockCode);
                    }
                }else{
                    ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.toast_data_error));
                }
            }
        });
    }

    /**
     * 展示股票代码模糊查询结果的listview的item单击事件
     * @param position 被单击的item的position
     */
    public void onSearchListViewItemClick(int position) {
        CodeTableBean bean = mStockFuzzyQueryManager.getSearchStocksAdapter().getItem(position);
        mService.request20000ForHqData(bean.getCode(),bean.getMarket());
        TradeUtils.hideSystemKeyBoard(mActivity);
        mStockCodeEdKeyboardManager.dismiss();
        mLastInputStockCodeLength = 6;
    }

    /**
     * 点击合约
     * @param position 选中的item的序号
     */
    public void onContractItemClick(int position) {
        ArrayList<RChooseContractBean> dataList = mContractAdapter.getListData();
        RChooseContractBean bean = dataList.get(position);
        mService.request20000ForHqData(bean.getStock_code(),bean.getMarket());
    }

    /**
     * 点击可容标的
     * @param position 选中的item的序号
     */
    public void onTicketItemClick(int position) {
        ArrayList<RSelectCollaterSecurityBean> dataList = mObjectAdapter.getListData();
        RSelectCollaterSecurityBean bean = dataList.get(position);
        mService.request20000ForHqData(bean.getStock_code(),bean.getMarket());
    }

    /**
     * 当价格输入框的内容发生改变时，执行此方法
     */
    private void requestLinkOnPriceChange(){
        String entrustCode = getEntrustCode();
        String curEntrustPrice = getEntrustPrice();
        if(mStockLinkageBean != null && !TextUtils.isEmpty(entrustCode) &&
                !TextUtils.isEmpty(curEntrustPrice) && !mLastEntrustPrice.equals(curEntrustPrice)) {
            mService.requestLinkageData(entrustCode, curEntrustPrice,
                    mStockLinkageBean.getMarket(), mStockLinkageBean.getExchange_type());
            mLastEntrustPrice = curEntrustPrice;
        }
    }

    /**
     * 当数量或者价格发生变化时，改变button上的数据
     */
    private void onListenerEdtNum(){
        String price = getEntrustPrice();
        String num = getEntrustAmount();
        try {
            if (!TextUtils.isEmpty(price) && !TextUtils.isEmpty(num)) {
                double fPrice = Double.parseDouble(price);
                double fNum = Double.parseDouble(num);
                BigDecimal bigDecimal = new BigDecimal(fPrice * fNum);
                String plainPrice;
                if (mService.mCount == 2) {
                    plainPrice = TradeUtils.formatDouble2(bigDecimal.toPlainString());
                } else {
                    plainPrice = TradeUtils.formatDouble3(bigDecimal.toPlainString());
                }
                mBtnBuyOrSell.setText(mBtnBuyOrSaleText + mResources.getString(R.string.rmb) + plainPrice);
            }else{
                mBtnBuyOrSell.setText(mBtnBuyOrSaleText);
            }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * 点击radioButton (融券合约、可容标的)
     */
    public void onClickRadioButtonList(int checkId) {
        if (checkId == R.id.rb_contract) { //融券合约
            onClickRbContract();
        } else if (checkId == R.id.rb_object) { //可容标的
            onClickRbObject();
        }
    }

    /**
     * 选中融券合约
     */
    public void onClickRbContract() {
        mRbContract.setTextColor(mResources.getColor(R.color.trade_color_white));
        mRbObject.setTextColor(mResources.getColor(R.color.trade_sale));
        mLinContractLayout.setVisibility(View.VISIBLE);
        mLinTicketLayOut.setVisibility(View.GONE);
    }

    /**
     * 选中可容标的
     */
    public void onClickRbObject() {
        mRbObject.setTextColor(mResources.getColor(R.color.trade_color_white));
        mRbContract.setTextColor(mResources.getColor(R.color.trade_sale));
        mLinTicketLayOut.setVisibility(View.VISIBLE);
        mLinContractLayout.setVisibility(View.GONE);
    }
    /**
     * “涨停价”按钮单击监听事件
     */
    public void onClickUpLimit() {
        mEdStockPrice.setText(mTvUpLimit.getText().toString().trim());
    }

    /**
     * “跌停价”按钮单击事件监听
     */
    public void onClickDownLimit() {
        mEdStockPrice.setText(mTvDownLimit.getText().toString().trim());
    }

    /**
     * 交易价格+，按钮单击事件
     */
    public void onClickTradeAdd() {
        double curTradePrice = getTradePriceEditText();
        if (curTradePrice != 0) {
            if(mCodeTableBean != null){
                double step = Double.parseDouble(mCodeTableBean.getStep_unit());
                if(mService.mCount == 2){
                    mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice + step));
                }else{
                    mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice + step));
                }
            }else{
                mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice + 0.01));
            }
        }
    }

    /**
     * 交易价格-，按钮单击事件
     */
    public void onClickTradeSubstract() {
        double curTradePrice = getTradePriceEditText();
        if (curTradePrice != 0) {
            if(mCodeTableBean != null){
                double step = Double.parseDouble(mCodeTableBean.getStep_unit());
                if(mService.mCount == 2){
                    mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice - step));
                }else{
                    mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice - step));
                }
            }else{
                mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice- 0.01));
            }
        }
    }

    /**
     * 股票名称单击事件
     */
    public void onClickStockName() {
        // 注意！此处代码务必和 mEdStockCode 焦点获取监听事件一致
        if (mEdStockCode.hasFocus()) {
            showOptionalList();
            mStockCodeEdKeyboardManager.show();
        }else{
            mEdStockCode.performClick();
            mEdStockCode.requestFocus();
            showOptionalList();
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
        String entrustPrice = getEntrustPrice();
        String entrustAmount = getEntrustAmount();
        String stockCode = getEntrustCode();
        // 没正确输入股票代码时
        if (TextUtils.isEmpty(stockCode)) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_code));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        // 联动失败时
        if (mStockLinkageBean == null) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_code2));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        if (entrustAmount == null || entrustAmount.equals("")) { // 没输入委托数量时
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_amount));
            TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
            return;
        }
        if (entrustPrice == null || entrustPrice.equals("")) { // 没输入委托价格时
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_price));
            mEntrustNumEDKeyboardManager.dismiss();
            mStockCodeEdKeyboardManager.dismiss();
            TradeUtils.showKeyBoard(mActivity, mEdStockPrice, true);
            return;
        }
        try {
            double  entrustAmountDouble = Double.parseDouble(entrustAmount);
            double entrustMaxAmountDouble = Double.parseDouble(mStockLinkageBean.getStock_max_amount());
            // 如果是卖出，那么数量必须能被100整除，或者等于最大可卖数量，否则报错
            if (entrustAmountDouble % 100 != 0 && entrustAmountDouble != entrustMaxAmountDouble) {
                ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_sale_amount_error));
                TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
                return;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }catch (NullPointerException e1){
            e1.printStackTrace();
        }
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
        RCreditSaleDialog dialog = new RCreditSaleDialog(mActivity, mService);
        dialog.setDataToViews(mStockLinkageBean.getStock_name(),
                mStockLinkageBean.getStock_code(), getEntrustPrice(), getEntrustAmount());
        dialog.show();
    }

    /**
     * 点击五档盘
     * @param v
     */
    public void onClickBuyOrSaleDisk(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ll_buy1) {
            mEdStockPrice.setText(mPriceBuy1.getText().toString());
        } else if (viewId == R.id.ll_buy2) {
            mEdStockPrice.setText(mPriceBuy2.getText().toString());
        } else if (viewId == R.id.ll_buy3) {
            mEdStockPrice.setText(mPriceBuy3.getText().toString());
        } else if (viewId == R.id.ll_buy4) {
            mEdStockPrice.setText(mPriceBuy4.getText().toString());
        } else if (viewId == R.id.ll_buy5) {
            mEdStockPrice.setText(mPriceBuy5.getText().toString());
        } else if (viewId == R.id.ll_sale1) {
            mEdStockPrice.setText(mPriceSale1.getText().toString());
        } else if (viewId == R.id.ll_sale2) {
            mEdStockPrice.setText(mPriceSale2.getText().toString());
        } else if (viewId == R.id.ll_sale3) {
            mEdStockPrice.setText(mPriceSale3.getText().toString());
        } else if (viewId == R.id.ll_sale4) {
            mEdStockPrice.setText(mPriceSale4.getText().toString());
        } else if (viewId == R.id.ll_sale5) {
            mEdStockPrice.setText(mPriceSale5.getText().toString());
        }
        for (LinearLayout ll : mBuySaleDiskLlList) {
            if (viewId == ll.getId()) {
                ll.setBackgroundResource(R.drawable.common_no_corner_transparent);
            } else {
                ll.setBackgroundColor(mResources.getColor(R.color.transparent));
            }
        }
    }

    /**
     * 弹出popupwindow，显示自选股列表
     */
    public void showOptionalList() {
        if (TextUtils.isEmpty(getEntrustCode())) {
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            mStockFuzzyQueryManager.execQueryOptional();
            mStockFuzzyQueryManager.showQueryPopupWindow(mEdStockCode);
        }
    }

    /**
     * 监听价格输入框失去焦点事件，失去焦点时请求联动接口更新数据
     */
    public void onPriceEdtFocusChange(boolean hasFocus) {
        if (!hasFocus) {
            requestLinkOnPriceChange();
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
     * 在成功从服务器获得股票联动数据时
     * @param bean 装联动数据的实体类
     */
    public void onGetStockLinkAgeData(RStockLinkBean bean) {
        mEdEntrustAmount.setText("");
        mStockLinkageBean = bean;
        setLinkageState(true);
    }

    /**
     * 获取行情数据时
     * @param bean 装行情数据的实体类
     */
    public void onGetHqData(CodeTableBean bean) {
        if(mService.mCount == 4){ //债券的单位不一样
            mTvStockUnit.setText(mResources.getString(R.string.trade_stock2));
        }else{   // 股票或基金
            mTvStockUnit.setText(mResources.getString(R.string.trade_stock));
        }
        mCodeTableBean = bean;
        mEdStockCode.setText(bean.getCode());
        mTvStockName.setText(bean.getName());
        mTvUpLimit.setText(bean.getUpLimit());
        mTvDownLimit.setText(bean.getDownLimit());
        mNowPrice = bean.getNow();
    }

    /**
     * 请求行情接口发现股票停牌时
     */
    public void onGetSuspendStock(String stockName, String stockCode) {
        clearDataInViewsExpectStockCodeEd();
        mTvStockName.setText(stockName);
        mEdStockCode.setText(stockCode);
    }

    /**
     * 清空界面上的所有数字
     */
    public void clearDataInViews() {
        // 空字符串常量
        final String blankStr = "";
        // 清除股票代码输入框上的数据
        mEdStockCode.setText(blankStr);
        // 清除其他控件上的数据
        clearDataInViewsExpectStockCodeEd();
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 清除除了股票代码输入框外的其他布局控件上的数据
     */
    public void clearDataInViewsExpectStockCodeEd() {
        final String blankStr = "";
        mTvStockName.setText(blankStr);
        mTvUpLimit.setText(blankStr);
        mTvDownLimit.setText(blankStr);
        mEdStockPrice.setText(blankStr);
        mEdEntrustAmount.setText(blankStr);
        mNowPrice = blankStr;
        mLastEntrustPrice = blankStr;
        mStockLinkageBean = null;
        mCodeTableBean = null;
        mNowPrice = "";
        mLastEntrustPrice = "";
        mService.mCount = 2;
        mTvStockUnit.setText(mResources.getString(R.string.trade_stock));
        mBtnBuyOrSell.setText(mBtnBuyOrSaleText);
        // 清除五档盘中的数据
        clearWuDangData();
    }

    /**
     * 在成功从服务器获取五档买卖盘数据时调用
     * 设置五档买卖盘数据和当前委托价格数据（当前委托价格，买入时取买一，卖出时取卖一）。
     */
    public void onGetWuDangDishData(StockBuySellDish bean,String market,String exchangeType, boolean isSetText) {
        ArrayList<String> priceColorList = bean.getPriceColorsList();
        ArrayList<String> valueList = bean.getValueBuySale();
        setWuDangDataToViews(mPriceSale5, valueList.get(0), priceColorList.get(0));
        setWuDangDataToViews(mPriceSale4, valueList.get(1), priceColorList.get(1));
        setWuDangDataToViews(mPriceSale3, valueList.get(2), priceColorList.get(2));
        setWuDangDataToViews(mPriceSale2, valueList.get(3), priceColorList.get(3));
        setWuDangDataToViews(mPriceSale1, valueList.get(4), priceColorList.get(4));
        setWuDangDataToViews(mAmountSale5, valueList.get(5), priceColorList.get(5));
        setWuDangDataToViews(mAmountSale4, valueList.get(6), priceColorList.get(6));
        setWuDangDataToViews(mAmountSale3, valueList.get(7), priceColorList.get(7));
        setWuDangDataToViews(mAmountSale2, valueList.get(8), priceColorList.get(8));
        setWuDangDataToViews(mAmountSale1, valueList.get(9), priceColorList.get(9));
        setWuDangDataToViews(mPriceBuy1, valueList.get(10), priceColorList.get(10));
        setWuDangDataToViews(mPriceBuy2, valueList.get(11), priceColorList.get(11));
        setWuDangDataToViews(mPriceBuy3, valueList.get(12), priceColorList.get(12));
        setWuDangDataToViews(mPriceBuy4, valueList.get(13), priceColorList.get(13));
        setWuDangDataToViews(mPriceBuy5, valueList.get(14), priceColorList.get(14));
        setWuDangDataToViews(mAmountBuy1, valueList.get(15), priceColorList.get(15));
        setWuDangDataToViews(mAmountBuy2, valueList.get(16), priceColorList.get(16));
        setWuDangDataToViews(mAmountBuy3, valueList.get(17), priceColorList.get(17));
        setWuDangDataToViews(mAmountBuy4, valueList.get(18), priceColorList.get(18));
        setWuDangDataToViews(mAmountBuy5, valueList.get(19), priceColorList.get(19));
        //是否是自动刷新，如果是自动刷新，则不需要执行下面的逻辑
        if (isSetText) {
            String saleOne = valueList.get(10);
            if (saleOne != null && !TextUtils.isEmpty(saleOne)) {
                float saleFloat = Float.parseFloat(saleOne);
                if (saleFloat > 0) {
                    mLastEntrustPrice = saleOne;
                } else if (saleFloat <= 0) {
                    mLastEntrustPrice = mNowPrice;
                }
            } else {
                mLastEntrustPrice = mNowPrice;
            }
            mEdStockPrice.setText(mLastEntrustPrice);
            mService.requestLinkageData(getEntrustCode(), getEntrustPrice(), exchangeType, market);
        }
    }

    /**
     * 清空五档买卖盘和当前委托价格上的数据
     */
    public void clearWuDangData() {
        mPriceSale5.setText("");
        mPriceSale4.setText("");
        mPriceSale3.setText("");
        mPriceSale2.setText("");
        mPriceSale1.setText("");
        mAmountSale5.setText("");
        mAmountSale4.setText("");
        mAmountSale3.setText("");
        mAmountSale2.setText("");
        mAmountSale1.setText("");
        mPriceBuy1.setText("");
        mPriceBuy2.setText("");
        mPriceBuy3.setText("");
        mPriceBuy4.setText("");
        mPriceBuy5.setText("");
        mAmountBuy1.setText("");
        mAmountBuy2.setText("");
        mAmountBuy3.setText("");
        mAmountBuy4.setText("");
        mAmountBuy5.setText("");
        // 还原五档盘上TextView的颜色
        mPriceSale5.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale4.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale3.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale2.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale1.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mAmountSale5.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale4.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale3.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale2.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale1.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mPriceBuy1.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy2.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy3.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy4.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy5.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mAmountBuy1.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy2.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy3.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy4.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy5.setTextColor(mResources.getColor(R.color.trade_text_color9));
        // 清空委托价格数据
        mEdStockPrice.setText("");
    }

    /**
     * 根据入参设置本页面股票联动状态，本页面分两种状态，联动和不联动。
     * 联动时，涨停跌停价和五档买卖盘等控件可点击，并有附带效果；
     * 不联动时，或联动失败时，将上述控件设置为不可点击状态。
     * @param linkageState 设置股票是否联动；true：进入联动
     */
    private void setLinkageState(boolean linkageState) {
        try {
            // 设置五档盘每一行的可用状态
            for (LinearLayout ll : mBuySaleDiskLlList) {
                ll.setEnabled(linkageState);
                // 背景色设为透明
                ll.setBackgroundColor(mResources.getColor(R.color.transparent));
            }
            // 涨停、跌停,市价委托弹出框 按钮的可用状态
            mTvUpLimit.setEnabled(linkageState);
            mTvDownLimit.setEnabled(linkageState);
            if (linkageState && mStockLinkageBean != null) {
                // 设置持仓键盘仓位选择键功能
                if (!mEdStockPrice.hasFocus()) {
                    mEdEntrustAmount.requestFocus();
                    mEntrustNumEDKeyboardManager.show();
                }
                mEntrustNumEDKeyboardManager.setKeyCodeListener(new KeyboardManager.KeyCodeListener() {
                    @Override
                    public void onKeyCode(int i) {
                        switch (i) {
                            case -11: // 单击了1/4仓
                                setAmountPos(4);
                                break;
                            case -12: // 单击了1/3仓
                                setAmountPos(3);
                                break;
                            case -13: // 单击了半仓
                                setAmountPos(2);
                                break;
                            case -14: // 单击了全仓
                                setAmountPos(1);
                                break;
                        }
                    }
                });
                int max_amount = 1;
                max_amount = Integer.parseInt(mStockLinkageBean.getStock_max_amount());
                mEdEntrustAmount.setHint(mResources.getString(R.string.trade_hint_most_sale) + max_amount);
            } else {
                // 不能联动时，设置持仓键盘仓位选择键无效
                mEntrustNumEDKeyboardManager.setKeyCodeListener(new KeyboardManager.KeyCodeListener() {
                    @Override
                    public void onKeyCode(int i) {
                        mEdEntrustAmount.setText("");
                        ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_select_stock_first));
                    }
                });
                mEdEntrustAmount.setHint("");
                mEdEntrustAmount.setText("");
            }
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        }

    }

    /**
     * 设置单个数据到五档盘TextViews上面
     */
    private void setWuDangDataToViews(TextView textView, String data, String textColor) {
        textView.setText(data);
        if (!textColor.equals("")) {
            textView.setTextColor(Color.parseColor(textColor));
        }
    }

    /**
     * 仓位选择方法，当选择了相应仓位
     * @param pos 对应的1/pos仓位，例如，传入2时，就表示1/2仓
     */
    public void setAmountPos(int pos) {
        int max_amount = 1;
        String amountStr = "";
        try {
            max_amount = Integer.parseInt(mStockLinkageBean.getStock_max_amount());
            if( pos == 1){ //卖出时点击全仓
                amountStr = String.valueOf(max_amount);
            }else{
                amountStr = TradeUtils.formatNumToHundreds(max_amount / pos);
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        mEdEntrustAmount.setText(amountStr);
    }
    /**
     * 得到合约列表数据
     */
    public void onGetContract(ArrayList<RChooseContractBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLvContract.setVisibility(View.GONE);
            mLinContractNoData.setVisibility(View.VISIBLE);
            mLinContractLoading.setVisibility(View.GONE);
        } else {
            mLinContractLoading.setVisibility(View.GONE);
            mLvContract.setVisibility(View.VISIBLE);
            mLinContractNoData.setVisibility(View.GONE);
            mContractAdapter.setListData(dataList);
            mLvContract.setAdapter(mContractAdapter);
        }
    }

    /**
     * 得到可容标的
     */
    public void onGetObject(ArrayList<RSelectCollaterSecurityBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLvTicket.setVisibility(View.GONE);
            mLinTicketNoData.setVisibility(View.VISIBLE);
            mLinTicketLoading.setVisibility(View.GONE);
        } else {
            mLinTicketLoading.setVisibility(View.GONE);
            mLvTicket.setVisibility(View.VISIBLE);
            mLinTicketNoData.setVisibility(View.GONE);
            mObjectAdapter.setListData(dataList);
            mLvTicket.setAdapter(mObjectAdapter);
        }
    }

    /**
     * 点击title栏右侧 “查询” 字样
     */
    public void onClickTitleRight(){
        Intent intent = new Intent(mActivity, RSelectObjectSecurityActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 获得当前股票信息
     * @return
     */
    public RStockLinkBean getStockLinkageBean() {
        return mStockLinkageBean;
    }

    /**
     * 获取当前委托交易（买入或者卖出）的价格
     * @return {@link #mEdStockPrice}中输入的价格
     */
    public String getEntrustPrice() {
        float result = 0;
        String entrustPrice = "";
        String priceEditTextContent = mEdStockPrice.getText().toString().trim();
        if (!priceEditTextContent.equals("")) {
            try {
                result = Float.parseFloat(priceEditTextContent);
                if(result <= 0){
                    entrustPrice = "0";
                }else {
                    entrustPrice = String.valueOf(result);
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            } catch (NullPointerException nfe) {
                nfe.printStackTrace();
            }
        }
        return entrustPrice;
    }

    /**
     * 获取{@link #mEdStockPrice}中的文本信息
     * @return {@link #mEdStockPrice}中的文本信息
     */
    private double getTradePriceEditText() {
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
     * 获取当前委托交易（买入或者卖出）的交易数量
     * @return {@link #mEdEntrustAmount}中输入的数量
     */
    public String getEntrustAmount() {
        return mEdEntrustAmount.getText().toString().trim();
    }

    public String getEntrustCode() {
        return mEdStockCode.getText().toString().trim();
    }
}