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
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionMoneyBean;
import com.thinkive.android.trade_bz.a_stock.activity.SelectListActivity;
import com.thinkive.android.trade_bz.a_stock.bean.BankTransferBean;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bll.BankOutServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
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
public class TransferBankOutFragment extends AbsBaseFragment {
    /**
     * 银证转账的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     * 银证转账的控制器
     */
    private BankOutViewController mController;
    /**
     * 转出--选择银行
     */
    private TextView mTvOutSelectBank;
    /**
     * 转出--可转金额
     */
    private TextView mTvOutCanUseMoney;
    /**
     * 转出--输入转账金额
     */
    private EditText mEdOutTransferMoney;
    /**
     * 转出--输入资金密码
     */
    private EditText mEdOutFundPassword;
    /**
     * 转出--输入银行密码
     */
    private EditText mEdOutBankPassword;
    /**
     * 转出--提交转账
     */
    private Button mBtnOutClickCommit;
    /**
     * 整个布局
     */
    private LinearLayout mLinDataLayOut;
    /**
     * 正在加载时的布局
     */
    private LinearLayout mLinLoadingLayOut;
    /**
     * 业务类
     */
    private BankOutServicesImpl mServices;
    /**
     * 转出--资金密码布局
     */
    private LinearLayout mLinOutFundPwd;
    /**
     * 转出--银行密码布局
     */
    private LinearLayout mLinOutBankPwd;
    private KeyboardManager mKeyboardForBankPwdOut;
    private KeyboardManager mKeyboardForFundPwdOut;
    /**
     * 全局资源对象
     */
    private Resources mResources;
    /**
     * 存管银行数据集
     */
    private ArrayList<BankTransferBean> mBankDataList;
    /**
     * 当前选中的存管银行信息
     */
    private BankTransferBean mMsgBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_bank_out_layout, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mKeyboardForBankPwdOut.dismiss();
        mKeyboardForFundPwdOut.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        //是为了避免两个类同时发出同样的请求
        mServices.requestBankList();
    }

    @Override
    protected void findViews(View view) {
        mTvOutSelectBank = (TextView) view.findViewById(R.id.tv_bank_out_select);
        mTvOutCanUseMoney = (TextView) view.findViewById(R.id.tv_bank_out_text);
        mEdOutTransferMoney = (EditText) view.findViewById(R.id.edt_bank_out_money);
        mEdOutFundPassword = (EditText) view.findViewById(R.id.edt_bank_out_pwd2);
        mEdOutBankPassword = (EditText) view.findViewById(R.id.edt_bank_out_pwd1);
        mBtnOutClickCommit = (Button) view.findViewById(R.id.btn_bank_out_commit);
        mLinOutFundPwd = (LinearLayout) view.findViewById(R.id.lin_out_pwd_text2);
        mLinOutBankPwd = (LinearLayout) view.findViewById(R.id.lin_out_pwd_text1);
        mLinDataLayOut = (LinearLayout) view.findViewById(R.id.lin_transfer_out);
        mLinLoadingLayOut = (LinearLayout) view.findViewById(R.id.lin_loading_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mBtnOutClickCommit, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvOutSelectBank, mController);
        mEdOutTransferMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TradeUtils.onLimitAfterPoint(mEdOutTransferMoney, s, 10,5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new BankOutViewController(this);
        mResources = mActivity.getResources();
        Bundle bundle = getArguments();
        String userType = "";
        if(bundle != null){
            userType = bundle.getString("userType");
        }
        mServices = new BankOutServicesImpl(this, userType);
    }

    @Override
    protected void initViews() {
        //添加自绘键盘
        mKeyboardForBankPwdOut = TradeTools.initKeyBoard(mActivity, mEdOutBankPassword, KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        mKeyboardForFundPwdOut = TradeTools.initKeyBoard(mActivity, mEdOutFundPassword, KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        //禁用系统键盘
        TradeUtils.hideSystemKeyBoard(mActivity);
        mServices.requestCanUseMOney();
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
            mTvOutSelectBank.setHint(mResources.getString(R.string.bank_no_text_hint));
        } else {
            mTvOutSelectBank.setHint(mResources.getString(R.string.one_key_hint_select));
            mBankDataList = dataList;
            BankTransferBean bean =  mBankDataList.get(0);
            mMsgBean = bean;
            mTvOutSelectBank.setText(bean.getBank_account() + "  " +
                    bean.getBank_name()+"  "+formateMoneyType(bean.getMoney_type()));
            mServices.requestBankServices(mMsgBean.getBank_code(), "1");
        }
    }

    /**
     * 点击跳转页面选择银行
     */
    public void clickOutSelectBank() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mBankDataList == null || mBankDataList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.bank_no_text_hint));
        }else{
            Intent intent = new Intent(mActivity,SelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mBankDataList);
            intent.putExtra("useType","bank_account");
            startActivityForResult(intent,84);
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
        mTvOutSelectBank.setText(bean.getBank_account() + "  " +
                bean.getBank_name()+"  "+formateMoneyType(bean.getMoney_type()));
        mServices.requestBankServices(mMsgBean.getBank_code(), "1");
    }
    
    /**
     * 得到当前账号的业务信息
     * 根据返回参数判断银行密码框和资金密码框是否显示
     */
    public void getCurrentServices(BankTransferBean data) {
        //银行密码框是否可见
        if(data.getBank_password_flag().equals("1")){
            mLinOutBankPwd.setVisibility(View.VISIBLE);
        }else{
            mLinOutBankPwd.setVisibility(View.GONE);
        }
        //资金密码框是否可见
        if(data.getFund_password_flag().equals("1")){
            mLinOutFundPwd.setVisibility(View.VISIBLE);
        }else{
            mLinOutFundPwd.setVisibility(View.GONE);
        }
    }

    /**
     * 得到当前转账的结果
     * @param data
     */
    public void getTransferResult(BankTransferBean data) {
        ToastUtils.toast(mActivity, mResources.getString(R.string.bank_in_result) + data.getSerial_no());
        mServices.requestCanUseMOney();
        clearAllData();
    }

    /**
     * 清除所有数据
     */
    private void clearAllData(){
        mEdOutTransferMoney.setText("");
        mEdOutBankPassword.setText("");
        mEdOutFundPassword.setText("");
        mKeyboardForBankPwdOut.dismiss();
        mKeyboardForFundPwdOut.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 得到当前可转资金
     */
    public void getCanUseMoney(MoneySelectBean bean) {
        if( !TextUtils.isEmpty(bean.getEnable_balance())){
            mTvOutCanUseMoney.setText(TradeUtils.formatDoubleUnit(Double.parseDouble(bean.getFetch_balance())));
        }
    }
    /**
     * 得到当前可转资金
     * 重载个股期权
     */
    public void getCanUseMoney(OptionMoneyBean bean) {
        if( !TextUtils.isEmpty(bean.getEnable_balance())){
            mTvOutCanUseMoney.setText(TradeUtils.formatDoubleUnit(Double.parseDouble(bean.getEnable_balance())));
        }
    }
    /**
     * 点击转出之提交转账所执行的操作
     */
    public void clickOutCommit() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String balances = mEdOutTransferMoney.getText().toString();
        String fundPwd=mEdOutFundPassword.getText().toString();
        String bankPwd= mEdOutBankPassword.getText().toString();
        //还没选择存管银行
        if(mMsgBean == null){
            ToastUtils.toast(mActivity, mResources.getString(R.string.bank_no_text_error));
            return;
        }
        //是否输入资金密码
        if (TextUtils.isEmpty(mEdOutTransferMoney.getText().toString())) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.bank_money_error));
            return;
        }
        //如果银行密码框显示
        if (mEdOutBankPassword.getVisibility() == View.VISIBLE && mEdOutBankPassword.isShown()) {
            if(TextUtils.isEmpty(bankPwd)){
                ToastUtils.toast(mActivity, mResources.getString(R.string.bank_password_hint));
                return;
            }
        }
        //如果资金密码框显示
        if(mEdOutFundPassword.getVisibility() == View.VISIBLE && mEdOutFundPassword.isShown()){
            if(TextUtils.isEmpty(fundPwd)){
                ToastUtils.toast(mActivity, mResources.getString(R.string.bank_money_pwd_hint));
                return;
            }
        }
        mKeyboardForBankPwdOut.dismiss();
        mKeyboardForFundPwdOut.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
        mServices.requestTransferBank(mMsgBean.getBank_code(), mMsgBean.getMoney_type(),
                "1", balances, fundPwd, bankPwd);
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

class BankOutViewController extends AbsBaseController implements View.OnClickListener{

    private TransferBankOutFragment mFragment;

    public BankOutViewController(TransferBankOutFragment mFragment) {
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
        if (resId == R.id.tv_bank_out_select) { //转出--选择银行
            mFragment.clickOutSelectBank();
        } else if (resId == R.id.btn_bank_out_commit) { //转出--提交转账
            mFragment.clickOutCommit();
        }
    }
}
