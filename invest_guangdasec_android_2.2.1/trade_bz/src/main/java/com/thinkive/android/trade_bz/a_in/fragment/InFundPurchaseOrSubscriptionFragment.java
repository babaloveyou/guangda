package com.thinkive.android.trade_bz.a_in.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.bean.InFundOrderBean;
import com.thinkive.android.trade_bz.a_in.bean.InFundQueryBean;
import com.thinkive.android.trade_bz.a_in.bll.InFundPurchaseOrSubscriptionOrRedemptionImpl;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * Description：场内货币基金申购、LOF基金认购、LOF基金申购 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/9 <br>
 */
public class InFundPurchaseOrSubscriptionFragment extends AbsBaseFragment {
    private int fragmentFlag;//fragment标志
    private String fragmentName;//fragment的名称
    private String entrustBs;//委托标志
    private InFundPurchaseOrSubscriptionOrRedemptionImpl service;
    private KeyboardManager mKeyboardManager;//框架内，自绘键盘管理器
    private InFundQueryBean inFundQueryBean;//输入基金代码后的返回结果对应的实体
    private EditText tvInFundCodeInput;//基金代码
    private TextView tvInFundNameShow;//基金名称
    private TextView tvInFundNetValueShow;//基金净值
    private LinearLayout inFundSubscriptionUpperLimitLl;//认购上限LinearLayout
    private TextView inFundSubscriptionUpperLimitTitle;//认购上限标题显示
    private TextView tvInFundSubscriptionUpperLimitShow;//认购上限
    private TextView tvInFundSubscriptionAllButton;//认购上限，全部按钮
    private LinearLayout inFundSubscriptionMoneyLl;//认购份额LinearLayout
    private TextView inFundSubscriptionMoneyTitle;//认购金额标题显示
    private EditText tvInFundSubscriptionMoneyInput;//认购金额
//    private LinearLayout inFundPurchaseUpperLimitLl;//申购上限LinearLayout
//    private TextView tvInFundPurchaseUpperLimitShow;//申购上限
//    private TextView tvInFundPurchaseAllButton;//申购上限，全部按钮
//    private LinearLayout inFundPurchaseMoneyLl;//申购金额LinearLayout
//    private EditText tvInFundPurchaseMoneyInput;//申购金额
    private TextView tvInFundAvailableFundsShow;//可用资金
    private Button tvInFundPurchaseOrSubscriptionSubmit;//提交按钮

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_in_fund_purchase_or_subscription, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        tvInFundCodeInput = (EditText) view.findViewById(R.id.tv_in_fund_code_input);
        tvInFundNameShow = (TextView) view.findViewById(R.id.tv_in_fund_name_show);
        tvInFundNetValueShow = (TextView) view.findViewById(R.id.tv_in_fund_net_value_show);
        inFundSubscriptionUpperLimitLl = (LinearLayout) view.findViewById(R.id.in_fund_subscription_upper_limit_ll);
        inFundSubscriptionUpperLimitTitle = (TextView) view.findViewById(R.id.in_fund_subscription_upper_limit_title);
        tvInFundSubscriptionUpperLimitShow = (TextView) view.findViewById(R.id.tv_in_fund_subscription_upper_limit_show);
        tvInFundSubscriptionAllButton = (TextView) view.findViewById(R.id.tv_in_fund_subscription_all_button);
        inFundSubscriptionMoneyLl = (LinearLayout) view.findViewById(R.id.in_fund_subscription_money_ll);
        inFundSubscriptionMoneyTitle = (TextView) view.findViewById(R.id.in_fund_subscription_money_title);
        tvInFundSubscriptionMoneyInput = (EditText) view.findViewById(R.id.tv_in_fund_subscription_money_input);
//        inFundPurchaseUpperLimitLl = (LinearLayout) view.findViewById(R.id.in_fund_purchase_upper_limit_ll);
//        tvInFundPurchaseUpperLimitShow = (TextView) view.findViewById(R.id.tv_in_fund_purchase_upper_limit_show);
//        tvInFundPurchaseAllButton = (TextView) view.findViewById(R.id.tv_in_fund_purchase_all_button);
//        inFundPurchaseMoneyLl = (LinearLayout) view.findViewById(R.id.in_fund_purchase_money_ll);
//        tvInFundPurchaseMoneyInput = (EditText) view.findViewById(R.id.tv_in_fund_purchase_money_input);
        tvInFundAvailableFundsShow = (TextView) view.findViewById(R.id.tv_in_fund_available_funds_show);
        tvInFundPurchaseOrSubscriptionSubmit = (Button) view.findViewById(R.id.tv_in_fund_purchase_or_subscription_submit);
    }

    /**
     * 设置事件
     */
    @Override
    protected void setListeners() {
        //基金代码输入框，文本内容变化事件
        tvInFundCodeInput.addTextChangedListener(new TextWatcher() {
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
                if(temp.length() == 6){
                    //查询数据
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put("entrustBs",entrustBs);
                    params.put("stockCode",temp.toString());
                    service.inFundQuery(params);
                }else{
                    clearAllData(null);
                }
            }
        });

        //认购上限，全部按钮 点击事件
        tvInFundSubscriptionAllButton.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                String netVauleStr = tvInFundNetValueShow.getText().toString();
                if(netVauleStr != null && !"".equals(netVauleStr)){
                    /*double netValue = Double.parseDouble(tvInFundNetValueShow.getText().toString());
                    int maxAmount = Integer.parseInt(tvInFundSubscriptionUpperLimitShow.getText().toString());
                    tvInFundSubscriptionMoneyInput.setText(formatDouble(netValue*maxAmount,returnDecimalPlaces(netValue))+"");*/
                    tvInFundSubscriptionMoneyInput.setText(tvInFundSubscriptionUpperLimitShow.getText().toString().trim());
                }else{
                    ToastUtils.toast(getActivity(),getString(R.string.in_fund_tip0));
                }
            }
        });

        //申购上限，全部按钮 点击事件
//        tvInFundPurchaseAllButton.setOnClickListener(new TextView.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //提交按钮 点击事件
        tvInFundPurchaseOrSubscriptionSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                HashMap<String,String> params = new HashMap<String, String>();
                String entrustBsInner = entrustBs;
                String exchangeType = inFundQueryBean.getExchange_type();//交易市场类别
                String stockAccount = inFundQueryBean.getStock_account();//证券账号
                String stockCode = tvInFundCodeInput.getText().toString().trim();//基金代码
                String entrustPrice = tvInFundNetValueShow.getText().toString().trim();//基金净值
                String entrustAmount = tvInFundSubscriptionMoneyInput.getText().toString().trim();//认购金额
                /**
                 * 入参校验
                 */
                if(inFundQueryBean == null){
                    ToastUtils.toast(getContext(),getString(R.string.in_fund_tip2));
                    return;
                }
                if(entrustAmount == null || TextUtils.isEmpty(entrustAmount)){
                    ToastUtils.toast(getContext(),getString(R.string.in_fund_tip3));
                    return;
                }else if(entrustAmount != null && Double.parseDouble(tvInFundAvailableFundsShow.getText().toString()) - Double.parseDouble(entrustAmount)<0){
                    if("17".equals(entrustBs)){
                        //认购
                        ToastUtils.toast(getContext(),getString(R.string.in_fund_tip12));
                    } else if("20".equals(entrustBs)){
                        //申购
                        ToastUtils.toast(getContext(),getString(R.string.in_fund_tip13));
                    }
                    return;
                }

                params.put("entrustBs",entrustBsInner);
                params.put("exchangeType",exchangeType);
                params.put("stockAccount",stockAccount);
                params.put("stockCode",stockCode);
                params.put("entrustPrice",entrustPrice);
                params.put("entrustAmount",entrustAmount);
                service.inFundOrderSubmit(params);
            }
        });

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null ){
            fragmentFlag = bundle.getInt("fragmentFlag",0);
        }
        service = new InFundPurchaseOrSubscriptionOrRedemptionImpl(this,null);
    }

    @Override
    protected void initViews() {
        //查询持有资金
        service.requestHoldFund();
        //添加自绘键盘
        mKeyboardManager = TradeTools.initKeyBoard(getActivity(), tvInFundCodeInput, KeyboardManager.KEYBOARD_TYPE_STOCK);
        if(fragmentFlag == 0){
            fragmentName = getResources().getString(R.string.in_monetary_fund_subscription_title);
//            entrustBs = "104";
            entrustBs = "17";
        }else if(fragmentFlag ==1){
            fragmentName = getResources().getString(R.string.in_monetary_fund_purchase_title);
//            entrustBs = "105";
            entrustBs = "20";
            inFundSubscriptionUpperLimitTitle.setText(R.string.in_fund_purchase_upper_limit_title);
            inFundSubscriptionMoneyTitle.setText(R.string.in_fund_purchase_money_title);
        }else if(fragmentFlag == 2){
            fragmentName = getResources().getString(R.string.in_fund_subscription_title);
//            entrustBs = "104";
            entrustBs = "17";
        }else if(fragmentFlag == 3){
            fragmentName = getResources().getString(R.string.in_fund_purchase_title);
//            entrustBs = "105";
            entrustBs = "20";
            inFundSubscriptionUpperLimitTitle.setText(R.string.in_fund_purchase_upper_limit_title);
            inFundSubscriptionMoneyTitle.setText(R.string.in_fund_purchase_money_title);
        }
    }

    @Override
    protected void setTheme() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mKeyboardManager.dismiss();
    }

    @Override
    public void onPause() {
        super.onPause();
        TradeUtils.hideSystemKeyBoard(getActivity());
    }

    /**
     * 返回double类型的数据保留scale位小数的结果，int是要保留的小数位数，不足位补0
     * @param value
     * @param scale
     * @return
     */
    public double formatDouble(double value,int scale){
        DecimalFormat decimalFormat = new DecimalFormat(returnDemcimalPlacesStr(scale));
        decimalFormat.setMinimumFractionDigits(scale);//设置最少需要的小数位数，不足时补0
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String temp=decimalFormat.format(value);
        return Double.parseDouble(temp);
    }

    /**
     * 返回double类型的数据的小数位数
     * @param value
     * @return
     */
    public int returnDecimalPlaces(double value){
        int places=0;
        int index=Double.toString(value).indexOf(".");
        if(index>=0)
        {
            places=Double.toString(value).substring(index+1).length();
        }
        return places;
    }

    /**
     * 返回要保留的小数位数对应的正则表达式
     * @param value
     * @return
     */
    public String returnDemcimalPlacesStr(int value){
        String str="0";
        if(value!=0){
            str="##0"+".";
            for(int i=1;i<=value;i++){
                str+="0";
            }
        }
        return str;
    }

    /**
     * 查询基金信息，返回结果处理
     * @param inFundQueryBeanArrayList
     */
    public void inFundQueryResult(ArrayList<InFundQueryBean> inFundQueryBeanArrayList){
        //隐藏状态框
        if(inFundQueryBeanArrayList != null && inFundQueryBeanArrayList.size()>0){
            inFundQueryBean = inFundQueryBeanArrayList.get(0);
            //基金名称
            tvInFundNameShow.setText(inFundQueryBean.getStock_name());
            //基金净值
            tvInFundNetValueShow.setText(inFundQueryBean.getPrice());
            //认购上限
            tvInFundSubscriptionUpperLimitShow.setText(inFundQueryBean.getStock_max_amount());
        }else{
            ToastUtils.toast(getActivity(),getString(R.string.in_fund_error));
        }
    }

    /**
     * 基金，委托下单，返回结果处理
     * @param inFundOrderBeanArrayList
     */
    public void inFundOrderSubmitReuslt(ArrayList<InFundOrderBean> inFundOrderBeanArrayList){
        ToastUtils.toast(getActivity(),getString(R.string.in_fund_tip4));
    }

    /**
     * 清除所有数据，ignoreFlagSet是要忽略，不进行清除的元素的标志集合。传null时，清除全部
     * @param ignoreFlagSet
     */
    public void clearAllData(HashSet<String> ignoreFlagSet){
        //清除显示内容
        //清除名称
        tvInFundNameShow.setText("");
        //清除净值
        tvInFundNetValueShow.setText("");
        //清除上限
        tvInFundSubscriptionUpperLimitShow.setText("");
        //清除数量
        tvInFundSubscriptionMoneyInput.setText("");
        //清除中间实体
        //清除 输入基金代码后的返回结果对应的实体
        inFundQueryBean = null;
    }

    /**
     * 查询持有资金，结果查询
     * @param moneySelectBean
     */
    public void getHoldFund(MoneySelectBean moneySelectBean){
        if(moneySelectBean != null){
            tvInFundAvailableFundsShow.setText(moneySelectBean.getEnable_balance());
        }
    }
}
