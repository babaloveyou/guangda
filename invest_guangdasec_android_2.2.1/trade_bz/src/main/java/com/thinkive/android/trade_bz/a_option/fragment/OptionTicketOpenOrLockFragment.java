package com.thinkive.android.trade_bz.a_option.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.activity.OptionSelectListActivity;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractLockBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractOpenBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionCoveredSecuritiesTransferBean;
import com.thinkive.android.trade_bz.a_option.bll.OptionTicketOpenOrLockServicesImpl;
import com.thinkive.android.trade_bz.a_stock.bean.UserInfo;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 个股期权 备兑券解锁 备兑券锁定
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/17
 */
public class OptionTicketOpenOrLockFragment extends AbsBaseFragment {

    private TKFragmentActivity mActivity;
    private OptionTicketOpenOrLockServicesImpl mServices;
    private OptionOpenOrLockController mController;
    private Resources mResources;
    private TextView mTvSelectStock;
    private TextView mTvNumCut;
    private EditText mEdtEntrustNum;
    private TextView mTvEntrustNumText;
    private TextView mTvNumAdd;
    private TextView mTvMaxEnable;
    private TextView mTvMaxEnableText;
    private TextView mTvClickAllMax;
    private Button mBtnClickCommit;
    /**
     * 0锁定  1解锁
     */
    private int mBuyOrSell;
    private static ArrayList<OptionContractLockBean> optionContractLockBeens = null;//备兑券锁定 合约列表
    private OptionContractLockBean selectedOptionContractLockBean = null;//备兑锁定 选中的合约
    private static ArrayList<OptionContractOpenBean> optionContractOpenBeens = null;//备兑券解锁 合约列表
    private OptionContractOpenBean selectedOptionContractOpenBean = null;//备兑解锁 选中的合约

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option_lock_open, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvSelectStock = (TextView)view.findViewById(R.id.tv_select_stock);
        mTvNumCut = (TextView)view.findViewById(R.id.tv_num_cut);
        mEdtEntrustNum = (EditText)view.findViewById(R.id.edt_entrust_num);
        mTvEntrustNumText = (TextView)view.findViewById(R.id.tv_entrust_str);
        mTvNumAdd = (TextView)view.findViewById(R.id.tv_num_add);
        mTvMaxEnable = (TextView)view.findViewById(R.id.tv_max_enable);
        mTvMaxEnableText = (TextView)view.findViewById(R.id.tv_max_text);
        mTvClickAllMax = (TextView)view.findViewById(R.id.tv_click_all_max);
        mBtnClickCommit = (Button)view.findViewById(R.id.btn_click_commit);
    }


    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mTvSelectStock, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvClickAllMax, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnClickCommit, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvNumCut, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvNumAdd, mController);

    }

    @Override
    protected void initData() {
        mActivity=(TKFragmentActivity)getActivity();
        mResources = CoreApplication.getInstance().getResources();
        mController = new OptionOpenOrLockController(this);
        mServices = new OptionTicketOpenOrLockServicesImpl(this);
        Bundle bundle = getArguments();
        if(bundle != null){
            mBuyOrSell = bundle.getInt("buyOrSale",0);
        }
    }

    @Override
    protected void initViews() {
        HashMap<String,String> params = new HashMap<String,String>();//查询合约入参
        if(mBuyOrSell == 0 && optionContractLockBeens == null){ //锁定
            mTvMaxEnableText.setText(mResources.getString(R.string.option_buy24));
            mTvEntrustNumText.setText(mResources.getString(R.string.option_buy23));
            UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
            if(!"".equals(sOptionUserInfo_hu.getStock_account())){
                params.put("stockAccount", sOptionUserInfo_hu.getStock_account());
                params.put("tradeSector", "15");//15是上海股票期权
                mServices.requestOptionContractLock(params);
            }else{
                ToastUtils.toast(getContext(),getString(R.string.option_error15));
            }
        }else if(mBuyOrSell == 1 && optionContractOpenBeens == null){// 解锁
            mTvMaxEnableText.setText(mResources.getString(R.string.option_buy25));
            mTvEntrustNumText.setText(mResources.getString(R.string.option_buy26));
            UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
            if(!"".equals(sOptionUserInfo_hu.getStock_account())){
                params.put("stockAccount", sOptionUserInfo_hu.getStock_account());
                params.put("tradeSector", "15");//15是上海股票期权
                mServices.requestOptionContractOpen(params);
            }else{
                ToastUtils.toast(getContext(),getString(R.string.option_error16));
            }
        }
    }

    @Override
    protected void setTheme() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data == null){
            return;
        }
        if(requestCode == 0){
            //备兑锁定
            if(resultCode == 88){
                //成功返回选择结果
                int position=data.getIntExtra("data_select_result",0);
                selectedOptionContractLockBean = optionContractLockBeens.get(position);
                //选择后，设置页面上一些元素的显示
                mTvSelectStock.setText(selectedOptionContractLockBean.getStock_code());
                mTvMaxEnable.setText(selectedOptionContractLockBean.getOrder_qty());
            }
        }else if(requestCode == 1){
            //备兑解锁
            if(resultCode == 88){
                //成功返回选择结果
                int position=data.getIntExtra("data_select_result",0);
                selectedOptionContractOpenBean = optionContractOpenBeens.get(position);
                //选择后，设置页面上一些元素的显示
                mTvSelectStock.setText(selectedOptionContractOpenBean.getStock_code());
                mTvMaxEnable.setText(selectedOptionContractOpenBean.getCvd_stk_avl());
            }
        }
    }

    /**
     * 点击选择合约
     */
    public void onClickSelectContract(){
        Intent intent = new Intent(mActivity,OptionSelectListActivity.class);
        if(mBuyOrSell == 0){
            //锁定
            intent.putParcelableArrayListExtra("data_select",optionContractLockBeens);
            intent.putExtra("useType","option_contract_lock");
            startActivityForResult(intent,0);
        }else if(mBuyOrSell == 1){
            //解锁
            intent.putParcelableArrayListExtra("data_select",optionContractOpenBeens);
            intent.putExtra("useType","option_contract_open");
            startActivityForResult(intent,1);
        }
    }

    /**
     * 点击数量减
     */
    public void onClickNumCut(){
        int num = getIntEntrustNum();
        if (num != 0) {
            mEdtEntrustNum.setText(String.valueOf(num - 100));
        }
    }
    /**
     * 点击数量加
     */
    public void onClickNumAdd(){
        int num = getIntEntrustNum();
        if (num != 0) {
            mEdtEntrustNum.setText(String.valueOf(num + 100));
        }
    }

    /**
     * 点击 “全部”
     */
    public void onClickMaxAll(){
        mEdtEntrustNum.setText(mTvMaxEnable.getText().toString());
    }
    /**
     * 点击提交委托按钮
     */
    public void onClickBtnCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String stockCodeName=mTvSelectStock.getText().toString().trim();//标的证券名称
        String entrustAmount = mEdtEntrustNum.getText().toString().trim();//委托数量
        String enableAmount = mTvMaxEnable.getText().toString().trim();//可用数量
        //证券名称
        if(TextUtils.isEmpty(stockCodeName)){
            ToastUtils.toast(getContext(),getString(R.string.option_error22));
        }
        //需要输入委托数量，且委托数量必须满足要求
        if(TextUtils.isEmpty(entrustAmount) || !TextUtils.isDigitsOnly(entrustAmount)||!isInteger(entrustAmount)){
            ToastUtils.toast(mActivity,R.string.option_error11);
            return;
        }else{
            if(Integer.parseInt(enableAmount)-Integer.parseInt(entrustAmount)<0){
                //委托数量，不能操作最多可用数量
                ToastUtils.toast(getContext(),getString(R.string.option_error20));
            }
        }
        HashMap<String,String> params = new HashMap<String,String>();
        if(mBuyOrSell == 0){
            //锁定
            params.put("password","");
            params.put("fundAccountOpt",selectedOptionContractLockBean.getFund_account_opt());
            params.put("stockAccount",selectedOptionContractLockBean.getStock_account());
            params.put("exchangeType",selectedOptionContractLockBean.getTrade_sector());
            params.put("stockCode",selectedOptionContractLockBean.getStock_code());
            params.put("lockDirection","1");
            params.put("entrustAmount",entrustAmount);
        }else if(mBuyOrSell == 1){
            //解锁
            params.put("password","");
            params.put("fundAccountOpt",selectedOptionContractOpenBean.getFund_account());
            params.put("stockAccount",selectedOptionContractOpenBean.getStock_account());
            params.put("exchangeType",selectedOptionContractOpenBean.getTrade_sector());
            params.put("stockCode",selectedOptionContractOpenBean.getStock_code());
            params.put("lockDirection","2");
            params.put("entrustAmount",entrustAmount);
        }
        mServices.requestOptionCoveredSecuritiesTransfer(params);
    }

    /**
     * 获取委托数量输入框上面的数据
     * 返回int类型的值
     */
    private int getIntEntrustNum() {
        int result = 0;
        String priceEditTextContent = mEdtEntrustNum.getText().toString().trim();
        if (!TextUtils.isEmpty(priceEditTextContent)) {
            try {
                result = Integer.parseInt(priceEditTextContent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return result;
    }

    /***
     * 判断 String 是否int
     *
     * @param input
     * @return
     */
    private boolean isInteger(String input){
        Matcher mer = Pattern.compile("^[0-9]+$").matcher(input);
        return mer.find();
    }

    /**
     * 备兑锁定 获取合约 结果处理
     */
    public void getOptionContractLockResult(ArrayList<OptionContractLockBean> optionContractLockBeens){
        if(optionContractLockBeens!=null && optionContractLockBeens.size()>0){
            this.optionContractLockBeens = optionContractLockBeens;
            mTvSelectStock.setHint(R.string.option_buy1);
            mTvMaxEnable.setText(optionContractLockBeens.get(0).getOrder_qty());
        }else{
            mTvMaxEnable.setText(R.string.no_text_set);
            mTvSelectStock.setHint(R.string.option_buy17);
        }
    }

    /**
     * 备兑解锁 获取合约 结果处理
     */
    public void getOptionContractOpenResult(ArrayList<OptionContractOpenBean> optionContractOpenBeens){
        if(optionContractOpenBeens != null && optionContractOpenBeens.size()>0){
            this.optionContractOpenBeens = optionContractOpenBeens;
            mTvSelectStock.setHint(R.string.option_buy1);
            mTvMaxEnable.setText(optionContractOpenBeens.get(0).getCvd_stk_avl());
        }else{
            mTvMaxEnable.setText(R.string.no_text_set);
            mTvSelectStock.setHint(R.string.option_buy17);
        }
    }

    /**
     *  备兑锁定/解锁 提交 结果处理
     * @param optionCoveredSecuritiesTransferBeens
     */
    public void getOptionCoveredSercuritiesTransferResult(ArrayList<OptionCoveredSecuritiesTransferBean> optionCoveredSecuritiesTransferBeens){
        ToastUtils.toast(getContext(),getString(R.string.option_error17));
    }

}

class OptionOpenOrLockController extends AbsBaseController implements View.OnClickListener {

    private OptionTicketOpenOrLockFragment mFragment;

    public OptionOpenOrLockController(OptionTicketOpenOrLockFragment mFragment) {
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
        if (resId == R.id.tv_click_all_max) { // 点击全部
            mFragment.onClickMaxAll();
        }else if (resId == R.id.tv_num_add) { // 交易数量+
            mFragment.onClickNumAdd();
        } else if (resId == R.id.tv_num_cut) { // 交易数量-
            mFragment.onClickNumCut();
        } else if (resId == R.id.btn_click_commit) { // 点击提交按钮
            mFragment.onClickBtnCommit();
        }else if(resId == R.id.tv_select_stock){ //选择合约
            mFragment.onClickSelectContract();
        }
    }
}



