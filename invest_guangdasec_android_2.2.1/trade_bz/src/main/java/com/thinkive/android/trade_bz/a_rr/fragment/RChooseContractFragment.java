package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RChooseContractActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.RReturnMoneyAdapter;
import com.thinkive.android.trade_bz.a_rr.adapter.RReturnStockAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;

/**
 * 融资融券_指定合约_选择合约（多选）
 * 注：ONE_TYPE  只允许选择同类证券  ANY_ONE 可任意选择证券
 * @author 张雪梅
 * @company ThinkIve
 * @date 2016/7/20
 */
public class RChooseContractFragment extends AbsBaseFragment {
    public static final int RESULT_CODE_SAVE = 99;
    public static final int RESULT_CODE_CANCEL = 100;
    public static final String RESULT_SAVE = "select_save";
    public static final String ONE_TYPE = "select_one_type";
    public static final String ANY_ONE = "select_any_one";
    public static final String USE_TYPE = "use_type";
    public static final String CONTRACT_DATA_LIST = "contract_data_list";

    private ListView mListView;
    private LinearLayout mLinReturnStock;
    private LinearLayout mLinReturnMoney;
    private RChooseContractActivity mActivity;
    private RReturnStockAdapter mReturnStockAdapter;
    private RReturnMoneyAdapter mReturnMoneyAdapter;
    private ArrayList<RChooseContractBean> mContractDataList;
    /**
     * ONE_TYPE  只允许选择同类证券
     * ANY_ONE 可任意选择证券
     */
    private String mUserType = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_choose_contracts, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Bundle bundle = getArguments();
        if (bundle != null) {   //清除纪录避免影响下次操作
            bundle.remove(USE_TYPE);
            bundle.remove(CONTRACT_DATA_LIST);
        }
    }

    @Override
    protected void findViews(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_r_choose_contract);
        mListView.setDivider(null);
        mLinReturnStock = (LinearLayout)view.findViewById(R.id.lin_head_return_stock);
        mLinReturnMoney = (LinearLayout)view.findViewById(R.id.lin_head_return_money);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_ITEM_CLICK, mListView, new RChooseContractFragmentController());
    }

    @Override
    protected void initData() {
        mActivity = (RChooseContractActivity) getActivity();
        mReturnStockAdapter = new RReturnStockAdapter(mActivity);
        mReturnMoneyAdapter = new RReturnMoneyAdapter(mActivity);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUserType = bundle.getString(USE_TYPE);
            mContractDataList = bundle.getParcelableArrayList(CONTRACT_DATA_LIST);
        }
        //给适配器设置数据
        if (mContractDataList != null && mContractDataList.size() > 0) {
            if (mUserType.equals(ONE_TYPE)) { //只能选择同类证券(还券)
                mLinReturnStock.setVisibility(View.VISIBLE);
                mReturnStockAdapter.setListData(mContractDataList);
                mListView.setAdapter(mReturnStockAdapter);
            }else if (mUserType.equals(ANY_ONE)) {  //任意选择（还款）
                mLinReturnMoney.setVisibility(View.VISIBLE);
                mReturnMoneyAdapter.setListData(mContractDataList);
                mListView.setAdapter(mReturnMoneyAdapter);
            }
        }
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 点击item选择合约
     *
     * @param position
     */
    public void onClickContractItem(int position) {
        if (mUserType.equals(ONE_TYPE)) { //只能选择同类证券
            RChooseContractBean bean = mContractDataList.get(position);
            boolean checked = bean.isChecked();
            if (!checked) {
                // 选中
                mContractDataList.get(position).setChecked(true);
                // 除了与该证券相同的，其它都不可以选择
                for (RChooseContractBean rChooseContractBean : mContractDataList) {
                    if (rChooseContractBean.getStock_code().equals(bean.getStock_code())) {
                        rChooseContractBean.setCan_checked(true);
                    } else {
                        rChooseContractBean.setCan_checked(false);
                        rChooseContractBean.setChecked(false);
                    }
                }
            } else {
                mContractDataList.get(position).setChecked(false); //取消选中
                int i = 0;
                for (RChooseContractBean rChooseContractBean : mContractDataList) {
                    if (rChooseContractBean.isChecked()) {
                        i = 1;//仍然有列表项被选中
                    }
                }
                if (i == 0) { //说明当前没有任何一条数据被选中，所有设置为全部可选
                    for (RChooseContractBean list : mContractDataList) {
                        list.setCan_checked(true);
                    }
                }
            }
            //刷新适配器
            mReturnStockAdapter.notifyDataSetChanged();

        } else if (mUserType.equals(ANY_ONE)) {  //任意选择
            boolean checked = mContractDataList.get(position).isChecked();
            if (!checked) {
                mContractDataList.get(position).setChecked(true);
            } else {
                mContractDataList.get(position).setChecked(false);
            }
            mReturnMoneyAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 点击保存
     */
    public void onClickSave() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(RESULT_SAVE, mContractDataList);
        mActivity.setResult(RESULT_CODE_SAVE, intent);
        mActivity.finish();
    }

    /**
     * 点击取消
     */
    public void onClickCancel() {
        Intent intent = new Intent();
        mActivity.setResult(RESULT_CODE_CANCEL, intent);
        mActivity.finish();
    }


    /**
     * 自动选中之前选中过的数据·
     *
     * @param curDataList
     */
    private void autoSelectLastContract(ArrayList<RChooseContractBean> curDataList) {
//        ArrayList<RChooseContractBean> dataList = new ArrayList<RChooseContractBean>();
//        if(mContractDataList != null && mContractDataList.size() > 0) {
//            for (RChooseContractBean bean : mContractDataList) {
//                boolean isCheck = bean.isChecked();
//                if (isCheck) {
//                    dataList.add(bean);
//                }
//            }
//        }
//        if(mContractLastSelectList != null && mContractLastSelectList.size() > 0){
//            for (RChooseContractBean bean : curDataList) {
//                for (RChooseContractBean hasSelectBean : mContractLastSelectList) {
//                    if (bean.getCompact_id().equals(hasSelectBean.getCompact_id())) {
//                        bean.setChecked(true);
//                    }
//                }
//            }
//            mReturnStockAdapter.notifyDataSetChanged();
//        }
    }

    class RChooseContractFragmentController extends AbsBaseController implements
            AdapterView.OnItemClickListener {

        public RChooseContractFragmentController() {

        }

        @Override
        public void register(int i, View view) {
            switch (i) {
                case ON_ITEM_CLICK:
                    ((ListView) view).setOnItemClickListener(this);
                    break;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onClickContractItem(position);
        }
    }
}