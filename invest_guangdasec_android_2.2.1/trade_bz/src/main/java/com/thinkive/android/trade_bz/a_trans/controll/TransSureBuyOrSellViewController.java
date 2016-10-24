package com.thinkive.android.trade_bz.a_trans.controll;

import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_trans.fragment.TransSureBuyOrSaleFragment;

/**
 * 转股交易 互报确认买入、互报确认卖出
 * @author 张雪梅
 * @corporation thinkive
 * @date 2015/12/31
 */
public class TransSureBuyOrSellViewController extends AbsBaseController implements
        View.OnClickListener,View.OnFocusChangeListener{

    private TransSureBuyOrSaleFragment mFragment = null;

    public TransSureBuyOrSellViewController(TransSureBuyOrSaleFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
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
    public void onFocusChange(View v, boolean hasFocus) {
        mFragment.onPriceEdtFocusChange(hasFocus);
    }
}
