package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectListActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.RChooseContractExtensionAdapter;
import com.thinkive.android.trade_bz.a_rr.adapter.RSelectPopHoldStockAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;

/**
 * 融资融券选择列表（单选）
 * 可以动态传入使用类型
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/15
 */
public class RSelectListFragment<T extends Parcelable> extends AbsBaseFragment {
    public static final String SELECT_RESULT = "select_result";
    public static final int RESULT_CODE = 104;
    private RSelectListActivity mActivity;
    private ListView mListView;
    /**
     * 合约展期的头部
     */
    private LinearLayout mLinExpandContract;
    private ArrayList<T> mDataList;
    private String mUseType = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_listview_head, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mListView = (ListView)view.findViewById(R.id.lv_have_head);
        mListView.setDivider(null);
        mLinExpandContract = (LinearLayout)view.findViewById(R.id.lin_head_expand_contract);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, new FundRansomListController());
    }

    @Override
    protected void initData() {
        mActivity = (RSelectListActivity)getActivity();
       Bundle bundle = getArguments();
        if(bundle != null) {
            mDataList = bundle.getParcelableArrayList("data_select");
            mUseType = bundle.getString("useType");
            if(mDataList != null && mDataList.size() != 0){
                if (mUseType.equals("select_contract")) {  //合约选择
                    mLinExpandContract.setVisibility(View.VISIBLE);
                    ArrayList<RChooseContractBean> dataList = (ArrayList<RChooseContractBean>)mDataList;
                    RChooseContractExtensionAdapter adapter = new RChooseContractExtensionAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }else if (mUseType.equals("stock_hold")) {  //选择持仓证券
                    mLinExpandContract.setVisibility(View.GONE);
                    ArrayList<MyStoreStockBean> dataList = (ArrayList<MyStoreStockBean>)mDataList;
                    RSelectPopHoldStockAdapter adapter = new RSelectPopHoldStockAdapter(mActivity);
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
        intent.putExtra(SELECT_RESULT,position);
        mActivity.setResult(RESULT_CODE, intent);
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

