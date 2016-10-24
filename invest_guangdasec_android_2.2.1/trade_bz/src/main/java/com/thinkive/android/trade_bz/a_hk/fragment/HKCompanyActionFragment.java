package com.thinkive.android.trade_bz.a_hk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.activity.HKListActivity;
import com.thinkive.android.trade_bz.a_hk.bean.HKCompanyActionTypeBean;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockMessageBean;
import com.thinkive.android.trade_bz.a_hk.bll.HKCompanyActionServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 *  公司行为
 * @author 张雪梅
 * @company Thinkive
 * @date 16/5/23
 */

public class HKCompanyActionFragment extends AbsBaseFragment {
    public final static int BUSINESS_REQUEST_CODE = 66;
    public final static int SUB_REQUEST_CODE = 67;
    private HKCompanyActionController mController;
    private HKCompanyActionServicesImpl mServices;
    private TKFragmentActivity mActivity;
    private KeyboardManager mKeyboardManager;
    private View mChildScrollView;
    private ScrollView mScrollView;
    /**
     * 证券代码
     */
    private EditText mEdtStockCode;
    /**
     *证券名称
     */
    private TextView mTvStockName;
    /**
     *业务类型
     */
    private TextView mTvBusinessType;
    /**
     *申购类型
     */
    private TextView mTvSubType;
    /**
     *行为代码
     */
    private EditText mEdtActionCode;
    /**
     *委托数量
     */
    private EditText mEdtEntrustNum;
    /**
     *下单按钮
     */
    private Button mBtnCommit;
    /**
     *业务类型
     */
    private String[] mBusinessTypeStr;
    private String[] mBusinessTypeNum;
    private String mBusinessType = "";
    private ArrayList<HKCompanyActionTypeBean> mBusinessTypeList;
    /**
     * 申报类型
     */
    private String[] mSubTypeStr;
    private String[] mSubTypeNum;
    private String mSubType = "";
    private ArrayList<HKCompanyActionTypeBean> mSubTypeList;
    /**
     * 证券联动数据集
     */
    private HKStockMessageBean mDataLinkBean;

    public HKCompanyActionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mChildScrollView = inflater.inflate(R.layout.fragment_hk_company_action, null);
        findViews(mRootView);
        initData();
        setListeners();
        initViews();
        return mRootView;
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mScrollView=(ScrollView)view.findViewById(R.id.sll_layout);
        mScrollView.addView(mChildScrollView);
        mEdtStockCode = (EditText) mChildScrollView.findViewById(R.id.edt_hk_action_stock_code);
        mEdtStockCode.addTextChangedListener(mTextWatcher);
        mTvStockName = (TextView) mChildScrollView.findViewById(R.id.tv_hk_action_name);
        mTvBusinessType = (TextView) mChildScrollView.findViewById(R.id.tv_hk_action_type1);
        mTvSubType = (TextView) mChildScrollView.findViewById(R.id.tv_hk_action_type2);
        mEdtActionCode = (EditText) mChildScrollView.findViewById(R.id.edt_hk_action_code);
        mEdtEntrustNum = (EditText) mChildScrollView.findViewById(R.id.edt_hk_action_entrust_num);
        mBtnCommit = (Button) mChildScrollView.findViewById(R.id.btn_hk_action_commit);
    }

    @Override
    protected void initData() {
        mController = new HKCompanyActionController(this);
        mServices = new HKCompanyActionServicesImpl(this);
        mActivity = (TKFragmentActivity)getActivity();

        mBusinessTypeStr = mActivity.getResources().getStringArray(R.array.hk_business_type_str);
        mBusinessTypeNum = mActivity.getResources().getStringArray(R.array.hk_business_type_num);
        mBusinessTypeList  = new ArrayList<HKCompanyActionTypeBean>();

        mSubTypeStr = mActivity.getResources().getStringArray(R.array.hk_sub_type_str);
        mSubTypeNum = mActivity.getResources().getStringArray(R.array.hk_sub_type_num);
        mSubTypeList = new ArrayList<HKCompanyActionTypeBean>();
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtStockCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mTvBusinessType, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvSubType, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnCommit, mController);
    }

    @Override
    protected void initViews() {
        //业务类型数据集赋值
        for (int i = 0; i < mBusinessTypeStr.length; i++) {
            HKCompanyActionTypeBean bean = new HKCompanyActionTypeBean();
            bean.setEntrust_way_name(mBusinessTypeStr[i]);
            bean.setEntrust_way_num(mBusinessTypeNum[i]);
            mBusinessTypeList.add(bean);
        }
       //申报类型数据集赋值
        for (int i = 0; i < mSubTypeStr.length; i++) {
            HKCompanyActionTypeBean bean = new HKCompanyActionTypeBean();
            bean.setEntrust_way_name(mSubTypeStr[i]);
            bean.setEntrust_way_num(mSubTypeNum[i]);
            mSubTypeList.add(bean);
        }
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 监听证券输入情况
     */
    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 5) {
                //请求证券详情
                mServices.requestVoteSubLink(mEdtStockCode.getText().toString());
            }else{
                onGetFailMessage();
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    /**
     * 获取证券联动数据
     */
    public void onGetVoteSubLinkData(HKStockMessageBean bean){
        if(bean != null){
            mDataLinkBean = bean;
            mTvStockName.setText(bean.getName());
        }
    }
    /**
     * 未获取证券联动数据
     */
    public void onGetFailMessage(){
        mTvStockName.setText("");
        mDataLinkBean = null;
    }

    /**
     * 清除所有数据
     */
    public void clearAllData(){
        onGetFailMessage();
        mEdtStockCode.setText("");
        mTvBusinessType.setText("");
        mTvSubType.setText("");
        mEdtActionCode.setText("");
        mEdtEntrustNum.setText("");
        mBusinessType = "";
        mSubType = "";
    }

    /**
     * 点击业务类型类型
     */
    public void onClickBusinessType(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        TradeUtils.hideSystemKeyBoard(mActivity);
        mKeyboardManager.dismiss();
        if(mBusinessTypeList == null || mBusinessTypeList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_company_behavior_item7));
        }else{
            Intent intent = new Intent(mActivity,HKListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mBusinessTypeList);
            intent.putExtra("useType","company_action");
            startActivityForResult(intent,BUSINESS_REQUEST_CODE);
        }
    }

    /**
     * 点击申报类型
     */
    public void onClickSubType(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        TradeUtils.hideSystemKeyBoard(mActivity);
        if(mSubTypeList == null || mSubTypeList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_company_behavior_item8));
        }else{
            Intent intent = new Intent(mActivity,HKListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mSubTypeList);
            intent.putExtra("useType","company_action");
            startActivityForResult(intent,SUB_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        if(requestCode == BUSINESS_REQUEST_CODE){ //业务类型
            HKCompanyActionTypeBean bean =  mBusinessTypeList.get(position);
            mBusinessType = bean.getEntrust_way_num();
            mTvBusinessType.setText(bean.getEntrust_way_name());
        }else if(requestCode == SUB_REQUEST_CODE){ //申报类型
            HKCompanyActionTypeBean bean =  mSubTypeList.get(position);
            mSubType = bean.getEntrust_way_num();
            mTvSubType.setText(bean.getEntrust_way_name());
        }
    }

    /**
     * 点击提交按钮
     */
    public void onClickCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String stockCode = mEdtStockCode.getText().toString().trim();
        String actionCode = mEdtActionCode.getText().toString().trim();
        String entrustNum = mEdtEntrustNum.getText().toString().trim();
        if(TextUtils.isEmpty(stockCode) || stockCode.length() != 5){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.hk_vote0));
            return;
        }
//        if(mDataLinkBean == null){
//            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.error_hint16));
//            return;
//        }
        if(TextUtils.isEmpty(mBusinessType)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_company_action2));
            return;
        }
        if(TextUtils.isEmpty(mSubType)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_company_action3));
            return;
        }
        if(TextUtils.isEmpty(actionCode)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_company_action1));
            return;
        }
        if(TextUtils.isEmpty(entrustNum)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_company_action0));
            return;
        }
        mServices.requestActionCommit(actionCode,mBusinessType,stockCode,entrustNum,mSubType);
    }
}

class HKCompanyActionController extends AbsBaseController implements View.OnClickListener{

    private HKCompanyActionFragment mFragment;

    public HKCompanyActionController(HKCompanyActionFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i){
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_hk_action_type1){
            mFragment.onClickBusinessType();
        } else if(v.getId() == R.id.tv_hk_action_type2){
            mFragment.onClickSubType();
        } else if(v.getId() == R.id.btn_hk_action_commit){
            mFragment.onClickCommit();
        }
    }
}