package com.android.thinkive.invest_sd.bll;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.network.http.MyImageRequest;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.invest_sd.adapter.FristPageMenuAdapter;
import com.android.thinkive.invest_sd.adapter.NewRecommendAdapter;
import com.android.thinkive.invest_sd.adapter.NewsAdapter;
import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;
import com.android.thinkive.invest_sd.beans.NewsBean;
import com.android.thinkive.invest_sd.beans.RecommendBean;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.fragment.MainPageFragment;
import com.android.thinkive.invest_sd.jsonbean.FristPageCicrleItemJsonbean;
import com.android.thinkive.invest_sd.jsonbean.HotProductJsonBean;
import com.android.thinkive.invest_sd.jsonbean.NewsJsonResultBean;
import com.android.thinkive.invest_sd.jsonbean.RecommendJsonBean;
import com.android.thinkive.invest_sd.util.BitmapCache;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.thinkive.invest_sd.util.ImageUtil;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.thinkive.aqf.utils.ToastUtils;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xuemei on 2015/9/9.
 * 主页面的业务类
 */
public class MainPageService {
//    /**
//     * 热门资讯图片加载总数
//     */
//    private final int LOAD_HOT_MSG_ITEM_SUM = 2;
//    private final int LOAD_HOT_COUNSELOR_GROUP_SUM = 5;
    /**
     * 本类服务的Fragment
     */
    private MainPageFragment mFragment;
    /**
     * 上方广告图片加载个数
     */
    private int mLoadAdImageCount;
//    /**
//     * 快捷菜单图片加载个数
//     */
//    private int mLoadFastMenuItemCount;
//    /**
//     * 热门资讯图片加载进度
//     */
//    private int mLoadHotMsgItemCount;
    /**
     * 热门投顾组合图片加载进度
     */
    private int mLoadHotCounselorGroup;

    private BitmapCache mImageCacheManager;

    public MainPageService(MainPageFragment fragment) {
        mFragment = fragment;
        mImageCacheManager = BitmapCache.getInstance(fragment.getActivity());
    }

    /**
     * 请求今日热门资讯
     */
    public void requestHotMessage() {
       HashMap<String,String> params = new HashMap();
        params.put("numPerPage", "2");
        params.put("funcNo", "1000921");
        params.put("curPage", "1");
        params.put("weixin_pk", "gh_f0ead9bdfcf0");
        final RequestBean request = new RequestBean();
        request.setParam(params);
        request.setUrlName("NEWS_URL_HTTP");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                NewsJsonResultBean result = JsonParseUtil.parseJsonToObject(jsonObject, NewsJsonResultBean.class);
                if(result==null){
                    ToastUtils.Toast(mFragment.getActivity(), "资讯加载失败：未获取到数据");
                    return;
                }
                if (result.getError_no() == 0) {
                        if (result.getResults() != null && result.getResults().size() >= 1 && result.getResults().get(0).getData() != null) {
                            List<NewsBean> list = result.getResults().get(0).getData();
                            if (list.size() >= 0) {
                                mFragment.onGetHotMessage(list);
                            }
                        }
                }
            }
            @Override
            public void onErrorResponse(Exception e) {
                com.thinkive.aqf.info.utils.ToastUtils.Toast(mFragment.getActivity(), "资讯加载失败:请检查网络");
            }
        });
}
    /**
     * 请求今日热门投顾组合
     */
    public void requestHotGroupMessage() {
//        String user_data = ThinkiveTools.getDataByMsg(mFragment.getActivity(), "ytg_user_data");
//        try {
//            JSONObject jsonObject = new JSONObject(user_data);
//            String customer_id = jsonObject.getString("userId");
//            if (TextUtils.isEmpty(customer_id)) {
//                LogUtil.printTradeLog("e", "useId取不到！！");
//            }
//            HashMap<String, String> paramMap = new HashMap<String, String>();
//            paramMap.put("numPerPage", "5");
//            paramMap.put("curPage", "1");
//            paramMap.put("customer_id", customer_id);
//            new Request408343(paramMap, new IRequestAction() {
//                @Override
//                public void onSuccess(Context context, Bundle bundle) {
//                    final ArrayList<HotInvestCounselor> dataList = bundle.getParcelableArrayList(Request408343.BUNDLE_KEY);
//                    if (dataList == null) {
//                        return;
//                    }
//                    ArrayList<HotInvestCounselor> subList = new ArrayList<HotInvestCounselor>();
//                    // 有色圆形图案资源数组
//                    int[] circleColours = new int[]{
//                            R.drawable.margin_circle_colour1,
//                            R.drawable.margin_circle_colour2,
//                            R.drawable.margin_circle_colour3,
//                    };
//                    // 一共有几条数据需要设置有色圆圈，不超过三条
//                    int circleSetCount = 3 <= dataList.size() ? 3 : dataList.size();
//                    for (int i = 0; i < circleSetCount; i++) {
//                        dataList.get(i).setProfitColor(circleColours[i]);
//                        subList.add(dataList.get(i));
//                    }
//                    mFragment.onGetHotGroupMessage(subList);
//                    mLoadHotCounselorGroup = 0;
//                    final int maxWidth = (int) ScreenUtils.dpToPx(context, 25);
//                    final int maxHeight = (int) ScreenUtils.dpToPx(context, 25);
//                    MyImageRequest imageRequest;
//                    String imgUrl = ConfigManager.getInstance(context).getAddressConfigBean(Constants.URL_INVEST_COUNSELOR).getPriorityValue();
//                    imgUrl = ThinkiveUtil.formatUrlToIp(imgUrl);
//                    int subListSize = subList.size();
//                    for (int i = 0; i < subListSize; i++) {
//                        final HotInvestCounselor bean = subList.get(i);
//                        // 先读取缓存中的图片
//                        mFragment.onGetHotGroupImg(i,
//                                mImageCacheManager.getImage(bean.getFace_image_small()));
//                        imageRequest = new MyImageRequest(imgUrl + bean.getFace_image_small(), new Response.Listener<Bitmap>() {
//                            @Override
//                            public void onResponse(Bitmap bitmap) {
//                                bitmap = BitmapUtil.makeBitmapToCircular(bitmap);
//                                mFragment.onGetHotGroupImg(mLoadHotCounselorGroup, bitmap);
//                                mLoadHotCounselorGroup++;
//                                mImageCacheManager.saveBmpToSd(bitmap, bean.getFace_image_small());
//                            }
//                        }, maxWidth, maxHeight, Bitmap.Config.RGB_565, null);
//                        CoreApplication.getInstance().addToRequestQueue(imageRequest);
//                    }
//                }
//
//                @Override
//                public void onFailed(Context context, Bundle bundle) {
//                    ToastUtils.toast(context, bundle.getString(BaseRequest.ERROR_INFO));
//                }
//            }).request();
//        } catch (JSONException je) {
//            je.printStackTrace();
//        }
    }

    /**
     * 请求广告滑动页图片
     */
    public void requestAds(boolean isRefresh) {
        final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_HEAD, mFragment.getActivity());
        if (data!=null&&!isRefresh) {
            FristPageCicrleItemJsonbean jsonben = JsonParseUtil.parseJsonToObject(data, FristPageCicrleItemJsonbean.class);
            mFragment.onGetAdPagersData(jsonben.getResults());
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
                    ToastUtils.Toast(mFragment.getActivity(), "加载广告图失败：");
                    return;
                }
                FristPageCicrleItemJsonbean jsonben =  JsonParseUtil.parseJsonToObject(jsonObject, FristPageCicrleItemJsonbean.class);
                if(jsonben==null){
                    ToastUtils.Toast(mFragment.getActivity(), "加载广告图失败：未获取到数据");
                    return;
                }
                if(jsonben.getError_no() == 0) {
                    mFragment.onGetAdPagersData(jsonben.getResults());
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_HEAD, jsonObject,mFragment.getActivity());
                }else{
                    ToastUtils.Toast(mFragment.getActivity(), "加载广告图失败" + jsonben.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(mFragment.getActivity(), "加载广告图失败,请检查网络");
            }
        });
    }

    /**
     * 快捷菜单数据请求方法
     */
    public void requestFastMenu(boolean isRefresh) {
         final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_MENU,mFragment.getActivity());
        if (data!=null&&!isRefresh) {
            //取到缓存 直接去界面显示
            FristPageCicrleItemJsonbean jsonbean = JsonParseUtil.parseJsonToObject(data, FristPageCicrleItemJsonbean.class);
            List<FristPageCicrleItemBean> list = new ArrayList<FristPageCicrleItemBean>();
            for (FristPageCicrleItemBean bean : jsonbean.getResults()) {
                if (bean.getIs_open() == 1 ) {
                    list.add(bean);
                }
            }
            //多加一个显示配置
            list.add(new FristPageCicrleItemBean(4));
            mFragment.onGetFastMenuData(list);
        }
        //取网络数据
        RequestBean request = new RequestBean();
        HashMap<String,String> params= new HashMap<>();
        params.put("funcNo", "902000");
        params.put("channel", "0");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject==null){
                    ToastUtils.Toast(  mFragment.getActivity(), "菜单加载失败：");
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
                        mFragment.onGetFastMenuData(list);
                        FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_MENU, jsonObject,mFragment.getActivity());
                    }else{
                        FristPageCicrleItemJsonbean localJsonbean = JsonParseUtil.parseJsonToObject(data, FristPageCicrleItemJsonbean.class);
                        List<FristPageCicrleItemBean> locallist =  localJsonbean.getResults();
                        List<FristPageCicrleItemBean> netlist =  jsonbean.getResults();
                        //遍历网络数据
                        for(FristPageCicrleItemBean netbean : netlist){
                            boolean isnotfound = true;
                            for(int i=0;i<locallist.size();i++){
                                FristPageCicrleItemBean localbean = locallist.get(i);
                                if(netbean.getId() == localbean.getId()){
                                    isnotfound = false;
                                    int isshwo = localbean.getIs_default();
                                    localbean = netbean;
                                    localbean.setIs_default(isshwo);
                                }
                            }
                            if(isnotfound){
                                locallist.add(netbean);
                            }
                        }
                       //遍历本地
                        for(int i=0;i<locallist.size();i++){
                            FristPageCicrleItemBean localbean = locallist.get(i);
                            boolean isnotfound = true;
                            for(FristPageCicrleItemBean netbean : netlist){
                                if(netbean.getId() == localbean.getId()){
                                    isnotfound = false;
                                }
                            }
                            if(isnotfound){
                                locallist.remove(localbean);
                            }
                        }
                        //刷新显示
                        List<FristPageCicrleItemBean> list = new ArrayList<FristPageCicrleItemBean>();
                        for (int i=0;i<locallist.size();i++) {
                            FristPageCicrleItemBean bean = locallist.get(i);
                            if (bean.getIs_default() == 1 ) {
                                list.add(bean);
                            }
                        }
                        //多加一个显示配置
                        list.add(new FristPageCicrleItemBean(4));
                        mFragment.onGetFastMenuData(list);
                        try {
                            String data  = new ObjectMapper().writeValueAsString(locallist);
                            FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_MENU, new JSONObject(data),mFragment.getActivity());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    ToastUtils.Toast(mFragment.getActivity(), "菜单加载失败：" + jsonbean.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(mFragment.getActivity(), "菜单加载失败,请检查网络" );
            }
        });
    }


    /**
     * 热销产品加载
     */
    public void requestHotProductData(boolean isRefresh) {
        final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_HOTPRODUCT, mFragment.getActivity());
        if (data != null&!isRefresh) {
            HotProductJsonBean jsonbean = JsonParseUtil.parseJsonToObject(data, HotProductJsonBean.class);
            mFragment.onGetHotDataDataList(jsonbean.getResults());
        }
        RequestBean request = new RequestBean();
        HashMap<String, String> params = new HashMap<>();
        params.put("funcNo", "3000001");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD_SC");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (jsonObject == null && data == null) {
                    ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败：");
                    return;
                }
                HotProductJsonBean jsonbean = JsonParseUtil.parseJsonToObject(jsonObject, HotProductJsonBean.class);
                if(jsonbean==null){
                    ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败：未获取到数据");
                    return;
                }
                if (jsonbean.getError_no() == 0) {
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_HOTPRODUCT, jsonObject, mFragment.getActivity());
                    mFragment.onGetHotDataDataList(jsonbean.getResults());
                } else {
                    if (data == null) {
                    ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败：" + jsonbean.getError_info());
                }
            }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败,请检查网络");
            }
        });
    }


    /**
     * 最新推荐理财加载
     */
    public void requestRecommendData(boolean isRefresh) {
        final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_REMMOEND, mFragment.getActivity());
        if (data != null&!isRefresh) {
            RecommendJsonBean jsonbean = JsonParseUtil.parseJsonToObject(data, RecommendJsonBean.class);
//            mFragment.onGetRecommendDataList(jsonbean.getResults().get(0).getData());
        }
        RequestBean request = new RequestBean();
        HashMap<String, String> params = new HashMap<>();
        params.put("funcNo", "1002004");
        params.put("product_shelf", "1");
        params.put("page", "1");
        params.put("numPerPage", "3");
        params.put("recommend_type", "1");
        params.put("product_status", "0");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (jsonObject == null && data == null) {
                    ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败：");
                    return;
                }
                RecommendJsonBean jsonbean = JsonParseUtil.parseJsonToObject(jsonObject, RecommendJsonBean.class);
                if(jsonbean==null){
                    ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败：未获取到数据");
                    return;
                }
                if (jsonbean.getError_no() == 0) {
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_REMMOEND, jsonObject, mFragment.getActivity());
//                    mFragment.onGetRecommendDataList(jsonbean.getResults().get(0).getData());
                } else {
                    if (data == null) {
                        ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败：" + jsonbean.getError_info());
                    }
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(mFragment.getActivity(), "最新推荐理财加载失败,请检查网络");
            }
        });
    }
        /**
         * 请求精品推荐的基金产品
         */
    public void requestRecommendData2(boolean isRefresh) {
        final JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_REMMOEND_LC,mFragment.getActivity());
        if (data!=null&&!isRefresh) {
            RecommendJsonBean jsonbean = JsonParseUtil.parseJsonToObject(data, RecommendJsonBean.class);
//            mFragment.onGetRecommendDataList(jsonbean.getResults().get(0).getData());
        }
        RequestBean request = new RequestBean();
        HashMap<String,String> params= new HashMap<>();
        params.put("funcNo", "1002005");
        params.put("product_shelf", "1");
        params.put("numPerPage", "3");
        params.put("page", "1");
        params.put("recommend_type", "4");
        params.put("product_status", "0");
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject==null&&data==null){
                    ToastUtils.Toast( mFragment.getActivity(), "最新推荐基金加载失败：");
                    return;
                }
                RecommendJsonBean jsonbean = JsonParseUtil.parseJsonToObject(jsonObject, RecommendJsonBean.class);
                if(jsonbean!=null&&jsonbean.getError_no()==0){
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_REMMOEND_LC, jsonObject, mFragment.getActivity());
//                    mFragment.onGetRecommendDataList2(jsonbean.getResults().get(0).getData());
                }else{
                    if(data==null) {
                        if(jsonbean!=null) {
                            ToastUtils.Toast(mFragment.getActivity(), "最新推荐基金加载失败：" + jsonbean.getError_info());
                        }else{
                            ToastUtils.Toast(mFragment.getActivity(), "最新推荐基金加载失败：未获取到数据");
                        }
                    }

                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast( mFragment.getActivity(), "最新推荐基金加载失败,请检查网络");
            }
        });
    }
}



