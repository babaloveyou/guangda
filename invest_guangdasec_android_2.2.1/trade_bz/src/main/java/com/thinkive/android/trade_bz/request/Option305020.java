package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionCoveredSecuritiesTransferBean;
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
 * Description：个股期权 备兑证券划转（冻结和解冻） 305020 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/30 <br>
 */
public class Option305020 extends BaseOptionRequest{
    public static final String OPTION_BUNDLE_COVERED_SERCURITIES_TRANSFER = "Option305020";
    /**
     * 构造方法
     * @param paramMap
     * @param action
     */
    public Option305020(HashMap<String,String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo","305020");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    /**
     * 对成功返回的结果进行初步处理
     * @param jsonObject 服务器返回的全部数据
     */
    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle=new Bundle();
        try {
            JSONArray resultTargets = jsonObject.getJSONArray("dsName");
            JSONArray jsonArray = jsonObject.getJSONArray(resultTargets.get(0).toString());
            ArrayList<OptionCoveredSecuritiesTransferBean> optionCoveredSecuritiesTransferBeens = JsonParseUtils.createBeanList(OptionCoveredSecuritiesTransferBean.class,jsonArray);
            bundle.putParcelableArrayList(OPTION_BUNDLE_COVERED_SERCURITIES_TRANSFER,optionCoveredSecuritiesTransferBeens);
            transferAction(REQUEST_SUCCESS,bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
