package com.thinkive.android.trade_bz.a_level.fragment;

import android.os.Bundle;
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
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockLinkageBean;
import com.thinkive.android.trade_bz.a_level.bll.LFundSubscriptionServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 场外基金交易--认购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/6
 */
public class LFundSubscriptionFragment extends AbsBaseFragment {
    /**
     * 基金交易的Activity
     */
    private AppCompatActivity mActivity;
    /**
     * 业务类
     */
    private LFundSubscriptionServicesImpl mServices;
    /**
     * 控制类
     */
    private LFundSbscriptionViewController mController;
    /**
     * 框架内，自绘键盘管理器
     */
    private KeyboardManager mKeyboardManager;
    /**
     * 基金代码
     */
    private EditText mEdtFundCode;
    /**
     * 当前价格
     */
    private TextView mTvFundNowPrice;
    /**
     * 基金名称
     */
    private TextView mTvFundName;
    /**
     * 认购数量
     */
    private EditText mEdtSubscriptionNum;
    /**
     * 输入基金认购金额
     */
    private EditText mEdtFundSubscribeMoney;
    /**
     * 显示最多认购金额
     */
    private TextView mTvFoundMaxMoney;
    /**
     * 认购按钮
     */
    private Button mBtClickSubscribe;
    /**
     * 证券账户
     */
    private String mStockAccount;
    /**
     * 市场交易类别
     */
    private String mExchangeType;
    /**
     * 股票代码
     */
    private String mStockCode;
    /**
     * 认购数量
     */
    private String mSubscribeNum;
    /**
     * 认购金额
     */
    private String mFundBlance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level_fund_subtion, null);
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
        mEdtFundCode = (EditText) view.findViewById(R.id.edt_level_fund_code);
        mEdtFundCode.addTextChangedListener(mTextWatcher);
        mTvFundNowPrice = (TextView) view.findViewById(R.id.tv_fund_now_price);
        mTvFundName = (TextView) view.findViewById(R.id.tv_level_name);
        mEdtSubscriptionNum = (EditText) view.findViewById(R.id.edt_fund_subtion_num);
        mEdtFundSubscribeMoney = (EditText) view.findViewById(R.id.edt_fund_subtion_money);
        mTvFoundMaxMoney = (TextView) view.findViewById(R.id.tv_fund_maxmoney);
        mBtClickSubscribe = (Button) view.findViewById(R.id.btn_fund_subtion);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtClickSubscribe, mController);
    }

    @Override
    protected void initData() {
        mActivity = (AppCompatActivity) getActivity();
        mServices = new LFundSubscriptionServicesImpl(this);
        mController = new LFundSbscriptionViewController(this);
    }

    @Override
    protected void initViews() {
        //查询当前资金账户信息
        mServices.requestCurrentMoney();
        //添加自绘键盘
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtFundCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
        setTheme();
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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() == 6) {
                //发送基金详情数据请求
                mServices.requestFundMessage(mEdtFundCode.getText().toString());
            }
        }
    };

    /**
     * 得到当前可用金额最多可认购数量
     */
    public void getMaxSubscribe(MoneySelectBean bean) {
        if( !TextUtils.isEmpty(bean.getEnable_balance())){
            mTvFoundMaxMoney.setText(TradeUtils.formatDouble3(Double.parseDouble(bean.getEnable_balance())));
        }
    }
    /**
     * 得到当前基金的正确详情信息
     */
    public void getFundMessage(StockLinkageBean bean) {
        mTvFundName.setText(bean.getStock_name());
        mTvFundNowPrice.setText(bean.getPrice());
        mStockAccount=bean.getStock_account();
        mExchangeType=bean.getExchange_type();
    }
    /**
     * 当前基金代码输入错误
     */
    public void getFundMessage(){
        mTvFundName.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvFundNowPrice.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mStockAccount=null;
        mExchangeType=null;
    }
    /**
     * 得到当前基金认购返回数据
     */
    public void getFundSubscription(String resultStr) {
        ToastUtils.toast(mActivity, resultStr);
        mServices.requestCurrentMoney();
        clearAllData();
    }

    /**
     * 清除所有数据
     */
    private void clearAllData(){
        mEdtFundCode.setText("");
        mTvFundName.setText("");
        mTvFundNowPrice.setText("");
        mEdtSubscriptionNum.setText("");
        mEdtFundSubscribeMoney.setText("");
        mKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 点击认购按钮
     */
    public void clickBtnSubscription() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        mStockCode = mEdtFundCode.getText().toString();
        mSubscribeNum = mEdtSubscriptionNum.getText().toString();
        mFundBlance = mEdtFundSubscribeMoney.getText().toString();
        if (!TextUtils.isEmpty(mStockCode) && mStockCode.length() == 6) {
            if (TextUtils.isEmpty(mSubscribeNum)) {
                ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_fund_error9));
            } else {
                if (TextUtils.isEmpty(mFundBlance)) {
                    ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_subtion_money_hint));
                } else {
                    mServices.requestFundSubscription(mExchangeType, mStockAccount, mStockCode, mFundBlance, mSubscribeNum);
                }
            }
        } else {
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_error3));
        }
    }
}

class LFundSbscriptionViewController extends AbsBaseController implements View.OnClickListener {

    private LFundSubscriptionFragment mFragment;

    public LFundSbscriptionViewController(LFundSubscriptionFragment fragment) {
        mFragment = fragment;
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
        if (resId == R.id.btn_fund_subtion) { //按钮
            mFragment.clickBtnSubscription();
        }
    }
}
