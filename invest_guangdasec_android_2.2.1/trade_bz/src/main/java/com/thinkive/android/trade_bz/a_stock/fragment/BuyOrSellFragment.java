package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.ListenerController;
import com.jakewharton.rxbinding.view.RxView;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BuyOrSellForPopAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.HolderAccountAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.SearchStocksAdapter;
import com.thinkive.android.trade_bz.a_stock.adapter.TradeBottomFragmentVpAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.LoginInfo;
import com.thinkive.android.trade_bz.a_stock.bean.MarketEntrustWay;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.a_stock.bean.StockLinkageBean;
import com.thinkive.android.trade_bz.a_stock.bll.BuyOrSellServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.BuyOrSellViewController;
import com.thinkive.android.trade_bz.dialog.ATradeComfirmDialog;
import com.thinkive.android.trade_bz.dialog.MessageOkCancelDialog;
import com.thinkive.android.trade_bz.keyboard.KeyboardEventListener;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.others.tools.StockFuzzyQueryManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.SizeUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.BottomFragmentVp;
import com.thinkive.android.trade_bz.views.ClearEditText;
import com.thinkive.android.trade_bz.views.popupwindows.PopupWindowInListView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.thinkive.android.trade_bz.R.drawable.sale_no_corner_kong;
import static com.thinkive.android.trade_bz.a_stock.bean.LoginInfo.getFilStockLimitBeans;

/**
 * 买入、卖出共享的一个Fragment。
 * 通过{@link #mBuyOrSell}来区分到底是买入还是卖出
 *
 * @author 王志鸿
 * @version 1.0
 * @corporation thinkive
 * @date 2015/6/5
 */
public class BuyOrSellFragment extends AbsBaseFragment implements Serializable, KeyboardManager.OnKeyCodeDownListener {
    /**
     * 输入“证券代码”
     */
    private ClearEditText mEdStockCode = null;

    /**
     * 显示涨停价
     */
    private TextView mTvUpLimit = null;
    /**
     * 显示跌停价
     */
    private TextView mTvDownLimit = null;
    /**
     * 购买价 -
     */
    private LinearLayout mTvSubtract = null;
    /**
     * 购买价 +
     */
    private LinearLayout mTvAdd = null;
    /**
     * 股票购买的价格输入框
     */
    private EditText mEdStockPrice = null;
    /**
     * 委托交易数量输入框
     */
    private ClearEditText mEdEntrustAmount = null;
    /**
     * 交易按钮，买入，或者卖出
     */
    private Button mBtnBuyOrSell = null;
    /**
     * 股东账号
     */
    private TextView mHolderAccount;
    /**
     * 重选股东账号
     */
    private TextView mShowHolderAccountsTv;
    /**
     * 之前的展示股东账号的触发
     */
    private TextView mShowHolderAccountsPreTv;
    /**
     * 之前的展示股东账号夫布局
     */
    private LinearLayout mPreAccountParentLl;
    /**
     * 之后的展示股东账号夫布局
     */
    private LinearLayout mAfterAccountParentLl;
    /**
     * 之前和之后的展示股东账号夫布局容器
     */
    private FrameLayout mAccountParentFl;
    /**
     * 股东账号popwindow填充布局
     */
    private View mPopupWindowView;
    /**
     * 股东账号popwindow对象
     */
    private PopupWindow mAccountPopWindow;
    /**
     * 价格布局替换之前
     */
    private TextView mEdStockPricePre;
    /**
     * 价格布局替换之后的父布局
     */
    private LinearLayout mAfterPriceView;
    /**
     * 加、减单位
     */
    private TextView mTv1;
    private TextView mTv2;
    /**
     * 价格编辑器删除
     */
    private ImageView mDeletePriceIv;
    /**
     * 价格编辑器
     */
    private String mStockNumText;
    /**
     * 价格编辑器文本监听
     */
    private PriceTextWatcher mPriceTextWatcher;
    /**
     * 股票数量编辑器文本监听
     */
    private NumTextWatcher mNumTextWatcher;
    /**
     * 最大可买可卖
     */
    private TextView mMaxStockNumTv;
    /**
     * 股票数量布局替换之前
     */
    private TextView mStockNumPreTv;
    /**
     * 股票数量布局替换之后夫布局
     */
    private LinearLayout mStockNumAfterView;
    /**
     * 全仓、半仓、三分之一仓、四分之一仓
     */
    private TextView mAllNumTv;
    private TextView mQuarterNumTv;
    private TextView mHalfNumTv;
    private TextView mThirdNumTv;
    /**
     * 待买入的股票总价
     */
    private String plainPrice;
    /**
     * 股票数量布局替换容器
     */
    private FrameLayout mInPutAmountFl;
    /**
     * 股票总价展示窗填充View
     */
    private View mTotalPricePopView;
    /**
     * 股票总价展示窗对象
     */
    private PopupWindow mTotalPricePopWindow;
    /**
     * 底部FragmentViewPager
     */
    private BottomFragmentVp mBottomFragmentVp;
    /**
     * 底部viewpager数据集
     */
    private List<Fragment> mBottomFragmentsList = new ArrayList<>();
    /**
     * 第一页持仓碎片
     */
    private BottomHoldeFragment mBottomHoldeFragment;
    /**
     * 底部vP标题
     */
    private TextView mTitleOfBottom;
    /**
     * 第一页持仓碎片适配器
     */
    private HolderAccountAdapter mHolderAccountAdapter;
    /**
     * 第三页撤单碎片
     */
    private BottomRevocationFragment mBottomRevocationFragment;
    /**
     * 底部小圆点indicator父容器
     */
    private LinearLayout mBottomIndicator;
    /**
     * 现价
     */
    private TextView mNowPriceTv;
    /**
     * 涨幅
     */
    private TextView mIncreaseAmountTv;
    /**
     * 五档分割线
     */
    private ProgressBar mProgressBar;

    /**
     * 上一个小圆点
     */
    private int preSelectPagePos = 0;
    /**
     * 今日委托碎片
     */
    private BottomTodayEntrustFragment mBottomTodayEntrustFragment;
    /**
     * 可用资金
     */
    private TextView mCanUseMoneyTv;
    /**
     * 股东账号列表消失时间戳
     */
    private long mAccountPopdismissTime;


    /**
     * fragment页面根布局
     */
    private View mRootView;


    // 五档买卖盘控件定义开始：买一到买五，卖一到卖五。价格和数量
    private TextView mPriceBuy5;
    private TextView mPriceBuy4;
    private TextView mPriceBuy3;
    private TextView mPriceBuy2;
    private TextView mPriceBuy1;
    private TextView mPriceSale5;
    private TextView mPriceSale4;
    private TextView mPriceSale3;
    private TextView mPriceSale2;
    private TextView mPriceSale1;
    private TextView mAmountBuy5;
    private TextView mAmountBuy4;
    private TextView mAmountBuy3;
    private TextView mAmountBuy2;
    private TextView mAmountBuy1;
    private TextView mAmountSale1;
    private TextView mAmountSale2;
    private TextView mAmountSale3;
    private TextView mAmountSale4;
    private TextView mAmountSale5;
    /**
     * 五档盘行的集合，里面的每个LinearLayout代表五档盘的一行
     */
    private ArrayList<LinearLayout> mBuySaleDiskLlList;
    /**
     * 股东账号数据
     */
    private ArrayList<String> mHolderAccounts;
    /**
     * 装有股票代码和股票名称的LinearLayout，目前用来弹出模糊查询PopupWindow
     */
    private LinearLayout mLlStockCodeName;
    /**
     * 该类的宿主Activity
     */
    private MultiTradeActivity mActivity = null;
    /**
     * 该类的业务类
     */
    private BuyOrSellServiceImpl mService = null;
    /**
     * 控制该类的控制器
     */
    private BuyOrSellViewController mController = null;
    /**
     * 我的持仓数据的LisView的适配器
     */
    private TradeBottomFragmentVpAdapter mTradeBottomFragmentVpAdapter;

    /**
     * 买入或者卖出，买入：0，卖出：1
     * 请在本类构造方法执行完后就给这个变量赋值！
     */
    private int mBuyOrSell;
    /**
     * 上一次输入股票代码的长度
     */
    private int mLastInputStockCodeLength = 0;
    /**
     * 框架内的原生自绘键盘管理器，股票代码输入框{@link #mEdStockCode}使用
     */
    private KeyboardManager mStockCodeEdKeyboardManager;
    /**
     * 框架内的原生自绘键盘管理器，委托数量输入框{@link #mEdEntrustAmount}使用
     */
    private KeyboardManager mEntrustNumEDKeyboardManager;
    /**
     * 字体管理器，用于给本类界面上的文字或数字设置字体
     */
    private FontManager mFontManager;
    /**
     * 股票模糊查询管理器，用于管理一个popupwindow，
     * 这个popupwindow是用来展示股票代码的模糊查询结果的
     */
    private StockFuzzyQueryManager mStockFuzzyQueryManager;
    /**
     * 上一次的委托价格
     */
    private String mLastEntrustPrice = "";
    /**
     * 弹出的对话框
     */
    private PopupWindowInListView mPopupWindow;
    /**
     * 市价委托弹出框的适配器
     */
    private BuyOrSellForPopAdapter mAdapterForPop;
    /**
     * 市价委托深市委托状态
     */
    private String[] mShenEntrustWays;
    /**
     * 市价委托沪市委托状态
     */
    private String[] mHuEntrustWays;
    /**
     * 市价委托深市委托编号
     */
    private String[] mShenEntrustWaysNum;
    /**
     * 市价委托沪市委托编号
     */
    private String[] mHuEntrustWaysNum;
    /**
     * 全局资源对象
     */
    private Resources mResources;
    /**
     * 市价委托数据集
     */
    private ArrayList<MarketEntrustWay> mEntrustWaysList;
    /**
     * 昨收价格
     */
    private String mNowPrice = "";
    /**
     * 委托方式
     */
    private String mEntrustBs = "";
    /**
     * 设置到买入或者卖出按钮上的文字
     */
    private String mBtnBuyOrSaleText = "";
    /**
     * 联动到的最大可委托数量
     */
    private String mMaxCanUseStock = "";
    /**
     * 从行情获取的证券信息类
     */
    private CodeTableBean mCodeTableBean = null;
    /**
     * 开启定时器，每隔3秒刷新五档盘
     */
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            if (!TradeUtils.isFastClick()) {
                mService.getHoldList();
            }
            if (mCodeTableBean != null) {
                mService.requestStockWuDangPan(mCodeTableBean.getCode(),
                        mCodeTableBean.getMarket(), mCodeTableBean.getExchange_type(), false);
            }
            handler.postDelayed(this, 2000);
        }
    };
    /**
     * 总价显示窗控制
     */
    private Handler mPriceWindowHandler = new Handler();
    private Runnable mPriceWindowRunnable = new Runnable() {
        public void run() {
            hidePriceWindow();
        }
    };

    public BuyOrSellFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_a_buy_or_sell, null);
            findViews(mRootView);
            initData();
            setListeners();
            initViews();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 初始化股票模糊查询popupwindow的宽度和单击事件
        mStockFuzzyQueryManager.setPopupwindowWidth(mLlStockCodeName.getWidth());
        mStockFuzzyQueryManager.setPopupWindowReserveWidthReferView(mLlStockCodeName);
        mStockFuzzyQueryManager.initListViewPopupwindow(mController);
        if (mShowHolderAccountsTv.getText() == null) {
            mShowHolderAccountsTv.setText(LoginInfo.getSelectHolderAccount());
        }

        // 初始化股东账号popwindow
        //从持仓跳转至此
        setDataToViewsFromHoldList();
        // 开启自动刷新模式
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 2000);

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        //切换的时候，清空所有数据
        clearDataInViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        if (mHolderAccounts != null && mHolderAccounts.size() != 0) {
            mHolderAccounts.clear();
        }
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
    }

    @Override
    protected void findViews(View view) {
        mEdStockCode = (ClearEditText) view.findViewById(R.id.edt_stock_code);
        mTvUpLimit = (TextView) view.findViewById(R.id.tv_up_limit);
        mTvDownLimit = (TextView) view.findViewById(R.id.tv_down_limit);
        mTvSubtract = (LinearLayout) view.findViewById(R.id.ibt_subtract);
        mTvAdd = (LinearLayout) view.findViewById(R.id.ibt_add);
        mEdStockPrice = (EditText) view.findViewById(R.id.edt_stock_price_after);
        mEdStockPricePre = (TextView) view.findViewById(R.id.edt_stock_price_pre);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mBtnBuyOrSell = (Button) view.findViewById(R.id.btn_buy_or_sell);
        mLlStockCodeName = (LinearLayout) mRootView.findViewById(R.id.ll_code_name);
        mAfterPriceView = (LinearLayout) mRootView.findViewById(R.id.ll_price_after);
        mDeletePriceIv = (ImageView) mRootView.findViewById(R.id.iv_delete_price);
        mInPutAmountFl = (FrameLayout) mRootView.findViewById(R.id.ll_input_amount);
        mMaxStockNumTv = (TextView) mRootView.findViewById(R.id.tv_stock_max_num);
        mStockNumPreTv = (TextView) mRootView.findViewById(R.id.tv_stock_num_pre);
        mEdEntrustAmount = (ClearEditText) view.findViewById(R.id.ed_entrust_num);

        mHolderAccount = (TextView) view.findViewById(R.id.tv_show_holder_account);
        mShowHolderAccountsTv = (TextView) view.findViewById(R.id.tv_show_pop_more_account);
        mShowHolderAccountsPreTv = (TextView) view.findViewById(R.id.tv_show_pop_more_account_pre);
        mPreAccountParentLl = (LinearLayout) view.findViewById(R.id.ll_account_pre);
        mAfterAccountParentLl = (LinearLayout) view.findViewById(R.id.ll_account_after);
        mAccountParentFl = (FrameLayout) view.findViewById(R.id.fl_account_container);
        mStockNumAfterView = (LinearLayout) view.findViewById(R.id.ll_stock_num_after);
        mAllNumTv = (TextView) view.findViewById(R.id.tv_all_num);
        mHalfNumTv = (TextView) view.findViewById(R.id.tv_half_num);
        mThirdNumTv = (TextView) view.findViewById(R.id.tv_third_num);
        mQuarterNumTv = (TextView) view.findViewById(R.id.tv_quarter_num);
        mBottomFragmentVp = (BottomFragmentVp) view.findViewById(R.id.vp_bottom_fragment);
        mTitleOfBottom = (TextView) view.findViewById(R.id.tv_header_of_grade_bottom);
        mBottomIndicator = (LinearLayout) view.findViewById(R.id.indcator_bottom_circle);
        mNowPriceTv = (TextView) view.findViewById(R.id.tv_now_price);
        mIncreaseAmountTv = (TextView) view.findViewById(R.id.tv_increase_amount);
        mCanUseMoneyTv = (TextView) view.findViewById(R.id.tv_can_use_money);
        mTv1 = (TextView) mRootView.findViewById(R.id.step1);
        mTv2 = (TextView) mRootView.findViewById(R.id.step2);
        // 五档买卖盘中的LinearLayout
        LinearLayout tempLl = (LinearLayout) view.findViewById(R.id.ll_buy1);
        mBuySaleDiskLlList = new ArrayList<LinearLayout>();
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy2);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy3);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy4);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_buy5);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale1);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale2);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale3);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale4);
        mBuySaleDiskLlList.add(tempLl);
        tempLl = (LinearLayout) view.findViewById(R.id.ll_sale5);
        mBuySaleDiskLlList.add(tempLl);

        // 五档买卖盘中LinearLayout中的TextView
        mPriceBuy5 = (TextView) mRootView.findViewById(R.id.tv_buy5_price);
        mPriceBuy4 = (TextView) mRootView.findViewById(R.id.tv_buy4_price);
        mPriceBuy3 = (TextView) mRootView.findViewById(R.id.tv_buy3_price);
        mPriceBuy2 = (TextView) mRootView.findViewById(R.id.tv_buy2_price);
        mPriceBuy1 = (TextView) mRootView.findViewById(R.id.tv_buy1_price);
        mPriceSale1 = (TextView) mRootView.findViewById(R.id.tv_sale1_price);
        mPriceSale2 = (TextView) mRootView.findViewById(R.id.tv_sale2_price);
        mPriceSale3 = (TextView) mRootView.findViewById(R.id.tv_sale3_price);
        mPriceSale4 = (TextView) mRootView.findViewById(R.id.tv_sale4_price);
        mPriceSale5 = (TextView) mRootView.findViewById(R.id.tv_sale5_price);
        mAmountBuy5 = (TextView) mRootView.findViewById(R.id.tv_buy5_amount);
        mAmountBuy4 = (TextView) mRootView.findViewById(R.id.tv_buy4_amount);
        mAmountBuy3 = (TextView) mRootView.findViewById(R.id.tv_buy3_amount);
        mAmountBuy2 = (TextView) mRootView.findViewById(R.id.tv_buy2_amount);
        mAmountBuy1 = (TextView) mRootView.findViewById(R.id.tv_buy1_amount);
        mAmountSale1 = (TextView) mRootView.findViewById(R.id.tv_sale1_amount);
        mAmountSale2 = (TextView) mRootView.findViewById(R.id.tv_sale2_amount);
        mAmountSale3 = (TextView) mRootView.findViewById(R.id.tv_sale3_amount);
        mAmountSale4 = (TextView) mRootView.findViewById(R.id.tv_sale4_amount);
        mAmountSale5 = (TextView) mRootView.findViewById(R.id.tv_sale5_amount);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_CLICK, mTvSubtract, mController);
        registerListener(ListenerController.ON_CLICK, mTvAdd, mController);
        registerListener(ListenerController.ON_CLICK, mBtnBuyOrSell, mController);
        registerListener(ListenerController.ON_CLICK, mTvUpLimit, mController);
        registerListener(ListenerController.ON_CLICK, mTvDownLimit, mController);
        registerListener(BuyOrSellViewController.ON_TEXT_CHANGE, mEdStockCode, mController);
        registerListener(BuyOrSellViewController.ON_FOCUS_CHANGE, mEdStockPrice, mController);
        //显示股东账号按钮监听
        RxView.touches(mShowHolderAccountsPreTv).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Action1<MotionEvent>() {
            @Override
            public void call(MotionEvent motionEvent) {
                onClickPopAccount();
                hidePreAccountView();
            }
        });
        RxView.clicks(mShowHolderAccountsTv).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                onClickPopAccount();
            }
        });
        registerListener(ListenerController.ON_CLICK, mEdStockPricePre, mController);
        registerListener(ListenerController.ON_CLICK, mStockNumPreTv, mController);
        registerListener(ListenerController.ON_CLICK, mDeletePriceIv, mController);
        registerListener(ListenerController.ON_CLICK, mAllNumTv, mController);
        registerListener(ListenerController.ON_CLICK, mHalfNumTv, mController);
        registerListener(ListenerController.ON_CLICK, mThirdNumTv, mController);
        registerListener(ListenerController.ON_CLICK, mQuarterNumTv, mController);
        registerListener(ListenerController.ON_CLICK, mLlStockCodeName, mController);
        mBottomFragmentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                                      @Override
                                                      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                                      }

                                                      @Override
                                                      public void onPageSelected(int position) {
                                                          ImageView iv = (ImageView) mBottomIndicator.getChildAt(position);
                                                          iv.setImageResource(R.drawable.enabletrue);
                                                          ImageView preIv = (ImageView) mBottomIndicator.getChildAt(preSelectPagePos);
                                                          preIv.setImageResource(R.drawable.enablefalse);
                                                          preSelectPagePos = position;
                                                          mBottomIndicator.invalidate();
                                                          Fragment item = mTradeBottomFragmentVpAdapter.getItem(position);
                                                          if (item instanceof BottomHoldeFragment) {
                                                              mTitleOfBottom.setText(BottomHoldeFragment.TITLE);
                                                          } else if (item instanceof BottomRevocationFragment)

                                                          {
                                                              mTitleOfBottom.setText(BottomRevocationFragment.TITLE);
                                                          } else

                                                          {
                                                              mTitleOfBottom.setText("当日委托");
                                                          }
                                                      }

                                                      @Override
                                                      public void onPageScrollStateChanged(int state) {

                                                      }
                                                  }

        );
        mEdStockPrice.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {
                                                             requestLinkOnPriceChange();
                                                         }
                                                     }

        );


        mEdEntrustAmount.setOnFocusChangeListener(new View.OnFocusChangeListener()

                                                  {
                                                      @Override
                                                      public void onFocusChange(View v, boolean hasFocus) {
                                                          if (!hasFocus && mEdEntrustAmount.getText().toString().length() == 0) {
                                                              resetPreNumView();
                                                          }
                                                      }
                                                  }

        );
        for (
                LinearLayout ll
                : mBuySaleDiskLlList)

        {
            registerListener(ListenerController.ON_CLICK, ll, mController);
        }

        mPriceTextWatcher = new

                PriceTextWatcher();

        mEdStockPrice.addTextChangedListener(mPriceTextWatcher);

        //股票数量edt监听数量
        mNumTextWatcher = new

                NumTextWatcher();

        mEdEntrustAmount.addTextChangedListener(mNumTextWatcher);
    }

    @Override
    protected void initData() {
        mService = new BuyOrSellServiceImpl(this);
        addBottomFragments();
        mActivity = (MultiTradeActivity) getActivity();
        mTradeBottomFragmentVpAdapter = new TradeBottomFragmentVpAdapter(getChildFragmentManager(), mBottomFragmentsList);
        mResources = CoreApplication.getInstance().getResources();
        mController = new BuyOrSellViewController(this);
        mFontManager = FontManager.getInstance(mActivity);
        mAdapterForPop = new BuyOrSellForPopAdapter(mActivity);
        mPopupWindow = new PopupWindowInListView(mActivity, itemsOnClick);
        if (mBuyOrSell == 0) { // 如果是买入
            mEntrustBs = "0";
            mBtnBuyOrSaleText = mResources.getString(R.string.trade_buying);
            mShenEntrustWays = mResources.getStringArray(R.array.shen_buy_ways);
            mHuEntrustWays = mResources.getStringArray(R.array.hu_buy_ways);
            mShenEntrustWaysNum = mResources.getStringArray(R.array.shen_buy_num);
            mHuEntrustWaysNum = mResources.getStringArray(R.array.hu_buy_num);
        } else if (mBuyOrSell == 1) { // 如果是卖出
            mEntrustBs = "1";
            mBtnBuyOrSaleText = mResources.getString(R.string.trade_sell);
            mShenEntrustWays = mResources.getStringArray(R.array.shen_sell_ways);
            mHuEntrustWays = mResources.getStringArray(R.array.hu_sell_ways);
            mShenEntrustWaysNum = mResources.getStringArray(R.array.shen_sell_num);
            mHuEntrustWaysNum = mResources.getStringArray(R.array.hu_sell_num);
        }
        mBtnBuyOrSell.setText(mBtnBuyOrSaleText);
        mStockFuzzyQueryManager = StockFuzzyQueryManager.getInstance(mActivity);
        mEntrustWaysList = new ArrayList<MarketEntrustWay>();
        mLastEntrustPrice = "0";
        mBottomFragmentVp.setAdapter(mTradeBottomFragmentVpAdapter);
        //初始化小圆点
        addCircles();
    }

    private void addCircles() {
        //动态添加白色点所有对应的indicator的内容
        for (int i = 0; i < mTradeBottomFragmentVpAdapter.getCount(); i++) {
            ImageView ivIndicator = new ImageView(getContext());
            if (i != 0) {
                ivIndicator.setImageResource(R.drawable.enablefalse);
            } else {
                ivIndicator.setImageResource(R.drawable.enabletrue);
            }
            int unit = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()) + .5f);
            int width = unit;//10dp<--->10px
            int height = unit;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

            if (i != 0) {//不是第一个点的时候设置对应的marginLeft
                params.leftMargin = unit;
            }
            mBottomIndicator.addView(ivIndicator, params);
        }
    }

    private void addBottomFragments() {
        if (mBottomFragmentsList != null && mBottomFragmentsList.size() > 0) {
            return;
        }
        //底部持仓
        mBottomHoldeFragment = new BottomHoldeFragment();
        mBottomHoldeFragment.setFragment(this);
        mBottomHoldeFragment.setService(mService);
        mBottomFragmentsList.add(mBottomHoldeFragment);
        //当日委托
        mBottomTodayEntrustFragment = new BottomTodayEntrustFragment();
        mBottomTodayEntrustFragment.setFragment(this);
        mBottomFragmentsList.add(mBottomTodayEntrustFragment);
        //底部撤单
        mBottomRevocationFragment = new BottomRevocationFragment();
        mBottomRevocationFragment.setFragment(this);
        mBottomFragmentsList.add(mBottomRevocationFragment);

    }

    @Override
    protected void initViews() {
        // 如果是买入
        if (mBuyOrSell == 0) {
            mBtnBuyOrSell.setBackgroundResource(R.drawable.trade_buy);
            // 设置“价格加减号”按钮样式
            mTvAdd.setBackgroundColor(mResources.getColor(R.color.trade_buy));
            mTvSubtract.setBackgroundColor(mResources.getColor(R.color.trade_buy));
            // 设置各个边框背景
            mRootView.findViewById(R.id.ll_code_name).setBackgroundResource(R.drawable.buy_no_corner_kong);
            mRootView.findViewById(R.id.ll_input_price).setBackgroundResource(R.drawable.buy_no_corner_kong);

            mInPutAmountFl.setBackgroundResource(R.drawable.buy_no_corner_kong);
            mAccountParentFl.setBackgroundResource(R.drawable.buy_no_corner_kong);
            mTvAdd.setBackgroundColor(mResources.getColor(R.color.trade_buy));
            mTvSubtract.setBackgroundColor(mResources.getColor(R.color.trade_buy));

            // 如果是卖出
        } else if (mBuyOrSell == 1) {
            mBtnBuyOrSell.setBackgroundResource(R.drawable.trade_sale);
            // 设置“价格加减号”按钮样式
            mTvAdd.setBackgroundColor(mResources.getColor(R.color.trade_sale));
            mTvSubtract.setBackgroundColor(mResources.getColor(R.color.trade_sale));
            // 设置各个边框背景
            mRootView.findViewById(R.id.ll_code_name).setBackgroundResource(R.drawable.sale_no_corner_kong);
            mRootView.findViewById(R.id.ll_input_price).setBackgroundResource(R.drawable.sale_no_corner_kong);
            mAccountParentFl.setBackgroundResource(R.drawable.sale_no_corner_kong);
            mInPutAmountFl.setBackgroundResource(sale_no_corner_kong);
            mTv1.setBackgroundColor(mResources.getColor(R.color.trade_sale));
            mTv2.setBackgroundColor(mResources.getColor(R.color.trade_sale));
        }
        mStockCodeEdKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdStockCode, KeyboardManager.KEYBOARD_TYPE_STOCK, new TradeTools.OnFocusChangeWithKeyboard() {
            @Override
            public void onFocusChange(boolean hasFocus) {
                if (hasFocus) {
                    showOptionalList();
                    if (!TextUtils.isEmpty(mEdStockCode.getText())) {
                        mEdStockCode.setText(mEdStockCode.getText().toString().substring(0, 6));
                        mEdStockCode.performClick();
                        mEdStockCode.requestFocus();
                        setEdtCursor(mEdStockCode);
                    }
                }
            }
        }, new TradeTools.OnClickWithKeyboard() {
            @Override
            public void onClick() {
                showOptionalList();
            }
        });
        //拓展键盘确定按钮功能
        mStockCodeEdKeyboardManager.setOnKeyCodeDownListener(this);

        mEntrustNumEDKeyboardManager = TradeTools.initKeyBoard(mActivity, mEdEntrustAmount, KeyboardManager.KEYBOARD_TYPE_MERCHANDISE);
        setDataToViewsFromHq();
        // 调用业务类中，请求数据的方法
        mService.getHoldList();
        setTheme();

        // 设置字体
        mFontManager.setTextFont(mTvUpLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mTvDownLimit, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mEdStockPrice, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mEdEntrustAmount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mBtnBuyOrSell, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceBuy1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mPriceSale1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountBuy1, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale5, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale4, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale3, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale2, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(mAmountSale1, FontManager.FONT_DINPRO_MEDIUM);
    }


    @Override
    protected void setTheme() {
    }

    /**
     * 在我的行情个股页面，点击买卖跳转至此
     */
    private void setDataToViewsFromHq() {
        Bundle bundle = mActivity.getIntent().getExtras();
        if (bundle != null) {
            String codeFormHq = bundle.getString("hq_stock_code");
            String market = bundle.getString("hq_market");
            int viewPagerFragmentPos = bundle.getInt("ViewPagerFragmentPos");
            if (!TextUtils.isEmpty(codeFormHq) && viewPagerFragmentPos == mBuyOrSell) {
                mService.request20000ForHqData(codeFormHq, market);
                //移除bundle中的数据，避免影响下次操作
                bundle.remove("hq_stock_code");
                bundle.remove("hq_market");
                bundle.remove("ViewPagerFragmentPos");
            }
        }
    }

    /**
     * 在我的持仓页面，点击买卖跳转至此
     */
    private void setDataToViewsFromHoldList() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String default_stock_code = bundle.getString("hold_stock_code");
            String hold_market = bundle.getString("hold_market");
            if (!TextUtils.isEmpty(default_stock_code)) {
                mService.request20000ForHqData(default_stock_code, hold_market);
                //移除bundle中的数据，避免影响下次操作
                bundle.remove("hold_stock_code");
                bundle.remove("hold_market");
            }
        }
    }

    /**
     * 当价格输入框的内容发生改变时，执行此方法
     */
    private void requestLinkOnPriceChange() {
        String entrustCode = getEntrustCode();
        String curEntrustPrice = getEntrustPrice();
        if (mCodeTableBean != null && !TextUtils.isEmpty(mMaxCanUseStock) &&
                !TextUtils.isEmpty(curEntrustPrice) && !mLastEntrustPrice.equals(curEntrustPrice)) {
            mService.requestLinkageData(entrustCode, curEntrustPrice, mCodeTableBean.getMarket(),
                    mCodeTableBean.getExchange_type(), String.valueOf(mBuyOrSell));
            mLastEntrustPrice = curEntrustPrice;
        }
    }

    /**
     * 当股票代码输入框{@link #mEdStockCode}中的输入内容改变的时候
     */
    public void onSearchTextChange() {
        //内部的监听被外部设置取代 要处理
        if (!TextUtils.isEmpty(mEdStockCode.getText()) && mEdStockCode.getText().toString().length() < 7) {
            mEdStockCode.setClearIconVisible(true);
            mEdStockCode.invalidate();
        } else {
            mEdStockCode.setClearIconVisible(false);
            mEdStockCode.invalidate();
        }
        // 当前股票代码输入框输入的内容
        String curEditContent = getEntrustCode();
        // 当前输入内容的长度
        int curInputLength = curEditContent.length();
        setLinkageState(false);
        // 没有输入完成时
        if (curInputLength >= 1 && curInputLength < 6) {
            clearDataInViewsExpectStockCodeEd();
            //判断输入的是否是数字
            if (TradeUtils.isNumeric(curEditContent)) {
                //如果是数字（输入4位及以上开始模糊查询）
                if (curInputLength > 3 && curInputLength < 6) {
                    mStockFuzzyQueryManager.execQuery(curEditContent, "1", mEdStockCode);
                }
            } else { //如果是字母（输入2位及以上开始模糊查询）
                mStockFuzzyQueryManager.execQuery(curEditContent, "1", mEdStockCode);
            }
        } else if (curInputLength == 6) {
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            mStockCodeEdKeyboardManager.dismiss();
            // 主要校验是否是用户输入的代码
            if (mLastInputStockCodeLength == 5 || mLastInputStockCodeLength == 3) {
                onStockLengthMax(curEditContent, mEdStockCode);
            }
        } else if (curInputLength == 0) {
            clearDataInViewsExpectStockCodeEd();
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            mHolderAccount.setText(LoginInfo.setSelectHolderAccount(""));
            resetPreAccountView();
            mMaxStockNumTv.setText("");
        }

        //控制最大可编辑长度为6
        if (mEdStockCode.getText().toString().length() > 6 && mEdStockCode.getText().toString().length() < 10) {
            mEdStockCode.setText(mEdStockCode.getText().toString().substring(0, 6));
            //            mEdStockCode.performClick();
            mEdStockCode.requestFocus();
            setEdtCursor(mEdStockCode);
        }
        mLastInputStockCodeLength = curInputLength;
        if (!TextUtils.isEmpty(mEdStockPrice.getText())) {
            setEdtCursor(mEdStockPrice);
        }
    }

    /**
     * 当用户输入满6位时
     */
    private void onStockLengthMax(String curEditContent, final View edStockCode) {
        mStockFuzzyQueryManager.execQuery(curEditContent, "1", new StockFuzzyQueryManager.IHqCallBackStock() {
            @Override
            public void onGetStockMsg(ArrayList<CodeTableBean> dataList) {
                mStockFuzzyQueryManager.dismissQueryPopupWindow();
                if (dataList != null && dataList.size() > 0) {
                    if (dataList.size() == 1) {
                        CodeTableBean bean = dataList.get(0);
                        hidePreAccountView();
                        mEdStockCode.setText(bean.getCode() + "(" + bean.getName() + ")");
                        mEdStockCode.clearFocus();
                        mService.request20000ForHqData(bean.getCode(), bean.getMarket());
                    } else {
                        SearchStocksAdapter adapter = mStockFuzzyQueryManager.getSearchStocksAdapter();
                        adapter.setListData(dataList);
                        adapter.notifyDataSetChanged();
                        mStockFuzzyQueryManager.showQueryPopupWindow(edStockCode);
                    }
                } else {
                    ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.toast_data_error));
                }
            }
        });
    }


    /**
     * 为弹出窗口实现监听类
     */
    private ListView.OnItemClickListener itemsOnClick = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            clickPopList(position);
        }
    };

    /**
     * 点击市价委托弹出框的item
     *
     * @param position
     */
    public void clickPopList(int position) {
        MarketEntrustWay bean = mAdapterForPop.getItem(position);
        mEntrustBs = bean.getEntrust_way_num();
        //改变市价委托时，重新联动最大可买
        if (!TextUtils.isEmpty(mMaxCanUseStock)) {
            mService.requestLinkageData(getEntrustCode(), "", mCodeTableBean.getMarket(),
                    mCodeTableBean.getExchange_type(), String.valueOf(mBuyOrSell));
        }
        mPopupWindow.dismiss();
    }

    /**
     * 展示股票代码模糊查询结果的listview的item单击事件
     *
     * @param position 被单击的item的position
     */
    public void onSearchListViewItemClick(int position) {
        CodeTableBean bean = mStockFuzzyQueryManager.getSearchStocksAdapter().getItem(position);
        mService.request20000ForHqData(bean.getCode(), bean.getMarket());
        mEdStockCode.setText(bean.getCode() + "(" + bean.getName() + ")");
        mEdStockCode.clearFocus();
        TradeUtils.hideSystemKeyBoard(mActivity);
        TradeUtils.hideSystemKeyBoard(mActivity);
        mStockFuzzyQueryManager.dismissQueryPopupWindow();
        mStockCodeEdKeyboardManager.dismiss();
        mLastInputStockCodeLength = 6;
    }

    /**
     * “涨停价”按钮单击监听事件
     */
    public void onClickUpLimit() {
        mEdStockPrice.setText(mTvUpLimit.getText().toString().trim());
    }

    /**
     * “跌停价”按钮单击事件监听
     */
    public void onClickDownLimit() {
        mEdStockPrice.setText(mTvDownLimit.getText().toString().trim());
    }

    /**
     * 交易价格+，按钮单击事件
     */
    public void onClickTradeAdd() {
        double curTradePrice = getTradePriceEditText();
        if (curTradePrice != 0) {
            if (mCodeTableBean != null) {
                double step = Double.parseDouble(mCodeTableBean.getStep_unit());
                if (mService.mCount == 2) {
                    mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice + step));
                } else {
                    mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice + step));
                }
            } else {
                mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice + 0.01));
            }
            setEdtCursor(mEdStockPrice);
        }
    }

    private void setStep(double step) {
        mTv1.setText(step + "");
        mTv2.setText(step + "");
    }

    /**
     * 交易价格-，按钮单击事件
     */
    public void onClickTradeSubstract() {
        double curTradePrice = getTradePriceEditText();
        if (curTradePrice != 0) {
            if (mCodeTableBean != null) {
                double step = Double.parseDouble(mCodeTableBean.getStep_unit());
                if (mService.mCount == 2) {
                    mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice - step));
                } else {
                    mEdStockPrice.setText(TradeUtils.formatDouble3(curTradePrice - step));
                }
            } else {
                mEdStockPrice.setText(TradeUtils.formatDouble2(curTradePrice - 0.01));
            }
            setEdtCursor(mEdStockPrice);
        }
    }

    /**
     * 股票名称单击事件
     */
    public void onClickStockName() {
        // 注意！此处代码务必和 mEdStockCode 焦点获取监听事件一致
        if (mEdStockCode.hasFocus()) {
            showOptionalList();
            mStockCodeEdKeyboardManager.show();

        } else {
            mEdStockCode.performClick();
            mEdStockCode.requestFocus();
            showOptionalList();
        }
    }

    /**
     * 买入或者卖出按钮单击事件
     * 弹出确认操作对话框对话框，设置对话框布局，然后监听对话框中按钮的单击事件
     */
    public void onClickTrade() {
        if (TradeUtils.isFastClick2()) {
            return;
        }
        String entrustPrice = getEntrustPrice();
        String entrustAmount = getEntrustAmount();
        String stockCode = getEntrustCode();
        // 没正确输入股票代码时
        if (TextUtils.isEmpty(stockCode) || stockCode.length() != 6) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_code));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        // 联动失败时允许下单，前提是20000接口调用成功
        if (mCodeTableBean == null) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_code2));
            TradeUtils.showKeyBoard(mActivity, mEdStockCode, false);
            return;
        }
        // 没输入委托数量时
        if (entrustAmount == null || entrustAmount.equals("")) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_amount));
            TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
            return;
        }
        //若是限价委托
        if (mRootView.findViewById(R.id.ll_input_price).getVisibility() == View.VISIBLE) {
            if (entrustPrice == null || entrustPrice.equals("")) { // 没输入委托价格时
                ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_price));
                mEntrustNumEDKeyboardManager.dismiss();
                mStockCodeEdKeyboardManager.dismiss();
                TradeUtils.showKeyBoard(mActivity, mEdStockPrice, true);
                return;
            }
        }
        //若是市价委托
        if (mEntrustBs == null || TextUtils.isEmpty(mEntrustBs)) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_select_bs));
            return;
        }
        try {
            double entrustAmountDouble = Double.parseDouble(entrustAmount);
            double entrustMaxAmountDouble = Double.parseDouble(mMaxCanUseStock);
            // 如果是买入，那么数量必须能被100整除，否则报错
            if (mBuyOrSell == 0 && entrustAmountDouble % 100 != 0) {
                ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_buy_amount_error));
                TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
                return;
                // 如果是卖出，那么数量必须能被100整除，或者等于最大可卖数量，否则报错
            } else if (mBuyOrSell == 1 && entrustAmountDouble % 100 != 0 && entrustAmountDouble != entrustMaxAmountDouble) {
                ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_sale_amount_error));
                TradeUtils.showKeyBoard(mActivity, mEdEntrustAmount, false);
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);
        ATradeComfirmDialog dialog = new ATradeComfirmDialog(mActivity, mBuyOrSell, mService);
        dialog.setDataToViews(mCodeTableBean, getEntrustPrice(), getEntrustAmount());
        dialog.setEntrustBs(mEntrustBs);
        dialog.show();


    }

    /**
     * 点击五档盘
     *
     * @param v
     */
    public void onClickBuyOrSaleDisk(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ll_buy1) {
            mEdStockPrice.setText(mPriceBuy1.getText().toString());
        } else if (viewId == R.id.ll_buy2) {
            mEdStockPrice.setText(mPriceBuy2.getText().toString());
        } else if (viewId == R.id.ll_buy3) {
            mEdStockPrice.setText(mPriceBuy3.getText().toString());
        } else if (viewId == R.id.ll_buy4) {
            mEdStockPrice.setText(mPriceBuy4.getText().toString());
        } else if (viewId == R.id.ll_buy5) {
            mEdStockPrice.setText(mPriceBuy5.getText().toString());
        } else if (viewId == R.id.ll_sale1) {
            mEdStockPrice.setText(mPriceSale1.getText().toString());
        } else if (viewId == R.id.ll_sale2) {
            mEdStockPrice.setText(mPriceSale2.getText().toString());
        } else if (viewId == R.id.ll_sale3) {
            mEdStockPrice.setText(mPriceSale3.getText().toString());
        } else if (viewId == R.id.ll_sale4) {
            mEdStockPrice.setText(mPriceSale4.getText().toString());
        } else if (viewId == R.id.ll_sale5) {
            mEdStockPrice.setText(mPriceSale5.getText().toString());
        }
        setEdtCursor(mEdStockPrice);
        for (LinearLayout ll : mBuySaleDiskLlList) {
            if (viewId == ll.getId()) {
                ll.setBackgroundResource(R.drawable.common_no_corner_transparent);
            } else {
                ll.setBackgroundColor(mResources.getColor(R.color.transparent));
            }
        }
    }

    /**
     * 弹出popupwindow，显示自选股列表
     */
    public void showOptionalList() {
        if (TextUtils.isEmpty(getEntrustCode())) {
            mStockFuzzyQueryManager.dismissQueryPopupWindow();
            //屏蔽自选股
            //            mStockFuzzyQueryManager.execQueryOptional();
            //            mStockFuzzyQueryManager.showQueryPopupWindow(mEdStockCode);
        }
    }

    /**
     * 监听价格输入框失去焦点事件，失去焦点时请求联动接口更新数据
     */
    public void onPriceEdtFocusChange(boolean hasFocus) {
        if (!hasFocus) {
            if (mEdStockPrice.getText().toString().length() == 0) {
                resetPrePriceView();
            }
            requestLinkOnPriceChange();
        } else {
            if (mEdStockPrice.getText() != null) {
                setEdtCursor(mEdStockPrice);
            }
        }
    }

    /**
     * 从服务器返回了委托交易（买入或卖出）成功时执行
     */
    public void onSuccessEntrustTrade(String resultStr) {
        MessageOkCancelDialog dialog = new MessageOkCancelDialog(mActivity, null);
        dialog.setMsgText(resultStr);
        dialog.setCancelBtnVisiable(false);
        dialog.show();

    }

    /**
     * 在成功从服务器获得股票联动数据时
     *
     * @param bean 装联动数据的实体类
     */
    public void
    onGetStockLinkAgeData(StockLinkageBean bean) {
        mMaxCanUseStock = bean.getStock_max_amount();
        setLinkageState(true);
        mMaxStockNumTv.setText(mMaxCanUseStock + "");
        //服务端帮忙设置的股东账号,默认初始化是exchangetype相同的账号信息第一条的股东账号,当价格编辑失去焦点 联动查询按照服务器的提示更新股东账号
        filtLoginfoStockLimitList(bean);
        hidePreAccountView();
        mHolderAccount.setText(LoginInfo.setSelectHolderAccount(bean.getStock_account()));
        if (mHolderAccounts == null) {
            mHolderAccounts = new ArrayList<>();
        }
        mHolderAccounts.clear();
        for (SignStockAccountLimitBean b : LoginInfo.getFilStockLimitBeans()) {
            mHolderAccounts.add(b.getStock_account());
        }
        if (mHolderAccountAdapter == null) {
            mHolderAccountAdapter = new HolderAccountAdapter(mActivity, mHolderAccounts, mHolderAccount) {
                @Override
                protected void onItemClick(String accountString, int position) {
                    hidePreAccountView();
                    mHolderAccount.setText(LoginInfo.setSelectHolderAccount(accountString));
                    notifyDataSetChanged();
                    if (mAccountPopWindow.isShowing()) {
                        mAccountPopWindow.dismiss();
                    }
                }
            };
        } else {
            mHolderAccountAdapter.setDataList(mHolderAccounts);
        }
        if (mAccountPopWindow == null) {
            return;
        }
        if (mHolderAccounts.size() >= 3) {
            mAccountPopWindow.setHeight(SizeUtil.dp2px(mActivity, 105 + 1));
        } else if (mHolderAccounts.size() == 1) {
            mAccountPopWindow.setHeight(SizeUtil.dp2px(mActivity, 35));
        } else if (mHolderAccounts.size() == 2) {
            mAccountPopWindow.setHeight(SizeUtil.dp2px(mActivity, 70.5f));
        } else if (mHolderAccounts.size() == 0) {
            return;
        }
        mAccountPopWindow.update();

    }

    //联动查询后获取的exchange_type作为入参money_type的判断条件进行可用资金查询
    private void filtLoginfoStockLimitList(StockLinkageBean bean) {
        ArrayList<SignStockAccountLimitBean> stockLimitBeans = LoginInfo.getStockLimitBeans();
        ArrayList<SignStockAccountLimitBean> cList = new ArrayList<>();
        String exchange_type = bean.getExchange_type();
        if (exchange_type == "0" || exchange_type == "1") {
            for (int i = 0; i < stockLimitBeans.size(); i++) {
                if ("0".equals(stockLimitBeans.get(i).getExchange_type()) || "1".equals(stockLimitBeans.get(i).getExchange_type())) {
                    cList.add(stockLimitBeans.get(i));
                }
            }
        } else {
            for (int i = 0; i < stockLimitBeans.size(); i++) {
                if ("2".equals(stockLimitBeans.get(i).getExchange_type()) || "3".equals(stockLimitBeans.get(i).getExchange_type())) {
                    cList.add(stockLimitBeans.get(i));
                }
            }
        }

        LoginInfo.setFilStockLimitBeans(cList);
    }


    /**
     * 获取行情数据时
     *
     * @param bean 装行情数据的实体类
     */
    public void onGetHqData(CodeTableBean bean) {
        mCodeTableBean = bean;
        mEdStockCode.setText(mCodeTableBean.getCode() + "(" + mCodeTableBean.getName() + ")");
        double step = Double.parseDouble(mCodeTableBean.getStep_unit());
        setStep(step);
        mTvUpLimit.setText(mCodeTableBean.getUpLimit());
        mTvDownLimit.setText(mCodeTableBean.getDownLimit());
        mNowPrice = mCodeTableBean.getNow();
        //根据市场设置市价委托方式
        String[] entrust_way = {};
        String[] entrust_way_num = {};
        if (mCodeTableBean.getMarket().equals("SZ")) {
            entrust_way = mShenEntrustWays;
            entrust_way_num = mShenEntrustWaysNum;
        } else if (mCodeTableBean.getMarket().equals("SH")) {
            entrust_way = mHuEntrustWays;
            entrust_way_num = mHuEntrustWaysNum;
        }
        int entrust_ways_length = entrust_way.length;
        mEntrustWaysList.clear();
        for (int i = 0; i < entrust_ways_length; i++) {
            MarketEntrustWay entrustWayBean = new MarketEntrustWay();
            entrustWayBean.setEntrust_way_name(entrust_way[i]);
            entrustWayBean.setEntrust_way_num(entrust_way_num[i]);
            mEntrustWaysList.add(entrustWayBean);
        }
        hidePreAccountView();
        hidePrePriceView();
        hidePreNumView();
    }

    private void hidePreNumView() {
        mStockNumPreTv.setVisibility(View.GONE);
        mStockNumAfterView.setVisibility(View.VISIBLE);
    }

    /**
     * 请求行情接口发现股票停牌时
     */
    public void onGetSuspendStock(String stockCode) {
        clearDataInViewsExpectStockCodeEd();
        mEdStockCode.setText(stockCode);
    }


    /**
     * 请求行情接口发现股票停牌时
     */
    public void onGetSuspendStock(String stockName, String stockCode) {
        clearDataInViewsExpectStockCodeEd();
        mEdStockCode.setText(stockCode + "(" + stockName + ")");
    }

    /**
     * 清空界面上的所有数字
     */
    public void clearDataInViews() {
        // 清除股票代码输入框上的数据
        // 清除其他控件上的数据
        mEdStockCode.setText("");
        mCanUseMoneyTv.setText("");
        clearDataInViewsExpectStockCodeEd();
        mEntrustNumEDKeyboardManager.dismiss();
        mStockCodeEdKeyboardManager.dismiss();
        TradeUtils.hideSystemKeyBoard(mActivity);

        resetPrePriceView();
        resetPreNumView();
    }

    /**
     * 清除除了股票代码输入框外的其他布局控件上的数据
     */
    public void clearDataInViewsExpectStockCodeEd() {
        final String blankStr = "";
        mTvUpLimit.setText(blankStr);
        mTvDownLimit.setText(blankStr);
        mEdStockPrice.setText(blankStr);
        mEdEntrustAmount.setText(blankStr);
        mEdEntrustAmount.setHint(blankStr);
        mMaxCanUseStock = "";
        mCodeTableBean = null;
        mNowPrice = blankStr;
        mLastEntrustPrice = blankStr;
        mService.mCount = 2;
        mEntrustWaysList.clear();
        if (!mEntrustBs.equals("0") && !mEntrustBs.equals("1")) {
            mEntrustBs = blankStr;
        }
        mCanUseMoneyTv.setText("");
        // 清除五档盘中的数据
        clearWuDangData();
        mHolderAccount.setText(LoginInfo.setSelectHolderAccount(""));
        if (mAccountPopWindow != null) {
            mAccountPopWindow.dismiss();
        }
    }

    /**
     * 在成功从服务器获取五档买卖盘数据时调用
     * 设置五档买卖盘数据和当前委托价格数据（当前委托价格，买入时取买一，卖出时取卖一）。
     */
    public void onGetWuDangDishData(StockBuySellDish bean, String market, String exchangeType, boolean isSetText, String nowPrice, String increase) {
        //现价和涨幅
        mNowPriceTv.setTextColor(mResources.getColor(R.color.trade_text));
        mIncreaseAmountTv.setTextColor(mResources.getColor(R.color.trade_text));
        mNowPriceTv.setText(nowPrice);
        mIncreaseAmountTv.setText(increase + "%");

        ArrayList<String> priceColorList = bean.getPriceColorsList();
        ArrayList<String> valueList = bean.getValueBuySale();
        setWuDangDataToViews(mPriceSale5, valueList.get(0), priceColorList.get(0));
        setWuDangDataToViews(mPriceSale4, valueList.get(1), priceColorList.get(1));
        setWuDangDataToViews(mPriceSale3, valueList.get(2), priceColorList.get(2));
        setWuDangDataToViews(mPriceSale2, valueList.get(3), priceColorList.get(3));
        setWuDangDataToViews(mPriceSale1, valueList.get(4), priceColorList.get(4));
        setWuDangDataToViews(mAmountSale5, valueList.get(5), priceColorList.get(5));
        setWuDangDataToViews(mAmountSale4, valueList.get(6), priceColorList.get(6));
        setWuDangDataToViews(mAmountSale3, valueList.get(7), priceColorList.get(7));
        setWuDangDataToViews(mAmountSale2, valueList.get(8), priceColorList.get(8));
        setWuDangDataToViews(mAmountSale1, valueList.get(9), priceColorList.get(9));
        setWuDangDataToViews(mPriceBuy1, valueList.get(10), priceColorList.get(10));
        setWuDangDataToViews(mPriceBuy2, valueList.get(11), priceColorList.get(11));
        setWuDangDataToViews(mPriceBuy3, valueList.get(12), priceColorList.get(12));
        setWuDangDataToViews(mPriceBuy4, valueList.get(13), priceColorList.get(13));
        setWuDangDataToViews(mPriceBuy5, valueList.get(14), priceColorList.get(14));
        setWuDangDataToViews(mAmountBuy1, valueList.get(15), priceColorList.get(15));
        setWuDangDataToViews(mAmountBuy2, valueList.get(16), priceColorList.get(16));
        setWuDangDataToViews(mAmountBuy3, valueList.get(17), priceColorList.get(17));
        setWuDangDataToViews(mAmountBuy4, valueList.get(18), priceColorList.get(18));
        setWuDangDataToViews(mAmountBuy5, valueList.get(19), priceColorList.get(19));
        //        initColorDividerView(valueList);
        //是否是自动刷新，如果是自动刷新，则不需要执行下面的逻辑
        if (isSetText) {
            if (mBuyOrSell == 0) { // 如果是买入，设置卖1价格到股票价格数据框中
                String buyOne = valueList.get(4);
                if (buyOne != null && !TextUtils.isEmpty(buyOne)) {
                    float buyFloat = Float.parseFloat(buyOne);
                    if (buyFloat > 0) {
                        mLastEntrustPrice = buyOne;
                    } else if (buyFloat <= 0) {
                        mLastEntrustPrice = mNowPrice;
                    }
                } else {
                    mLastEntrustPrice = mNowPrice;
                }
            } else if (mBuyOrSell == 1) { // 如果是卖出，设置买1价格到股票价格数据框中
                String saleOne = valueList.get(10);
                if (saleOne != null && !TextUtils.isEmpty(saleOne)) {
                    float saleFloat = Float.parseFloat(saleOne);
                    if (saleFloat > 0) {
                        mLastEntrustPrice = saleOne;
                    } else if (saleFloat <= 0) {
                        mLastEntrustPrice = mNowPrice;
                    }
                } else {
                    mLastEntrustPrice = mNowPrice;
                }
            }
            mEdStockPrice.setText(mLastEntrustPrice);
            setEdtCursor(mEdStockPrice);
            mService.requestLinkageData(getEntrustCode(), getEntrustPrice(), market, exchangeType, String.valueOf(mBuyOrSell));
        }
    }

    //买卖分割线比例变色
    private void initColorDividerView(ArrayList<String> valueList) {
        List<String> ls1 = valueList.subList(0, 5);
        List<String> ls2 = valueList.subList(5, 10);
        List<String> ls3 = valueList.subList(10, 15);
        List<String> ls4 = valueList.subList(15, 20);
        double sell = 0;
        double buy = 0;
        for (int i = 0; i < 5; i++) {
            sell += Double.parseDouble(ls1.get(i)) * Double.parseDouble(ls2.get(i));
            buy += Double.parseDouble(ls3.get(i)) * Double.parseDouble(ls4.get(i));
        }
        mProgressBar.setProgress((int) (sell / (sell + buy) * 100));
        mProgressBar.setSecondaryProgress(mProgressBar.getMax());
    }

    /**
     * 清空五档买卖盘和当前委托价格上的数据
     */
    public void clearWuDangData() {
        mProgressBar.setSecondaryProgress(0);
        mProgressBar.setProgress(0);
        mNowPriceTv.setText("");
        mIncreaseAmountTv.setText("");
        mPriceSale5.setText("");
        mPriceSale4.setText("");
        mPriceSale3.setText("");
        mPriceSale2.setText("");
        mPriceSale1.setText("");
        mAmountSale5.setText("");
        mAmountSale4.setText("");
        mAmountSale3.setText("");
        mAmountSale2.setText("");
        mAmountSale1.setText("");
        mPriceBuy1.setText("");
        mPriceBuy2.setText("");
        mPriceBuy3.setText("");
        mPriceBuy4.setText("");
        mPriceBuy5.setText("");
        mAmountBuy1.setText("");
        mAmountBuy2.setText("");
        mAmountBuy3.setText("");
        mAmountBuy4.setText("");
        mAmountBuy5.setText("");
        // 还原五档盘上TextView的颜色
        mPriceSale5.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale4.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale3.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale2.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceSale1.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mAmountSale5.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale4.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale3.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale2.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountSale1.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mPriceBuy1.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy2.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy3.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy4.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mPriceBuy5.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mAmountBuy1.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy2.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy3.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy4.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mAmountBuy5.setTextColor(mResources.getColor(R.color.trade_text_color9));
        // 清空委托价格数据
        mEdStockPrice.setText("");
    }

    /**
     * 根据入参设置本页面股票联动状态，本页面分两种状态，联动和不联动。
     * 联动时，涨停跌停价和五档买卖盘等控件可点击，并有附带效果；
     * 不联动时，或联动失败时，将上述控件设置为不可点击状态。
     *
     * @param linkageState 设置股票是否联动；true：进入联动
     */
    private void setLinkageState(boolean linkageState) {
        try {
            // 设置五档盘每一行的可用状态
            for (LinearLayout ll : mBuySaleDiskLlList) {
                ll.setEnabled(linkageState);
                // 背景色设为透明
                ll.setBackgroundColor(mResources.getColor(R.color.transparent));
            }
            // 涨停、跌停,市价委托弹出框 按钮的可用状态
            mTvUpLimit.setEnabled(linkageState);
            mTvDownLimit.setEnabled(linkageState);
            if (linkageState && mMaxCanUseStock != null && !TextUtils.isEmpty(mMaxCanUseStock)) {
                // 设置持仓键盘仓位选择键功能
                if (!mEdStockPrice.hasFocus()) {
                    mEdEntrustAmount.requestFocus();
                    mEntrustNumEDKeyboardManager.show();
                }
                mEntrustNumEDKeyboardManager.setKeyCodeListener(new KeyboardManager.KeyCodeListener() {
                    @Override
                    public void onKeyCode(int i) {
                        switch (i) {
                            case -11: // 单击了1/4仓
                                setAmountPos(4);
                                setEdtCursor(mEdEntrustAmount);
                                break;
                            case -12: // 单击了1/3仓
                                setAmountPos(3);
                                setEdtCursor(mEdEntrustAmount);
                                break;
                            case -13: // 单击了半仓
                                setAmountPos(2);
                                setEdtCursor(mEdEntrustAmount);
                                break;
                            case -14: // 单击了全仓
                                setAmountPos(1);
                                setEdtCursor(mEdEntrustAmount);
                                break;
                            case KeyboardEventListener.KEY_CODE_INCREMENT:
                                //// TODO: 2016/10/19  +100
                                if (!TextUtils.isEmpty(mEdEntrustAmount.getText())) {
                                    mEdEntrustAmount.setText((Integer.parseInt(mEdEntrustAmount.getText().toString()) + 100) / 100 * 100 + "");
                                    setEdtCursor(mEdEntrustAmount);
                                }
                                break;
                            //// TODO: 2016/10/19  -100 
                            case KeyboardEventListener.KEY_CODE_DECREMENT:
                                if (!TextUtils.isEmpty(mEdEntrustAmount.getText())) {
                                    int num = Integer.parseInt(mEdEntrustAmount.getText().toString()) / 100 * 100 - 100;
                                    mEdEntrustAmount.setText(num > 0 ? num + "" : "0");
                                    setEdtCursor(mEdEntrustAmount);
                                }
                                break;
                        }
                    }
                });
                int max_amount = 1;
                max_amount = Integer.parseInt(mMaxCanUseStock);
                if (mBuyOrSell == 0) { // 买入时，设置委托数量输入框的输入提示hint为：最多可买xx股
                    mMaxStockNumTv.setText(max_amount + "");
                } else if (mBuyOrSell == 1) { // 卖出时，设置委托数量输入框的输入提示hint为：最多可卖xx股
                    mMaxStockNumTv.setText(max_amount + "");
                }
            } else {
                // 不能联动时，设置持仓键盘仓位选择键无效
                mEntrustNumEDKeyboardManager.setKeyCodeListener(new KeyboardManager.KeyCodeListener() {
                    @Override
                    public void onKeyCode(int i) {
                        mEdEntrustAmount.setText("");
                        ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_select_stock_first));
                    }
                });
                mEdEntrustAmount.setText("");
                if (!mEntrustBs.equals("0") && !mEntrustBs.equals("1")) {
                    mEntrustBs = "";
                }
            }
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        }

    }


    /**
     * 设置单个数据到五档盘TextViews上面
     */
    private void setWuDangDataToViews(TextView textView, String data, String textColor) {
        textView.setText(data);
        if (!textColor.equals("")) {
            textView.setTextColor(Color.parseColor(textColor));
        }
    }

    /**
     * 仓位选择方法，当选择了相应仓位
     *
     * @param pos 对应的1/pos仓位，例如，传入2时，就表示1/2仓
     */
    public void setAmountPos(int pos) {
        int max_amount = 1;
        String amountStr = "";
        try {
            max_amount = Integer.parseInt(mMaxCanUseStock);
            if (mBuyOrSell == 1 && pos == 1) { //卖出时点击全仓
                amountStr = String.valueOf(max_amount);
            } else {
                amountStr = TradeUtils.formatNumToHundreds(max_amount / pos);
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        mEdEntrustAmount.setText(amountStr);
    }

    /**
     * 获取BuyOrSellServiceImpl 类传过来的持仓数据
     *
     * @param dataList 持仓列表数据表
     */
    public void getStoreData(ArrayList<MyStoreStockBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mBottomHoldeFragment.setNoDate();
        } else {
            mBottomHoldeFragment.notifyData(dataList);
        }
    }

    /**
     * 买入卖出的区分标志
     *
     * @param buyOrSell
     */
    public void setBuyOrSell(int buyOrSell) {
        mBuyOrSell = buyOrSell;
    }

    /**
     * 获取当前委托交易（买入或者卖出）的价格
     *
     * @return {@link #mEdStockPrice}中输入的价格
     */
    public String getEntrustPrice() {
        float result = 0;
        String entrustPrice = "";
        String priceEditTextContent = mEdStockPrice.getText().toString().trim();
        if (!priceEditTextContent.equals("")) {
            try {
                result = Float.parseFloat(priceEditTextContent);
                if (result <= 0) {
                    entrustPrice = "0";
                } else {
                    entrustPrice = String.valueOf(result);
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            } catch (NullPointerException nfe) {
                nfe.printStackTrace();
            }
        }
        return entrustPrice;
    }

    /**
     * 获取{@link #mEdStockPrice}中的文本信息
     *
     * @return {@link #mEdStockPrice}中的文本信息
     */
    private double getTradePriceEditText() {
        double result = 0;
        String priceEditTextContent = mEdStockPrice.getText().toString().trim();
        if (!priceEditTextContent.equals("")) {
            try {
                result = Double.parseDouble(priceEditTextContent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取当前委托交易（买入或者卖出）的交易数量
     *
     * @return {@link #mEdEntrustAmount}中输入的数量
     */
    public String getEntrustAmount() {
        return mEdEntrustAmount.getText().toString().trim();
    }

    public String getEntrustCode() {
        if (mEdStockCode.getText().toString().trim().length() > 6) {

            return mEdStockCode.getText().toString().trim().substring(0, 6);
        } else {
            return mEdStockCode.getText().toString().trim();
        }
    }

    /**
     * 当数量或者价格发生变化时，改变button上的数据
     */
    private void onListenerEdtNum() {
        String price = getEntrustPrice();
        String num = getEntrustAmount();
        try {
            if (!TextUtils.isEmpty(price) && !TextUtils.isEmpty(num)) {
                double fPrice = Double.parseDouble(price);
                double fNum = Double.parseDouble(num);
                BigDecimal bigDecimal = new BigDecimal(fPrice * fNum);
                if (mService.mCount == 2) {
                    plainPrice = TradeUtils.formatDouble2(bigDecimal.toPlainString());
                } else {
                    plainPrice = TradeUtils.formatDouble3(bigDecimal.toPlainString());
                }


                mPriceWindowHandler.removeCallbacks(mPriceWindowRunnable);
                //显示
                if (mTotalPricePopView == null) {
                    LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
                    mTotalPricePopView = inflater.inflate(R.layout.popwindow_total_price, null);
                    if (mTotalPricePopView.getParent() != null) {
                        ViewGroup parent = (ViewGroup) mTotalPricePopView.getParent();
                        parent.removeView(mTotalPricePopView);
                    }
                    //计算popwindow宽度
                    mTotalPricePopWindow = new PopupWindow(mTotalPricePopView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
                    mTotalPricePopWindow.setBackgroundDrawable(new BitmapDrawable());
                }
                TextView textView = (TextView) mTotalPricePopView.findViewById(R.id.tv_total_price);
                textView.setText(plainPrice.isEmpty() ? "0" : plainPrice);
                mTotalPricePopWindow.update();
                mTotalPricePopWindow.showAsDropDown(mInPutAmountFl, mInPutAmountFl.getWidth() - SizeUtil.dp2px(mActivity, 90), 0);
                mPriceWindowHandler.postDelayed(mPriceWindowRunnable, 2000);
            }
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    //显示股东账户选框

    public void onClickPopAccount() {
        if (TextUtils.isEmpty(mEdStockCode.getText())) {
            return;
        }
        mStockFuzzyQueryManager.dismissQueryPopupWindow();
        //股东账号的popwindow设置了点击外部消失,会和弹出popwindow按钮的事件冲突,所以用时间戳控制popwindow刚消失后不可以马上弹出
        if ((mAccountPopWindow != null && mAccountPopWindow.isShowing() || (new Date().getTime() - mAccountPopdismissTime) < 300)) {
            mAccountPopWindow.dismiss();
            if (mEdStockCode.getText().toString().length() < 6 && TextUtils.isEmpty(mHolderAccount.getText())) {
                resetPreAccountView();
            }
            return;
        }
        if (mHolderAccounts == null) {
            mHolderAccounts = new ArrayList<>();
        }
        mHolderAccounts.clear();
        //填充股东账号到适配器数据
        if (mEdStockCode.getText().toString().length() < 6) {
            for (SignStockAccountLimitBean bean : LoginInfo.getStockLimitBeans()) {
                String stock_account = bean.getStock_account();
                mHolderAccounts.add(stock_account);
            }
        } else {
            for (SignStockAccountLimitBean bean : getFilStockLimitBeans()) {
                String stock_account = bean.getStock_account();
                mHolderAccounts.add(stock_account);
            }
        }

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
        mPopupWindowView = inflater.inflate(R.layout.popwindow_account_select, null);
        setAccountPopupWindow();
        Drawable drawable = CoreApplication.getInstance().getResources().getDrawable(R.drawable.shape_bg_alph_popwindow);
        mAccountPopWindow.setBackgroundDrawable(drawable);
        mAccountPopWindow.setOutsideTouchable(true);
        ListView accountLv = (ListView) mPopupWindowView;
        if (mHolderAccountAdapter == null) {
            mHolderAccountAdapter = new HolderAccountAdapter(mActivity, mHolderAccounts, mHolderAccount) {
                @Override
                protected void onItemClick(String accountString, int position) {
                    hidePreAccountView();
                    mHolderAccount.setText(LoginInfo.setSelectHolderAccount(accountString));
                    notifyDataSetChanged();
                    if (mAccountPopWindow.isShowing()) {
                        mAccountPopWindow.dismiss();
                    }
                }
            };
        }
        accountLv.setAdapter(mHolderAccountAdapter);
        mHolderAccountAdapter.setDataList(mHolderAccounts);
        mAccountPopWindow.showAsDropDown(mAccountParentFl, 0, 0);
        mAccountPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mAccountPopdismissTime = new Date().getTime();
            }
        });
    }

    //更新股东账号view
    public void setAccountPopupWindow() {
        if (mHolderAccounts.size() >= 3) {
            mAccountPopWindow = new PopupWindow(mPopupWindowView, mAccountParentFl.getWidth(), SizeUtil.dp2px(mActivity, 105 + 1), false);
        } else if (mHolderAccounts.size() == 1) {
            mAccountPopWindow = new PopupWindow(mPopupWindowView, mAccountParentFl.getWidth(), SizeUtil.dp2px(mActivity, 35), false);
        } else if (mHolderAccounts.size() == 2) {
            mAccountPopWindow = new PopupWindow(mPopupWindowView, mAccountParentFl.getWidth(), SizeUtil.dp2px(mActivity, 70.5f), false);
        } else if (mHolderAccounts.size() == 0) {
            return;
        }
    }

    /*
    * 股东账号点击之后布局改变
    * */
    public void hidePreAccountView() {
        if (TextUtils.isEmpty(mEdStockCode.getText())) {
            return;
        }
        mPreAccountParentLl.setVisibility(View.GONE);
        mAfterAccountParentLl.setVisibility(View.VISIBLE);
    }

    //复位股东账号布局
    public void resetPreAccountView() {
        mPreAccountParentLl.setVisibility(View.VISIBLE);
        mAfterAccountParentLl.setVisibility(View.GONE);
    }

    //股票价格替换之前的布局
    public void onPrePriceClick() {
        mStockFuzzyQueryManager.dismissQueryPopupWindow();
        hidePrePriceView();
    }

    private void hidePrePriceView() {
        mEdStockPricePre.setVisibility(View.GONE);
        mAfterPriceView.setVisibility(View.VISIBLE);
        mEdStockPrice.setFocusable(true);
        mEdStockPrice.requestFocus();
        mEdStockCode.clearFocus();
        setEdtCursor(mEdStockPrice);
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEdStockPrice, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void resetPrePriceView() {
        if (!TextUtils.isEmpty(mEdStockCode.getText())) {
            return;
        }
        mEdStockPricePre.setVisibility(View.VISIBLE);
        mAfterPriceView.setVisibility(View.GONE);
    }

    private void resetPreNumView() {
        if (!TextUtils.isEmpty(mEdStockCode.getText())) {
            return;
        }
        mStockNumPreTv.setVisibility(View.VISIBLE);
        mStockNumAfterView.setVisibility(View.GONE);
    }

    public void onClickDeletPriceText() {
        mEdStockPrice.setText("");
    }


    //点击替换掉股票数量初始显示布局
    public void onPreNumClick() {
        mStockFuzzyQueryManager.dismissQueryPopupWindow();
        mStockNumPreTv.setVisibility(View.GONE);
        mStockNumAfterView.setVisibility(View.VISIBLE);
        mEdEntrustAmount.setFocusable(true);
        mEdEntrustAmount.requestFocus();
        mEdStockCode.clearFocus();
        setEdtCursor(mEdEntrustAmount);
    }


    private void setEdtCursor(EditText et) {
        if (et == null) {
            return;
        }
        CharSequence text = et.getText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    //全仓
    public void setStockNumAll() {
        setAmountPos(1);
        mEdEntrustAmount.requestFocus();
        setEdtCursor(mEdEntrustAmount);
    }

    //半仓
    public void setStockNumHalf() {
        setAmountPos(2);
        mEdEntrustAmount.requestFocus();
        setEdtCursor(mEdEntrustAmount);
    }

    //1/3仓
    public void setStockNumThird() {
        setAmountPos(3);
        mEdEntrustAmount.requestFocus();
        setEdtCursor(mEdEntrustAmount);
    }

    //1/4仓
    public void setStockNumQuarter() {
        setAmountPos(4);
        mEdEntrustAmount.requestFocus();
        setEdtCursor(mEdEntrustAmount);
    }

    public void onNameClick() {
        if (!TextUtils.isEmpty(mEdStockCode.getText()) && getEntrustCode().toString().length() >= 6) {
            mEdStockCode.setText(mEdStockCode.getText().toString().substring(0, 6));
            mEdStockCode.performClick();
            mEdStockCode.requestFocus();
            setEdtCursor(mEdStockCode);
        }
    }

    //设置可用资金可用资金
    public void onGetCanUseBalance(String canUseMoney) {
        mCanUseMoneyTv.setText(canUseMoney);
    }

    //点击底部持仓信息的时候带入数据
    public void getHolderStock(MyStoreStockBean bean) {
        mEdStockCode.setText("");
        mEdStockCode.setText(bean.getStock_code().substring(0, 6));
        setEdtCursor(mEdStockCode);
        onStockLengthMax(mEdStockCode.getText().toString(), mEdStockCode);
    }

    public void jumpToRevation() {
        if (mBottomFragmentVp.getCurrentItem() == 2) {
            mBottomRevocationFragment.requestNewData();
        }
        mBottomFragmentVp.setCurrentItem(2);
        mBottomTodayEntrustFragment.requestNewData();
    }


    class NumTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            onListenerEdtNum();
            if (TextUtils.isEmpty(mEdEntrustAmount.getText())) {
                mEdEntrustAmount.setClearIconVisible(false);
                mEdEntrustAmount.invalidate();
            } else {
                mEdEntrustAmount.setClearIconVisible(true);
                mEdEntrustAmount.invalidate();
            }
            mStockNumText = mEdEntrustAmount.getText().toString();
            if (TextUtils.isEmpty(mStockNumText)) {
                resetPreNumView();
            }
        }
    }


    class PriceTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mService.mCount == 2) {
                TradeUtils.onLimitAfterPoint(mEdStockPrice, s, 5, mService.mCount);
            } else {
                TradeUtils.onLimitAfterPoint(mEdStockPrice, s, 5, 3);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mEdStockPrice.getText().toString().length() != 0) {
                mDeletePriceIv.setVisibility(View.VISIBLE);
            } else {
                mDeletePriceIv.setVisibility(View.INVISIBLE);
                resetPrePriceView();
            }
            requestLinkOnPriceChange();
            onListenerEdtNum();
        }

    }

    public int getBuyOrSell() {
        return mBuyOrSell;
    }

    //隐藏股票总价显示窗
    private void hidePriceWindow() {
        mTotalPricePopWindow.dismiss();
    }

    //拓展了键盘的确定按钮功能
    @Override
    public void onKeyCodeDown() {
        if (mEdStockCode.getText().length() == 6) {
            onStockLengthMax(mEdStockCode.getText().toString().trim(), mEdStockCode);
        } else {
            onSearchTextChange();
        }
    }
}