package com.thinkive.android.trade_bz.a_out.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.activity.FundListActivity;
import com.thinkive.android.trade_bz.a_out.adapter.FundCompanyAdapter;
import com.thinkive.android.trade_bz.a_out.adapter.FundPledgePopAdapter;
import com.thinkive.android.trade_bz.a_out.adapter.FundRansomAdapter;
import com.thinkive.android.trade_bz.a_out.bean.FundCompanyBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.a_out.bean.FundReturnMoneyDateBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;

/**
 * 场外基金选择列表（单选）
 * 可以动态传入使用类型
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/14
 */
public class FundSelectListFragment<T extends Parcelable> extends AbsBaseFragment {
    private FundListActivity mActivity;
    private ListView mListView;
    private ArrayList<T> mDataList;
    private String mUseType = "";
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_listview, null);
        this.rootView=rootView;
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mListView = (ListView)view.findViewById(R.id.lv_pop);
        mListView.setDivider(null);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, new FundRansomListController());
    }

    @Override
    protected void initData() {
        mActivity = (FundListActivity)getActivity();
       Bundle bundle = getArguments();
        if(bundle != null) {
            mDataList = bundle.getParcelableArrayList("data_select");
            mUseType = bundle.getString("useType");
            if(mDataList != null && mDataList.size() != 0){
                if (mUseType.equals("fund_hold")) {  //选择持仓基金
                    ArrayList<FundHoldBean> dataList = (ArrayList<FundHoldBean>)mDataList;
                    FundRansomAdapter adapter = new FundRansomAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }else if(mUseType.equals("fund_date")){ // 基金定投扣款日
                    ArrayList<FundReturnMoneyDateBean> dataList = (ArrayList<FundReturnMoneyDateBean>)mDataList;
                    FundPledgePopAdapter adapter = new FundPledgePopAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }else if(mUseType.equals("fund_open")){ // 基金开户
                    // TODO: 2016/9/1  不知道是否要查询框，先注释
                    /*//获取要添加的元素的实体
                    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                    LinearLayout childLinearLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_pop_for_list_fund_search,null);
                    //设置布局参数
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    //加载布局
                    LinearLayout rootLinearLayout = (LinearLayout) this.rootView;
                    rootLinearLayout.addView(childLinearLayout,0,layoutParams);*/

                    ArrayList<FundCompanyBean> dataList = (ArrayList<FundCompanyBean>)mDataList;
                    FundCompanyAdapter adapter = new FundCompanyAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }
            }
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
     * 点击列表
     */
    public void onClickItem(int position) {
        Intent intent = new Intent();
        intent.putExtra("data_select_result",position);
        mActivity.setResult(20, intent);
        mActivity.finish();
    }


    class FundRansomListController extends AbsBaseController implements AdapterView.OnItemClickListener {

        public FundRansomListController() {
        }
        @Override
        public void register(int i, View view) {
            super.register(i, view);
            switch (i) {
                case ON_ITEM_CLICK:
                    ((ListView) view).setOnItemClickListener(this);
                    break;
            }
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onClickItem(position);
        }
    }
}

