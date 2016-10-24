package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.SelectListActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BankTransferSelectAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.OneKeyPopAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.SignAccountLimitAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.SignStockAccountAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.BankTransferBean;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.a_stock.bean.SignAgreementBean;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;

import java.util.ArrayList;

/**
 * 普通交易选择列表（单选）
 * 可以动态传入使用类型
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/31
 */
public class SelectListFragment<T extends Parcelable> extends AbsBaseFragment {
    private SelectListActivity mActivity;
    private ListView mListView;
    private ArrayList<T> mDataList;
    private String mUseType = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_listview, null);
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
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, new ListController());
    }

    @Override
    protected void initData() {
        mActivity = (SelectListActivity)getActivity();
        Bundle bundle = getArguments();
        if(bundle != null) {
            mDataList = bundle.getParcelableArrayList("data_select");
            mUseType = bundle.getString("useType");
            if(mDataList != null && mDataList.size() != 0){
                if(mUseType.equals("one_key")){ //一键归集选择资金账号
                    ArrayList<OneKeyBean> dataList = (ArrayList<OneKeyBean>)mDataList;
                    OneKeyPopAdapter adapter = new OneKeyPopAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }else if(mUseType.equals("bank_account")){ //银证转账选择存管银行
                    ArrayList<BankTransferBean> dataList = (ArrayList<BankTransferBean>)mDataList;
                    BankTransferSelectAdapter adapter = new BankTransferSelectAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }else if(mUseType.equals("stock_account")){ //选择股东账号
                    ArrayList<SignAgreementBean> dataList = (ArrayList<SignAgreementBean>)mDataList;
                    SignStockAccountAdapter adapter = new SignStockAccountAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }else if(mUseType.equals("account_limit")){ //客户证券账户权限选择
                    ArrayList<SignStockAccountLimitBean> dataList = (ArrayList<SignStockAccountLimitBean>)mDataList;
                    SignAccountLimitAdapter adapter = new SignAccountLimitAdapter(mActivity);
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


    class ListController extends AbsBaseController implements AdapterView.OnItemClickListener {

        public ListController() {
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

