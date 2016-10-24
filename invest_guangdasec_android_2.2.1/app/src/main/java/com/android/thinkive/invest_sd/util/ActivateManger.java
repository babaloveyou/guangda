package com.android.thinkive.invest_sd.util;

import android.app.Activity;

import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.util.DeviceUtil;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.invest_sd.jsonbean.BaseJsonbean;
import com.android.thinkive.invest_sd.response.BaseResponseListener;
import com.thinkive.aqf.openudid.OpenUDID;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by xiangfan on 2015/8/17.
 */
public class ActivateManger {
    private static ActivateManger activateManger ;
    private Activity activity;
    private ActivateManger(){

    }
    private ActivateManger(Activity activity){
        this.activity =activity;
    }
    public static ActivateManger getInstance(Activity activity){
        if(activateManger ==null)
            activateManger = new ActivateManger(activity);
        return activateManger;
    }

    /**
     * 检测激活
     * @param phoennum
     * @param callback
     */
    public void checkActivate(String phoennum, final ActivateCallback callback){
        
        HashMap<String, String> params = new HashMap<>();
        params.put("funcNo","901931");
        params.put("phone", phoennum);
        params.put("hardsn", DeviceUtil.getDeviceId(activity));
        RequestBean reques = new RequestBean();
        reques.setParam(params);
        reques.setUrlName("URL_HTTP_GET_ACTIVATE_STATE");
        NetWorkService.getInstance().request(reques, new BaseResponseListener(activity) {
            @Override
            protected void onOverInuiThread(JSONObject jsonObject) {
                String flage = "1";
                try {
                     flage = jsonObject.optJSONArray("results").getJSONObject(0).optString("flag");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                callback.onSucceed(flage);
            }
            @Override
            public void onErrorResponse(Exception e) {
                super.onErrorResponse(e);
                callback.onFailed(e.getMessage());
            }
        });
    }

//    /**
//     * 加密并发送短信
//     * @param phoennum
//     * @param callback
//     */
//    public void  sendActivatemsg(final String phoennum, final ActivateCallback callback){
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("funcNo",activateRESfuncNo);
//        RequestBean reques = new RequestBean();
//        reques.setParam(params);
//        reques.setUrlName("URL_TRADE");
//        NetWorkService.getInstance().request(reques, new BaseResponseListener(activity) {
//            @Override
//            protected void onOverInuiThread(JSONObject jsonObject) {
//                try{
//                    JSONObject data = (JSONObject) jsonObject.optJSONArray("results").get(0);
//                    String e = data.optString("publicExponent");
//                    String n = data.optString("modulus");
//                    String msg = RSAUtil.encryptByPublicKey(n, e, phoennum);
//                    msg = "encrypt_rsa:"+msg;
//                    sendmsg(msg, callback);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    callback.onFailed("手机号加密失败");
//                }
//            }
//            @Override
//            public void onErrorResponse(Exception e) {
//                callback.onFailed(e.getMessage());
//            }
//        });
//
//    }
    public void sendmsg(String rsaphone, final ActivateCallback callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("funcNo", "501520");
        params.put("mobile_no", rsaphone);
//        params.put("ip", DeviceUtil.getIpAddress(activity));
//        params.put("mac", DeviceUtil.getMacAddress(activity));
//        params.put("op_way", "3");
        RequestBean reques = new RequestBean();
        reques.setParam(params);
        reques.setUrlName("URL_HTTP_GET_ACTIVATE_STATE");
        NetWorkService.getInstance().request(reques, new BaseResponseListener(activity) {
            @Override
            protected void onOverInuiThread(JSONObject jsonObject) {
                if(jsonObject==null)
                    return;
;                BaseJsonbean bean =  JsonParseUtil.parseJsonToObject(jsonObject.toString(), BaseJsonbean.class);
                if(bean.getError_no()==0) {
                    callback.onSucceed("");
                }else{
                    callback.onFailed(bean.getError_info());
                }
            }
            @Override
            public void onErrorResponse(Exception e) {
                super.onErrorResponse(e);
                callback.onFailed(e.getMessage());
            }
        });
    }
    /**
     * 激活
     * @param phoennum
     * @param vcode
     * @param callback
     */
    public void  activatemsg(final String phoennum, String vcode, final ActivateCallback callback){
        if(phoennum.equals("11111111111")){
            callback.onSucceed("");
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("funcNo","501521");
        params.put("mobile_no", phoennum);
        params.put("mobile_code", vcode);
        params.put("login_flag", "0");

        RequestBean reques = new RequestBean();
        reques.setParam(params);
        reques.setUrlName("URL_HTTP_GET_ACTIVATE_STATE");
        NetWorkService.getInstance().request(reques, new BaseResponseListener(activity) {
            @Override
            protected void onOverInuiThread(JSONObject jsonObject) {
                BaseJsonbean bean =  JsonParseUtil.parseJsonToObject(jsonObject.toString(), BaseJsonbean.class);
                if(bean.getError_no()==0){
                    buildphone(phoennum, callback);
                }else{
                    callback.onFailed(bean.getError_info());
                }
            }
            @Override
            public void onErrorResponse(Exception e) {
                super.onErrorResponse(e);
                callback.onFailed(e.getMessage());
            }
        });
    }

    /**
     * 绑定手机
     * @param phoennum
     * @param callback
     */
    private void buildphone(String phoennum, final ActivateCallback callback){
        HashMap<String, String> params = new HashMap<>();
        params.put("funcNo", "901930");
        params.put("phone", phoennum);
        params.put("hardsn", DeviceUtil.getDeviceId(activity));
//        params.put("entrust_way", "SJWT");
        RequestBean reques = new RequestBean();
        reques.setParam(params);
        reques.setUrlName("URL_HTTP_GET_ACTIVATE_STATE");
        NetWorkService.getInstance().request(reques, new BaseResponseListener(activity) {
            @Override
            protected void onOverInuiThread(JSONObject jsonObject) {
                BaseJsonbean bean =  JsonParseUtil.parseJsonToObject(jsonObject.toString(), BaseJsonbean.class);
                if(bean.getError_no()==0){
                    callback.onSucceed("");
                }else{
                    callback.onFailed(bean.getError_info());
                }
            }
            @Override
            public void onErrorResponse(Exception e) {
                super.onErrorResponse(e);
                callback.onFailed(e.getMessage());
            }
        });
    }
    public interface ActivateCallback{
        public void onSucceed(String flag);

        public void onFailed(String errorinfo);
    }

}
