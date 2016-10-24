package com.thinkive.android.trade_bz.a_new.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_new.bean.NewOneKeyBean;
import com.thinkive.android.trade_bz.a_new.fragment.NewGiveUpRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.NewStock301538;
import com.thinkive.android.trade_bz.request.NewStock301539;
import com.thinkive.android.trade_bz.request.NewStock303051;
import com.thinkive.android.trade_bz.request.NewStock303052;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 放弃认购申报撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewGiveUpRevocationSubServiceImpl extends BasicServiceImpl {

    private NewGiveUpRevocationFragment mFragment = null;
    /**
     * 用户类型
     */
    private String mUserType;

    public NewGiveUpRevocationSubServiceImpl(NewGiveUpRevocationFragment fragment, String userType) {
        mFragment = fragment;
        mUserType = userType;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }


    /**
     * 请求一键申购列表数据
     */
    public void requestTodayNew() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        //普通账号
        if (mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
            new NewStock301538(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewOneKeyBean> dataList = bundle.getParcelableArrayList(NewStock301538.BUNDLE_KEY_TODAY_TOTAL_STOCK);
                    for(NewOneKeyBean bean : dataList){
                        //将申购上限赋值给申购数
                        bean.setInput_num(bean.getMaxamount());
                    }
                    mFragment.onGetStockLinkAgeData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301538.ERROR_INFO));
                }
            }).request();
            //信用账号
        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            new NewStock303051(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewOneKeyBean> dataList = bundle.getParcelableArrayList(NewStock303051.BUNDLE_KEY_TODAY_TOTAL_STOCK);
                    for(NewOneKeyBean bean : dataList){
                        //将申购上限赋值给申购数
                        bean.setInput_num(bean.getMaxamount());
                    }
                    mFragment.onGetStockLinkAgeData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303051.ERROR_INFO));
                }
            }).request();
        }
    }

    /**
     * 一键申购
     */
    public void requestOneKeySub(ArrayList<NewOneKeyBean> dataList) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        //普通账号
        if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)){
            //股票代码1:股票市场1:委托价格1:委托数量1:股东账户1|股票代码2:股票市场2:委托价格2:委托数量2:股东账户2
            paramMap.put("entrustcodes",forMateData(dataList));
            new NewStock301539(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301539.BUNDLE_KEY_ONE_KEY_SUB));
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301539.ERROR_INFO));
                }
            }).request();
            //信用账号
        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            //股票代码1:股票市场1:委托价格1:委托数量1:股东账户1|股票代码2:股票市场2:委托价格2:委托数量2:股东账户2
            paramMap.put("entrustcodes", forMateData(dataList));
            new NewStock303052(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303052.BUNDLE_KEY_ONE_KEY_SUB));
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303052.ERROR_INFO));
                }
            }).request();
        }
    }

    /**
     * 一键申购时拼接证券信息
     * @param dataList
     * @return
     */
    private String forMateData(ArrayList<NewOneKeyBean> dataList){
        StringBuilder stockMsg = new StringBuilder();
        String result = "";
        if(dataList != null && dataList.size() > 0) {
            for (NewOneKeyBean list : dataList) {
                if(list.isCheck()) {
                    String tempStr = "";
                    String code = list.getStock_code();
                    String market = list.getExchange_type();
                    String price = list.getPrice();
                    String num = list.getMaxamount();
                    String account = list.getStock_account();
                    tempStr = code + ":" + market + ":" + price + ":" + num + ":" + account + "|";
                    stockMsg.append(tempStr);
                }
            }
            if(!TextUtils.isEmpty(result) && result.length() > 0){
                result = stockMsg.deleteCharAt(stockMsg.lastIndexOf("|")).toString();
            }else{
                result ="";
            }
        }
        return result;
    }
}
