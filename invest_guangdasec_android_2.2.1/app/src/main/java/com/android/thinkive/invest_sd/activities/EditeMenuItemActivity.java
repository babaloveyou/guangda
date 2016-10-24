package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.adapter.FristMenuEditAdapter;
import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.jsonbean.FristPageCicrleItemJsonbean;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by xiangfan on 2015/9/29.
 */
public class EditeMenuItemActivity extends Activity {
    private LinearLayout ll_edit_item_tctm;
    private TreeMap<Integer,List<FristPageCicrleItemBean>> datamap;
    private LayoutInflater mInfater;
    private FristMenuEditAdapter.EditeClickItemListener editeClickItemListener;
    FristPageCicrleItemJsonbean jsonbean;
    private boolean isichange =false;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(this);
        setContentView(R.layout.activity_meun_edit_page);
        mInfater = LayoutInflater.from(this);

        editeClickItemListener = new FristMenuEditAdapter.EditeClickItemListener() {
            @Override
            public void click(FristPageCicrleItemBean bean) {
                for(FristPageCicrleItemBean bean1 :jsonbean.getResults()){
                    if(bean1.getId()==bean.getId()){
                        isichange = true;
                        bean1.setIs_default(bean.getIs_default());
                    }
                }
            }
        };
        findViews();
        initViews();
        initData();
        setListeners();
    }

    protected void findViews() {
        ll_edit_item_tctm = (LinearLayout) findViewById(R.id.ll_edit_item_tctm);
    }

    protected void setListeners() {
       findViewById(R.id.iv_login_back).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.putExtra("isichange", isichange);
               if (isichange) {
                   try {
                       String a = new ObjectMapper().writeValueAsString(jsonbean);
                       FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_MENU, new JSONObject(a), EditeMenuItemActivity.this);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
               setResult(RESULT_OK, intent);
               finish();
           }
       });
    }

    protected void initData() {
        JSONObject data = FileCacheToSdcardUtil.getImageResponseJson(Constant.CACHA_FRISTPAGE_MENU,EditeMenuItemActivity.this);
        if(data!=null) {
             jsonbean = JsonParseUtil.parseJsonToObject( data, FristPageCicrleItemJsonbean.class);
            datamap = new TreeMap<Integer,List<FristPageCicrleItemBean>>();
            for(FristPageCicrleItemBean bean :jsonbean.getResults()){
                if(datamap.containsKey(bean.getTctm_id())){
                    datamap.get(bean.getTctm_id()).add(bean);
                }else{
                    List<FristPageCicrleItemBean> list = new ArrayList<FristPageCicrleItemBean>();
                    list.add(bean);
                    datamap.put(bean.getTctm_id(),list);
                }
            }
                int i = 0;
            for(List<FristPageCicrleItemBean> list : datamap.values()){
                View v = mInfater.inflate(R.layout.item_edit_menu_gridview,null);
                TextView name = (TextView) v.findViewById(R.id.tv_tctm_name);
                GridView gridView = (GridView) v.findViewById(R.id.gridview_munu_item);
                if(i== datamap.values().size()-1){
                View line = v.findViewById(R.id.view_edit_page_line);
                    line.setVisibility(View.GONE);
                }
                name.setText(list.get(0).getTctm_name());
                FristMenuEditAdapter adapter = new FristMenuEditAdapter(this);
                adapter.setlist(list);
                adapter.setListener(editeClickItemListener);
                gridView.setAdapter(adapter);
                i++;
                ll_edit_item_tctm.addView(v);
            }
        }

    }

    protected void initViews() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("isichange", isichange);
            if(isichange){
                try {
                    String a = new ObjectMapper().writeValueAsString(jsonbean);
                    FileCacheToSdcardUtil.saveImageResponseJson(Constant.CACHA_FRISTPAGE_MENU, new JSONObject(a),EditeMenuItemActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
}
