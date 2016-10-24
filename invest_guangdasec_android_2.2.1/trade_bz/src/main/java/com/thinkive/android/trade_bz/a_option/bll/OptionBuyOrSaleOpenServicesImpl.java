package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.ICallBack;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionEntrustOrderBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionExerciseEndDateBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionExercisePriceBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionInfoBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionBuyOrSaleOpenFragment;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305001;
import com.thinkive.android.trade_bz.request.Option305005;
import com.thinkive.android.trade_bz.request.RequestHQ20000;
import com.thinkive.android.trade_bz.request.RequestHQ20003;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 个股期权 买入开仓，卖出开仓,备兑券开仓
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/20
 */

public class OptionBuyOrSaleOpenServicesImpl {
    private OptionBuyOrSaleOpenFragment mFragment = null;
    private Context mContext;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public OptionBuyOrSaleOpenServicesImpl(OptionBuyOrSaleOpenFragment fragment) {
        mFragment = fragment;
        mContext = CoreApplication.getInstance();
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 开始联动从行情校验证券
     * @param params
     */
    public void startLinkage(final HashMap<String,String> params) {
        final String stockCode;
        if(params !=null && params.containsKey("stockCode")&& !"".equals(params.get("stockCode"))){
            stockCode = params.get("stockCode");
        }else{
            stockCode ="";
        }
        sendMsgToHqForStockList(stockCode, new IHqCallBackStock() {
            @Override
            public void onGetStockMsg(ArrayList<CodeTableBean> dataList) {
                // 只有获取到唯一结果时才是正常情况
                if (dataList.size() == 1 && stockCode.equals(dataList.get(0).getCode())) {
                    CodeTableBean codeTableBean = dataList.get(0);
                    request20000ForHqData(params, codeTableBean.getMarket());
                } else {
                    ToastUtils.toast(mContext, mContext.getResources().getString(R.string.option_error0));
                    mFragment.onClearAllData(null);
                }
            }
        });
    }

    /**
     * 给行情模块发送消息，让行情模块给本类返回股票搜索提示列表
     * 这里的模块通信异步返回结果。
     */
    private void sendMsgToHqForStockList(String curEditStockCode, final IHqCallBackStock iHqCallBackStock) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("searchKey", curEditStockCode);
            jsonObject.put("num", "30");
            AppMessage msg = new AppMessage("self-stock", 60101, jsonObject);
            msg.setCallBack(new ICallBack() {
                @Override
                public void callback(Object o) {
                    ArrayList<CodeTableBean> dataList = new ArrayList<CodeTableBean>();
                    try {
                        if (o != null && !o.equals("")) {
                            String result = o.toString();
                            JSONObject resultJsonObject = new JSONObject(result);
                            JSONArray resultJsonArray = resultJsonObject.getJSONArray("results");
                            int resultJsonArrayLength = resultJsonArray.length();
                            CodeTableBean tempBean;
                            for (int i = 0; i < resultJsonArrayLength; i++) {
                                tempBean = JsonParseUtil.parseJsonToObject(resultJsonArray.getJSONObject(i), CodeTableBean.class);
                                dataList.add(tempBean);
                            }
                            iHqCallBackStock.onGetStockMsg(dataList);
                        } else {
                            ToastUtils.toast(mContext, mContext.getResources().getString(R.string.option_error4));
                        }
                    } catch (JSONException je) {
                        je.printStackTrace();
                    }
                }
            });
            MessageManager.getInstance(CoreApplication.getInstance()).sendMessage(msg);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    interface IHqCallBackStock {
        void onGetStockMsg(ArrayList<CodeTableBean> dataList);
    }

    /**
     * 证券校验完成后，从行情获取证券信息
     * @param params
     * @param market
     */
    public void request20000ForHqData(final HashMap<String,String> params, final String market) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("version", "1");
        final String stockCode;
        if(params !=null && params.containsKey("stockCode")&& !"".equals(params.get("stockCode"))){
            stockCode = params.get("stockCode");
        }else{
            stockCode ="";
        }
        paramMap.put("stock_list", market + ":" + stockCode);
        //名称：市场：代码：涨停：跌停：是否停牌:股票类型:现价
        paramMap.put("field", "22:23:24:45:46:48:21:2");
        new RequestHQ20000(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                CodeTableBean data = (CodeTableBean) bundle.getSerializable(RequestHQ20000.BUNDLE_KEY_SOCKET);
                if (data != null) {
                    if ("1".equals(data.getIssuspend())) {//停牌
                        ToastUtils.toast(context, context.getResources().getString(R.string.option_error1));
                        mFragment.onGetSuspendStock(data.getName(), data.getCode());
                    } else if ("2".equals(data.getIssuspend())) {//未停牌
                        //请数据传出去
                        mFragment.onGetHq20000Data(data);
                        //请求五档盘
                        requestStockWuDangPan(data.getCode(), data.getMarket());
                        //查询20000接口后，验证证券有效，没有停牌时，再请求305001接口
                        //查询305001接口，查询并返回期权代码信息列表
                        requestStockOptionInfo("optionInfo", params);
                    }
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ20000.ERROR_INFO));
            }
}).request();
    }

    /**
     * 获取股票行情五档买卖盘数据
     */
    public void requestStockWuDangPan(final String stockCode, final String market) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_list", market + ":" + stockCode);
        paramMap.put("version", "1");
        new RequestHQ20003(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                StockBuySellDish bean = (StockBuySellDish) bundle.getSerializable(RequestHQ20003.BUNDLE_KEY_WUDANG);
                if (bean != null) {
                    ArrayList<String> valueList = bean.getValueBuySale();
                    for (int i = 0; i <= 4; i++) { // 卖价五~卖价一
                        valueList.set(i, TradeUtils.formatDouble2(valueList.get(i)));
                    }
                    for (int i = 5; i <= 9; i++) { // 卖量五~卖量一
                        valueList.set(i, TradeUtils.formateDataWithQUnit(valueList.get(i)));
                    }
                    for (int i = 10; i <= 14; i++) { // 买价一~买价五
                        valueList.set(i, TradeUtils.formatDouble2(valueList.get(i)));
                    }
                    for (int i = 15; i <= 19; i++) { // 买量一~买量五
                        valueList.set(i, TradeUtils.formateDataWithQUnit(valueList.get(i)));
                    }
                    mFragment.onGetWuDangDishData(bean);
                }

            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ20003.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 委托下单
     */
    public void requestEntrustOrder(HashMap<String,String> params) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("exchange_type", params.get("exchangeType"));  // 交易市场类别
        paramMap.put("entrust_prop_opt", params.get("entrustPropOpt"));  // 委托属性 //衍生品合约账户
        paramMap.put("option_code", params.get("optionCode"));  //期权合约编码
        paramMap.put("entrust_amount", params.get("entrustAmount"));  //委托数量
        paramMap.put("entrust_price", params.get("entrustPrice"));  //   委托价格
        paramMap.put("entrust_bs", params.get("entrustBs"));  //  委托标志
        paramMap.put("entrust_oc", params.get("entrustOc"));  // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
        paramMap.put("covered_flag", params.get("coveredFlag"));  //备兑标志
        paramMap.put("stock_account", params.get("stockAccount"));  // 证券账户
        paramMap.put("option_type", params.get("optionType"));  //期权类别  0：认购 1：认沽
        new Option305005(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ArrayList<OptionEntrustOrderBean> optionEntrustOrderBeans=null;
                if(bundle!=null){
                    optionEntrustOrderBeans=bundle.getParcelableArrayList(Option305005.BUNDLE_KEY_ENTRUST_ORDER);
                }
                mFragment.showEntrustOrderResult(optionEntrustOrderBeans);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                String errorNo=bundle.getString(Option305005.ERROR_NO);
                String errorInfo=bundle.getString(Option305005.ERROR_INFO);
                LogUtil.printLog("e", "Option305005出错,error_no:" + errorNo + "||error_info:" + errorInfo);
                ToastUtils.toast(mContext, errorInfo);
            }
        }).request();
    }

    /**
     * 查询个股期权信息
     * @param flag 查询标志，因为多个地方要使用到这个查询
     * @param params
     */
    public void requestStockOptionInfo(final String flag,HashMap<String,String> params) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        if(params.containsKey("stockCode") && !"".equals(params.get("stockCode"))){
            paramMap.put("stock_code", params.get("stockCode"));  // 标的证券代码
        }
        if(params.containsKey("optionCode") && !"".equals(params.get("optionCode"))){
            paramMap.put("option_code",params.get("optionCode"));
        }
        if(params.containsKey("exerciseEndDate") && !"".equals(params.get("exerciseEndDate")) && params.containsKey("exercisePrice") &&!"".equals(params.get("exercisePrice"))){
            paramMap.put("exercise_month",params.get("exerciseEndDate"));
            paramMap.put("exercise_price", params.get("exercisePrice"));
        }
        if(params.containsKey("entrustBs") && !"".equals(params.get("entrustBs"))){
            paramMap.put("entrust_bs",params.get("entrustBs"));//  委托标志
        }
        if(params.containsKey("entrustOc") && !"".equals(params.get("entrustOc"))){
            paramMap.put("entrust_oc", params.get("entrustOc"));  // 开平仓方向 0：开仓  1：平仓  2：行权3，自动行权
        }
        if(params.containsKey("coveredFlag") && !"".equals(params.get("coveredFlag"))){
            paramMap.put("covered_flag", params.get("coveredFlag"));  //备兑标志
        }
        if(params.containsKey("optionType") && !"".equals(params.get("optionType"))){
            paramMap.put("option_type", params.get("optionType"));  //期权类别  0：认购 1：认沽
        }
        if(params.containsKey("entrustPrice") && !"".equals(params.get("entrustPrice"))){
            paramMap.put("entrust_price", params.get("entrustPrice"));  //委托价格
        }
        new Option305001(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionInfoBean> optionInfoBeans=null;
                ArrayList<OptionExerciseEndDateBean> optionExerciseEndDateBeans=null;
                ArrayList<OptionExercisePriceBean> optionExercisePriceBeans=null;
                if(bundle!=null){
                    optionInfoBeans=bundle.getParcelableArrayList(Option305001.BUNDLE_KEY_STOCK_OPTION);
                    optionExerciseEndDateBeans=bundle.getParcelableArrayList(Option305001.BUNDLE_KEY_EXERCISE_END_DATE_OPTION);
                    optionExercisePriceBeans=bundle.getParcelableArrayList(Option305001.BUNDLE_KEY_EXERCISE_PRICE_OPTION);
                }
                mFragment.getStockOptionInfoAndShow(flag,optionInfoBeans,optionExerciseEndDateBeans,optionExercisePriceBeans);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                String errorNo=bundle.getString(Option305001.ERROR_NO);
                String errorInfo=bundle.getString(Option305001.ERROR_INFO);
                LogUtil.printLog("e","Option305001出错,error_no:"+errorNo+"||error_info:"+errorInfo);
                ToastUtils.toast(mContext,errorInfo);
            }
        }).request();
    }
}
