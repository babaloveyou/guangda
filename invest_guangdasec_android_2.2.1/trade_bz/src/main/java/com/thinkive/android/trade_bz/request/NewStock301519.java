package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewSuscribeBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 新股申购--查询申购额度
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/21
 */
public class NewStock301519 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_SUBSDRIBE = "Request301519_result";

    public NewStock301519(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301519");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        ArrayList<NewSuscribeBean> dataList = new ArrayList<NewSuscribeBean>();
        try {
            JSONArray jArray = jsonObject.getJSONArray("results");
            JSONObject tempJsonObject;
            NewSuscribeBean beanShen;
            for(int i = 0; i<jArray.length(); i++) {
                tempJsonObject = jArray.getJSONObject(i);
                beanShen = JsonParseUtils.createBean(NewSuscribeBean.class, tempJsonObject);
                dataList.add(beanShen);
            }
            if(dataList != null){
                bundle.putParcelableArrayList(BUNDLE_KEY_SUBSDRIBE, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(ERROR_INFO, mContext.getResources().
                        getString(R.string.toast_data_from_service_error_nodata));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
