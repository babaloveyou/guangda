package com.thinkive.android.trade_bz.a_option.controll;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionBuyOrSaleCloseFragment;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;


/**
 * 个股期权 买入平仓，卖出平仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/16
 */
public class OptionBuyOrSaleCloseController extends AbsBaseController implements
        View.OnClickListener,TextWatcher{

    private OptionBuyOrSaleCloseFragment mFragment = null;

    public OptionBuyOrSaleCloseController(OptionBuyOrSaleCloseFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_TEXT_CHANGE:
                ((EditText) view).addTextChangedListener(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        //点击五档盘布局
        if (resId == R.id.ll_buy1 ||
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
        } else if (resId == R.id.tv_price_add) { // 交易价格+
            mFragment.onClickPriceAdd();
        } else if (resId == R.id.tv_price_cut) { // 交易价格-
            mFragment.onClickPriceCut();
        } else if (resId == R.id.tv_num_add) { // 交易数量+
            mFragment.onClickNumAdd();
        } else if (resId == R.id.tv_num_cut) { // 交易数量-
            mFragment.onClickNumCut();
        } else if (resId == R.id.tv_select_contract) { // 点击选择合约
            mFragment.onClickSelectContract();
        } else if (resId == R.id.tv_select_sub_bs) { // 点击选择报价方式
            mFragment.onClickSelectSubType();
        } else if (resId == R.id.btn_click_commit) { // 点击提交按钮
            mFragment.onClickBtnCommit();
        } else if (resId == R.id.tv_click_all_max) { // 点击全部
            mFragment.onClickMaxAll();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void afterTextChanged(Editable s) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mFragment.onInPutPriceChange();
    }
}
