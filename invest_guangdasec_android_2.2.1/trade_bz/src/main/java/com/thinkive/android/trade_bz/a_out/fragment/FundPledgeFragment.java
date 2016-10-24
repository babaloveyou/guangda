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
import com.thinkive.android.trade_bz.a_out.bean.FundInfoBean;
import com.thinkive.android.trade_bz.a_out.bean.FundReturnMoneyDateBean;
import com.thinkive.android.trade_bz.a_out.bll.FundPledgeServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.DatePickerSelect;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *  场外基金定投下单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/26
 */
public class FundPledgeFragment extends AbsBaseFragment {
    private AppCompatActivity mActivity;
    private FundPledgeViewController mController;
    private FundPledgeServiceImpl mServices;
    /**
     * 开始日期
     */
    private TextView mTvSeltctDataStart;
    /**
     * 结束日期
     */
    private TextView mTvSelectDataEnd;
    /**
     * 扣款日期
     */
    private TextView mTvSelectDataTake;
    /**
     * 基金代码
     */
    private EditText mEdtFundCode;
    /**
     * 基金名称
     */
    private TextView mTvFundName;
    /**
     * 基金净值
     */
    private TextView mTvFundProfit;
    /**
     * 定投金额
     */
    private TextView mEdtFundPledgeNum;
    /**
     * 提交按钮
     */
    private Button mBtnClickCommit;
    /**
     * 日期选择器
     */
    private DatePickerSelect mDateSelect;
    /**
     * 基金公司
     */
    public String mFundCompany = "";
    /**
     * 框架内，自绘键盘管理器
     */
    private KeyboardManager mKeyboardManager;
    /**
     * 协议种类
     */
    private String mSaveType = "";
    /**
     * 委托编号
     */
    private String mAllotno = "";
    /**
     * 转换基金详情
     */
    private FundInfoBean mFundMsgBean;
    /**
     * 扣款日数据集
     */
    private ArrayList<FundReturnMoneyDateBean> mTakeDateList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_pledge_order, null);
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
        mTvSeltctDataStart=(TextView)view.findViewById(R.id.tv_set_data_start);
        mTvSelectDataEnd=(TextView)view.findViewById(R.id.tv_set_data_end);
        mTvSelectDataTake=(TextView)view.findViewById(R.id.tv_select_data_take);
        mEdtFundCode =(EditText)view.findViewById(R.id.edt_fund_dire_code);
        mEdtFundCode.addTextChangedListener(mTextWatcher);
        mTvFundName=(TextView)view.findViewById(R.id.tv_fund_dire_name);
        mTvFundProfit=(TextView)view.findViewById(R.id.tv_fund_profit);
        mEdtFundPledgeNum =(TextView)view.findViewById(R.id.edt_fund_pledge_num);
        mBtnClickCommit=(Button)view.findViewById(R.id.btn_fund_commmit);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSeltctDataStart, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectDataEnd, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectDataTake, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnClickCommit, mController);
    }

    @Override
    protected void initData() {
        mActivity=(AppCompatActivity)getActivity();
        mController=new FundPledgeViewController(this);
        mServices=new FundPledgeServiceImpl(this);
        mDateSelect=new DatePickerSelect(mActivity);
    }

    @Override
    protected void initViews() {
        mTvSeltctDataStart.setText(TradeUtils.getCurrentDate());
        mTvSelectDataEnd.setText(TradeUtils.getAfterWeek());
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
            mFundMsgBean = bean;
            mTvFundName.setText(bean.getFund_name());
            mTvFundProfit.setText(bean.getNav());
            mFundCompany = bean.getFund_company();
        }
    }
    /**
     * 当前基金代码输入错误
     */
    public void getFundMessage(){
        mTvFundName.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvFundProfit.setHint(mActivity.getResources().getString(R.string.no_text_set));
        mTvFundName.setText("");
        mTvFundProfit.setText("");
        mTvSelectDataTake.setText("");
        mEdtFundPledgeNum.setText("");
        mFundCompany = "";
        mFundMsgBean = null;
        mTakeDateList = null;
    }
    /**
     * 点击选择开始日期
     */
    public void onClickStartDate(){
        mDateSelect.showDateDialog(mTvSeltctDataStart,mTvSeltctDataStart.getText().toString());
    }

    /**
     * 点击选择结束日期
     */
    public void onClickEndDate(){
        mDateSelect.showDateDialog(mTvSelectDataEnd,mTvSelectDataEnd.getText().toString());
    }

    /**
     * 扣款日
     */
    public void onClickTakeMoneyDate(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mTakeDateList == null || mTakeDateList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.fund_pledge12));
        }else{
        Intent intent = new Intent(mActivity,FundListActivity.class);
        intent.putParcelableArrayListExtra("data_select",mTakeDateList);
        intent.putExtra("useType","fund_date");
        startActivityForResult(intent,86);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        FundReturnMoneyDateBean bean =  mTakeDateList.get(position);
        mTvSelectDataTake.setText(bean.getPay_day());
        mSaveType = bean.getSave_plan_cls();
        mAllotno = bean.getCycle_num();
    }

    /**
     * 按钮提交
     */
    public void onClickBtnCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String code = mEdtFundCode.getText().toString();
        String num = mEdtFundPledgeNum.getText().toString();
        String start = mTvSeltctDataStart.getText().toString();
        String end = mTvSelectDataEnd.getText().toString();
        String takeDate = mTvSelectDataTake.getText().toString();
        if(TextUtils.isEmpty(code) || code.length() != 6){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.level_error3));
            return;
        }
        if(mFundMsgBean == null){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_error3));
            return;
        }
        if(TextUtils.isEmpty(num)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_convert7));
            return;
        }

        if(checkOutDate(start,end)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.date_select_error));
            return;
        }

        if(TextUtils.isEmpty(takeDate)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_convert10));
            return;
        }
        mServices.requestSetDriection(code,num,start,end,takeDate,mFundCompany,mSaveType,mAllotno);
    }

    /**
     * 得到基金还款日期
     */
    public void onGetFundReturnDate(ArrayList<FundReturnMoneyDateBean> dataList){
        if(dataList != null && dataList.size() > 0){
            mTvSelectDataTake.setHint(mActivity.getResources().getString(R.string.fund_pledge8));
            mTakeDateList = dataList;
        }else{
            mTvSelectDataTake.setHint(mActivity.getResources().getString(R.string.fund_pledge12));
        }
    }
    /**
     * 得到定投的结果
     */
    public void onGetDirectionResult(String result){
        if(result != null){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_rirection) + result);
            clearAllData();
        }
    }
    /**
     * 清除所有数据
     */
    private void clearAllData(){
        getFundMessage();
        mEdtFundCode.setText("");
        mEdtFundPledgeNum.setText("");
        mTvSelectDataTake.setText("");
        mSaveType =  "";
        mAllotno =  "";
        mKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * 判断开始日期和截止日期的大小
     * @param beginDate
     * @param endDate
     * @return true 开始日期不能大于当前日期，截止日期不能大于开始日期
     */
    public static boolean checkOutDate(String beginDate,String endDate){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        try {
            Date curDate = df.parse(date);
            Date begin = df.parse(beginDate);
            Date end = df.parse(endDate);
            if (begin.getTime() < curDate.getTime()) {
                return true;
            }
            if (end.getTime() < begin.getTime()) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}


class FundPledgeViewController extends AbsBaseController implements
        View.OnClickListener {

    private FundPledgeFragment mFragment;

    public FundPledgeViewController(FundPledgeFragment fragment) {
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
        if(resId== R.id.tv_set_data_start){
            mFragment.onClickStartDate();
        }else if(resId== R.id.tv_set_data_end){
            mFragment.onClickEndDate();
        }else if(resId== R.id.tv_select_data_take){
            mFragment.onClickTakeMoneyDate();
        }else if(resId== R.id.btn_fund_commmit){
            mFragment.onClickBtnCommit();
        }
    }
}


