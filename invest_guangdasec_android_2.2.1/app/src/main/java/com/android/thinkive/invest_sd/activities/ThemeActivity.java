package com.android.thinkive.invest_sd.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.theme.ThemeManager;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by zhuduanchang on 2015/6/26.
 */
public class ThemeActivity extends BaseActivity {
    private ArrayList<String> mThemeList = new ArrayList<String>();
    ListView mListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        CoreApplication.getInstance().pushActivity(this.getClass().getName(), this);
        mThemeList = ThemeManager.getInstance(this).getAllThemeName();
        initViews();
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initViews() {
        mListview = (ListView)findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mThemeList);
        mListview.setAdapter(arrayAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = mThemeList.get(position);
                ThemeManager.saveTheme(ThemeActivity.this, path);
                ThemeManager.getInstance(ThemeActivity.this).loadThemeInfo();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    getWindow().setStatusBarColor(Color.RED);
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CoreApplication.getInstance().popActivity(this.getClass().getName());
    }

    @Override
    protected void setTheme() {

    }
}
