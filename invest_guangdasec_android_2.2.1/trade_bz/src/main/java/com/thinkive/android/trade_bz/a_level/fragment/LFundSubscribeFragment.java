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
import com.thinkive.android.trade_bz.a_level.bll.LFundSubscribeServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 场外基金交易--申购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/6
 */
public class LFundSubscribeFragment extends AbsBaseFragment {
    /**
     * 基金交易的Activity
     */
    private AppCompatActivity mActivity;
    /**
     * 业务类
     */
    private LFundSubscribeServicesImpl mServices;
    /**
     * 控制器
     */
    private LFundSubscribeController mController;
    /**
     * 输入基金代码
     */
    private EditText mEdtFundCode;
    /**
     * 基金名称
     */
    private TextView mTvFundName;
    /**
     * 基金现价
     */
    private TextView mTvFundNowPrice;
    /**
     * 申购上限
     */
    private TextView tvLevelFundSubscriptionUpLimit;
    /**
     * 全部按钮
     */
    private TextView tvLevelFundSubscriptionAllButton;
    /**
     * 输入基金申购金额
     */
    private EditText mEdtFundSubscribeMoney;
    /**
     * 输入申购数量
     */
    private EditText mEdtSubscribeNum;
    /**
     * 显示最多申购金额
     */
    private TextView mTvFoundMaxMoney;
    /**
     * 申购按钮
     */
    private Button mBtClickSubscribe;
    /**
     * 申购金额
     */
    private String mFundBlance;
    /**
     * 框架内，自绘键盘管理器
     */
    private KeyboardManager mKeyboardManager;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level_fund_subscribe, null);
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
        mEdtFundCode = (EditText) view.findViewById(R.id.edt_fund_code);
        mEdtFundCode.addTextChangedListener(mTextWatcher);
        mTvFundName = (TextView) view.findViewById(R.id.tv_fund_name);
        mTvFundNowPrice = (TextView) view.findViewById(R.id.tv_fund_net_value);
        tvLevelFundSubscriptionUpLimit = (TextView) view.findViewById(R.id.tv_level_fund_subscription_up_limit);
        tvLevelFundSubscriptionAllButton = (TextView) view.findViewById(R.id.tv_level_fund_subscription_all_button);
        mEdtFundSubscribeMoney = (EditText) view.findViewById(R.id.edt_fund_subscribe_money);
        mTvFoundMaxMoney = (TextView) view.findViewById(R.id.tv_fund_max_money);
        mBtClickSubscribe = (Button) view.findViewById(R.id.btn_fund_subtricbue);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtClickSubscribe, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK,tvLevelFundSubscriptionAllButton,mController);
    }

    @Override
    protected void initData() {
        mActivity = (AppCompatActivity) getActivity();
        mServices = new LFundSubscribeServicesImpl(this);
        mController = new LFundSubscribeController(this);

    }

    @Override
    protected void initViews() {
        //发送当前可用金额请求
        mServices.requestCurrentMoney();
        //添加自绘键盘
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtFundCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到当前可用金额最多可申购数量
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
        tvLevelFundSubscriptionUpLimit.setText(bean.getStock_max_amount());
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
     * 得到当前基金申购返回数据
     */
    public void getFundSubscribe(String resultStr) {
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
        mEdtSubscribeNum.setText("");
        mEdtFundSubscribeMoney.setText("");
        mKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }


    /**
     * 点击按钮所执行的操作
     */
    public void clickBtnSubtricbe(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        mStockCode=mEdtFundCode.getText().toString();
        mFundBlance = mEdtFundSubscribeMoney.getText().toString();
        if( !TextUtils.isEmpty(mStockCode) && mStockCode.length() == 6 ){
            if(TextUtils.isEmpty(mFundBlance)){
                ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_error1));
            }else{
                mServices.requestFundSubscribe(mExchangeType,mStockAccount,mStockCode,mFundBlance);
            }
        }else{
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_error3));
        }
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
     * 点击全部按钮
     */
    public void clickBtnAll(){
        mEdtFundSubscribeMoney.setText(tvLevelFundSubscriptionAllButton.getText());
    }
}

/**
 * 控制类
 */
class LFundSubscribeController extends AbsBaseController implements View.OnClickListener {

    private LFundSubscribeFragment mFragment;

    public LFundSubscribeController(LFundSubscribeFragment fragment) {
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
        if (resId == R.id.btn_fund_subtricbue) {
            mFragment.clickBtnSubtricbe();
        }else if(resId == R.id.tv_level_fund_subscription_all_button){
            //点击全部按钮
            mFragment.clickBtnAll();
        }
    }
}
