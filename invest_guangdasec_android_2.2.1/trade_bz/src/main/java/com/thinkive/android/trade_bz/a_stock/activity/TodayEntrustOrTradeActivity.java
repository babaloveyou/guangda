package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;
import com.thinkive.android.trade_bz.a_stock.fragment.TodayEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.TodayTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.request.Request306001;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * 今日成交、今日委托所在的Activity
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/3
 */
public class TodayEntrustOrTradeActivity extends AbsNavBarActivity {
    private RadioButtonFragment2 mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        initData();
        initViews();
        processRequst360001();
    }

    private void processRequst360001() {
        HashMap<String, String> map306001 = new HashMap<String, String>();
        new Request306001(map306001, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                String date = bundle.getString(Request306001.BUNDLE_KEY_306001);
                Constants.TODAY_DATE = date;
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request306001.ERROR_INFO));
            }
        }).request();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        //隐藏Activity的标题栏
        setTitleBarVisible(View.GONE);
    }

    @Override
    protected void initData() {
        TodayEntrustFragment fragment1 = new TodayEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.today_entrust));

        TodayTradeFragment fragment2 = new TodayTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.today_trade));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
        mFragment.setArguments(getIntent().getExtras());
    }

}
