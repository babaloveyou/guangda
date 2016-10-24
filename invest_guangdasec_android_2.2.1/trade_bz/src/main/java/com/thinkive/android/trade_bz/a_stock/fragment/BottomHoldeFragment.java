package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BottomHoldLvAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.bll.BuyOrSellServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301503;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/17.
 */
public class BottomHoldeFragment extends BaseLazzyFragment {

    public static final String TITLE="我的持仓";
    private ListView mHoldLv;
    //暂无数据
    private LinearLayout mNoDataLl;
    //加载中
    private LinearLayout mLoadingLl;
    private BottomHoldLvAdapter mBottomHoldLvAdapter;
    private MultiTradeActivity mActivity;
    private BuyOrSellFragment mFragment;

    private BuyOrSellServiceImpl mService;
    private View mView;
    //针对父Fragment runnable设定的标记  防止操作不同对象
    private  boolean mIsPrepared =  false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView != null) {
            return mView;
        }
        mView = inflater.inflate(R.layout.fragment_trade_bottom_hold, null);
        mActivity= (MultiTradeActivity) mFragment.getActivity();
        findViews(mView);
        initView();
        initListener();
        mIsPrepared = true;
        isPrepared = true;
        return mView;

    }

    @Override
    protected void lazyLoad() {
        //懒加载标记
        if (!isPrepared || !isVisible) {
            return;
        }
        processData();
    }

    @Override
    public void onResume() {
        super.onResume();
        lazyLoad();
    }

    private void initListener() {
        mHoldLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<MyStoreStockBean> dataList = mBottomHoldLvAdapter.getListData();
                MyStoreStockBean bean = dataList.get(position);
//                String stockCode = bean.getStock_code();
//                String market = bean.getMarket();
//                if (mService == null) {
//                    ToastUtil.showToast("mService==null");
//                }
//                mService.request20000ForHqData(stockCode,market);
                mFragment.getHolderStock(bean);

            }

        });
        mNoDataLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoading();
                processData();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsPrepared = false;
    }

    private void processData() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new Request301503(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<MyStoreStockBean> dataList = bundle.getParcelableArrayList(Request301503.BUNDLE_KEY_RESULT);
                if (dataList != null && dataList.size() != 0) {
                    mHoldLv.setVisibility(View.VISIBLE);
                    mNoDataLl.setVisibility(View.GONE);
                    mLoadingLl.setVisibility(View.GONE);
                    mBottomHoldLvAdapter.setDataList(dataList);
                    mBottomHoldLvAdapter.notifyDataSetChanged();
                } else {
                    setNoDate();
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301503.ERROR_INFO));
            }
        }).request();
    }

    private void initView() {
        mBottomHoldLvAdapter = new BottomHoldLvAdapter(mActivity);
        mHoldLv.setAdapter(mBottomHoldLvAdapter);
    }


    private void findViews(View view) {
        mHoldLv = (ListView) view.findViewById(R.id.lv_hold);
        mNoDataLl = (LinearLayout) view.findViewById(R.id.rl_no_data);
        mLoadingLl = (LinearLayout) view.findViewById(R.id.bottom_list_loading);
    }
    public void setLoading() {
        mHoldLv.setVisibility(View.GONE);
        mNoDataLl.setVisibility(View.GONE);
        mLoadingLl.setVisibility(View.VISIBLE);
    }
    public void setNoDate() {
        mHoldLv.setVisibility(View.GONE);
        mNoDataLl.setVisibility(View.VISIBLE);
        mLoadingLl.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void setFragment(BuyOrSellFragment fragment) {
        mFragment = fragment;
    }

    public void setService(BuyOrSellServiceImpl service) {
        mService = service;
    }

    public void notifyData(ArrayList<MyStoreStockBean> dataList) {
        if(!mIsPrepared){
            return;
        }
        mBottomHoldLvAdapter.setDataList(dataList);
        mBottomHoldLvAdapter.notifyDataSetChanged();
        mHoldLv.setVisibility(View.VISIBLE);
        mNoDataLl.setVisibility(View.GONE);
        mLoadingLl.setVisibility(View.GONE);
    }

}
