package com.thinkive.android.trade_bz.a_trans.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.activity.TransSubHqOneActivityTrade;
import com.thinkive.android.trade_bz.a_trans.activity.TransSubHqTwoActivityTrade;
import com.thinkive.android.trade_bz.a_trans.bean.TransSubHqBean;
import com.thinkive.android.trade_bz.a_trans.bll.TransHqSelectServiceImpl;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 定价申报行情查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransHqOneFragment extends AbsBaseFragment {
    private TransSubHqOneActivityTrade mActivity;
    private TransHqOneController mController;
    private TransHqSelectServiceImpl mServices;
    private RadioGroup mRadioGroup;
    private RadioButton mRbAll;
    private RadioButton mRbBuy;
    private RadioButton mRbSale;
    /**
     * 输入股票代码
     */
    private EditText mEdtInputCode;
    /**
     * 点击查询按钮
     */
    private Button mBtnClickSelect;
    /**
     * 查询类别
     */
    private String mBsFlag = "";
    /**
     * 全局资源
     */
    private Resources mResources;
    /**
     * 模糊查询弹出框
     */
//    private StockFuzzyQueryManager mStockFuzzyQueryManager;
    /**
     * 代码输入框的键盘
     */
    private KeyboardManager mStockCodeEdKeyboardManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trans_hq_select, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mStockFuzzyQueryManager.setPopupwindowWidth(mEdtInputCode.getWidth());
//        mStockFuzzyQueryManager.setPopupWindowReserveWidthReferView(mEdtInputCode);
//        mStockFuzzyQueryManager.initListViewPopupwindow(mController);
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mStockCodeEdKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mRadioGroup = (RadioGroup)view.findViewById(R.id.rg_trans_hq);
        mRbAll = (RadioButton)view.findViewById(R.id.rb_trans_hq_all);
        mRbBuy = (RadioButton)view.findViewById(R.id.rb_trans_hq_buy);
        mRbSale = (RadioButton)view.findViewById(R.id.rg_trans_sale);
        mEdtInputCode = (EditText)view.findViewById(R.id.edt_trans_hq_input);
        mBtnClickSelect = (Button)view.findViewById(R.id.btn_trans_hq_select);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRadioGroup, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnClickSelect, mController);
        registerListener(AbsBaseController.ON_TEXT_CHANGE, mEdtInputCode, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TransSubHqOneActivityTrade) getActivity();
        mResources = mActivity.getResources();
        mServices = new TransHqSelectServiceImpl(this);
        mController=new TransHqOneController(this);
//        mStockFuzzyQueryManager = StockFuzzyQueryManager.getInstance(mActivity);
    }

    @Override
    protected void initViews() {
        mStockCodeEdKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtInputCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
        setTheme();
    }

    @Override
    protected void setTheme() {}

    /**
     * 当股票代码输入框中的输入内容改变的时候
     */
    public void onSearchTextChange() {
//        String stockCode = mEdtInputCode.getText().toString().trim();
//        if(stockCode.length() > 2 && stockCode.length() < 6 ){
//            mStockFuzzyQueryManager.execQuery(stockCode,mEdtInputCode);
//        }else{
//            mStockFuzzyQueryManager.dismissQueryPopupWindow();
//        }
    }

    /**
     * 点击查询按钮
     */
    public void onClickBtnSelect(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String stockCode = mEdtInputCode.getText().toString().trim();
        if(stockCode != null && stockCode.length() == 6){
            mServices.startLinkage(stockCode);
        }else{
            ToastUtils.toast(mActivity, mResources.getString(R.string.trans_stock25));
        }
    }

    /**
     * 管理radioButton点击事件
     */
    public void onCheckChange(int checkId){
        if(checkId == R.id.rb_trans_hq_all){
            mBsFlag = "";
            mRbAll.setTextColor(mResources.getColor(R.color.trade_color_white));
            mRbBuy.setTextColor(mResources.getColor(R.color.trade_color1));
            mRbSale.setTextColor(mResources.getColor(R.color.trade_color1));
        }else if(checkId == R.id.rb_trans_hq_buy){
            mBsFlag = "6B";
            mRbAll.setTextColor(mResources.getColor(R.color.trade_color1));
            mRbBuy.setTextColor(mResources.getColor(R.color.trade_color_white));
            mRbSale.setTextColor(mResources.getColor(R.color.trade_color1));
        }else if(checkId == R.id.rg_trans_sale){
            mBsFlag = "6S";
            mRbAll.setTextColor(mResources.getColor(R.color.trade_color1));
            mRbBuy.setTextColor(mResources.getColor(R.color.trade_color1));
            mRbSale.setTextColor(mResources.getColor(R.color.trade_color_white));
        }
    }
    /**
     * 点击模糊查询弹出框的item
     */
    public void onSearchListViewItemClick(int position) {
//        CodeTableBean SelectBean = mStockFuzzyQueryManager.getSearchStocksAdapter().getItem(position);
//        mEdtInputCode.setText(SelectBean.getCode());
    }

    /**
     * 当该证券一切正常时
     * 查询该证券行情
     */
    public void onGetHqData(CodeTableBean bean) {
        mStockCodeEdKeyboardManager.dismiss();
        mServices.requestLimitSubData(bean.getCode(),mBsFlag);
    }

    /**
     * 得到该证券行情数据
     * @param dataList
     */
    public void getTodayTradeData(ArrayList<TransSubHqBean> dataList) {
        if(dataList == null || dataList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.trans_hold12));
        }else {
            Intent intent = new Intent(mActivity,TransSubHqTwoActivityTrade.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("hq_data",dataList);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}

class TransHqOneController extends AbsBaseController implements View.OnClickListener,
        ListView.OnItemClickListener, TextWatcher,RadioGroup.OnCheckedChangeListener{

    private TransHqOneFragment mFragment = null;

    public TransHqOneController(TransHqOneFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_ITEM_CLICK:
                ((ListView) view).setOnItemClickListener(this);
                break;
            case ON_TEXT_CHANGE:
                ((EditText) view).addTextChangedListener(this);
                break;
            case ON_CHECK_CHANGE:
                ((RadioGroup) view).setOnCheckedChangeListener(this);
                break;
        }
    }
    @Override
    public void onClick(View v) {
        int clickId = v.getId();
        if(clickId == R.id.btn_trans_hq_select){
            mFragment.onClickBtnSelect();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragment.onSearchListViewItemClick(position);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragment.onCheckChange(checkedId);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mFragment.onSearchTextChange();
    }
    @Override
    public void afterTextChanged(Editable s) { }
}


