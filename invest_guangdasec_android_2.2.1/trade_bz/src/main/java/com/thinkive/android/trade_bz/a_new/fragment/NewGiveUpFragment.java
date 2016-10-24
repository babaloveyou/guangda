package com.thinkive.android.trade_bz.a_new.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.adapter.NewDistributeAdapter;
import com.thinkive.android.trade_bz.a_new.bll.NewGiveUpServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

/**
 * 放弃认购申报
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewGiveUpFragment extends AbsBaseFragment {
    private NewGiveUpServicesImpl mServices;
    private TKFragmentActivity mActivity;
    private NewDistributeAdapter mAdapter;
    private NewGiveUpController mController;
    /**
     * 点击选择证券
     */
    private TextView mTvSelectStock;
    /**
     *中签数量
     */
    private TextView mTvWinNum;
    /**
     * 应缴金额
     */
    private TextView mTvShouldMoney;
    /**
     *放弃数量
     */
    private EditText mEdtGiveUpNum;
    /**
     *提交
     */
    private Button mBtnCommit;
    /**
     *用户类型
     */
    private String mUserType = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_give_up, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }
    @Override
    protected void findViews(View view) {
        mTvSelectStock = (TextView)view.findViewById(R.id.tv_select_stock);
        mTvWinNum = (TextView)view.findViewById(R.id.tv_win_num);
        mTvShouldMoney = (TextView)view.findViewById(R.id.tv_should_money);
        mEdtGiveUpNum = (EditText)view.findViewById(R.id.edt_give_up_num);
        mBtnCommit = (Button)view.findViewById(R.id.btr_commit);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mTvSelectStock, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnCommit, mController);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            mUserType = bundle.getString("userType");
        }
        mActivity =(TKFragmentActivity)getActivity();
        mServices =new NewGiveUpServicesImpl(this,mUserType);
//        mAdapter =new NewDistributeAdapter(mActivity);
        mController=new NewGiveUpController(this);
    }

    @Override
    protected void initViews() {
        setTheme();
    }


    @Override
    protected void setTheme() {

    }

    /**
     * 选择证券
     */
    public void onClickSelectStock(){

    }

    /**
     * 提交按钮
     */
    public void onClickCommit(){

    }
}

class NewGiveUpController extends AbsBaseController implements View.OnClickListener{
    private NewGiveUpFragment mSelectFragment;

    public NewGiveUpController(NewGiveUpFragment mFragment) {
        mSelectFragment = mFragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_select_stock) { // 选择证券
            mSelectFragment.onClickSelectStock();
        } else if (resId == R.id.btr_commit) { //提交
            mSelectFragment.onClickCommit();
        }
    }
}



