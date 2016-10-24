package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerController;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RChooseContractActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectListActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RStockReturnOrderStockServiceImpl;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 融资融券现券还券（直接还券）
 * @author 张雪梅
 * @date 2016/7/18
 */

public class RStockReturnOrderStockFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private RStockReturnOrderStockController mController;
    private RStockReturnOrderStockServiceImpl mServices;
    private Resources mResources;
    /**
     * 选择合约布局
     */
    private RelativeLayout mRLChooseStockList;
    /**
     * 点击选择合约
     */
    private TextView mTvChooseContract;
    /**
     * 提交委托
     */
    private Button mBtnClickCommit;
    /**
     * 管理RadioButton
     */
    private RadioGroup mRadioGroup;
    /**
     *现券还券
     */
    private RadioButton mRbReturnStock;
    /**
     *选择合约
     */
    private RadioButton mRbSelectContract;
    /**
     * 证券编码
     */
    private TextView mTvSelectCode;
    /**
     * 委托数量
     */
    private EditText mEdtInputNum;
    /**
     * 需归还数量
     */
    private TextView mTvShouldReturn;
    /**
     * 最大归还数量
     */
    private TextView mTvMaxCanReturn;
    /**
     * 点击全部
     */
    private TextView mTvClickAll;
    /**
     * 数量加
     */
    private TextView mTvNumAdd;
    /**
     * 数量减
     */
    private TextView mTvNumCut;
    /**
     * 正在加载
     */
    private LinearLayout mLinLoading;
    /**
     * 股票代码
     */
    private String mStockCode = "";
    /**
     * 我的持仓数据集
     */
    private ArrayList<MyStoreStockBean> mHoldDataList;
    /**
     * 当前用户选择合约的结果集
     */
    private ArrayList<RChooseContractBean> mContractDataList;
    /**
     * 当前证券联动bean
     */
    private RStockToStockLinkBean mStockLinkBean;
    /**
     * 总共需要还的数量
     */
    private String mAllShouldReturn = "";
    /**
     *选中合约后需要还的数量
     */
    private String mSelectShouldReturn ="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_r_stock_return_stock, null);
        findViews(mRootView);
        initData();
        setListeners();
        initViews();
        return mRootView;
    }

    @Override
    protected void findViews(View view) {
        mLinLoading = (LinearLayout)view.findViewById(R.id.lin_loading_set);
        mRadioGroup = (RadioGroup)view.findViewById(R.id.rg_return_stock);
        mRbReturnStock = (RadioButton)view.findViewById(R.id.rb_return_stock);
        mRbSelectContract = (RadioButton)view.findViewById(R.id.rb_select_contract);
        mRLChooseStockList = (RelativeLayout) view.findViewById(R.id.rl_r_choose_stock);
        mTvChooseContract = (TextView) view.findViewById(R.id.tv_select_contract);
        mBtnClickCommit = (Button) view.findViewById(R.id.btn_r_stock_return_commit);
        mTvSelectCode = (TextView) view.findViewById(R.id.tv_r_stock_code);
        mEdtInputNum = (EditText) view.findViewById(R.id.edt_stock_num);
        mTvShouldReturn = (TextView) view.findViewById(R.id.tv_r_return_stock_num);
        mTvMaxCanReturn = (TextView) view.findViewById(R.id.tv_r_return_stock_max_num);
        mTvClickAll = (TextView) view.findViewById(R.id.tv_r_return_stock_all);
        mTvNumAdd= (TextView) view.findViewById(R.id.ibt_add);
        mTvNumCut= (TextView) view.findViewById(R.id.ibt_subtract);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mResources = mActivity.getResources();
        mServices = new RStockReturnOrderStockServiceImpl(this);
        mController = new RStockReturnOrderStockController(this);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_CLICK, mBtnClickCommit, mController);
        registerListener(ListenerController.ON_CLICK, mTvSelectCode, mController);
        registerListener(ListenerController.ON_CLICK, mTvChooseContract, mController);
        registerListener(ListenerController.ON_CLICK, mTvClickAll, mController);
        registerListener(ListenerController.ON_CLICK, mTvNumAdd, mController);
        registerListener(ListenerController.ON_CLICK, mTvNumCut, mController);
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRadioGroup, mController);
    }

    @Override
    protected void initViews() {
        mTvChooseContract.setClickable(false);//只有选择完证券代码以后，才能点击
        mRLChooseStockList.setBackgroundResource(R.color.trade_color15);
        mServices.getHoldList();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 点击radioButton
     * @param checkId
     */
    public void onCheckChange(int checkId){
        //**************现券还券*****************
        if(checkId == R.id.rb_return_stock){
            mRLChooseStockList.setVisibility(View.GONE);
            mServices.setIsContractEntrust(false);
            mRbReturnStock.setTextColor(mResources.getColor(R.color.trade_color_white));
            mRbSelectContract.setTextColor(mResources.getColor(R.color.trade_color1));
            mTvShouldReturn.setText(mAllShouldReturn);
            //**************指定合约**************
        }else if(checkId == R.id.rb_select_contract){
            mRLChooseStockList.setVisibility(View.VISIBLE);
            mServices.setIsContractEntrust(true);
            mRbReturnStock.setTextColor(mResources.getColor(R.color.trade_color1));
            mRbSelectContract.setTextColor(mResources.getColor(R.color.trade_color_white));
            mTvShouldReturn.setText(mSelectShouldReturn);
        }
    }
    /**
     * 持仓列表数据表
     */
    public void getStoreData(ArrayList<MyStoreStockBean> dataList) {
        mLinLoading.setVisibility(View.GONE);
        if (dataList == null || dataList.size() == 0) {
            mTvSelectCode.setHint(R.string.r_select_not);
        } else {
            mTvSelectCode.setHint(R.string.one_key_hint_select);
            mHoldDataList = dataList;
        }
    }

    /**
     * 点击选择证券
     */
    public void onClickStockCode(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mHoldDataList != null && mHoldDataList.size() > 0) {
            Intent intent = new Intent(mActivity, RSelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select", mHoldDataList);
            intent.putExtra("useType", "stock_hold");
            startActivityForResult(intent, 47);
        }else {
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.r_select_not));
        }
    }

    /**
     * 得到合约数据集
     * @param datalist
     */
    public void getChooseContractData(ArrayList<RChooseContractBean> datalist) {
        mTvChooseContract.setClickable(true); //合约选择可以点击了
        mRLChooseStockList.setBackgroundResource(R.color.trade_color_white);
        if (datalist == null || datalist.size() == 0) {
            mTvChooseContract.setHint(mResources.getString(R.string.r_choose_no_contract));
            mContractDataList = new ArrayList<RChooseContractBean>();
        } else {
            mTvChooseContract.setHint(mResources.getString(R.string.r_choose_contract_title));
            mContractDataList = datalist;
        }
    }

    /**
     * 点击选择合约列表
     */
    public void onClickTvChooseContract() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mContractDataList == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.r_choose_contract_loading));
        }else if(mContractDataList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.r_choose_no_contract));
        }else {
            Intent intent = new Intent(getActivity(), RChooseContractActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(RChooseContractFragment.USE_TYPE, RChooseContractFragment.ANY_ONE);
            bundle.putParcelableArrayList(RChooseContractFragment.CONTRACT_DATA_LIST, mContractDataList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 13);
        }
    }
    /**
     * 合约选择完成后/证券选择完成后
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        if(resultCode == RSelectListFragment.RESULT_CODE){ //证券选完
            mTvChooseContract.setClickable(false); //合约不可以点击
            mRLChooseStockList.setBackgroundResource(R.color.trade_color15);
            mTvChooseContract.setHint(mResources.getString(R.string.r_choose_contract_loading));
            mTvChooseContract.setText("");
            int position = data.getIntExtra(RSelectListFragment.SELECT_RESULT,0);
            MyStoreStockBean bean = mHoldDataList.get(position);
            mStockCode = bean.getStock_code();
            mServices.requestChooseContract(mStockCode); //请求合约
            mServices.requestStockLink(mStockCode); //联动最大可还
            mTvSelectCode.setText(mStockCode + " " + bean.getStock_name());
        }else if(resultCode == RChooseContractFragment.RESULT_CODE_SAVE){ //合约选完
            ArrayList<RChooseContractBean> tempList = new ArrayList<RChooseContractBean>();
            mContractDataList = data.getParcelableArrayListExtra(RChooseContractFragment.RESULT_SAVE);
            if(mContractDataList != null && mContractDataList.size() > 0) {
                for (RChooseContractBean bean : mContractDataList) {
                    boolean isCheck = bean.isChecked();
                    if (isCheck) {
                        tempList.add(bean);
                    }
                }
            }
            if(tempList != null && tempList.size() > 0){
                mTvChooseContract.setText(mActivity.getResources().getString(R.string.contract_not21)+ tempList.size()+mActivity.getResources().getString(R.string.contract_not22));
                mSelectShouldReturn = resultData(tempList);
                mTvShouldReturn.setText(mSelectShouldReturn);
            }else{
                mTvChooseContract.setText("");
            }
        }
    }

    //累加未还数量
    private String resultData(ArrayList<RChooseContractBean> dataList){
        int result = 0;
        try {
            for (RChooseContractBean bean : dataList) {
                String money = bean.getReal_compact_amount();
                if (money != null && !TextUtils.isEmpty(money)) {
                    result += Integer.parseInt(money);
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return String.valueOf(result);
    }

    /**
     * 获得联动结果
     */
    public void onGetLinkResult(RStockToStockLinkBean data) {
        mStockLinkBean = data;
        mTvMaxCanReturn.setText(data.getEnable_amount());
        mTvShouldReturn.setText(data.getEnable_return_amount());
    }

    /**
     * 点击全部
     */
    public void onClickAll(){
        mEdtInputNum.setText(mTvShouldReturn.getText().toString());
    }

    /**
     * 数量加
     */
    public void onClickNumAdd(){
        String num = mEdtInputNum.getText().toString();
        try {
            if (!TextUtils.isEmpty(num)) {
                int intNum = Integer.parseInt(num);
                if (intNum != 0) {
                    mEdtInputNum.setText(String.valueOf(intNum + 100));
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    /**
     * 数量减
     */
    public void onClickNumCut(){
        String num = mEdtInputNum.getText().toString();
        try {
            if(!TextUtils.isEmpty(num)){
                int intNum = Integer.parseInt(num);
                if (intNum != 0 && intNum > 100) {
                    mEdtInputNum.setText(String.valueOf(intNum - 100));
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    /**
     * 提交委托
     */
    public void onClickCommit() {
        String entrustNum = mEdtInputNum.getText().toString().trim();
        if (TextUtils.isEmpty(mStockCode) || mStockLinkBean == null) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.r_property_list4));
            return;
        }
        if (TextUtils.isEmpty(entrustNum)) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_amount));
            return;
        }
        mServices.requestCommit(mStockLinkBean,entrustNum);
    }

    /**
     * 清空所有数据
     */
    public void clearAllData(){
        mStockCode = "";
        mStockLinkBean = null;
        mTvChooseContract.setText("");
        mTvChooseContract.setClickable(false); //合约不可以点击
        mRLChooseStockList.setBackgroundResource(R.color.trade_color15);
        mTvChooseContract.setHint(mResources.getString(R.string.r_choose_contract_loading));
        mTvSelectCode.setText("");
        mTvMaxCanReturn.setText("");
        mTvShouldReturn.setText("");
        mEdtInputNum.setText("");
    }

    public ArrayList<RChooseContractBean> getSelectContractList() {
        return mContractDataList;
    }
}

/**
 * 本类控制器
 */
class RStockReturnOrderStockController extends AbsBaseController
        implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    private RStockReturnOrderStockFragment mFragment;

    public RStockReturnOrderStockController(RStockReturnOrderStockFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_CHECK_CHANGE:
                ((RadioGroup)view).setOnCheckedChangeListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.btn_r_stock_return_commit) {
            mFragment.onClickCommit();
        } else if (resId == R.id.tv_select_contract) {
            mFragment.onClickTvChooseContract();
        } else if (resId == R.id.tv_r_stock_code) {
            mFragment.onClickStockCode();
        }else if (resId == R.id.tv_r_return_stock_all) {
            mFragment.onClickAll();
        }else if (resId == R.id.ibt_add) {
            mFragment.onClickNumAdd();
        }else if (resId == R.id.ibt_subtract) {
            mFragment.onClickNumCut();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragment.onCheckChange(checkedId);
    }
}