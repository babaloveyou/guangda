package com.android.thinkive.invest_sd.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.thinkive.framework.compatible.TKActivity;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.util.AppUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * @author ---余巍---
 * @version [版本号, 2015-2-2]
 * @Description 开户首页
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class OpenAccountHomeActivity extends TKActivity {
    /**
     * 返回按钮
     */
    private ImageView mTextViewBack = null;
    /**
     * 开户
     */
    private TextView mOpenAccount = null;
    /**
     * 港股通开户
     */
    private TextView mOpenGGT = null;
    /**
     * 融资融券开户
     */
    private TextView mOpenRZRQ = null;
    private TextView mOrderCardTv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        findViews();
        setListeners();
        initData();
    }

    public void findViews() {
        mTextViewBack = (ImageView) findViewById(R.id.tv_back);
        mOpenAccount = (TextView) findViewById(R.id.tv_open_account);
        mOpenGGT = (TextView) findViewById(R.id.tv_open_ggt);
        mOpenRZRQ = (TextView) findViewById(R.id.tv_open_rzrq);
        mOrderCardTv = (TextView) findViewById(R.id.tv_order_card);
    }

    public void setListeners() {
        mTextViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mOpenAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "m/open/index.html";
                AppUtil.startModel("open", url, OpenAccountHomeActivity.this);
                finish();
            }
        });
        mOpenGGT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "m/ggt/index.html#!/business/index.html";
                AppUtil.startModel("ggt", url, OpenAccountHomeActivity.this);
                finish();
            }
        });
        mOpenRZRQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OpenAccountHomeActivity.this, "此功能正在开发中,敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        mOrderCardTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://wap.cebbank.com/pwap/MpOpenOnlineInit.do?Channel=0&ChannelID=102");
                intent.setData(content_url);
                startActivity(intent);
            }
        });
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
    @Override
    protected void initData() {
    }

    @Override
    protected void initViews() {

    }
}
