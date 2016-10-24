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
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.SelectListActivity;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.a_stock.bll.OneKeySelectServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.adapter.OneKeyAdapter;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 *  一键归集---资金转账
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/14
 */
public class OneKeyMoneyFragment extends AbsBaseFragment {
    /**
     * 依托的Activity
     */
    private TKFragmentActivity mActivity;
    /**
     * 该类的控制器
     */
    private OneKeyMoneyController mController;
    /**
     * 转出账号
     */
    private TextView mTvOutAccount;
    /**
     * 转入账号
     */
    private TextView mTvInAccount;
    /**
     * 输入转账金额
     */
    private EditText mEdtTransferMoney;
    /**
     * 输入资金密码
     */
    private EditText mEdtMoneyPwd;
    /**
     * ‘转账’按钮
     */
    private Button mBtnClick;
    /**
     * 资金账户数据列表
     */
    private ListView mListView;
    /**
     * 下方适配器
     */
    private OneKeyAdapter mAdapter;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLinLoading;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLinHaveData;
    /**
     * 业务类
     */
    private OneKeySelectServicesImpl mServices;
    /**
     * 主资金账号数据集
     */
    private ArrayList<OneKeyBean> mMainDataList;
    /**
     * 辅资金账号数据集
     */
    private ArrayList<OneKeyBean> mFuDataList;
    /**
     * 全局资源对象
     */
    private Resources mResources;
    /**
     * 是谁触发了选择列表  2 主资金账号 ，3 辅资金账号
     */
    private int mWhatClick;
    /**
     * 主资金数据集
     */
    private OneKeyBean mMainDataBean;
    /**
     * 辅资金数据集
     */
    private OneKeyBean mFuDataBean;
    /**
     * 密码键盘
     */
    private KeyboardManager mKeyboardForFundPwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_one_key_transfer, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mKeyboardForFundPwd.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWhatClick = 0;
    }

    @Override
    protected void findViews(View view) {
        mTvOutAccount =(TextView)view.findViewById(R.id.tv_onem_out_account);
        mTvInAccount =(TextView)view.findViewById(R.id.tv_onem_in_account);
        mEdtTransferMoney =(EditText)view.findViewById(R.id.edt_onem_money);
        mEdtMoneyPwd =(EditText)view.findViewById(R.id.edt_onem_pwd);
        mBtnClick =(Button)view.findViewById(R.id.btn_onem_transfer);
        mListView=(ListView)view.findViewById(R.id.lv_one_key_transfer);
        mLinLoading = (LinearLayout) view.findViewById(R.id.ll_list_loading);
        mLinHaveData =(LinearLayout)view.findViewById(R.id.lin_have_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvOutAccount, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvInAccount, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnClick, mController);
        mEdtTransferMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TradeUtils.onLimitAfterPoint(mEdtTransferMoney, s, 10,5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mActivity=(TKFragmentActivity)getActivity();
        mController=new OneKeyMoneyController(this);
        mAdapter=new OneKeyAdapter(mActivity);
        mServices=new OneKeySelectServicesImpl(this);
        mMainDataList = new ArrayList<OneKeyBean>();
        mFuDataList = new ArrayList<OneKeyBean>();
        mResources = mActivity.getResources();
    }

    @Override
    protected void initViews() {
        mListView.addHeaderView(LayoutInflater.from(getActivity()).inflate(
                R.layout.head_a_one_key, null));
        //添加自绘键盘
        mKeyboardForFundPwd = TradeTools.initKeyBoard(mActivity, mEdtMoneyPwd,KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        setTheme();
        mServices.requestOneKeyMessage();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 当没有请求到任何数据时
     */
    private void setInVisibleView(){
        mTvOutAccount.setHint(mResources.getString(R.string.one_key_no_text_out));
        mTvInAccount.setHint(mResources.getString(R.string.one_key_no_text_in));
        mLinLoading.setVisibility(View.GONE);
        mLinHaveData.setVisibility(View.VISIBLE);
    }

    /**
     * 得到账户归集数据列表
     * @param dataList
     */
    public void onGetMoneySelectList(ArrayList<OneKeyBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            setInVisibleView();
        } else {
            mLinLoading.setVisibility(View.GONE);
            mLinHaveData.setVisibility(View.VISIBLE);
            //给下方列表设值
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
            // 遍历区分资金账号
            for (OneKeyBean bean : dataList) {
                if (bean.getFundseq().equals("0")) { //主资金账号
                    mMainDataList.add(bean);
                } else if (bean.getFundseq().equals("1")) { //辅资金账号
                    mFuDataList.add(bean);
                }
            }
            // 转出账号
            if (mMainDataList == null ||mMainDataList.size() == 0) {
                mTvOutAccount.setHint(mResources.getString(R.string.one_key_no_text_out));
            } else{
                mTvOutAccount.setHint(mResources.getString(R.string.one_key_hint_select));
            }
            //转入账号
            if (mFuDataList == null || mFuDataList.size() == 0) {
                mTvInAccount.setHint(mResources.getString(R.string.one_key_no_text_in));
            }else{
                mTvInAccount.setHint(mResources.getString(R.string.one_key_hint_select));
            }
        }
    }
    /**
     * 点击转出账号跳转页面
     */
    public void clickTansferOutAccount(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mMainDataList == null || mMainDataList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.one_key_no_text_out));
        }else{
            mWhatClick = 2;
            Intent intent = new Intent(mActivity,SelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mMainDataList);
            intent.putExtra("useType","one_key");
            startActivityForResult(intent,60);
        }
    }
    /**
     * 点击转入账号跳转页面
     */
    public void clickTransferInAccount(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mFuDataList == null || mFuDataList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.one_key_no_text_in));
        }else{
            mWhatClick = 3;
            Intent intent = new Intent(mActivity,SelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mFuDataList);
            intent.putExtra("useType","one_key");
            startActivityForResult(intent,61);
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
        if(mWhatClick == 2){ //主资金账号
            OneKeyBean bean =  mMainDataList.get(position);
            mMainDataBean = bean;
            mTvOutAccount.setText(forMateAccount(bean.getFundseq())+""+bean.getBank_name()
                    +"  "+bean.getFund_account()+" "+bean.getMoney_type_name());
        }else if(mWhatClick == 3){ //辅资金账号
            OneKeyBean bean =  mFuDataList.get(position);
            mFuDataBean = bean;
            mTvInAccount.setText(forMateAccount(bean.getFundseq())+""+bean.getBank_name()
                    +"  "+bean.getFund_account()+" "+bean.getMoney_type_name());
        }
    }
    /**
     * 点击 ‘转账’按钮
     * 非空判断
     */
    public void onClickBtnTansfer(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String money=mEdtTransferMoney.getText().toString();
        String pwd=mEdtMoneyPwd.getText().toString();
        if(mMainDataBean == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.one_key4));
            return;
        }
        if(mFuDataBean == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.one_key5));
            return;
        }
        if(TextUtils.isEmpty(money)){
            ToastUtils.toast(mActivity,mResources.getString(R.string.one_key_three));
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            ToastUtils.toast(mActivity,mResources.getString(R.string.one_key_four));
            return;
        }
        mKeyboardForFundPwd.dismiss();
        mServices.requestTransferMoney(pwd,mMainDataBean.getMoney_type(), money,
                mMainDataBean.getFund_account(),mFuDataBean.getFund_account());
    }
    /**
     * 得到资金转账的返回结果
     * @param data
     */
    public void getTransferMoneyResult(OneKeyBean data){
        ToastUtils.toast(mActivity,data.getOutsno()+"--"+data.getInsno());
        mEdtTransferMoney.setText("");
        mEdtMoneyPwd.setText("");
        mTvInAccount.setText("");
        mTvOutAccount.setText("");
    }

    /**
     * 根据返回参数不同，返回不同字符串
     * @param str
     * @return (主) (辅)
     */
    public  String forMateAccount(String str){
        if(str.equals("0")){
            return mResources.getString(R.string.one_key_zhu2);
        }else if(str.equals("1")){
            return mResources.getString(R.string.one_key_fu2);
        }
        return str;
    }
}

class OneKeyMoneyController extends AbsBaseController implements  View.OnClickListener{

    private OneKeyMoneyFragment moneyFragment;

    public OneKeyMoneyController(OneKeyMoneyFragment mMoneyFragment) {
        moneyFragment = mMoneyFragment;
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.btn_onem_transfer) {//‘转账’
            moneyFragment.onClickBtnTansfer();
        } else if (resId == R.id.tv_onem_out_account) {//转出账号
            moneyFragment.clickTansferOutAccount();
        } else if (resId == R.id.tv_onem_in_account) {//转入账号
            moneyFragment.clickTransferInAccount();
        }
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }
}
