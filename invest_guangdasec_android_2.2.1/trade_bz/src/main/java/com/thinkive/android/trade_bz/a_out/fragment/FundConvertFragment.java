package com.thinkive.android.trade_bz.a_out.fragment;

import android.content.Intent;
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
import com.thinkive.android.trade_bz.a_out.activity.FundListActivity;
import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_out.bean.FundInfoBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.a_out.bll.FundConvertServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 场外基金转换
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/26
 */
public class FundConvertFragment extends AbsBaseFragment {
    private AppCompatActivity mActivity;
    private FundConvertController mController;
    private FundConvertServiceImpl mServices;
    /**
     * 转出账户
     */
    private TextView mTvTransOutName;
    /**
     * 转出账户净值
     */
    private TextView mTvTransOutProfit;
    /**
     * 基金名称
     */
    private TextView mTvFundInName;
    /**
     * 转入账户
     */
    private EditText mEdtTransInCode;
    /**
     * 转入账户净值
     */
    private TextView mTvTransInProfit;
    /**
     * 转出数量
     */
    private EditText mEdtTransNum;
    /**
     * 该账户可转数量
     */
    private TextView mTvCanTransOutNum;
    /**
     * 转换按钮
     */
    private Button mBtnConvert;
    /**
     * 转出基金代码
     */
    private String mTransOutFundCode = "";
    /**
     * 框架内，自绘键盘管理器
     */
    private KeyboardManager mKeyboardManager;
    /**
     * 基金公司
     */
    private String mFundOutCompany = "";
    /**
     * 持有基金（转出基金）
     */
    private ArrayList<FundHoldBean> mDataList;
    /**
     * 转入基金数据集
     */
    private FundInfoBean mInDataBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_convert, null);
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
        mTvTransOutName = (TextView) view.findViewById(R.id.tv_out_fund_out);
        mTvTransOutProfit = (TextView) view.findViewById(R.id.tv_fund_out_profit);
        mTvFundInName = (TextView) view.findViewById(R.id.tv_fund_in_name);
        mEdtTransInCode = (EditText) view.findViewById(R.id.edt_fund_in_name);
        mEdtTransInCode.addTextChangedListener(mTextWatcher);
        mTvTransInProfit = (TextView) view.findViewById(R.id.tv_fund_in_profit);
        mEdtTransNum = (EditText) view.findViewById(R.id.edt_fund_out_num);
        mTvCanTransOutNum = (TextView) view.findViewById(R.id.tv_can_out_num);
        mBtnConvert = (Button) view.findViewById(R.id.btn_fund_convert);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTransOutName, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnConvert, mController);
    }

    @Override
    protected void initData() {
        mActivity = (AppCompatActivity) getActivity();
        mController = new FundConvertController(this);
        mServices = new FundConvertServiceImpl(this);
    }

    @Override
    protected void initViews() {
        mServices.requestHoldStockList();
        //添加自绘键盘
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtTransInCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
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
                mServices.requestFundMessage(mEdtTransInCode.getText().toString());
            }else{
                getFundMessage();
            }
        }
    };
    /**
     * 得到当前基金的正确详情信息
     */
    public void getFundMessage(FundInfoBean bean) {
        if(bean != null){
            mInDataBean = bean;
            mTvTransInProfit.setText(bean.getNav());
            mTvFundInName.setText(bean.getFund_name());
        }
    }
    /**
     * 当前基金代码输入错误
     */
    public void getFundMessage(){
        mTvTransInProfit.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvFundInName.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvTransInProfit.setText("");
        mTvFundInName.setText("");
        mInDataBean = null;
    }

    /**
     * 得到可转托基金的数据列表
     * @param dataList
     */
    public void onGetCanTransDataList(ArrayList<FundHoldBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mTvTransOutName.setHint(mActivity.getResources().getString(R.string.fund_convert8));
        } else {
            mDataList = dataList;
            mTvTransOutName.setHint(mActivity.getResources().getString(R.string.fund_input_hint2));
        }
    }
    /**
     * 点击选择基金
     */
    public void onClickLinForPop() {
        if(mDataList == null || mDataList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.fund_convert8));
        }else{
            Intent intent = new Intent(mActivity,FundListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mDataList);
            intent.putExtra("useType","fund_hold");
            startActivityForResult(intent,87);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        FundHoldBean bean =  mDataList.get(position);
        mTvTransOutName.setText(bean.getFund_name());
        mTvTransOutProfit.setText(bean.getNav());
        mTvCanTransOutNum.setText(bean.getEnable_shares());
        mTransOutFundCode = bean.getFund_code();
        mFundOutCompany = bean.getFund_company();
    }

    /**
     * 点击转换按钮
     */
    public void onClickBtnConvert() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String outName = mTvTransOutName.getText().toString();
        String entrustNum = mEdtTransNum.getText().toString().trim();
        if(TextUtils.isEmpty(outName) || TextUtils.isEmpty(mTransOutFundCode)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_convert11));
            return;
        }
        if(mInDataBean == null){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_error2));
            return;
        }
        if(TextUtils.isEmpty(entrustNum)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_convert0));
            return;
        }
        if(TextUtils.isEmpty(entrustNum)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_convert0));
            return;
        }
        if(!mFundOutCompany.equals(mInDataBean.getFund_company())){ //转入基金与转出基金公司不一致
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_convert9));
            return;
        }
        mServices.requestFundConvert(mTransOutFundCode,mInDataBean.getFund_code(),
                entrustNum, mInDataBean.getFund_company());
    }

    /**
     * 得到转换的结果
     * @param bean
     */
    public void onGetConvertData(PublicUseBean bean) {
        ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trans_con) + bean.getSerial_no());
        mServices.requestHoldStockList();
        clearAllData();
    }

    /**
     * 清除所有数据
     */
    private void clearAllData(){
        mTvTransOutName.setText("");
        mTvTransOutProfit.setText("");
        mTvCanTransOutNum.setText("");
        mEdtTransInCode.setText("");
        mTvFundInName.setText("");
        mTvTransInProfit.setText("");
        mEdtTransNum.setText("");
        mTransOutFundCode = "";
        mFundOutCompany = "";
        mInDataBean = null;
        mKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }
}

class FundConvertController extends AbsBaseController implements
        View.OnClickListener {

    private FundConvertFragment mFragment;

    public FundConvertController(FundConvertFragment fragment) {
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
        if (TradeUtils.isFastClick()) {
            return;
        }
        int resId = v.getId();
        if (resId == R.id.tv_out_fund_out) { // 点击选择基金
            mFragment.onClickLinForPop();
        } else if (resId == R.id.btn_fund_convert) { //按钮
            mFragment.onClickBtnConvert();
        }
    }
}


