package com.thinkive.android.trade_bz.a_rr.bll;


import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RTransferFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.RequestLogin;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 *  融资融券--划转
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */
public class RTransMainServiceImpl extends BasicServiceImpl {

    private RTransferFragment mFragment=null;
    public RTransMainServiceImpl(RTransferFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 划转的时候，如果普通账户未登录
     * 那么就需要进行普通交易登录
     * 注：功能号和入参视柜台而定
     */
    public void requestNormalLogin(String loginAccount, final String loginPassword) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("input_content", loginAccount);
        paramMap.put("entrust_way", TradeLoginManager.ENTRUST_WAY);
        paramMap.put("op_station", TradeLoginManager.OP_STATION_2);
        paramMap.put("password", loginPassword);
        paramMap.put("input_type", "5");
        paramMap.put("funcNo", "1000008");
        new RequestLogin(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                TradeLoginManager.sIsNormalLogin_in_credit = true;
                ToastUtils.toast(context, R.string.login_success);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, RequestLogin.ERROR_INFO);
            }
        }, TradeLoginManager.LOGIN_TYPE_NORMAL_IN_CREDIT).request();
    }
}
