package com.android.thinkive.invest_sd.util;

import android.content.Context;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiangfan on 2015/11/5.
 */
public class AppUtil {

    public static void startModel(String moduleName,Context context){
        startModel(moduleName, "", -1,context);
    }
    public static void startModel(String moduleName,String toPage,Context context){
        startModel(moduleName,toPage,-1,context);
    }
    public static void startModel(String moduleName,String toPage,int product_id,Context context){
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
        MessageManager.getInstance(context).sendMessage(msg);
    }
}
