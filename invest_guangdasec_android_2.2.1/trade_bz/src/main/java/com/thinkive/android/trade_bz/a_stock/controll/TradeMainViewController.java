package com.thinkive.android.trade_bz.a_stock.controll;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.a_stock.fragment.NormalTradeFragment;

/**
 * 描述：交易首页视图监听注册器
 * @author 黎丝军
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public class TradeMainViewController extends ListenerControllerAdapter implements View.OnClickListener, AdapterView.OnItemClickListener {
    private NormalTradeFragment mFragment;

    /**
     * 视图监听构造器
     *
     * @param fragment 交易界面对象
     */
    public TradeMainViewController(NormalTradeFragment fragment) {
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
