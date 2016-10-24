package com.thinkive.android.trade_bz.a_trans.controll;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_trans.fragment.TransTradeBuyOrSaleFragment;

/**
 * 转股交易 成交确认买入、成交确认卖出
 * @author 张雪梅
 * @corporation thinkive
 * @date 2015/12/31
 */
public class TransTradeBuyOrSellViewController extends AbsBaseController implements
        View.OnClickListener, ListView.OnItemClickListener, View.OnFocusChangeListener{

    private TransTradeBuyOrSaleFragment mFragment = null;

    public TransTradeBuyOrSellViewController(TransTradeBuyOrSaleFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_ITEM_CLICK:
                ((ListView) view).setOnItemClickListener(this);
                break;
            case ON_FOCUS_CHANGE:
                view.setOnFocusChangeListener(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_trans_price_cut) { // 交易价格-
            mFragment.onClickPriceCut();
        } else if (resId == R.id.tv_trans_price_add) { // 交易价格+
            mFragment.onClickPriceAdd();
        }  else if (resId == R.id.tv_trans_num_add) { // 交易数量+
            mFragment.onClickNumAdd();
        } else if (resId == R.id.tv_trans_num_cut) { // 交易数量-
            mFragment.onClickNumCut();
        }else if (resId == R.id.btn_trans_trade_commit) { // 买入或者卖出按钮
            mFragment.onClickTrade();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragment.onStoreListViewItemClick(position);
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        mFragment.onPriceEdtFocusChange(hasFocus);
    }
}
