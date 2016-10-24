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
import com.thinkive.android.trade_bz.a_stock.bean.StockLinkageBean;
import com.thinkive.android.trade_bz.a_level.bll.LFundRansomServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 场外基金交易--赎回
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/6
 */
public class LFundRansomFragment extends AbsBaseFragment {
    /**
     * 基金交易的Activity
     */
    private AppCompatActivity mActivity;
    /**
     * 业务类
     */
    private LFundRansomServicesImpl mServices;
    /**
     * 该类的控制器
     */
    private LevelFundRransomViewController mController;
    /**
     * 输入基金代码
     */
    private EditText mEdtFundCode;
    /**
     * 基金净值
     */
    private TextView mTvFundNowPrice;
    /**
     * 输入赎回数量
     */
    private EditText mEtRansomCount;
    /**
     * 该基金可用份数
     */
    private TextView mTvCanUseNum;
    /**
     * 赎回按钮
     */
    private Button mBtClickRansom;
    /**
     * 基金名称
     */
    private TextView mTvFundName;
    /**
     * 赎回价格
     */
    private EditText mEdtRansomPrice;
    /**
     * 赎回金额
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
    /**
     * 赎回数量
     */
    private String mSubscribeNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level_fund_ransom, null);
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
        mEdtFundCode = (EditText) view.findViewById(R.id.edt_ransom_code);
        mEdtFundCode.addTextChangedListener(mTextWatcher);
        mTvFundName = (TextView) view.findViewById(R.id.tv_fund_ransom_name);
        mTvFundNowPrice = (TextView) view.findViewById(R.id.tv_fund_ransom_now_price);
        mEtRansomCount = (EditText) view.findViewById(R.id.edt_fund_ransom_count);
        mTvCanUseNum = (TextView) view.findViewById(R.id.tv_fund_ransom_max);
        mBtClickRansom = (Button) view.findViewById(R.id.btn_fund_ransom);
        mEdtRansomPrice = (EditText) view.findViewById(R.id.edt_fund_ransom_price);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtClickRansom, mController);
    }

    @Override
    protected void initData() {
        mActivity = (AppCompatActivity) getActivity();
        mServices = new LFundRansomServicesImpl(this);
        mController = new LevelFundRransomViewController(this);


    }

    @Override
    protected void initViews() {
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
     * 得到当前基金的正确详情信息
     */
    public void getFundMessage(StockLinkageBean bean) {
        mTvFundName.setText(bean.getStock_name());
        mTvFundNowPrice.setText(bean.getPrice());
        mTvCanUseNum.setText(bean.getStock_max_amount());
        mStockAccount=bean.getStock_account();
        mExchangeType=bean.getExchange_type();
    }
    /**
     * 当前基金代码输入错误
     */
    public void getFundMessage(){
        mTvFundName.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvFundNowPrice.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvCanUseNum.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mStockAccount=null;
        mExchangeType=null;
    }
    /**
     * 得到当前基金赎回返回结果
     */
    public void getFundRansonResult(String resultStr) {
        mServices.requestFundMessage(mEdtFundCode.getText().toString());
        ToastUtils.toast(mActivity, resultStr);
        clearAllData();
    }

    /**
     * 清除所有数据
     */
    private void clearAllData(){
        mEdtFundCode.setText("");
        mTvFundName.setText("");
        mTvFundNowPrice.setText("");
        mEdtRansomPrice.setText("");
        mEtRansomCount.setText("");
        mKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }


    /**
     * 点击按钮所执行的操作
     */
    public void clickBtnRansom() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        mStockCode = mEdtFundCode.getText().toString();
        mSubscribeNum = mEtRansomCount.getText().toString();
        mFundBlance = mEdtRansomPrice.getText().toString();
        if (!TextUtils.isEmpty(mStockCode) && mStockCode.length() == 6) {
            if (TextUtils.isEmpty(mSubscribeNum)) {
                ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_ransom_hint));
            } else {
                if (TextUtils.isEmpty(mFundBlance)) {
                    ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_ramson_text4));
                } else {
                    mServices.requestFundRansom(mExchangeType, mStockAccount, mStockCode, mFundBlance, mSubscribeNum);
                }
            }
        } else {
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_error3));
        }
    }
}

class LevelFundRransomViewController extends AbsBaseController implements
        View.OnClickListener {

    private LFundRansomFragment mFragment;

    public LevelFundRransomViewController(LFundRansomFragment fragment) {
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
        if (resId == R.id.btn_fund_ransom) { //按钮
            mFragment.clickBtnRansom();
        }
    }
}
