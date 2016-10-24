package com.thinkive.android.trade_bz.a_hk.fragment;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.adapter.HkHoldListForBuySaleAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKHoldStockBean;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockLinkBean;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockMessageBean;
import com.thinkive.android.trade_bz.a_hk.bll.HKBuySellServiceImpl;
import com.thinkive.android.trade_bz.a_hk.controll.HKBuySellViewController;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.controll.BuyOrSellViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.dialog.HKTradeConfirmDialog;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.others.tools.StockFuzzyQueryManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 买入、卖出共享的一个Fragment。
 * 通过{@link #mBuyOrSell}来区分到底是买入还是卖出
 * @author 王志鸿
 * @version 1.0
 * @corporation thinkive
 * @date 2015/6/5
 */
public class HKBuySellFragment extends AbsBaseFragment {
    /**
     * 输入“证券代码”
     */
    private EditText mEdStockCode = null;
    /**
     * 输入“股票名称”
     */
    private TextView mTvStockName = null;
    /**
     * 显示汇率
     */
    private TextView mTvExchangeRate = null;
    /**
     * 每手
     */
    private TextView mTvOneUnit = null;
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
     * 储存我的持仓数据的LisView
     */
    private ListView mLvMyStore = null;
    /**
     * fragment页面根布局
     */
    private View mRootView;
    /**
     * 一档盘 买价
     */
    private TextView mPriceBuy1;
    /**
     *一档盘 卖价
     */
    private TextView mPriceSale1;
    /**
     *一档盘 买量
     */
    private TextView mAmountBuy1;
    /**
     *一档盘 买量
     */
    private TextView mAmountSale1;
    /**
     *一档盘 买布局
     */
    private LinearLayout mLinBuyLay;
    /**
     *一档盘 买布局
     */
    private LinearLayout mLinSaleLay;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLoading;
    /**
     * 用来弹出模糊查询PopupWindow 参照物
     */
    private LinearLayout mLlStockCodeName;
    /**
     * 该类的宿主Activity
     */
    private TKFragmentActivity mActivity = null;
    /**
     * 该类的业务类
     */
    private HKBuySellServiceImpl mService = null;
    /**
     * 控制该类的控制器
     */
    private HKBuySellViewController mController = null;
    /**
     * 我的持仓数据的LisView的适配器
     */
    private HkHoldListForBuySaleAdapter mMyStoreListViewAdapter;
    /**
     * 买入或者卖出，买入：0，卖出：1
     */
    private int mBuyOrSell = 0;
    /**
     * 设置在买卖按钮上的文字
     */
    private String mBtnBuyOrSaleText = "";
    /**
     * 当前的股票联动数据 bean
     */
    private HKStockLinkBean mStockLinkageBean;
    /**
     * 框架内的原生自绘键盘管理器，股票代码输入框{@link #mEdStockCode}使用
     */
    private KeyboardManager mStockCodeEdKeyboardManager;
    /**
     * 框架内的原生自绘键盘管理器，委托数量输入框{@link #mEdEntrustAmount}使用
     */
    private KeyboardManager mEntrustNumEDKeyboardManager;
    /**
     * 字体管理器，用于给本类界面上的文字或数字设置字体
     */
    private FontManager mFontManager;
    /**
     * 股票模糊查询管理器，用于管理一个popupwindow，
     * 这个popupwindow是用来展示股票代码的模糊查询结果的
     */
    private StockFuzzyQueryManager mStockFuzzyQueryManager;
    /**
     * 上一次的委托价格
     */
    private String mLastEntrustPrice = "";
    /**
     * 全局资源对象
     */
    private Resources mResources;
    /**
     * 股票信息的实体类
     */
    private HKStockMessageBean mStockMsgBean;
    /**
     * 上一次输入股票代码的长度
     */
    private int mLastInputStockCodeLength = 0;
    /**
     * 限价委托按钮
     */
    private TextView  mTvTradeLimit;
    /**
     * 市价委托按钮
     */
    private TextView  mTvTradeMarket;
    /**
     * 限价委托选中小图标
     */
    private ImageView mIvLeftSelected;
    /**
     * 市价委托选中小图标
     */
    private ImageView mIvRightSelected;
    /**
     * 股票代码
     */
    private String mStockCode = "";
    /**
     * 开启定时器，每隔4秒刷新持仓
     */
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run () {
            if (!TradeUtils.isFastClick()) {
                mService.getHoldList();
            }
            if (mStockMsgBean != null) {
                mService.startLinkage(mStockMsgBean.getCode(),true);
            }
            handler.postDelayed(this,2000);
        }
    };

    public HKBuySellFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_hk_buy_or_sell, null);
            initData();
            findViews(mRootView);
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
        setDataToViewsFromHoldList();
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        //清除从行情或者持仓跳转至此的数据，避免影响下一次操作
        Bundle bundle = getArguments();
        if(bundle != null){
            bundle.remove("code");
            bundle.remove("buyOrSale");
        }
        //清除所有数据
        clearDataInViews();
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
        mTvExchangeRate = (TextView) view.findViewById(R.id.tv_exchange_rate);
        mTvOneUnit = (TextView) view.findViewById(R.id.tv_amount);
        mTvSubtract = (TextView) view.findViewById(R.id.ibt_subtract);
        mTvAdd = (TextView) view.findViewById(R.id.ibt_add);
        mEdStockPrice = (EditText) view.findViewById(R.id.edt_stock_price);
        mEdEntrustAmount = (EditText) view.findViewById(R.id.ed_entrust_num);
        mBtnBuyOrSell = (Button) view.findViewById(R.id.btn_buy_or_sell);
        mLvMyStore = (ListView) view.findViewById(R.id.lv_show_stock);
        mLvMyStore.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.ll_buy_or_sell_list_loading);
        mLiNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLlStockCodeName = (LinearLayout) mRootView.findViewById(R.id.ll_code_name);

        mTvTradeLimit = (TextView) view.findViewById(R.id.tv_trade_limit);
        mTvTradeMarket = (TextView) view.findViewById(R.id.tv_trade_market);
        mIvLeftSelected = (ImageView) view.findViewById(R.id.iv_left_selected);
        mIvRightSelected = (ImageView) view.findViewById(R.id.iv_right_selected);
        // 买卖盘
        mLinBuyLay = (LinearLayout) view.findViewById(R.id.ll_buy1);
        mLinSaleLay = (LinearLayout) view.findViewById(R.id.ll_sale1);
        mPriceBuy1 = (TextView) mRootView.findViewById(R.id.tv_buy1_price);
        mPriceSale1 = (TextView) mRootView.findViewById(R.id.tv_sale1_price);
        mAmountBuy1 = (TextView) mRootView.findViewById(R.id.tv_buy1_amount);
        mAmountSale1 = (TextView) mRootView.findViewById(R.id.tv_sale1_amount);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mTvSubtract, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvAdd, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvStockName, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnBuyOrSell, mController);
        registerListener(AbsBaseController.ON_ITEM_CLICK, mLvMyStore, mController);
        registerListener(BuyOrSellViewController.ON_TEXT_CHANGE, mEdStockCode, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvExchangeRate, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvOneUnit, mController);
        registerListener(BuyOrSellViewController.ON_FOCUS_CHANGE, mEdStockPrice, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinBuyLay, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinSaleLay, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvTradeLimit, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvTradeMarket, mController);
        //监听价格输入框
        mEdStockPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TradeUtils.onLimitAfterPoint(mEdStockPrice, s, 5, 3);
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
        mResources = CoreApplication.getInstance().getResources();
        mActivity = (TKFragmentActivity) getActivity();
        mMyStoreListViewAdapter = new HkHoldListForBuySaleAdapter(mActivity);
        mController = new HKBuySellViewController(this);
        mFontManager = FontManager.getInstance(mActivity);
        mStockFuzzyQueryManager = StockFuzzyQueryManager.getInstance(mActivity);
        mService = new HKBuySellServiceImpl(this);
        Bundle bundle = getArguments();
        if(bundle != null){
            mBuyOrSell = bundle.getInt("buyOrSale",0);
            mStockCode = bundle.getString("code");
        }
        // 如果是买入
        if (mBuyOrSell == 0) {
            mService.setEntrust_bs("0");
            mBtnBuyOrSaleText = mResources.getString(R.string.hk_buy);
            // 如果是卖出
        } else if (mBuyOrSell == 1) {
            mService.setEntrust_bs("1");
            mBtnBuyOrSaleText = mResources.getString(R.string.hk_sell);
        }
        mService.setEntrust_level("0");
    }

    @Override
    protected void initViews() {
        mBtnBuyOrSell.setText(mBtnBuyOrSaleText);
        // 如果是买入
        if (mBuyOrSell == 0) {
            mBtnBuyOrSell.setBackgroundResource(R.color.trade_buy);
            mTvAdd.setBackgroundColor(mResources.getColor(R.color.trade_buy));
            mTvSubtract.setBackgroundColor(mResources.getColor(R.color.trade_buy));

            mTvTradeLimit.setBackgroundResource(R.drawable.buy_selector_button);
            mTvTradeMarket.setBackgroundResource(R.drawable.buy_selector_button);
            mTvTradeLimit.setTextColor(mResources.getColor(R.color.trade_buy));
            mTvTradeMarket.setTextColor(mResources.getColor(R.color.trade_buy));
            mIvLeftSelected.setImageResource(R.drawable.trade_buy_selected);
            mIvRightSelected.setImageResource(R.drawable.trade_buy_selected);

            mRootView.findViewById(R.id.ll_code_name).setBackgroundResource(R.drawable.buy_no_corner_kong);
            mRootView.findViewById(R.id.ll_input_price).setBackgroundResource(R.drawable.buy_no_corner_kong);
            mRootView.findViewById(R.id.ll_input_amount).setBackgroundResource(R.drawable.buy_no_corner_kong);
            mEdStockPrice.setHint(mResources.getString(R.string.trade_hint_input_buy_price));
            // 如果是卖出
        } else if (mBuyOrSell == 1) {
            mBtnBuyOrSell.setBackgroundResource(R.color.trade_sale);
            mTvAdd.setBackgroundColor(mResources.getColor(R.color.trade_sale));
            mTvSubtract.setBackgroundColor(mResources.getColor(R.color.trade_sale));

            mTvTradeLimit.setBackgroundResource(R.drawable.sale_selector_button);
            mTvTradeMarket.setBackgroundResource(R.drawable.sale_selector_button);
            mTvTradeLimit.setTextColor(mResources.getColor(R.color.trade_sale));
            mTvTradeMarket.setTextColor(mResources.getColor(R.color.trade_sale));
            mIvLeftSelected.setImageResource(R.drawable.trade_sale_selected);
            mIvRightSelected.setImageResource(R.drawable.trade_sale_selected);

            mRootView.findViewById(R.id.ll_code_name).setBackgroundResource(R.drawable.sale_no_corner_kong);
            mRootView.findViewById(R.id.ll_input_price).setBackgroundResource(R.drawable.sale_no_corner_kong);
            mRootView.findViewById(R.id.ll_input_amount).setBackgroundResource(R.drawable.sale_no_corner_kong);
            mEdStockPrice.setHint(mResources.getString(R.string.trade_hint_input_sale_price));
        }
        mTvExchangeRate.setTextColor(Color.parseColor(TradeConfigUtils.sPriceUpColor));
        mTvOneUnit.setTextColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
        mEntrustNumEDKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdEntrustAmount, KeyboardManager.KEYBOARD_TYPE_MERCHANDISE);
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

        setTheme();
        // 设置字体
        mFontManager.setTextFont(mTvExchangeRate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvOneUnit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mEdStockPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mEdEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mBtnBuyOrSell, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale1, FontManager.FONT_DINPRO_MEDIUM);
        mLvMyStore.setAdapter(mMyStoreListViewAdapter);
        // 调用业务类中，请求数据的方法
        mService.getHoldList();
    }

    @Override
    protected void setTheme() { }

    /**
     * 当价格输入框的内容发生改变时，执行此方法
     */
    private void requestLinkOnPriceChange(){
        String entrustCode = getEntrustCode();
        String curEntrustPrice = getEntrustPrice();
        if(mStockLinkageBean != null &&!TextUtils.isEmpty(curEntrustPrice) && !mLastEntrustPrice.equals(curEntrustPrice)) {
            mService.requestLinkageData(entrustCode, curEntrustPrice);
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
                plainPrice = TradeUtils.formatDouble3(bigDecimal.toPlainString());
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
     * 接收从我的持仓,行情 ,跳转至买入卖出
     */
    private void setDataToViewsFromHoldList() {
        if(mStockCode != null && !TextUtils.isEmpty(mStockCode)){
            mService.startLinkage(mStockCode,false);


        }
    }
    /**
     * 当股票代码输入框{@link #mEdStockCode}中的输入内容改变的时候
     */
    public void onSearchTextChange() {
        // 当前股票代码输入框输入的内容
        String curEditContent = getEntrustCode();
        // 当前输入内容的长度
        int curInputLength = curEditContent.length();
        setLinkageState(false);
        // 没有输入完
        if (curInputLength > 1 && curInputLength < 5) {
            clearDataInViewsExpectStockCodeEd();
            //判断输入的是否是数字
            if(TradeUtils.isNumeric(curEditContent)){
                //如果是数字（输入3位及以上开始模糊查询）
                if (curInputLength > 2 && curInputLength < 5) {
                    mStockFuzzyQueryManager.execQuery(curEditContent,"2", mLlStockCodeName);
                }
            }else{ //如果是字母（输入2位及以上开始模糊查询）
                mStockFuzzyQueryManager.execQuery(curEditContent,"2", mLlStockCodeName);
            }
        } else if (curInputLength == 5) {
            // 输入完成时，隐藏输入提示列表，并显示之前被输入提示列表“遮挡”的界面
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            mStockCodeEdKeyboardManager.dismiss();
            // 如果之前没输入完，现在刚刚输入完
            if (mLastInputStockCodeLength == 4) {
                mService.startLinkage(curEditContent,false);
            }
        } else {
            clearDataInViewsExpectStockCodeEd();
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
        }
        mLastInputStockCodeLength = curInputLength;
    }

    /**
     * 展示股票代码模糊查询结果的listview的item单击事件
     * @param position
     * 被单击的item的position
     */
    public void onSearchListViewItemClick(int position) {
        CodeTableBean SelectBean = mStockFuzzyQueryManager.getSearchStocksAdapter().getItem(position);
        String selectCode = SelectBean.getCode();
        mService.startLinkage(selectCode,false);
        TradeUtils.hideSystemKeyBoard(mActivity);
        mStockCodeEdKeyboardManager.dismiss();
        mLastInputStockCodeLength = 5;
    }

    /**
     * 持仓列表项单击事件监听
     * @param position
     * 选中的item的序号
     */
    public void onStoreListViewItemClick(int position) {
        ArrayList<HKHoldStockBean> dataList = mMyStoreListViewAdapter.getListData();
        HKHoldStockBean bean = dataList.get(position);
        mService.startLinkage(bean.getStock_code(),false);
    }

    /**
     * “汇率”按钮单击监听事件
     */
    public void onClickAmount() {}
    /**
     * “额度”按钮单击事件监听
     */
    public void onClickExchangeRate() {}

    /**
     * 交易价格+，按钮单击事件
     */
    public void onClickTradeAdd() {
        double curTradePrice = getTradePriceEditText();
        if (curTradePrice != 0) {
            if(mStockLinkageBean != null && !TextUtils.isEmpty(mStockLinkageBean.getStep_price())){
                double data =  Double.parseDouble(mStockLinkageBean.getStep_price());
                mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice + data));
            }else{
                mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice + 0.01));
            }
        }
    }

    /**
     * 交易价格-，按钮单击事件
     */
    public void onClickTradeSubstract() {
        double curTradePrice = getTradePriceEditText();
        if (curTradePrice != 0) {
            if(mStockLinkageBean != null && !TextUtils.isEmpty(mStockLinkageBean.getStep_price())){
                double data =  Double.parseDouble(mStockLinkageBean.getStep_price());
                mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice - data));
            }else{
                mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice - 0.01));
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
     * 增强限价盘
     */
    public void onClickTradeLimit() {
        mTvTradeLimit.setEnabled(false);
        mTvTradeMarket.setEnabled(true);
        mIvLeftSelected.setVisibility(View.VISIBLE);
        mIvRightSelected.setVisibility(View.INVISIBLE);
        mService.setEntrust_level("0");
    }

    /**
     * 竞价限价盘
     */
    public void onClickTradeMarket() {
        mTvTradeLimit.setEnabled(true);
        mTvTradeMarket.setEnabled(false);
        mIvLeftSelected.setVisibility(View.INVISIBLE);
        mIvRightSelected.setVisibility(View.VISIBLE);
        mService.setEntrust_level("1");
    }

    /**
     * 买入或者卖出按钮单击事件
     * 弹出确认操作对话框对话框，设置对话框布局，然后监听对话框中按钮的单击事件
     */
    public void onClickTrade() {
        if (TradeUtils.isFastClick2()) {
            return;
        }
        String entrustPrice = getEntrustPrice();
        String entrustAmount = getEntrustAmount();
        String stockCode = getEntrustCode();
        // 没正确输入股票代码时
        if (TextUtils.isEmpty(stockCode) || stockCode.length() != 5) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.hk_order24));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        // 没有正确联动到证券信息
        if (mStockMsgBean == null) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.hk_order27));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        // 没输入委托数量时
        if (entrustAmount == null || entrustAmount.equals("")) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.hk_order23));
            TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
            return;
        }
        // 没输入委托价格时
        if (entrustPrice == null || entrustPrice.equals("")) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.hk_order22));
            mEntrustNumEDKeyboardManager.dismiss();
            mStockCodeEdKeyboardManager.dismiss();
            TradeUtils.showKeyBoard(mActivity, mEdStockPrice, true);
            return;
        }
        try{
            //联动成功后才能取到每手单位，所以联动成功后才会校验委托数量
            if( mStockLinkageBean != null){
                double entrustAmountDouble = Double.parseDouble(entrustAmount);
                double entrustMaxAmountDouble = Double.parseDouble(mStockLinkageBean.getStock_max_amount());
                int unit = Integer.parseInt(mStockLinkageBean.getBuy_unit());
                // 如果是买入，那么数量必须能被unit整除
                if (mBuyOrSell == 0 && entrustAmountDouble % unit != 0) {
                    ToastUtils.toast(mActivity, mResources.getString(R.string.hk_buy_or_sell0)
                            + unit + mResources.getString(R.string.hk_buy_or_sell1));
                    TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
                    return;
                    // 如果是卖出，那么数量必须能被unit整除，或者等于最大可卖数量
                } else if (mBuyOrSell == 1 && entrustAmountDouble % unit != 0 && entrustAmountDouble != entrustMaxAmountDouble) {
                    ToastUtils.toast(mActivity, mResources.getString(R.string.hk_buy_or_sell2)
                            + unit + mResources.getString(R.string.hk_buy_or_sell3));
                    TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
                    return;
                }
            }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
        HKTradeConfirmDialog dialog = new HKTradeConfirmDialog(mActivity, mBuyOrSell, mService);
        dialog.setDataToViews(mStockMsgBean.getName(),mStockMsgBean.getCode(), getEntrustPrice(), getEntrustAmount());
        dialog.show();
    }

    /**
     * 弹出popupwindow，显示自选股列表
     */
    public void showOptionalList() {
        if (TextUtils.isEmpty(getEntrustCode())) {
            // 发消息给行情，查询股票输入提示列表
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            mStockFuzzyQueryManager.execQueryOptional();
            mStockFuzzyQueryManager.showQueryPopupWindow(mLlStockCodeName);
        }
    }

    /**
     * 点击买卖盘执行的操作
     * @param v
     */
    public void onClickBuyOrSaleDisk(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ll_buy1) {
            mEdStockPrice.setText(mPriceBuy1.getText().toString());
            mLinBuyLay.setBackgroundResource(R.drawable.common_no_corner_transparent);
        }else{
            mLinBuyLay.setBackgroundColor(mResources.getColor(R.color.transparent));
        }
        if (viewId == R.id.ll_sale1) {
            mEdStockPrice.setText(mPriceSale1.getText().toString());
            mLinSaleLay.setBackgroundResource(R.drawable.common_no_corner_transparent);
        }else{
            mLinSaleLay.setBackgroundColor(mResources.getColor(R.color.transparent));
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
     * 联动成功
     */
    public void onGetStockLinkAgeData(HKStockLinkBean bean) {
        mStockLinkageBean = bean;
        mEdEntrustAmount.setText("");
        mTvOneUnit.setText(mStockLinkageBean.getBuy_unit());
        if(mBuyOrSell==0){
            mTvExchangeRate.setText(mStockLinkageBean.getBuy_exchange_rate());
        }else if(mBuyOrSell==1){
            mTvExchangeRate.setText(mStockLinkageBean.getSell_exchange_rate());
        }
        setLinkageState(true);
    }

    /**
     * 请求50000接口，获取相关数据
     */
    public void onGetHqData(HKStockMessageBean bean,boolean isRefresh) {
        String buyPrice = bean.getBuy_price();
        String salePrice = bean.getSell_price();
        mPriceSale1.setText(salePrice);
        mPriceSale1.setTextColor(bean.getSellColor());
        mAmountSale1.setText(TradeUtils.formateDataWithQUnit(bean.getSell_num()));
        mPriceBuy1.setText(buyPrice);
        mPriceBuy1.setTextColor(bean.getBuyColor());
        mAmountBuy1.setText(TradeUtils.formateDataWithQUnit(bean.getBuy_num()));
        //如果不是五档刷新才执行
        if(!isRefresh){
            mStockMsgBean = bean;
            mTvStockName.setText(bean.getName());
            mEdStockCode.setText(TradeUtils.subOneNum(bean.getCode()));
            if(mBuyOrSell==0){
                if(salePrice != null && !TextUtils.isEmpty(salePrice)){
                    double price = Double.parseDouble(salePrice);
                    if(price > 0){
                        mEdStockPrice.setText(salePrice);
                    }else {
                        mEdStockPrice.setText(bean.getNow_price());
                    }
                }else {
                    mEdStockPrice.setText(bean.getNow_price());
                }
            }else if(mBuyOrSell==1){
                if(buyPrice != null && !TextUtils.isEmpty(buyPrice)){
                    double price = Double.parseDouble(buyPrice);
                    if(price > 0){
                        mEdStockPrice.setText(buyPrice);
                    }else {
                        mEdStockPrice.setText(bean.getNow_price());
                    }
                }else {
                    mEdStockPrice.setText(bean.getNow_price());
                }
            }
            mService.requestLinkageData(mStockMsgBean.getCode(),getEntrustPrice());
        }
    }

    /**
     * 发现证券停牌（除了证券代码和名称）
     */
    public void onGetSuspendStock(String stockName, String code) {
        clearDataInViewsExpectStockCodeEd();
        mTvStockName.setText(stockName);
        mEdStockCode.setText(code);
    }
    /**
     * 清空界面上的所有数字
     */
    public void clearDataInViews() {
        mEdStockCode.setText("");
        mStockCode = "";
        clearDataInViewsExpectStockCodeEd();
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 发生异常时，清空界面数据
     */
    public void clearDataInViewsExpectStockCodeEd() {
        String blankStr = "";
        mStockLinkageBean = null;
        mStockMsgBean = null;
        mLastEntrustPrice = blankStr;
        mTvStockName.setText(blankStr);
        mTvExchangeRate.setText(blankStr);
        mTvOneUnit.setText(blankStr);
        mEdStockPrice.setText(blankStr);
        mEdEntrustAmount.setText(blankStr);
        // 清空买卖盘的数据
        mPriceSale1.setText(blankStr);
        mAmountSale1.setText(blankStr);
        mPriceBuy1.setText(blankStr);
        mAmountBuy1.setText(blankStr);
        // 还原五档盘上TextView的颜色
        mPriceSale1.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mAmountSale1.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mPriceBuy1.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mAmountBuy1.setTextColor(mResources.getColor(R.color.trade_text_color9));
    }

    /**
     * 根据入参设置本页面股票联动状态，本页面分两种状态，联动和不联动。
     * 联动时，涨停跌停价和五档买卖盘等控件可点击，并有附带效果；
     * 不联动时，或联动失败时，将上述控件设置为不可点击状态。
     * @param linkageState
     * 设置股票是否联动；true：进入联动
     */
    private void setLinkageState(boolean linkageState) {
        try {
            mLinSaleLay.setEnabled(linkageState);
            mLinSaleLay.setBackgroundColor(mResources.getColor(R.color.transparent));
            mLinBuyLay.setEnabled(linkageState);
            mLinBuyLay.setBackgroundColor(mResources.getColor(R.color.transparent));
            //额度和汇率
            mTvExchangeRate.setEnabled(linkageState);
            mTvOneUnit.setEnabled(linkageState);
            if (linkageState && mStockLinkageBean != null) {
                // 设置持仓键盘仓位选择键功能
                if (!mEdStockPrice.hasFocus() && !mActivity.isFinishing()) {
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
                if (mBuyOrSell == 0) { // 买入时，设置委托数量输入框的输入提示hint为：最多可买xx股
                    mEdEntrustAmount.setHint(mResources.getString(R.string.trade_hint_most_buy) + max_amount);
                } else if (mBuyOrSell == 1) { // 卖出时，设置委托数量输入框的输入提示hint为：最多可卖xx股
                    mEdEntrustAmount.setHint(mResources.getString(R.string.trade_hint_most_sale) + max_amount);
                }
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
     * 仓位选择方法，当选择了相应仓位
     * @param pos
     * 对应的1/pos仓位，例如，传入2时，就表示1/2仓
     */
    public void setAmountPos(int pos) {
        String result = "";
        if(mStockLinkageBean != null){
            String maxStr = mStockLinkageBean.getStock_max_amount();
            String unitStr = mStockLinkageBean.getBuy_unit();
            if(!TextUtils.isEmpty(maxStr) && !TextUtils.isEmpty(unitStr)){
                int max_amount = Integer.parseInt(maxStr);
                int buy_unit = Integer.parseInt(unitStr);
                result = formatNumToHundreds(max_amount / pos,buy_unit);
            }
        }
        mEdEntrustAmount.setText(result);
    }

    /**
     * 点击仓位时，计算可委托
     * @param maxNum
     * @param unit
     * @return
     */
    public static String formatNumToHundreds(int maxNum,int unit) {
        int tempNum = 0;
        if(maxNum > unit){
            if(maxNum % unit != 0){
                tempNum = maxNum - (maxNum % unit);
            }else if(maxNum % unit == 0){
                tempNum = maxNum;
            }
        }else if(maxNum < unit){
            tempNum = 0;
        }else if(maxNum == unit){
            tempNum = maxNum;
        }
        return String.valueOf(tempNum);
    }

    /**
     * 获取HKBuySellServiceImpl 类传过来的持仓数据
     * @param dataList
     * 持仓列表数据表
     */
    public void getStoreData(ArrayList<HKHoldStockBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLvMyStore.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.VISIBLE);
            mLoading.setVisibility(View.GONE);
        } else {
            mLvMyStore.setVisibility(View.VISIBLE);
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.GONE);
            mMyStoreListViewAdapter.setListData(dataList);
            mMyStoreListViewAdapter.notifyDataSetChanged();
        }
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

    public String getEntrustAmount() {
        return mEdEntrustAmount.getText().toString().trim();
    }
    public String getEntrustCode() {
        return mEdStockCode.getText().toString().trim();
    }
    public String getEntrustPrice() {
        return mEdStockPrice.getText().toString().trim();
    }
}