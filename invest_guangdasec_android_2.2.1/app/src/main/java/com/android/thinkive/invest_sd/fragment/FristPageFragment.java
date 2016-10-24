package com.android.thinkive.invest_sd.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.message.MessageService;
import com.android.thinkive.framework.module.IModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.activities.EditeMenuItemActivity;
import com.android.thinkive.invest_sd.activities.LoginActivity;
import com.android.thinkive.invest_sd.adapter.FristPageMenuAdapter;
import com.android.thinkive.invest_sd.adapter.HeadImagePagerAdapter;
import com.android.thinkive.invest_sd.adapter.NewRecommendAdapter;
import com.android.thinkive.invest_sd.base.BaseFragment;
import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;
import com.android.thinkive.invest_sd.beans.FristPageNoticeBean;
import com.android.thinkive.invest_sd.beans.RecommendBean;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.jsonbean.FristPageCicrleItemJsonbean;
import com.android.thinkive.invest_sd.jsonbean.FristPageNoticeJsonBean;
import com.android.thinkive.invest_sd.jsonbean.RecommendJsonBean;
import com.android.thinkive.invest_sd.util.AppUtil;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.thinkive.invest_sd.util.ImageUtil;
import com.android.thinkive.invest_sd.widget.AutoTextView;
import com.android.thinkive.invest_sd.widget.ScrollViewExtend;
import com.thinkive.aqf.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiangfan on 2015/10/29.
 */
public class FristPageFragment extends BaseFragment implements IModule{
    public final int REQUEST_EDIT_MENU = 110;
    private ViewPager viewpager_frist_head;
    private HeadImagePagerAdapter headImagePagerAdapter;
    private LinearLayout ll_head_dot;
    private int currentIndex;
    private ScrollViewExtend frist_fragment_main_scrollview;
    //当前显示公告的index
    private int notficepostion = 1;

    //公告的具体内容
    private List<FristPageNoticeBean> noticeList;
    //轮播公告的空间
    private AutoTextView tv_frist_notfice;
    //包含公告的空间 如无公告需要隐藏
    private View ll_notice_show;

    private ListView listview_new_recommend;
    private NewRecommendAdapter recommendAdapter;

    private GridView gridview_frist_menu;
    private FristPageMenuAdapter fristPageMenuAdapter;

    private View but_new_recommend_more;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            notficepostion = notficepostion % noticeList.size();

            tv_frist_notfice.next();
            if(notficepostion ==0 ){
                tv_frist_notfice.setText(noticeList.get(noticeList.size()-1).getTitle());
            }else {
                tv_frist_notfice.setText(noticeList.get(notficepostion-1).getTitle());
            }
            notficepostion++;
            handler.postDelayed(this, 4000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleManager.getInstance().registerModule(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fristpage,null);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    @Override
    protected void initView(View view) {
        viewpager_frist_head = (ViewPager) view.findViewById(R.id.viewpager_frist_head);
        ll_head_dot = (LinearLayout) view.findViewById(R.id.ll_head_dot);
        gridview_frist_menu = (GridView) view.findViewById(R.id.gridview_frist_menu);
        gridview_frist_menu.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listview_new_recommend = (ListView) view.findViewById(R.id.listview_new_recommend);
        but_new_recommend_more = view.findViewById(R.id.but_new_recommend_more);
        frist_fragment_main_scrollview = (ScrollViewExtend) view.findViewById(R.id.frist_fragment_main_scrollview);
        tv_frist_notfice = (AutoTextView) view.findViewById(R.id.tv_frist_notfice);
        ll_notice_show = view.findViewById(R.id.ll_notice_show);
    }
    private void initData(){
        loadHeadImae();
        loadRecommend();
        loadMeun();
        loadNotice();
    }
    @Override
    protected void setTheme() {

    }

    private void initEvent(){
        frist_fragment_main_scrollview.setOnScrollListener(new ScrollViewExtend.OnScrollListener() {
            @Override
            public void onScrollChanged(ScrollViewExtend scrollView, int x, int y, int oldx, int oldy) {
                Log.e("滚动!!!!!!!!!!"+y);
            }
        });
        gridview_frist_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getCount() - 1) {
                    Intent intent = new Intent(getActivity(), EditeMenuItemActivity.class);
                    startActivityForResult(intent, REQUEST_EDIT_MENU);
                } else {
                    Adapter adapter = parent.getAdapter();
                    FristPageCicrleItemBean bean = (FristPageCicrleItemBean) ((FristPageMenuAdapter) adapter).getItem(position);
                    if(bean.getJump_url().contains("trade")){
                        com.thinkive.aqf.info.utils.ToastUtils.Toast(getActivity(),"攻城狮正在玩命开发中");
                        return;
                    }
                    if (bean.getJump_type() == 0) {
                        JSONObject params = new JSONObject();
                        try {
                            params.put("moduleName", bean.getJump_url());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startModel(bean.getJump_url());
                    } else if (bean.getJump_type() == 1) {
                        String moudle = bean.getJump_url();
                        String a[] = moudle.split(",");
                        if (a.length == 1) {
                            startModel(a[0]);
                        } else if (a.length >= 2) {
                            startModel(a[0], a[1]);
                        }
                    } else if (bean.getJump_type() == 2) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(bean.getJump_url());
                        intent.setData(content_url);
                        startActivity(intent);
                    }
                }
            }
        });
        but_new_recommend_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject params = new JSONObject();
                try {
                    params.put("moduleName","financial-mall");
                    params.put("toPage","mall/itemsFinan.html");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startModel("financial-mall","mall/itemsFinan.html");

            }
        });
    }

    @Override
    public void onResume() {
        frist_fragment_main_scrollview.setScrollY(0);
        super.onResume();
    }

    public void setScrolltop(){
        frist_fragment_main_scrollview.setScrollY(0);
    }
    /**
     * 打开模块zo
     */
    private void startModel(String moduleName){
        startModel(moduleName, "", -1);
    }
    /**
     * 打开模块
     */
    private void startModel(String moduleName,String toPage){
        startModel(moduleName,toPage,-1);
    }
    /**
     * 打开模块
     */
    private void startModel(String moduleName,String toPage,int product_id){
        JSONObject content = new JSONObject();
        JSONObject params = new JSONObject();
        try {
            if(product_id!=-1) {
                toPage = toPage +"?product_id="+product_id;
            }
            params.put("toPage",toPage);
//            params.put("type","1");
            content.put("moduleName",moduleName);
            content.put("params",params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppMessage msg = new AppMessage("home",50101,content);
        MessageManager.getInstance(getActivity()).sendMessage(msg);
    }
    @Override
    public String onMessageReceive(AppMessage appMessage) {
        return null;
    }


    /**
     * 加载公告通知
     */
    private void loadNotice(){
        RequestBean request = new RequestBean();
        HashMap<String,String> params= new HashMap<>();
        params.put("funcNo", "9405072");
        params.put("catalog_id", "2052");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject==null){
                    ToastUtils.Toast(getActivity(), "公告加载失败：");
                    return;
                }
                FristPageNoticeJsonBean jsonben =  JsonParseUtil.parseJsonToObject(jsonObject, FristPageNoticeJsonBean.class);
                if(jsonben.getError_no()==0){
                        if(jsonben.getResults()==null||jsonben.getResults().size()<=0){
                            ll_notice_show.setVisibility(View.GONE);
                        }else{
                            ll_notice_show.setVisibility(View.VISIBLE);
                            if(jsonben.getResults().size()>2) {
                                noticeList = new ArrayList<FristPageNoticeBean>();
                                noticeList.add(jsonben.getResults().get(0));
                                noticeList.add(jsonben.getResults().get(1));
                            }else{
                                noticeList = jsonben.getResults();
                            }
                            handler.postDelayed(runnable, 200);
                        }
                }else{
                    ToastUtils.Toast(getActivity(), "公告加载失败："+jsonben.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(getActivity(), "公告加载失败请检查网络");
            }
        });
        }
    /**
     * 加载顶部广告
     */
    private void loadHeadImae() {
        final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_HEAD,getActivity());
        if (data!=null) {
            FristPageCicrleItemJsonbean jsonben = JsonParseUtil.parseJsonToObject(data, FristPageCicrleItemJsonbean.class);
            buildHeadImage(jsonben);
        }
        RequestBean request = new RequestBean();
        HashMap<String,String> params= new HashMap<>();
        params.put("funcNo", "902001");
        params.put("channel", "0");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject==null){
                    ToastUtils.Toast(getActivity(), "加载广告图失败：");
                    return;
                }
                FristPageCicrleItemJsonbean jsonben =  JsonParseUtil.parseJsonToObject(jsonObject, FristPageCicrleItemJsonbean.class);
                if(jsonben.getError_no() == 0) {
                    buildHeadImage(jsonben);
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_HEAD, jsonObject,getActivity());
                }else{
                    ToastUtils.Toast(getActivity(), "加载广告图失败" + jsonben.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(getActivity(), "加载广告图失败,请检查网络");
            }
        });
    }

    public void buildHeadImage(FristPageCicrleItemJsonbean jsonbean) {
        if (jsonbean != null && jsonbean.getError_no() == 0 && jsonbean.getResults() != null && jsonbean.getResults().size() > 0) {
            List<FristPageCicrleItemBean> topList = new ArrayList<FristPageCicrleItemBean>();
            FristPageCicrleItemBean launcher_bean =null ;
            for(FristPageCicrleItemBean bean :jsonbean.getResults()){
                if ("startAd".equals(bean.getGroup_no())){
                    launcher_bean = bean;
                }
                if("rockAdmenu".equals(bean.getGroup_no())){
                    topList.add(bean);
                }
            }
            if(launcher_bean !=null){
                ImageUtil.saveLauncherImage(launcher_bean.getImage_url(),getActivity());
            }
            ll_head_dot.removeAllViews();
            for (int i = 0; i < topList.size(); i++) {
                ImageView view = new ImageView(getActivity());
                view.setImageResource(R.drawable.selector_dot);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 8, 0);
                view.setLayoutParams(lp);
                ll_head_dot.addView(view);
            }
            if (headImagePagerAdapter == null) {
                headImagePagerAdapter = new HeadImagePagerAdapter(topList, getActivity());
                viewpager_frist_head.setAdapter(headImagePagerAdapter);
            } else {
                headImagePagerAdapter.setlist(topList);
                headImagePagerAdapter.notifyDataSetChanged();
            }
            ll_head_dot.getChildAt(0).setEnabled(false);
            viewpager_frist_head.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    setCurDot(i);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }
    }
    private void setCurDot(int positon) {
        if (positon < 0 || positon > ll_head_dot.getChildCount() - 1 || currentIndex == positon) {
            return;
        }
        ll_head_dot.getChildAt(positon).setEnabled(false);
        ll_head_dot.getChildAt(currentIndex).setEnabled(true);

        currentIndex = positon;
    }



    /**
     * 加载热门推荐
     */
    private void loadRecommend() {
        final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_REMMOEND,getActivity());
        if (data!=null) {
            RecommendJsonBean jsonbean = JsonParseUtil.parseJsonToObject(data, RecommendJsonBean.class);
            if (recommendAdapter == null) {
                recommendAdapter = new NewRecommendAdapter(getActivity(), jsonbean.getResults().get(0).getData());
                listview_new_recommend.setAdapter(recommendAdapter);
                recommendAdapter.setListener(new NewRecommendAdapter.OnRecommendItenClick() {
                    @Override
                    public void onclick(RecommendBean bean) {
                        int product_id =  bean.getProduct_id();
                        startModel("financial-mall","mall/itemsFinanInfo.html",product_id);
                    }
                });
            } else {
                recommendAdapter.setList(jsonbean.getResults().get(0).getData());
                recommendAdapter.notifyDataSetChanged();
            }
        }

        RequestBean request = new RequestBean();
        HashMap<String,String> params= new HashMap<>();
        params.put("funcNo", "1002004");
        params.put("product_shelf", "1");
        params.put("numPerPage", "4");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject==null){
                    ToastUtils.Toast(getActivity(), "热门推荐加载失败：");
                    return;
                }
                RecommendJsonBean jsonbean = JsonParseUtil.parseJsonToObject(jsonObject, RecommendJsonBean.class);
                if(jsonbean.getError_no()==0){
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_REMMOEND, jsonObject,getActivity());
                    if (recommendAdapter == null) {
                        recommendAdapter = new NewRecommendAdapter(getActivity(), jsonbean.getResults().get(0).getData());
                        listview_new_recommend.setAdapter(recommendAdapter);
                        recommendAdapter.setListener(new NewRecommendAdapter.OnRecommendItenClick() {
                            @Override
                            public void onclick(RecommendBean bean) {
                                int product_id =  bean.getProduct_id();
//                                AppTool.startModel(getIAppControl(), "financial-mall");
                                try {
                                    startModel("financial-mall", "mall/itemsFinanInfo.html", product_id);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        recommendAdapter.setList(jsonbean.getResults().get(0).getData());
                        recommendAdapter.notifyDataSetChanged();
                    }
                }else{
                    ToastUtils.Toast(getActivity(), "热门推荐加载失败：" + jsonbean.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(getActivity(), "热门推荐加载失败,请检查网络");
            }
        });
    }

    /**
     * 加载菜单
     */
    private void loadMeun() {
        final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_MENU,getActivity());
        if (data!=null) {
            //取到缓存 直接去界面显示
            FristPageCicrleItemJsonbean jsonbean = JsonParseUtil.parseJsonToObject(data, FristPageCicrleItemJsonbean.class);
            List<FristPageCicrleItemBean> list = new ArrayList<FristPageCicrleItemBean>();
            for (FristPageCicrleItemBean bean : jsonbean.getResults()) {
                if (bean.getIs_default() == 1 ) {
                    list.add(bean);
                }
            }
            //多加一个显示配置
            list.add(new FristPageCicrleItemBean(4));
            if (fristPageMenuAdapter == null) {
                fristPageMenuAdapter = new FristPageMenuAdapter(getActivity());
                fristPageMenuAdapter.setlist(list);
                gridview_frist_menu.setAdapter(fristPageMenuAdapter);
            } else {
                fristPageMenuAdapter.setlist(list);
                fristPageMenuAdapter.notifyDataSetChanged();
            }
//            loadUserMeun();
        }
        //取网络数据
        RequestBean request = new RequestBean();
        HashMap<String,String> params= new HashMap<>();
        params.put("funcNo", "902000");
        params.put("channel", "1");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject==null){
                    ToastUtils.Toast(getActivity(), "菜单加载失败：");
                    return;
                }
                FristPageCicrleItemJsonbean jsonbean = JsonParseUtil.parseJsonToObject(jsonObject, FristPageCicrleItemJsonbean.class);
                if (jsonbean != null && jsonbean.getError_no() == 0 && jsonbean.getResults() != null && jsonbean.getResults().size() > 0) {
                    if (data == null) {
                        //本地无缓存需使用网路数据刷新界面并添加缓存
                        List<FristPageCicrleItemBean> list = new ArrayList<FristPageCicrleItemBean>();
                        for (FristPageCicrleItemBean bean : jsonbean.getResults()) {
                            if (bean.getIs_default() == 1 ) {
                                list.add(bean);
                            }
                        }
                        //多加一个显示配置
                        list.add(new FristPageCicrleItemBean(4));
                        if (fristPageMenuAdapter == null) {
                            fristPageMenuAdapter = new FristPageMenuAdapter(getActivity());
                            fristPageMenuAdapter.setlist(list);
                            gridview_frist_menu.setAdapter(fristPageMenuAdapter);
                        } else {
                            fristPageMenuAdapter.setlist(list);
                            fristPageMenuAdapter.notifyDataSetChanged();
                        }
                        FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_MENU, jsonObject,getActivity());
//                        loadUserMeun();
                    }
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_MENU_EDIT, jsonObject,getActivity());
                } else {
                    ToastUtils.Toast(getActivity(), "菜单加载失败：" + jsonbean.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(getActivity(), "菜单加载失败,请检查网络" );
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
                JSONObject result = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_MENU,getActivity());
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
                        if (fristPageMenuAdapter == null) {
                            fristPageMenuAdapter = new FristPageMenuAdapter(getActivity());
                            fristPageMenuAdapter.setlist(list);
                            gridview_frist_menu.setAdapter(fristPageMenuAdapter);
                        } else {
                            fristPageMenuAdapter.setlist(list);
                            fristPageMenuAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }
}
