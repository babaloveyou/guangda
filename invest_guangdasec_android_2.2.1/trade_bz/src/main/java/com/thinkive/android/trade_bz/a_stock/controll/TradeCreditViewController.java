package com.thinkive.android.trade_bz.a_stock.controll;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.a_stock.fragment.CreditTradeFragment;

/**
 * Created by Administrator on 2016/10/24.
 */
public class TradeCreditViewController extends ListenerControllerAdapter implements View.OnClickListener, AdapterView.OnItemClickListener{
    private CreditTradeFragment mFragment;

    /**
     * 视图监听构造器
     *
     * @param fragment 交易界面对象
     */
    public TradeCreditViewController(CreditTradeFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_ITEM_CLICK:
                GridView gv = (GridView) view;
                gv.setOnItemClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        mFragment.onClick(v);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragment.onItemClick((GridView) parent,position);
    }

}
