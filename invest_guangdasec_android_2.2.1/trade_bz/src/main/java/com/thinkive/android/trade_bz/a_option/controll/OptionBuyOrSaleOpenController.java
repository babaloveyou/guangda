package com.thinkive.android.trade_bz.a_option.controll;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionBuyOrSaleOpenFragment;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;

/**
 * 个股期权 买入开仓，卖出开仓,备兑券开仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/20
 */
public class OptionBuyOrSaleOpenController extends AbsBaseController implements
        View.OnClickListener,TextWatcher,RadioGroup.OnCheckedChangeListener{

    private OptionBuyOrSaleOpenFragment mFragment = null;

    public OptionBuyOrSaleOpenController(OptionBuyOrSaleOpenFragment fragment) {
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
            case ON_CHECK_CHANGE:
                ((RadioGroup) view).setOnCheckedChangeListener(this);
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
        } else if (resId == R.id.tv_select_sub_bs) { // 点击选择报价方式
            mFragment.onClickSelectSubType();
        } else if (resId == R.id.btn_click_commit) { // 点击提交按钮
            mFragment.onClickBtnCommit();
        } else if (resId == R.id.tv_click_all_max) { // 点击全部
            mFragment.onClickMaxAll();
        }else if (resId == R.id.tv_cut_date) { // 到期月份
            mFragment.onClickSelectCutDate();
        } else if (resId == R.id.tv_exercise_price) { // 行权价格
            mFragment.onClickSelectExercisePrice();
        } else if (resId == R.id.tv_stock_name) { // 证券名称
            mFragment.onClickStockName();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragment.onChickChange(checkedId);
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
