package com.thinkive.android.trade_bz.a_rr.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCollateralSecurityActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.RSelectCollaterSecurityAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectCollaterSecurityServiceImpl;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.tools.StockFuzzyQueryManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 融资融券--查询--担保品证券
 * Announcements：
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */
public class RSelectCollaterSecurityFragment extends AbsBaseFragment implements KeyboardManager.OnKeyCodeDownListener {
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
     * 该类的控制器
     */
    private RCollaterSecurityController mController;
    /**
     * 自定义的listView对象
     */
    private PullToRefreshListView mPullToRefreshListView;
    /**
     * 证券代码
     */
    private EditText mEdtCode;
    /**
     * 整个收缩框的外布局
     */
    private RelativeLayout mRlLayout;
    /**
     * 股票模糊查询管理器
     */
    private StockFuzzyQueryManager mStockFuzzyQueryManager;
    /**
     * 框架内的原生自绘键盘管理器
     */
    private KeyboardManager mStockCodeEdKeyboardManager;
    /*
    * 搜索输入框之前的布局
    * */
    private TextView mTvPreCode;
    private int lastLenth = -1;
    private Dialog mProgressDialog;

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
        mStockFuzzyQueryManager.setPopupwindowWidth(mRlLayout.getWidth());
        mStockFuzzyQueryManager.setPopupWindowReserveWidthReferView(mRlLayout);
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
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//        }
        mEdtCode = (EditText) view.findViewById(R.id.edt_collater_code);
        mRlLayout = (RelativeLayout) view.findViewById(R.id.lin_lay_collater);
        mTvPreCode = (TextView) view.findViewById(R.id.tv_collater_code_pre);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mPullToRefreshListView, mController);
        registerListener(AbsBaseController.ON_TEXT_CHANGE, mEdtCode, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvPreCode, mController);


        mEdtCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setEdtCursor(mEdtCode);
                }
            }
        });
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
        mStockCodeEdKeyboardManager.setOnKeyCodeDownListener(this);
        setTheme();
        initProcessDialog();
    }
    private void initProcessDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new Dialog(getContext(),R.style.transparent);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_progress, null);
            mProgressDialog.setContentView(view);
            Window dialogWindow = mProgressDialog.getWindow();
            dialogWindow.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.y = 20;
            //            lp.alpha = 0.5f;// 透明度
            //            lp.dimAmount = 0.5f;// 黑暗度
            dialogWindow.setAttributes(lp);
        }
        mProgressDialog .show();
    }
    @Override
    protected void setTheme() {

    }

    public void saveData(ArrayList<RSelectCollaterSecurityBean> list) {
        mActivity.saveData(list);
    }

    /**
     * 得到担保品证券的数据列表
     *
     * @param datalist
     */
    public void getCollaterSecurityData(ArrayList<RSelectCollaterSecurityBean> datalist) {
        if (datalist == null || datalist.size() == 0) {
            mProgressDialog.dismiss();
            mPullToRefreshListView.setVisibility(View.GONE);
        } else {
            mProgressDialog.dismiss();
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(datalist);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
    }

    /*
    * 以空股票代码作为key查询时 先去加载内存数据
    * */
    public boolean processMemoryOriginDataList() {
        if (mActivity.getData().size() == 0) {
            return false;
        } else {
            mProgressDialog.dismiss();
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(mActivity.getData());
            mAdapter.notifyDataSetChanged();
            refreshComplete();
            return true;
        }

    }

    /**
     * 实时监听代码输入
     */
    public void onTextChange() {
        String inputCode = mEdtCode.getText().toString().trim();
        int length = inputCode.length();

        if (length == 0) {
            if (lastLenth > 0) {
                showPreTv();
            }
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            if (!processMemoryOriginDataList()) {
                mServices.requestCollaterSecurity("");
            }
        } else {
            setEdtCursor(mEdtCode);
            if (length > 2 && length < 6) { // 输入了，但没有输入完成的时候
                // 发消息给行情，查询股票输入提示列表
                mStockFuzzyQueryManager.execQuery(inputCode, "1", mRlLayout);
            } else if (length == 6) {
                mStockCodeEdKeyboardManager.dismiss();
                mStockFuzzyQueryManager.dismissQueryPopupWindow();
                onSelectShow(inputCode);
            }
        }
        mStockFuzzyQueryManager.dismissQueryPopupWindow();
        lastLenth = length;
    }

    void showRealEdt() {
        mEdtCode.setVisibility(View.VISIBLE);
        mTvPreCode.setVisibility(View.GONE);
        mEdtCode.setFocusable(true);
        mEdtCode.setFocusableInTouchMode(true);
        mEdtCode.requestFocus();
        mEdtCode.requestFocusFromTouch();
        setEdtCursor(mEdtCode);
//        mEdtCode.performClick();
    }

    void showPreTv() {
        mTvPreCode.setVisibility(View.VISIBLE);
        mEdtCode.setVisibility(View.GONE);
    }

    /**
     * 查询时的状态
     */
    private void onSelectShow(String code) {
        boolean isMatching = false;
        //先去内存中匹配 如果匹配不到或者内存还未初始化再去查询
        if (mActivity.getData().size() != 0) {
            ArrayList<RSelectCollaterSecurityBean> newList = new ArrayList<>();
            for (int i = 0; i < mActivity.getData().size(); i++) {
                RSelectCollaterSecurityBean bean = mActivity.getData().get(i);
                String stock_code = bean.getStock_code();
                if (stock_code.equals(code)) {
                    newList.add(bean);
                    mAdapter.setListData(newList);
                    mAdapter.notifyDataSetChanged();
                    isMatching = true;
                    newList = null;
                    break;
                }
            }
            if (!isMatching) {
                mProgressDialog.dismiss();
                mPullToRefreshListView.setVisibility(View.GONE);
                mStockCodeEdKeyboardManager.dismiss();
                TradeUtils.hideSystemKeyBoard(mActivity);
                mStockCodeEdKeyboardManager.dismiss();
            }
        } else {
            mServices.requestCollaterSecurity(code);
            mProgressDialog.dismiss();
            mPullToRefreshListView.setVisibility(View.GONE);
            mStockCodeEdKeyboardManager.dismiss();
            TradeUtils.hideSystemKeyBoard(mActivity);
        }

    }

    /**
     * 展示股票代码模糊查询结果的listview的item单击事件
     */
    public void onSearchListViewItemClick(int position) {
        CodeTableBean SelectBean = mStockFuzzyQueryManager.getSearchStocksAdapter().getItem(position);
        mEdtCode.setText(SelectBean.getCode());
        setEdtCursor(mEdtCode);
        onSelectShow(SelectBean.getCode());
    }

    private void setEdtCursor(EditText et) {
        if (et == null) {
            return;
        }
        CharSequence text = et.getText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
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
        showPreTv();
        if (!processMemoryOriginDataList()) {
            mServices.requestCollaterSecurity("");
        }
    }

    public void clearFocus() {
        mEdtCode.clearFocus();
    }

    @Override
    public void onKeyCodeDown() {
        if (mStockCodeEdKeyboardManager != null) {
            mStockCodeEdKeyboardManager.dismiss();
        }
        if (TextUtils.isEmpty(mEdtCode.getText())) {
            showPreTv();
        } else if (mEdtCode.getText().length() == 6) {
            onSelectShow(mEdtCode.getText().toString());
        }

    }
}

/**
 * 控制类
 */
class RCollaterSecurityController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener, View.OnClickListener,
        TextWatcher, ListView.OnItemClickListener {

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
        if (resId == R.id.tv_collater_code_pre) { // 查询
            mFragment.showRealEdt();
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
        mFragment.onTextChange();
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



