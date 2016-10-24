package com.thinkive.android.trade_bz.a_stock.controll;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.BuyOrSellFragment;

/**
 * 买入卖出的控制器
 */
public class BuyOrSellViewController extends AbsBaseController implements
        View.OnClickListener, ListView.OnItemClickListener, TextWatcher,
        View.OnFocusChangeListener {

    private BuyOrSellFragment mFragment = null;

    public BuyOrSellViewController(BuyOrSellFragment fragment) {
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
            case ON_TEXT_CHANGE:
                ((EditText) view).addTextChangedListener(this);
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
        if (resId == R.id.ibt_subtract) { // 交易价格+
            mFragment.onClickTradeSubstract();
        } else if (resId == R.id.ibt_add) { // 交易价格-
            mFragment.onClickTradeAdd();
        }  else if (resId == R.id.iv_delete_price) {//删除价格按钮
            mFragment.onClickDeletPriceText();
        } else if (resId == R.id.iv_delete_num) {//删除数量iv
            mFragment.onClickDeletNumText();
        } else if (resId == R.id.tv_stock_num_pre) {//股票数量替换之前的布局
            mFragment.onPreNumClick();
        } else if (resId == R.id.edt_stock_price_pre) {//股票价格替换之前的布局
            mFragment.onPrePriceClick();
        } else if (resId == R.id.btn_buy_or_sell) { // 买入或者卖出按钮
            mFragment.onClickTrade();
        } else if (resId == R.id.tv_all_num) {//全仓按钮
            mFragment.setStockNumAll();
        } else if (resId == R.id.tv_half_num) {//半仓
            mFragment.setStockNumHalf();
        } else if (resId == R.id.tv_third_num) {//1/3仓
            mFragment.setStockNumThird();
        }else if (resId == R.id.tv_quarter_num) {//1/4仓
            mFragment.setStockNumQuarter();
        } else if (resId == R.id.ll_code_name) {//点击名字
            mFragment.onNameClick();
        } else if (resId == R.id.ll_buy1 ||
                resId == R.id.ll_buy2 ||
                resId == R.id.ll_buy3 ||
                resId == R.id.ll_buy4 ||
                resId == R.id.ll_buy5 ||
                resId == R.id.ll_sale1 ||
                resId == R.id.ll_sale2 ||
                resId == R.id.ll_sale3 ||
                resId == R.id.ll_sale4 ||
                resId == R.id.ll_sale5) {
            mFragment.onClickBuyOrSaleDisk(v);
        } else if (resId == R.id.tv_down_limit) { // 涨停价
            mFragment.onClickDownLimit();
        } else if (resId == R.id.tv_up_limit) { // 跌停价
            mFragment.onClickUpLimit();
        } else if (resId == R.id.tv_stock_name) { // 股票名称
            mFragment.onClickStockName();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int adapterViewId = parent.getId();
        if (adapterViewId == R.id.lv_pop) { // 股票代码搜索提示列表的item单击事件
            mFragment.onSearchListViewItemClick(position);
        }else {
            mFragment.onClickBuyOrSaleDisk(view);
        }
    }

    //---------------------TextWatcher的三个方法，定义开始--------------------
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mFragment.onSearchTextChange();
    }
    //---------------------TextWatcher的三个方法，定义结束--------------------

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int resId = v.getId();
        if (resId == R.id.edt_stock_price) {
            mFragment.onPriceEdtFocusChange(hasFocus);
        }
    }

}
