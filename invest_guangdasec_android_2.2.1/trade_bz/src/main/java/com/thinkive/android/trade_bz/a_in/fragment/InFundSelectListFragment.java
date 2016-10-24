package com.thinkive.android.trade_bz.a_in.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.adapter.InFundSercuritiesPositionsAdapter;
import com.thinkive.android.trade_bz.a_in.bean.InFundSercuritiesPositionsQueryBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;

/**
 * 场内基金，选择列表类（单选）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/21
 */

public class InFundSelectListFragment<T extends Parcelable> extends AbsBaseFragment {
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
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, new ListController());
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            mDataList = bundle.getParcelableArrayList("data_select");
            mUseType = bundle.getString("useType");
            if(mDataList != null && mDataList.size() != 0){
                if(mUseType.equals("in_fund_sercurities_Positions")){
                    //赎回，选择基金列表
                    ArrayList<InFundSercuritiesPositionsQueryBean> dataList = (ArrayList<InFundSercuritiesPositionsQueryBean>)mDataList;
                    InFundSercuritiesPositionsAdapter adapter = new InFundSercuritiesPositionsAdapter(getActivity());
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
        getActivity().setResult(88, intent);
        getActivity().finish();
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

