package com.thinkive.android.trade_bz.a_net.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.adapter.NetAddUpVoteAdapter;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustBean;
import com.thinkive.android.trade_bz.a_net.bll.NetAddVoteServicesImpl;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 网络投票-累计议案投票
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/3
 */
public class NetAddUpVoteFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private NetAddVoteServicesImpl mServices;
    private LinearLayout mLinLoading;
    private PullToRefreshListView mLvRefreshList;
    private ListView mListView;
    private LinearLayout mLinNoDataSet;
    private NetAddUpVoteAdapter mAdapter;
    private ArrayList<NetVoteEntrustBean> mNetVoteList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_net_add_up_vote, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Bundle bundle =  getArguments();
        if(bundle != null){
            bundle.remove(NetVoteFragment.NET_VOTE_LIST);
        }
    }

    @Override
    protected void findViews(View view) {
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mLvRefreshList.getRefreshableView();
        mListView.setDivider(null);
        //只有当其子类控件不需要获取焦点时才获取焦点
//        mListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        mLinNoDataSet = (LinearLayout) view.findViewById(R.id.lin_no_data_set);
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mServices = new NetAddVoteServicesImpl(this);
        mAdapter = new NetAddUpVoteAdapter(mActivity);
    }

    @Override
    protected void initViews() {
        mLvRefreshList.setPullRefreshEnabled(false);
        mLvRefreshList.setPullLoadEnabled(false);
        Bundle bundle =  getArguments();
        if(bundle != null){
            mNetVoteList = new ArrayList<NetVoteEntrustBean>();
            ArrayList<NetVoteEntrustBean> dataList =bundle.getParcelableArrayList(NetVoteFragment.NET_VOTE_LIST);
            if(dataList != null){
                for(NetVoteEntrustBean bean : dataList){
                    if(bean.getVote_type().equals("L")){
                        mNetVoteList.add(bean);
                    }
                }
            }
            onGetAddUpVoteList(mNetVoteList);
        }
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到累计议案数据集
     */
    public void onGetAddUpVoteList(ArrayList<NetVoteEntrustBean> dataList){
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
     * 点击提交
     */
    public void onClickCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        ArrayList<NetVoteEntrustBean> tempList = new ArrayList<NetVoteEntrustBean>();
        if(mNetVoteList != null && mNetVoteList.size() > 0){
            //循环移除主议案
            for(NetVoteEntrustBean bean : mNetVoteList){
                if(TextUtils.isEmpty(bean.getVote_leijino())){
                    tempList.add(bean);
                }
            }
            if(tempList != null && tempList.size() > 0){
                HashMap<String, String> resultMap = resultMap(tempList);
                //循环检测是否已完全投票
                for(NetVoteEntrustBean bean : tempList){
                    if(TextUtils.isEmpty(bean.getVote_number())){
                        ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.net_vote34));
                        return;
                    }
                }
                if(resultMap != null && resultMap.size() > 0){
                    mServices.requestCommitVote(resultMap);
                }
            }
        }
    }

    /**
     * 提交成功后
     * 清空所有选项
     */
    public void onSuccess() {
        if (mNetVoteList != null && mNetVoteList.size() > 0) {
            for (NetVoteEntrustBean bean : mNetVoteList) {
                bean.setVote_number("");
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 生成数据map
     * @param dataList
     * @return
     */
    private HashMap<String,String> resultMap (ArrayList<NetVoteEntrustBean> dataList){
        HashMap<String,String> results=new HashMap<String, String>();
        StringBuilder codeMsg = new StringBuilder();
        StringBuilder meetIdMsg = new StringBuilder();
        StringBuilder voteIdMsg = new StringBuilder();
        StringBuilder voteNumMsg = new StringBuilder();
        String exchange_type = "";
        String stock_account = "";
        if(dataList != null && dataList.size() > 0) {
            for (NetVoteEntrustBean list : dataList) {
                String code = list.getRef_code()+",";
                String meetId = list.getMeeting_seq()+",";
                String voteId = list.getV_id()+",";
                String voteNum = list.getVote_number()+",";
                exchange_type = list.getExchange_type();
                stock_account = list.getStock_account();
                codeMsg.append(code);
                meetIdMsg.append(meetId);
                voteIdMsg.append(voteId);
                voteNumMsg.append(voteNum);
            }
            if(!TextUtils.isEmpty(exchange_type)){
                results.put("exchange_type",exchange_type);
            }else{
                results.put("exchange_type","");
            }

            if(!TextUtils.isEmpty(stock_account)){
                results.put("stock_account",stock_account);
            }else{
                results.put("stock_account","");
            }

            if(!TextUtils.isEmpty(codeMsg) && codeMsg.length() > 0){
                results.put("stock_code",codeMsg.deleteCharAt(codeMsg.lastIndexOf(",")).toString());
            }else{
                results.put("stock_code","");
            }

            if(!TextUtils.isEmpty(meetIdMsg) && meetIdMsg.length() > 0){
                results.put("meeting_seq",meetIdMsg.deleteCharAt(meetIdMsg.lastIndexOf(",")).toString());
            }else{
                results.put("meeting_seq","");
            }

            if(!TextUtils.isEmpty(voteIdMsg) && voteIdMsg.length() > 0){
                results.put("v_id",voteIdMsg.deleteCharAt(voteIdMsg.lastIndexOf(",")).toString());
            }else{
                results.put("v_id","");
            }

            if(!TextUtils.isEmpty(voteNumMsg) && voteNumMsg.length() > 0){
                results.put("vote_number",voteNumMsg.deleteCharAt(voteNumMsg.lastIndexOf(",")).toString());
            }else{
                results.put("vote_number","");
            }
        }
        return results;
    }
}

