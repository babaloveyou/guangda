package com.thinkive.android.trade_bz.a_out.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.activity.FundScribeActivity;
import com.thinkive.android.trade_bz.a_out.bean.FundInfoBean;
import com.thinkive.android.trade_bz.a_out.bll.FundSubscribeServicesImpl;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 基金交易----申购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/6
 */
public class FundSubscribeFragment extends AbsBaseFragment {
    /**
     * 基金交易的Activity
     */
    private FundScribeActivity mActivity;
    /**
     * 业务类
     */
    private FundSubscribeServicesImpl mServices;
    /**
     * 控制器
     */
    private FundSubscribeController mController;
    /**
     * 输入基金代码
     */
    private EditText mEdtFundCode;
    /**
     * 基金名称
     */
    private TextView mTvFundName;
    /**
     * 基金净值
     */
    private TextView mTvFundNetValue;
    /**
     * 输入基金申购金额
     */
    private EditText mEdtFundSubscribeMoney;
    /**
     * 显示最多申购金额
     */
    private TextView mTvFoundMaxMoney;
    /**
     * 上限
     */
    public TextView mTvUpLimit;
    /**
     * 点击全部
     */
    private TextView mTvUpLimitClick;
    /**
     * 申购按钮
     */
    private Button mBtClickSubscribe;
    /**
     * 框架内，自绘键盘管理器
     */
    private KeyboardManager mKeyboardManager;
    /**
     * 基金详情
     */
    private FundInfoBean mFundInfoBean;
    /**
     *  可用资金
     */
    private MoneySelectBean moneySelectBean;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_subscribe_layout, null);
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
        mTvFundNetValue = (TextView) view.findViewById(R.id.tv_fund_net_value);
        mTvUpLimit = (TextView) view.findViewById(R.id.tv_fund_sub_max);
        mTvUpLimitClick = (TextView) view.findViewById(R.id.tv_fund_click_sub_max);
        mEdtFundSubscribeMoney = (EditText) view.findViewById(R.id.edt_fund_subscribe_money);
        mTvFoundMaxMoney = (TextView) view.findViewById(R.id.tv_fund_max_money);
        mBtClickSubscribe = (Button) view.findViewById(R.id.btn_fund_subtricbue);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtClickSubscribe, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvUpLimitClick, mController);
    }

    @Override
    protected void initData() {
        mActivity = (FundScribeActivity) getActivity();
        mServices = new FundSubscribeServicesImpl(this);
        mController = new FundSubscribeController(this);
    }

    @Override
    protected void initViews() {
        //发送当前可用金额请求
        mServices.requestCurrentMoney();
        //添加自绘键盘
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtFundCode,KeyboardManager.KEYBOARD_TYPE_STOCK);
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 得到当前可用金额最多可申购数量
     */
    public void getMaxSubscribe(MoneySelectBean bean) {
        this.moneySelectBean = moneySelectBean;
        if( !TextUtils.isEmpty(bean.getEnable_balance())){
            //设置可用资金
            mTvFoundMaxMoney.setText(TradeUtils.formatDouble3(Double.parseDouble(bean.getEnable_balance())));
            //设置申购上限
            mTvUpLimit.setText(TradeUtils.formatDouble3(Double.parseDouble(bean.getEnable_balance())));
        }
    }

    /**
     * 得到当前基金的正确详情信息
     */
    public void getFundMessage(FundInfoBean bean) {
        mFundInfoBean = bean;
        mTvFundName.setText(bean.getFund_name());
        mTvFundNetValue.setText(bean.getNav());
        //todo:缺字段
//        mTvUpLimit.setText("?????");
    }

    /**
     * 当前基金代码输入错误
     */
    public void getFundMessage() {
        mTvFundName.setText("");
        mTvFundNetValue.setText("");
//        mTvUpLimit.setText("");
        mFundInfoBean = null;
    }

    /**
     * 得到当前基金申购返回数据
     */
    public void getFundSubscribe(PublicUseBean bean) {
        ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_subtion_result) + bean.getSerial_no());
        mServices.requestCurrentMoney();
        clearAllData();
    }
    /**
     * 清除所有数据
     */
    private void clearAllData(){
        getFundMessage();
        mEdtFundCode.setText("");
        mEdtFundSubscribeMoney.setText("");
        mKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 点击全部
     */
    public void onClickAll(){
        //把上限数量直接设置到金额输入框
        mEdtFundSubscribeMoney.setText(mTvUpLimit.getText().toString());
    }

    /**
     * 点击按钮所执行的操作
     */
    public void clickBtnSubtricbe() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String fundCode = mEdtFundCode.getText().toString().trim();
        String fundBalance = mEdtFundSubscribeMoney.getText().toString();
        if (TextUtils.isEmpty(fundCode) || fundCode.length() != 6) {
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_error3));
            return;
        }
        if (mFundInfoBean == null) {
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_error3));
            return;
        }
        if(TextUtils.isEmpty(fundBalance)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_error1));
            return;
        }
        /*if(moneySelectBean == null || (Double.parseDouble(fundBalance)-Double.parseDouble(this.moneySelectBean.getEnable_balance())<0)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_error4));
            return;
        }*/
        //发送申购请求
        mServices.requestFundSubscribe(fundCode, fundBalance,mFundInfoBean.getFund_company(),"0");
    }

    /**
     * 监听基金代码的输入情况
     */
    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(FundSubscribeFragment.class.getSimpleName(),"2.temp:"+temp+"||s:"+s);
            temp = s;
            Log.d(FundSubscribeFragment.class.getSimpleName(),"3.temp:"+temp+"||s:"+s);
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d(FundSubscribeFragment.class.getSimpleName(),"1.temp:"+temp+"||s:"+s);
        }
        @Override
        public void afterTextChanged(Editable s) {
            Log.d(FundSubscribeFragment.class.getSimpleName(),"4.temp:"+temp+"||s:"+s);
            if (temp.length() == 6) {
                //发送基金详情数据请求
                mServices.requestFundMessage(mEdtFundCode.getText().toString());
            }else{
                getFundMessage();
            }
        }
    };

    /**
     * 弹出基金开户对话框
     */
    public void openFundAccountDialog(final String errorNum,final String errorStr){
        MessageOkCancelDialog dialog = new MessageOkCancelDialog(mActivity, new MessageOkCancelDialog.IOkListener() {
            @Override
            public void onClickOk() {
                if(mFundInfoBean == null)
                    return;
                String fundBalance = mEdtFundSubscribeMoney.getText().toString();
                if(errorNum.equals("-30200710")){//开户
                    mServices.requestOpenAccount(mFundInfoBean.getFund_company());
                }else if(errorNum.equals("-30200711")){ //风险预警
                    mServices.requestFundSubscribe(mFundInfoBean.getFund_code(),
                            fundBalance,mFundInfoBean.getFund_company(),"1");
                }
            }
        });
        dialog.setCancelBtnVisiable(true);
        dialog.setMsgText(errorStr);
        dialog.show();
    }
}

/**
 * 控制类
 */
class FundSubscribeController extends AbsBaseController implements View.OnClickListener {

    private FundSubscribeFragment mFragment;

    public FundSubscribeController(FundSubscribeFragment fragment) {
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
        }else if (resId == R.id.tv_fund_click_sub_max) {
            mFragment.onClickAll();
        }
    }
}
