package com.thinkive.android.trade_bz.a_stock.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.ThinkiveInitializer;
import com.android.thinkive.framework.WebViewManager;
import com.android.thinkive.framework.compatible.ListenerController;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.storage.MemoryStorage;
import com.android.thinkive.framework.util.Constant;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.activity.HKMultiTradeActivity;
import com.thinkive.android.trade_bz.a_in.activity.InFundMainActivity;
import com.thinkive.android.trade_bz.a_level.activity.LFundTradeMainActivity;
import com.thinkive.android.trade_bz.a_net.activity.NetVoteMainActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionMainActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundTradeMainActivity;
import com.thinkive.android.trade_bz.a_rr.activity.CreditHistoryEntrustOrTradeActivity;
import com.thinkive.android.trade_bz.a_rr.activity.CreditMoneyFlowActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RBuyStockToStockActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCashReturnActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCollaterBuyOrSaleActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCollaterTransActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSaleStockToMoneyActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCollateralSecurityActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCreditLimitActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCreditQuanDetailActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCreditZiDetailActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectDOselectActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RStockReturnStockActivity;
import com.thinkive.android.trade_bz.a_rr.activity.SubBondMultiActivity;
import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.NewStockWebActivity;
import com.thinkive.android.trade_bz.a_stock.activity.OneKeyActivity;
import com.thinkive.android.trade_bz.a_stock.activity.SignAgreementActivity;
import com.thinkive.android.trade_bz.a_stock.activity.TradeH5Activity;
import com.thinkive.android.trade_bz.a_stock.activity.TradeLoginActivity;
import com.thinkive.android.trade_bz.a_stock.activity.TradnsferActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.FastMenuAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.MoreMenuAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bean.TradeFastItemBean;
import com.thinkive.android.trade_bz.a_stock.bll.TradeMainServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.TradeCreditViewController;
import com.thinkive.android.trade_bz.a_trans.activity.TransStockMainActivityTrade;
import com.thinkive.android.trade_bz.dialog.LoadingDialog;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.EncryptManager;
import com.thinkive.android.trade_bz.others.tools.ThinkiveTools;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.others.tools.TradeWebFragmentManager;
import com.thinkive.android.trade_bz.receivers.TradeBaseBroadcastReceiver;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtil;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.TrimGridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


/**
 * Created by Administrator on 2016/10/10.
 */
public class CreditTradeFragment extends AbsTitlebarFragment implements IModule {
    public static final String PREFERENCE_KEY_PHONE_NUMBER = "preference_key_phone_number_credit";

    private View mRootView;

    /**
     * 交易首页Activity
     */
    private FragmentActivity mActivity = null;
    /**
     * 页面控件事件监听控制器，从本类的Activity中取得
     */
    private TradeCreditViewController mHomeController;
    /**
     * 手机验证功能的业务类
     */
    private TradeMainServicesImpl mServices;
    /**
     * 登录后实现页面跳转的广播接收器，登录后登录业务类发出广播，通知本对象进行页面跳转
     */
    private MainBroadcastReceiver mBroadcastReceiver;
    /**
     * 登录前单击的按钮ID
     */
    private int mClickBtnBeforeLogin;
    /**
     * 登录前单击的更多菜单pos
     */
    private int mMoreMenuPosBeforeLogin;
    /**
     * 登录前，如果在行情模块点击了“买入”或“卖出”按钮，则使用本变量记住行情模块传入的信息
     */
    private JSONObject mJsonDataFromHq;
    /**
     * webview管理器
     */
    private WebViewManager mWebViewManager;
    /**
     *
     */
    private String mFuncNo999;
    private String mTemp_token_key;
    private TradeLoginManager mTradeLoginManager;
    private LoadingDialog loadingDialog;//请求数据状态框


    private TrimGridView mFastMenuGv;//快捷菜单GridView
    private TrimGridView mMoreMenuGv;//更多菜单
    private FastMenuAdapter mFastAdapter;
    private MoreMenuAdapter mMoreAdapter;
    private List<TradeFastItemBean> mFastBeansList = new ArrayList<>();//顶部快捷菜单GridView数据集
    private List<TradeFastItemBean> mMoreBeanList = new ArrayList<>();//更多菜单GridView数据集

    private RelativeLayout mShowMoreRl;//点击展示更多
    private boolean isShowMore = false;//更多是否显示
    private ImageView mRotateArrow;//更多右侧带动画的箭头
    /*
    * 箭头的顺时针逆时针动画
    */
    private ObjectAnimator mArrowClwsAnimator;
    private ObjectAnimator mArrowCcwsAnimator;
    private RelativeLayout mNotCloseOutRl;//未平仓合约
    private RelativeLayout mBuyNewRl;//新股申购
    private RelativeLayout mSubBondRl;//标的证券
    private RelativeLayout mCloseOutRl;//已平仓合约
    private TextView mLogOutTv;//底部注销按钮
    private float mDensity;//像素密度
    private int mHiddenViewMeasuredHeight;//点击更多拉出的父布局高度
    private String[] mFastMenuTitles = {"融资买入", "融券卖出", "撤单", "个人持仓", "还款", "还券", "银行转账", "个人资产", "买担保品", "卖担保品", "划转担保品", "担保品查询"};
    private int[] mImgRes = {R.mipmap.financing_buy, R.mipmap.securities_sell, R.mipmap.credit_revocation, R.mipmap.credit_hold, R.mipmap.credit_refund, R.mipmap.credit_bond, R.mipmap.credit_bank_transfer, R.mipmap.credit_fund, R.mipmap.buy_collateral, R.mipmap.sell_collateral, R.mipmap.collateral_transfer, R.mipmap.collateral_search};
    private TradeParentFragment mParentFragment;//持有的 在TradeParentFragment初始化好的TradeParentFragment对象
    private RelativeLayout mShowDateRl;
    private Dialog mDialog;
    private ScrollView mScrollView;
    private OnSpeciallClickType onSpecialClickType = null;

    //主题枚举
    public enum OnSpeciallClickType {
        RepayByMoney,
        RepayByStock,
        ReStockByOwnStock,
        ReStockByBuyStock
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = super.onCreateView(inflater, container, savedInstanceState);
            setTitleRootVisibility(View.GONE);
            View rootView = inflater.inflate(R.layout.fragment_credit_trade, null);
            setSubViewToContainer(rootView);
            findViews(rootView);
            initGrid();
            fillData();
            setListeners();
            initViews();
        }
        return mRootView;
    }


    private void fillData() {
        for (int i = 0; i < mImgRes.length; i++) {
            mFastBeansList.add(new TradeFastItemBean(mImgRes[i], mFastMenuTitles[i], null));
        }
        mFastAdapter.setListData(mFastBeansList);
        mFastAdapter.notifyDataSetChanged();

        mMoreBeanList.add(new TradeFastItemBean(0, "当日委托", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "历史委托", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "资金流水", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "当日成交", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "历史成交", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "交割单", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "授信额度", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "融资明细", null));
        mMoreBeanList.add(new TradeFastItemBean(0, "融券明细", null));
        mMoreAdapter.setListData(mMoreBeanList);
        mMoreAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        //实时更新主页状态
        updateLogoutBtnState();
        //防止测试点点点
        mParentFragment.setLogTvClickable(true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //实时更新主页状态
            updateLogoutBtnState();
            //防止测试点点点
            mParentFragment.setLogTvClickable(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBroadcastReceiver.unregister();
    }

    @Override
    protected void initData() {
        mServices = new TradeMainServicesImpl(this);
        mHomeController = new TradeCreditViewController(this);
        // 登录后实现页面跳转的广播接收器，登录后登录业务类发出广播，通知本对象进行页面跳转
        mBroadcastReceiver = new CreditTradeFragment.MainBroadcastReceiver(ThinkiveInitializer.getInstance().getContext());
        // 登录后实现页面跳转的广播接收器，登录后登录业务类发出广播，通知本对象进行页面跳转
        mBroadcastReceiver.setActions(TradeBaseBroadcastReceiver.ACTION_START_ACTIVITY,
                TradeBaseBroadcastReceiver.ACTION_H5_UPDATE_FINISH,
                TradeBaseBroadcastReceiver.ACTION_CHANGE_PWD_SUCCESS,
                TradeBaseBroadcastReceiver.ACTION_ERROR_999,
                TradeBaseBroadcastReceiver.ACTION_TRANSFORM_TRADE_LOGIN);
        mBroadcastReceiver.register();
        mWebViewManager = WebViewManager.getInstance();
        mTradeLoginManager = TradeLoginManager.getInstance();
    }

    @Override
    protected void findViews(View view) {
        mFastMenuGv = (TrimGridView) view.findViewById(R.id.gv_fast_menu_credit);
        mMoreMenuGv = (TrimGridView) view.findViewById(R.id.gv_more_menu_credit);
        mShowMoreRl = (RelativeLayout) view.findViewById(R.id.rl_more_credit);
        mRotateArrow = (ImageView) view.findViewById(R.id.iv_more_can_rotate_credit);

        mBuyNewRl = (RelativeLayout) view.findViewById(R.id.rl_new_buy_credit);
        mSubBondRl = (RelativeLayout) view.findViewById(R.id.rl_sub_bond);
        mNotCloseOutRl = (RelativeLayout) view.findViewById(R.id.rl_not_close_out_credit);
        mCloseOutRl = (RelativeLayout) view.findViewById(R.id.rl_close_out_credit);
        mShowDateRl = (RelativeLayout) view.findViewById(R.id.contract_show_data_credit);
        mLogOutTv = (TextView) view.findViewById(R.id.tv_exit_logout_credit);
        mScrollView = (ScrollView) view.findViewById(R.id.scroll_parent);


    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_ITEM_CLICK, mFastMenuGv, mHomeController);
        registerListener(ListenerController.ON_ITEM_CLICK, mMoreMenuGv, mHomeController);
        registerListener(ListenerController.ON_CLICK, mShowMoreRl, mHomeController);
        registerListener(ListenerController.ON_CLICK, mBuyNewRl, mHomeController);
        registerListener(ListenerController.ON_CLICK, mNotCloseOutRl, mHomeController);
        registerListener(ListenerController.ON_CLICK, mCloseOutRl, mHomeController);
        registerListener(ListenerController.ON_CLICK, mShowDateRl, mHomeController);
        registerListener(ListenerController.ON_CLICK, mSubBondRl, mHomeController);

        mLogOutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
                    logOff();
                }
            }
        });


    }

    private void initGrid() {
        //更多展开的参数
        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewMeasuredHeight = (int) (mDensity * 114 + 0.5);


        mFastAdapter = new FastMenuAdapter(getActivity());
        mFastAdapter.setListData(mFastBeansList);
        mFastMenuGv.setAdapter(mFastAdapter);

        mMoreAdapter = new MoreMenuAdapter(getActivity());
        mMoreAdapter.setListData(mMoreBeanList);
        mMoreMenuGv.setAdapter(mMoreAdapter);

        mMoreMenuGv.setVisibility(GONE);
    }

    @Override
    protected void initViews() {
        setTheme();
        // 设置交易模块的RSA加密参数，以便后面的程序加密
        EncryptManager.getInstance().requestRsaParam();
        String webviewName = TradeUtils.getPreUrl(TradeWebFragmentManager.sWebCacheFragment.getUrl());
        mWebViewManager.preLoad(TradeWebFragmentManager.sWebCacheFragment.getUrl(), webviewName, false);

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 暴露给外界的交易首页初始化方法，集中处理交易模块的初始化操作
     *
     * @param activity 外部调用的Activity
     */
    public void init(FragmentActivity activity) {
        // TODO：注意，这样传递Activity对象，可能会在后台重启后出现错误。
        mActivity = activity;
        initData();
        ModuleManager.getInstance().registerModule(this, Constants.MODULE_NAME_TRADE);
    }

    public void setParent(TradeParentFragment parent) {
        mParentFragment = parent;
    }

    /**
     * 获得我的持仓数据
     *
     * @param data
     */
    public void onGetMyHoldData(MoneySelectBean data) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 获得我的持仓数据 失败
     */
    public void onGetMyHoldDataFail() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public String onMessageReceive(final AppMessage appMessage) {
        final int msgId = appMessage.getMsgId();
        CoreApplication.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = appMessage.getContent();
                LogUtil.printLog("d", "交易模块收到消息，消息号是：" + msgId
                        + "。消息内容是：" + jsonObject.toString());
                switch (msgId) {
                    case 60252: // H5页面返回键的消息
                        Activity activity = TradeWebFragmentManager.sWebCacheFragment.getActivity();
                        if (activity != null) {
                            activity.finish();
                        }
                        break;
                    case 60403: // 统一登录退出登录消息
                        setLogout();
                        break;
                    case 7060403: // 统一账户校验成功（手机号登录成功）
                        //                        try {
                        //                            String temp_token_key = jsonObject.getString("moduleName");
                        //                            if (temp_token_key.contains(Constants.MODULE_NAME_TRADE)) {
                        //                                mTemp_token_key = temp_token_key;
                        //                                TradeFlags.addFlag(TradeFlags.FLAG_PHONE_LOGIN);
                        //                                MemoryStorage memoryStorage = new MemoryStorage();
                        //                                String temp_token = memoryStorage.loadData(temp_token_key);
                        //                                mServices.startServerSession(temp_token);
                        //                            }
                        //                        } catch (JSONException je) {
                        //                            je.printStackTrace();
                        //                        }
                        break;
                    case 60200: // 资金账号校验成功（业务模块登录成功）
                        try {
                            String acct_type = jsonObject.getString("account_type");
                            String acct_value = jsonObject.getString("account");
                            addCheckStatus(acct_type);
                            mServices.requestUserMsgFromServer(acct_value, acct_type);
                        } catch (JSONException je) {
                            je.printStackTrace();
                        }
                        break;
                    case 50113:
                        String sso_flag = jsonObject.optString("sso_flag");
                        if ("0".equals(sso_flag)) {
                            mClickBtnBeforeLogin = 0;
                        }

                        break;
                    case 50101:
                        JSONObject params = jsonObject.optJSONObject("params");
                        if (params != null && "my_stock".equals(params.optString("toPage"))) {
                            mClickBtnBeforeLogin = R.id.tv_my_hold;
                        }
                        break;
                }
                MessageManager.getInstance(mActivity).buildMessageReturn(1, null, null);
            }
        });
        return null;
    }

    /**
     * 根据账户类型添加校验的标志位
     *
     * @param acct_type 账户类型
     */
    private void addCheckStatus(String acct_type) {
        int flag;
        switch (acct_type) {
            case TradeLoginManager.LOGIN_TYPE_CREDIT_ACCOUNT:
                flag = TradeFlags.FLAG_CREDIT_ACCOUNT_CHECK_SUCCESS;
                break;
            case TradeLoginManager.LOGIN_TYPE_OPTION_ACCOUNT:
                flag = TradeFlags.FLAG_OPTION_ACCOUNT_CHECK_SUCCESS;
                break;
            default:
                flag = TradeFlags.FLAG_NORMAL_ACCOUNT_CHECK_SUCCESS;
                break;
        }
        TradeFlags.addFlag(flag);
    }

    /**
     * 在行情模块中单击了买入或者卖出时
     * 已登录时直接进入买卖页面，并传入数据；未登录时，进入登录页并传入数据，登录成功后再用广播将数据传回本方法。
     */
    public void onClickBuyOrSaleInHq(JSONObject jsonObject) {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) { // 如果已登录
            Intent intent = new Intent(mActivity, MultiTradeActivity.class);
            Bundle bundle = new Bundle();
            try {
                bundle.putString("hq_stock_code", jsonObject.getString("code"));
                bundle.putString("hq_market", jsonObject.getString("market"));
                // 买入还是卖出的标志
                String type = jsonObject.getString("type");
                if (type.equals("buy")) { // 买入
                    bundle.putInt("ViewPagerFragmentPos", 0);
                } else { // 不是买入就当做卖出
                    bundle.putInt("ViewPagerFragmentPos", 1);
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
            intent.putExtras(bundle);
            startActivity(intent);
        } else { // 如果没登录
            mClickBtnBeforeLogin = -1;
            mJsonDataFromHq = jsonObject;
            startLogin(mClickBtnBeforeLogin, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 更新右上角“退出”按钮状态（是否可见）
     * 注：只有登录成功了，才显示
     */
    public void updateLogoutBtnState() {
        if (!isAdded()) {
            return;
        }
        if (!mParentFragment.isNormalTrade()) {
            if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
                showExitBtn();
                mParentFragment.setLoginToExit();
                mLogOutTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
                            logOff();
                        }
                    }
                });
                if (loadingDialog == null && mClickBtnBeforeLogin == 0) {//只有之间点击的登录按钮的时候，会返回交易主界面，在onResume的时候，才需要查询持仓数据
                    loadingDialog = new LoadingDialog(getContext());
                }
                if (loadingDialog != null) {
                    loadingDialog.show(R.string.querying_data);
                    if (loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                }
            } else {
                mParentFragment.setExitTologin();
                setAccountBtnText("");
                hideExitBtn();
            }
        }
        mParentFragment.initLoginTvListener();
    }

    /**
     * 展示交易首页页面，隐藏手机验证页面，并设置标题
     */


    /**
     * 交易首页按键事件监听
     *
     * @param view 被单击的控件
     */
    public void onClick(View view) {
        int viewId = view.getId();
        onClick(viewId);
    }


    /**
     * 交易首页GV点击事件监听
     *
     * @param parent gridview等
     */
    public void onItemClick(GridView parent, int position) {
        //        if (TradeUtils.isFastClick2()) {
        //            return;
        //        }
        if (parent.getCount() == 12) {
            switch (position) {
                case 0:
                    onClickTwoFinance(0);
                    //                    ToastUtil.showToast("融资买入");
                    break;
                case 1:
                    onClickTwoFinance(1);
                    //                    ToastUtil.showToast("融券卖出");
                    break;
                case 2:
                    onClickCreditRevotion();
                    //                    ToastUtil.showToast("撤单");
                    break;
                case 3:
                    onClickCreditHolderStock();
                    //                    ToastUtil.showToast("个人持仓");
                    break;
                case 4:
                    //弹出现金还款dialog
                    showBottomRefundDialog();
                    //                    ToastUtil.showToast("还款");
                    break;
                case 5:
                    showBottomReStockDialog();
                    //                    ToastUtil.showToast("还券");
                    break;
                case 6:
                    onClickTransferAccount();
                    //                    ToastUtil.showToast("银行转账");
                    break;
                case 7:
                    onClickCreditHolderStock();
                    //                    ToastUtil.showToast("个人资产");
                    break;
                case 8:
                    onClickGuaranteeIn();
                    //                    ToastUtil.showToast("买担保品");
                    break;
                case 9:
                    onClickGuaranteeOut();
                    //                    ToastUtil.showToast("卖担保品");
                    break;
                case 10:
                    onClickGuaranteeTransfer();
                    //                    ToastUtil.showToast("划转担保品");

                    break;
                case 11:
                    onGuaranteeSearch();
                    //                    ToastUtil.showToast("担保品查询");
                    break;
            }
        } else if (parent.getCount() == 9) {
            mMoreMenuPosBeforeLogin = position;
            switch (position) {
                case 0:
                    onClickTodayEntrust();
                    break;
                case 1:
                    onclickHistoryEntrust();
                    break;
                case 2:
                    onClickFundFlow();
                    break;
                case 3:
                    onClickTodayTrade();
                    break;
                case 4:
                    onClickHistoryTrade();
                    break;
                case 5:
                    onClickDelivery();
                    break;
                case 6:
                    onClickCreditLimitSelect();
                    break;
                case 7:
                    onClickCreditZiDetail();
                    break;
                case 8:
                    onClickCreditQuanDetail();
                    break;
            }

        }
    }

    private void onClickCreditQuanDetail() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RSelectCreditQuanDetailActivity.class);
            mActivity.startActivity(intent);
        } else {
            startLogin(1508, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    private void onClickCreditZiDetail() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RSelectCreditZiDetailActivity.class);
            mActivity.startActivity(intent);
        } else {
            startLogin(1507, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    private void onClickCreditLimitSelect() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RSelectCreditLimitActivity.class);
            mActivity.startActivity(intent);

        } else {
            startLogin(1506, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    private void onClickDelivery() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RSelectDOselectActivity.class);
            mActivity.startActivity(intent);

        } else {
            startLogin(1505, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }

    }

    private void onClickHistoryTrade() {
        if (!TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) { // 未登录时，调转到登录页面
            startLogin(1504, TradeLoginManager.LOGIN_TYPE_CREDIT);
        } else { // 已登录时
            Intent intent = new Intent(mActivity, CreditHistoryEntrustOrTradeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("childePos", 0);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        }
    }

    private void onClickTodayTrade() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, MultiCreditTradeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pos", 3);
            bundle.putInt("childePos", 1);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);

        } else {
            startLogin(1503, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    private void onClickFundFlow() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, CreditMoneyFlowActivity.class);
            mActivity.startActivity(intent);

        } else {
            startLogin(1502, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }

    }

    private void onclickHistoryEntrust() {
        if (!TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) { // 未登录时，调转到登录页面
            startLogin(1501, TradeLoginManager.LOGIN_TYPE_CREDIT);
        } else { // 已登录时
            Intent intent = new Intent(mActivity, CreditHistoryEntrustOrTradeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("childePos", 1);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        }
    }

    private void onClickTodayEntrust() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, MultiCreditTradeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pos", 3);
            bundle.putInt("childePos", 0);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        } else {
            startLogin(1500, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    /**
     * 交易首页按键事件监听
     *
     * @param viewId 被单击的控件的Id
     */

    //    mNotCloseOutRl = (RelativeLayout) view.findViewById(R.id.rl_not_close_out);
    //    mCloseOutRl = (RelativeLayout) view.findViewById(R.id.rl_close_out);
    //    mShowDateRl = (RelativeLayout) view.findViewById(R.id.contract_show_date);
    //
    public void onClick(int viewId) {
        if (viewId == R.id.rl_more_credit) {
            if (!isShowMore) {
                mMoreMenuGv.setVisibility(View.VISIBLE);
                expandGv();
                arrowTrunRight();
                isShowMore = true;
            } else {
                closeGv();
                arrowTurnLeft();
                isShowMore = false;
            }
        }
        if (TradeUtils.isFastClick2()) {
            return;
        }
        mClickBtnBeforeLogin = viewId;
        if (viewId == R.id.rl_new_buy_credit) {
            onClickNewStock();
        } else if (viewId == R.id.rl_not_close_out_credit) {
            ToastUtil.showToast("未平仓合约");
        } else if (viewId == R.id.rl_close_out_credit) {
            ToastUtil.showToast("已平仓合约");
        } else if (viewId == R.id.contract_show_data_credit) {
            ToastUtil.showToast("和约展期");
        } else if (viewId == R.id.rl_sub_bond) {
            onClickSubBond();
        }
    }


    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    private void closeGv() {
        ValueAnimator animator = createDropAnimator(mMoreMenuGv, mHiddenViewMeasuredHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mMoreMenuGv.setVisibility(GONE);
            }
        });
        animator.start();
    }

    private void expandGv() {
        mMoreMenuGv.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(mMoreMenuGv, 0, mHiddenViewMeasuredHeight);
        animator.start();
    }

    //关闭显示更多时箭头动画
    private void arrowTurnLeft() {
        if (mArrowCcwsAnimator == null) {
            mArrowCcwsAnimator = ObjectAnimator.ofFloat(mRotateArrow, "rotation", 90F, 0F);
        }
        mArrowCcwsAnimator.start();
    }

    //展开时箭头动画
    private void arrowTrunRight() {
        if (mArrowClwsAnimator == null) {
            mArrowClwsAnimator = ObjectAnimator.ofFloat(mRotateArrow, "rotation", 0F, 90F);
        }
        mArrowClwsAnimator.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mArrowCcwsAnimator != null) {
            mArrowCcwsAnimator = null;
        }
        if (mArrowClwsAnimator != null) {
            mArrowClwsAnimator = null;
        }
    }

    public void tradeMainLogin() {
        mClickBtnBeforeLogin = 0;
        startLogin(mClickBtnBeforeLogin, TradeLoginManager.LOGIN_TYPE_CREDIT);
        mParentFragment.setLogTvClickable(false);
    }

    public void tradeMainLogout() {
        logOff();
    }

    /**
     * 新股申购
     */
    private void onClickNewStock() {
        NewStockWebActivity.getCreditNewStockFragment().setUrl(ConfigManager.getInstance().getAddressConfigValue("CREDIT_NEWSTOCK_URL"));
        NewStockWebActivity.getCreditNewStockFragment().preloadUrl(mActivity, ConfigManager.getInstance().getAddressConfigValue("CREDIT_NEWSTOCK_URL"));
        Intent intent = new Intent(mActivity, NewStockWebActivity.class);
        intent.putExtra("loginType", NewStockWebActivity.CREDIT);
        mActivity.startActivity(intent);
    }

    /**
     * 标的证券
     */
    private void onClickSubBond() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, SubBondMultiActivity.class);
            mActivity.startActivity(intent);
        } else {
            startLogin(1201, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    /**
     * 融资融券
     */
    private void onClickTwoFinance(int flag) {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, MultiCreditTradeActivity.class);
            Bundle bundle = new Bundle();
            if (flag == 0) {
                //                Intent intent = new Intent(mActivity, RCreditBuyActivity.class);
                bundle.putInt("pos", 0);
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
            if (flag == 1) {
                //                Intent intent = new Intent(mActivity, RCreditSaleActivity.class);
                bundle.putInt("pos", 1);
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        } else {
            if (flag == 0) {
                //1001 fastmenu第一个按钮
                startLogin(1000, TradeLoginManager.LOGIN_TYPE_CREDIT);
            }
            if (flag == 1) {  //1001 fastmenu第二个按钮
                startLogin(1001, TradeLoginManager.LOGIN_TYPE_CREDIT);
            }
        }
    }

    //信用交易撤单
    private void onClickCreditRevotion() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, MultiCreditTradeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pos", 2);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);

        } else {
            startLogin(1002, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    /*
    *
    * 个人持仓
    * */
    private void onClickCreditHolderStock() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, MultiCreditTradeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pos", 4);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);

        } else {
            startLogin(1003, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }

    }

    /*
    * 还款
    * */
    private void onClickCreditRefund() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RSaleStockToMoneyActivity.class);//卖券
            //            Intent intent = new Intent(mActivity, RCashReturnActivity.class);//现金
            mActivity.startActivity(intent);
        } else {
            startLogin(1004, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }

    }


    /*
    * 还券
    * */
    private void onClickCreditTicket() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RBuyStockToStockActivity.class);//买券还券
            mActivity.startActivity(intent);
        } else {
            startLogin(1005, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    public void showBottomRefundDialog() {
        mDialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout_refund, null);
        Button mFundRefundBtn = (Button) inflate.findViewById(R.id.btn_fund_refund);
        Button mStockRefundBtn = (Button) inflate.findViewById(R.id.btn_stock_refund);
        Button mCancelBtn = (Button) inflate.findViewById(R.id.btn_cancel);
        mFundRefundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSpecialClickType = OnSpeciallClickType.RepayByMoney;
                onClickRepay();
                mDialog.dismiss();
            }
        });
        mStockRefundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSpecialClickType = OnSpeciallClickType.RepayByStock;
                onClickCreditRefund();
                mDialog.dismiss();
            }
        });
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.setContentView(inflate);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }

    private void onClickRepay() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RCashReturnActivity.class);//现金还款
            mActivity.startActivity(intent);
        } else {
            startLogin(1004, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    private void onClickReStockByOwnStock() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RStockReturnStockActivity.class);//现券还券
            mActivity.startActivity(intent);
        } else {
            startLogin(1005, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    public void showBottomReStockDialog() {
        mDialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout_restock, null);
        Button sFundReStockBtn = (Button) inflate.findViewById(R.id.btn_fund_restock);
        Button sStockReStockBtn = (Button) inflate.findViewById(R.id.btn_stock_restock);
        Button mCancelBtn = (Button) inflate.findViewById(R.id.btn_cancel);
        sFundReStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSpecialClickType = OnSpeciallClickType.ReStockByOwnStock;
                onClickReStockByOwnStock();
                mDialog.dismiss();
            }
        });
        sStockReStockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSpecialClickType = OnSpeciallClickType.ReStockByBuyStock;
                onClickCreditTicket();
                mDialog.dismiss();
            }
        });
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.setContentView(inflate);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }

    /*
    * 担保品买入
    * */
    private void onClickGuaranteeIn() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RCollaterBuyOrSaleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("buyOrSale", 0);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        } else {
            startLogin(1008, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }

    }

    /*
    * 担保品卖出
    * */
    private void onClickGuaranteeOut() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RCollaterBuyOrSaleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("buyOrSale", 1);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        } else {
            startLogin(1009, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    /*
    * 担保品划转
    */
    private void onClickGuaranteeTransfer() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            Intent intent = new Intent(mActivity, RCollaterTransActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ViewPagerFragmentPos", 0);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            startLogin(1010, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    /*
   *
   * 担保品查询
   * */
    private void onGuaranteeSearch() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            //点击右侧按钮所执行的操作
            startActivity(new Intent(mActivity, RSelectCollateralSecurityActivity.class));
        } else {
            startLogin(1011, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    /**
     * 场外基金交易
     */
    private void onClickFundTrade() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, FundTradeMainActivity.class);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_fun_trade, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 场内基金交易
     */
    private void onClickInFundTrade() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, InFundMainActivity.class);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_fun_trade_in, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 分级基金交易
     */
    private void onClickLevelFundTrade() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, LFundTradeMainActivity.class);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_fun_trade_level, TradeLoginManager.LOGIN_TYPE_NORMAL);

        }
    }

    /**
     * OTC交易
     */
    private void onClickTradeOTC() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            final Intent intent = new Intent(mActivity, TradeH5Activity.class);
            TradeWebFragmentManager.sWebCacheFragment.setFuncModule("4");
            TradeWebFragmentManager.sWebCacheFragment.preloadUrl(mActivity);
            TradeWebFragmentManager.sWebCacheFragment.prepareMsgToH5ForSkip(TradeLoginManager.LOGIN_TYPE_NORMAL);
            intent.setClass(mActivity, TradeH5Activity.class);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mActivity.startActivity(intent);
                }
            }, 150);
        } else {
            startLogin(R.id.tv_fun_otc, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 港股通
     */
    private void onClickHkTrade() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, HKMultiTradeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ViewPagerFragmentPos", 0);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_home_g_g_t, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 股转
     */
    private void onClickTransStock() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, TransStockMainActivityTrade.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ViewPagerFragmentPos", 0);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_home_trans_stock, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 个股期权
     */
    private void onClickOneStockOption() {
        if (TradeFlags.check(TradeFlags.FLAG_OPTION_TRADE_YES)) {
            Intent intent = new Intent(mActivity, OptionMainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ViewPagerFragmentPos", 0);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            //            startLogin(1006, TradeLoginManager.LOGIN_TYPE_OPTION);
        }
    }

    /**
     * 银证转账
     */
    private void onClickTransferAccount() {
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
            TradnsferActivity.getCreditFragment().setUrl(ConfigManager.getInstance().getAddressConfigValue("CREDIT_TRANSFER_URL"));
            TradnsferActivity.getCreditFragment().preloadUrl(mActivity, ConfigManager.getInstance().getAddressConfigValue("CREDIT_TRANSFER_URL"));
            Intent intent = new Intent(mActivity, TradnsferActivity.class);
            intent.putExtra("loginType", TradnsferActivity.CREDIT);
            mActivity.startActivity(intent);
        } else {
            startLogin(1106, TradeLoginManager.LOGIN_TYPE_CREDIT);
        }
    }

    /**
     * 一键归集
     */
    private void onClickOneKeyCollect() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, OneKeyActivity.class);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_oneKey_collection, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }


    /**
     * 退市板块协议签署
     */
    private void onClickSignAgreement() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, SignAgreementActivity.class);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_sign_agreement, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 网络投票
     */
    private void onClickNetVote() {
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
            Intent intent = new Intent(mActivity, NetVoteMainActivity.class);
            mActivity.startActivity(intent);
        } else { // 未登录时，调转到登录页面
            startLogin(R.id.tv_net_direction, TradeLoginManager.LOGIN_TYPE_NORMAL);
        }
    }

    /**
     * 预售邀约
     */
    private void onClickPresell() {

    }

    /**
     * 开始进行登录
     *
     * @param clickIdBeforeLogin 用户启动登录所单击的按钮的id
     * @param loginType          登录类型
     */
    private void startLogin(int clickIdBeforeLogin, String loginType) {
        if (TradeLoginManager.IF_UNITY_LOGIN) {
            //手机号登录成功建立会话失败 会话未建立
            if (!TradeFlags.check(TradeFlags.FLAG_CREATE_SESSION_SUCCESS) && TradeFlags.check(TradeFlags.FLAG_PHONE_LOGIN)) {
                //重新请求建立会话
                if (mTemp_token_key != null && mTemp_token_key.contains(Constants.MODULE_NAME_TRADE)) {
                    MemoryStorage memoryStorage = new MemoryStorage();
                    String temp_token = memoryStorage.loadData(mTemp_token_key);
                    mServices.startServerSession(temp_token);
                }
            }
            //            sendMsgToSSO(loginType);

        } else {
            Intent intent = new Intent(mActivity, TradeLoginActivity.class);
            intent.putExtra(MainBroadcastReceiver.INTENT_KEY_CLICK_VIEW_ID, clickIdBeforeLogin);
            intent.putExtra(Constants.LOGIN_TYPE, loginType);
            mActivity.startActivity(intent);
        }
    }


    public void logOff() {
        if (TradeUtils.isFastClick2()) {
            return;
        }
        MessageOkCancelDialog dialog = new MessageOkCancelDialog(mActivity, new MessageOkCancelDialog.IOkListener() {
            @Override
            public void onClickOk() {
                if (TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
                    mServices.requestLogout();
                }
                setLogout();
            }
        });
        dialog.setCancelBtnVisiable(true);
        dialog.setMsgText(R.string.dialog_logout_credit);
        dialog.show();
    }

    /**
     * 注销时的操作
     * 默认注销所有已登录的账户
     */
    public void setLogout() {
        clearAllFlags();
        clearAllUserInfo();
        //更新页面状态
        updateLogoutBtnState();
        //清除供给H5的用户信息
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.removeData(Constants.CREDIT_LOGIN_USERINFO_FORH5);
        //        CommonUtil.syncWebviewCookies(getActivity(), ConfigManager.getInstance().getUrlName("CREDIT_NEWSTOCK_URL"), "");
        try {
            sendMessageCireditLogout(NewStockWebActivity.getCreditNewStockFragment());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageCireditLogout(BaseWebFragment baseWebFragment) throws JSONException {
        JSONObject param = new JSONObject();
        //退出登录发个消息
        AppMessage appMessage = new AppMessage(222222, param);
        baseWebFragment.sendMessageToH5(appMessage);
    }

    /**
     * 清除标志位
     */
    public void clearAllFlags() {
        String shakePhone = ThinkiveTools.getDataToMemoryByMsg("shakePhone");
        if (TextUtils.isEmpty(shakePhone)) {
            TradeFlags.removeFlag(TradeFlags.FLAG_PHONE_LOGIN);
        }
        //移除信用交易标志位
        TradeFlags.removeFlag(TradeFlags.FLAG_CREDIT_ACCOUNT_CHECK_SUCCESS);
        TradeFlags.removeFlag(TradeFlags.FLAG_GET_CREDIT_USERINFO_SUCCESS);

        if (TradeFlags.check(TradeFlags.FLAG_NOT_UNITY_LOGIN_TYPE)) {//单独登录
            if (!TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES) && !TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
                TradeFlags.removeFlag(TradeFlags.FLAG_CREATE_SESSION_SUCCESS);
                mServices.requestClearSession();
            }
        }
    }

    /**
     * 清除用户信息
     */
    public void clearAllUserInfo() {
        //清除用户信息
        mTradeLoginManager.clearCreditUserInfo();
    }

    /**
     * 600003接口成功返回了用户信息之后
     * 获取到用户信息后，更新首页布局
     *
     * @param account
     * @param acctType
     */
    public void onGetUserInfo(String account, String acctType) {
        // 信用账号
        if (TradeLoginManager.LOGIN_TYPE_CREDIT_ACCOUNT.equals(acctType)) {
            TradeLoginManager.sCreditLoginAccount = account;
            TradeLoginManager.sCreditLoginType = acctType;
            TradeFlags.addFlag(TradeFlags.FLAG_GET_CREDIT_USERINFO_SUCCESS);
            // 个股期权
        } else if (TradeLoginManager.LOGIN_TYPE_OPTION_ACCOUNT.equals(acctType)) {
            TradeLoginManager.sOptionLoginAccount = account;
            TradeLoginManager.sOptionLoginType = acctType;
            TradeFlags.addFlag(TradeFlags.FLAG_GET_OPTION_USERINFO_SUCCESS);
            // 普通交易
        } else {
            TradeLoginManager.sNormalLoginAccount = account;
            TradeLoginManager.sNormalLoginType = acctType;
            TradeFlags.addFlag(TradeFlags.FLAG_GET_NORMAL_USERINFO_SUCCESS);
        }
        autoSkipPage();
    }

    /**
     * 登录成功时执行
     * 自动跳转到触发登录的页面
     */
    public void autoSkipPage() {
        // 之前在行情模块点击了“买入”、“卖出”按钮，召唤了统一登录
        if (mClickBtnBeforeLogin == -1) {
            onClickBuyOrSaleInHq(mJsonDataFromHq);
        } else if (mClickBtnBeforeLogin > 0) {
            onClick(mClickBtnBeforeLogin);
        } else {
            TradeTools.sendMsgToLoginForSubmitFinish(mActivity);
        }
        // 重置该变量的值，防止本次操作影响下一次操作
        mClickBtnBeforeLogin = 0;
    }

    /**
     * 给外壳模块的SSO模块发送消息，以进行统一登录
     *
     * @param loginType 登陆类型  信用账户或普通账户
     */
    public void sendMsgToSSO(String loginType) {
        String acctType;
        //如果登录类型是融资融券
        if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(loginType)) {
            acctType = TradeLoginManager.LOGIN_TYPE_CREDIT_ACCOUNT;
            //如果是个股期权
        } else if (TradeLoginManager.LOGIN_TYPE_OPTION.equals(loginType)) {
            acctType = TradeLoginManager.LOGIN_TYPE_OPTION_ACCOUNT;
            //否则就是普通交易
        } else {
            acctType = ConfigManager.getInstance().getSystemConfigValue("NORMAL_ACCT_TYPE");
        }
        JSONObject paramJsonObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            paramJsonObject.put("moduleName", Constants.MODULE_NAME_TRADE);
            paramJsonObject.put("toPage", "");
            paramJsonObject.put("isLoad", "");
            paramJsonObject.put("acct_type", acctType);
            jsonObject.put("moduleName", Constants.MODULE_NAME_SSO);
            jsonObject.put("params", paramJsonObject);
            AppMessage appMessage = new AppMessage("home", 50101, jsonObject);
            MessageManager.getInstance(mActivity).sendMessage(appMessage);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    public void showExitBtn() {
        mLogOutTv.setVisibility(View.VISIBLE);
    }

    public void hideExitBtn() {
        mLogOutTv.setVisibility(View.GONE);
    }

    public void scrollToTop() {
        mScrollView.fullScroll(ScrollView.FOCUS_UP);
    }


    /**
     * 负责接收广播以实现登录结束后，自动从本页跳转到登录前用户点击过得按钮所对应的页面。
     * 例如，未登录时，用户点击买入按钮，进入登录页，登录后发送广播，
     */
    public class MainBroadcastReceiver extends TradeBaseBroadcastReceiver {

        public static final String INTENT_KEY_CLICK_VIEW_ID = "click_view_id";
        public static final String INTENT_KEY_JSON_FORM_HQ = "json_from_hq";
        //修改密码成功后(返回修改的类型)
        public static final String CHANGE_PWD_TYPE_RESULT = "change_pwd_type_result";
        //修改密码时是统一登陆还是单独登录
        public static final String CHANGE_PWD_TYPE = "change_pwd_type";

        public MainBroadcastReceiver(Context context) {
            super(context);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            String action = intent.getAction();
            switch (action) {
                case ACTION_START_ACTIVITY:  // 如果是登录后跳转指令广播
                    int viewId = intent.getIntExtra(INTENT_KEY_CLICK_VIEW_ID, 1000);

                    if (viewId != -1) { // 此时，说明当初是未登录时，在交易主页点击某个按钮，触发登录的
                        if (viewId >= 1000 && viewId <= 2000) {
                            if (viewId - 1000 < 200) {
                                viewId = viewId - 1000;
                                if (viewId == 4) {
                                    if (onSpecialClickType == OnSpeciallClickType.RepayByStock) {
                                        onClickCreditRefund();//卖券还款
                                    }
                                    if (onSpecialClickType == OnSpeciallClickType.RepayByMoney) {
                                        onClickRepay();//现金还款
                                    }
                                } else if (viewId == 5) {
                                    if (onSpecialClickType == OnSpeciallClickType.ReStockByBuyStock) {
                                        onClickCreditTicket();//买券还券
                                    }
                                    if (onSpecialClickType == OnSpeciallClickType.ReStockByOwnStock) {
                                        onClickReStockByOwnStock();//现券还券
                                    }

                                } else if (viewId >= 0 && viewId <= 11) {
                                    onItemClick(mFastMenuGv, viewId);
                                }
                            } else if (viewId - 1000 >= 500) {
                                viewId = viewId - 1500;
                                onItemClick(mMoreMenuGv, viewId);
                            } else if (viewId - 1000 > 200 && viewId - 1000 < 500) {
                                viewId = viewId - 1200;
                                if (viewId == 1) {
                                    onClickSubBond();
                                }
                            }
                        }
                    } else { // 此时，说明当初是在未登录时，从行情模块点击“买入”或“卖出”按钮，触发登录的
                        onClickBuyOrSaleInHq(mJsonDataFromHq);
                    }


                    break;
                case ACTION_CHANGE_PWD_SUCCESS:  // 如果是修改密码成功的广播
                    //修改了那种账户的密码
                    String userType = intent.getStringExtra(CHANGE_PWD_TYPE_RESULT);
                    //登陆类型  单独登陆还是统一账户登陆
                    boolean notUnityLogin = intent.getBooleanExtra(CHANGE_PWD_TYPE, true);
                    //如果是统一登陆
                    if (notUnityLogin) {
                        switch (userType) {
                            case TradeLoginManager.LOGIN_TYPE_CREDIT:  //信用
                                mClickBtnBeforeLogin = 0;
                                mTradeLoginManager.clearCreditUserInfo();
                                startLogin(mClickBtnBeforeLogin, TradeLoginManager.LOGIN_TYPE_CREDIT);
                                break;
                            case TradeLoginManager.LOGIN_TYPE_OPTION:  //期权
                                mClickBtnBeforeLogin = 0;
                                mTradeLoginManager.clearOptionUserInfo();
                                startLogin(mClickBtnBeforeLogin, TradeLoginManager.LOGIN_TYPE_OPTION);
                                break;
                            default:   //普通交易
                                mClickBtnBeforeLogin = 0;
                                mTradeLoginManager.clearNormalUserInfo();
                                startLogin(mClickBtnBeforeLogin, TradeLoginManager.LOGIN_TYPE_NORMAL);
                                break;
                        }
                        // 如果交易单独登录
                    } else {
                        startLoginWithoutUnity();
                    }
                    break;
                case ACTION_ERROR_999:  //所有已登录的账户都清空
                    Bundle bundle = intent.getExtras();
                    mFuncNo999 = bundle.getString(Constant.FUNC_NO);
                    boolean isUnityLogin = bundle.getBoolean("isUnityLogin", true);
                    //统一账户才会执行重建会话操作
                    if (isUnityLogin) {
                        if (!mFuncNo999.equals("600003")) { //不是600003报的超时
                            clearAllUserInfo();//清除用户信息
                            clearAllFlags(); //清除所有标志位
                            mClickBtnBeforeLogin = 0;
                            // 先发送广播，退出资金账号校验状态。外壳、交易模块分别接收处理
                            TradeBaseBroadcastReceiver.sendBroadcast(mActivity, new Intent(), ACTION_LOGOUT_FUND_ACCOUNT);
                            // 退出所有的二级页面
                            clearSecondPage();
                            // 开启资金账号校验页面
                            startLogin(mClickBtnBeforeLogin, TradeLoginManager.LOGIN_TYPE_NORMAL);
                        } else {
                            mTradeLoginManager.reGetToken();//重构token
                        }
                    } else {  //如果交易单独登录
                        startLoginWithoutUnity();
                    }
                    break;
                case ACTION_LOGOUT_FUND_ACCOUNT:  // 发起退出资金账号校验状态
                    mClickBtnBeforeLogin = 0;
                    break;
                case ACTION_TRANSFORM_TRADE_LOGIN:
                    Intent intent2 = new Intent(mActivity, TradeLoginActivity.class);
                    intent2.putExtra(MainBroadcastReceiver.INTENT_KEY_CLICK_VIEW_ID, mClickBtnBeforeLogin);
                    intent2.putExtra(Constants.LOGIN_TYPE, TradeLoginManager.LOGIN_TYPE_NORMAL);
                    mActivity.startActivity(intent2);
                    break;
            }
        }

    }


    /**
     * 退出所有二级页面
     */
    private void clearSecondPage() {
        AppMessage appMessage = new AppMessage(Constants.MODULE_NAME_HOME, 50119, new JSONObject());
        MessageManager.getInstance(mActivity).sendMessage(appMessage);
    }

    /**
     * 开启单独登录
     */
    private void startLoginWithoutUnity() {
        clearSecondPage();
        mClickBtnBeforeLogin = 0;
        if (TradeFlags.check(TradeFlags.FLAG_PHONE_LOGIN)) {//手机号已登录
            startLogin(mClickBtnBeforeLogin, TradeLoginManager.LOGIN_TYPE_NORMAL);
        } else {
            Intent intent = new Intent(mActivity, TradeLoginActivity.class);
            intent.putExtra(MainBroadcastReceiver.INTENT_KEY_CLICK_VIEW_ID, mClickBtnBeforeLogin);
            intent.putExtra(Constants.LOGIN_TYPE, TradeLoginManager.LOGIN_TYPE_NORMAL);
            mActivity.startActivity(intent);
        }
    }

    public String getFuncNo999() {
        return mFuncNo999;
    }

    public void setFuncNo999(String funcNo999) {
        mFuncNo999 = funcNo999;
    }
}
