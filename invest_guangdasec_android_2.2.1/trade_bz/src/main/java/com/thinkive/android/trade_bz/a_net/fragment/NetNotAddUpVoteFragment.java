package com.thinkive.android.trade_bz.a_net.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.adapter.NetNotAddUpVoteAdapter;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustBean;
import com.thinkive.android.trade_bz.a_net.bll.NetNotAddVoteServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.views.popupwindows.PopupWindowShowBottom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 网络投票-非累计议案投票
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/3
 */
public class NetNotAddUpVoteFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private NetNotAddVoteServicesImpl mServices;
    private NetNotAddVoteController mController;
    private NetNotAddUpVoteAdapter mAdapter;
    private LinearLayout mLinLoading;
    private LinearLayout mLinHaveDate;
    private LinearLayout mLinNoDate;
    private PullToRefreshListView mLvRefreshList;
    private ListView mListView;
    private Button mTvClickCommit;
    private PopupWindowShowBottom mOneKeyPop;
    private  ArrayList<NetVoteEntrustBean> mNetVoteList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_net_not_add_vote, null);
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
        mLinHaveDate = (LinearLayout) view.findViewById(R.id.lin_have_date);
        mLinNoDate = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mLvRefreshList.getRefreshableView();
        mListView.setDivider(null);
        mTvClickCommit = (Button) view.findViewById(R.id.tv_click_commit);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK,mTvClickCommit,mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mServices = new NetNotAddVoteServicesImpl(this);
        mController = new NetNotAddVoteController(this);
        mAdapter = new NetNotAddUpVoteAdapter(mActivity,this);
        mOneKeyPop = new PopupWindowShowBottom(mActivity, mController);
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
                    if(bean.getVote_type().equals("P")){
                        mNetVoteList.add(bean);
                    }
                }
            }
            onGetNotAddVoteList(mNetVoteList);
        }
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到非累计议案数据集
     * @param dataList
     */
    public void onGetNotAddVoteList(ArrayList<NetVoteEntrustBean> dataList){
        if(dataList != null && dataList.size() > 0){
            mLinLoading.setVisibility(View.GONE);
            mLinHaveDate.setVisibility(View.VISIBLE);
            mLinNoDate.setVisibility(View.GONE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }else{
            mLinLoading.setVisibility(View.GONE);
            mLinHaveDate.setVisibility(View.GONE);
            mLinNoDate.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 同意
     */
    public void onClickAdapterAgree(NetVoteEntrustBean bean){
        bean.setCheckAgree(true);
        bean.setVote_result("1");
        bean.setCheckOppose(false);
        bean.setCheckGiveUp(false);
    }
    /**
     * 反对
     */
    public void onClickAdapterOppose(NetVoteEntrustBean bean){
        bean.setCheckOppose(true);
        bean.setVote_result("2");
        bean.setCheckAgree(false);
        bean.setCheckGiveUp(false);
    }
    /**
     * 弃权
     */
    public void onClickAdapterGiveUp(NetVoteEntrustBean bean){
        bean.setCheckGiveUp(true);
        bean.setVote_result("3");
        bean.setCheckAgree(false);
        bean.setCheckOppose(false);
    }

    /**
     * 点击一键表决
     */
    public void onClickOneKeyCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if (mOneKeyPop != null && mOneKeyPop.isShowing()) {
            mOneKeyPop.dismiss();
        } else {
            mOneKeyPop.showAtLocation(mLinHaveDate, Gravity.CENTER, 0, 0);
        }
    }
    /**
     * 全部同意
     */
    public void ClickPopAllAgree(){
        if(mNetVoteList != null && mNetVoteList.size() > 0){
            for(NetVoteEntrustBean bean : mNetVoteList){
                bean.setCheckAgree(true);
                bean.setCheckOppose(false);
                bean.setCheckGiveUp(false);
                bean.setVote_result("1");
            }
        }
        mAdapter.notifyDataSetChanged();
        mOneKeyPop.dismiss();
    }
    /**
     * 全部反对
     */
    public void ClickPopAllOppose(){
        if(mNetVoteList != null && mNetVoteList.size() > 0){
            for(NetVoteEntrustBean bean : mNetVoteList){
                bean.setCheckAgree(false);
                bean.setCheckOppose(true);
                bean.setCheckGiveUp(false);
                bean.setVote_result("2");
            }
        }
        mAdapter.notifyDataSetChanged();
        mOneKeyPop.dismiss();
    }
    /**
     * 全部弃权
     */
    public void ClickPopAllGiveUp(){
        if(mNetVoteList != null && mNetVoteList.size() > 0){
            for(NetVoteEntrustBean bean : mNetVoteList){
                bean.setCheckAgree(false);
                bean.setCheckOppose(false);
                bean.setCheckGiveUp(true);
                bean.setVote_result("3");
            }
        }
        mAdapter.notifyDataSetChanged();
        mOneKeyPop.dismiss();
    }

    /**
     * 点击提交
     */
    public void onClickCommit(){
        if(mNetVoteList != null && mNetVoteList.size() > 0){
            HashMap<String, String> resultMap = resultMap(mNetVoteList);
            for(NetVoteEntrustBean bean : mNetVoteList){
                if(TextUtils.isEmpty(bean.getVote_result())){
                    ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.net_vote34));
                    return;
                }
            }
            if(resultMap != null && resultMap.size() > 0){
                mServices.requestCommitVote(resultMap);
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
                bean.setCheckAgree(false);
                bean.setCheckOppose(false);
                bean.setCheckGiveUp(false);
                bean.setVote_result("");
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
        StringBuilder voteResultMsg = new StringBuilder();
        String exchange_type = "";
        String stock_account = "";
        if(dataList != null && dataList.size() > 0) {
            for (NetVoteEntrustBean list : dataList) {
                String code = list.getRef_code()+",";
                String meetId = list.getMeeting_seq()+",";
                String voteId = list.getV_id()+",";
                String voteResult = list.getVote_result()+",";
                exchange_type = list.getExchange_type();
                stock_account = list.getStock_account();
                codeMsg.append(code);
                meetIdMsg.append(meetId);
                voteIdMsg.append(voteId);
                voteResultMsg.append(voteResult);
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

            if(!TextUtils.isEmpty(voteResultMsg) && voteResultMsg.length() > 0){
                results.put("vote_result",voteResultMsg.deleteCharAt(voteResultMsg.lastIndexOf(",")).toString());
            }else{
                results.put("vote_result","");
            }
        }
        return results;
    }
}

class NetNotAddVoteController extends AbsBaseController implements View.OnClickListener {

    private NetNotAddUpVoteFragment mFragment;

    public NetNotAddVoteController(NetNotAddUpVoteFragment fragment) {
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
        if(clickId == R.id.tv_click_commit){ // 一键表决
            mFragment.onClickOneKeyCommit();
        }else if(clickId == R.id.pop_all_agree){ // 全部同意
            mFragment.ClickPopAllAgree();
        }else if(clickId == R.id.pop_all_oppose){ // 全部反对
            mFragment.ClickPopAllOppose();
        }else if(clickId == R.id.pop_all_give_up){ // 全部弃权
            mFragment.ClickPopAllGiveUp();
        }
    }
}



