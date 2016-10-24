package com.thinkive.android.trade_bz.a_in.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.activity.InFundSelectListActivity;
import com.thinkive.android.trade_bz.a_in.bean.InFundOrderBean;
import com.thinkive.android.trade_bz.a_in.bean.InFundQueryBean;
import com.thinkive.android.trade_bz.a_in.bean.InFundSercuritiesPositionsQueryBean;
import com.thinkive.android.trade_bz.a_in.bll.InFundPurchaseOrSubscriptionOrRedemptionImpl;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Description：货币基金赎回、LOF基金赎回 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/10 <br>
 */
public class InFundRedemptionFragment extends AbsBaseFragment {
    private int fragmentFlag;//fragment标志
    private String fragmentName;//fragment名称
    private String entrustBs;//委托标志
    private InFundPurchaseOrSubscriptionOrRedemptionImpl service;
    private ArrayList<InFundSercuritiesPositionsQueryBean> inFundSercuritiesPositionsQueryBeanArrayList;//证券持仓列表
    private InFundSercuritiesPositionsQueryBean selectedInFundSercuritiesPositionsQueryBean;//当前选中的持仓证券对应的实体
    private ArrayList<InFundQueryBean> inFundQueryBeanArrayList;//场内基金查询结果列表
    private InFundQueryBean inFundQueryBean;//输入基金代码后的返回结果对应的实体
    private TextView tvInFundNameShow;//基金名称
    private TextView tvInFundNetValueShow;//基金净值
    private TextView tvInFundRedemptionUpperLimitShow;//赎回上限
    private TextView tvInFundRedemptionAllButton;//赎回上限 全部按钮
    private EditText tvInFundRedemptionAmountInput;//赎回数量
    private Button tvInFundPurchaseOrSubscriptionSubmit;//提交委托
    private TextView tvInFundAvailableFundsShow;//持有资金

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_in_fund_redemption, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        tvInFundNameShow = (TextView) view.findViewById(R.id.tv_in_fund_name_show);
        tvInFundNetValueShow = (TextView) view.findViewById(R.id.tv_in_fund_net_value_show);
        tvInFundRedemptionUpperLimitShow = (TextView) view.findViewById(R.id.tv_in_fund_redemption_upper_limit_show);
        tvInFundRedemptionAllButton = (TextView) view.findViewById(R.id.tv_in_fund_redemption_all_button);
        tvInFundRedemptionAmountInput = (EditText) view.findViewById(R.id.tv_in_fund_redemption_amount_input);
        tvInFundPurchaseOrSubscriptionSubmit = (Button) view.findViewById(R.id.tv_in_fund_purchase_or_subscription_submit);
        tvInFundAvailableFundsShow = (TextView) view.findViewById(R.id.tv_in_fund_available_funds_show);
    }

    @Override
    protected void setListeners() {
        //选择基金
        tvInFundNameShow.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inFundSercuritiesPositionsQueryBeanArrayList != null && inFundSercuritiesPositionsQueryBeanArrayList.size() > 0) {
                    Intent intent = new Intent(getActivity(), InFundSelectListActivity.class);
                    intent.putParcelableArrayListExtra("data_select", inFundSercuritiesPositionsQueryBeanArrayList);
                    intent.putExtra("useType", "in_fund_sercurities_Positions");
                    startActivityForResult(intent, 0);
                } else {
                    ToastUtils.toast(getActivity(), getString(R.string.in_fund_tip5));
                }
            }
        });
        //赎回上限 "全部"按钮，点击事件
        tvInFundRedemptionAllButton.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                String netVauleStr = tvInFundNetValueShow.getText().toString();
                if (netVauleStr != null && !"".equals(netVauleStr)) {
                    /*double netValue = Double.parseDouble(tvInFundNetValueShow.getText().toString());
                    int maxAmount = Integer.parseInt(tvInFundRedemptionUpperLimitShow.getText().toString());
                    tvInFundRedemptionAmountInput.setText(formatDouble(netValue * maxAmount, returnDecimalPlaces(netValue)) + "");*/
                    tvInFundRedemptionAmountInput.setText(tvInFundRedemptionUpperLimitShow.getText().toString());
                } else {
                    ToastUtils.toast(getActivity(), getString(R.string.in_fund_tip0));
                }
            }
        });
        //提交委托
        tvInFundPurchaseOrSubscriptionSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                HashMap<String, String> params = new HashMap<String, String>();
                String entrustBsInner = entrustBs;
                String exchangeType = inFundQueryBean.getExchange_type();//交易市场类别
                String stockAccount = inFundQueryBean.getStock_account();//证券账号
                String stockCode = inFundQueryBean.getStock_code();//基金代码
                String entrustPrice = tvInFundNetValueShow.getText().toString().trim();//基金净值
                String entrustAmount = tvInFundRedemptionAmountInput.getText().toString().trim();//认购金额
                /**
                 * 入参校验
                 */
                if (inFundSercuritiesPositionsQueryBeanArrayList == null || inFundSercuritiesPositionsQueryBeanArrayList.size() == 0) {
                    ToastUtils.toast(getContext(), getString(R.string.in_fund_tip6));
                    return;
                }
                if (selectedInFundSercuritiesPositionsQueryBean == null) {
                    ToastUtils.toast(getContext(),getString(R.string.in_fund_tip7));
                    return;
                }
                if (inFundQueryBeanArrayList == null || inFundQueryBean == null) {
                    ToastUtils.toast(getContext(), getString(R.string.in_fund_tip8));
                    return;
                }
                if (entrustAmount == null || TextUtils.isEmpty(entrustAmount) || !TradeUtils.isNumeric(entrustAmount)) {
                    ToastUtils.toast(getContext(), getString(R.string.in_fund_tip9));
                    return;
                } else if(entrustAmount != null && Double.parseDouble(entrustAmount)-Double.parseDouble(tvInFundRedemptionUpperLimitShow.getText().toString())<0){
                    ToastUtils.toast(getContext(),getString(R.string.in_fund_tip14));
                }
                params.put("entrustBs", entrustBsInner);
                params.put("exchangeType", exchangeType);
                params.put("stockAccount", stockAccount);
                params.put("stockCode", stockCode);
                params.put("entrustPrice", entrustPrice);
                params.put("entrustAmount", entrustAmount);
                service.inFundOrderSubmit(params);
            }
        });
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            fragmentFlag = bundle.getInt("fragmentFlag");
        }
        service = new InFundPurchaseOrSubscriptionOrRedemptionImpl(null, this);
    }

    @Override
    protected void initViews() {
        //查询持有资金
        service.requestHoldFund();
        //查询基金列表
        HashMap<String, String> params = new HashMap<String, String>();
        if (fragmentFlag == 0) {
            fragmentName = getResources().getString(R.string.in_monetary_fund_redemption_title);
            entrustBs = "21";
        } else if (fragmentFlag == 1) {
            fragmentName = getResources().getString(R.string.in_fund_redemption_title);
            entrustBs = "21";
            params.put("islof","1");
        }
        service.inFundSercuritiesPositionsQuery(params);
    }

    @Override
    protected void setTheme() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            if (resultCode == 88) {
//                clearAllData("");
                //正常的返回结果
                int position = data.getIntExtra("data_select_result", 0);
                selectedInFundSercuritiesPositionsQueryBean = inFundSercuritiesPositionsQueryBeanArrayList.get(position);
                Log.d("data_select_result", "index:" + position + " selectedInFundSercuritiesPositionsQueryBean:" + selectedInFundSercuritiesPositionsQueryBean.getStock_code());
                //根据选择的结果，设置内容，并请求301514查询详情数据
                tvInFundNameShow.setText(selectedInFundSercuritiesPositionsQueryBean.getStock_name());

                //请求301514获取基金详情
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("entrustBs", entrustBs);
                params.put("stockCode", selectedInFundSercuritiesPositionsQueryBean.getStock_code());
                service.inFundQuery(params);
            } else {
                clearAllData(null);
            }
        }
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
     * 证券持仓查询 结果处理
     *
     * @param inFundSercuritiesPositionsQueryBeanArrayList
     */
    public void InFundSercuritiesPositionsQueryReuslt(ArrayList<InFundSercuritiesPositionsQueryBean> inFundSercuritiesPositionsQueryBeanArrayList) {
        if (inFundSercuritiesPositionsQueryBeanArrayList != null && inFundSercuritiesPositionsQueryBeanArrayList.size() > 0) {
            this.inFundSercuritiesPositionsQueryBeanArrayList = inFundSercuritiesPositionsQueryBeanArrayList;
        } else {
            ToastUtils.toast(getActivity(), getString(R.string.in_fund_tip10));
        }
    }


    /**
     * 查询基金信息，返回结果处理
     *
     * @param inFundQueryBeanArrayList
     */
    public void inFundQueryResult(ArrayList<InFundQueryBean> inFundQueryBeanArrayList) {
        if (inFundQueryBeanArrayList != null && inFundQueryBeanArrayList.size() > 0) {
            this.inFundQueryBeanArrayList = inFundQueryBeanArrayList;
            inFundQueryBean = inFundQueryBeanArrayList.get(0);
            //基金净值
            tvInFundNetValueShow.setText(inFundQueryBean.getPrice());
            //赎回上限
            tvInFundRedemptionUpperLimitShow.setText(inFundQueryBean.getStock_max_amount());
        } else {
            ToastUtils.toast(getActivity(), getString(R.string.in_fund_error2));
        }
    }

    /**
     * 基金，委托下单，返回结果处理
     *
     * @param inFundOrderBeanArrayList
     */
    public void inFundOrderSubmitReuslt(ArrayList<InFundOrderBean> inFundOrderBeanArrayList) {
        if (inFundOrderBeanArrayList != null && inFundOrderBeanArrayList.size() > 0) {
            ToastUtils.toast(getActivity(), getString(R.string.in_fund_tip11));
        }
    }

    /**
     * 清除所有数据，ignoreFlagSet是要忽略，不进行清除的元素的标志集合。传null时，清除全部
     *
     * @param ignoreFlagSet
     */
    public void clearAllData(HashSet<String> ignoreFlagSet) {
        //清除显示内容
        //清除名称
        tvInFundNameShow.setText("");
        //清除净值
        tvInFundNetValueShow.setText("");
        //清除上限
        tvInFundRedemptionUpperLimitShow.setText("");
        //清除数量
        tvInFundRedemptionAmountInput.setText("");
        //清除中间实体
        //清除 当前选中的持仓证券对应的实体
        selectedInFundSercuritiesPositionsQueryBean = null;
        //清除 场内基金查询结果列表
        inFundQueryBeanArrayList = null;
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
