package com.android.thinkive.invest_sd.response;

import android.app.Activity;
import android.text.TextUtils;

import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;

import org.json.JSONObject;

/**
 * Created by xiangfan on 2015/7/14.
 */
public abstract class BaseResponseListener implements ResponseListener<JSONObject> {
    private Activity activity;
    private String chacekey;
    public BaseResponseListener(Activity activity) {
        this.activity = activity;
    }

    public BaseResponseListener(Activity activity, String chacekey) {
        this.activity = activity;
        this.chacekey = chacekey;
    }

    @Override
    public void onResponse(final JSONObject jsonObject) {
        chacheJsonvalue(jsonObject);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onOverInuiThread(jsonObject);
            }
        });
    }
    @Override
    public void onErrorResponse(Exception e) {

    }
    protected abstract void onOverInuiThread(JSONObject jsonObject);


    protected void chacheJsonvalue(JSONObject jsonObject){
        if(!TextUtils.isEmpty(chacekey))
        {
            FileCacheToSdcardUtil.saveImageResponseJson(chacekey, jsonObject,activity);
        }
    }

}
