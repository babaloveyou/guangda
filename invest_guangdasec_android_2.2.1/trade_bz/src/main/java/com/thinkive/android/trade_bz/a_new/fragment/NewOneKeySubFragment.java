package com.thinkive.android.trade_bz.a_new.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.adapter.NewOneKeySubAdapter;
import com.thinkive.android.trade_bz.a_new.bean.NewOneKeyBean;
import com.thinkive.android.trade_bz.a_new.bll.NewOneKeySubServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 批量新股申购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/18
 */
public class NewOneKeySubFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private NewOneKeySubController mController;
    private NewOneKeySubServiceImpl mServices;
    private NewOneKeySubAdapter mAdapter;
    /**
     * ListView
     */
    private ListView mListView;
    /**
     * 自定义的listView对象
     */
    private PullToRefreshListView mRefreshListView;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLinLoading;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLinNoDataSet;
    /**
     * 有数据时展示的页面
     */
    private LinearLayout mLinHaveDataSet;
    /**
     * 批量发申购按钮
     */
    private Button mBtnTotalSub;
    /**
     * 全选按钮
     */
    private CheckBox mCheckBox;
    /**
     * 用户类型，用户是用普通账户还是融资融券账户进入的本类
     */
    private String mUserType = "";
    /**
     * 当日可批量申购的证券信息
     */
    private ArrayList<NewOneKeyBean> mDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_one_key_sub, null);
        initData();
        findViews(rootView);
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLinNoDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLinHaveDataSet = (LinearLayout) view.findViewById(R.id.lin_have_data);
        mBtnTotalSub = (Button)view.findViewById(R.id.btn_total_new_stock);
        mCheckBox = (CheckBox)view.findViewById(R.id.rb_foot_one_key_sub);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mRefreshListView, mController);
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mCheckBox, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnTotalSub, mController);
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, mController);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            mUserType = bundle.getString("userType");
        }
        mActivity = (TKFragmentActivity) getActivity();
        mController = new NewOneKeySubController(this);
        mServices = new NewOneKeySubServiceImpl(this,mUserType);
        mAdapter = new NewOneKeySubAdapter(mActivity);
    }

    @Override
    protected void initViews() {
        mRefreshListView.setPullLoadEnabled(false);
        mServices.requestTodayNew();
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 获取今日新股列表
     */
    public void onGetStockLinkAgeData(ArrayList<NewOneKeyBean> dataList) {
        if (dataList == null || dataList.size()==0){
            mLinNoDataSet.setVisibility(View.VISIBLE);
            mLinHaveDataSet.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
        } else {
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.GONE);
            mLinHaveDataSet.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
            mDataList = dataList;
        }
        refreshComplete();
    }

    /**
     * 点击全选
     */
    public void onCheckSelectALL(boolean isChecked){
        if(mDataList == null)
            return;
        if(isChecked){ //全选被选中
            for(NewOneKeyBean bean : mDataList){
                bean.setCheck(true);
            }
        }else{//全选未被选中
            for(NewOneKeyBean bean : mDataList){
                bean.setCheck(false);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 点击列表项
     */
    public void onClickItem(int position){
        boolean checked = mDataList.get(position).isCheck();
        if (!checked) {
            mDataList.get(position).setCheck(true);
        } else {
            mDataList.get(position).setCheck(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 点击批量申购按钮
     */
    public void onClickBtnSub(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        mServices.requestOneKeySub(mDataList);
    }

    /**
     * 从服务器返回了委托交易（买入或卖出）成功时执行
     */
    public void onSuccessEntrustTrade(String resultStr) {
        MessageOkCancelDialog dialog = new MessageOkCancelDialog(mActivity, null);
        dialog.setMsgText(resultStr);
        dialog.setCancelBtnVisiable(false);
        dialog.show();
    }

    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mRefreshListView.onPullDownRefreshComplete();
        mRefreshListView.onPullUpRefreshComplete();
        mRefreshListView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }
    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestTodayNew();
    }
}

class NewOneKeySubController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener,View.OnClickListener,
        ListView.OnItemClickListener,RadioButton.OnCheckedChangeListener{

    private NewOneKeySubFragment mFragment = null;

    public NewOneKeySubController(NewOneKeySubFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView)view).setOnRefreshListener(this);
                break;
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_ITEM_CLICK:
                ((ListView)view).setOnItemClickListener(this);
                break;
            case ON_CHECK_CHANGE:
                ((CheckBox)view).setOnCheckedChangeListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.btn_total_new_stock) {//申购下单
            mFragment.onClickBtnSub();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        mFragment.onClickItem(position);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        mFragment.onCheckSelectALL(isChecked);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}

