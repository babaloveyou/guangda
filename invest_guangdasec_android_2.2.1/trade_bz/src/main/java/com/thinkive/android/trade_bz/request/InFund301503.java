package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_in.bean.InFundSercuritiesPositionsQueryBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Description：证券持仓查询（301503） <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/12 <br>
 */

public class InFund301503 extends BaseNormalRequest{
    public static final String BUNDLE_KEY_301503 = "InFund301503";
    public InFund301503(HashMap<String,String> paramMap,IRequestAction action) {
        super(action);
        paramMap.put("funcNo","301503");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle=new Bundle();
        try {
            JSONArray resultTargets = jsonObject.getJSONArray("dsName");
            JSONArray jsonArray = jsonObject.getJSONArray(resultTargets.get(0).toString());
            ArrayList<InFundSercuritiesPositionsQueryBean> inFundSercuritiesPositionsQueryBeanArrayList = JsonParseUtils.createBeanList(InFundSercuritiesPositionsQueryBean.class,jsonArray);
            bundle.putParcelableArrayList(BUNDLE_KEY_301503,inFundSercuritiesPositionsQueryBeanArrayList);
            transferAction(REQUEST_SUCCESS,bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
