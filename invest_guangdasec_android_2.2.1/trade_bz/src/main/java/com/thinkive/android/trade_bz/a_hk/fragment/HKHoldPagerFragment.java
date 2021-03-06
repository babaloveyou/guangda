package com.thinkive.android.trade_bz.a_hk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.activity.HKMultiTradeActivity;
import com.thinkive.android.trade_bz.a_hk.bll.HKMyholdPagerServicesImpl;
import com.thinkive.android.trade_bz.a_stock.activity.TransferBanktActivity;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.StringUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 港股通-我的持仓 头部资金账户（货币信息）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/29
 */
public class HKHoldPagerFragment extends AbsBaseFragment {
    private HKMyholdPagerServicesImpl mServices;
    private HKMultiTradeActivity mActivity;
    private HKPagerHoldViewController mHKPagerHoldViewController;
    /**
     * 动态设置头部的 钱名
     */
    private TextView mTvMoneyName;
    private int mResIdMoneyTypeText;
    /**
     * 动态设置头部的 代号
     */
    private TextView mTvMoneyNumber;
    private int mResIdMoneyNumId;
    /**
     * 总资产
     */
    private TextView mTvAllMoney;
    private TextView mTvAllMoneya;
    /**
     * 总盈亏
     */
    private TextView mTvAllPorfit;
    private TextView mTvAllPorfita;
    /**
     * 今日盈亏
     */
    private TextView mTvTodayProfit;
    private TextView mTvTodayProfita;
    /**
     * 可用
     */
    private TextView mTvCanUse;
    private TextView mTvCanUsea;
    /**
     * 可取
     */
    private TextView mTvCanGet;
    private TextView mTvCanGeta;
    /**
     * 银行转账按钮
     */
    private TextView mTvBlankMoney;
    /**
     * 设置字体
     */
    private FontManager mFontManager;
    /**
     * 资金类型
     */
    private int mMoneyType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hk_head_pager, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvMoneyName = (TextView) view.findViewById(R.id.tv_money_unit);
        mTvMoneyNumber = (TextView) view.findViewById(R.id.tv_money_number);
        mTvAllMoney = (TextView) view.findViewById(R.id.tv_myhold_money);
        mTvAllPorfit = (TextView) view.findViewById(R.id.tv_myhold_port);
        mTvTodayProfit = (TextView) view.findViewById(R.id.tv_myhold_value);
        mTvCanUse = (TextView) view.findViewById(R.id.tv_myhold_canuse);
        mTvCanGet = (TextView) view.findViewById(R.id.tv_myhold_canget);

        mTvAllMoneya = (TextView) view.findViewById(R.id.tv_myhold_moneya);
        mTvAllPorfita = (TextView) view.findViewById(R.id.tv_myhold_porta);
        mTvTodayProfita = (TextView) view.findViewById(R.id.tv_myhold_valuea);
        mTvCanUsea = (TextView) view.findViewById(R.id.tv_myhold_canusea);
        mTvCanGeta = (TextView) view.findViewById(R.id.tv_myhold_cangeta);
        mTvBlankMoney = (TextView) view.findViewById(R.id.tv_holdpager_bank);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBlankMoney, mHKPagerHoldViewController);
    }

    @Override
    protected void initData() {
        mActivity = (HKMultiTradeActivity) getActivity();
        mHKPagerHoldViewController = new HKPagerHoldViewController(this);
        mFontManager = FontManager.getInstance(mActivity);
        Bundle bundle = getArguments();
        if(bundle != null) {
            mMoneyType = bundle.getInt("moneyType");
        }
        mServices = new HKMyholdPagerServicesImpl(this);
    }

    @Override
    protected void initViews() {
        mTvMoneyName.setText(mResIdMoneyTypeText);
        mTvMoneyNumber.setText(mResIdMoneyNumId);
        setTheme();
        mServices.requestMyHoldPager(mMoneyType);
        mFontManager.setTextFont(mTvAllMoney, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvAllMoneya, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCanUse, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCanUsea, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvAllPorfit, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvAllPorfita, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCanGet, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCanGeta, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvTodayProfit, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvTodayProfita, FontManager.FONT_DINPRO_BOLD);
    }

    /**
     * 从业务类中获得货币类型数据信息
     */
    public void getMoneyData() {
        if (mServices == null) {
            mServices = new HKMyholdPagerServicesImpl(this);
        }
        mServices.requestMyHoldPager(mMoneyType);
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 设置通用的头部信息
     */
    public void setOriginalViews(int moneyNumId, int moneyTypeTextId) {
        mResIdMoneyNumId = moneyNumId;
        mResIdMoneyTypeText = moneyTypeTextId;
    }

    /**
     * 接收业务类传递过来的数据,并设置
     */
    public void getMoneyAccountData(MoneySelectBean bean) {
        try {
            mTvAllMoney.setText(StringUtils.subStringBefor(bean.getAssert_val()));
            mTvAllMoneya.setText(StringUtils.subStringAfter(bean.getAssert_val()));

            mTvTodayProfit.setText(StringUtils.subStringBefor(bean.getTotal_income_balance()));
            mTvTodayProfita.setText(StringUtils.subStringAfter(bean.getTotal_income_balance()));

            mTvAllPorfit.setText(StringUtils.subStringBefor(bean.getDaily_income_balance()));
            mTvAllPorfita.setText(StringUtils.subStringAfter(bean.getDaily_income_balance()));

            mTvCanUse.setText(StringUtils.subStringBefor(bean.getEnable_balance()));
            mTvCanUsea.setText(StringUtils.subStringAfter(bean.getEnable_balance()));

            mTvCanGet.setText(StringUtils.subStringBefor(bean.getFetch_balance()));
            mTvCanGeta.setText(StringUtils.subStringAfter(bean.getFetch_balance()));

        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    /**
     * 点击银证转账按钮所执行的操作
     */
    public void onClickBank() {
        // 使用普通账户进入模块
        if (TradeUtils.isFastClick()) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("userType", TradeLoginManager.LOGIN_TYPE_NORMAL);
        intent.setClass(mActivity, TransferBanktActivity.class);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
}

/**
 * 控制类
 */
class HKPagerHoldViewController extends AbsBaseController implements View.OnClickListener {

    private HKHoldPagerFragment mHKHoldPagerFragment;

    public HKPagerHoldViewController(HKHoldPagerFragment mFragment) {
        mHKHoldPagerFragment = mFragment;
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
            mHKHoldPagerFragment.onClickBank();
        }
    }
}