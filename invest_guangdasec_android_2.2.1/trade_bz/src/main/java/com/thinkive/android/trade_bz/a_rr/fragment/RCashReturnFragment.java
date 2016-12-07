package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.CreditTradnsferActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCashReturnActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCashReturnServiceImpl;
import com.thinkive.android.trade_bz.a_stock.activity.TradeLoginActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.dialog.CashReturnConfirmDialog;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.EditUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.ClearEditText;

import java.util.ArrayList;

/**
 * 融资融券--现金还款(直接还款)
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/20
 */

public class RCashReturnFragment extends AbsBaseFragment {
    private RCashReturnActivity mActivity;
    private RCashReturnServiceImpl mServices;
    private RCashReturnController mController;
    private Resources mResources;
    private TextView mTvCanUseCash;
    private TextView mTvOtherCash;
    private TextView mTvReturnAllClick;
    private ClearEditText mEdtInputNum;
    private Button mBtnCommit;
    private LinearLayout mLinLoading;
    /**
     * 合约结果集
     */
    private ArrayList<RChooseContractBean> mContractDataList;
    private String mAllNeedReturnMoney = "";
    private String mSelectNeedReturnMoney = "";
    private TextView mToBankTv;
    private RStockToStockLinkBean mRStockToStockLinkBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_cash_return, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mTvCanUseCash = (TextView) view.findViewById(R.id.tv_r_cash_can_use);
        mTvOtherCash = (TextView) view.findViewById(R.id.tv_r_cash_other);
        mTvReturnAllClick = (TextView) view.findViewById(R.id.tv_r_return_all);
        mEdtInputNum = (ClearEditText) view.findViewById(R.id.edt_r_cash_num);
        mBtnCommit = (Button) view.findViewById(R.id.btn_r_cash_commit);
        mToBankTv = (TextView) view.findViewById(R.id.tv_to_stock_bank);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnCommit, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvReturnAllClick, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mToBankTv, mController);
        mEdtInputNum.addTextChangedListener(new com.thinkive.android.trade_bz.utils.MyTextWatcher() {
            @Override
            public void doAfterChange(Editable s) {
                if (!TextUtils.isEmpty(mEdtInputNum.getText())) {
                    mEdtInputNum.setClearIconVisible(true);
                    mEdtInputNum.invalidate();
                } else {
                    mEdtInputNum.setClearIconVisible(false);
                    mEdtInputNum.invalidate();
                }
            }
        });
    }

    @Override
    protected void initData() {
        mActivity = (RCashReturnActivity) getActivity();
        mResources = mActivity.getResources();
        mServices = new RCashReturnServiceImpl(this);
        mController = new RCashReturnController(this);
        mServices.requestStockLink();
    }

    @Override
    protected void initViews() {
        //        mServices.requestChooseContract("");//请求合约
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 获得可用金额和需还金额
     */
    public void onGetCanUseMoney(RStockToStockLinkBean data) {
        mLinLoading.setVisibility(View.GONE);
        mRStockToStockLinkBean = data;
        mTvCanUseCash.setText(data.getFin_enrepaid_balance());
        mAllNeedReturnMoney = data.getReal_compact_balance();
        if (!mServices.isIsContractEntrust()) {
            mTvOtherCash.setText(mAllNeedReturnMoney);
        }
    }


    private String resultData(ArrayList<RChooseContractBean> dataList) {
        double result = 0;
        try {
            for (RChooseContractBean bean : dataList) {
                String money = bean.getReal_compact_balance();
                if (money != null && !TextUtils.isEmpty(money)) {
                    result += Double.parseDouble(money);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return TradeUtils.formatDouble2(result);
    }

    /**
     * 点击全部按钮
     */
    public void onClickTvAll() {
        mEdtInputNum.setText(mTvOtherCash.getText().toString());
        EditUtils.setEdtCursor(mEdtInputNum);
    }

    /*
    * 跳转银证转账
    * */
    public void onClickToBank() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, CreditTradnsferActivity.class);
            mActivity.startActivity(intent);
        } else {
            Intent intent = new Intent(mActivity, TradeLoginActivity.class);
            intent.putExtra(Constants.LOGIN_TYPE, TradeLoginManager.LOGIN_TYPE_CREDIT);
            mActivity.startActivity(intent);
        }
    }

    /**
     * 点击提交按钮
     */
    public void onClickBtnCommit() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String entrustNum = mEdtInputNum.getText().toString().trim();
        if (TextUtils.isEmpty(entrustNum)) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.r_property_list6));
        } else {
            CashReturnConfirmDialog dialog = new CashReturnConfirmDialog(getActivity(), mServices);
            dialog.setDataBean(mRStockToStockLinkBean);
            dialog.setBalance(entrustNum);
            dialog.setDataToViews();
            dialog.show();
        }
    }

    /**
     * 清空界面的所有数据
     */
    public void clearAllData() {
        mEdtInputNum.setText("");
        mTvCanUseCash.setText("");
        mTvOtherCash.setText("");
        mContractDataList = null;
        mSelectNeedReturnMoney = "";
        mAllNeedReturnMoney = "";
    }

    public ArrayList<RChooseContractBean> getSelectContractList() {
        return mContractDataList;
    }

    public void onSuccessEntrust(String string) {
        MessageOkCancelDialog dialog = new MessageOkCancelDialog(mActivity, null);
        dialog.setMsgText(string);
        dialog.setCancelBtnVisiable(false);
        dialog.show();
    }
}

/**
 * 本类控制器
 */
class RCashReturnController extends AbsBaseController implements
        View.OnClickListener {

    private RCashReturnFragment mFragment;

    public RCashReturnController(RCashReturnFragment Fragment) {
        mFragment = Fragment;
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
        if (resId == R.id.btn_r_cash_commit) {
            mFragment.onClickBtnCommit();
        } else if (resId == R.id.tv_r_return_all) {
            mFragment.onClickTvAll();
        } else if (resId == R.id.tv_to_stock_bank) {
            mFragment.onClickToBank();
        }
    }


}