package com.thinkive.android.trade_bz.a_hk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.activity.HKListActivity;
import com.thinkive.android.trade_bz.a_hk.adapter.HKCompanyActionTypeAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKCompanyActionTypeBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;

/**
 * 港股选择列表（单选）
 * 可以动态传入使用类型
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/27
 */
public class HKSelectListFragment<T extends Parcelable> extends AbsBaseFragment {
    private HKListActivity mActivity;
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
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, new FundRansomListController());
    }

    @Override
    protected void initData() {
        mActivity = (HKListActivity)getActivity();
        Bundle bundle = getArguments();
        if(bundle != null) {
            mDataList = bundle.getParcelableArrayList("data_select");
            mUseType = bundle.getString("useType");
            if(mDataList != null && mDataList.size() != 0){
                if (mUseType.equals("company_action")) {  //公司行为业务类型选择
                    ArrayList<HKCompanyActionTypeBean> dataList = (ArrayList<HKCompanyActionTypeBean>)mDataList;
                    HKCompanyActionTypeAdapter adapter = new HKCompanyActionTypeAdapter(mActivity);
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
        mActivity.setResult(19, intent);
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

