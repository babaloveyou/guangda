package com.thinkive.android.trade_bz.a_rr.controll;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RCreditSaleFragment;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;

/**
 * 融券卖出
 */
public class RCreditSaleViewController extends AbsBaseController implements
        View.OnClickListener, ListView.OnItemClickListener, TextWatcher,
        View.OnFocusChangeListener, RadioGroup.OnCheckedChangeListener{

    private RCreditSaleFragment mFragment = null;

    public RCreditSaleViewController(RCreditSaleFragment fragment) {
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
            case ON_CHECK_CHANGE:
                ((RadioGroup)view).setOnCheckedChangeListener(this);
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
        } else if (resId == R.id.btn_buy_or_sell) { // 买入或者卖出按钮
            mFragment.onClickTrade();
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
        } else if (adapterViewId == R.id.lv_show_contract) { // 点击合约列表
            mFragment.onContractItemClick(position);
        } else if (adapterViewId == R.id.lv_show_object) { // 点击可容标的
            mFragment.onTicketItemClick(position);
        } else {
            mFragment.onClickBuyOrSaleDisk(view);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragment.onClickRadioButtonList(checkedId);
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
