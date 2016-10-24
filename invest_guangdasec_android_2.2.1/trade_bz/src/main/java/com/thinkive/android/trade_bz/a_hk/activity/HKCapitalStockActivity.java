package com.thinkive.android.trade_bz.a_hk.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalCombinedFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalCompanyMsgFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalCorporateFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalDeliveryFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalDifferenceFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalExchangeFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalFundFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalLiabilitiesFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalLimitFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalMarketDayFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalNoTradeFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalNotificationFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalSecutitieseFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalStatementFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalStockFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalTrustFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCapitalVoteMsgFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

/**
 * 创建者     舒旺
 * 创建时间   2016/5/27 17:58
 * 描述	      港股 查询的Activity  所有Fragment都通用此Activity
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalStockActivity extends AbsNavBarActivity {

    private AbsBaseFragment mFragment;
    private String mHkfragment;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //点击不同的Activity传递不同的标题以及区分不同Fragment
        Bundle bundle = getIntent().getExtras();
        mActivityTitle = bundle.getString("title");
        mHkfragment = bundle.getString("hkfragment");

        initViews();
        initData();

    }

    @Override
    protected void initData() {
        super.initData();

        if (!TextUtils.isEmpty(mActivityTitle) && null != mActivityTitle) {
            setTitleText(mActivityTitle);
        }

        setBackBtnVisibility(View.VISIBLE);
        replaceFragment(R.id.fl_container, mFragment);

    }

    @Override
    protected void initViews() {
        super.initViews();

        if ("hk_0".equals(mHkfragment)) {  //资金股份查询

            mFragment = new HKCapitalStockFragment();

        } else if ("hk_1".equals(mHkfragment)) {  //未交明细查询

            mFragment = new HKCapitalNoTradeFragment();

        } else if ("hk_2".equals(mHkfragment)) {  //标的证券查询

            mFragment = new HKCapitalSecutitieseFragment();

        } else if ("hk_3".equals(mHkfragment)) {  //资金明细查询

            mFragment = new HKCapitalFundFragment();

        } else if ("hk_4".equals(mHkfragment)) {  //组合费查询

            mFragment = new HKCapitalCombinedFragment();

        } else if ("hk_5".equals(mHkfragment)) {  //负债查询

            mFragment = new HKCapitalLiabilitiesFragment();

        } else if ("hk_6".equals(mHkfragment)) {  //差价查询

            mFragment = new HKCapitalDifferenceFragment();

        } else if ("hk_7".equals(mHkfragment)) {  //汇率查询

            mFragment = new HKCapitalExchangeFragment();

        } else if ("hk_8".equals(mHkfragment)) {  //额度查询

            mFragment = new HKCapitalLimitFragment();

        } else if ("hk_9".equals(mHkfragment)) {  //投票委托查询

            mFragment = new HKCapitalTrustFragment();

        } else if ("hk_10".equals(mHkfragment)) {  //公司行为委托查询

            mFragment = new HKCapitalCorporateFragment();

        } else if ("hk_11".equals(mHkfragment)) {  //对账单查询

            mFragment = new HKCapitalStatementFragment();

        } else if ("hk_12".equals(mHkfragment)) {  //交割单查询

            mFragment = new HKCapitalDeliveryFragment();

        } else if ("hk_13".equals(mHkfragment)) {  //通知信息查询

            mFragment = new HKCapitalNotificationFragment();

        } else if ("hk_14".equals(mHkfragment)) {  //港股通交易日历查询

            mFragment = new HKCapitalMarketDayFragment();
        } else if ("hk_15".equals(mHkfragment)) {  //投票信息查询

            mFragment = new HKCapitalVoteMsgFragment();
        } else if ("hk_16".equals(mHkfragment)) {  //公司行为信息查询

            mFragment = new HKCapitalCompanyMsgFragment();
        }
    }
}
