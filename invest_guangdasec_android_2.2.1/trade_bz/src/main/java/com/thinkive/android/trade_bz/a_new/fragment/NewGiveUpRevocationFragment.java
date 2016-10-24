package com.thinkive.android.trade_bz.a_new.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.adapter.NewGiveUpRevocationAdapter;
import com.thinkive.android.trade_bz.a_new.bean.NewOneKeyBean;
import com.thinkive.android.trade_bz.a_new.bll.NewGiveUpRevocationSubServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 放弃认购申报撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewGiveUpRevocationFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private NewGiveUpRevocationController mController;
    private NewGiveUpRevocationSubServiceImpl mServices;
    private NewGiveUpRevocationAdapter mAdapter;
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
     * 用户类型，用户是用普通账户还是融资融券账户进入的本类
     */
    private String mUserType = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_list, null);
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
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mRefreshListView, mController);
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, mController);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            mUserType = bundle.getString("userType");
        }
        mActivity = (TKFragmentActivity) getActivity();
        mController = new NewGiveUpRevocationController(this);
        mServices = new NewGiveUpRevocationSubServiceImpl(this,mUserType);
        mAdapter = new NewGiveUpRevocationAdapter(mActivity);
    }

    @Override
    protected void initViews() {
        mRefreshListView.setPullLoadEnabled(false);
//        mServices.requestTodayNew();
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
            mRefreshListView.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
        } else {
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
//            mAdapter.setListData(dataList);
//            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
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

class NewGiveUpRevocationController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener,ListView.OnItemClickListener{

    private NewGiveUpRevocationFragment mFragment = null;

    public NewGiveUpRevocationController(NewGiveUpRevocationFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView)view).setOnRefreshListener(this);
                break;
            case ON_ITEM_CLICK:
                ((ListView)view).setOnItemClickListener(this);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}



