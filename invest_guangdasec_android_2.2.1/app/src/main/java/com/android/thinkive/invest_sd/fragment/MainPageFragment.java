package com.android.thinkive.invest_sd.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.storage.MemoryStorage;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.framework.util.ScreenUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.activities.CommWebActivity;
import com.android.thinkive.invest_sd.activities.EditeMenuItemActivity;
import com.android.thinkive.invest_sd.activities.LoginActivity;
import com.android.thinkive.invest_sd.activities.NewsActivity;
import com.android.thinkive.invest_sd.activities.NewsDetailActivity;
import com.android.thinkive.invest_sd.activities.NoticeInfoDetailActivity;
import com.android.thinkive.invest_sd.activities.WebViewActivity;
import com.android.thinkive.invest_sd.adapter.FastMenuAdapter;
import com.android.thinkive.invest_sd.adapter.FristPageMenuAdapter;
import com.android.thinkive.invest_sd.adapter.HeadImagePagerAdapter;
import com.android.thinkive.invest_sd.adapter.HotMessageMainPageAdapter;
import com.android.thinkive.invest_sd.adapter.HotProductAdapter;
import com.android.thinkive.invest_sd.adapter.NewRecommendAdapter;
import com.android.thinkive.invest_sd.adapter.RecommendMainPageAdapter;
import com.android.thinkive.invest_sd.adapter.RecommendMainPageAdapter2;
import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;
import com.android.thinkive.invest_sd.beans.FristPageNoticeBean;
import com.android.thinkive.invest_sd.beans.HotProductBean;
import com.android.thinkive.invest_sd.beans.NewsBean;
import com.android.thinkive.invest_sd.beans.RecommendBean;
import com.android.thinkive.invest_sd.bll.MainPageService;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.controllers.MainFragmentViewController;
import com.android.thinkive.invest_sd.jsonbean.FristPageCicrleItemJsonbean;
import com.android.thinkive.invest_sd.jsonbean.FristPageNoticeJsonBean;
import com.android.thinkive.invest_sd.util.AppUtil;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.thinkive.invest_sd.util.ImageUtil;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;
import com.android.thinkive.invest_sd.util.WebFragmentManager;
import com.android.thinkive.invest_sd.widget.AutoTextView;
import com.android.thinkive.invest_sd.widget.ListViewInScrollView;
import com.thinkive.android.quotation.activities.StockCodeSearchActivity;
import com.thinkive.android.quotation.views.pulltorefresh.PullToRefreshScrollView;
import com.thinkive.aqf.utils.DateUtils;
import com.thinkive.aqf.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

/**
 * 标准外壳首页模块的主Fragment
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/9
 */
public class MainPageFragment extends AbsBaseFragment implements IModule {
    /**
     * 配置的requestcode
     */
    public final int REQUEST_EDIT_MENU = 110;
    /**z
     * 控制类
     */
    private MainFragmentViewController mController;
    /**
     * 业务类
     */
    private MainPageService mServices;
    /**
     * context
     */
    private AppCompatActivity mActivity;
    /**
     * 嵌套于ScrollView的布局
     */
    private View mChildScrollView;
    /**
     * 自定义ScrollView
     */
    private PullToRefreshScrollView mRefreshScrollview;
    /**
     * ScrollView
     */
    private ScrollView mScrollView;
//    /**
//     * 铃铛图标
//     */
//    private ImageView mImMessageIcon;
//    /**
//     * 搜索图标
//     */
//    private ImageView mImLookOutIcon;
    /**
     * 热点点击加载更多
     */
//    private TextView mTvHotMoreClick;
    /**
     * 今日热门组合点击加载更多
     */
//    private TextView mTvHotGroupClick;
    /**
     * 快捷菜单GridView
     */
    private GridView mGvFastMenu;
    /**
     * 上方的广告滑动页
     */
    private ViewPager mViewPager;
    /**
     * 广告滑动页适配器
     */
    private HeadImagePagerAdapter mPagerAdapter;

    /**
     * 广告滑动页、及其下方位置标识圆点容器
     */
    private RelativeLayout mRlAd;
    /**
     * 显示小圆点的容器
     */
    private ViewGroup mPointGroup;
    /**
     * 存放小圆点的集合类
     */
    private View[] mPointForViewPager;
    /**
     * 广告滑动页的正在加载布局
     */
    private LinearLayout mLlAdLoading;
    /**
     * 快捷菜单适配器
     */
    private FastMenuAdapter mFastMenuAdapter;
//    /**
//     * 当前热门组合数据集合
//     */
//    private ArrayList<HotInvestCounselor> mHotInvestCounselors;
//    /**
//     * 今日热门组合的适配器
//     */
//    private HotGroupMainPageAdapter mHotGroupAdapter;
//    /**
//     * 自定义今日热门组合的listview
//     */
//    private ListViewInScrollView mListHotGroup;
//    /**
//     * 热门资讯的适配器
//     */
    private HotMessageMainPageAdapter mHotMessageAdapter;
//    /**
//     * 自定义热门资讯的listview
//     */
//    private ListViewInScrollView mListHotMessage;
    /**
     * 精品推荐更多字样
     */
    private TextView mTvRecommendClick;
    /**
     * 自定义精品推荐listview
     */
    private ListViewInScrollView mListRecommend;
//    /**
//     * 自定义精品推荐listview
//     */
//    private ListViewInScrollView mListRecommend2;
//    /**
//     * 精品推荐适配器
//     */
//    private RecommendMainPageAdapter2 mRecommendAdapter2;
//    /**
//     * 精品推荐适配器
//     */
//    private RecommendMainPageAdapter mRecommendAdapter;

    private HotProductAdapter mHotProductAdapter;
    /**
     * 循环播放广告的定时任务
     */
    private TimerTask mTimerTask;
    /**
     * 广告ViewPage的高度
     */
    ViewGroup.LayoutParams viewPageLayoutParams;
    /**
     * 循环广告图片
     */
    private TimerHandler handler = new TimerHandler(this);
    /**
     * 加载H5页面时的Url前缀（例如：file:///android_asset/www/）
     */
    private String mH5UrlPre;
    /**
     * 广告循环position
     */
    private static int mAdIndex = 0;
    /**
     * 滑动到最后一页执行该动画
     */
    private static Animation mAnimOnLast;
    /**
     * webFragment管理类，负责用HashMap手机WebFragment对象
     */
    private WebFragmentManager mWebFragmentManager;
    //公告的具体内容
    private List<FristPageNoticeBean> noticeList;
    //轮播公告的空间
    private AutoTextView tv_frist_notfice;
    //包含公告的空间 如无公告需要隐藏
    private View ll_notice_show;
    //当前显示公告的index
    private int notficepostion = 1;
    private boolean isScroll =false;
    /**
     * 轮播公告
     */
    Handler notfichandler = new Handler();
    Runnable notficrunnable = new Runnable() {
        @Override
        public void run() {
            isScroll= true;
            notficepostion++;
            notficepostion = notficepostion % noticeList.size();
            tv_frist_notfice.next();
            if(notficepostion ==0 ){
                tv_frist_notfice.setText(noticeList.get(noticeList.size()-1).getTitle());
            }else {
                tv_frist_notfice.setText(noticeList.get(notficepostion-1).getTitle());
            }
            handler.postDelayed(this, 4000);
        }
    };
    /**
     * 用于循环播放广告图片的handler类
     */
    static class TimerHandler extends Handler {

        public TimerHandler(MainPageFragment mainPageFragment) {
            mMainPageFragmentWeakReference = new WeakReference<MainPageFragment>(mainPageFragment);
        }

        WeakReference<MainPageFragment> mMainPageFragmentWeakReference;

        @Override
        public void handleMessage(Message msg) {
            if ((Integer) msg.obj >= mMainPageFragmentWeakReference.get().mViewPager.getChildCount()) {
                mAdIndex = 0;
                mMainPageFragmentWeakReference.get().mViewPager.setAnimation(mAnimOnLast);
                mMainPageFragmentWeakReference.get().mViewPager.setCurrentItem(0, false);
            } else {
                mMainPageFragmentWeakReference.get().mViewPager.setCurrentItem((Integer) msg.obj);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_page, null);
        mChildScrollView = inflater.inflate(R.layout.child_scrollview_fragment_main, null);
        ModuleManager.getInstance().registerModule(this);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mRefreshScrollview = (PullToRefreshScrollView) view.findViewById(R.id.scroll_app_main);
        mScrollView = mRefreshScrollview.getRefreshableView();
        mScrollView.addView(mChildScrollView);
//        mImMessageIcon = (ImageView) view.findViewById(R.id.im_message_icon);
//        mImLookOutIcon = (ImageView) view.findViewById(R.id.im_look_out_icon);
//        mTvHotMoreClick = (TextView) view.findViewById(R.id.tv_hot_message_more);
//        mTvHotGroupClick = (TextView) view.findViewById(R.id.tv_group_more);
        mTvRecommendClick = (TextView) view.findViewById(R.id.tv_recomend_more);
        mListRecommend = (ListViewInScrollView) view.findViewById(R.id.ls_recomend);
        mListRecommend.setFocusable(false);
        mListRecommend.setDivider(null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_multi_trade);
        mRlAd = (RelativeLayout) view.findViewById(R.id.fl_ad);
        mLlAdLoading = (LinearLayout) view.findViewById(R.id.ll_adLoading);
        mPointGroup = (ViewGroup) view.findViewById(R.id.ll_point_group);
        mGvFastMenu = (GridView) view.findViewById(R.id.gv_fast_menu);
        mGvFastMenu.setFocusable(false);
        tv_frist_notfice = (AutoTextView) view.findViewById(R.id.tv_frist_notfice);
        ll_notice_show = view.findViewById(R.id.ll_notice_show);
        view.findViewById(R.id.main_page_login_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.startModel("user-center","m/index/index.html#!/account/userCenter.html",getActivity());
//                MemoryStorage storage = new MemoryStorage();
//                if(!"1".equals(storage.loadData(Constant.LOGIN_STATE))) {
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intent);
//                }
            }
        });
        view.findViewById(R.id.but_mainpage_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ConfigManager.getInstance(mActivity).getAddressConfigValue("ABOUT_URL");
                Intent intent = new Intent(mActivity, WebViewActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title", "关于我们");
                intent.putExtra("statusColor","#4359aa");
                mActivity.startActivity(intent);
            }
        });
        view.findViewById(R.id.iv_mainpage_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ConfigManager.getInstance(mActivity).getAddressConfigValue("ABOUT_URL");
                Intent intent = new Intent(mActivity, WebViewActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title", "关于我们");
                intent.putExtra("statusColor","#1199EE");
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    void setListeners() {
        tv_frist_notfice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noticeList == null) {
                    return;
                }
                int index = notficepostion - 1;
                if (index < 0) {
                    index = noticeList.size() - 1;
                }
                FristPageNoticeBean bean = noticeList.get(index);
                Intent intent = new Intent(getActivity(), NoticeInfoDetailActivity.class);
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("time", bean.getPublish_date());
                intent.putExtra("id", bean.getArticle_id() + "");
                startActivity(intent);
            }
        });
//        registerListener(MainFragmentViewController.ON_CLICK, mImMessageIcon, mController);j
//        registerListener(MainFragmentViewController.ON_CLICK, mTvHotMoreClick, mController);
//        registerListener(MainFragmentViewController.ON_CLICK, mTvHotGroupClick, mController);
        registerListener(MainFragmentViewController.ON_CLICK, mTvRecommendClick, mController);
        registerListener(MainFragmentViewController.ON_PAGER_CHANGED, mViewPager, mController);
        registerListener(MainFragmentViewController.ON_ITEM_CLICK, mGvFastMenu, mController);
//        registerListener(MainFragmentViewController.ON_ITEM_CLICK, mListHotGroup, mController);
//        registerListener(MainFragmentViewController.ON_ITEM_CLICK, mListHotMessage, mController);
        registerListener(MainFragmentViewController.ON_ITEM_CLICK, mListRecommend, mController);
//        registerListener(MainFragmentViewController.ON_ITEM_CLICK, mListRecommend2, mController);
        registerListener(MainFragmentViewController.ON_SCROLLVIEW_REFLASH, mRefreshScrollview, mController);
    }

    @Override
    void initData() {
        mActivity = (AppCompatActivity) getActivity();
        mController = new MainFragmentViewController(this);
        mServices = new MainPageService(this);
//        mPagerAdapter = new AdFragmentPagerAdapter(getChildFragmentManager());
        mFastMenuAdapter = new FastMenuAdapter(mActivity);
//        mHotGroupAdapter = new HotGroupMainPageAdapter(mActivity);
        mHotMessageAdapter = new HotMessageMainPageAdapter(mActivity);
//        mRecommendAdapter = new RecommendMainPageAdapter(mActivity);
//        mRecommendAdapter2 = new RecommendMainPageAdapter2(mActivity);
        mH5UrlPre = ConfigManager.getInstance(mActivity).getAddressConfigValue("H5_url_pre");
        mWebFragmentManager = WebFragmentManager.getInstance();
    }

    @Override
    void
    initViews() {
        // 设置广告ViewPager的高度，
        viewPageLayoutParams = mViewPager.getLayoutParams();
        ViewGroup.LayoutParams AdRlLayoutParams = mRlAd.getLayoutParams();
        AdRlLayoutParams.width = viewPageLayoutParams.width = (int) ScreenUtil.getScreenWidth(mActivity);
        AdRlLayoutParams.height = viewPageLayoutParams.height = (int) (viewPageLayoutParams.width / 2.67);
        mViewPager.setLayoutParams(viewPageLayoutParams);
        mRlAd.setLayoutParams(AdRlLayoutParams);
        mRlAd.invalidate();
        // 设置禁止上拉加载更多
        mRefreshScrollview.setPullLoadEnabled(false);
        // 设置正在加载布局和广告Viewpager布局的显隐
        mLlAdLoading.setVisibility(View.VISIBLE);
        mRlAd.setVisibility(View.GONE);
        // 请求上方广告ViewPager中的数据
        mServices.requestAds(false);
        // 请求快捷菜单中的内容
        mServices.requestFastMenu(false);
        loadNotice();
        // 请求今日热门资讯
//        mServices.requestHotMessage();
        // 请求
        mServices.requestHotGroupMessage();
        // 请求热销产品
        mServices.requestHotProductData(false);
        // 请求精品推荐
//        mServices.requestRecommendData(false);
        // 请求精品推荐理财产品
//        mServices.requestRecommendData2(false);
        mGvFastMenu.setAdapter(mFastMenuAdapter);
        //--------------设置listview父布局----------------
//        mListHotGroup.setmParentScrollView(mScrollView);
//        mListHotMessage.setmParentScrollView(mScrollView);
        mListRecommend.setmParentScrollView(mScrollView);
//        mListRecommend2.setmParentScrollView(mScrollView);
        // 初始化动画
        mAnimOnLast = new TranslateAnimation(100, 0, 0, 0);
        mAnimOnLast.setDuration(100);
        mAnimOnLast.setFillAfter(true);
    }

    @Override
    protected void setTheme() {
    }

    @Override
    public String onMessageReceive(AppMessage appMessage) {
        return null;
    }

    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mRefreshScrollview.onPullDownRefreshComplete();
        mRefreshScrollview.onPullUpRefreshComplete();
        mRefreshScrollview.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }

    /**
     * 整个页面被下拉时执行
     */
    public void onDownRefresh() {
//         请求上方广告ViewPager中的数据
        mServices.requestAds(true);
        // 请求快捷菜单中的内容
        mServices.requestFastMenu(true);
//        // 请求今日热门资讯
        mServices.requestHotMessage();
//        // 请求今日热门组合
//        mServices.requestHotGroupMessage();
        // 请求精品推荐
        loadNotice();
        mServices.requestHotProductData(true);
//        mServices.requestRecommendData(true);
//        mServices.requestRecommendData2(true);
    }

    /**
     * 上方广告滑动ViewPager的页面选择监听方法
     *
     * @param position
     *         被选择的是第几个页面
     */
    public void onPagerSelected(int position) {
        mAdIndex = position;
        // 更新三个小圆点的选中状态
        for (int i = 0; i < mPointForViewPager.length; i++) {
            if (position == i) {
                mPointForViewPager[i].setBackgroundResource(R.drawable.radio_dark);
            } else {
                mPointForViewPager[i].setBackgroundResource(R.drawable.radio_light);
            }
        }
    }



    /**
     * 快捷菜单{@link #mGvFastMenu}的单击事件
     *
     * @param position
     *         被单击的快捷菜单{@link #mGvFastMenu}的哪一项
     */
    public void onItemClickFastMenu(int position) {
        FristPageCicrleItemBean imgItemBean = mFastMenuAdapter.getItem(position);
        if(imgItemBean.getJump_type() ==4){
            Intent intent = new Intent(getActivity(), EditeMenuItemActivity.class);
            startActivityForResult(intent, REQUEST_EDIT_MENU);
            return;
        }
        if (imgItemBean.getJump_url().contains("trade")) { // is_open 值为1时表示启用
            Toast.makeText(getActivity(), R.string.no_model2, Toast.LENGTH_SHORT).show();
            return;
        }
        if (imgItemBean.getJump_type()==0) { // 跳转原生
            JSONObject params = new JSONObject();
            try {
                params.put("moduleName", imgItemBean.getJump_url());
            } catch (JSONException e) {
                e.printStackTrace();
            }
                AppUtil.startModel(imgItemBean.getJump_url(), "", getActivity());
//            try {
//                Intent intent = new Intent(mActivity, Class.forName(imgItemBean.getJump_url()));
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException cnfe) {
//                cnfe.printStackTrace();
//            }
        } else if (imgItemBean.getJump_type()==1) { // 跳转H5网页
            AppUtil.startModel("comm_h5",imgItemBean.getJump_url(),getActivity());
//            String jumpUrl = imgItemBean.getJump_url();
//            if (jumpUrl.equals("ygt_open")) {
//                openYgtOpenPage();
//            } else {
//                openH5Page(mH5UrlPre + imgItemBean.getJump_url());
//            }
        }else {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("url",imgItemBean.getJump_url());
            intent.putExtra("title",imgItemBean.getImage_name());
            intent.putExtra("statusColor","#4359aa");
            startActivity(intent);
        }
    }

    /**
     * 点击 “ 铃铛图标 ” 所执行的操作
     */
    public void onClickMessageIcon() {
        Toast.makeText(getActivity(), R.string.no_model2, Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击“搜索图标”所执行的操作
     */
    public void onClickLookOutIcon() {
        startActivity(new Intent(mActivity, StockCodeSearchActivity.class));
    }

    /**
     * 点击 “精品推荐更多” 所执行的操作
     */
    public void onClickRecommendMore() {
//        String url =  "m/mall/index.html#!/account/mainPage.html";
        String url =  "m/mall/index.html";
        AppUtil.startModel("comm_h5", url, getActivity());

    }

    /**
     * 点击精品推荐item
     */
    public void onClickRecommendItem(int position) {
        HotProductBean bean = mHotProductAdapter.getItem(position);
        String  url =  "m/mall/index.html#!/mall/itemsFinanInfo.html?product_id="+bean.getProductId()+"&gobackmain=true";
        AppUtil.startModel("comm_h5", url, getActivity());

    }
    /**
     * 点击精品推荐理财产品item
     */
    public void onClickRecommendItem2(int position) {
//        HotProductBean bean = mHotProductAdapter.getItem(position);
//        String  url =  "m/mall/index.html#!/mall/itemsFundInfo.html?product_id="+bean.getProductId()+"&gobackmain=true";
//        AppUtil.startModel("comm_h5", url, getActivity());

    }

    /**
     * 点击 “今日热门组合”更多 所执行的操作
     */
    public void onClickHotGroupMore() {
        String url = mH5UrlPre + "m/ytg/index.html#!/portfolio/index.html?isFromApp=true";
//        Intent intent = new Intent(mActivity, CommWebActivity.class);
//        intent.putExtra("url", url);
//        startActivity(intent);

        openH5Page(url);
    }

    /**
     * 热门投顾组合点击事件，多个项的点击事件都在这里实现，用position区分各个项
     *
     * @param position
     *         被点击的投顾组合的序号
     */
    public void onItemClickHotInvestCounselor(int position) {
//        HotInvestCounselor hotInvestCounselor = mHotInvestCounselors.get(position);
//        String url = mH5UrlPre + "m/ytg/index.html#!/portfolio/portfolioDetail.html?portfolio_id=" +
//                hotInvestCounselor.getPortfolio_id() + "&isFromApp=true";
//        String preUrl = ThinkiveUtil.getPreUrl(url);
//        WebCacheFragment webCacheFragment = mWebFragmentManager.getWebCacheFragment(preUrl);
//        webCacheFragment.preloadUrl(mActivity, url);
//        final Intent intent = new Intent(mActivity, CommWebActivity.class);
//        intent.putExtra(CommWebActivity.BUNDLE_KEY_FRAGMENT_KEY, preUrl);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(intent);
//            }
//        }, 700);
//        openH5Page(url);
    }

    /**
     * 点击“热门资讯更多”所执行的操作
     */
    public void onClickHotMessageMore() {
//        AppUtil.startModel("comm_h5","m/index/index.html#!/xjb/account/index.html",getActivity());
        Intent intent = new Intent(mActivity, NewsActivity.class);
        startActivity(intent);
    }

    /**
     * 点击热门资讯item
     */
    public void onClickHotMessage(int position) {
        NewsBean hotMessageBean = mHotMessageAdapter.getItem(position);
        Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
        intent.putExtra("id", hotMessageBean.getId());
        intent.putExtra("title", hotMessageBean.getTitle());
        intent.putExtra("publish_date", hotMessageBean.getPublish_date());
        startActivity(intent);
    }

    /**
     * 获取广告Pager图片数据，但是这个数据是除了广告图片本身的其他数据，
     * 当获取到广告位图数据时，调用{@link #onGetAdPagerImg(int, Bitmap)}方法
     *
     * @param pagerBeans
     *         从服务器那边得到的Pager广告图片实体
     */
    public void
    onGetAdPagersData(List<FristPageCicrleItemBean> pagerBeans) {
        mLlAdLoading.setVisibility(View.GONE);
        mRlAd.setVisibility(View.VISIBLE);

        List<FristPageCicrleItemBean> topList = new ArrayList<FristPageCicrleItemBean>();
        FristPageCicrleItemBean launcher_bean =null ;
        for(FristPageCicrleItemBean bean :pagerBeans){
            if ("startAd".equals(bean.getGroup_no())){
                launcher_bean = bean;
            }
            if("rockAdmenu".equals(bean.getGroup_no())){
                topList.add(bean);
            }
        }
        if(launcher_bean !=null){
            ImageUtil.saveLauncherImage(launcher_bean.getImage_url(), getActivity());
        }

        HeadImagePagerAdapter adapter = new HeadImagePagerAdapter(topList,getActivity());
        adapter.setListener(new HeadImagePagerAdapter.OnItemClickListener() {
            @Override
            public void onclick( FristPageCicrleItemBean imgItemBean) {
                if(TextUtils.isEmpty(imgItemBean.getJump_url())){
                    return;
                }
                if (imgItemBean.getJump_url().contains("trade")) { // is_open 值为1时表示启用
                    Toast.makeText(getActivity(), R.string.no_model2, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (imgItemBean.getJump_type()==0) { // 跳转原生
                    JSONObject params = new JSONObject();
                    try {
                        params.put("moduleName", imgItemBean.getJump_url());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    AppUtil.startModel(imgItemBean.getJump_url(), "", getActivity());
//            try {
//                Intent intent = new Intent(mActivity, Class.forName(imgItemBean.getJump_url()));
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException cnfe) {
//                cnfe.printStackTrace();
//            }
                } else if (imgItemBean.getJump_type()==1) { // 跳转H5网页
                    AppUtil.startModel("comm_h5",imgItemBean.getJump_url(),getActivity());
//            String jumpUrl = imgItemBean.getJump_url();
//            if (jumpUrl.equals("ygt_open")) {
//                openYgtOpenPage();
//            } else {
//                openH5Page(mH5UrlPre + imgItemBean.getJump_url());
//            }
                }else {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url",imgItemBean.getJump_url());
                    intent.putExtra("title", imgItemBean.getImage_name());
                    intent.putExtra("statusColor", "#1199EE");
                    startActivity(intent);
                }
            }
        });
        mViewPager.setAdapter(adapter);
        // 循环广告图片
        circulationPicture();
        // 设置ViewPager小圆点
        mPointForViewPager = new View[topList.size()];
        mPointGroup.removeAllViews();
        for (int i = 0; i < topList.size(); i++) {
            // 设置小圆点的相关参数,默认选中第一个
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(20, 0, 20, 0);
            TextView textView = new TextView(mActivity);
            textView.setLayoutParams(params);
            mPointForViewPager[i] = textView;
            if (i == 0) {
                mPointForViewPager[i].setBackgroundResource(R.drawable.radio_dark);
            } else {
                mPointForViewPager[i].setBackgroundResource(R.drawable.radio_light);
            }
            mPointGroup.addView(mPointForViewPager[i]);
        }
        refreshComplete();
    }

    /**
     * 当获取到一个首页广告图片时执行
     * 逐个获取首页广告图片，获取到一张时，就执行这个方法设置一张。
     *
     * @param position
     *         获取的图片应该放在ViewPager的第几个Fragment上面
     * @param bitmap
     *         从服务器获取到的位图
     */
    public void onGetAdPagerImg(int position, @Nullable Bitmap bitmap) {
//        if (mFragments != null && mFragments.size() - 1 >= position && bitmap != null) {
//            mFragments.get(position).setAdImage(bitmap);
//        }
    }

    /**
     * 获取到快捷菜单数据时
     *
     * @param beans
     *         从服务器那边得到的菜单项图片实体
     */
    public void onGetFastMenuData(List<FristPageCicrleItemBean> beans) {
        if (beans != null) {
            mFastMenuAdapter.setListData(beans);
            mFastMenuAdapter.notifyDataSetChanged();
//            ThinkiveUtil.setGridViewHeight(4, mGvFastMenu, 12, (int) ScreenUtil.dpToPx(mActivity, 18));
//            mGvFastMenu.setPadding(0, 12, 0, 0);
        }
    }
//
//    /**
//     * 得到精品推荐的数据列表
//     *
//     * @param dataList
//     *         精品推荐的数据列表
//     */
//    public void onGetRecommendDataList(final List<RecommendBean> dataList) {
//        if (dataList != null && dataList.size() != 0) {
//            mRecommendAdapter.setListData(dataList);
//            mListRecommend.setAdapter(mRecommendAdapter);
//            ListViewInScrollView.setListViewHeight(mListRecommend);
//        }
//    }

    /**
     * 得到热销产品数据列表
     *
     * @param dataList
     *         精品推荐的数据列表
     */
    public void onGetHotDataDataList(final List<HotProductBean> dataList) {
        TreeMap<Integer,ArrayList<HotProductBean>> map = new TreeMap<>();

        if (dataList != null && dataList.size() != 0) {
            for(HotProductBean bean : dataList){
                if(map.get(bean.getSortId())==null){
                    map.put(bean.getSortId(),new ArrayList<HotProductBean>());
                }
                bean.setUi_type(1);
                map.get(bean.getSortId()).add(bean);
            }
            ArrayList<HotProductBean> showlist = new ArrayList<>();

            Iterator it = map.keySet().iterator();
            while (it.hasNext())
            {
                ArrayList<HotProductBean> data = map.get(it.next());
                HotProductBean bean = new HotProductBean();
                bean.setAssortment(data.get(0).getAssortment());
                bean.setSlogan(data.get(0).getSlogan());
                bean.setUi_type(2);
                showlist.add(bean);
                showlist.addAll(data);
            }
            mHotProductAdapter = new HotProductAdapter(getActivity(),showlist);
            mHotProductAdapter.setListener(new HotProductAdapter.OnRecommendItenClick() {
                @Override
                public void onclick(HotProductBean bean) {
                    String  url;
                    switch (bean.getProductLinkType()){
                        case 0 :
                              url =  "m/mall/index.html#!/mall/itemsFinanInfo.html?product_id="+bean.getProductId()+"&gobackmain=true&sell_type="+bean.getSellType();
                            AppUtil.startModel("comm_h5", url, getActivity());
                            break;
                        case 1 :
                            url =  "m/mall/index.html#!/mall/itemsFundInfo.html?product_id="+bean.getProductId()+"&gobackmain=true&sell_type="+bean.getSellType();
                            AppUtil.startModel("comm_h5", url, getActivity());
                            break;
                        case 2 :
                            url =  "m/mall/index.html#!/mall/itemsInfoDetail.html?product_id="+bean.getProductId()+"&gobackmain=true&sell_type="+bean.getSellType();
                            AppUtil.startModel("comm_h5", url, getActivity());
                            break;
                        case 3 :
                            url =  "m/mall/index.html#!/mall/itemsOTCInfo.html?product_id="+bean.getProductId()+"&gobackmain=true&sell_type="+bean.getSellType();
                            AppUtil.startModel("comm_h5", url, getActivity());
                            break;
                        default: ToastUtils.Toast(getActivity(),"预售产品,敬请期待");
                    }
                }
            });
            mListRecommend.setAdapter(mHotProductAdapter);
            ListViewInScrollView.setListViewHeight(mListRecommend);
        }
    }

//    /**
//     * 得到精品推荐的数据列表
//     *
//     * @param dataList
//     *         精品推荐的数据列表
//     */
//    public void onGetRecommendDataList2(final List<RecommendBean> dataList) {
//        if (dataList != null && dataList.size() != 0) {
//            mRecommendAdapter2.setListData(dataList);
//            mListRecommend2.setAdapter(mRecommendAdapter2);
//            ListViewInScrollView.setListViewHeight(mListRecommend2);
//        }
//    }

    /**
     * 得到当前投顾组合的热门信息
     *
     * @param dataList
     *         从服务器获取的数据
     */
//    public void onGetHotGroupMessage(final ArrayList<HotInvestCounselor> dataList) {
//        if (dataList != null && dataList.size() != 0) {
//            mHotInvestCounselors = dataList;
//            mHotGroupAdapter.setListData(dataList);
//            mListHotGroup.setAdapter(mHotGroupAdapter);
//            //设置listview的高度
//            ListViewInScrollView.setListViewHeight(mListHotGroup);
//        } else {
//            mHotInvestCounselors = new ArrayList<HotInvestCounselor>();
//        }
//    }

    /**
     * 当获取热门投顾组合列表图片时
     *
     * @param position
     *         获取的是第几项数据的图片
     * @param bitmap
     *         获取的位图形式的图片
     */
//    public void onGetHotGroupImg(int position, Bitmap bitmap) {
//        if (position >= mHotGroupAdapter.getCount()) {
//            return;
//        }
//        mHotGroupAdapter.getItem(position).setImage_bmp(bitmap);
//        mHotGroupAdapter.notifyDataSetChanged();
//    }

    /**
     * 得到当前热点资讯
     *
     * @param dataList
     *         从服务器获取的数据
     */
    public void onGetHotMessage(List<NewsBean> dataList) {
        if (dataList != null && dataList.size() != 0) {
            mHotMessageAdapter.setListData(dataList);
//            mListHotMessage.setAdapter(mHotMessageAdapter);
//            ListViewInScrollView.setListViewHeight(mListHotMessage);
        }
    }

    /**
     * 循环播放广告图片
     */
    public void circulationPicture() {
        Timer t = new Timer();
        // 停止以前启动的定时器任务
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
        // 重置定时器任务对象
        mTimerTask = new TimerTask() {
            public void run() {
                Message msg = Message.obtain();
                mAdIndex++;
                msg.obj = mAdIndex;
                handler.sendMessage(msg);
            }
        };
        // 开始定时执行定时器任务
        t.schedule(mTimerTask, 3000, 3000);
    }

    /**
     * 打开一个H5页面。延时打开，以防出现同一模块的上一个页面残留
     * 但是，打开“开户”模块的H5，需要用到下面的方法{@link #openYgtOpenPage()}
     *
     * @param url
     *         被打开的H5页面的Url
     */
    private void openH5Page(String url) {
        String preUrl = ThinkiveUtil.getPreUrl(url);
        WebCacheFragment webCacheFragment = mWebFragmentManager.getWebCacheFragment(preUrl);
        if (webCacheFragment == null) {
            if(preUrl.contains("m/open/index")){
                webCacheFragment = new OpenAccountFragment();
            }else {
                webCacheFragment = new WebCacheFragment();
            }
        }
        webCacheFragment.preloadUrl(mActivity, url);
        final Intent intent = new Intent(mActivity, CommWebActivity.class);
        intent.putExtra(CommWebActivity.BUNDLE_KEY_FRAGMENT_KEY, preUrl);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 150);
    }

    /**
     * 打开云柜台的开户模块，这个模块比较特殊，需要启动独立的类
     */
    private void openYgtOpenPage() {
//        String url = mH5UrlPre + "m/open/index.html";
//        String preUrl = ThinkiveUtil.getPreUrl(url);
//        YgtOpenFragment ygtOpenFragment = new YgtOpenFragment();
//        ygtOpenFragment.preloadUrl(mActivity, url);
//        mWebFragmentManager.putCacheFragment(preUrl, ygtOpenFragment);
//        final Intent intent = new Intent(mActivity, YgtOpenActivity.class);
//        intent.putExtra(CommWebActivity.BUNDLE_KEY_FRAGMENT_KEY, preUrl);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(intent);
//            }
//        }, 150);
    }
    /**
     * 加载公告通知
     */
    private void loadNotice(){
        RequestBean request = new RequestBean();
        HashMap<String,String> params= new HashMap<>();
        params.put("funcNo", "9405072");
        params.put("catalog_id", "2048");
        params.put("numPerPage", "3");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject==null){
                    ToastUtils.Toast(getActivity(), "公告加载失败：未获取到数据");
                    return;
                }
                FristPageNoticeJsonBean jsonben =  JsonParseUtil.parseJsonToObject(jsonObject, FristPageNoticeJsonBean.class);
                if(jsonben==null){
                    ToastUtils.Toast(getActivity(), "公告加载失败：未获取到数据");
                    return;
                }
                if(jsonben.getError_no()==0){
                    if(jsonben.getResults()==null||jsonben.getResults().size()<=0){
                        tv_frist_notfice.setText("暂无公告");
                    }else{
                            noticeList = jsonben.getResults();
                        if(!isScroll) {
                            notfichandler.postDelayed(notficrunnable, 200);
                        }
                    }
                }else{
                    ToastUtils.Toast(getActivity(), "公告加载失败："+jsonben.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                e.printStackTrace();
                ToastUtils.Toast(getActivity(), "公告加载失败请检查网络");
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_OK)
            return;

        if (requestCode == REQUEST_EDIT_MENU) {
            if (data == null)
                return;
            boolean isichange = data.getBooleanExtra("isichange", false);
            if (isichange) {
                JSONObject result = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_MENU, getActivity());
                if (result!=null) {
                    FristPageCicrleItemJsonbean jsonbean = JsonParseUtil.parseJsonToObject(result, FristPageCicrleItemJsonbean.class);
                    if (jsonbean != null && jsonbean.getError_no() == 0 && jsonbean.getResults() != null && jsonbean.getResults().size() > 0) {

                        //本地无缓存需使用默认数据刷新界面并添加缓存
                        List<FristPageCicrleItemBean> list = new ArrayList<FristPageCicrleItemBean>();
                        for (FristPageCicrleItemBean bean : jsonbean.getResults()) {
                            if (bean.getIs_default() == 1 ) {
                                list.add(bean);
                            }
                        }
                        //多加一个显示配置
                        list.add(new FristPageCicrleItemBean(4));
                        onGetFastMenuData(list);
                    }
                }
            }
        }
    }
}
