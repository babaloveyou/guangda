package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.SelectListActivity;
import com.thinkive.android.trade_bz.a_stock.activity.SignAgreementActivity;
import com.thinkive.android.trade_bz.a_stock.activity.SignAgreementMsgActivity;
import com.thinkive.android.trade_bz.a_stock.bean.SignAgreementBean;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;
import com.thinkive.android.trade_bz.a_stock.bll.SignAgreementImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;


/**
 *  退市板块协议签署
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */
public class SignAgreementFragment extends AbsBaseFragment {
    public static final int ACCOUNT_REQUEST_CODE = 59;
    public static final int TYPE_REQUEST_CODE = 68;
    private SignAgreementActivity mActivity;
    private SignAgreementImpl mServices;
    private SignAgreementController mController;
    private Resources mResources;
    private TextView mTvSelectStockAccount;
    private TextView mTvSelectBusinessType;
    private TextView mTvOpenStatus;
    private CheckBox mCheckboxAgreement;
    private TextView mTvClickWarnBook;
    private Button mBtnClickCommit;
    private ArrayList<SignAgreementBean> mStockAccountList;
    private SignAgreementBean mStockAccountBean;
    private ArrayList<SignStockAccountLimitBean> mAccountLimitList;
    private SignStockAccountLimitBean mAccountLimitBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_sign_agreement, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvSelectStockAccount = (TextView)view.findViewById(R.id.tv_select_stock_account);
        mTvSelectBusinessType = (TextView) view.findViewById(R.id.tv_select_business_type);
        mTvOpenStatus = (TextView) view.findViewById(R.id.tv_open_status);
        mCheckboxAgreement = (CheckBox) view.findViewById(R.id.checkbox_agreement);
        mTvClickWarnBook = (TextView) view.findViewById(R.id.tv_click_warn_book);
        mBtnClickCommit = (Button) view.findViewById(R.id.btn_click_open);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK,mTvSelectStockAccount,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvSelectBusinessType,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvClickWarnBook,mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnClickCommit,mController);
    }

    @Override
    protected void initData() {
        mActivity = (SignAgreementActivity)getActivity();
        mServices = new SignAgreementImpl(this);
        mController = new SignAgreementController(this);
        mResources = mActivity.getResources();
    }

    @Override
    protected void initViews() {
        mServices.requestStockAccountList();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 获取到股东列表
     */
    public void onGetStockAccountList(ArrayList<SignAgreementBean> dataList){
        if(dataList != null && dataList.size() > 0){
            mStockAccountList = dataList;
            mTvSelectStockAccount.setHint(mResources.getString(R.string.sign_agreement3));
        }else{
            mTvSelectStockAccount.setHint(mResources.getString(R.string.sign_agreement8));
        }
    }

    /**
     * 获得客户证券账户权限查询
     */
    public void onGetAccountLimit(ArrayList<SignStockAccountLimitBean> dataList){
        if(dataList != null && dataList.size() > 0){
            mAccountLimitList = dataList;
            mTvSelectBusinessType.setHint(mResources.getString(R.string.sign_agreement3));
        }else{
            mTvSelectBusinessType.setHint(mResources.getString(R.string.sign_agreement16));
        }
    }

    /**
     * 点击选择股东账号
     */
    public void onClickStockAccount(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mStockAccountList != null && mStockAccountList.size() > 0){
            Intent intent = new Intent(mActivity,SelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mStockAccountList);
            intent.putExtra("useType","stock_account");
            startActivityForResult(intent,ACCOUNT_REQUEST_CODE);
        }else{
            ToastUtils.toast(mActivity,mResources.getString(R.string.sign_agreement8));
        }
    }

    /**
     *点击选择业务类型
     */
    public void onClickBusinessType(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mAccountLimitList == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.sign_agreement17));
        }else if(mAccountLimitList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.sign_agreement16));
        }else if(mAccountLimitList != null && mAccountLimitList.size() > 0){
            Intent intent = new Intent(mActivity,SelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mAccountLimitList);
            intent.putExtra("useType","account_limit");
            startActivityForResult(intent,TYPE_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        if(requestCode == ACCOUNT_REQUEST_CODE){ //股东账号选择完成
            int position = data.getIntExtra("data_select_result",0);
            mStockAccountBean = mStockAccountList.get(position);
            mTvSelectStockAccount.setText(mStockAccountBean.getStock_account() + " " +mStockAccountBean.getExchange_type_name());
            mTvSelectBusinessType.setText("");
            mTvOpenStatus.setText("");
            mAccountLimitList = null;
            //客户证券账户权限查询
            mServices.requestStockAccountLimit(mStockAccountBean.getStock_account(),mStockAccountBean.getExchange_type());
        }else if(requestCode == TYPE_REQUEST_CODE){ //业务类型选择完毕
            int position = data.getIntExtra("data_select_result",0);
            mAccountLimitBean = mAccountLimitList.get(position);
            mTvSelectBusinessType.setText(mAccountLimitBean.getBusiness_type_name());
            mTvOpenStatus.setText(mAccountLimitBean.getHas_delist_name());
            if(mAccountLimitBean.getHas_delist().equals(1)){ // 1：已开通
                mBtnClickCommit.setText(mResources.getString(R.string.sign_agreement20));
            }else{
                mBtnClickCommit.setText(mResources.getString(R.string.sign_agreement7));
            }
        }
    }

    /**
     *点击风险警示书
     */
    public void onClickWarnBook(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mAccountLimitBean != null) {
            Intent intent = new Intent(mActivity, SignAgreementMsgActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("warn_book", mAccountLimitBean);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            ToastUtils.toast(mActivity,mResources.getString(R.string.sign_agreement18));
        }
    }

    /**
     *点击开通按钮
     */
    public void onClickBtnOpen(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mStockAccountBean == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.sign_agreement17));
            return;
        }
        if(mAccountLimitBean == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.sign_agreement18));
            return;
        }
        if(!mCheckboxAgreement.isChecked()){
            ToastUtils.toast(mActivity,mResources.getString(R.string.sign_agreement9));
            return;
        }
        mServices.requestLimitCommit(mAccountLimitBean);
    }

    /**
     * 清空所有数据
     */
    public void clearAllData(){
        mTvOpenStatus.setText("");
        mTvSelectBusinessType.setText("");
        mTvSelectStockAccount.setText("");
        mStockAccountBean = null;
        mAccountLimitList = null;
        mAccountLimitBean = null;
    }
}

class SignAgreementController extends AbsBaseController implements View.OnClickListener {
    private SignAgreementFragment mFragment;

    public SignAgreementController(SignAgreementFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int clickId = v.getId();
        if (clickId == R.id.tv_select_stock_account) {
            mFragment.onClickStockAccount();
        } else if (clickId == R.id.tv_select_business_type) {
            mFragment.onClickBusinessType();
        } else if (clickId == R.id.tv_click_warn_book) {
            mFragment.onClickWarnBook();
        }else if (clickId == R.id.btn_click_open) {
            mFragment.onClickBtnOpen();
        }
    }
}
