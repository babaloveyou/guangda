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

import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCollateralSecurityActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.RSelectCollaterSecurityAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectCollaterSecurityServiceImpl;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.StockFuzzyQueryManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 融资融券--查询--担保品证券
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */
public class RSelectCollaterSecurityFragment extends AbsBaseFragment {
    /**
     * ListView
     */
    private ListView mListView;
    /**
     * 适配器
     */
    private RSelectCollaterSecurityAdapter mAdapter;
    /**
     * Fragment的宿主
     */
    private RSelectCollateralSecurityActivity mActivity;
    /**
     * 该Fragement的业务类
     */
    private RSelectCollaterSecurityServiceImpl mServices;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 该类的控制器
     */
    private RCollaterSecurityController mController;
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
     * 整个收缩框的外布局
     */
    private LinearLayout mLinLayout;
    /**
     * 股票模糊查询管理器
     */
    private StockFuzzyQueryManager mStockFuzzyQueryManager;
    /**
     * 框架内的原生自绘键盘管理器
     */
    private KeyboardManager mStockCodeEdKeyboardManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_select_collater_security, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mStockFuzzyQueryManager.setPopupwindowWidth(mLinLayout.getWidth());
        mStockFuzzyQueryManager.setPopupWindowReserveWidthReferView(mLinLayout);
        mStockFuzzyQueryManager.initListViewPopupwindow(mController);
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mStockCodeEdKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_r_select_collater);
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.ll_collater_list_loading);
        mLiNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mEdtCode = (EditText) view.findViewById(R.id.edt_collater_code);
        mTvName = (TextView) view.findViewById(R.id.tv_collater_name);
        mTvClick = (TextView) view.findViewById(R.id.tv_collater_click);
        mLinLayout=(LinearLayout)view.findViewById(R.id.lin_lay_collater);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mPullToRefreshListView, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvClick, mController);
        registerListener(AbsBaseController.ON_TEXT_CHANGE, mEdtCode, mController);
    }

    @Override
    protected void initData() {
        mActivity = (RSelectCollateralSecurityActivity) getActivity();
        mAdapter = new RSelectCollaterSecurityAdapter(mActivity);
        mController = new RCollaterSecurityController(this);
        mServices = new RSelectCollaterSecurityServiceImpl(this);
        mStockFuzzyQueryManager = StockFuzzyQueryManager.getInstance(mActivity);
    }

    @Override
    protected void initViews() {
        //请求数据
        mServices.requestCollaterSecurity("");
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        mListView.addHeaderView(LayoutInflater.from(mActivity).inflate(R.layout.head_r_select_collater_security, null));
        mStockCodeEdKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到担保品证券的数据列表
     * @param datalist
     */
    public void getCollaterSecurityData(ArrayList<RSelectCollaterSecurityBean> datalist) {
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
        }
        refreshComplete();
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
            onSelectShow(inputCode);
        }else{
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.r_revocation_error));
        }
    }

    /**
     * 实时监听代码输入
     */
    public void onTextChange(){
        String inputCode = mEdtCode.getText().toString().trim();
        int length = inputCode.length();
        if (length == 0) {
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
        } else if (length > 2 && length < 6) { // 输入了，但没有输入完成的时候
            // 发消息给行情，查询股票输入提示列表
            mStockFuzzyQueryManager.execQuery(inputCode,"1", mLinLayout);
        } else if (length == 6) {
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            onSelectShow(inputCode);
        }
        mStockFuzzyQueryManager.dismissQueryPopupWindow();
    }

    /**
     * 查询时的状态
     */
    private void onSelectShow(String code){
        mServices.requestCollaterSecurity(code);
        mLiNoData.setVisibility(View.GONE);
        mLoading.setVisibility(View.VISIBLE);
        mPullToRefreshListView.setVisibility(View.GONE);
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 展示股票代码模糊查询结果的listview的item单击事件
     */
    public void onSearchListViewItemClick(int position) {
        CodeTableBean SelectBean = mStockFuzzyQueryManager.getSearchStocksAdapter().getItem(position);
        mEdtCode.setText(SelectBean.getCode());
        mTvName.setText(SelectBean.getName());
        onSelectShow(SelectBean.getCode());
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
        mServices.requestCollaterSecurity("");
    }
}

/**
 * 控制类
 */
class RCollaterSecurityController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener,View.OnClickListener,
        TextWatcher,ListView.OnItemClickListener{

    private RSelectCollaterSecurityFragment mFragment;

    public RCollaterSecurityController(RSelectCollaterSecurityFragment fragment) {
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
        if (resId == R.id.tv_collater_click) { // 查询
            mFragment.clickTvSelect();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragment.onSearchListViewItemClick(position);
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



