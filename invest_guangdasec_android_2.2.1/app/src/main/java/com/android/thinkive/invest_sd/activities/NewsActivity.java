package com.android.thinkive.invest_sd.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKActivity;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.module.IModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.adapter.NewsAdapter;
import com.android.thinkive.invest_sd.beans.NewsBean;
import com.android.thinkive.invest_sd.jsonbean.NewsJsonResultBean;
import com.android.thinkive.invest_sd.util.ImageUtil;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.thinkive.aqf.info.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * @Description TODO 微资讯列表
 * @version [版本号, 2014-10-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 * @author ---余巍---
 */
public class NewsActivity extends TKActivity implements IModule {

    private ListView mListView;
    private TextView mTvAleart;
    private RelativeLayout mRelativeLayoutTop;
    private TextView mTitle;
    private ImageView mImageView;
    private TextView mTextViewSubAttention,mTextViewServiceAttention;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleManager.getInstance().registerModule(this);
        setContentView(R.layout.activity_news);
        loadingDialog = new LoadingDialog(this);
        findViews();
        setListeners();
        loadData();
    }

    public void loadData() {
        loadingDialog.show("正在加载");
        HashMap<String,String> params = new HashMap();
        params.put("numPerPage", "15");
        params.put("funcNo", "1000921");
        params.put("curPage", "1");
        params.put("weixin_pk", "gh_f0ead9bdfcf0");
        final RequestBean request = new RequestBean();
        request.setParam(params);
        request.setUrlName("NEWS_URL_HTTP");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                loadingDialog.dismiss();
                NewsJsonResultBean result = JsonParseUtil.parseJsonToObject(jsonObject,NewsJsonResultBean.class);
                if(result.getError_no() == 0 ){
                    try{
                        if(result.getResults()!=null&&result.getResults().size()>=1&&result.getResults().get(0).getData()!=null){
                            List<NewsBean> list = result.getResults().get(0).getData();
                            if(list.size()>=0){
                                getmTvAleart().setVisibility(View.GONE);
                                getmRelativeLayoutTop().setVisibility(View.VISIBLE);
                                NewsBean bean = list.get(0);
                                String url = ConfigManager.getInstance(NewsActivity.this).getAddressConfigValue("WEIZIXUN_URL_PHOTO")+ bean.getImage_path();
                                ImageUtil.setImage(getmImageView(),url,NewsActivity.this);
                                NewsAdapter newsAdapter = new NewsAdapter(list,NewsActivity.this);
                                    getmListView().setAdapter(newsAdapter);
                            }else{
                                getmTvAleart().setVisibility(View.VISIBLE);
                                getmRelativeLayoutTop().setVisibility(View.GONE);
                            }
                        }else{
                            getmTvAleart().setVisibility(View.VISIBLE);
                            getmRelativeLayoutTop().setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    getmTvAleart().setVisibility(View.VISIBLE);
                    getmRelativeLayoutTop().setVisibility(View.GONE);
                    ToastUtils.Toast(NewsActivity.this,"资讯加载失败:"+result.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                loadingDialog.dismiss();
                ToastUtils.Toast(NewsActivity.this,"资讯加载失败:请检查网络");
            }
        });
//        startTask(new NewsRequest(parameter));
    }

    public void findViews() {
        mTextViewBack = (ImageView) findViewById(R.id.tv_back);
        mListView = (ListView) findViewById(R.id.lv);
        mTvAleart = (TextView) findViewById(R.id.tv_alert);
        mRelativeLayoutTop = (RelativeLayout) findViewById(R.id.rela_top_view);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mImageView = (ImageView) findViewById(R.id.img_top);
        mTextViewSubAttention=(TextView)findViewById(R.id.tv_sub_attention);
        mTextViewServiceAttention=(TextView)findViewById(R.id.tv_service_attention);
    }

    public void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsBean bean = (NewsBean) parent.getAdapter().getItem(position);
                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("publish_date", bean.getPublish_date());
                startActivity(intent);
            }
        });
        mTextViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRelativeLayoutTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsBean bean = (NewsBean) mListView.getAdapter().getItem(0);
                Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("publish_date", bean.getPublish_date());
                startActivity(intent);
            }
        });
        mTextViewSubAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View subdialogView = LayoutInflater.from(NewsActivity.this).inflate(
                        R.layout.attention_sub_news, null);
                Dialog subDialog = new AlertDialog.Builder(NewsActivity.this).create();
                subDialog.show();
                subDialog.getWindow().setContentView(subdialogView);
            }
        });
        mTextViewServiceAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View serviceDialogView = LayoutInflater.from(NewsActivity.this).inflate(
                        R.layout.attention_service_news, null);
                Dialog serviceDialog = new AlertDialog.Builder(NewsActivity.this).create();
                serviceDialog.show();
                serviceDialog.getWindow().setContentView(serviceDialogView);
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
    public RelativeLayout getmRelativeLayoutTop() {
        return mRelativeLayoutTop;
    }

    public TextView getmTitle() {
        return mTitle;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public TextView getmTvAleart() {
        return mTvAleart;
    }

    public ListView getmListView() {
        return mListView;
    }

    private ImageView mTextViewBack;

    @Override
    public String onMessageReceive(AppMessage appMessage) {
        return null;
    }
}
