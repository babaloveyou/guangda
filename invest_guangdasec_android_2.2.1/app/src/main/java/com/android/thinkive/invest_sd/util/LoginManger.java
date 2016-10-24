package com.android.thinkive.invest_sd.util;

import android.app.Activity;
import android.preference.Preference;

import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.framework.util.PreferencesUtil;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.jsonbean.BaseJsonbean;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.android.volley.Request;
import com.thinkive.aqf.info.utils.ToastUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiangfan on 2015/11/4.
 */
public class LoginManger {
    private static LoginManger loginManger ;
    private Activity activity;
    private int exitcount = 0;
    private int requestexitcount = 0;
    private LoginManger(){

    }
    private LoginManger(Activity activity){
        this.activity =activity;
    }
    public static LoginManger getInstance(Activity activity){
        if(loginManger ==null)
            loginManger = new LoginManger(activity);
        return loginManger;
    }

    public void ExitLogin(LoginCallback callback){
        exitcount = 0;
        requestexitcount=0;
       HashMap params = new HashMap();
        params.put("phone", PreferencesUtil.getString(activity,Constant.MOBILE_NUMBER));
        params.put("entrust_way", "SJWT");
        params.put("funcNo","1000001");
        exit("URL_EXIT_FUND", params, "我的富尊", callback);
        params.put("funcNo","1000001");
        exit("URL_EXIT_MALL", params, "商城",callback);
//        exit("URL_EXIT_TRADE", params, "交易",callback);
        params.clear();
        params.put("phone", PreferencesUtil.getString(activity, Constant.MOBILE_NUMBER));
        params.put("exitFlag","exit");
        params.put("funcNo","600005");
        exit("URL_EXIT_XD", params, "小贷",callback);
        params.clear();
//        exit("URL_EXIT_GGT", params, "港股通",callback);
    }

    private void exit(String urlName,HashMap params, final String name, final LoginCallback callback){
            requestexitcount++;
        RequestBean requestBean  = new RequestBean();
        requestBean.setUrlName(urlName);
        requestBean.setParam(params);
        NetWorkService.getInstance().request(requestBean, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                BaseJsonbean bean = JsonParseUtil.parseJsonToObject(jsonObject,BaseJsonbean.class);
                if(bean.getError_no() == 0){
                    exitcount ++;
                    if(exitcount == requestexitcount){
                        callback.onSucceed();
                    }
                }else{
                    ToastUtils.Toast(activity,name+"注销失败："+bean.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(activity,name+"注销失败,请检查网络" );
            }
        });

    }

    public interface LoginCallback{
        public void onSucceed();
    }
}
