package com.thinkive.android.trade_bz.a_net.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustSelectBean;
import com.thinkive.android.trade_bz.a_net.fragment.NetVoteSelectFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.NetVote301534;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 网络投票查询页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */

public class NetVoteSelectServicesImpl {
    private NetVoteSelectFragment mFragment = null;

    public NetVoteSelectServicesImpl(NetVoteSelectFragment mFragment) {
        this.mFragment = mFragment;
    }

    /**
     * 初始化投票委托数据
     * @param begin
     * @param end
     */
    public void requestVoteEntrustList(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new NetVote301534(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<NetVoteEntrustSelectBean> dataList = bundle.getParcelableArrayList(NetVote301534.BUNDLE_KEY_NET_VOTE);
                mFragment.onGetVoteEntrustList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(NetVote301534.ERROR_INFO));
            }

        }).request();
    }
}
