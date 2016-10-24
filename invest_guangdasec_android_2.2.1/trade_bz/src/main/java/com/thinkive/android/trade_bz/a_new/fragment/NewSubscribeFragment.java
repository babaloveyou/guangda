package com.thinkive.android.trade_bz.a_new.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.activity.NewStockMainActivity;
import com.thinkive.android.trade_bz.a_new.adapter.NewSubscribeAdapter;
import com.thinkive.android.trade_bz.a_new.bean.NewStockBean;
import com.thinkive.android.trade_bz.a_new.bean.NewSuscribeBean;
import com.thinkive.android.trade_bz.a_new.bll.NewSubscribeServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.dialog.NewStockConfirmDialog;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 新股-申购Fragment
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */
public class NewSubscribeFragment extends AbsBaseFragment {
    private NewStockMainActivity mActivity;
    private NewSubscribeServiceImpl mServices;
    private NewSubscribeController mController;
    private NewSubscribeAdapter mAdapter;
    /**
     * 申购额度：沪市
     */
    private TextView mTvSubscribeHuLimit;
    /**
     * 申购额度：深市
     */
    private TextView mTvSubscribeShenLimit;
    /**
     * 申购代码
     */
    private TextView mTvSubscribeCode;
    /**
     * 显示申购价格的文本，可能会改为输入框
     */
    private TextView mTvSubscribePrice;
    /**
     * 申购数量输入框
     */
    private EditText mEdSubscribeAmount;
    /**
     * 申购最大值
     */
    private TextView mTvSubscribeAmountMax;
    /**
     * “申购”按钮
     */
    private Button mBuSubscribe;
    /**
     * 新股列表
     */
    private ListView mLvNewStockList;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLlNoData;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLlLoading;
    /**
     * 申购单位，申购数量输入框中的输入内容必须是它的倍数
     */
    private String mSubscribeUnit = "";
    /**
     * 记录当前被用户选中的新股信息
     */
    private NewStockBean mCurNewStockBean;
    /**
     * 用户类型，用户是用普通账户还是融资融券账户进入的本类
     */
    private String mUserType = "";
    /**
     * 全局资源对象
     */
    private Resources mResources;
    /**
     * 今日新股数据集
     */
    private ArrayList<NewStockBean> mNewStockDataList;
    private String mShenLimit = "";
    private String mHuLimit = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_stock_subscribe, null);
        initData();
        findViews(rootView);
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    @Override
    protected void findViews(View view) {
        mTvSubscribeHuLimit = (TextView) view.findViewById(R.id.tv_new_subscribe_limit_hu);
        mTvSubscribeShenLimit = (TextView) view.findViewById(R.id.tv_new_subscribe_limit_shen);
        mTvSubscribeCode = (TextView) view.findViewById(R.id.tv_stock_code);
        mTvSubscribePrice = (TextView) view.findViewById(R.id.tv_subscribe_price);
        mEdSubscribeAmount = (EditText) view.findViewById(R.id.ed_subscribe_amount);
        mTvSubscribeAmountMax = (TextView) view.findViewById(R.id.tv_subscribe_amount_max);
        mBuSubscribe = (Button) view.findViewById(R.id.bu_subscribe);
        mLlLoading = (LinearLayout) view.findViewById(R.id.ll_fund_list_loading);
        mLlNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLvNewStockList = (ListView) view.findViewById(R.id.lv_new_stock_list);
        mLvNewStockList.setDivider(null);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mBuSubscribe, mController);
        registerListener(AbsBaseController.ON_ITEM_CLICK, mLvNewStockList, mController);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            mUserType = bundle.getString("userType");
        }
        mActivity = (NewStockMainActivity) getActivity();
        mResources = CoreApplication.getInstance().getResources();
        mServices = new NewSubscribeServiceImpl(this, mUserType);
        mController = new NewSubscribeController(this);
        mAdapter = new NewSubscribeAdapter(mActivity);
        mSubscribeUnit = "1";
        //请求可申购额度
        mServices.requestSubscribeLimit();
    }

    @Override
    protected void initViews() {
        mServices.requestTodayNew();
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 得到当前可申购的额度
     */
    public void onGetSubscribeLimitData(ArrayList<NewSuscribeBean> dataList) {
        for(NewSuscribeBean list : dataList){
            // 0：深A，
            if (list.getExchange_type() != null && list.getExchange_type().equals("0")) {
                mShenLimit = list.getEnable_amount();
                mTvSubscribeShenLimit.setText(mShenLimit+mResources.getString(R.string.trade_stock));
                // 2：沪A
            } else if (list.getExchange_type() != null && list.getExchange_type().equals("2")) {
                mHuLimit = list.getEnable_amount();
                mTvSubscribeHuLimit.setText(mHuLimit+mResources.getString(R.string.trade_stock));
            }
        }
    }

    /**
     * 获取今日新股列表
     */
    public void onGetTodayNew(ArrayList<NewStockBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLlLoading.setVisibility(View.GONE);
            mLlNoData.setVisibility(View.VISIBLE);
            mLvNewStockList.setVisibility(View.GONE);
            mTvSubscribeCode.setHint(mResources.getString(R.string.new_subscribe_amount_error3));
        } else if (dataList.size() > 0) {
            mNewStockDataList = dataList;
            mLlLoading.setVisibility(View.GONE);
            mLlNoData.setVisibility(View.GONE);
            mLvNewStockList.setVisibility(View.VISIBLE);
            mTvSubscribeCode.setHint(mResources.getString(R.string.new_subscribe_select_new));
            mAdapter.setListData(dataList);
            mLvNewStockList.setAdapter(mAdapter);
        }
    }

    /**
     * 点击下发列表
     * @param position
     */
    public void onItemClick(int position){
        mCurNewStockBean =  mNewStockDataList.get(position);
        mTvSubscribeCode.setText(mCurNewStockBean.getStock_code() + " " + mCurNewStockBean.getStock_name());
        mTvSubscribePrice.setText(mCurNewStockBean.getIssue_price());
        mSubscribeUnit = mCurNewStockBean.getApplyunitonline();
        //设置最大可用量
        forMateMaxNum();
    }

    /**
     * 最大可申购数量不是由联动决定的。
     * 而是取上限与额度的
     */
    private void forMateMaxNum(){
        int mShenLimit = getShenLimit();
        int mHuLimit = getHuLimit();
        int upLimit = 0;
        try {
            if(!TextUtils.isEmpty(mCurNewStockBean.getApplymax_online())) {
                String exchangeType = mCurNewStockBean.getExchange_type();
                upLimit = Integer.parseInt(mCurNewStockBean.getApplymax_online());
                if (exchangeType.equals("0") || exchangeType.equals("1")) {     //深市
                    if (mShenLimit > upLimit) {
                        mTvSubscribeAmountMax.setText(String.valueOf(upLimit));
                    } else {
                        mTvSubscribeAmountMax.setText(String.valueOf(mShenLimit));
                    }
                } else if (exchangeType.equals("2") || exchangeType.equals("3")) { //沪市
                    if (mHuLimit > upLimit) {
                        mTvSubscribeAmountMax.setText(String.valueOf(upLimit));
                    } else {
                        mTvSubscribeAmountMax.setText(String.valueOf(mHuLimit));
                    }
                }
            }else{
                mTvSubscribeAmountMax.setText("");
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    /**
     * 点击申购按钮
     * 1.单击按钮进行申购前，必须先选择一直新股去申购
     * 2.输入的申购数量必须是申购单位的整数倍
     */
    public void onClickBtnSubscribe() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        if (mCurNewStockBean == null || TextUtils.isEmpty(mTvSubscribeCode.getText())) { // 如果用户还没有选择一直新股
            ToastUtils.toast(mActivity, mResources.getString(R.string.new_subscribe_select_stock_first));
            return;
        }
        if (!checkAmountInput()) { // 如果输入的申购数量不是申购单位的整数倍
            ToastUtils.toast(mActivity, mResources.getString(R.string.new_subscribe_amount_error1)
                    + mSubscribeUnit + mResources.getString(R.string.new_subscribe_amount_error2));
            return;
        }
        if(mCurNewStockBean != null){
            NewStockConfirmDialog dialog = new NewStockConfirmDialog(mActivity,mServices);
            dialog.setDataToViews(mCurNewStockBean,getEdSubscribeAmountInput());
            dialog.show();
        }
    }

    /**
     * 得到申购委托结果
     */
    public void getEnturstResult(String strResult) {
        ToastUtils.toast(mActivity, strResult);
        //申购成功后，清除页面数据
        clearDataForView();
    }

    /**
     * 当联动或申购失败时清除界面数据
     */
    public void clearDataForView(){
        mTvSubscribeAmountMax.setText("");
        mEdSubscribeAmount.setText("");
        mTvSubscribePrice.setText("");
        mTvSubscribeCode.setText("");
        mEdSubscribeAmount.setText("");
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    /**
     * {@link #mEdSubscribeAmount} 输入合法性检测
     * 所输入内容必须是{@link #mSubscribeUnit}的整数倍
     */
    public boolean checkAmountInput() {
        boolean result = false;
        String inputContent = getEdSubscribeAmountInput();
        int inputNum = 0;
        try {
            int subscribeUnitInt = Integer.parseInt(mSubscribeUnit);
            inputNum = Integer.parseInt(inputContent);
            result = inputNum % subscribeUnitInt == 0;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return result;
    }
    /**
     * 获取{@link #mEdSubscribeAmount}中输入的内容
     */
    private String getEdSubscribeAmountInput() {
        return  mEdSubscribeAmount.getText().toString().trim();
    }

    public String getUserType() {
        return mUserType;
    }

    private int getShenLimit(){
        int intResult = 0;
        try {
            if (!TextUtils.isEmpty(mShenLimit)) {
                int num = Integer.parseInt(mShenLimit);
                if (num != 0) {
                    intResult = num;
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return intResult;
    }

    private int getHuLimit(){
        int intResult = 0;
        try {
            if (!TextUtils.isEmpty(mHuLimit)) {
                int num = Integer.parseInt(mHuLimit);
                if (num != 0) {
                    intResult = num;
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return intResult;
    }
}

/**
 * 申购控制类
 */
class NewSubscribeController extends AbsBaseController implements
        View.OnClickListener,ListView.OnItemClickListener{
    private NewSubscribeFragment mFragment;

    public NewSubscribeController(NewSubscribeFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_ITEM_CLICK:
                ((ListView) view).setOnItemClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.bu_subscribe) { // 申购按钮单击事件
            mFragment.onClickBtnSubscribe();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragment.onItemClick(position);
    }
}
