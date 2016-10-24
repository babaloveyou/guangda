package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerController;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RContractExpandActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectListActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.bll.RChooseContractExpandServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 融资融券——下单——合约展期
 * @author 王延龙
 * @date 2016/4/18
 * @company Thinkive
 */
public class RContractExpandFragment extends AbsBaseFragment {
    private RContractExpandActivity mActivity;
    private RChooseContractExpandServiceImpl mService;
    private RContractExpandController mController;
    private ArrayList<RChooseContractBean> mDataList;
    private LinearLayout mLinLoading;
    /**
     * 证券代码
     */
    private TextView mTvStockCode;
    /**
     * 开仓时间
     */
    private TextView mTvOpenDate;
    /**
     * 证券名称
     */
    private TextView mTvStockName;
    /**
     * 合约编号
     */
    private TextView mTvContractId;
    /**
     * 到期时间
     */
    private TextView mTvEndDate;
    /**
     * 提交申请
     */
    private Button mCommit;
    /**
     * 当前所选中的合约
     */
    private RChooseContractBean mChooseContractBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_contract_extension, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mLinLoading = (LinearLayout)view.findViewById(R.id.lin_loading_set);
        mTvStockCode = (TextView)view.findViewById(R.id.tv_r_stock_code);
        mTvOpenDate = (TextView)view.findViewById(R.id.tv_r_open_date);
        mTvStockName = (TextView)view.findViewById(R.id.tv_r_stock_name);
        mTvContractId = (TextView)view.findViewById(R.id.tv_r_contract_id);
        mTvEndDate = (TextView)view.findViewById(R.id.tv_r_end_date);
        mCommit = (Button)view.findViewById(R.id.btn_r_contract_extension_commit);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_CLICK, mTvStockCode, mController);//选择合约
        registerListener(ListenerController.ON_CLICK, mCommit, mController);//提交申请
    }

    @Override
    protected void initData() {
        mActivity = (RContractExpandActivity)getActivity();
        mController = new RContractExpandController(this);
        mService = new RChooseContractExpandServiceImpl(this);
    }

    @Override
    protected void initViews() {
        mService.getContract();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 获取到合约数据
     * @param datalist
     */
    public void getChooseContractData(ArrayList<RChooseContractBean> datalist) {
        mLinLoading.setVisibility(View.GONE);
        if (datalist != null && datalist.size() > 0) {
            mDataList = datalist;
            mTvStockCode.setClickable(true);
            mTvStockCode.setHint(mActivity.getResources().getString(R.string.r_choose_contract_title));
        }else{
            mTvStockCode.setClickable(false);
            mTvStockCode.setHint(mActivity.getResources().getString(R.string.r_choose_no_contract));
        }
    }

    /**
     * 点击选择合约
     */
    public void onClickChooseStock() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mDataList != null && mDataList.size() > 0) {
            Intent intent = new Intent(mActivity, RSelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select", mDataList);
            intent.putExtra("useType", "select_contract");
            startActivityForResult(intent, 49);
        }else {
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.r_choose_no_contract));
        }
    }
    /**
     * 当股票选择页面关闭时，带着数据回来
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            int position = data.getIntExtra(RSelectListFragment.SELECT_RESULT,0);
            mChooseContractBean = mDataList.get(position);
            if(mChooseContractBean != null){
                mTvStockCode.setText(mChooseContractBean.getStock_code());
                mTvOpenDate.setText(mChooseContractBean.getOpen_date());
                mTvStockName.setText(mChooseContractBean.getStock_name());
                mTvContractId.setText(mChooseContractBean.getCompact_id());
                mTvEndDate.setText(mChooseContractBean.getRet_end_date());
            }
        }
    }

    /**
     * 点击委托按钮
     */
    public void onClickCommit() {
        if(mChooseContractBean == null){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.r_choose_contract_title));
        }else{
            mService.requestChooseContractExtension(mChooseContractBean);
        }
    }

    /**
     * 委托成功后清除所有数据
     */
    public void clearAllData(){
        mTvStockCode.setText("");
        mTvOpenDate.setText("");
        mTvStockName.setText("");
        mTvContractId.setText("");
        mTvEndDate .setText("");
        mChooseContractBean = null;
    }
}

/**
 * 本类控制器
 */
class RContractExpandController extends AbsBaseController implements View.OnClickListener{

    RContractExpandFragment mFragment = new RContractExpandFragment();

    /**
     * 带参构造
     * @param fragment 控制器宿主fragment
     */
    public RContractExpandController(RContractExpandFragment fragment){
        mFragment = fragment;
    }

    /**
     * 事件注册器
     * @param eventType 事件类型
     * @param view
     */
    @Override
    public void register(int eventType, View view) {
        switch (eventType){
            case ON_CLICK:
                view.setOnClickListener(this);
        }
    }

    /**
     * 点击事件处理
     * @param v 控件
     */
    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if(resId == R.id.tv_r_stock_code){
            mFragment.onClickChooseStock();
        }else if(resId == R.id.btn_r_contract_extension_commit){
            mFragment.onClickCommit();
        }
    }
}