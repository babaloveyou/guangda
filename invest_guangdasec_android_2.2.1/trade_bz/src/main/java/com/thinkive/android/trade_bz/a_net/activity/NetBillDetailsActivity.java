package com.thinkive.android.trade_bz.a_net.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.fragment.NetAddUpVoteFragment;
import com.thinkive.android.trade_bz.a_net.fragment.NetNotAddUpVoteFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment;
import com.thinkive.android.trade_bz.others.tools.TradeTools;

/**
 * 网络投票议案详情
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */
public class NetBillDetailsActivity extends AbsNavBarActivity {

    private RadioButtonFragment mFragment = null;
    private NetNotAddUpVoteFragment netNotAddUpVoteFragment = null;
    private NetAddUpVoteFragment addUpVoteFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.net_vote20);
        setBackBtnVisibility(View.VISIBLE);
        setLogoutBtnVisibility(View.VISIBLE);
        setLogoutBtnText(R.string.net_vote32);
    }

    @Override
    protected void initData() {
        netNotAddUpVoteFragment = new NetNotAddUpVoteFragment();
        netNotAddUpVoteFragment.setName(getResources().getString(R.string.sign_agreement13));

        addUpVoteFragment = new NetAddUpVoteFragment();
        addUpVoteFragment.setName(getResources().getString(R.string.sign_agreement14));

        mFragment = new RadioButtonFragment(netNotAddUpVoteFragment,addUpVoteFragment);
    }

    @Override
    protected void onLogouBtEvent() {
        super.onLogouBtEvent();
        int index = mFragment.getCheckAt();
        if(index == 1){ //当前处于累计议案投票页面
            addUpVoteFragment.onClickCommit();
        }else{ //当前处于非累计议案投票页面
            netNotAddUpVoteFragment.onClickCommit();
        }
    }
}
