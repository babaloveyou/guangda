package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BottomRevocationAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.RevocationServicesImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */
public class BottomRevocationFragment extends BaseLazzyFragment {
    public static final String TITLE = "撤单";
    private RevocationServicesImpl mRevocationServices;
    private MultiTradeActivity mActivity;
    private BuyOrSellFragment mFragment;
    private View mView;
    private ListView mRevotionLv;
    private LinearLayout mNoDataLl;
    private LinearLayout mLoadingLl;
    private BottomRevocationAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView != null) {
            return mView;
        }
        mView = inflater.inflate(R.layout.fragment_trade_bottom_revotion, null);
        mActivity = (MultiTradeActivity) mFragment.getActivity();
        findViews(mView);
        initView();
        initListener();
        initData();
        isPrepared = true;
        return mView;
    }

    @Override
    protected void lazyLoad() {
        //懒加载标记
        if (!isPrepared || !isVisible) {
            return;
        }
        mRevocationServices.requestRevocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 调用业务类中，初始化撤单数据的方法
        lazyLoad();
    }
    public void requestNewData() {
        lazyLoad();
    }
    private void initData() {
        mActivity = (MultiTradeActivity) mFragment.getActivity();
        mRevocationServices = new RevocationServicesImpl(this);
        mAdapter = new BottomRevocationAdapter(getContext(), mRevocationServices);
    }

    private void initListener() {
        mNoDataLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoading();
                lazyLoad();
            }
        });
    }

    private void setLoading() {
        mNoDataLl.setVisibility(View.GONE);
        mRevotionLv.setVisibility(View.GONE);
        mLoadingLl.setVisibility(View.VISIBLE);
    }

    private void initView() {
        mRevotionLv.setDivider(null);
    }

    private void findViews(View view) {
        mRevotionLv = (ListView) view.findViewById(R.id.lv_revotion);
        mNoDataLl = (LinearLayout) view.findViewById(R.id.rl_no_data);
        mLoadingLl = (LinearLayout) view.findViewById(R.id.bottom_list_loading);
    }



    /**
     * 接收RevocationServicesImpl 业务类传递过来的撤单数据
     *
     * @param dataList 从请求类传来的持仓数据集合
     */
    public void onGetStoreData(ArrayList<RevocationBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mRevotionLv.setVisibility(View.GONE);
            mNoDataLl.setVisibility(View.VISIBLE);
            mLoadingLl.setVisibility(View.GONE);
        } else {
            //买入卖出通过entrust_bs筛选数据
            ArrayList<RevocationBean> revocationBeensBuy = new ArrayList<>();
            ArrayList<RevocationBean> revocationBeensSell = new ArrayList<>();


            for (RevocationBean bean : dataList) {
                String entrust_bs = bean.getEntrust_bs();
                if ("0".equals(entrust_bs)) {
                    revocationBeensBuy.add(bean);
                } else if ("1".equals(entrust_bs)) {
                    revocationBeensSell.add(bean);
                }
            }
            mLoadingLl.setVisibility(View.GONE);
            mRevotionLv.setVisibility(View.VISIBLE);
            mNoDataLl.setVisibility(View.GONE);
            mAdapter.setListData(dataList);
            if (mFragment.getBuyOrSell()==0) {//如果是买
                mAdapter.setListData(revocationBeensBuy);
                revocationBeensSell = null;
            } else {//卖
                mAdapter.setListData(revocationBeensSell);
                revocationBeensBuy = null;
            }
            mRevotionLv.setAdapter(mAdapter);
            //            mAdapter.notifyDataSetChanged();
        }
    }

    public void setFragment(BuyOrSellFragment fragment) {
        mFragment = fragment;
        mActivity = (MultiTradeActivity) mFragment.getActivity();
    }

    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }
}
