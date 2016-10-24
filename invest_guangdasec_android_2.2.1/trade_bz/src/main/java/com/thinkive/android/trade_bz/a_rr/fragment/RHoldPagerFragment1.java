package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSecuritiesMarginActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_stock.activity.TransferBanktActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.StringUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 融资融券持仓头部的第一个fragment
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/3/29
 */
public class RHoldPagerFragment1 extends AbsBaseFragment {
    private RSecuritiesMarginActivity mActivity;
    private RPagerHoldViewController mPagerHoldViewController;
    /**
     * 总资产
     */
    private TextView mTvAllMoney;
    private TextView mTvAllMoneya;
    /**
     * 维持担保比例
     */
    private TextView mTvprofitPer;
    private TextView mTvprofitPera;
    /**
     * 冲抵争取拿市值
     */
    private TextView mTvAllMarketValue;
    private TextView mTvAllMarketValuea;
    /**
     * 可用保证金
     */
    private TextView mTvCanUse;
    private TextView mTvCanUsea;
    /**
     * 现金
     */
    private TextView mTvCash;
    private TextView mTvCasha;
    /**
     * 银行转账按钮
     */
    private TextView mTvBlankMoney;
    /**
     * 设置字体
     */
    private FontManager mFontManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_head_pager1, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvAllMoney = (TextView) view.findViewById(R.id.tv_r_pager_all);
        mTvCanUse = (TextView) view.findViewById(R.id.tv_r_pager_can);
        mTvprofitPer = (TextView) view.findViewById(R.id.tv_r_pager_per);
        mTvCash = (TextView) view.findViewById(R.id.tv_r_pager_cash);
        mTvAllMarketValue = (TextView) view.findViewById(R.id.tv_r_pager_market);

        mTvAllMoneya = (TextView) view.findViewById(R.id.tv_r_pager_alla);
        mTvCanUsea = (TextView) view.findViewById(R.id.tv_r_pager_cana);
        mTvprofitPera = (TextView) view.findViewById(R.id.tv_r_pager_pera);
        mTvCasha = (TextView) view.findViewById(R.id.tv_r_pager_casha);
        mTvAllMarketValuea = (TextView) view.findViewById(R.id.tv_r_pager_marketa);
        mTvBlankMoney = (TextView) view.findViewById(R.id.tv_r_pager_click);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBlankMoney, mPagerHoldViewController);
    }

    @Override
    protected void initData() {
        mActivity = (RSecuritiesMarginActivity) getActivity();
        mPagerHoldViewController = new RPagerHoldViewController(this);
        mFontManager = FontManager.getInstance(mActivity);
    }

    @Override
    protected void initViews() {
        mFontManager.setTextFont(mTvAllMoney, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvAllMoneya, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCanUse, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCanUsea, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvprofitPer, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvprofitPera, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCash, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvCasha, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvAllMarketValue, FontManager.FONT_DINPRO_BOLD);
        mFontManager.setTextFont(mTvAllMarketValuea, FontManager.FONT_DINPRO_BOLD);
        setTheme();
    }
    @Override
    protected void setTheme() {

    }
    /**
     * 接收业务类传递过来的数据,并设置
     */
    public void getMoneyAccountData(RSelectPropertBean bean) {
        try {
            mTvAllMoney.setText(StringUtils.subStringBefor(bean.getAssert_val()));
            mTvAllMoneya.setText(StringUtils.subStringAfter(bean.getAssert_val()));

            mTvprofitPer.setText(StringUtils.subStringBefor(bean.getPer_assurescale_value()));
            mTvprofitPera.setText(StringUtils.subStringAfter(bean.getPer_assurescale_value()));

            mTvAllMarketValue.setText(StringUtils.subStringBefor(bean.getMarket_value()));
            mTvAllMarketValuea.setText(StringUtils.subStringAfter(bean.getMarket_value()));

            mTvCanUse.setText(StringUtils.subStringBefor(bean.getEnable_bail_balance()));
            mTvCanUsea.setText(StringUtils.subStringAfter(bean.getEnable_bail_balance()));

            mTvCash.setText(StringUtils.subStringBefor(bean.getFund_asset()));
            mTvCasha.setText(StringUtils.subStringAfter(bean.getFund_asset()));

        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }


    /**
     * 点击银证转账按钮所执行的操作
     */
    public void onClickBank() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("userType", TradeLoginManager.LOGIN_TYPE_CREDIT);
        Intent intent = new Intent(mActivity, TransferBanktActivity.class);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
}

/**
 * HoldPagerFragment的控制类
 */
class RPagerHoldViewController extends AbsBaseController implements View.OnClickListener {

    private RHoldPagerFragment1 mHoldPagerFragment;

    public RPagerHoldViewController(RHoldPagerFragment1 mFragment) {
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
        if (resId == R.id.tv_r_pager_click) {
            mHoldPagerFragment.onClickBank();
        }
    }
}

