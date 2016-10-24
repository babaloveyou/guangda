package com.thinkive.android.trade_bz.a_new.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.activity.NewGiveUpActivity;
import com.thinkive.android.trade_bz.a_new.activity.NewGiveUpRevocationActivity;
import com.thinkive.android.trade_bz.a_new.activity.NewHistoryListActivity;
import com.thinkive.android.trade_bz.a_new.activity.NewOneKeySubActivity;
import com.thinkive.android.trade_bz.a_new.activity.NewSubSelectActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

/**
 * 新股申购更多
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/21
 */

public class NewStockMoreFragment extends AbsBaseFragment {
    /**
     * Fragment的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     * 该类的控制器
     */
    private NewStockMoreController mController;
    /**
     * 一键申购
     */
    private TextView mTvOneKeySub;
    /**
     * 申报记录查询
     */
    private TextView mTvSubSelect;
    /**
     * 放弃认购申报
     */
    private TextView mTvGiveUpSub;
    /**
     * 放弃认购申报撤单
     */
    private TextView mTvGiveUpSubRevocation;
    /**
     * 历史缴费纪录查询
     */
    private TextView mTvHistoryList;
    /**
     * 使用的用户类型
     */
    private String mUserType = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_more, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }


    @Override
    protected void findViews(View view) {
        mTvOneKeySub = (TextView) view.findViewById(R.id.tv_one_key_sub);
        mTvSubSelect = (TextView) view.findViewById(R.id.tv_sub_select);
        mTvGiveUpSub = (TextView) view.findViewById(R.id.tv_give_up_sub);
        mTvGiveUpSubRevocation = (TextView) view.findViewById(R.id.tv_give_up_sub_revocation);
        mTvHistoryList = (TextView) view.findViewById(R.id.tv_history_list);

    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvOneKeySub, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSubSelect, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvGiveUpSub, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvGiveUpSubRevocation, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryList, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new NewStockMoreController(this);
        Bundle bundle = getArguments();
        if(bundle != null){
            mUserType = bundle.getString("userType");
        }
    }

    @Override
    protected void initViews() {
        setTheme();
    }

    @Override
    protected void setTheme() {

    }


    /**
     * 一键申购
     */
    public void OnClickOneKeySub() {
        Intent intent = new Intent(mActivity, NewOneKeySubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType",mUserType);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 申购记录查询
     */
    public void OnClickSubSelect() {
        Intent intent = new Intent(mActivity, NewSubSelectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType",mUserType);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 放弃认购申报
     */
    public void OnClickGiveUpSub() {
        Intent intent = new Intent(mActivity, NewGiveUpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType",mUserType);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 放弃认购申报撤单
     */
    public void OnClickGiveUpSubRevocation() {
        Intent intent = new Intent(mActivity, NewGiveUpRevocationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType",mUserType);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    /**
     * 历史缴费纪录查询
     */
    public void OnClickHistoryList() {
        Intent intent = new Intent(mActivity, NewHistoryListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType",mUserType);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

class NewStockMoreController  extends AbsBaseController implements View.OnClickListener {

    private NewStockMoreFragment mSelectFragment;

    public NewStockMoreController(NewStockMoreFragment mFragment) {
        mSelectFragment = mFragment;
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
        if (resId == R.id.tv_one_key_sub) {
            mSelectFragment.OnClickOneKeySub();
        } else if (resId== R.id.tv_sub_select){
            mSelectFragment.OnClickSubSelect();
        } else if (resId == R.id.tv_give_up_sub) {
            mSelectFragment.OnClickGiveUpSub();
        } else if (resId == R.id.tv_give_up_sub_revocation) {
            mSelectFragment.OnClickGiveUpSubRevocation();
        } else if (resId== R.id.tv_history_list){
            mSelectFragment.OnClickHistoryList();
        }
    }
}

