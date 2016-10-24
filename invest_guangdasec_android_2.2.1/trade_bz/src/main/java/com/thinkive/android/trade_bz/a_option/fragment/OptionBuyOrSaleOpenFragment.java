package com.thinkive.android.trade_bz.a_option.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.activity.OptionSelectListActivity;
import com.thinkive.android.trade_bz.a_option.bean.OptionEntrustOrderBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionEntrustPriceWayBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionExerciseEndDateBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionExercisePriceBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionInfoBean;
import com.thinkive.android.trade_bz.a_option.bll.OptionBuyOrSaleOpenServicesImpl;
import com.thinkive.android.trade_bz.a_option.controll.OptionBuyOrSaleOpenController;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.a_stock.bean.UserInfo;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.dialog.AbsOptionComfirmDialog;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 个股期权 买入开仓，卖出开仓,备兑券开仓
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/20
 */
public class OptionBuyOrSaleOpenFragment extends AbsBaseFragment {

    private TKFragmentActivity mActivity;
    private OptionBuyOrSaleOpenServicesImpl mServices;
    private OptionBuyOrSaleOpenController mController;
    private Resources mResources;
    /**
     * 子布局
     */
    private View mChildView;
    /**
     * 嵌套子布局的View
     */
    private ScrollView mScrollView;
    /**
     * 管理 RadioButton
     */
    private RadioGroup mRadioGroup;
    /**
     * 认购
     */
    private RadioButton mRbSubscription;
    /**
     * 认沽
     */
    private RadioButton mRbPutOption;
    /**
     * 证券代码
     */
    private EditText mEdtStockCode;
    /**
     * 证券名称
     */
    private TextView mTvStockName;
    /**
     * 到期月份
     */
    private TextView mTvCutDate;
    /**
     * 行权价格
     */
    private TextView mTvExercisePrice;
    /**
     * 合约代码
     */
    private EditText mTvContractCode;
    /**
     * 合约名称
     */
    private TextView mTvContractName;
    /**
     * 选择报价方式
     */
    private TextView mTvSelectSubBs;
    /**
     * 限价 输入价格组件
     */
    LinearLayout mLlInputPrice;
    /**
     * 价格减
     */
    private TextView mTvPriceCut;
    /**
     * 价格输入框
     */
    private EditText mEdtEntrustPrice;
    /**
     * 价格加
     */
    private TextView mTvPriceAdd;
    /**
     * 数量减
     */
    private TextView mTvNumCut;
    /**
     * 数量输入框
     */
    private EditText mEdtEntrustNum;
    /**
     * 数量加
     */
    private TextView mTvNumAdd;
    /**
     * 最大可用量
     */
    private TextView mTvMaxEnable;
    /**
     * 点击 “全部”
     */
    private TextView mTvClickAllMax;
    /**
     * 提交按钮
     */
    private Button mBtnClickCommit;
    /**
     * 五档盘行的集合，里面的每个LinearLayout代表五档盘的一行
     */
    private ArrayList<LinearLayout> mBuySaleDiskLlList;

    //***********************五档买入布局***********************
    private LinearLayout mLlBuy1;
    private LinearLayout mLlBuy2;
    private LinearLayout mLlBuy3;
    private LinearLayout mLlBuy4;
    private LinearLayout mLlBuy5;
    //***********************五档买入价格***********************
    private TextView mTvBuy1Price;
    private TextView mTvBuy2Price;
    private TextView mTvBuy3Price;
    private TextView mTvBuy4Price;
    private TextView mTvBuy5Price;
    //***********************五档买入数量***********************
    private TextView mTvBuy1Amount;
    private TextView mTvBuy2Amount;
    private TextView mTvBuy3Amount;
    private TextView mTvBuy4Amount;
    private TextView mTvBuy5Amount;
    //***********************五档卖出布局***********************
    private LinearLayout mLlSale5;
    private LinearLayout mLlSale4;
    private LinearLayout mLlSale3;
    private LinearLayout mLlSale2;
    private LinearLayout mLlSale1;
    //***********************五档卖出价格***********************
    private TextView mTvSale5Price;
    private TextView mTvSale4Price;
    private TextView mTvSale3Price;
    private TextView mTvSale2Price;
    private TextView mTvSale1Price;
    //***********************五档卖出数量***********************
    private TextView mTvSale5Amount;
    private TextView mTvSale4Amount;
    private TextView mTvSale3Amount;
    private TextView mTvSale2Amount;
    private TextView mTvSale1Amount;
    /**
     * 框架内，自绘键盘管理器
     */
    private KeyboardManager mKeyboardManager;

    /**
     * 委托价格自定义键盘
     */
    private KeyboardManager keyboardManager2;

    /**
     * 上一次设置到价格委托框的值
     */
    private String mLastEntrustPrice = "";
    /**
     * 从行情获取的现价
     */
    private String mNowPrice = "";
    /**
     * 区分买入卖出的标志
     */
    private int mBuyOrSell;
    /**
     * 区分买入卖出的标志 对应的fragment的标题名称
     */
    private String mBuyOrSellName;
    /**
     * 期权类型 0：认购 1：认沽
     */
    private String mOptionType = "0";
    /**
     * 当前证券的行情信息
     */
    private CodeTableBean mCodeTableBean;
    /**
     * 获取的标的证券列表
     */
    private ArrayList<OptionInfoBean> optionInfoBeans = null;
    /**
     * 选择完行权日期和行权价格之后，重新请求 获取期权代码信息 后，返回的唯一结果
     */
    private OptionInfoBean singleOptionInfoBean = null;
    /**
     * 获取的行权到期月份列表
     */
    private ArrayList<OptionExerciseEndDateBean> optionExerciseEndDateBeans = null;
    /**
     * 选中的到期月份值
     */
    private OptionExerciseEndDateBean selectedExerciseEndDate = null;
    /**
     * 获取的行权价格列表
     */
    private ArrayList<OptionExercisePriceBean> optionExercisePriceBeans = null;
    /**
     * 选中的行权价格
     */
    private OptionExercisePriceBean selectedExercisePrice = null;
    /**
     * 所有委托报价方式列表
     */
    private static ArrayList<OptionEntrustPriceWayBean> allEntrustQuotedPriceWays = null;
    /**
     * 深市委托报价方式列表
     */
    private static ArrayList<OptionEntrustPriceWayBean> sSEntrustQuotedPriceWays = null;
    /**
     * 沪市委托报价方式列表
     */
    private static ArrayList<OptionEntrustPriceWayBean> hSEntrustQuotedPriceWays = null;
    /**
     * 当前标的证券所属证券市场，对应的委托报价方式列表
     */
    private ArrayList<OptionEntrustPriceWayBean> curEntrustQuotedPriceWays = null;
    /**
     * 当前选择的 委托报价方式
     */
    private OptionEntrustPriceWayBean selectedEntrustQuotedPriceWay = null;
    /**
     * 最小价差
     */
    private double minPriceStep;
    /**
     * 这个标志，用来标志，是否是用户的操作导致文本变化，还是程序进行清除内容，引起的文本内容变化。防止两个输入框的内容变化，循环调用文本监听事件。如果只有一个输入框，绑定了事件的时候，不需要这个。
     */
    private boolean isTextChangedByUserOperation = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mChildView = inflater.inflate(R.layout.fragment_option_open, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mKeyboardManager.dismiss();
        keyboardManager2.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    @Override
    protected void findViews(View view) {
        mBuySaleDiskLlList = new ArrayList<LinearLayout>();
        mScrollView = (ScrollView) view.findViewById(R.id.sll_layout);
        mScrollView.addView(mChildView);

        mRadioGroup = (RadioGroup) mChildView.findViewById(R.id.rg_option_open);
        mRbSubscription = (RadioButton) mChildView.findViewById(R.id.rb_option_subscription);
        mRbPutOption = (RadioButton) mChildView.findViewById(R.id.rb_option_put);
        mEdtStockCode = (EditText) mChildView.findViewById(R.id.edt_stock_code);
        mTvStockName = (TextView) mChildView.findViewById(R.id.tv_stock_name);
        mTvCutDate = (TextView) mChildView.findViewById(R.id.tv_cut_date);
        mTvExercisePrice = (TextView) mChildView.findViewById(R.id.tv_exercise_price);
        mTvContractCode = (EditText) mChildView.findViewById(R.id.tv_contract_code);
        mTvContractName = (TextView) mChildView.findViewById(R.id.tv_contract_name);
        mTvSelectSubBs = (TextView) mChildView.findViewById(R.id.tv_select_sub_bs);
        mLlInputPrice = (LinearLayout) mChildView.findViewById(R.id.ll_input_price);
        mTvPriceCut = (TextView) mChildView.findViewById(R.id.tv_price_cut);
        mEdtEntrustPrice = (EditText) mChildView.findViewById(R.id.edt_entrust_price);
        mTvPriceAdd = (TextView) mChildView.findViewById(R.id.tv_price_add);
        mTvNumCut = (TextView) mChildView.findViewById(R.id.tv_num_cut);
        mEdtEntrustNum = (EditText) mChildView.findViewById(R.id.edt_entrust_num);
        mTvNumAdd = (TextView) mChildView.findViewById(R.id.tv_num_add);
        mTvMaxEnable = (TextView) mChildView.findViewById(R.id.tv_max_enable);
        mTvClickAllMax = (TextView) mChildView.findViewById(R.id.tv_click_all_max);
        mBtnClickCommit = (Button) mChildView.findViewById(R.id.btn_click_commit);


        //**************************************五档买入布局*************************
        mLlBuy1 = (LinearLayout) mChildView.findViewById(R.id.ll_buy1);
        mLlBuy2 = (LinearLayout) mChildView.findViewById(R.id.ll_buy2);
        mLlBuy3 = (LinearLayout) mChildView.findViewById(R.id.ll_buy3);
        mLlBuy4 = (LinearLayout) mChildView.findViewById(R.id.ll_buy4);
        mLlBuy5 = (LinearLayout) mChildView.findViewById(R.id.ll_buy5);
        mBuySaleDiskLlList.add(mLlBuy1);
        mBuySaleDiskLlList.add(mLlBuy2);
        mBuySaleDiskLlList.add(mLlBuy3);
        mBuySaleDiskLlList.add(mLlBuy4);
        mBuySaleDiskLlList.add(mLlBuy5);
        //**************************************五档买入价格************************
        mTvBuy1Price = (TextView) mChildView.findViewById(R.id.tv_buy1_price);
        mTvBuy2Price = (TextView) mChildView.findViewById(R.id.tv_buy2_price);
        mTvBuy3Price = (TextView) mChildView.findViewById(R.id.tv_buy3_price);
        mTvBuy4Price = (TextView) mChildView.findViewById(R.id.tv_buy4_price);
        mTvBuy5Price = (TextView) mChildView.findViewById(R.id.tv_buy5_price);
        //**************************************五档买入数量*************************
        mTvBuy1Amount = (TextView) mChildView.findViewById(R.id.tv_buy1_amount);
        mTvBuy2Amount = (TextView) mChildView.findViewById(R.id.tv_buy2_amount);
        mTvBuy3Amount = (TextView) mChildView.findViewById(R.id.tv_buy3_amount);
        mTvBuy4Amount = (TextView) mChildView.findViewById(R.id.tv_buy4_amount);
        mTvBuy5Amount = (TextView) mChildView.findViewById(R.id.tv_buy5_amount);
        //**************************************五档卖出布局**************************
        mLlSale5 = (LinearLayout) mChildView.findViewById(R.id.ll_sale5);
        mLlSale4 = (LinearLayout) mChildView.findViewById(R.id.ll_sale4);
        mLlSale3 = (LinearLayout) mChildView.findViewById(R.id.ll_sale3);
        mLlSale2 = (LinearLayout) mChildView.findViewById(R.id.ll_sale2);
        mLlSale1 = (LinearLayout) mChildView.findViewById(R.id.ll_sale1);
        mBuySaleDiskLlList.add(mLlSale5);
        mBuySaleDiskLlList.add(mLlSale4);
        mBuySaleDiskLlList.add(mLlSale3);
        mBuySaleDiskLlList.add(mLlSale2);
        mBuySaleDiskLlList.add(mLlSale1);
        //**************************************五档卖出价格**************************
        mTvSale5Price = (TextView) mChildView.findViewById(R.id.tv_sale5_price);
        mTvSale4Price = (TextView) mChildView.findViewById(R.id.tv_sale4_price);
        mTvSale3Price = (TextView) mChildView.findViewById(R.id.tv_sale3_price);
        mTvSale2Price = (TextView) mChildView.findViewById(R.id.tv_sale2_price);
        mTvSale1Price = (TextView) mChildView.findViewById(R.id.tv_sale1_price);
        //**************************************五档卖出数量*************************
        mTvSale5Amount = (TextView) mChildView.findViewById(R.id.tv_sale5_amount);
        mTvSale4Amount = (TextView) mChildView.findViewById(R.id.tv_sale4_amount);
        mTvSale3Amount = (TextView) mChildView.findViewById(R.id.tv_sale3_amount);
        mTvSale2Amount = (TextView) mChildView.findViewById(R.id.tv_sale2_amount);
        mTvSale1Amount = (TextView) mChildView.findViewById(R.id.tv_sale1_amount);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_TEXT_CHANGE, mEdtEntrustPrice, mController);
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRadioGroup, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvCutDate, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvStockName, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvExercisePrice, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvSelectSubBs, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvPriceCut, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvPriceAdd, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvNumCut, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvNumAdd, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvClickAllMax, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnClickCommit, mController);
        /*//循环为五档盘注册监听
        for (LinearLayout ll : mBuySaleDiskLlList) {
            registerListener(ListenerController.ON_CLICK, ll, mController);
        }*/
        //监听证券代码输入
        mEdtStockCode.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isTextChangedByUserOperation) {
                    //是用户操作，引起的文本内容变化，才需要做事件处理
                    if (temp.length() == 6) {
                        //根据合约代码查询五档数据  这里使用行情来查询五档盘的数据，传入参数是 标的证券代码。所以这里的操作可以和用户输完标的证券后的请求一起发出。但是因为这个里面已经做了一些校验和提示，所以305001可以不用做这些相关的提示和提示了
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("stockCode", temp.toString());
                        if (mBuyOrSell == 0) {
                            //买入开仓
                            params.put("entrustBs", "0"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                            params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                        } else if (mBuyOrSell == 1) {
                            //卖出开仓
                            params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                            params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                        } else if (mBuyOrSell == 2) {
                            //备兑开仓
                            params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                            params.put("coveredFlag", "1"); //备兑标志 0：不是备兑  1：是备兑
                        }
                        params.put("optionType", mOptionType); //期权类别  0：认购 1：认沽
                        mServices.startLinkage(params);
                    } else {
                        HashSet<String> ignoreFlagSet = new HashSet<String>();
                        ignoreFlagSet.add("queryByStockCode");
                        onClearAllData(ignoreFlagSet);
                    }
                } else {
                    //不是用户操作，即时由程序设置内容后，或者清除内容后，引起的文本内容变化，不需要做事件处理。不过需要把标志重置为默认的值，是用户操作。如果不做重置操作，下次，用户操作，引起的内容变化，也不会做处理了。
                    isTextChangedByUserOperation = true;
                }
            }
        });


        //监听合约代码输入
        mTvContractCode.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isTextChangedByUserOperation) {
                    //是用户操作，引起的文本内容变化，才需要做事件处理
                    if (temp.length() == 8) {
                        //查询305001接口，查询并返回期权代码信息列表
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("optionCode", temp.toString());
                        if (mBuyOrSell == 0) {
                            //买入开仓
                            params.put("entrustBs", "0"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                            params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                        } else if (mBuyOrSell == 1) {
                            //卖出开仓
                            params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                            params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                        } else if (mBuyOrSell == 2) {
                            //备兑开仓
                            params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                            params.put("coveredFlag", "1"); //备兑标志 0：不是备兑  1：是备兑
                        }
                        params.put("optionType", mOptionType); //期权类别  0：认购 1：认沽
                        mServices.requestStockOptionInfo("optionInfo3", params);
                    } else {
                        HashSet<String> ignoreFlagSet = new HashSet<String>();
                        ignoreFlagSet.add("queryByContractCode");
                        onClearAllData(ignoreFlagSet);
                    }
                } else {
                    //不是用户操作，即时由程序设置内容后，或者清除内容后，引起的文本内容变化，不需要做事件处理。不过需要把标志重置为默认的值，是用户操作。如果不做重置操作，下次，用户操作，引起的内容变化，也不会做处理了。
                    isTextChangedByUserOperation = true;
                }
            }
        });

        //委托价格，监听事件
        keyboardManager2.setInputCompleteListener(new KeyboardManager.OnInputCompleteListener() {
            @Override
            public void onInputComplete() {
                String optionCode = mTvContractCode.getText().toString().trim();
                String entrustPrice = mEdtEntrustPrice.getText().toString().trim();
                if(singleOptionInfoBean != null&&!TextUtils.isEmpty(optionCode)){
                    //查询305001接口，查询并返回最大可平
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("optionCode", optionCode);
                    params.put("entrustPrice" ,entrustPrice);
                    if (mBuyOrSell == 0) {
                        //买入开仓
                        params.put("entrustBs", "0"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                        params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                        params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                    } else if (mBuyOrSell == 1) {
                        //卖出开仓
                        params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                        params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                        params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                    } else if (mBuyOrSell == 2) {
                        //备兑开仓
                        params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                        params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                        params.put("coveredFlag", "1"); //备兑标志 0：不是备兑  1：是备兑
                    }
//                        params.put("optionType", mOptionType); //期权类别  0：认购 1：认沽
                    mServices.requestStockOptionInfo("optionInfo4", params);
                }else{
                    ToastUtils.toast(getContext(),"请先进行前面的操作");
                }
            }
        });

    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mResources = CoreApplication.getInstance().getResources();
        mController = new OptionBuyOrSaleOpenController(this);
        mServices = new OptionBuyOrSaleOpenServicesImpl(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mBuyOrSell = bundle.getInt("buyOrSale");
        }

        //初始化 委托报价方式列表
        if (allEntrustQuotedPriceWays == null) {
            allEntrustQuotedPriceWays = new ArrayList<OptionEntrustPriceWayBean>();
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("11", "委托申报", -1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("13", "撤单申报", -1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("1", "股票期权申报-限价 GFD", 0));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("2", "股票期权申报-限价 FOK", 0));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("5", "股票期权申报-市价剩转限价 GFD", 0));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("4", "股票期权申报-市价 FOK", 1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("3", "股票期权申报-市价 IOC", 1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("6", "市价最优五档即时成交剩余撤销", 1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("7", "市价全额成交或撤销", 1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("8", "市价本方最优价格", 1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("9", "市价对手方最优价格", 1));
            allEntrustQuotedPriceWays.add(new OptionEntrustPriceWayBean("10", "市价即时成交剩余撤销", 1));
        }

        //初始化 深市委托报价方式列表 121、122、123、124、125、130、131 下面的索引值，刚好是对应上面的添加元素到allEntrustQuotedPriceWays，语句的所要获取值的linenum减去allEntrustQuotedPriceWays添加值的第一行的linenum之差。
        if (sSEntrustQuotedPriceWays == null) {
            sSEntrustQuotedPriceWays = new ArrayList<OptionEntrustPriceWayBean>();
            sSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(7));
            sSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(8));
            sSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(9));
            sSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(10));
            sSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(11));
            sSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(2));
            sSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(3));
        }

        //初始化 沪市委托报价方式列表 130、131、132、133、134
        if (hSEntrustQuotedPriceWays == null) {
            hSEntrustQuotedPriceWays = new ArrayList<OptionEntrustPriceWayBean>();
            hSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(2));
            hSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(3));
            hSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(4));
            hSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(5));
            hSEntrustQuotedPriceWays.add(allEntrustQuotedPriceWays.get(6));
        }
        //添加自绘键盘
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtStockCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
        keyboardManager2 = TradeTools.initKeyBoard(mActivity, mEdtEntrustPrice, KeyboardManager.KEYBOARD_TYPE_DIGITAL);
    }

    @Override
    protected void initViews() {
        if (mBuyOrSell == 0) {
            mBuyOrSellName = mResources.getString(R.string.option_main3);
            //买入开仓
            mRadioGroup.setVisibility(View.VISIBLE);//备兑券开仓不需要显示认购，认沽
        } else if (mBuyOrSell == 1) {
            //卖出开仓
            mBuyOrSellName = mResources.getString(R.string.option_main4);
            mRadioGroup.setVisibility(View.VISIBLE);//备兑券开仓不需要显示认购，认沽
        } else if (mBuyOrSell == 2) {
            //备兑开仓
            mBuyOrSellName = mResources.getString(R.string.option_main7);
            mRadioGroup.setVisibility(View.GONE);//备兑券开仓不需要显示认购，认沽
        }
    }

    @Override
    protected void setTheme() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == 1 || requestCode == 2) {
            //选取日期或者价格后都可能要重新请求305001接口
            if (requestCode == 1) {
                //选择到期月份后的返回值
                if (resultCode == 88) {
                    //正常的返回结果
                    int position = data.getIntExtra("data_select_result", 0);
                    selectedExerciseEndDate = optionExerciseEndDateBeans.get(position);
                    Log.d("data_select_result", "index:" + position + " selectedExerciseEndDate:" + selectedExerciseEndDate.getExe_end_date());
                    //设置返回结果
                    mTvCutDate.setText(selectedExerciseEndDate.getExe_end_date());
                }
            } else if (requestCode == 2) {
                //选择行权价格之后的返回值
                if (resultCode == 88) {
                    int position = data.getIntExtra("data_select_result", 0);
                    selectedExercisePrice = optionExercisePriceBeans.get(position);
                    Log.d("data_select_result", "index:" + position + " selectedExercisePrice:" + selectedExercisePrice.getExercise_price());
                    mTvExercisePrice.setText(selectedExercisePrice.getExercise_price());
                }
            }
            //判断如果行权日期和行权价格是否已经设置了，使用现有的 标的证券代码、行权价格、行权年月作为参数，再次请求  期权代码信息查询（305001）  ,这个条件是，选了到期月份后，要重新查询行权价格集合
            if ((requestCode == 1 && resultCode == 88) || (selectedExerciseEndDate != null && !"".equals(selectedExerciseEndDate.getExe_end_date()) && selectedExercisePrice != null && !"".equals(selectedExercisePrice.getExercise_price()))) {
                CharSequence temp = mEdtStockCode.getText();
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("stockCode", temp.toString());
                params.put("exerciseEndDate", selectedExerciseEndDate.getExe_end_date());
                if (requestCode != 1) {
                    //选择完，月份和价格后的查询，需要这个参数
                    params.put("exercisePrice", selectedExercisePrice.getExercise_price());
                }
                if (mBuyOrSell == 0) {
                    //买入开仓
                    params.put("entrustBs", "0"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                    params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                    params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                } else if (mBuyOrSell == 1) {
                    //卖出开仓
                    params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                    params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                    params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                } else if (mBuyOrSell == 2) {
                    //备兑开仓
                    params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                    params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                    params.put("coveredFlag", "1"); //备兑标志 0：不是备兑  1：是备兑
                }
                params.put("optionType", mOptionType); //期权类别  0：认购 1：认沽
                if (requestCode != 1) {
                    //选择完，月份和价格后的查询
                    mServices.requestStockOptionInfo("optionInfo2", params);
                } else {
                    //选了到期月份后，要重新查询行权价格集合
                    mServices.requestStockOptionInfo("optionInfo4", params);
                }
            }
        } else if (requestCode == 3) {
            //选择委托 报价方式之后的返回值
            if (resultCode == 88) {
                int position = data.getIntExtra("data_select_result", 0);
                selectedEntrustQuotedPriceWay = curEntrustQuotedPriceWays.get(position);
                Log.d("data_select_result", "index:" + position + " selectedEntrustQuotedPriceWay:" + selectedEntrustQuotedPriceWay);
                mTvSelectSubBs.setText(selectedEntrustQuotedPriceWay.getEntrust_way_name());
                int entrust_way_type = selectedEntrustQuotedPriceWay.getEntrust_way_type();
                if (entrust_way_type == 1) {
                    //如果是市价要设置 委托价格为不可输入
                    mLlInputPrice.setBackgroundColor(Color.TRANSPARENT);
                    mTvPriceCut.setBackgroundDrawable(mResources.getDrawable(R.drawable.common_all_corner_gray));
                    mTvPriceCut.setClickable(false);
                    mTvPriceAdd.setBackgroundDrawable(mResources.getDrawable(R.drawable.common_all_corner_gray));
                    mTvPriceAdd.setClickable(false);
                    mEdtEntrustPrice.setTextColor(mResources.getColor(R.color.trade_text_color9));
                    mEdtEntrustPrice.setFocusable(false);
                } else {
                    //如果是限价要设置 委托价格为可输入
                    mLlInputPrice.setBackgroundColor(mResources.getColor(R.color.trade_color_white));
                    mTvPriceCut.setBackgroundDrawable(mResources.getDrawable(R.drawable.common_all_corner_shi));
                    mTvPriceCut.setClickable(true);
                    mTvPriceAdd.setBackgroundDrawable(mResources.getDrawable(R.drawable.common_all_corner_shi));
                    mTvPriceAdd.setClickable(true);
                    mEdtEntrustPrice.setTextColor(mResources.getColor(R.color.trade_text_color6));
                    mEdtEntrustPrice.setFocusable(true);
                    mEdtEntrustPrice.setFocusableInTouchMode(true);
                }
            }
        }
    }

    /**
     * 当委托价格发生改变时
     * 需要联动最大可用量
     * 多重判断 1.证券校验完成 2.现价不能为空 3.现价不等于上次委托价  4.买入才实时联动
     */
    public void onInPutPriceChange() {
        String curPrice = mEdtEntrustPrice.getText().toString();
        if (!TextUtils.isEmpty(curPrice)
                && !curPrice.equals(mLastEntrustPrice) && mBuyOrSell == 1) {
        }
    }

    /**
     * 点击RadioGroup
     */
    public void onChickChange(int checkId) {
        if (checkId == R.id.rb_option_subscription) { //认购
            mRbSubscription.setTextColor(mResources.getColor(R.color.trade_color_white));
            mRbPutOption.setTextColor(mResources.getColor(R.color.trade_buy));
            mOptionType = "0";
        } else if (checkId == R.id.rb_option_put) { // 认沽
            mRbSubscription.setTextColor(mResources.getColor(R.color.trade_buy));
            mRbPutOption.setTextColor(mResources.getColor(R.color.trade_color_white));
            mOptionType = "1";
        }
    }

    /**
     * 点击证券名称
     */
    public void onClickStockName() {
        // 注意！此处代码务必和 mEdtStockCode 焦点获取监听事件一致
        if (mEdtStockCode.hasFocus()) {
            mKeyboardManager.show();
        } else {
            mEdtStockCode.performClick();
            mEdtStockCode.requestFocus();
        }
    }

    /**
     * 点击选择到期月份
     */
    public void onClickSelectCutDate() {
        if (optionExerciseEndDateBeans == null || optionExerciseEndDateBeans.size() == 0) {
            ToastUtils.toast(mActivity, R.string.option_error5);
        } else {
            Intent intent = new Intent(mActivity, OptionSelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select", optionExerciseEndDateBeans);
            intent.putExtra("useType", "option_exercise_end_date");
            startActivityForResult(intent, 1);
        }
    }

    /**
     * 点击选择行权价格
     */
    public void onClickSelectExercisePrice() {
        if (optionExercisePriceBeans == null || optionExercisePriceBeans.size() == 0) {
            ToastUtils.toast(mActivity, R.string.option_error5);
        } else {
            Intent intent = new Intent(mActivity, OptionSelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select", optionExercisePriceBeans);
            intent.putExtra("useType", "option_exercise_price");
            startActivityForResult(intent, 2);
        }
    }

    /**
     * 点击选择报价方式
     */
    public void onClickSelectSubType() {
        if(curEntrustQuotedPriceWays == null){
            ToastUtils.toast(getContext(),R.string.option_error6);
        }else{
            Intent intent = new Intent(mActivity, OptionSelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select", curEntrustQuotedPriceWays);
            intent.putExtra("useType", "option_entrust_quoted_price_ways");
            startActivityForResult(intent, 3);
        }
    }

    /**
     * 点击价格减
     */
    public void onClickPriceCut() {
        double price = getDoubleEntrustPrice();
        if (price != 0) {
            mEdtEntrustPrice.setText(formatDouble(price - minPriceStep, returnDecimalPlaces(minPriceStep)) + "");
        }
    }

    /**
     * 点击价格加
     */
    public void onClickPriceAdd() {
        double price = getDoubleEntrustPrice();
        if (price != 0) {
            mEdtEntrustPrice.setText(formatDouble(price + minPriceStep, returnDecimalPlaces(minPriceStep)) + "");
        }
    }

    /*
      * 判断是否为浮点数，包括double和float
      * @param str 传入的字符串
      * @return 是浮点数返回true,否则返回false
    */
    public boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 返回double类型的数据保留scale位小数的结果，int是要保留的小数位数，不足位补0
     *
     * @param value
     * @param scale
     * @return
     */
    public double formatDouble(double value, int scale) {
        DecimalFormat decimalFormat = new DecimalFormat(returnDemcimalPlacesStr(scale));
        decimalFormat.setMinimumFractionDigits(scale);//设置最少需要的小数位数，不足时补0
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String temp = decimalFormat.format(value);
        return Double.parseDouble(temp);
    }

    /**
     * 返回double类型的数据的小数位数
     *
     * @param value
     * @return
     */
    public int returnDecimalPlaces(double value) {
        int places = 0;
        int index = Double.toString(value).indexOf(".");
        if (index >= 0) {
            places = Double.toString(value).substring(index + 1).length();
        }
        return places;
    }

    /**
     * 返回要保留的小数位数对应的正则表达式
     *
     * @param value
     * @return
     */
    public String returnDemcimalPlacesStr(int value) {
        String str = "0";
        if (value != 0) {
            str = "##0" + ".";
            for (int i = 1; i <= value; i++) {
                str += "0";
            }
        }
        return str;
    }

    /**
     * 点击数量减
     */
    public void onClickNumCut() {
        int num = getIntEntrustNum();
        if (num != 0) {
            mEdtEntrustNum.setText(String.valueOf(num - 100));
        }
    }

    /**
     * 点击数量加
     */
    public void onClickNumAdd() {
        int num = getIntEntrustNum();
        if (num != 0) {
            mEdtEntrustNum.setText(String.valueOf(num + 100));
        }
    }

    /**
     * 点击 “全部”
     */
    public void onClickMaxAll() {
        mEdtEntrustNum.setText(mTvMaxEnable.getText().toString());
    }

    /**
     * 点击提交委托按钮
     */
    public void onClickBtnCommit() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        final HashMap<String, String> legalParams = new HashMap<String, String>();//通过验证后，合法的数据
        String stockCode = getStringStockCode();//标的证券代码
        String stockName = getStockName();//到期月份
        String exercisePrice = getSelectedExerciseEndDate();//行权价格
        String optionCode = getOptionCode();//合约代码
        String entrustQuotedPriceWay = getSelectedEntrustQuotedPriceWay();//报价方式
        String entrustPrice = getStringEntrustPrice();//委托价格
        String entrustAmount = getStringEntrustNum();//委托数量
        String enableAmount = getStringEnableAmount();//最大可用数量
        //检测是否输入了正确的标的证券代码
        if ((TextUtils.isEmpty(stockCode) || !TextUtils.isDigitsOnly(stockCode) || stockCode.length() != 6)) {
            ToastUtils.toast(getActivity(), getString(R.string.option_error21));
            return;
        }
        //需要选择到期月份
        if (TextUtils.isEmpty(stockName)) {
            ToastUtils.toast(mActivity, R.string.option_error7);
            return;
        }
        //需要选择行权价格
        if (TextUtils.isEmpty(stockName)) {
            ToastUtils.toast(mActivity, R.string.option_error8);
            return;
        }
        //需要选择报价方式
        if (TextUtils.isEmpty(entrustQuotedPriceWay)) {
            ToastUtils.toast(mActivity, R.string.option_error9);
            return;
        }
        //如果是报价方式是限价，需要输入委托价格，且委托价格必须满足要求
        if (selectedEntrustQuotedPriceWay != null) {
            if (selectedEntrustQuotedPriceWay.getEntrust_way_type() == 0) {
                //报价方式，才需要输入价格
                if (TextUtils.isEmpty(entrustPrice) || !(TradeUtils.isNumeric(entrustPrice) ||this.isDouble(entrustPrice))) {
                    ToastUtils.toast(mActivity, R.string.option_error10);
                    return;
                }
            }
        } else {
            ToastUtils.toast(getContext(), R.string.option_error9);
        }
        //需要输入委托数量，且委托数量必须满足要求
        if (TextUtils.isEmpty(entrustAmount) || !TextUtils.isDigitsOnly(entrustAmount) || !isInteger(entrustAmount)) {
            ToastUtils.toast(mActivity, R.string.option_error11);
            return;
        } else {
            if (Integer.parseInt(enableAmount) - Integer.parseInt(entrustAmount) < 0) {
                //委托数量，不能操作最多可用数量
                ToastUtils.toast(getContext(), getString(R.string.option_error20));
                return;
            }
        }
        //验证通过后，将各个值加入
        legalParams.put("stockCode", stockCode);
        legalParams.put("stockName", stockName);
        legalParams.put("exercisePrice", exercisePrice);
        legalParams.put("optionCode", optionCode);
        legalParams.put("entrustQuotedPriceWay", entrustQuotedPriceWay);
        legalParams.put("entrustPrice", entrustPrice);
        legalParams.put("entrustAmount", entrustAmount);
        //弹出提示确认框
        AbsOptionComfirmDialog entrustSubmitComfirmDialog = new AbsOptionComfirmDialog(mActivity) {
            //初始化内容界面，以及数据
            {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_trade_comfirm, null);
                setSubViewToParent(view);
                // 显示证券名称
                TextView stockNameTextView = (TextView) view.findViewById(R.id.tv_pop_name);
                // 显示证券代码
                TextView stockCodeTextView = (TextView) view.findViewById(R.id.tv_pop_code);
                //买卖方向
                TextView buyOrSaleView = (TextView) view.findViewById(R.id.tv_pop_buy);
                if (mBuyOrSell == 0) { // 如果单击的是买入
                    buyOrSaleView.setText(R.string.trade_buying);
                } else if (mBuyOrSell == 1) { // 如果单机的是卖出
                    buyOrSaleView.setText(R.string.trade_sell);
                }
                // 显示委托价格
                TextView entrustPriceTextView = (TextView) view.findViewById(R.id.tv_pop_price);
                // 显示委托数量
                TextView entrustNumTextView = (TextView) view.findViewById(R.id.tv_pop_entrust_number);
                stockNameTextView.setText(legalParams.get("stockCode"));
                stockCodeTextView.setText(legalParams.get("stockName"));
                entrustPriceTextView.setText(legalParams.get("entrustPrice"));
                entrustNumTextView.setText(legalParams.get("entrustAmount"));
            }

            @Override
            protected void onClickOk() {
                submitEntry(legalParams);
                dismiss();
            }
        };
        entrustSubmitComfirmDialog.show();
    }

    /**
     * 点击五档盘
     * 获取到当前五档的价格，设置给委托价格输入框
     */
    public void onClickBuyOrSaleDisk(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ll_buy1) {
            mEdtEntrustPrice.setText(mTvBuy1Price.getText().toString());
        } else if (viewId == R.id.ll_buy2) {
            mEdtEntrustPrice.setText(mTvBuy2Price.getText().toString());
        } else if (viewId == R.id.ll_buy3) {
            mEdtEntrustPrice.setText(mTvBuy3Price.getText().toString());
        } else if (viewId == R.id.ll_buy4) {
            mEdtEntrustPrice.setText(mTvBuy4Price.getText().toString());
        } else if (viewId == R.id.ll_buy5) {
            mEdtEntrustPrice.setText(mTvBuy5Price.getText().toString());
        } else if (viewId == R.id.ll_sale1) {
            mEdtEntrustPrice.setText(mTvSale1Price.getText().toString());
        } else if (viewId == R.id.ll_sale2) {
            mEdtEntrustPrice.setText(mTvSale2Price.getText().toString());
        } else if (viewId == R.id.ll_sale3) {
            mEdtEntrustPrice.setText(mTvSale3Price.getText().toString());
        } else if (viewId == R.id.ll_sale4) {
            mEdtEntrustPrice.setText(mTvSale4Price.getText().toString());
        } else if (viewId == R.id.ll_sale5) {
            mEdtEntrustPrice.setText(mTvSale5Price.getText().toString());
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
     * 在成功从服务器获取五档买卖盘数据时调用
     * 设置五档买卖盘数据和当前委托价格数据（当前委托价格，买入时取买一，卖出时取卖一）。
     */
    public void onGetWuDangDishData(StockBuySellDish bean) {
        ArrayList<String> priceColorList = bean.getPriceColorsList();
        ArrayList<String> valueList = bean.getValueBuySale();
        setWuDangDataToViews(mTvSale5Price, valueList.get(0), priceColorList.get(0));
        setWuDangDataToViews(mTvSale4Price, valueList.get(1), priceColorList.get(1));
        setWuDangDataToViews(mTvSale3Price, valueList.get(2), priceColorList.get(2));
        setWuDangDataToViews(mTvSale2Price, valueList.get(3), priceColorList.get(3));
        setWuDangDataToViews(mTvSale1Price, valueList.get(4), priceColorList.get(4));
        setWuDangDataToViews(mTvSale5Amount, valueList.get(5), priceColorList.get(5));
        setWuDangDataToViews(mTvSale4Amount, valueList.get(6), priceColorList.get(6));
        setWuDangDataToViews(mTvSale3Amount, valueList.get(7), priceColorList.get(7));
        setWuDangDataToViews(mTvSale2Amount, valueList.get(8), priceColorList.get(8));
        setWuDangDataToViews(mTvSale1Amount, valueList.get(9), priceColorList.get(9));
        setWuDangDataToViews(mTvBuy1Price, valueList.get(10), priceColorList.get(10));
        setWuDangDataToViews(mTvBuy2Price, valueList.get(11), priceColorList.get(11));
        setWuDangDataToViews(mTvBuy3Price, valueList.get(12), priceColorList.get(12));
        setWuDangDataToViews(mTvBuy4Price, valueList.get(13), priceColorList.get(13));
        setWuDangDataToViews(mTvBuy5Price, valueList.get(14), priceColorList.get(14));
        setWuDangDataToViews(mTvBuy1Amount, valueList.get(15), priceColorList.get(15));
        setWuDangDataToViews(mTvBuy2Amount, valueList.get(16), priceColorList.get(16));
        setWuDangDataToViews(mTvBuy3Amount, valueList.get(17), priceColorList.get(17));
        setWuDangDataToViews(mTvBuy4Amount, valueList.get(18), priceColorList.get(18));
        setWuDangDataToViews(mTvBuy5Amount, valueList.get(19), priceColorList.get(19));
        /*if (mBuyOrSell == 0) { // 如果是买入，设置卖1价格到股票价格数据框中
            String buyOne = valueList.get(10);
            if (buyOne != null && !TextUtils.isEmpty(buyOne)) {
                float buyFloat = Float.parseFloat(buyOne);
                if (buyFloat > 0) {
                    mLastEntrustPrice = buyOne;
                } else if (buyFloat <= 0) {
                    mLastEntrustPrice = mNowPrice;
                }
            }
        } else if (mBuyOrSell == 1) { // 如果是卖出，设置买1价格到股票价格数据框中
            String saleOne = valueList.get(4);
            if (saleOne != null && !TextUtils.isEmpty(saleOne)) {
                float saleFloat = Float.parseFloat(saleOne);
                if (saleFloat > 0) {
                    mLastEntrustPrice = saleOne;
                } else if (saleFloat <= 0) {
                    mLastEntrustPrice = mNowPrice;
                }
            }
        }
        mEdtEntrustPrice.setText(mLastEntrustPrice);*/
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
     * 行情20000接口请求成功
     */
    public void onGetHq20000Data(CodeTableBean bean) {
        mCodeTableBean = bean;
        mTvStockName.setText(bean.getName());
        mNowPrice = (TradeUtils.formatDouble2(bean.getNow()));
    }

    /**
     * 请求行情接口发现股票停牌时
     * 清空所有数据，只保留名称和代码
     */
    public void onGetSuspendStock(String stockName, String stockCode) {
        mTvStockName.setText(stockName);
        HashSet<String> ignoreFlagSet = new HashSet<String>();
        ignoreFlagSet.add("queryByStockCode");
        onClearAllData(ignoreFlagSet);
    }

    /**
     * 清空界面上的所有数据
     *
     * @param ignoreFlagSet 清除所有数据，ignoreFlagSet是要忽略，不进行清除的元素的标志集合。传null时，清除全部
     *
     */
    public void onClearAllData(HashSet<String> ignoreFlagSet) {
        if (ignoreFlagSet !=null && !ignoreFlagSet.contains("queryByStockCode")) {
            setStockCodeOrContractCodeByProgram(mEdtStockCode, "");
            mTvStockName.setText("");
        }
        mTvCutDate.setText("");
        mTvExercisePrice.setText("");
        if (ignoreFlagSet !=null && !ignoreFlagSet.contains("queryByContractCode")) {
            setStockCodeOrContractCodeByProgram(mTvContractCode, "");
        }
        mTvContractName.setText("");
        mTvSelectSubBs.setText("");
        mTvMaxEnable.setText("");
        mEdtEntrustNum.setText("");
        mEdtEntrustPrice.setText("");
        mLastEntrustPrice = "";
        mNowPrice = "";
        clearWuDangData();

        //清除中间实体数据

        /**
         * 当前证券的行情信息
         */
        mCodeTableBean = null;
        /**
         * 获取的标的证券列表
         */
        optionInfoBeans = null;
        /**
         * 选择完行权日期和行权价格之后，重新请求 获取期权代码信息 后，返回的唯一结果
         */
        singleOptionInfoBean = null;
        /**
         * 获取的行权到期月份列表
         */
        optionExerciseEndDateBeans = null;
        /**
         * 选中的到期月份值
         */
        selectedExerciseEndDate = null;
        /**
         * 获取的行权价格列表
         */
        optionExercisePriceBeans = null;
        /**
         * 选中的行权价格
         */
        selectedExercisePrice = null;
        /**
         * 当前标的证券所属证券市场，对应的委托报价方式列表
         */
        curEntrustQuotedPriceWays = null;
        /**
         * 当前选择的 委托报价方式
         */
        selectedEntrustQuotedPriceWay = null;
    }

    /**
     * 使用程序来设置标的证券代码或者证券合约编码时，必须设置一个标志isTextChangedByUserOperation的值
     * ，防止因程序设置的值，引起文本变化事件（该事件，只在用户进行输入，删除，修改的时候，应该被触发）。
     *
     * @param e     要设置值的编辑框对象
     * @param value 要设置的值
     */
    public void setStockCodeOrContractCodeByProgram(EditText e, String value) {
        isTextChangedByUserOperation = false;
        e.setText(value);
    }

    /**
     * 清空五档买卖盘和当前委托价格上的数据
     */
    public void clearWuDangData() {
        mTvSale5Price.setText("");
        mTvSale4Price.setText("");
        mTvSale3Price.setText("");
        mTvSale2Price.setText("");
        mTvSale1Price.setText("");
        mTvSale5Amount.setText("");
        mTvSale4Amount.setText("");
        mTvSale3Amount.setText("");
        mTvSale2Amount.setText("");
        mTvSale1Amount.setText("");
        mTvBuy1Price.setText("");
        mTvBuy2Price.setText("");
        mTvBuy3Price.setText("");
        mTvBuy4Price.setText("");
        mTvBuy5Price.setText("");
        mTvBuy1Amount.setText("");
        mTvBuy2Amount.setText("");
        mTvBuy3Amount.setText("");
        mTvBuy4Amount.setText("");
        mTvBuy5Amount.setText("");
        // 还原五档盘上TextView的颜色
        mTvSale5Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale4Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale3Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale2Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale1Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale5Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale4Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale3Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale2Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale1Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy1Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy2Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy3Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy4Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy5Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy1Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy2Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy3Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy4Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy5Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        // 清空委托价格数据
        mEdtEntrustPrice.setText("");
    }

    /**
     * 获取委托价格输入框上面的数据
     * 返回double类型的值
     */
    private double getDoubleEntrustPrice() {
        double result = 0;
        String priceEditTextContent = mEdtEntrustPrice.getText().toString().trim();
        if (!TextUtils.isEmpty(priceEditTextContent)) {
            try {
                result = Double.parseDouble(priceEditTextContent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取委托数量输入框上面的数据
     * 返回int类型的值
     */
    private int getIntEntrustNum() {
        int result = 0;
        String priceEditTextContent = mEdtEntrustNum.getText().toString().trim();
        if (!TextUtils.isEmpty(priceEditTextContent)) {
            try {
                result = Integer.parseInt(priceEditTextContent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取委托价格输入框上面的数据
     * 返回String类型的值
     */
    private String getStringEntrustPrice() {
        return mEdtEntrustPrice.getText().toString().trim();
    }

    /**
     * 获取委托数量输入框上面的数据
     * 返回String类型的值
     */
    private String getStringEntrustNum() {
        return mEdtEntrustNum.getText().toString().trim();
    }

    /**
     * 获取证券代码输入框上面的数据
     * 返回String类型的值
     */
    private String getStringStockCode() {
        return mEdtStockCode.getText().toString().trim();
    }

    /**
     * 获取标的证券名称
     *
     * @return
     */
    private String getStockName() {
        return mTvStockName.getText().toString().trim();
    }

    /**
     * 获取行权月份
     *
     * @return
     */
    private String getSelectedExerciseEndDate() {
        return mTvCutDate.getText().toString().trim();
    }

    /**
     * 获取行权价格
     *
     * @return
     */
    private String getSelectedExercisePrice() {
        return mTvExercisePrice.getText().toString().trim();
    }

    /**
     * 获取合约代码
     *
     * @return
     */
    private String getOptionCode() {
        return mTvContractCode.getText().toString().trim();
    }

    /**
     * 获取报价方式
     *
     * @return
     */
    private String getSelectedEntrustQuotedPriceWay() {
        return mTvSelectSubBs.getText().toString().trim();
    }

    /**
     * 获取可用数量
     *
     * @return
     */
    private String getStringEnableAmount() {
        return (!"".equals(mTvMaxEnable.getText().toString().trim())) ? (mTvMaxEnable.getText().toString().trim()) : "0";
    }

    /***
     * 判断 String 是否int
     *
     * @param input
     * @return
     */
    private boolean isInteger(String input) {
        Matcher mer = Pattern.compile("^[0-9]+$").matcher(input);
        return mer.find();
    }

    /**
     * 获取期权代码信息，并显示到对应的位置
     *
     * @param flag                       用于标识是什么请求的返回结果
     * @param optionInfoBeans            期权代码信息列表
     * @param optionExerciseEndDateBeans 行权日期列表
     * @param optionExercisePriceBeans   行权价格列表
     */
    public void getStockOptionInfoAndShow(String flag, ArrayList<OptionInfoBean> optionInfoBeans, ArrayList<OptionExerciseEndDateBean> optionExerciseEndDateBeans, ArrayList<OptionExercisePriceBean> optionExercisePriceBeans) {
        if ("optionInfo".equals(flag)) {
            //输入标的证券后的查询
            if (optionInfoBeans == null || optionInfoBeans.size() == 0
                    || optionExerciseEndDateBeans == null || optionExerciseEndDateBeans.size() == 0
                    || optionExercisePriceBeans == null || optionExercisePriceBeans.size() == 0) {
                ToastUtils.toast(getContext(), R.string.option_error14);
            } else {
                this.optionInfoBeans = optionInfoBeans;
                OptionInfoBean tempBean = optionInfoBeans.get(0);
                String stock_name = tempBean.getStock_name();//证券名称
                String stock_code = tempBean.getStock_code();//证券代码
                String exchange_type = tempBean.getExchange_type();
                //交易市场类别exchange_type（ 根据交易市场类别的数字字符串，第一位为0为深市，第一位为1为沪市），设置报价方式的集合
                UserInfo sOptionUserInfo_shen = TradeLoginManager.sOptionUserInfo_shen;
                String stock_account_shen = sOptionUserInfo_shen.getStock_account();
                UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
                String stock_account_hu = sOptionUserInfo_hu.getFund_account();
                if (!"".equals(exchange_type) && exchange_type.charAt(0) == '0') {
                    //深市
                    if (stock_account_shen != null && !"".equals(stock_account_shen)) {
                        curEntrustQuotedPriceWays = hSEntrustQuotedPriceWays;
                    } else {
                        ToastUtils.toast(mActivity, getString(R.string.option_error12));
                        return;
                    }
                } else if (!"".equals(exchange_type) && exchange_type.charAt(0) == '1') {
                    //沪市
//                    if (fund_account_hu != null && !"".equals(fund_account_hu)) {
                    if(1 ==1){
                        curEntrustQuotedPriceWays = hSEntrustQuotedPriceWays;
                    } else {
                        ToastUtils.toast(mActivity, getString(R.string.option_error13));
                        return;
                    }
                } else {
                    //其他
                    String error = "对不起，您没有" + stock_code + "该标的证券的操作权限，请咨询管理员";
                    LogUtil.printLog("e", error);
                    ToastUtils.toast(mActivity, error);
                    return;
                }
                this.optionExerciseEndDateBeans = optionExerciseEndDateBeans;
                this.optionExercisePriceBeans = optionExercisePriceBeans;
            }
        } else if ("optionInfo2".equals(flag)) {
            //选择完到期月份和行权价格后的查询
            if (optionInfoBeans == null || optionInfoBeans.size() == 0) {


            } else if (optionInfoBeans.size() == 1) {
                singleOptionInfoBean = optionInfoBeans.get(0);
                //设置标的证券 ,这里不设置，因为五档盘的请求里面集合了设置标的证券名称的。
                String stock_name = singleOptionInfoBean.getStock_name();//证券名称
                String stock_code = singleOptionInfoBean.getStock_code();//证券代码
                //设置合约代码
                String option_code = singleOptionInfoBean.getOption_code();//期权合约编码
                setStockCodeOrContractCodeByProgram(mTvContractCode, option_code);
                //设置合约名称
                String option_name = singleOptionInfoBean.getOption_name();//期权名称
                mTvContractName.setText(option_name);//设置合约名称
                //设置委托价格区间
                String min_price = singleOptionInfoBean.getMin_price();//跌停价格
                String max_price = singleOptionInfoBean.getMax_price();//涨停价格
                mEdtEntrustPrice.setHint(min_price+"~"+max_price);

                //设置最小价差
                minPriceStep = Double.parseDouble(singleOptionInfoBean.getOpt_price_step());//最小价差
                //设置最多可平
                String enable_amount = singleOptionInfoBean.getEnable_amount();//可用数量
                mTvMaxEnable.setText(enable_amount);
            } else {

            }

        } else if ("optionInfo3".equals(flag)) {
            //输入合约代码后的返回结果
            if (optionInfoBeans == null || optionInfoBeans.size() == 0) {
                ToastUtils.toast(mActivity, getString(R.string.option_error19));
            } else {
                this.optionInfoBeans = optionInfoBeans;
                //获取需要用到的返回值
                OptionInfoBean tempBean = optionInfoBeans.get(0);
                String stock_name = tempBean.getStock_name();//证券名称
                String stock_code = tempBean.getStock_code();//证券代码
                String exerciseMonth = tempBean.getExercise_month();//到期月份
                String exercisePrice = tempBean.getExercise_price();//行权价格
                String optionName = tempBean.getOption_name();//合约名称
                String enableAmount = tempBean.getEnable_amount();//最多可平
                String exchange_type = tempBean.getExchange_type();//交易市场类别exchange_type（ 根据交易市场类别的数字字符串，第一位为0为深市，第一位为1为沪市），设置报价方式的集合
                //对显示值进行设置
                //报价方式
                UserInfo sOptionUserInfo_shen = TradeLoginManager.sOptionUserInfo_shen;
                String stock_account_shen = sOptionUserInfo_shen.getStock_account();
                UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
                String stock_account_hu = sOptionUserInfo_hu.getStock_account();
                if (!"".equals(exchange_type) && exchange_type.charAt(0) == '0') {
                    //深市
                    if (stock_account_shen != null && !"".equals(stock_account_shen)) {
                        curEntrustQuotedPriceWays = hSEntrustQuotedPriceWays;
                    } else {
                        ToastUtils.toast(mActivity, getString(R.string.option_error12));
                        return;
                    }
                } else if (!"".equals(exchange_type) && exchange_type.charAt(0) == '1') {
                    //沪市
//                    if (fund_account_hu != null && !"".equals(fund_account_hu)) {
                    if(1 ==1){
                        curEntrustQuotedPriceWays = hSEntrustQuotedPriceWays;
                    } else {
                        ToastUtils.toast(mActivity, getString(R.string.option_error13));
                        return;
                    }
                } else {
                    //其他
                    String error = "对不起，您没有" + stock_code + "该标的证券的操作权限，请资讯管理员";
                    LogUtil.printLog("e", error);
                    ToastUtils.toast(mActivity, error);
                    return;
                }
                //根据合约编码查询305001后，再返回标的证券编码，查询五档行情数据
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("stockCode", stock_code);
                if (mBuyOrSell == 0) {
                    //买入开仓
                    params.put("entrustBs", "0"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                    params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                    params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                } else if (mBuyOrSell == 1) {
                    //卖出开仓
                    params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                    params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                    params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
                } else if (mBuyOrSell == 2) {
                    //备兑开仓
                    params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
                    params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
                    params.put("coveredFlag", "1"); //备兑标志 0：不是备兑  1：是备兑
                }
                params.put("optionType", mOptionType); //期权类别  0：认购 1：认沽
                mServices.startLinkage(params);
                setStockCodeOrContractCodeByProgram(mEdtStockCode, stock_code);//设置标的证券显示内容
                this.singleOptionInfoBean = optionInfoBeans.get(0);
                //因为是根据合约代码查询的，所以行权日期和行权价格都是确定的
                //设置当前选定的行权日期，以及可选的行权日期集合
                mTvCutDate.setText(exerciseMonth);//到期月份
                this.selectedExerciseEndDate = new OptionExerciseEndDateBean();
                this.selectedExerciseEndDate.setExe_end_date(exerciseMonth);
                this.optionExerciseEndDateBeans = new ArrayList<OptionExerciseEndDateBean>();
                this.optionExerciseEndDateBeans.add(this.selectedExerciseEndDate);
                //设置当前选定的行权价格，以及可选的行权价格集合
                mTvExercisePrice.setText(exercisePrice);//行权价格
                this.selectedExercisePrice = new OptionExercisePriceBean();
                this.selectedExercisePrice.setExercise_price(exercisePrice);
                this.optionExercisePriceBeans = new ArrayList<OptionExercisePriceBean>();
                this.optionExercisePriceBeans.add(this.selectedExercisePrice);
                mTvContractName.setText(optionName);//合约名称
                mTvMaxEnable.setText(enableAmount);//最多可平
            }
        } else if ("optionInfo4".equals(flag)) {
            //选了到期月份后，要重新查询行权价格集合
            if (optionInfoBeans == null || optionInfoBeans.size() == 0
                    || optionExerciseEndDateBeans == null || optionExerciseEndDateBeans.size() == 0
                    || optionExercisePriceBeans == null || optionExercisePriceBeans.size() == 0) {
                //如果没有，则价格设置为不可选

            } else {
                this.optionExercisePriceBeans = optionExercisePriceBeans;
            }
        } else if("optionInfo5".equals(flag)){
            //填写了委托价格后，再次去查询305001，获得最多可平的数据
            if (optionInfoBeans == null || optionInfoBeans.size() == 0) {
                ToastUtils.toast(mActivity, getString(R.string.option_error19));
            } else {
                //获取需要用到的返回值
                OptionInfoBean tempBean = optionInfoBeans.get(0);
                String enableAmount = tempBean.getEnable_amount();//最多可平
                mTvMaxEnable.setText(enableAmount);//最多可平
            }
        }
    }

    /**
     * 提交委托
     */
    public void submitEntry(HashMap<String, String> legalParams) {
        //提交委托 下面设置，提交委托 请求的入参 ，有些参数，如果没有设置到界面上去，或者用户对这些参数不会进行修改，然后已有变量保存了这些值，可以直接从变量中获取。其他的值可选择从界面上获取
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("exchangeType", singleOptionInfoBean.getExchange_type()); // 交易市场类别
        params.put("entrustPropOpt", selectedEntrustQuotedPriceWay.getEntrust_way_code()); // 委托属性 即报价方式
        params.put("optionCode", singleOptionInfoBean.getOption_code()); //期权合约编码
        params.put("entrustAmount", legalParams.get("entrustPrice")); //委托数量
        params.put("entrustPrice", legalParams.get("entrustAmount")); //委托价格
        if (mBuyOrSell == 0) {
            //买入开仓
            params.put("entrustBs", "0"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
            params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
        } else if (mBuyOrSell == 1) {
            //卖出开仓
            params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
            params.put("coveredFlag", "0"); //备兑标志 0：不是备兑  1：是备兑
        } else if (mBuyOrSell == 2) {
            //备兑开仓
            params.put("entrustBs", "1"); //  委托标志 0：买入，1：卖出 另外，备兑开仓entrust_bs传1 备兑平仓entrust_bs传0
            params.put("entrustOc", "0"); // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
            params.put("coveredFlag", "1"); //备兑标志 0：不是备兑  1：是备兑
        }
        params.put("stockAccount", singleOptionInfoBean.getStock_account()); // 证券账户
        params.put("optionType", mOptionType); //期权类别  0：认购 1：认沽
        mServices.requestEntrustOrder(params);
    }

    /**
     * 显示委托下单的结果
     *
     * @param optionEntrustOrderBeans
     */
    public void showEntrustOrderResult(ArrayList<OptionEntrustOrderBean> optionEntrustOrderBeans) {
        try {
            OptionEntrustOrderBean tempBean = optionEntrustOrderBeans.get(0);
            String init_date = tempBean.getInit_date();//交易日期
            String entrust_no = tempBean.getEntrust_no();//委托编号
            String report_no = tempBean.getReport_no();//申请编号
            String batch_no = tempBean.getBatch_no();//委托批号
            String entrust_time = tempBean.getEntrust_time();//委托时间
            String tip = "您好，委托成功。\n交易日期为：" + init_date + "\n委托编号为：" + entrust_no + "\n申请编号为：" + report_no + "\n委托批号为:" + batch_no + "\n委托时间为:" + entrust_time;
            ToastUtils.toast(mActivity, tip);
        } catch (Exception e) {
            LogUtil.printLog("e", "调用305005出错，错误信息：" + e.getStackTrace());
        }
    }
}



