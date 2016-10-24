package com.thinkive.android.trade_bz.a_hk.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockMessageBean;
import com.thinkive.android.trade_bz.a_hk.bll.HKVoteSubServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 *  投票申报
 * @author 张雪梅
 * @company Thinkive
 * @date 16/5/23
 */

public class HKVoteSubFragment extends AbsBaseFragment {
    private HKVoteSubController mController;
    private HKVoteSubServicesImpl mServices;
    private TKFragmentActivity mActivity;
    private KeyboardManager mKeyboardManager;
    private View mChildScrollView;
    private ScrollView mScrollView;
    /**
     * 证券代码
     */
    private EditText mEdtInPutCode;
    /**
     *证券名称
     */
    private TextView mTvStockName;
    /**
     *公告编号
     */
    private EditText mEdtNumber1;
    /**
     *议案编号
     */
    private EditText mEdtNumber2;
    /**
     *赞成数量
     */
    private EditText mEdtInPutYes;
    /**
     *反对数量
     */
    private EditText mEdtInputNo;
    /**
     *放弃数量
     */
    private EditText mEdtInputGiveUp;
    /**
     *提交按钮
     */
    private Button mBtnCommit;
    /**
     *数据联动结果集
     */
    private HKStockMessageBean mDataLinkBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mChildScrollView = inflater.inflate(R.layout.fragment_hk_vote_sub, null);
        findViews(mRootView);
        initData();
        setListeners();
        initViews();
        return mRootView;
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mScrollView=(ScrollView)view.findViewById(R.id.sll_layout);
        mScrollView.addView(mChildScrollView);
        mEdtInPutCode = (EditText)mChildScrollView.findViewById(R.id.edt_hk_vote_code);
        mEdtInPutCode.addTextChangedListener(mTextWatcher);
        mTvStockName = (TextView)mChildScrollView.findViewById(R.id.tv_hk_vote_name);
        mEdtNumber1 = (EditText)mChildScrollView.findViewById(R.id.edt_hk_vote_no1);
        mEdtNumber2 = (EditText)mChildScrollView.findViewById(R.id.edt_hk_vote_no2);
        mEdtInPutYes = (EditText)mChildScrollView.findViewById(R.id.edt_hk_vote_yes);
        mEdtInputNo = (EditText)mChildScrollView.findViewById(R.id.edt_hk_vote_no);
        mEdtInputGiveUp = (EditText)mChildScrollView.findViewById(R.id.edt_hk_vote_give_up);
        mBtnCommit = (Button)mChildScrollView.findViewById(R.id.btn_hk_vote_commit);
    }

    @Override
    protected void initData() {
        mController = new HKVoteSubController(this);
        mServices = new HKVoteSubServicesImpl(this);
        mActivity = (TKFragmentActivity)getActivity();
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdtInPutCode, KeyboardManager.KEYBOARD_TYPE_STOCK);
    }


    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mBtnCommit, mController);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 监听证券输入情况
     */
    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 5) {
                //请求证券详情
                mServices.requestVoteSubLink(mEdtInPutCode.getText().toString());
            }else{
                onGetFailMessage();
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    /**
     * 获取证券联动数据
     */
    public void onGetVoteSubLinkData(HKStockMessageBean bean){
        if(bean != null){
            mDataLinkBean = bean;
            mTvStockName.setText(bean.getName());
        }
    }
    /**
     * 未获取证券联动数据
     */
    public void onGetFailMessage(){
        mTvStockName.setText("");
        mDataLinkBean = null;
    }

    /**
     * 清除所有数据
     */
    public void clearAllData(){
        onGetFailMessage();
        mEdtNumber1.setText("");
        mEdtNumber2.setText("");
        mEdtInPutCode.setText("");
        mEdtInPutYes.setText("");
        mEdtInputNo.setText("");
        mEdtInputGiveUp.setText("");
    }
    /**
     * 点击提交
     */
    public void onClickBtnCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String code = mEdtInPutCode.getText().toString().trim();
        String yes = mEdtInPutYes.getText().toString().trim();
        String no = mEdtInputNo.getText().toString().trim();
        String giveUp = mEdtInputGiveUp.getText().toString().trim();
        String number1 = mEdtNumber1.getText().toString().trim();
        String number2 = mEdtNumber2.getText().toString().trim();
        if(TextUtils.isEmpty(code) || code.length() != 5){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_vote0));
            return;
        }
//        if(mDataLinkBean == null){
//            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.error_hint16));
//            return;
//        }
        if(TextUtils.isEmpty(number1)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_order28));
            return;
        }
        if(TextUtils.isEmpty(number2)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.hk_order29));
            return;
        }
        mKeyboardManager.dismiss();
        mServices.requestVoteSubCommit(code,number1,number2,yes,no,giveUp);
    }
}

class HKVoteSubController extends AbsBaseController implements View.OnClickListener{

    private HKVoteSubFragment mFragment;

    public HKVoteSubController(HKVoteSubFragment fragment) {
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
        if(v.getId() == R.id.btn_hk_vote_commit){
            mFragment.onClickBtnCommit();
        }
    }
}