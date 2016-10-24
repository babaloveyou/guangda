package com.thinkive.android.trade_bz.a_option.fragment;

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
import com.thinkive.android.trade_bz.a_option.activity.OptionSelectListActivity;
import com.thinkive.android.trade_bz.a_option.adapter.OptionContractAdapter;
import com.thinkive.android.trade_bz.a_option.adapter.OptionContractLockAdapter;
import com.thinkive.android.trade_bz.a_option.adapter.OptionContractOpenAdapter;
import com.thinkive.android.trade_bz.a_option.adapter.OptionEntrustPriceWayAdapter;
import com.thinkive.android.trade_bz.a_option.adapter.OptionExerciseEndDateAdapter;
import com.thinkive.android.trade_bz.a_option.adapter.OptionExercisePriceAdapter;
import com.thinkive.android.trade_bz.a_option.adapter.OptionHoldStockAdapter;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractLockBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractOpenBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionEntrustPriceWayBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionExerciseEndDateBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionExercisePriceBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionHoldStockBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;

/**
 * 个股期权选择列表类（单选）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/21
 */

public class OptionSelectListFragment<T extends Parcelable> extends AbsBaseFragment {
    private OptionSelectListActivity mActivity;
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
        mActivity = (OptionSelectListActivity)getActivity();
        Bundle bundle = getArguments();
        if(bundle != null) {
            mDataList = bundle.getParcelableArrayList("data_select");
            mUseType = bundle.getString("useType");
            if(mDataList != null && mDataList.size() != 0){
                if(mUseType.equals("option_contract")){  //个股期权选择合约
                    ArrayList<OptionContractBean> dataList = (ArrayList<OptionContractBean>)mDataList;
                    OptionContractAdapter adapter = new OptionContractAdapter(mActivity);
                    adapter.setListData(dataList);
                    mListView.setAdapter(adapter);
                }else if("option_exercise_end_date".equals(mUseType)){
                    //个股期权 选择行权日期
                    ArrayList<OptionExerciseEndDateBean> optionExerciseEndDateBeans=(ArrayList<OptionExerciseEndDateBean>)mDataList;
                    OptionExerciseEndDateAdapter adapter = new OptionExerciseEndDateAdapter(mActivity);
                    adapter.setListData(optionExerciseEndDateBeans);
                    mListView.setAdapter(adapter);
                }else if("option_exercise_price".equals(mUseType)){
                    //个股期权 选择行权价格
                    ArrayList<OptionExercisePriceBean> optionExercisePriceBeans= (ArrayList<OptionExercisePriceBean>) mDataList;
                    OptionExercisePriceAdapter adapter = new OptionExercisePriceAdapter(mActivity);
                    adapter.setListData(optionExercisePriceBeans);
                    mListView.setAdapter(adapter);
                }else if ("option_entrust_quoted_price_ways".equals(mUseType)){
                    //个股期权 委托报价方式
                    ArrayList<OptionEntrustPriceWayBean> optionEntrustPriceWayBeans = (ArrayList<OptionEntrustPriceWayBean>)mDataList;
                    OptionEntrustPriceWayAdapter optionEntrustPriceWayAdapter = new OptionEntrustPriceWayAdapter(mActivity);
                    optionEntrustPriceWayAdapter.setListData(optionEntrustPriceWayBeans);
                    mListView.setAdapter(optionEntrustPriceWayAdapter);
                }else if("option_hold_stock".equals(mUseType)){
                    //个股期权  持仓
                    //动态在当前fragment布局中加入列表的头部标题
                    LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
                    LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.item_option_hold_stock_title,null,false);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout rootLinearLayout=(LinearLayout)rootView;
                    rootLinearLayout.addView(linearLayout,0,layoutParams);
                    //设置 列表列表适配器 对应要填充的列表数据和listview对应的适配器
                    ArrayList<OptionHoldStockBean> optionHoldStockBeans = (ArrayList<OptionHoldStockBean>) mDataList;
                    OptionHoldStockAdapter optionHoldStockAdapter = new OptionHoldStockAdapter(mActivity);
                    optionHoldStockAdapter.setListData(optionHoldStockBeans);
                    mListView.setAdapter(optionHoldStockAdapter);
                }else if("option_contract_lock".equals(mUseType)){
                    //备兑 锁定
                    //动态在当前fragment布局中加入列表的头部标题
                    LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
                    LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.item_option_hold_stock_title,null,false);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout rootLinearLayout=(LinearLayout)rootView;
                    rootLinearLayout.addView(linearLayout,0,layoutParams);
                    //设置 列表列表适配器 对应要填充的列表数据和listview对应的适配器
                    ArrayList<OptionContractLockBean> optionContractLockBeens = (ArrayList<OptionContractLockBean>) mDataList;
                    OptionContractLockAdapter optionContractLockAdapter = new OptionContractLockAdapter(mActivity);
                    optionContractLockAdapter.setListData(optionContractLockBeens);
                    mListView.setAdapter(optionContractLockAdapter);
                }else if("option_contract_open".equals(mUseType)){
                    //备兑 解锁
                    //动态在当前fragment布局中加入列表的头部标题
                    LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
                    LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.item_option_hold_stock_title,null,false);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout rootLinearLayout=(LinearLayout)rootView;
                    rootLinearLayout.addView(linearLayout,0,layoutParams);
                    //设置 列表列表适配器 对应要填充的列表数据和listview对应的适配器
                    ArrayList<OptionContractOpenBean> optionContractOpenBeens = (ArrayList<OptionContractOpenBean>) mDataList;
                    OptionContractOpenAdapter optionContractOpenAdapter = new OptionContractOpenAdapter(mActivity);
                    optionContractOpenAdapter.setListData(optionContractOpenBeens);
                    mListView.setAdapter(optionContractOpenAdapter);
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
        mActivity.setResult(88, intent);
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

