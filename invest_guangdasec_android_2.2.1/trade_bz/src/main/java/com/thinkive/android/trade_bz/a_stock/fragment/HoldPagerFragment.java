package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.NormalTradnsferActivity;
import com.thinkive.android.trade_bz.a_stock.activity.TradeLoginActivity;
import com.thinkive.android.trade_bz.a_stock.bean.MoneyBean;
import com.thinkive.android.trade_bz.a_stock.bll.MyholdPagerServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import static com.thinkive.android.trade_bz.R.color.trade_down_green;
import static com.thinkive.android.trade_bz.R.color.trade_text;
import static com.thinkive.android.trade_bz.R.color.trade_text_color3;
import static com.thinkive.android.trade_bz.R.color.trade_text_color9;

/**
 * 我的持仓 头部资金账户（货币信息）
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/29
 */
public class HoldPagerFragment extends AbsBaseFragment {
    /**
     * 头部pager的业务类
     */
    private MyholdPagerServicesImpl mServices;
    /**
     * 关联的Activity
     */
    private MultiTradeActivity mActivity;
    private boolean mIsVisibleToUser = false;
    /**
     * 控制器
     */
    private PagerHoldViewController mPagerHoldViewController;

    /**
     * 动态设置头部的 代号
     */
    private TextView mTvMoneyNumber;
    private int mResIdMoneyNumId;
    private int mResIdImgIcon;
    /**
     * 总资产
     */
    private TextView mTvAllMoney;
    /**
     * 可用
     */
    private TextView mTvCanUse;
    private boolean isPrepare = false;

    /**
     * 银行转账按钮
     */
    private TextView mTvBlankMoney;

    private int page;
    private ImageView mIvIcon;
    private TextView mTvReferPofit;
    private TextView mTvTodayReferPofit;
    private TextView mTvFundAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_head_pager, null);
        findViews(rootView);
        initData();

        initViews();
        isPrepare = true;
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListeners();
    }

    @Override
    protected void findViews(View view) {
        mTvAllMoney = (TextView) view.findViewById(R.id.tv_myhold_money);
        mTvMoneyNumber = (TextView) view.findViewById(R.id.tv_monetary_unit);
        mIvIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTvBlankMoney = (TextView) view.findViewById(R.id.tv_holdpager_bank);
        mTvFundAccount = (TextView) view.findViewById(R.id.tv_fund_account);

        mTvCanUse = (TextView) view.findViewById(R.id.tv_myhold_canuse);
        mTvReferPofit = (TextView) view.findViewById(R.id.tv_refer_pofit);
        mTvTodayReferPofit = (TextView) view.findViewById(R.id.tv_today_refer_profit);

    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBlankMoney, mPagerHoldViewController);

        mTvCanUse.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void doAfterChange(Editable s) {
                if (mTvCanUse.getText().toString().startsWith(getResources().getString(R.string.common_emp_text))) {
                    mTvCanUse.setTextColor(getResources().getColor(trade_text_color9));
                } else {
                    mTvCanUse.setTextColor(getResources().getColor(trade_text_color3));
                }
            }
        });
        mTvReferPofit.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void doAfterChange(Editable s) {
                if (mTvReferPofit.getText().toString().startsWith(getResources().getString(R.string.common_emp_text)) || "0.000".equals(mTvReferPofit.getText().toString()) || "0.00".equals(mTvReferPofit.getText().toString())) {
                    mTvReferPofit.setTextColor(getResources().getColor(trade_text_color9));
                } else if (mTvReferPofit.getText().toString().startsWith("-")) {
                    mTvReferPofit.setTextColor(getResources().getColor(trade_down_green));
                } else {
                    mTvReferPofit.setTextColor(getResources().getColor(trade_text));
                }

            }
        });
        mTvTodayReferPofit.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void doAfterChange(Editable s) {
                if (mTvTodayReferPofit.getText().toString().startsWith(getResources().getString(R.string.common_emp_text)) || "0.000".equals(mTvTodayReferPofit.getText().toString()) || "0.00".equals(mTvTodayReferPofit.getText().toString())) {
                    mTvTodayReferPofit.setTextColor(getResources().getColor(trade_text_color9));
                } else if (mTvTodayReferPofit.getText().toString().startsWith("-")) {
                    mTvTodayReferPofit.setTextColor(getResources().getColor(trade_down_green));
                } else {
                    mTvTodayReferPofit.setTextColor(getResources().getColor(trade_text));
                }
            }
        });


    }

    @Override
    protected void initData() {
        mActivity = (MultiTradeActivity) getActivity();
        mPagerHoldViewController = new PagerHoldViewController(this);
        Bundle bundle = getArguments();
        page = bundle.getInt("page");
        if (page != 0) {
            page = (page + 1) % 2 == 0 ? (page + 1) : (page + 1) % 2;
        }
        mServices = new MyholdPagerServicesImpl(this);
    }

    @Override
    protected void initViews() {
        mTvMoneyNumber.setText(mResIdMoneyNumId);
        mIvIcon.setImageResource(mResIdImgIcon);
        if (mIsVisibleToUser) {
            mServices.requestMyHoldPager(page);
        }
        setTheme();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        mIsVisibleToUser = isVisibleToUser;
        if (mIsVisibleToUser && isPrepare && mServices != null) {
            mServices.requestMyHoldPager(page);
        }
    }


    /**
     * 从业务类中获得货币类型数据信息
     */
    public void getMoneyData() {
        if (mServices == null) {
            mServices = new MyholdPagerServicesImpl(this);
        }
        mServices.requestMyHoldPager(page);
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 设置通用的头部信息
     */
    public void setOriginalViews(int moneyNumId, int iconId) {
        mResIdMoneyNumId = moneyNumId;
        mResIdImgIcon = iconId;
    }

    /**
     * 接收业务类传递过来的数据,并设置
     */
    public void getMoneyAccountData(MoneyBean bean) {
        String assert_val = bean.getAssert_val();
        String balance = bean.getEnable_balance();
        String z_float_yk = bean.getZ_float_yk();
        String dayfloat_yk = bean.getDayfloat_yk();
        mTvFundAccount.setText("资金账号:" + TradeLoginManager.sNormalLoginAccount + " -  ");
        //        mTvAllMoney.setText(formateString(true, assert_val));
        //        mTvCanUse.setText(formateString(false, balance));
        //        mTvReferPofit.setText(formateString(false, z_float_yk));
        //        mTvTodayReferPofit.setText(formateString(false, dayfloat_yk));
        mTvAllMoney.setText(assert_val);
        mTvCanUse.setText(balance);
        mTvReferPofit.setText(z_float_yk);
        mTvTodayReferPofit.setText(dayfloat_yk);
    }

    private String formateString(boolean isBig, String s) {
        if (TextUtils.isEmpty(s)) {
            s = "--";
        } else {
            if ("0.00".equals(s) || "0.000".equals(s)) {
                //                s = "--";
            }
        }
        if ("--".equals(s)) {
            if (isBig) {
                return "    " + s;
            } else {
                return s;
            }
        } else {
            if (!s.startsWith("-")) {
                return TradeUtils.formatDouble2(s);
            } else {
                int i = s.lastIndexOf(".");
                return s.substring(0, i + 3);
            }
        }
    }


    /**
     * 点击银证转账按钮所执行的操作
     * 使用普通账户进入模块
     */
    public void onClickBank() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, NormalTradnsferActivity.class);
            mActivity.startActivity(intent);
        } else {
            startLogin(TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }


    private void startLogin(String loginType) {
        Intent intent = new Intent(mActivity, TradeLoginActivity.class);
        intent.putExtra(Constants.LOGIN_TYPE, loginType);
        mActivity.startActivity(intent);
    }
}

abstract class MyTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        doAfterChange(s);
    }

    public abstract void doAfterChange(Editable s);
}

/**
 * HoldPagerFragment的控制类
 */
class PagerHoldViewController extends AbsBaseController implements View.OnClickListener {

    private HoldPagerFragment mHoldPagerFragment;

    public PagerHoldViewController(HoldPagerFragment mFragment) {
        mHoldPagerFragment = mFragment;
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
        if (resId == R.id.tv_holdpager_bank) {
            mHoldPagerFragment.onClickBank();
        }
    }


}