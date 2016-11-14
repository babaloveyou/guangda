package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.DialogFrame;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.keyboard.KeyboardManager;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.network.http.HttpService;
import com.android.thinkive.framework.notice.NoticeManager;
import com.android.thinkive.framework.storage.MemoryStorage;
import com.android.thinkive.framework.theme.ThemeManager;
import com.android.thinkive.framework.util.Constant;
import com.android.thinkive.framework.util.FileUtil;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.framework.util.PreferencesUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.base.BaseActivity;
import com.android.thinkive.invest_sd.fragment.MainPageFragment;
import com.android.thinkive.invest_sd.fragment.MallFragment;
import com.android.thinkive.invest_sd.fragment.NewsWebFragment;
import com.android.thinkive.invest_sd.fragment.UserCenterFragment;
import com.android.thinkive.invest_sd.fragment.WebCacheFragment;
import com.android.thinkive.invest_sd.message.handler.Message50102;
import com.android.thinkive.invest_sd.message.handler.Message50108;
import com.android.thinkive.invest_sd.message.handler.Message50115;
import com.android.thinkive.invest_sd.message.handler.Message50222;
import com.android.thinkive.invest_sd.message.handler.Message50240;
import com.android.thinkive.invest_sd.message.handler.Message50275;
import com.android.thinkive.invest_sd.message.handler.Message60300;
import com.android.thinkive.invest_sd.util.ActivateManger;
import com.android.thinkive.invest_sd.util.LoginManger;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;
import com.android.thinkive.invest_sd.util.UpgradeHelperManager;
import com.android.thinkive.invest_sd.widget.BaseViewPager;
import com.android.thinkive.invest_sd.widget.IndicatorScrollView;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.thinkive.android.quotation.fragments.GNewOptionalFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.TradeParentFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.aqf.utils.ConfigUtil;
import com.umeng.analytics.MobclickAgent;

import org.apache.http.entity.mime.content.FileBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhuduanchang on 2015/6/19.
 */
public class MainActivity extends BaseActivity implements IModule {
    public final static int PICK_CONTACT = 0x1000;
    public final static int QR_CODESCAN = 0x1001;
    public final static int UPLOAD_IMAGE = 0x1002;

    public final static int LEFT_MENU_OPEN = 1001;
    public final static int LEFT_MENU_CLOSE = 1002;
    public final static int RIGHT_MENU_OPEN = 1003;
    public final static int RIGHT_MENU_CLOSE = 1004;
    public final static int INDICATOR_SHOW = 1005;
    public final static int INDICATOR_DISMISS = 1006;
    public final static int SET_CURRENT_ITEM = 1007;
    public final static int UPLOAD_IMAGE_SUCCESS = 1008;
    public final static int UPLOAD_IMAGE_FAILED = 1009;
    //退出应用时，两次按返回键的时间间隔
    private final static int INTERVAL_TIME = 2000;

    // 主题保存在assets/theme 目录下，  每一套主题都以zip包来保存
    private final static String THEME_ROOT = "theme";
//    private DrawerLayout mDrawerLayout;
    private IndicatorScrollView mIndicatorView;
//    //左侧滑栏
//    private NavigationView mLeftNavigationView;
//    //右侧滑栏
//    private NavigationView mRightNavigationView;

    private BaseViewPager mViewpager;
    private ImageView mImageView;
    private long mFirstPressedBackKeyTime = 0;
    private int mCurrentItem = 0;
    private FragmentAdapter mAdapter;
    //消息来源模块
    private String mSourceModule;

    private DialogFrame dialog;
    private LoadingDialog loadingDialog;
    //登录完成后需要跳转的模块
   public static String moduleName ;
    //登录完成后的子模块pageid
    public static  String toPage ;
    //打开登录的request
    public static final int REUQEST_LOGIN= 1011;

    //打开登录的request
    public static final int REUQEST_ACTIVATE= 1017;

    long lastlogintime;

    int lastloginmodule =-1;

    //导航栏资源，数量必须和viewpager的fragment数量一致。
    private int[] mTextIds = new int[]{R.string.item1, R.string.item2, R.string.item6,R.string.item5 ,R.string.item4};
    private int[] mNormalIds = new int[]{R.drawable.ic_main_a, R.drawable.ic_price_a,R.drawable.ic_trade_a, R.drawable.ic_news_a,R.drawable.ic_me_a,};
    private int[] mSerlectIds = new int[]{R.drawable.ic_main_b, R.drawable.ic_price_b,R.drawable.ic_trade_b , R.drawable.ic_news_b,R.drawable.ic_me_b,};

    private BroadcastReceiver mNoticeDismissReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //开始版本检测
            UpgradeHelperManager.getInstance().checkUpgrade(MainActivity.this, false);
        }
    };
    public static boolean islogin =false;
    private void
    setupViewPager() {
//        MallFragment mallFragment = new MallFragment();
//        String mallPreurl = ThinkiveUtil.getPreUrl(MallFragment.assert_url);
//        WebFragmentManager.getInstance().putCacheFragment(mallPreurl, mallFragment);
//        UserCenterFragment userCenterFragment = new UserCenterFragment();
//        String  userCenterurl = ThinkiveUtil.getPreUrl(UserCenterFragment.assert_url);
//        Log.d("@@@存储的url +++" + userCenterurl);
//        WebFragmentManager.getInstance().putCacheFragment(userCenterurl, userCenterFragment);

        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new MainPageFragment());
//        mAdapter.addFragment(new com.thinkive.android.quotation.fragments.ListFragment());
        mAdapter.addFragment(new GNewOptionalFragment());
//        mAdapter.addFragment(ThinkiveUtil.getWebviewFragment(TradeFragment.url));
//        mAdapter.addFragment( ThinkiveUtil.getWebviewFragment(MallFragment.url));
        mAdapter.addFragment(new TradeParentFragment());
        mAdapter.addFragment(new NewsWebFragment());
//        mAdapter.addFragment(ThinkiveUtil.getWebviewFragment(NewsWebFragment.assert_url));
        mAdapter.addFragment(ThinkiveUtil.getWebviewFragment(UserCenterFragment.url));
//        mAdapter.addFragment(new OptionalFragment());
//        mAdapter.addFragment(new FinancialMallFragment());
//        mAdapter.addFragment(new XdtFragment());
//        mAdapter.addFragment(new OpenAccountFragment());
//        mAdapter.addFragment(new GgtFragment());
        mViewpager.setAdapter(mAdapter);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LEFT_MENU_OPEN:
//                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    break;
                case LEFT_MENU_CLOSE:
//                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    break;
                case RIGHT_MENU_OPEN:
//                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                    break;
                case RIGHT_MENU_CLOSE:
//                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    break;
                case INDICATOR_SHOW:
                    mIndicatorView.setVisibility(View.VISIBLE);
                    break;
                case INDICATOR_DISMISS:
                    mIndicatorView.setVisibility(View.GONE);
                    break;
                case SET_CURRENT_ITEM:
                    if(msg.arg1 == REUQEST_LOGIN){
                        if(mCurrentItem ==msg.arg2) {
                            Log.d("@@@@@登录"+String.valueOf(System.currentTimeMillis()-lastlogintime));
                            Log.d("@@@@@登录"+mCurrentItem+"qqqqq"+lastloginmodule);
                            if(System.currentTimeMillis()-lastlogintime>2000){
                                lastloginmodule=mCurrentItem;
                                lastlogintime=System.currentTimeMillis();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent, REUQEST_LOGIN);
                            }
                        }
                        return;
                    }
                    mCurrentItem = msg.arg1;
                    mViewpager.setCurrentItem(msg.arg1, false);
                    //todo:XXX这里暂时先这样，后续优化
                    JSONObject content = (JSONObject) msg.obj;
                    if (content != null) {
                        String messageId = content.optString(Constant.MESSAGE_ID);
                        if ("60250".equals(messageId)) {
                            if (mAdapter.getItem(msg.arg1) instanceof BaseWebFragment) {
                                BaseWebFragment webFragment = (BaseWebFragment) mAdapter.getItem(msg.arg1);
                                webFragment.sendMessageToH5(content);
                            }
                        }else{
                            //将跳转的子页面数据传给h5
                            JSONObject param = content.optJSONObject("params");
                            if (param!=null) {
                                String url  = param.optString("toPage");
                                if(TextUtils.isEmpty(url)){
                                    return;
                                }
                                if(!url.startsWith("m"))
                                    url = "m"+url;
                                url = ConfigManager.getInstance(MainActivity.this).getAddressConfigValue("H5_url_pre")+url;
                                if (mAdapter.getItem(msg.arg1) instanceof BaseWebFragment) {
                                    BaseWebFragment webFragment = (BaseWebFragment) mAdapter.getItem(msg.arg1);
                                    webFragment.loadUrl(url);
                                }
                            }
                        }

                    }
                    break;
                case UPLOAD_IMAGE_SUCCESS:
                    String file = (String) msg.getData().get("path");
                    sendMessage50274("0", file);
                    try {
                        if (null != dialog) {
                            dialog.unWaitDialog();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case UPLOAD_IMAGE_FAILED:
                    String file1 = (String) msg.getData().get("path");
                    sendMessage50274("-1", file1);
                    try {
                        if (null != dialog) {
                            dialog.unWaitDialog();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                Log.d("nav_home");
                                Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_friends:

                                break;
                            case R.id.nav_messages:

                                break;
                            case R.id.nav_discussion:

                                break;
                        }
                        menuItem.setChecked(true);
//                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    /**
     * 将跳转完传给H5处理
     *
     * @param baseWebFragment
     */
    private void sendMessage50113(BaseWebFragment baseWebFragment, JSONObject param) {
        AppMessage appMessage = new AppMessage(50113, param);
        baseWebFragment.sendMessageToH5(appMessage);
    }

    public int  getCuItem(){
        return  mCurrentItem;
    }
    /**
     * 点首页图标跳转
     *
     * @param baseWebFragment
     */
    private void sendMessage50114(BaseWebFragment baseWebFragment, JSONObject param) {
        AppMessage appMessage = new AppMessage(50114, param);
        baseWebFragment.sendMessageToH5(appMessage);
    }
    //获取联系人电话
    private String getContactPhone(Cursor cursor) {
        String phoneNumber = "";
        String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
        //获取联系人所有电话信息
        while (phone.moveToNext()) {
            phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        return phoneNumber;
    }

    private void loadAssetsTheme() {
        String[] paths = null;
        try {
            paths = getAssets().list(THEME_ROOT);
            if (paths != null && paths.length > 0) {
                for (int i = 0; i < paths.length; i++) {
                    String assetName = THEME_ROOT + File.separator + paths[i];
                    String dirPath = ThemeManager.THEME_PATH;
                    Log.d("assetName " + assetName + " dirPath " + dirPath);
                    FileUtil.unZipAssets(MainActivity.this, assetName, dirPath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("loadAssetsTheme " + e.getMessage());
        }
    }

    /**
     * 50274功能号  回调H5上传完成
     *
     * @param error_no
     * @param file
     */
    private void sendMessage50274(String error_no, String file) {
        Bitmap bitmap = BitmapFactory.decodeFile(file);
        String imgBase64 = bitmapToBase64(bitmap);
        JSONObject param = new JSONObject();
        try {
            param.put("error_no", error_no);
            param.put("error_info", "");
            param.put("paramExt", "");
            param.put("base64Image", imgBase64);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppMessage appMessage = new AppMessage(50274, param);
        appMessage.setSourceModule(mSourceModule);
        MessageManager.getInstance(MainActivity.this).sendMessage(appMessage);
    }

    /**
     * 通讯录回调H5
     *
     * @param contactName
     * @param phoneNumber
     */
    private void sendMessage50223(String contactName, String phoneNumber) {
        JSONObject param = new JSONObject();
        try {
            param.put("name", contactName);
            param.put("phone", phoneNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppMessage appMessage = new AppMessage(50223, param);
        appMessage.setSourceModule(mSourceModule);
        MessageManager.getInstance(MainActivity.this).sendMessage(appMessage);
    }

    /**
     * 将返回键传给H5处理
     *
     * @param baseWebFragment
     */
    private void sendMessage50107(BaseWebFragment baseWebFragment) {
        JSONObject param = new JSONObject();
        AppMessage appMessage = new AppMessage(50107, param);
        baseWebFragment.sendMessageToH5(appMessage);
    }

    /**
     * 扫描二维码回调H5
     *
     * @param content
     */
    private void sendMessage50272(String content) {
        JSONObject param = new JSONObject();
        try {
            param.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppMessage appMessage = new AppMessage(50272, param);
        appMessage.setSourceModule(mSourceModule);
        ModuleManager.getInstance().sendMessageByWebModule(mSourceModule, appMessage);
    }

    private void sendMessage60301(JSONObject content) {
        AppMessage appMessage = new AppMessage(60301, content);
        appMessage.setSourceModule(mSourceModule);
        MessageManager.getInstance(MainActivity.this).sendMessage(appMessage);
    }

    /**
     * 通知Fragment的生命周期
     *
     * @param index 需要唤醒的下标,如果全体休眠请传入小于0的数
     */
    private void notifyFragmentLifeCycle(int index) {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            if (index != i) {
                mAdapter.getItem(i).onStop();
            } else {
                mAdapter.getItem(i).onResume();
            }
        }

    }

    /**
     * 获取银联认证结果
     */
    private void getCpAuthResult() {
//        try {
//            String resuleCode = Utils.getPayResult();
//            JSONObject result = new JSONObject();
//            if (resuleCode != null && !resuleCode.equals("")) {
//                // 根据返回码做出相应处理
//                if (Utils.getPayResult().indexOf("0000") > -1) {
//                    // 认证成功，返回卡信息及用户信息
//                    result.put("cerName", Utils.getCerName());
//                    result.put("respCode","0000");
//                    result.put("respMsg","认证成功");
//                    result.put("cerType",Utils.getCerType());
//                    result.put("cerNo",Utils.getCerNo());
//                    result.put("cardNo",Utils.getCardNo());
//                    result.put("cardMobile",Utils.getMobile());
//                    result.put("cpSign", Utils.getRespSign());
//                    // 为CP签名值，仅当认证成功时，才有签名，具体规则见下方2.4节
//                }else{
//                    // 认证失败
//                    result.put("respCode",resuleCode);
//                }
//                sendMessage60301(result);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        CPGlobaInfo.init(); // 清空返回结果
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleManager.getInstance().registerModule(this);
        ModuleManager.getInstance().registerModule(new NewsActivity());
        setContentView(R.layout.activity_main);
        ConfigUtil.init(this);
        initViews();
        setListeners();
        initData();
        if (TextUtils.isEmpty(ConfigManager.getInstance(this).getAddressConfigValue("NOTICE_URL"))) {
            UpgradeHelperManager.getInstance().checkUpgrade(this, false);
        } else {
            NoticeManager.getInstance(this).checkNoticeInfo("NOTICE_URL");
        }
        //用于退出应用，同时还可解决在小米等禁用了系统对话框的手机上不能弹框架的对话框问题
        CoreApplication.getInstance().pushActivity(this.getClass().getName(), this);

    }

    @Override
    protected void initViews() {
        loadingDialog  = new LoadingDialog(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.hide();

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mLeftNavigationView = (NavigationView) findViewById(R.id.nav_view);
//        if (mLeftNavigationView != null) {
//            setupDrawerContent(mLeftNavigationView);
//        }
//        mRightNavigationView = (NavigationView) findViewById(R.id.right_view);

        TextView userName = (TextView) findViewById(R.id.username);
        mImageView = (ImageView) findViewById(R.id.login_head);
        mViewpager = (BaseViewPager) findViewById(R.id.viewpager);
        mViewpager.setCanScroll(false);
        mViewpager.setOffscreenPageLimit(8);
        setupViewPager();

        mIndicatorView = (IndicatorScrollView) findViewById(R.id.indicator);
        mIndicatorView.initTitleAndIcons(mViewpager, mTextIds, mNormalIds, mSerlectIds);
//        mIndicatorView.setVisibility(View.GONE);
    }


    @Override
    protected void initData() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(NoticeManager.NOTICE_DISMISS_ACTION);
        registerReceiver(mNoticeDismissReceiver, filter);
        CoreApplication.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                loadAssetsTheme();
            }
        });

        //检测激活
        String phone =  PreferencesUtil.getString(this, com.android.thinkive.invest_sd.constants.Constant.MOBILE_NUMBER);
        if(!TextUtils.isEmpty(phone)) {
            ActivateManger.getInstance(this).checkActivate(phone, new ActivateManger.ActivateCallback() {
                @Override
                public void onSucceed(String flag) {
                    if(flag.equals("0")){
                        PreferencesUtil.putString(MainActivity.this, com.android.thinkive.invest_sd.constants.Constant.MOBILE_NUMBER,"");
                    }
                }

                @Override
                public void onFailed(String errorinfo) {

                }
            });
        }
    }

    @Override
    protected void setTheme() {
//        mImageView.setBackground(ThemeManager.getNormalDrawable(MainActivity.this, R.drawable.ic_login_head, ThemeManager.SUFFIX_PNG));
        mIndicatorView.setTheme();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        getCpAuthResult();
        TradeUtils.hideSystemKeyBoard(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {
        mIndicatorView.setOnItemClickListener(new IndicatorScrollView.OnItemClickListener() {
            @Override
            public void onClick(int index) {
                Log.d("mIndicatorView.onClick" + index);
                mCurrentItem = index;
                mViewpager.setCurrentItem(index, false);
                try {
                    if (index == 4) {
                        String urlPre = ConfigManager.getInstance(MainActivity.this).getAddressConfigValue("H5_url_pre");
                        WebCacheFragment fragment = (WebCacheFragment) mAdapter.getItem(index);
                        fragment.loadUrl(urlPre + "m/index/index.html#!/account/userCenter.html");
                    } else if (index == 2) {

//                        String urlPre = ConfigManager.getInstance(MainActivity.this).getAddressConfigValue("H5_url_pre");
//                        WebCacheFragment fragment = (WebCacheFragment) mAdapter.getItem(index);
//                        fragment.loadUrl(MallFragment.assert_url);
                    }
                } catch (Exception e) {

                }
            }
            //mIndicatorView.onPageScrolled(index);
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicatorView.onPageScrolled(position);
            }

            @Override
            public void onPageSelected(int position) {
                notifyFragmentLifeCycle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public String onMessageReceive(AppMessage appMessage) {
        int msgId = appMessage.getMsgId();
        IMessageHandler messageHandler = null;
        Log.d("home module message = " + appMessage.getContent());
        mSourceModule = appMessage.getContent().optString(Constant.MODULE_NAME);
        switch (msgId) {
            case 60250:
            case 50115:
                messageHandler = new Message50115();
                break;
            case 50222://打开通讯录
                messageHandler = new Message50222();
                break;
//            case 50101:  //进入模块
//                messageHandler = new Message50101(mHandler, mAdapter);
//                break;
            case 50102:  //打开关闭左右侧滑栏
                messageHandler = new Message50102(mHandler);
                break;
            case 50103://设置左右抽屉不生效区域
                break;
            case 50108://显示/隐藏底部导航栏
                messageHandler = new Message50108(mIndicatorView);
                break;
            case 50240://PDF
                messageHandler = new Message50240();
                break;
            case 50275://视频播放
                messageHandler = new Message50275();
                break;
            case 50276://二维码图片
                messageHandler = new Message50275();
                break;
            case 60300:
                messageHandler = new Message60300();
                break;
            case 60040:
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoginManger.getInstance(MainActivity.this).ExitLogin(new LoginManger.LoginCallback() {
                            @Override
                            public void onSucceed() {
                                islogin = false;
                                MemoryStorage storage = new MemoryStorage();
                                storage.clear();
                            }
                        });
                    }
                });
            case 70004:
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.show("正在注销");
                        CookieManager.getInstance().removeAllCookie();
                        LoginManger.getInstance(MainActivity.this).ExitLogin(new LoginManger.LoginCallback() {
                            @Override
                            public void onSucceed() {
                                loadingDialog.dismiss();
                                islogin = false;
                                mViewpager.setCurrentItem(0,false);
                                mCurrentItem=0;
                                try{
                                WebCacheFragment fragment = (WebCacheFragment) mAdapter.getItem(2);
                                    fragment.loadUrl(MallFragment.assert_url);
                                }catch (Exception e){

                                }
                                mIndicatorView.setVisibility(View.VISIBLE);
                                MemoryStorage storage = new MemoryStorage();
                                storage.clear();
                            }
                        });
                    }
                });
                break;
            default:
                break;
        }
        if (messageHandler != null) {
            return messageHandler.handlerMessage(MainActivity.this, appMessage);
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_CONTACT:
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Log.d("contactData = " + contactData);
                    Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = getContactPhone(cursor);
                        Log.d("contactName = " + contactName + " phoneNumber = " + phoneNumber);
                        sendMessage50223(contactName, phoneNumber);
                        cursor.close();
                    }
                }
                break;
            case QR_CODESCAN:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String content = bundle.getString("result");
                    Log.d("content = " + content);
                    sendMessage50272(content);

                }
                break;
            case UPLOAD_IMAGE:
                Log.d("upload_image");
                if (resultCode == Activity.RESULT_OK) {
                    final String path = data.getStringExtra("path");
                    String serverAddr = data.getStringExtra("serverAddr");
                    String fileName = data.getStringExtra("fileName");

                    dialog = new DialogFrame(this);
                    try {
                        dialog.waitDialog("请稍等...", "正在上传图片", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    HashMap<String, FileBody> file = new HashMap<String, FileBody>();
                    HashMap<String, String> stringMap = new HashMap<String, String>();
                    file.put(fileName, new FileBody(new File(path)));
                    HttpService.getInstance().multiPartRequest(serverAddr, file, stringMap, HttpService.TIMEOUT, 1, new ResponseListener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            Message msg = new Message();
                            msg.what = UPLOAD_IMAGE_SUCCESS;
                            Bundle bundle = new Bundle();
                            bundle.putString("path", path);
                            msg.setData(bundle);
                            mHandler.sendMessage(msg);
                        }

                        @Override
                        public void onErrorResponse(Exception e) {
                            Message msg = new Message();
                            msg.what = UPLOAD_IMAGE_FAILED;
                            Bundle bundle = new Bundle();
                            bundle.putString("path", path);
                            msg.setData(bundle);
                            mHandler.sendMessage(msg);
                        }
                    });

                }
                break;
            case REUQEST_LOGIN:
                if(data==null){
                    mViewpager.setCurrentItem(0);
                    mCurrentItem=0;
                    setmIndicatorViewopen(View.VISIBLE);
                    return;
                }
                 islogin = data.getBooleanExtra("islogin",false);

                if(islogin){
                if (ModuleManager.getInstance().getModule(moduleName) instanceof BaseWebFragment) {
                    BaseWebFragment fragment = (BaseWebFragment) ModuleManager.getInstance().getModule(moduleName);
                    if(!toPage.startsWith("m")) {
                        if (!toPage.startsWith("/"))
                            toPage = "m/" + toPage;
                        else {
                            toPage = "m" + toPage;
                        }
                    }
                    String url = ConfigManager.getInstance(MainActivity.this).getAddressConfigValue("H5_url_pre")+toPage;
                    fragment.loadUrl(url);
                }
                }else{
                    mViewpager.setCurrentItem(0);
                    mCurrentItem=0;
                    setmIndicatorViewopen(View.VISIBLE);
                }
                break;
            case REUQEST_ACTIVATE:
                if(data==null){
                    setmIndicatorViewopen(View.VISIBLE);
                    return;
                }
                boolean isactivite = data.getBooleanExtra("isactivite",false);
                if(isactivite) {
                    if (ModuleManager.getInstance().getModule(moduleName) instanceof BaseWebFragment) {
                        BaseWebFragment fragment = (BaseWebFragment) ModuleManager.getInstance().getModule(moduleName);
                        if (!toPage.startsWith("m")) {
                            if(!toPage.startsWith("/"))
                            toPage = "m/" + toPage;
                            else{
                                toPage = "m" + toPage;
                            }
                        }
                        String url = ConfigManager.getInstance(MainActivity.this).getAddressConfigValue("H5_url_pre") + toPage;
                        fragment.loadUrl(url);
                    }
                }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK&&mCurrentItem == 0){
            long secondPressBackKeyTime = System.currentTimeMillis();
            if (secondPressBackKeyTime - mFirstPressedBackKeyTime > INTERVAL_TIME) {
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                mFirstPressedBackKeyTime = secondPressBackKeyTime;
            } else {
                CoreApplication.getInstance().exit();
                mFirstPressedBackKeyTime = 0;
            }
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onBackPressed() {
        if (KeyboardManager.getInstance() != null && KeyboardManager.getInstance().isShowing()) {
            KeyboardManager.getInstance().dismiss();
            return;
        }

        if (mAdapter.getItem(mCurrentItem) instanceof BaseWebFragment) {
            BaseWebFragment webFragment = (BaseWebFragment) mAdapter.getItem(mCurrentItem);
            sendMessage50107(webFragment);
            return;
        }
        long secondPressBackKeyTime = System.currentTimeMillis();
        if (secondPressBackKeyTime - mFirstPressedBackKeyTime > INTERVAL_TIME) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mFirstPressedBackKeyTime = secondPressBackKeyTime;
        } else {
            CoreApplication.getInstance().exit();
            mFirstPressedBackKeyTime = 0;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNoticeDismissReceiver);
        CoreApplication.getInstance().popActivity(this.getClass().getName());
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP);
            }
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        public List<Fragment> getmFragments() {
            return mFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }
    public void setmIndicatorViewopen(int type){
        mIndicatorView.setVisibility(type);
    }
}
