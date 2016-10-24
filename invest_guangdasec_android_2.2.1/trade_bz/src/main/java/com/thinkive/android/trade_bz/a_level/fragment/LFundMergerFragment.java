package com.thinkive.android.trade_bz.a_level.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_level.bean.LFundDivideOrMergerBean;
import com.thinkive.android.trade_bz.a_level.bll.LFundMergerServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.HashSet;

/**
 *   分级基金合并
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/27
 * */
public class LFundMergerFragment extends AbsBaseFragment {
    private AppCompatActivity mActivity;
    private LFundMergerController mController;
    private LFundMergerServicesImpl mServices;
    /**
     * 框架内，自绘键盘管理器
     */
    private KeyboardManager mKeyboardManager;
    /**
     * 基金代码
     */
    private EditText mEdtMergerCode;
    /**
     *基金名称
     */
    private TextView mTvMergerName;
    /**
     * 基金可合并数量
     */
    private TextView mTvMergerNum;
    /**
     *合并数量
     */
    private EditText mEdtMergerNum;
    /**
     *合并
     */
    private Button mBtnMerger;
    /**
     * 基金账户
     */
    private String mStockAccount;
    /**
     * 交易市场类别
     */
    private String mExchangeType;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level_fund_merger, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mEdtMergerCode=(EditText)view.findViewById(R.id.edt_level_merger_code);
        mEdtMergerCode.addTextChangedListener(mTextWatcher);
        mTvMergerName=(TextView)view.findViewById(R.id.tv_level_merger_name);
        mTvMergerNum=(TextView)view.findViewById(R.id.tv_level_merger_num);
        mEdtMergerNum=(EditText)view.findViewById(R.id.edt_level_merger);
        mBtnMerger=(Button)view.findViewById(R.id.btn_level_merger);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnMerger, mController);
    }

    @Override
    protected void initData() {
        mActivity=(AppCompatActivity)getActivity();
        mController=new LFundMergerController(this);
        mServices=new LFundMergerServicesImpl(this);
    }

    @Override
    protected void initViews() {
        //添加自绘键盘
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtMergerCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
    }

    @Override
    protected void setTheme() {

    }
    /**
     * 监听基金代码的输入情况
     */
    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() == 6) {
                //发送基金详情数据请求
                mServices.requestFundMaxMerger(mEdtMergerCode.getText().toString());
            }else{
                HashSet<String> ignoreFlagSet = new HashSet<String>();
                ignoreFlagSet.add("mergerCode");
                ignoreFlagSet.add("keyboard");
                clearAllData(ignoreFlagSet);
            }
        }
    };

    /**
     * 当前基金代码输入错误
     */
    public void onGetFailFundMessage(){
        mTvMergerName.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvMergerNum.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mStockAccount=null;
        mExchangeType=null;
    }
    /**
     * 获得当前基金合并最大可合并数
     */
    public void onGetFundMaxMergerNum(LFundDivideOrMergerBean num){
        if(num != null){
            mStockAccount=num.getStock_account();
            mExchangeType=num.getExchange_type();
            mTvMergerName.setText(num.getFund_name());
            mTvMergerNum.setText(num.getFund_max_amount());
        }else{
            onGetFailFundMessage();
        }
    }

    /**
     * 获得合并结果
     * @param result
     */
    public void onGetFundMergerResult(LFundDivideOrMergerBean result){
        mServices.requestFundMaxMerger(mEdtMergerCode.getText().toString());
        ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_fund_error1) + result.getEntrust_no());
        HashSet<String> ignoreFlagSet = new HashSet<String>();
        ignoreFlagSet.add("keyboard");
        clearAllData(ignoreFlagSet);
    }
    /**
     * 清除所有数据，ignoreFlagSet是要忽略，不进行清除的元素的标志集合。传null时，清除全部
     */
    private void clearAllData(HashSet<String> ignoreFlagSet){
        mStockAccount = null;
        mExchangeType = null;
        if(ignoreFlagSet !=null && !ignoreFlagSet.contains("mergerCode")){
            mEdtMergerCode.setText("");
        }
        mTvMergerName.setText("");
        mTvMergerNum.setText("");
        mEdtMergerNum.setText("");
        if(ignoreFlagSet !=null && !ignoreFlagSet.contains("keyboard")){
            mKeyboardManager.dismiss();
            TradeUtils.hideSystemKeyBoard(mActivity);
        }
    }

    /**
     * 点击基金合并按钮所执行的操作
     */
    public void onClickMerger(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(TextUtils.isEmpty(mEdtMergerCode.getText().toString()) || mEdtMergerCode.getText().toString().length() != 6){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_error3));
        }else{
            if(TextUtils.isEmpty(mEdtMergerNum.getText().toString())){
                ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_fund_error4));
            }else{
                mServices.requestFundMerger(mEdtMergerCode.getText().toString(),mStockAccount,mEdtMergerNum.getText().toString(),mExchangeType);
            }
        }
    }
}

class LFundMergerController extends AbsBaseController implements View.OnClickListener {

    private LFundMergerFragment mFragment;

    public LFundMergerController(LFundMergerFragment mFragment) {
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
        if (resId == R.id.btn_level_merger) {
            mFragment.onClickMerger();
        }
    }
}




