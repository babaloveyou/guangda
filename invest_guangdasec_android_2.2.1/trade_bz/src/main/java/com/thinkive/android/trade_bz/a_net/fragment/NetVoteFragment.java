package com.thinkive.android.trade_bz.a_net.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.activity.NetBillDetailsActivity;
import com.thinkive.android.trade_bz.a_net.adapter.NetVoteMsgAdapter;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustBean;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteStockMsgBean;
import com.thinkive.android.trade_bz.a_net.bll.NetVoteServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 网络投票的投票页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */
public class NetVoteFragment extends AbsBaseFragment {
    public static final String NET_VOTE_LIST = "net_vote_stock_msg";
    private TKFragmentActivity mActivity;
    private NetVoteController mController;
    private NetVoteServicesImpl mServices;
    private NetVoteMsgAdapter mAdapter;
    private EditText mEdtStockCode;
    private TextView mTvClickSelect;
    private LinearLayout mLinLoading;
    private PullToRefreshListView mLvRefreshList;
    private ListView mListView;
    private LinearLayout mLinNoDataSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_net_vote, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mEdtStockCode = (EditText) view.findViewById(R.id.edt_stock_code);
        mTvClickSelect = (TextView) view.findViewById(R.id.tv_click_select);
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mLvRefreshList.getRefreshableView();
        mListView.setDivider(null);
        mLinNoDataSet = (LinearLayout) view.findViewById(R.id.lin_no_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK,mTvClickSelect,mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new NetVoteController(this);
        mServices = new NetVoteServicesImpl(this);
        mAdapter = new NetVoteMsgAdapter(mActivity,this);
    }

    @Override
    protected void initViews() {
        mLvRefreshList.setPullRefreshEnabled(false);
        mLvRefreshList.setPullLoadEnabled(false);
        mServices.requestVoteMsgList("");
        setTheme();
    }

    @Override
    protected void setTheme() {}

    /**
     * 得到投票信息数据集
     */
    public void onGetVoteMsgList(ArrayList<NetVoteStockMsgBean> dataList){
        if(dataList != null && dataList.size() > 0){
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }else{
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        }
    }

    /**
     * 点击投票
     */
    public void onClickAdapter(NetVoteStockMsgBean bean){
        //请求议案详情
        mServices.requestAddVoteList(bean);
    }

    /**
     * 得到议案数据集
     */
    public void onGetAddUpVoteList(ArrayList<NetVoteEntrustBean> dataList){
        if(dataList != null && dataList.size() > 0){
            Intent intent = new Intent(mActivity,NetBillDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(NET_VOTE_LIST,dataList);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.net_vote33));
        }
    }


    /**
     * 正在加载时的UI状态
     */
    private void loadingUpdateUI(){
        mLinLoading.setVisibility(View.VISIBLE);
        mLinNoDataSet.setVisibility(View.GONE);
        mLvRefreshList.setVisibility(View.GONE);
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 点击查询按钮
     */
    public void onClickSelect(){
        String stockCode = mEdtStockCode.getText().toString();
        if(TextUtils.isEmpty(stockCode) || stockCode.length() == 6){
            loadingUpdateUI();
            mServices.requestVoteMsgList(stockCode);
        }else{
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.net_vote21));
        }
    }
}

class NetVoteController extends AbsBaseController implements View.OnClickListener {

    private NetVoteFragment mFragment;

    public NetVoteController(NetVoteFragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int clickId=v.getId();
        if(clickId == R.id.tv_click_select){
            mFragment.onClickSelect();
        }
    }
}

