package com.thinkive.android.trade_bz.a_net.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustBean;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteStockMsgBean;
import com.thinkive.android.trade_bz.a_net.fragment.NetVoteFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.NetVote301530;
import com.thinkive.android.trade_bz.request.NetVote301531;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 网络投票的投票页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */

public class NetVoteServicesImpl {
    private NetVoteFragment mFragment = null;

    public NetVoteServicesImpl(NetVoteFragment mFragment) {
        this.mFragment = mFragment;
    }

    /**
     * 初始化投票信息数据
     */
    public void requestVoteMsgList(String stockCode) {
        HashMap<String, String> map = new HashMap<String, String>();
        if(stockCode != null && !TextUtils.isEmpty(stockCode)){
            map.put("vote_code",stockCode);
        }
        new NetVote301530(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<NetVoteStockMsgBean> dataList = bundle.getParcelableArrayList(NetVote301530.BUNDLE_KEY_NET_VOTE);
                mFragment.onGetVoteMsgList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(NetVote301530.ERROR_INFO));
            }

        }).request();
    }

    /**
     * 请求议案数据详情
     */
    public void requestAddVoteList(NetVoteStockMsgBean bean) {
        HashMap<String, String> map = new HashMap<String, String>();
        if(bean != null){
            map.put("meeting_seq", bean.getMeeting_seq()); //股东大会编码
        }
        new NetVote301531(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<NetVoteEntrustBean> dataList = bundle.getParcelableArrayList(NetVote301531.BUNDLE_KEY_NET_VOTE);
                mFragment.onGetAddUpVoteList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(NetVote301531.ERROR_INFO));
            }

        }).request();
    }
}
