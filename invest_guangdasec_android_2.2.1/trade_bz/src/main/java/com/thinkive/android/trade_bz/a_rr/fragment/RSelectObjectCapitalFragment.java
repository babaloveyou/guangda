package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.adapter.RSelectObjectCapitalAdapter;
import com.thinkive.android.trade_bz.a_rr.adapter.RSelectPopListViewAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectObjectCapitalServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.views.popupwindows.PopupWindowInListView;

import java.util.ArrayList;

/**
 * 融资融券--查询--标的证券--融资标的
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/20
 */
public class RSelectObjectCapitalFragment extends AbsBaseFragment {
    /**
     * ListView
     */
    private ListView mListView;
    /**
     * 适配器
     */
    private RSelectObjectCapitalAdapter mAdapter;
    /**
     * Fragment的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     * 该Fragement的业务类
     */
    private RSelectObjectCapitalServiceImpl mServices;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 该类的控制器
     */
    private RObjectCapitalController mController;
    /**
     * 自定义的listView对象
     */
    private PullToRefreshListView mPullToRefreshListView;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLoading;
    /**
     * 证券代码
     */
    private EditText mEdtCode;
    /**
     * 证券名称
     */
    private TextView mTvName;
    /**
     * 查询按钮
     */
    private TextView mTvClick;
    /**
     * 弹出的对话框
     */
    private PopupWindowInListView mPopupWindow;
    /**
     * 弹出对话框的适配器
     */
    private RSelectPopListViewAdapter mApapterForPop;
    /**
     * 数据列表
     */
    private ArrayList<RSelectCollaterSecurityBean> mDataList;
    /**
     * 整个收缩框的外布局
     */
    private LinearLayout mLinLayout;
    /**
     * 证券数据集
     */
    private RSelectCollaterSecurityBean mStockBean;
    /**
     * 框架内的原生自绘键盘管理器
     */
    private KeyboardManager mStockCodeEdKeyboardManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_select_object_captial, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mStockCodeEdKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_r_select_capital);
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.ll_capital_list_loading);
        mLiNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mEdtCode = (EditText) view.findViewById(R.id.edt_capital_code);
        mTvName = (TextView) view.findViewById(R.id.tv_capital_name);
        mTvClick = (TextView) view.findViewById(R.id.tv_capital_click);
        mLinLayout=(LinearLayout)view.findViewById(R.id.lin_object_capital);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mPullToRefreshListView, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvClick, mController);
        registerListener(AbsBaseController.ON_TEXT_CHANGE, mEdtCode, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mAdapter = new RSelectObjectCapitalAdapter(mActivity);
        mController = new RObjectCapitalController(this);
        mServices = new RSelectObjectCapitalServiceImpl(this);
        mApapterForPop=new RSelectPopListViewAdapter(mActivity);
        mPopupWindow = new PopupWindowInListView(mActivity, mController);
        mStockCodeEdKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
    }

    @Override
    protected void initViews() {
        //请求数据
        mServices.requestObjectCapital("");
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        mListView.addHeaderView(LayoutInflater.from(mActivity).inflate(R.layout.head_r_select_object_capital, null));
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到融资标的的数据列表
     * @param datalist
     */
    public void getObjectCapitalData(ArrayList<RSelectCollaterSecurityBean> datalist) {
        if (datalist == null || datalist.size() == 0) {
            mLiNoData.setVisibility(View.VISIBLE);
            mLoading.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.GONE);
        } else {
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(datalist);
            mListView.setAdapter(mAdapter);
            mDataList = datalist;
        }
        refreshComplete();
    }

    /**
     * 点击pop弹出列表项所执行的操作
     */
    public void clickPopList(int position) {
        mStockBean = mApapterForPop.getItem(position);
        mEdtCode.setText(mStockBean.getStock_code());
        mTvName.setText(mStockBean.getStock_name());
        mPopupWindow.dismiss();
    }

    /**
     * 点击‘查询’所执行的操作
     */
    public void clickTvSelect() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String inputCode = mEdtCode.getText().toString().trim();
        if(TextUtils.isEmpty(inputCode) || inputCode.length() == 6) {
            mServices.requestObjectCapital(inputCode);
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.VISIBLE);
            mPullToRefreshListView.setVisibility(View.GONE);
            TradeUtils.hideSystemKeyBoard(mActivity);
            mStockCodeEdKeyboardManager.dismiss();
        }else{
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.r_revocation_error));
        }
    }

    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mPullToRefreshListView.onPullDownRefreshComplete();
        mPullToRefreshListView.onPullUpRefreshComplete();
        mPullToRefreshListView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }

    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestObjectCapital("");
    }

    /**
     * 实时监听代码输入
     */
    public void onTextChange(){
        String inputCode = mEdtCode.getText().toString().trim();
        ArrayList listPop=new ArrayList();
        int length = inputCode.length();
        if(mDataList != null && length > 2 && length < 6){
            for(RSelectCollaterSecurityBean bean: mDataList ){
                String stockCode=bean.getStock_code().substring(0,length);
                if(stockCode.contains(inputCode)){
                    listPop.add(bean);
                }
            }
            if(listPop != null && listPop.size() >0){
                mApapterForPop.setListData(listPop);
                mPopupWindow.setListAdapter(mApapterForPop);
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    mPopupWindow.showPopwindow(mLinLayout, mLinLayout.getWidth(),mPopupWindow.getListViewHeight(mApapterForPop),0,0);
                }
            }
        }else{
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            }
        }
    }
}

/**
 * 控制类
 */
class RObjectCapitalController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener,View.OnClickListener,
        TextWatcher,ListView.OnItemClickListener {

    private RSelectObjectCapitalFragment mFragment;

    public RObjectCapitalController(RSelectObjectCapitalFragment fragment) {
        this.mFragment = fragment;
    }
    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView) view).setOnRefreshListener(this);
                break;
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_TEXT_CHANGE:
                ((EditText) view).addTextChangedListener(this);
                break;

        }
    }
    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_capital_click) { // 查询
            mFragment.clickTvSelect();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragment.clickPopList(position);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        mFragment.onTextChange();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
    }
}


