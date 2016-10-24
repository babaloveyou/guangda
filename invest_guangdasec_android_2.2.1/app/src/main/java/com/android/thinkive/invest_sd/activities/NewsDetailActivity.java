package com.android.thinkive.invest_sd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKActivity;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.jsonbean.BaseJsonbean;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.thinkive.aqf.info.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @Description TODO 微资讯详情
 * @version [版本号, 2014-10-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 * @author ---余巍---
 */
public class NewsDetailActivity extends TKActivity {

    private ImageView mTextViewBack;
    public TextView tv_title, tv_publish_date;
    private WebView webViewContent;
    private LoadingDialog loadingDialog;
    private String oldStr = "<img src=\"" + "/upload/";
    private String newStr = "<img width=\"100%\" src=\"https://e.ebscn.com/upload/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        loadingDialog = new LoadingDialog(this);
        findViews();
        setListeners();
        loadData();
    }

    public void loadData() {
        Intent intent = getIntent();
        if (intent != null) {
            String id=intent.getStringExtra("id");
            String title = intent.getStringExtra("title");
            String publish_date = intent.getStringExtra("publish_date");
            if (!TextUtils.isEmpty(title)) {
                tv_title.setText(title);
            }
            if (!TextUtils.isEmpty(publish_date)) {
                tv_publish_date.setText(publish_date);
            }
            queryContent(id,title);
        }

    }
    
    private void queryContent(String id,String title){
        loadingDialog.show("正在加载...");
        HashMap<String,String> params = new HashMap();
        params.put("id", id);
        params.put("title", title);
        params.put("funcNo", "1000922");
        final RequestBean request = new RequestBean();
        request.setParam(params);
        request.setUrlName("NEWS_URL_HTTP");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {


            @Override
            public void onResponse(JSONObject jsonObject) {
                loadingDialog.dismiss();
                BaseJsonbean result = JsonParseUtil.parseJsonToObject(jsonObject, BaseJsonbean.class);
                if (result.getError_no() == 0) {
                    try {
                        JSONArray jsonArray = jsonObject.optJSONArray("results");
                        JSONObject data = jsonArray.getJSONObject(0);
                        String content = data.optString("content").replace(oldStr, newStr);
                        getWebViewContent().loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    ToastUtils.Toast(NewsDetailActivity.this, "资讯加载失败:" + result.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                loadingDialog.dismiss();
                ToastUtils.Toast(NewsDetailActivity.this, "资讯加载失败:请检查网络");
            }

        });
    }
    public void findViews() {
        mTextViewBack = (ImageView) findViewById(R.id.tv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_publish_date = (TextView) findViewById(R.id.tv_publish_date);
        webViewContent=(WebView)findViewById(R.id.webViewContent);
    }

    public void setListeners() {
        mTextViewBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    /**
     * 获得WebView
     * @return WebView
     */
    public WebView getWebViewContent() {
        return webViewContent;
    }
}
