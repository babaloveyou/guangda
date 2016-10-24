package com.thinkive.android.trade_bz.a_option.controll;

import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.fragment.OptionExerciseFragment;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;

/**
 * 个股期权 行权
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/17
 */
public class OptionExerciseController extends AbsBaseController implements
        View.OnClickListener{

    private OptionExerciseFragment mFragment = null;

    public OptionExerciseController(OptionExerciseFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_num_add) { // 交易数量+
            mFragment.onClickNumAdd();
        } else if (resId == R.id.tv_num_cut) { // 交易数量-
            mFragment.onClickNumCut();
        } else if (resId == R.id.tv_select_contract) { // 点击选择合约
            mFragment.onClickSelectContract();
        } else if (resId == R.id.btn_click_commit) { // 点击提交按钮
            mFragment.onClickBtnCommit();
        } else if (resId == R.id.tv_click_all_max) { // 点击全部
            mFragment.onClickMaxAll();
        }
    }
}
