package com.thinkive.android.trade_bz.a_net.bll;


import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_net.fragment.NetAddUpVoteFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.NetVote301533;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * 网络投票-累计议案投票
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/5
 */
public class NetAddVoteServicesImpl {

    private NetAddUpVoteFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public NetAddVoteServicesImpl(NetAddUpVoteFragment mFragment) {
        this.mFragment = mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }



    /**
     * 提交投票
     */
    public void requestCommitVote(HashMap<String, String> resultMap) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("exchange_type",resultMap.get("exchange_type")); //市场（见数据字典)
        map.put("stock_account", resultMap.get("stock_account")); //股东代码
        map.put("stock_code",resultMap.get("stock_code")); //证券代码
        map.put("meeting_seq", resultMap.get("meeting_seq")); //股东大会编码
        map.put("v_id", resultMap.get("v_id")); //议案编号
        //投票数量 累计投票必填，普通投票填0（默认为0）(多个用逗号分隔，)
        map.put("vote_number",resultMap.get("vote_number"));
        //投票意见 普通：1赞同 2反对 3弃权  累计：固定填1(多个用逗号分隔，)
        map.put("vote_result", "1");
        new NetVote301533(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(NetVote301533.BUNDLE_KEY_NET_ENTRUST));
                mFragment.onSuccess();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(NetVote301533.ERROR_INFO));
            }
        }).request();
    }
}
