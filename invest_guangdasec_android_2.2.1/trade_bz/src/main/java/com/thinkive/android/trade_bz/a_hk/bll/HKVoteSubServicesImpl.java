package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockMessageBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKVoteSubFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.HK301621;
import com.thinkive.android.trade_bz.request.RequestHQ50000;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 *  投票申报
 * @author 张雪梅
 * @company Thinkive
 * @date 16/5/23
 */


public class HKVoteSubServicesImpl extends BasicServiceImpl {
    private HKVoteSubFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public HKVoteSubServicesImpl(HKVoteSubFragment mFragment) {
        this.mFragment = mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 从行情联动证券
     * @param stockCode
     */
    public void requestVoteSubLink(String stockCode) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("version","1");
        paramMap.put("field","4:5:22:24:41:42:43:48:2");
        if(stockCode != null && stockCode.length() == 5){
            paramMap.put("stock_list","HK"+":"+"0"+stockCode);
        }else {
            paramMap.put("stock_list","HK"+":"+stockCode);
        }
        new RequestHQ50000(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                HKStockMessageBean data = (HKStockMessageBean)bundle.getSerializable(RequestHQ50000.BUNDLE_KEY_SOCKET_HK);
                if(data.getIs_stop().equals("1")){//停牌
                    ToastUtils.toast(context, R.string.hk_order26);
                }else if(data.getIs_stop().equals("2")) {//未停牌
                    mFragment.onGetVoteSubLinkData(data);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ50000.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 投票下单
     */
    public void requestVoteSubCommit(String code, String number1, String number2, String yes, String no, String giveUp) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_account", TradeLoginManager.sNormalUserInfo_hk.getStock_account());
        map.put("stock_code", code);
//        map.put("isin_code",bean.getIsin_code()); //ISIN代码
        map.put("placard_id",number1); //公告编号
        map.put("motion_id",number2); //议案编号
        map.put("approve_amount",yes); //赞成数量
        map.put("oppose_amount",no); //反对数量
        map.put("waive_amount",giveUp); //弃权数量
        new HK301621(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(HK301621.BUNDLE_KEY_RESULT));
                mFragment.clearAllData();//委托成功后清除数据
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(HK301621.ERROR_INFO));
            }
        }).request();
    }
}
