package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.SignAgreementMsgActivity;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;

/**
 *  退市板块协议签署-协议详情
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */
public class SignAgreementMsgFragment extends AbsBaseFragment {
    private SignAgreementMsgActivity mActivity;
    private TextView mTvAgreementTitle;
    private TextView mTvAgreementContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_sign_agreement_msg, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvAgreementTitle = (TextView) view.findViewById(R.id.tv_agreement_title);
        mTvAgreementContent = (TextView) view.findViewById(R.id.tv_agreement_content);
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {
        mActivity = (SignAgreementMsgActivity)getActivity();
    }

    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            SignStockAccountLimitBean bean = (SignStockAccountLimitBean)bundle.getSerializable("warn_book");
            if(bean != null){
                mTvAgreementTitle.setText(bean.getTitle());
                mTvAgreementContent.setText(bean.getContent());
            }
        }
        setTheme();
    }

    @Override
    protected void setTheme() {

    }
}

