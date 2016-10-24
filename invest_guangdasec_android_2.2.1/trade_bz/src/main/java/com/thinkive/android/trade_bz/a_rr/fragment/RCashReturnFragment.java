package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RChooseContractActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCashReturnServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 融资融券--现金还款(直接还款)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/20
 */

public class RCashReturnFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private RCashReturnServiceImpl mServices;
    private RCashReturnController mController;
    private Resources mResources;
    private TextView mTvCanUseCash;
    private TextView mTvOtherCash;
    private TextView mTvReturnAllClick;
    private EditText mEdtInputNum;
    private Button mBtnCommit;
    private LinearLayout mLlChooseContract;
    private TextView mTvChooseContract;
    private RadioGroup mRadioGroup;
    private RadioButton mRbCashReturn;
    private RadioButton mRbSelectContract;
    private LinearLayout mLinLoading;
    /**
     * 合约结果集
     */
    private ArrayList<RChooseContractBean> mContractDataList;
    private String mAllNeedReturnMoney = "";
    private String mSelectNeedReturnMoney = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_cash_return, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mLinLoading = (LinearLayout)view.findViewById(R.id.lin_loading_set);
        mLlChooseContract = (LinearLayout) view.findViewById(R.id.ll_r_choose_contract);
        mTvChooseContract = (TextView) view.findViewById(R.id.tv_r_choose_contract);
        mTvCanUseCash = (TextView) view.findViewById(R.id.tv_r_cash_can_use);
        mTvOtherCash = (TextView) view.findViewById(R.id.tv_r_cash_other);
        mTvReturnAllClick = (TextView) view.findViewById(R.id.tv_r_return_all);
        mEdtInputNum = (EditText) view.findViewById(R.id.edt_r_cash_num);
        mBtnCommit = (Button) view.findViewById(R.id.btn_r_cash_commit);
        mRadioGroup = (RadioGroup)view.findViewById(R.id.rg_cash_return);
        mRbCashReturn = (RadioButton)view.findViewById(R.id.rb_cash_return);
        mRbSelectContract = (RadioButton)view.findViewById(R.id.rb_cash_return_contract);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnCommit, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvReturnAllClick, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvChooseContract, mController);
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRadioGroup, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mResources = mActivity.getResources();
        mServices = new RCashReturnServiceImpl(this);
        mController = new RCashReturnController(this);
        mServices.requestStockLink();
    }

    @Override
    protected void initViews() {
        mServices.requestChooseContract("");//请求合约
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 点击radioButton
     * @param checkId
     */
    public void onCheckRadioButton(int checkId){
        //*******************直接还款*****************
        if(checkId == R.id.rb_cash_return){
            mLlChooseContract.setVisibility(View.GONE);
            mServices.setIsContractEntrust(false);
            mRbCashReturn.setTextColor(mResources.getColor(R.color.trade_color_white));
            mRbSelectContract.setTextColor(mResources.getColor(R.color.trade_color1));
            mTvOtherCash.setText(mAllNeedReturnMoney);
            // ************************指定合约*********************************************
        }else if(checkId == R.id.rb_cash_return_contract) {
            if(mContractDataList != null && mContractDataList.size() > 0){
                onClickTvChooseContract();//选中指定合约，默认跳转至合约选择页面
            }else{
                mLlChooseContract.setVisibility(View.VISIBLE);
            }
            mServices.setIsContractEntrust(true);
            mRbCashReturn.setTextColor(mResources.getColor(R.color.trade_color1));
            mRbSelectContract.setTextColor(mResources.getColor(R.color.trade_color_white));
            mTvOtherCash.setText(mSelectNeedReturnMoney);
        }
    }

    /**
     * 获得可用金额和需还金额
     */
    public void onGetCanUseMoney(RStockToStockLinkBean data) {
        mTvCanUseCash.setText(data.getFin_enrepaid_balance());
        mAllNeedReturnMoney = data.getReal_compact_balance();
        if(!mServices.isIsContractEntrust()){
            mTvOtherCash.setText(mAllNeedReturnMoney);
        }
    }

    /**
     * 得到合约数据集
     * @param dataList
     */
    public void getChooseContractData(ArrayList<RChooseContractBean> dataList) {
        mLinLoading.setVisibility(View.GONE);
        if (dataList == null || dataList.size() == 0) {
            mTvChooseContract.setHint(mResources.getString(R.string.r_choose_no_contract));
            mContractDataList = new ArrayList<RChooseContractBean>();
        } else {
            mTvChooseContract.setHint(mResources.getString(R.string.r_choose_contract_title));
            mContractDataList = dataList;
        }
    }

    /**
     * 点击选择合约
     */
    public void onClickTvChooseContract() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mContractDataList == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.r_choose_contract_loading));
        }else if(mContractDataList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.r_choose_no_contract));
        }else {
            Intent intent = new Intent(getActivity(), RChooseContractActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(RChooseContractFragment.USE_TYPE, RChooseContractFragment.ANY_ONE);
            bundle.putParcelableArrayList(RChooseContractFragment.CONTRACT_DATA_LIST, mContractDataList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 16);
        }
    }
    /**
     * 接收startActivityForResult启动的Activity，在finish后的返回数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLlChooseContract.setVisibility(View.VISIBLE);
        if(data == null) {
            return;
        }
        if(resultCode == RChooseContractFragment.RESULT_CODE_SAVE){
            ArrayList<RChooseContractBean> tempList = new ArrayList<RChooseContractBean>();
            mContractDataList = data.getParcelableArrayListExtra(RChooseContractFragment.RESULT_SAVE);
            if(mContractDataList != null && mContractDataList.size() > 0) {
                for (RChooseContractBean bean : mContractDataList) {
                    boolean isCheck = bean.isChecked();
                    if (isCheck) {
                        tempList.add(bean);
                    }
                }
            }
            if(tempList.size() > 0){
                mTvChooseContract.setText(mActivity.getResources().getString(R.string.contract_not21)+ tempList.size()+mActivity.getResources().getString(R.string.contract_not22));
                mSelectNeedReturnMoney = resultData(tempList);
                mTvOtherCash.setText(mSelectNeedReturnMoney);
            }else{
                mTvChooseContract.setText("");
            }
        }
    }

    private String resultData(ArrayList<RChooseContractBean> dataList){
        double result = 0;
        try {
            for (RChooseContractBean bean : dataList) {
                String money = bean.getReal_compact_balance();
                if (money != null && !TextUtils.isEmpty(money)) {
                    result += Double.parseDouble(money);
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return TradeUtils.formatDouble2(result);
    }

    /**
     * 点击全部按钮
     */
    public void onClickTvAll(){
        mEdtInputNum.setText(mTvOtherCash.getText().toString());
    }


    /**
     * 点击提交按钮
     */
    public void onClickBtnCommit() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String entrustNum = mEdtInputNum.getText().toString().trim();
        if (TextUtils.isEmpty(entrustNum)) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.r_property_list6));
        } else {
            mServices.requrstCommit(entrustNum);
        }
    }
    /**
     * 清空界面的所有数据
     */
    public void clearAllData(){
        mEdtInputNum.setText("");
        mTvCanUseCash.setText("");
        mTvOtherCash.setText("");
        mContractDataList = null;
        mSelectNeedReturnMoney = "";
        mAllNeedReturnMoney = "";
        mTvChooseContract.setText("");
        mTvChooseContract.setHint(mResources.getString(R.string.r_choose_contract_loading));
    }

    public ArrayList<RChooseContractBean> getSelectContractList() {
        return mContractDataList;
    }
}

/**
 * 本类控制器
 */
class RCashReturnController extends AbsBaseController implements
        View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private RCashReturnFragment mFragment;

    public RCashReturnController(RCashReturnFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_CHECK_CHANGE:
                ((RadioGroup)view).setOnCheckedChangeListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.btn_r_cash_commit) {
            mFragment.onClickBtnCommit();
        } else if (resId == R.id.tv_r_choose_contract) {
            mFragment.onClickTvChooseContract();
        }  else if (resId == R.id.tv_r_return_all) {
            mFragment.onClickTvAll();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragment.onCheckRadioButton(checkedId);
    }
}