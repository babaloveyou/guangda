package com.thinkive.android.trade_bz.a_out.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.adapter.FundRiskQuestionAdapter;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestQuestion;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestResultBean;
import com.thinkive.android.trade_bz.a_out.bll.FundRiskTestServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_out.activity.FundRiskActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 基金交易--查询--风险等级查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/24
 */

public class FundRiskFragment extends AbsBaseFragment {
    private FundRiskActivity mActivity;
    private FundRiskController mController;
    private FundRiskTestServiceImpl mService;
    /**
     * 测试问卷视图根布局
     */
    private LinearLayout mLlTestPaper;
    /**
     * 测试结果视图根布局
     */
    private LinearLayout mLlTestResult;
    /**
     * 测试结果-风险等级
     */
    private TextView mTvRiskLevel;
    /**
     * 测试结果-是否过期
     */
    private TextView mTvIsOverDate;
    /**
     * 测试结果-测试日期
     */
    private TextView mTvTestDate;
    /**
     * 重新测评按钮
     */
    private TextView mTvretest;
    /**
     * 正在加载页面
     */
    private LinearLayout mLlLoading;
    /**
     * 问卷中的所有问题集合
     */
    private ArrayList<FundRiskTestQuestion> mQuestions;
    /**
     * 可刷新列表
     */
    private PullToRefreshListView mRefreshList;
    /**
     * 列表
     */
    private ListView mListView;
    /**
     *提交按钮
     */
    private Button mBtnClick;
    /**
     * 问卷适配器
     */
    private FundRiskQuestionAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_select_risk, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mLlTestPaper = (LinearLayout) view.findViewById(R.id.ll_risk_test_paper);
        mLlTestResult = (LinearLayout) view.findViewById(R.id.ll_risk_test_result);
        mTvRiskLevel = (TextView) view.findViewById(R.id.tv_risk_test_value_level);
        mTvIsOverDate = (TextView) view.findViewById(R.id.tv_risk_test_value_is_over_date);
        mTvTestDate = (TextView) view.findViewById(R.id.tv_risk_test_value_date);
        mTvretest = (TextView) view.findViewById(R.id.tv_retest);
        mLlLoading = (LinearLayout) view.findViewById(R.id.ll_risk_list_loading);
        mRefreshList = (PullToRefreshListView)view.findViewById(R.id.lv_refresh_list);
        mListView = mRefreshList.getRefreshableView();
        mListView.setDivider(null);
        mBtnClick = (Button)view.findViewById(R.id.tv_click_commit);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvretest, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnClick, mController);
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mRefreshList, mController);
    }

    @Override
    protected void initData() {
        mActivity = (FundRiskActivity) getActivity();
        mController = new FundRiskController(this);
        mService = new FundRiskTestServiceImpl(this);
        mAdapter = new FundRiskQuestionAdapter(mActivity);
    }

    @Override
    protected void initViews() {
        setTheme();
        //设置禁止下拉加载更多
        mRefreshList.setPullLoadEnabled(false);
        mService.requestTestResult("isOk");
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 进入程序判断测评是否过期
     */
    public void onGetIsCheckOk(FundRiskTestResultBean data){
        String flag = data.getRisk_flag();
        if(flag.contains("0")){
            onGetIsOutResult(data);
        }else if(flag.contains("1")){
            mService.requestTestPaper();
        }
        onGetIsOutResult(data);
    }

    /**
     * 测评完成后，得到测评结果
     * @param bean
     */
    public void onGetIsOutResult(FundRiskTestResultBean bean){
        showTestResult();
        mTvRiskLevel.setText(bean.getRisk_name());
        mTvTestDate.setText(bean.getUpdate_date());
        mTvIsOverDate.setText(formatRiskFlag(bean.getRisk_flag()));
    }

    /**
     * 格式化“是否过期”
     */
    private String formatRiskFlag(String str){
        if(str.contains("0")){
            str = mActivity.getResources().getString(R.string.fund_risk2);
        }else if(str.contains("1")){
            str = mActivity.getResources().getString(R.string.fund_risk1);
        }
        return str;
    }

    /**
     * 显示正在加载
     */
    private void showLoading() {
        mLlLoading.setVisibility(View.VISIBLE);
        mLlTestResult.setVisibility(View.GONE);
        mLlTestPaper.setVisibility(View.GONE);
    }

    /**
     * 显示测评问卷
     */
    private void showTestPaper() {
        mLlLoading.setVisibility(View.GONE);
        mLlTestResult.setVisibility(View.GONE);
        mLlTestPaper.setVisibility(View.VISIBLE);
    }

    /**
     * 显示测评结果
     */
    private void showTestResult() {
        mLlLoading.setVisibility(View.GONE);
        mLlTestResult.setVisibility(View.VISIBLE);
        mLlTestPaper.setVisibility(View.GONE);
    }

    /**
     * 获取从服务器返回的问题，并显示
     * 开始答题
     * @param questions
     */
    public void onGetTestPaper(ArrayList<FundRiskTestQuestion> questions) {
        // 获取服务器返回的数据
        mQuestions = questions;
        // 展示问卷布局
        showTestPaper();
        mAdapter.setListData(mQuestions);
        mListView.setAdapter(mAdapter);
        refreshComplete();
    }

    /**
     * 测评完成后，得到测评结果
     * @param bean
     */
    public void onGetTestResult(FundRiskTestResultBean bean) {
        showTestResult();
        if(bean != null){
            mTvRiskLevel.setText(bean.getRisk_name());
            mTvTestDate.setText(bean.getUpdate_date());
            mTvIsOverDate.setText(formatRiskFlag(bean.getRisk_flag()));
        }
    }
    /**
     * “重新测评”按钮单击事件监听
     */
    public void onClickRetest() {
        showLoading();
        mService.requestTestPaper();
    }

    /**
     * 提交按钮
     */
    public void clickBtnCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        for(FundRiskTestQuestion question : mQuestions){
            if(TextUtils.isEmpty(question.getSelect_answer())){
                ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.fund_risk3));
                return;
            }
        }
        mService.requestTestSubmit(mQuestions);
    }

    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mService.requestTestPaper();
    }

    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mRefreshList.onPullDownRefreshComplete();
        mRefreshList.onPullUpRefreshComplete();
        mRefreshList.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }
}

/**
 *控制器
 */
class FundRiskController extends AbsBaseController implements View.OnClickListener,
        PullToRefreshListView.OnRefreshListener {

    private FundRiskFragment mFragment = null;

    public FundRiskController(FundRiskFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView)view).setOnRefreshListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tv_click_commit) { // 提交
            mFragment.clickBtnCommit();
        } else  if (viewId == R.id.tv_retest) { // “重新测评”按钮单击事件
            mFragment.onClickRetest();
        }
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}

