package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectPropertyActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectPropertServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshScrollView;

/**
 * 融资融券--查询--资产负债
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/17
 */
public class RSelectPropertFragment extends AbsBaseFragment {
    /**
     * Activity
     */
    private RSelectPropertyActivity mActivity;
    /**
     * 自定义的ScrollView
     */
    private PullToRefreshScrollView mPullToRefreshScrollView;
    /**
     * 被加载的 ScrollView 子布局
     */
    private View mScrollChild;
    /**
     * 父类布局ScrollView
     */
    private ScrollView mScrollView;
    /**
     *控制器
     */
    private RSelectProperViewController mController;
    /**
     * 业务类
     */
    private RSelectPropertServiceImpl mServices;
    /**
     *按钮 “资产”
     */
    private RadioButton mRbMoney;
    /**
     *按钮 “负债”
     */
    private RadioButton mRbLibility;
    /**
     *按钮　“额度”
     */
    private RadioButton mRbLimit;
    /**
     *资产布局
     */
    private LinearLayout mLinOneLayout;
    /**
     *负债布局
     */
    private LinearLayout mLinTwoLayout;
    /**
     *额度布局
     */
    private LinearLayout mLinThreeLayout;
    /**
     * 信用账户总资产
     */
    private TextView mTvOneMoney;
    /**
     *信用账户净资产
     */
    private TextView mTvOneMoneyNet;
    /**
     *维持担保比例
     */
    private TextView mTvOneCollater;
    /**
     *证券市值
     */
    private TextView mTvOneMarketValue;
    /**
     *可用保证金
     */
    private TextView mTvOnecanUse;
    /**
     *资金余额
     */
    private TextView mTvOneOtherMoney;
    /**
     *资金可用
     */
    private TextView mTvOneUseMoney;
    /**
     *可转出担保品
     */
    private TextView mTvOneOurCollater;
    /**
     *可用于现金还款资金
     */
    private TextView mTvOneCashReturn;
    /**
     *现金可取
     */
    private TextView mTvOneCashGet;
    /**
     *融券卖出资金
     */
    private TextView mTvOneShell;
    /**
     * 总负债
     */
    private TextView mTvTwoLibility;
    /**
     *融资负债
     */
    private TextView mTvTwoFinancing;
    /**
     *融券负债
     */
    private TextView mTvTwoSecurities;
    /**
     *融资本金
     */
    private TextView mTvTwoCash;
    /**
     *融资息费
     */
    private TextView mTvTwoMoney;
    /**
     *应付融券市值
     */
    private TextView mTvTwoPayMarket;
    /**
     *融券息费
     */
    private TextView mTvTwoFinancingFei;
    /**
     *融资利率
     */
    private TextView mTvTwoInterset;
    /**
     *融券费率
     */
    private TextView mTvTwoTwoSecuritiesFei;
    /**
     *罚息年利率
     */
    private TextView mTvTwoYearRate;
    /**
     * 融资融券授信额度
     */
    private TextView mTvThreeAlllimit;
    /**
     *融资授信额度
     */
    private TextView mTvThreeZiLimit;
    /**
     *融券授信额度
     */
    private TextView mTvThreeQuanLimit;
    /**
     *融资可用额度
     */
    private TextView mTvThreeZiCan;
    /**
     *融券可用额度
     */
    private TextView mTvThreeQuanCan;
    /**
     *融资冻结额度
     */
    private TextView mTvThreeZiFreeze;
    /**
     *融券冻结额度
     */
    private TextView mTvThreeQuanFreeze;
    /**
     * 管理RadioButton
     */
    private RadioGroup mRadioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_scrollview, null);
        mScrollChild = inflater.inflate(R.layout.fragment_r_select_proper, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.sll_my_stock);
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        mScrollView.addView(mScrollChild);
        mRbMoney=(RadioButton)mScrollChild.findViewById(R.id.rb_r_money);
        mRbLibility=(RadioButton)mScrollChild.findViewById(R.id.rb_r_liability);
        mRbLimit=(RadioButton)mScrollChild.findViewById(R.id.rb_r_limit);
        mLinOneLayout=(LinearLayout)mScrollChild.findViewById(R.id.lin_proper_one);
        mLinTwoLayout=(LinearLayout)mScrollChild.findViewById(R.id.lin_proper_two);
        mLinThreeLayout=(LinearLayout)mScrollChild.findViewById(R.id.lin_proper_three);
        mRadioGroup=(RadioGroup)mScrollChild.findViewById(R.id.rg_property_select);

        mTvOneMoney=(TextView)mScrollChild.findViewById(R.id.tv_one_one);
        mTvOneMoneyNet=(TextView)mScrollChild.findViewById(R.id.tv_one_two);
        mTvOneCollater=(TextView)mScrollChild.findViewById(R.id.tv_one_three);
        mTvOneMarketValue=(TextView)mScrollChild.findViewById(R.id.tv_one_four);
        mTvOnecanUse=(TextView)mScrollChild.findViewById(R.id.tv_one_five);
        mTvOneOtherMoney=(TextView)mScrollChild.findViewById(R.id.tv_one_six);
        mTvOneUseMoney=(TextView)mScrollChild.findViewById(R.id.tv_one_seven);
        mTvOneOurCollater=(TextView)mScrollChild.findViewById(R.id.tv_one_eight);
        mTvOneCashReturn=(TextView)mScrollChild.findViewById(R.id.tv_one_night);
        mTvOneCashGet=(TextView)mScrollChild.findViewById(R.id.tv_one_ten);
        mTvOneShell=(TextView)mScrollChild.findViewById(R.id.tv_one_tenone);

        mTvTwoLibility=(TextView)mScrollChild.findViewById(R.id.tv_two_one);
        mTvTwoFinancing=(TextView)mScrollChild.findViewById(R.id.tv_two_two);
        mTvTwoSecurities=(TextView)mScrollChild.findViewById(R.id.tv_two_three);
        mTvTwoCash=(TextView)mScrollChild.findViewById(R.id.tv_two_four);
        mTvTwoMoney=(TextView)mScrollChild.findViewById(R.id.tv_two_five);
        mTvTwoPayMarket=(TextView)mScrollChild.findViewById(R.id.tv_two_six);
        mTvTwoFinancingFei=(TextView)mScrollChild.findViewById(R.id.tv_two_seven);
        mTvTwoInterset=(TextView)mScrollChild.findViewById(R.id.tv_two_eight);
        mTvTwoTwoSecuritiesFei=(TextView)mScrollChild.findViewById(R.id.tv_two_night);
        mTvTwoYearRate=(TextView)mScrollChild.findViewById(R.id.tv_two_ten);

        mTvThreeAlllimit=(TextView)mScrollChild.findViewById(R.id.tv_three_one);
        mTvThreeZiLimit=(TextView)mScrollChild.findViewById(R.id.tv_three_two);
        mTvThreeQuanLimit=(TextView)mScrollChild.findViewById(R.id.tv_three_three);
        mTvThreeZiCan=(TextView)mScrollChild.findViewById(R.id.tv_three_four);
        mTvThreeQuanCan=(TextView)mScrollChild.findViewById(R.id.tv_three_five);
        mTvThreeZiFreeze=(TextView)mScrollChild.findViewById(R.id.tv_three_six);
        mTvThreeQuanFreeze=(TextView)mScrollChild.findViewById(R.id.tv_three_seven);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_SCROLLVIEW_REFLASH, mPullToRefreshScrollView, mController);
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRadioGroup, mController);
    }

    @Override
    protected void initData() {
        mActivity=(RSelectPropertyActivity)getActivity();
        mController=new RSelectProperViewController(this);
        mServices=new RSelectPropertServiceImpl(this);
        Bundle bundle=getArguments();
        if(bundle != null) {
            int checkId = bundle.getInt("checkId");
            mRadioGroup.check(checkId);
            onClickRadioGroup(checkId);
        }
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mPullToRefreshScrollView.setPullLoadEnabled(false);
        //请求数据
        mServices.requestAllMoneyData();
        setTheme();
    }

    @Override
    protected void setTheme() {}

    /**
     * 得到当前账户的资产数据信息
     */
    public void getAllMoneyData(RSelectPropertBean data){
        mTvOneMoney.setText(data.getAssert_val());
        mTvOneMoneyNet.setText(data.getNet_asset());
        mTvOneCollater.setText(data.getPer_assurescale_value());
        mTvOneMarketValue.setText(data.getMarket_value());
        mTvOnecanUse.setText(data.getEnable_bail_balance());
        mTvOneOtherMoney.setText(data.getFund_asset());
        mTvOneUseMoney.setText(data.getFundavl());
        mTvOneOurCollater.setText(data.getGuaranteeout());
        mTvOneCashReturn.setText(data.getFin_enrepaid_balance());
        mTvOneCashGet.setText(data.getFetch_balance());
        mTvOneShell.setText(data.getSlo_sell_balance());

        mTvTwoLibility.setText(data.getTotal_debit());
        mTvTwoFinancing.setText(data.getFin_debit());
        mTvTwoSecurities.setText(data.getSlo_debit());
        mTvTwoCash.setText(data.getFin_enable_balance());
        mTvTwoMoney.setText(data.getFfee());
        mTvTwoPayMarket.setText(data.getFin_compact_fare());
        mTvTwoFinancingFei.setText(data.getDfee());
        mTvTwoInterset.setText(data.getFin_unbusi_balance());
        mTvTwoTwoSecuritiesFei.setText(data.getSlo_unbusi_balance());
        mTvTwoYearRate.setText(data.getUnderly_market_value());

        mTvThreeAlllimit.setText(data.getAcreditavl());
        mTvThreeZiLimit.setText(data.getFcreditbal());
        mTvThreeQuanLimit.setText(data.getDcreditbal());
        mTvThreeZiCan.setText(data.getFcreditavl());
        mTvThreeQuanCan.setText(data.getDcreditavl());
        mTvThreeZiFreeze.setText(data.getFin_used_quota());
        mTvThreeQuanFreeze.setText(data.getSlo_used_quota());

        refreshComplete();
    }
    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mPullToRefreshScrollView.onPullDownRefreshComplete();
        mPullToRefreshScrollView.onPullUpRefreshComplete();
        mPullToRefreshScrollView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }

    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestAllMoneyData();
    }

    /**
     * 点击RadioGroup
     */
    public void onClickRadioGroup(int checkId){
        if (checkId == R.id.rb_r_money) { //资产
            clickRbMoney();
        }else if (checkId == R.id.rb_r_liability){ //负债
            clickRbLibility();
        }else if (checkId == R.id.rb_r_limit){ //额度
            clickRbLimit();
        }
    }

    /**
     * 点击资产按钮
     */
    public void clickRbMoney(){
        mRbMoney.setTextColor(mActivity.getResources().getColor(R.color.trade_color_white));
        mRbLibility.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
        mRbLimit.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
        mLinOneLayout.setVisibility(View.VISIBLE);
        mLinTwoLayout.setVisibility(View.GONE);
        mLinThreeLayout.setVisibility(View.GONE);
    }

    /**
     * 点击负债按钮
     */
    public void clickRbLibility(){
        mRbMoney.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
        mRbLibility.setTextColor(mActivity.getResources().getColor(R.color.trade_color_white));
        mRbLimit.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
        mLinOneLayout.setVisibility(View.GONE);
        mLinTwoLayout.setVisibility(View.VISIBLE);
        mLinThreeLayout.setVisibility(View.GONE);
    }

    /**
     * 点击额度按钮
     */
    public void clickRbLimit(){
        mRbMoney.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
        mRbLibility.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
        mRbLimit.setTextColor(mActivity.getResources().getColor(R.color.trade_color_white));
        mLinOneLayout.setVisibility(View.GONE);
        mLinTwoLayout.setVisibility(View.GONE);
        mLinThreeLayout.setVisibility(View.VISIBLE);
    }
}

class RSelectProperViewController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener,RadioGroup.OnCheckedChangeListener{


    private RSelectPropertFragment mFragment = null;

    public RSelectProperViewController(RSelectPropertFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_SCROLLVIEW_REFLASH:
                ((PullToRefreshScrollView)view).setOnRefreshListener(this);
                break;

            case ON_CHECK_CHANGE:
                ((RadioGroup)view).setOnCheckedChangeListener(this);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragment.onClickRadioGroup(checkedId);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}

