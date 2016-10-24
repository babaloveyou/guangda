package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.SelectListActivity;
import com.thinkive.android.trade_bz.a_stock.bean.BankTransferBean;
import com.thinkive.android.trade_bz.a_stock.bll.BankInServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.dialog.BankInputPwdConfirmDialog;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 银证转账转入(融资融券--转账)
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/10
 */
public class TransferBankInFragment extends AbsBaseFragment {
    /**
     * 银证转账的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     * 银证转账的控制器
     */
    private BankInViewController mController;
    /**
     * 转入--选择银行
     */
    private TextView mTvInSelectBank;
    /**
     * 转入--输入转账金额
     */
    private EditText mEdInTransferMoney;
    /**
     * 转入--点击 “点击查询” 查询余额
     */
    private TextView mTvInSelectOtherMoney;
    /**
     * 转入--输入银行密码
     */
    private EditText mEdInBankPassword;
    /**
     * 转入--输入资金密码
     */
    private EditText mEdInfundPassword;
    /**
     * 转入--提交转账
     */
    private Button mBtInClickCommit;
    /**
     * 包揽整个页面的布局
     */
    private LinearLayout mLinDataLayOut;
    /**
     * 正在加载时的布局
     */
    private LinearLayout mLinLoadingLayOut;
    /**
     * 业务类
     */
    private BankInServicesImpl mServices;
    /**
     * 转入--银行密码布局
     */
    private LinearLayout mLinInBankPwd;
    /**
     * 转入--资金密码布局
     */
    private LinearLayout mLinInFundPwd;
    /**
     * 加载余额
     */
    private ProgressBar mProLoaingData;
    /**
     * 查询余额输入密码的对话框
     */
    private BankInputPwdConfirmDialog mDialog;
    private KeyboardManager mKeyboardForBankPwdIn;
    private KeyboardManager mKeyboardForFundPwdIn;
    /**
     * 存管银行列表
     */
    private ArrayList<BankTransferBean> mBankDataList;
    /**
     * 全局资源对象
     */
    private Resources mResources;
    /**
     * 当前选中的存管银行信息
     */
    private BankTransferBean mMsgBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_bank_in_layout, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        if(mDialog == null){
            mDialog = new BankInputPwdConfirmDialog(mActivity,mServices,mActivity);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mDialog != null){
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mKeyboardForBankPwdIn.dismiss();
        mKeyboardForFundPwdIn.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mTvInSelectBank = (TextView) view.findViewById(R.id.tv_bank_in_select);
        mEdInTransferMoney = (EditText) view.findViewById(R.id.edt_input_momey);
        mTvInSelectOtherMoney = (TextView) view.findViewById(R.id.tv_bank_click_select);
        mEdInBankPassword = (EditText) view.findViewById(R.id.edt_bank_in_pwd);
        mEdInfundPassword = (EditText) view.findViewById(R.id.edt_bank_in_pwd2);
        mBtInClickCommit = (Button) view.findViewById(R.id.btn_in_commit_transfer);
        mLinInBankPwd = (LinearLayout) view.findViewById(R.id.lin_in_pwd_text1);
        mLinInFundPwd = (LinearLayout) view.findViewById(R.id.lin_in_pwd_text2);
        mProLoaingData = (ProgressBar) view.findViewById(R.id.pro_loaing_money);
        mLinDataLayOut = (LinearLayout) view.findViewById(R.id.lin_transfer_in);
        mLinLoadingLayOut = (LinearLayout) view.findViewById(R.id.lin_loading_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mTvInSelectOtherMoney, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvInSelectBank, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtInClickCommit, mController);
        mEdInTransferMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TradeUtils.onLimitAfterPoint(mEdInTransferMoney, s, 10,5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new BankInViewController(this);
        mResources = mActivity.getResources();
        Bundle bundle = getArguments();
        String userType = "";
        if(bundle != null){
            userType = bundle.getString("userType");
        }
        mServices = new BankInServicesImpl(this, userType);
        mDialog = new BankInputPwdConfirmDialog(mActivity,mServices,mActivity);
    }

    @Override
    protected void initViews() {
        //添加自绘键盘
        mKeyboardForBankPwdIn= TradeTools.initKeyBoard(mActivity, mEdInBankPassword, KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        mKeyboardForFundPwdIn= TradeTools.initKeyBoard(mActivity, mEdInfundPassword, KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        //禁用系统键盘
        TradeUtils.hideSystemKeyBoard(mActivity);
        //请求账户数据列表
        mServices.requestBankList();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }
    /**
     * 得到银行存管数据列表
     */
    public void getBankSelectData(ArrayList<BankTransferBean> dataList) {
        //不管有没有请求到数据，都让布局展示出来
        mLinDataLayOut.setVisibility(View.VISIBLE);
        mLinLoadingLayOut.setVisibility(View.GONE);
        //若没有数据
        if (dataList == null || dataList.size()==0){
            mTvInSelectBank.setHint(mResources.getString(R.string.bank_no_text_hint));
        } else {
            mTvInSelectBank.setHint(mResources.getString(R.string.one_key_hint_select));
            mBankDataList = dataList;
            BankTransferBean bean =  mBankDataList.get(0);
            mMsgBean = bean;
            mTvInSelectBank.setText(bean.getBank_account() + "  " +
                    bean.getBank_name()+"  "+formateMoneyType(bean.getMoney_type()));
            mServices.requestBankServices(mMsgBean.getBank_code(), "0");
        }
    }

    /**
     * 点击跳转页面选择银行
     */
    public void clickInSelectBank() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mBankDataList == null || mBankDataList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.bank_no_text_hint));
        }else{
            Intent intent = new Intent(mActivity,SelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mBankDataList);
            intent.putExtra("useType","bank_account");
            startActivityForResult(intent,85);
        }
    }

    /**
     * 选择账号完成时执行
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        BankTransferBean bean =  mBankDataList.get(position);
        mMsgBean = bean;
        mTvInSelectBank.setText(bean.getBank_account() + "  " +
                bean.getBank_name()+"  "+formateMoneyType(bean.getMoney_type()));
        mServices.requestBankServices(mMsgBean.getBank_code(), "0");
    }

    /**
     * 得到当前银行账户的资金余额
     */
    public void getCurrentAccountMoney(BankTransferBean data) {
        mTvInSelectOtherMoney.setVisibility(View.VISIBLE);
        mProLoaingData.setVisibility(View.GONE);
        mTvInSelectOtherMoney.setText(data.getFundeffect());
        mTvInSelectOtherMoney.setClickable(false);
    }
    /**
     * 得到当前银行账户的资金余额
     */
    public void getCurrentAccountMoney() {
        mTvInSelectOtherMoney.setVisibility(View.VISIBLE);
        mProLoaingData.setVisibility(View.GONE);
    }

    /**
     * 得到当前账号的业务信息
     * 根据返回参数判断银行密码框和资金密码框是否显示
     */
    public void getCurrentServices(BankTransferBean data) {
        //银行密码框是否可见
        if(data.getBank_password_flag().equals("1")){
            mLinInBankPwd.setVisibility(View.VISIBLE);
        }else{
            mLinInBankPwd.setVisibility(View.GONE);
        }
        //资金密码框是否可见
        if(data.getFund_password_flag().equals("1")){
            mLinInFundPwd.setVisibility(View.VISIBLE);
        }else{
            mLinInFundPwd.setVisibility(View.GONE);
        }
    }

    /**
     * 得到当前转账的结果
     * @param data
     */
    public void getTransferResult(BankTransferBean data) {
        ToastUtils.toast(mActivity, mResources.getString(R.string.bank_in_result) + data.getSerial_no());
        clearAllData();
    }

    /**
     * 清除所有数据
     */
    private void clearAllData(){
        mEdInTransferMoney.setText("");
        mEdInBankPassword.setText("");
        mEdInfundPassword.setText("");
        mTvInSelectOtherMoney.setText("");
        mTvInSelectOtherMoney.setClickable(true);
        mKeyboardForBankPwdIn.dismiss();
        mKeyboardForFundPwdIn.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }
    /**
     * 点击转入之 “点击查询”字样所执行的操作
     * 查询余额
     */
    public void clickInSelectMoney() {
        if (TradeUtils.isFastClick()) {
            return;
        }
//        if(mMsgBean == null){
//            ToastUtils.toast(mActivity, mResources.getString(R.string.bank_no_text_error));
//        }else {
//            mDialog.setRequestData(mMsgBean.getBank_code(), mMsgBean.getMoney_type());
//            mDialog.show();
//        }

        //请求业务类是否需要密码
        if(mMsgBean == null){
            ToastUtils.toast(mActivity, mResources.getString(R.string.bank_no_text_error));
        }else {
            mServices.requestBankServices(mMsgBean.getBank_code(), "2");
        }
    }

    /**
     * 查询余额时是否需要密码
     * @param data
     */
    public void onGetIsNeedPwd(BankTransferBean data){
        if(mDialog == null || mMsgBean == null)
            return;
        if(data.getBank_password_flag().equals("1")){//需要银行密码
            mDialog.setRequestData(mMsgBean.getBank_code(), mMsgBean.getMoney_type());
            mDialog.show();
        }else{
            mServices.requestOtherMoney(mMsgBean.getBank_code(), mMsgBean.getMoney_type(), "");
        }
    }

    /**
     * 正在查询余额时的界面展示
     */
    public void setOnRequestStatus(){
        mTvInSelectOtherMoney.setVisibility(View.GONE);
        mProLoaingData.setVisibility(View.VISIBLE);
    }

    /**
     * 点击转入之提交转账所执行的操作
     */
    public void clickInCommit() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String balances = mEdInTransferMoney.getText().toString();
        String fundPwd = mEdInfundPassword.getText().toString();
        String bankPwd = mEdInBankPassword.getText().toString();
        //还没选择存管银行
        if(mMsgBean == null){
            ToastUtils.toast(mActivity, mResources.getString(R.string.bank_no_text_error));
            return;
        }
        //如果没有输入金额
        if (TextUtils.isEmpty(balances)) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.bank_money_error));
            return;
        }
        //如果银行密码框显示
        if (mEdInBankPassword.getVisibility() == View.VISIBLE && mEdInBankPassword.isShown()) {
            if (TextUtils.isEmpty(bankPwd)) {
                ToastUtils.toast(mActivity, mResources.getString(R.string.bank_password_hint));
                return;
            }
        }
        //如果资金密码框显示
        if (mEdInfundPassword.getVisibility() == View.VISIBLE && mEdInfundPassword.isShown()) {
            if (TextUtils.isEmpty(fundPwd)) {
                ToastUtils.toast(mActivity, mResources.getString(R.string.bank_money_pwd_hint));
                return;
            }
        }
        mKeyboardForBankPwdIn.dismiss();
        mKeyboardForFundPwdIn.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
        mServices.requestTransferBank(mMsgBean.getBank_code(), mMsgBean.getMoney_type(),
                "0", balances, fundPwd, bankPwd);
    }
    /**
     * 处理货币类型
     * @param str
     * @return
     */
    private String formateMoneyType(String str) {
        if (str.contains("0")) {
            str = mResources.getString(R.string.bank_money_one);
        } else if (str.contains("1")) {
            str = mResources.getString(R.string.bank_money_two);
        } else if (str.contains("2")) {
            str = mResources.getString(R.string.bank_money_three);
        }
        return str;
    }
}

class BankInViewController extends AbsBaseController implements View.OnClickListener{

    private TransferBankInFragment mFragment;

    public BankInViewController(TransferBankInFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_bank_in_select) { //转入--选择银行
            mFragment.clickInSelectBank();
        } else if (resId == R.id.tv_bank_click_select) { //转入--查询余额
            mFragment.clickInSelectMoney();
        } else if (resId == R.id.btn_in_commit_transfer) { //转入--提交转账
            mFragment.clickInCommit();
        }
    }
}
